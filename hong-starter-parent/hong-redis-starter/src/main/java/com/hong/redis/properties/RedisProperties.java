package com.hong.redis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * Description redis配置,包含单点和集群的配置 Metadata 元数据
 */
@ConfigurationProperties(prefix = RedisProperties.PREFIX)
@Data
public class RedisProperties {

	/**
	 * 配置前缀
	 */
	public static final String PREFIX = "spring.redis";

	/**
	 * 节点列表 | 集群配置
	 */
	private Map<String, List<String>> cluster;
	/**
	 * 节点列表 | 单点配置
	 */
	private String host;
	/**
	 * 节点列表 | 单点配置
	 */
	private String port;
	/**
	 * 密码
	 */
	private String password;
}