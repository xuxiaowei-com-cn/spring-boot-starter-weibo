package org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.client.InMemoryWeiBoWebsiteService;
import org.springframework.security.oauth2.server.authorization.client.WeiBoWebsiteService;
import org.springframework.security.oauth2.server.authorization.properties.WeiBoWebsiteProperties;

/**
 * 微博开放平台 网站应用 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class OAuth2WeiBoWebsiteConfiguration {

	private WeiBoWebsiteProperties weiBoWebsiteProperties;

	@Autowired
	public void setWeiBoWebsiteProperties(WeiBoWebsiteProperties weiBoWebsiteProperties) {
		this.weiBoWebsiteProperties = weiBoWebsiteProperties;
	}

	@Bean
	@ConditionalOnMissingBean
	public WeiBoWebsiteService weiBoWebsiteService() {
		return new InMemoryWeiBoWebsiteService(weiBoWebsiteProperties);
	}

}
