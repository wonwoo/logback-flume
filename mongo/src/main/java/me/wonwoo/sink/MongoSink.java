package me.wonwoo.sink;

import com.google.common.base.Throwables;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang.StringUtils;
import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.bson.Document;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static me.wonwoo.sink.MongoSinkConstants.*;

/**
 * Created by wonwoolee on 2017. 9. 10..
 */
public class MongoSink extends AbstractSink implements Configurable {

  private MongoClient client;
  private MongoCollection<Document> collection;
  private String host;
  private Integer port;
  private String username;
  private char[] password;
  private String database;
  private String collectionName;


  @Override
  public synchronized void start() {
    this.client = createNetworkMongoClient(null);
    MongoDatabase mongoDatabase = client.getDatabase(database);
    this.collection = mongoDatabase.getCollection(collectionName);
    super.start();
  }

  private MongoClient createNetworkMongoClient(MongoClientOptions options) {
    if (options == null) {
      options = MongoClientOptions.builder().build();
    }

    if (hasCredential()) {
      List<MongoCredential> credentials = new ArrayList<>();
      credentials.add(MongoCredential.createCredential(this.username, database,
          this.password));
      return new MongoClient(
          Collections.singletonList(new ServerAddress(host, port)), credentials,
          options);
    }
    return new MongoClient(Collections.singletonList(new ServerAddress(host, port)), options);
  }


  public boolean hasCredential() {
    return username != null && password != null;
  }

  @Override
  public synchronized void stop() {
    if (this.client != null) {
      this.client.close();
    }

    super.stop();
  }

  //TODO mongo 는 json 형태만..
  @Override
  public Status process() throws EventDeliveryException {
    Status result = Status.READY;
    Channel channel = getChannel();

    Transaction transaction = null;
    try {
      transaction = channel.getTransaction();
      transaction.begin();
      Event event = channel.take();
      String jsonEvent = new String(event.getBody(), StandardCharsets.UTF_8);
      collection.insertOne(Document.parse(jsonEvent));
      transaction.commit();

    } catch (Exception ex) {
      String errorMsg = "Failed to publish events : " + ex;
      if (transaction != null) {
        try {
          transaction.rollback();
        } catch (Exception e) {
          throw Throwables.propagate(e);
        }
      }
      throw new EventDeliveryException(errorMsg, ex);
    }
    return result;
  }

  @Override
  public void configure(Context context) {
    this.username = context.getString(USERNAME);
    String password = context.getString(PASSWORD);
    if (password != null) {
      this.password = password.toCharArray();
    }

    String host = context.getString(HOST);
    this.host = StringUtils.isBlank(host) ? DEFAULT_HOST : host;

    Integer port = context.getInteger(PORT);
    this.port = context.getInteger(PORT) == null ? DEFAULT_PORT : port;

    String database = context.getString(DATABASE);
    this.database = StringUtils.isBlank(database) ? DEFAULT_DATABASE : database;

    String collectionName = context.getString(COLLECTION_NAME);
    this.collectionName = StringUtils.isBlank(collectionName) ? DEFAULT_COLLECTION_NAME : collectionName;
  }
}
