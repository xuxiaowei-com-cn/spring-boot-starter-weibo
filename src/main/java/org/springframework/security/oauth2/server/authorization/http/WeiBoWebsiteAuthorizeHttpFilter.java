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
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.endpoint.OAuth2WeiBoParameterNames;
import org.springframework.security.oauth2.server.authorization.client.WeiBoWebsiteService;
import org.springframework.security.oauth2.server.authorization.properties.WeiBoWebsiteProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微博开放平台 网站应用 跳转到微博授权页面
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@Component
public class WeiBoWebsiteAuthorizeHttpFilter extends HttpFilter {

	public static final String PREFIX_URL = "/weibo/authorize";

	public static final String AUTHORIZE_URL = "https://api.weibo.com/oauth2/authorize"
			+ "?client_id=%s&redirect_uri=%s&scope=%s&state=%s&display=%s&forcelogin=%s&language=%s";

	private WeiBoWebsiteProperties weiBoWebsiteProperties;

	private WeiBoWebsiteService weiBoWebsiteService;

	/**
	 * 微博开放平台 网站应用 授权前缀
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

			String redirectUri = weiBoWebsiteService.getRedirectUriByAppid(appid);

			String binding = request.getParameter(OAuth2WeiBoParameterNames.BINDING);
			String scope = request.getParameter(OAuth2ParameterNames.SCOPE);
			String display = request.getParameter(OAuth2WeiBoParameterNames.DISPLAY);
			String forcelogin = request.getParameter(OAuth2WeiBoParameterNames.FORCELOGIN);
			String language = request.getParameter(OAuth2WeiBoParameterNames.LANGUAGE);

			String state = weiBoWebsiteService.stateGenerate(request, response, appid);
			weiBoWebsiteService.storeBinding(request, response, appid, state, binding);
			weiBoWebsiteService.storeUsers(request, response, appid, state, binding);

			String url = String.format(AUTHORIZE_URL, appid, redirectUri, scope, state, display, forcelogin, language);

			log.info("redirectUrl：{}", url);

			response.sendRedirect(url);
			return;
		}

		super.doFilter(request, response, chain);
	}

}
