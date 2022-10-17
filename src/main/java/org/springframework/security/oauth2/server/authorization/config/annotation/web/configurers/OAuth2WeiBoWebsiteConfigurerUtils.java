package org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryWeiBoWebsiteService;
import org.springframework.security.oauth2.server.authorization.client.WeiBoWebsiteService;
import org.springframework.security.oauth2.server.authorization.properties.WeiBoWebsiteProperties;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

/**
 * 微博开放平台 网站应用 OAuth 2.0 配置器的实用方法。
 *
 * @author xuxiaowei
 * @since 0.0.1
 * @see OAuth2ConfigurerUtils
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class OAuth2WeiBoWebsiteConfigurerUtils {

	public static OAuth2AuthorizationService getAuthorizationService(HttpSecurity httpSecurity) {
		return OAuth2ConfigurerUtils.getAuthorizationService(httpSecurity);
	}

	public static OAuth2TokenGenerator<? extends OAuth2Token> getTokenGenerator(HttpSecurity httpSecurity) {
		return OAuth2ConfigurerUtils.getTokenGenerator(httpSecurity);
	}

	public static WeiBoWebsiteService getWeiBoWebsiteService(HttpSecurity httpSecurity) {
		WeiBoWebsiteService weiBoWebsiteService = httpSecurity.getSharedObject(WeiBoWebsiteService.class);
		if (weiBoWebsiteService == null) {
			weiBoWebsiteService = OAuth2ConfigurerUtils.getOptionalBean(httpSecurity, WeiBoWebsiteService.class);
			if (weiBoWebsiteService == null) {
				WeiBoWebsiteProperties weiBoWebsiteProperties = OAuth2ConfigurerUtils.getOptionalBean(httpSecurity,
						WeiBoWebsiteProperties.class);
				weiBoWebsiteService = new InMemoryWeiBoWebsiteService(weiBoWebsiteProperties);
			}
		}
		return weiBoWebsiteService;
	}

}
