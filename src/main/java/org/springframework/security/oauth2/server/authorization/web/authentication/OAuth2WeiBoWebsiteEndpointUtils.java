package org.springframework.security.oauth2.server.authorization.web.authentication;

/**
 * 微博开放平台 网站应用 OAuth 2.0 协议端点的实用方法
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class OAuth2WeiBoWebsiteEndpointUtils {

	/**
	 * 微博开放平台 网站应用
	 */
	public static final String AUTH_CODE2SESSION_URI = "https://open.weibo.com/wiki/Oauth2/authorize";

	/**
	 * 错误代码
	 */
	public static final String ERROR_CODE = "C10000";

	/**
	 * 无效错误代码
	 */
	public static final String INVALID_ERROR_CODE = "C20000";

}
