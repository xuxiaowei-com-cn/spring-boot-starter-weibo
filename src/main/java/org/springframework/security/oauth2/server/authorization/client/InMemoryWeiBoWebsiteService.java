package org.springframework.security.oauth2.server.authorization.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.WeiBoWebsiteAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.endpoint.OAuth2WeiBoParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.exception.AppidWeiBoException;
import org.springframework.security.oauth2.server.authorization.exception.RedirectUriWeiBoException;
import org.springframework.security.oauth2.server.authorization.exception.RedirectWeiBoException;
import org.springframework.security.oauth2.server.authorization.properties.WeiBoWebsiteProperties;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2WeiBoWebsiteEndpointUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 微博开放平台 网站应用 账户服务接口 基于内存的实现
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class InMemoryWeiBoWebsiteService implements WeiBoWebsiteService {

	private final WeiBoWebsiteProperties weiBoWebsiteProperties;

	public InMemoryWeiBoWebsiteService(WeiBoWebsiteProperties weiBoWebsiteProperties) {
		this.weiBoWebsiteProperties = weiBoWebsiteProperties;
	}

	/**
	 * 根据 appid 获取重定向的地址
	 * @param appid 开放平台 网站应用 ID
	 * @return 返回重定向的地址
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public String getRedirectUriByAppid(String appid) throws OAuth2AuthenticationException {
		WeiBoWebsiteProperties.WeiBoWebsite weiBoWebsite = getWeiBoWebsiteByAppid(appid);
		String redirectUriPrefix = weiBoWebsite.getRedirectUriPrefix();

		if (StringUtils.hasText(redirectUriPrefix)) {
			return UriUtils.encode(redirectUriPrefix + "/" + appid, StandardCharsets.UTF_8);
		}
		else {
			OAuth2Error error = new OAuth2Error(OAuth2WeiBoWebsiteEndpointUtils.ERROR_CODE, "重定向地址前缀不能为空", null);
			throw new RedirectUriWeiBoException(error);
		}
	}

	/**
	 * 生成状态码
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 网站应用 ID
	 * @return 返回生成的授权码
	 */
	@Override
	public String stateGenerate(HttpServletRequest request, HttpServletResponse response, String appid) {
		return UUID.randomUUID().toString();
	}

	/**
	 * 储存绑定参数
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 网站应用 ID
	 * @param state 状态码
	 * @param binding 绑定参数
	 */
	@Override
	public void storeBinding(HttpServletRequest request, HttpServletResponse response, String appid, String state,
			String binding) {

	}

	/**
	 * 储存操作用户
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 网站应用 ID
	 * @param state 状态码
	 * @param binding 绑定参数
	 */
	@Override
	public void storeUsers(HttpServletRequest request, HttpServletResponse response, String appid, String state,
			String binding) {

	}

	/**
	 * 状态码验证（返回 {@link Boolean#FALSE} 时，将终止后面需要执行的代码）
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 网站应用 ID
	 * @param code 授权码
	 * @param state 状态码
	 * @return 返回 状态码验证结果
	 */
	@Override
	public boolean stateValid(HttpServletRequest request, HttpServletResponse response, String appid, String code,
			String state) {
		return true;
	}

	/**
	 * 获取 绑定参数
	 * @param request 请求
	 * @param response 响应
	 * @param appid 开放平台 网站应用 ID
	 * @param code 授权码
	 * @param state 状态码
	 * @return 返回 绑定参数
	 */
	@Override
	public String getBinding(HttpServletRequest request, HttpServletResponse response, String appid, String code,
			String state) {
		return null;
	}

	/**
	 * 根据 appid 获取 微博开放平台 网站应用属性配置
	 * @param appid 公众号ID
	 * @return 返回 微博开放平台 网站应用属性配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public WeiBoWebsiteProperties.WeiBoWebsite getWeiBoWebsiteByAppid(String appid)
			throws OAuth2AuthenticationException {
		List<WeiBoWebsiteProperties.WeiBoWebsite> list = weiBoWebsiteProperties.getList();
		if (list == null) {
			OAuth2Error error = new OAuth2Error(OAuth2WeiBoWebsiteEndpointUtils.ERROR_CODE, "appid 未配置", null);
			throw new AppidWeiBoException(error);
		}

		for (WeiBoWebsiteProperties.WeiBoWebsite weiBoWebsite : list) {
			if (appid.equals(weiBoWebsite.getAppid())) {
				return weiBoWebsite;
			}
		}
		OAuth2Error error = new OAuth2Error(OAuth2WeiBoWebsiteEndpointUtils.ERROR_CODE, "未匹配到 appid", null);
		throw new AppidWeiBoException(error);
	}

	/**
	 * 获取 OAuth 2.1 授权 Token（如果不想执行此方法后面的内容，可返回 null）
	 * @param request 请求
	 * @param response 响应
	 * @param tokenUrlPrefix 获取 Token URL 前缀
	 * @param tokenUrl Token URL
	 * @param uriVariables 参数
	 * @return 返回 OAuth 2.1 授权 Token
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
	@Override
	public OAuth2AccessTokenResponse getOAuth2AccessTokenResponse(HttpServletRequest request,
			HttpServletResponse response, String tokenUrlPrefix, String tokenUrl, String appid,
			String redirectUriPrefix, @NonNull Map<String, String> uriVariables) throws OAuth2AuthenticationException {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

		RestTemplate restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		messageConverters.add(5, new OAuth2AccessTokenResponseHttpMessageConverter());

		uriVariables.put(OAuth2ParameterNames.REDIRECT_URI, redirectUriPrefix + "/" + appid);

		return restTemplate.postForObject(tokenUrlPrefix + tokenUrl, httpEntity, OAuth2AccessTokenResponse.class,
				uriVariables);
	}

	/**
	 * 根据 AppID、code、accessTokenUrl 获取Token
	 * @param appid AppID
	 * @param code 授权码
	 * @param state 状态码
	 * @param binding 是否绑定，需要使用者自己去拓展
	 * @param accessTokenUrl 通过 code 换取网页授权 access_token 的 URL
	 * @param userinfoUrl 通过 access_token 获取用户个人信息
	 * @param remoteAddress 用户IP
	 * @param sessionId SessionID
	 * @return 返回 微博授权结果
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public WeiBoWebsiteTokenResponse getAccessTokenResponse(String appid, String code, String state, String binding,
			String accessTokenUrl, String userinfoUrl, String redirectUri, String remoteAddress, String sessionId)
			throws OAuth2AuthenticationException {
		Map<String, String> uriVariables = new HashMap<>(8);
		uriVariables.put(OAuth2ParameterNames.CLIENT_ID, appid);

		String secret = getSecretByAppid(appid);

		uriVariables.put(OAuth2ParameterNames.CLIENT_SECRET, secret);
		uriVariables.put(OAuth2ParameterNames.REDIRECT_URI, redirectUri);
		uriVariables.put(OAuth2ParameterNames.CODE, code);

		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		messageConverters.set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

		String forObject = restTemplate.postForObject(accessTokenUrl, httpEntity, String.class, uriVariables);

		WeiBoWebsiteTokenResponse weiBoWebsiteTokenResponse;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		try {
			weiBoWebsiteTokenResponse = objectMapper.readValue(forObject, WeiBoWebsiteTokenResponse.class);
		}
		catch (JsonProcessingException e) {
			OAuth2Error error = new OAuth2Error(OAuth2WeiBoWebsiteEndpointUtils.ERROR_CODE,
					"使用 微博开放平台 网站应用 授权code：" + code + " 获取Token异常",
					OAuth2WeiBoWebsiteEndpointUtils.AUTH_CODE2SESSION_URI);
			throw new OAuth2AuthenticationException(error, e);
		}

		String uid = weiBoWebsiteTokenResponse.getUid();
		String accessToken = weiBoWebsiteTokenResponse.getAccessToken();
		if (uid == null) {
			OAuth2Error error = new OAuth2Error(weiBoWebsiteTokenResponse.getError(),
					weiBoWebsiteTokenResponse.getErrorDescription(),
					OAuth2WeiBoWebsiteEndpointUtils.AUTH_CODE2SESSION_URI);
			throw new OAuth2AuthenticationException(error);
		}

		Map<String, String> map = new HashMap<>(4);
		map.put(OAuth2ParameterNames.ACCESS_TOKEN, accessToken);
		map.put(OAuth2WeiBoParameterNames.UID, uid);
		String string = restTemplate.getForObject(userinfoUrl, String.class, map);
		try {
			WeiBoWebsiteTokenResponse.UserInfo userInfo = objectMapper.readValue(string,
					WeiBoWebsiteTokenResponse.UserInfo.class);
			weiBoWebsiteTokenResponse.setUserInfo(userInfo);
		}
		catch (JsonProcessingException e) {
			OAuth2Error error = new OAuth2Error(OAuth2WeiBoWebsiteEndpointUtils.ERROR_CODE,
					"使用 微博开放平台 网站应用 获取用户个人信息异常：", OAuth2WeiBoWebsiteEndpointUtils.AUTH_CODE2SESSION_URI);
			throw new OAuth2AuthenticationException(error, e);
		}

		return weiBoWebsiteTokenResponse;
	}

	/**
	 * 构建 微博开放平台 网站应用 认证信息
	 * @param clientPrincipal 经过身份验证的客户端主体
	 * @param additionalParameters 附加参数
	 * @param details 登录信息
	 * @param appid AppID
	 * @param code 授权码
	 * @param uid 用户唯一标识
	 * @param credentials 证书
	 * @param accessToken 授权凭证
	 * @param expiresIn 过期时间
	 * @return 返回 认证信息
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public AbstractAuthenticationToken authenticationToken(Authentication clientPrincipal,
			Map<String, Object> additionalParameters, Object details, String appid, String code, String uid,
			Object credentials, String accessToken, Integer expiresIn) throws OAuth2AuthenticationException {
		List<GrantedAuthority> authorities = new ArrayList<>();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(weiBoWebsiteProperties.getDefaultRole());
		authorities.add(authority);
		User user = new User(uid, accessToken, authorities);

		UsernamePasswordAuthenticationToken principal = UsernamePasswordAuthenticationToken.authenticated(user, null,
				user.getAuthorities());

		WeiBoWebsiteAuthenticationToken authenticationToken = new WeiBoWebsiteAuthenticationToken(authorities,
				clientPrincipal, principal, user, additionalParameters, details, appid, code, uid);

		authenticationToken.setCredentials(credentials);

		return authenticationToken;
	}

	/**
	 * 授权成功重定向方法
	 * @param request 请求
	 * @param response 响应
	 * @param uriVariables 参数
	 * @param oauth2AccessTokenResponse OAuth2.1 授权 Token
	 * @param weiBoWebsite 微博开放平台 网站应用 配置
	 * @throws OAuth2AuthenticationException OAuth 2.1 可处理的异常，可使用
	 * {@link OAuth2AuthorizationServerConfigurer#tokenEndpoint(Customizer)} 中的
	 * {@link OAuth2TokenEndpointConfigurer#errorResponseHandler(AuthenticationFailureHandler)}
	 * 拦截处理此异常
	 */
	@Override
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response, Map<String, String> uriVariables,
			OAuth2AccessTokenResponse oauth2AccessTokenResponse, WeiBoWebsiteProperties.WeiBoWebsite weiBoWebsite)
			throws OAuth2AuthenticationException {

		OAuth2AccessToken accessToken = oauth2AccessTokenResponse.getAccessToken();

		try {
			response.sendRedirect(weiBoWebsite.getSuccessUrl() + "?" + weiBoWebsite.getParameterName() + "="
					+ accessToken.getTokenValue());
		}
		catch (IOException e) {
			OAuth2Error error = new OAuth2Error(OAuth2WeiBoWebsiteEndpointUtils.ERROR_CODE, "微博开放平台 网站应用重定向异常", null);
			throw new RedirectWeiBoException(error, e);
		}

	}

	public String getSecretByAppid(String appid) {
		Assert.notNull(appid, "appid 不能为 null");
		WeiBoWebsiteProperties.WeiBoWebsite weiBoWebsite = getWeiBoWebsiteByAppid(appid);
		return weiBoWebsite.getSecret();
	}

}
