package me.wonwoo.flume.channel;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChannelAttrTests {

	@Test
	public void channelAttr() {
		ChannelAttr channelAttr = new ChannelAttr();
		channelAttr.setCapacity("1000");
		channelAttr.setKeepAlive("10000");
		channelAttr.setTransactionCapacity("2000");
		channelAttr.setCheckpointInterval("10000");
		channelAttr.setMaxFileSize("10000000");
		channelAttr.setMinimumRequiredSpace("20000");
		channelAttr.setCheckpointDir("/user/test");
		channelAttr.setBackupCheckpointDir("/user/backup");
		channelAttr.setDataDirs("/user/data");
		channelAttr.setUseLogReplayV1("false");
		channelAttr.setUseFastReplay("false");
		channelAttr.setEncryptionActiveKey("test");
		channelAttr.setEncryptionCipherProvider("test");
		channelAttr.setUseDualCheckpoints("test");
		channelAttr.setCompressBackupCheckpoint("point");
		channelAttr.setFsyncPerTransaction("test");
		channelAttr.setFsyncInterval("1000");
		channelAttr.setCheckpointOnClose("test");

		assertThat(channelAttr.getCapacity()).isEqualTo("1000");
		assertThat(channelAttr.getKeepAlive()).isEqualTo("10000");
		assertThat(channelAttr.getTransactionCapacity()).isEqualTo("2000");
		assertThat(channelAttr.getCheckpointInterval()).isEqualTo("10000");
		assertThat(channelAttr.getMaxFileSize()).isEqualTo("10000000");
		assertThat(channelAttr.getMinimumRequiredSpace()).isEqualTo("20000");
		assertThat(channelAttr.getCheckpointDir()).isEqualTo("/user/test");
		assertThat(channelAttr.getBackupCheckpointDir()).isEqualTo("/user/backup");
		assertThat(channelAttr.getDataDirs()).isEqualTo("/user/data");
		assertThat(channelAttr.getUseLogReplayV1()).isEqualTo("false");
		assertThat(channelAttr.getUseFastReplay()).isEqualTo("false");
		assertThat(channelAttr.getEncryptionActiveKey()).isEqualTo("test");
		assertThat(channelAttr.getEncryptionCipherProvider()).isEqualTo("test");
		assertThat(channelAttr.getUseDualCheckpoints()).isEqualTo("test");
		assertThat(channelAttr.getCompressBackupCheckpoint()).isEqualTo("point");
		assertThat(channelAttr.getFsyncPerTransaction()).isEqualTo("test");
		assertThat(channelAttr.getFsyncInterval()).isEqualTo("1000");
		assertThat(channelAttr.getCheckpointOnClose()).isEqualTo("test");
	}

}