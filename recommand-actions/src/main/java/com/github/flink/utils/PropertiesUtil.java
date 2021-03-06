package com.github.flink.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 获取配置信息
 * @Author: zlzhang0122
 * @Date: 2019/9/12 18:45
 */
public class PropertiesUtil {
	private static final Logger logger = Logger.getLogger(PropertiesUtil.class);

	private final static String CONF_NAME = "config.properties";

	private static Properties contextProperties;

	static {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONF_NAME);
		contextProperties = new Properties();
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
			contextProperties.load(inputStreamReader);
		} catch (IOException e) {
			System.err.println("flink资源文件加载失败!");
			e.printStackTrace();
		}

		System.out.println("flink资源文件加载成功!");
	}

	public static String getStrValue(String key) {
		return contextProperties.getProperty(key);
	}

	public static int getIntValue(String key) throws Exception {
		String strValue = getStrValue(key);

		//todo check
		if(StringUtils.isNumeric(strValue)){
			return Integer.parseInt(strValue);
		}else{
			logger.error("PropertiesUtil getIntValue error, value is not a int value!");
			throw new Exception("PropertiesUtil getIntValue error, value is not a int value!");
		}
	}

	//获取0.8版本kafka配置信息
	public static Properties getKafka08Properties(String bootstrapServers, String zookeeperAddr, String groupId){
		Properties properties = getKafkaProperties(groupId);

		// only required for Kafka 0.8
		properties.setProperty("zookeeper.connect", getStrValue("kafka.zookeeper.connect"));

		return properties;
	}

	//获取kafka配置信息
	public static Properties getKafkaProperties(String groupId){
		Properties properties = new Properties();

		properties.setProperty("bootstrap.servers", getStrValue("kafka.bootstrap.servers"));
		properties.setProperty("group.id", groupId);

		return properties;
	}
}