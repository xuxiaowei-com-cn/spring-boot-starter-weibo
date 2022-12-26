package org.springframework.security.oauth2.server.authorization.authentication;

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

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 微博开放平台 网站应用 OAuth2 身份验证令牌
 *
 * @author xuxiaowei
 * @since 0.0.1
 * @see OAuth2AuthorizationCodeAuthenticationToken 用于 OAuth 2.0 授权代码授予的
 * {@link Authentication} 实现。
 * @see OAuth2RefreshTokenAuthenticationToken 用于 OAuth 2.0 刷新令牌授予的 {@link Authentication}
 * 实现。
 * @see OAuth2ClientCredentialsAuthenticationToken 用于 OAuth 2.0
 * 客户端凭据授予的{@link Authentication} 实现。
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class OAuth2WeiBoWebsiteAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	/**
	 * 授权类型：微博开放平台 网站应用
	 */
	public static final AuthorizationGrantType WEIBO_WEBSITE = new AuthorizationGrantType("weibo_website");

	/**
	 * AppID
	 */
	@Getter
	private final String appid;

	@Getter
	private final String redirectUri;

	/**
	 *
	 *
	 */
	@Getter
	private final String code;

	/**
	 * @see OAuth2ParameterNames#SCOPE
	 */
	@Getter
	private final String scope;

	@Getter
	private final String remoteAddress;

	@Getter
	private final String sessionId;

	@Getter
	private final String state;

	@Getter
	private final String binding;

	/**
	 * 子类构造函数。
	 * @param clientPrincipal 经过身份验证的客户端主体
	 * @param additionalParameters 附加参数
	 * @param appid AppID
	 * @param code 授权码
	 * @param scope {@link OAuth2ParameterNames#SCOPE}，授权范围
	 * @param remoteAddress 用户IP
	 * @param sessionId SessionID
	 * @param binding 是否绑定，需要使用者自己去拓展
	 */
	public OAuth2WeiBoWebsiteAuthenticationToken(Authentication clientPrincipal,
			Map<String, Object> additionalParameters, String appid, String redirectUri, String code, String scope,
			String remoteAddress, String sessionId, String state, String binding) {
		super(OAuth2WeiBoWebsiteAuthenticationToken.WEIBO_WEBSITE, clientPrincipal, additionalParameters);
		Assert.hasText(appid, "appid 不能为空");
		Assert.hasText(redirectUri, "redirectUri 不能为空");
		Assert.hasText(code, "code 不能为空");
		this.appid = appid;
		this.redirectUri = redirectUri;
		this.code = code;
		this.scope = scope;
		this.remoteAddress = remoteAddress;
		this.sessionId = sessionId;
		this.state = state;
		this.binding = binding;
	}

}
