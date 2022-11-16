package org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration;

/*-
 * #%L
 * spring-boot-starter-weibo
 * %%
 * Copyright (C) 2022 徐晓伟工作室
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
