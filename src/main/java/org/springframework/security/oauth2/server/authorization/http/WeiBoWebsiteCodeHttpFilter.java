package org.springframework.security.oauth2.server.authorization.http;

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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.endpoint.*;
import org.springframework.security.oauth2.server.authorization.client.WeiBoWebsiteService;
import org.springframework.security.oauth2.server.authorization.properties.WeiBoWebsiteProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.oauth2.server.authorization.authentication.OAuth2WeiBoWebsiteAuthenticationToken.WEIBO_WEBSITE;

/**
 * 微博开放平台 网站应用 授权码接收服务
 *
 * @author xuxiaowei
 * @since 0.0.1
 * @see OAuth2AccessTokenResponse
 * @see DefaultOAuth2AccessTokenResponseMapConverter
 * @see DefaultMapOAuth2AccessTokenResponseConverter
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@Component
public class WeiBoWebsiteCodeHttpFilter extends HttpFilter {

	public static final String PREFIX_URL = "/weibo/code";

	public static final String TOKEN_URL = "/oauth2/token?grant_type={grant_type}&appid={appid}&code={code}&state={state}&remote_address={remote_address}&session_id={session_id}&binding={binding}&redirect_uri={redirect_uri}";

	private WeiBoWebsiteProperties weiBoWebsiteProperties;

	private WeiBoWebsiteService weiBoWebsiteService;

	/**
	 * 微博开放平台 网站应用 使用code获取授权凭证URL前缀
	 */
	private String prefixUrl = PREFIX_URL;

	@Autowired
	public void setWeiBoWebsiteProperties(WeiBoWebsiteProperties weiBoWebsiteProperties) {
		this.weiBoWebsiteProperties = weiBoWebsiteProperties;
	}

	@Autowired
	public void setWeiBoWebsiteService(WeiBoWebsiteService weiBoWebsiteService) {
		this.weiBoWebsiteService = weiBoWebsiteService;
	}

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String requestUri = request.getRequestURI();
		AntPathMatcher antPathMatcher = new AntPathMatcher();
		boolean match = antPathMatcher.match(prefixUrl + "/*", requestUri);
		if (match) {
			log.info("requestUri：{}", requestUri);

			String appid = requestUri.replace(prefixUrl + "/", "");
			String code = request.getParameter(OAuth2ParameterNames.CODE);
			String state = request.getParameter(OAuth2ParameterNames.STATE);
			String grantType = WEIBO_WEBSITE.getValue();

			boolean valid = weiBoWebsiteService.stateValid(request, response, appid, code, state);
			if (!valid) {
				return;
			}

			String binding = weiBoWebsiteService.getBinding(request, response, appid, code, state);

			WeiBoWebsiteProperties.WeiBoWebsite oplatformWebsite = weiBoWebsiteService.getWeiBoWebsiteByAppid(appid);

			String clientId = oplatformWebsite.getClientId();
			String clientSecret = oplatformWebsite.getClientSecret();
			String tokenUrlPrefix = oplatformWebsite.getTokenUrlPrefix();
			String scope = oplatformWebsite.getScope();
			String redirectUriPrefix = oplatformWebsite.getRedirectUriPrefix();

			String remoteHost = request.getRemoteHost();
			HttpSession session = request.getSession(false);

			Map<String, String> uriVariables = new HashMap<>(8);
			uriVariables.put(OAuth2ParameterNames.GRANT_TYPE, grantType);
			uriVariables.put(OAuth2WeiBoWebsiteParameterNames.APPID, appid);
			uriVariables.put(OAuth2ParameterNames.CODE, code);
			uriVariables.put(OAuth2ParameterNames.STATE, state);
			uriVariables.put(OAuth2ParameterNames.SCOPE, scope);
			uriVariables.put(OAuth2WeiBoWebsiteParameterNames.REMOTE_ADDRESS, remoteHost);
			uriVariables.put(OAuth2WeiBoWebsiteParameterNames.SESSION_ID, session == null ? "" : session.getId());
			uriVariables.put(OAuth2WeiBoWebsiteParameterNames.BINDING, binding);

			OAuth2AccessTokenResponse oauth2AccessTokenResponse = weiBoWebsiteService.getOAuth2AccessTokenResponse(
					request, response, clientId, clientSecret, tokenUrlPrefix, TOKEN_URL, appid, redirectUriPrefix,
					uriVariables);
			if (oauth2AccessTokenResponse == null) {
				return;
			}

			weiBoWebsiteService.sendRedirect(request, response, uriVariables, oauth2AccessTokenResponse,
					oplatformWebsite);
			return;
		}

		super.doFilter(request, response, chain);
	}

}
