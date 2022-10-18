package org.springframework.security.oauth2.core.endpoint;

/**
 * 微博开放平台 网站应用 参数名称
 *
 * @author xuxiaowei
 * @since 0.0.1
 * @see OAuth2ParameterNames 在 OAuth 参数注册表中定义并由授权端点、令牌端点和令牌撤销端点使用的标准和自定义（非标准）参数名称。
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public interface OAuth2WeiBoParameterNames {

	/**
	 * AppID
	 *
	 * 
	 * 
	 */
	String APPID = "appid";

	/**
	 * 授权确认页面可适配的终端类型，取值为：
	 * <p>
	 * default - 默认的授权确认页，适用于PC端的网页浏览器，
	 * <p>
	 * mobile - 适配移动端的授权确认页，适用于手机浏览器场景。
	 */
	String DISPLAY = "display";

	/**
	 * 是否强制用户重新登录，
	 * <p>
	 * true：是，
	 * <p>
	 * false：否。
	 * <p>
	 * 默认false。
	 */
	String FORCELOGIN = "forcelogin";

	/**
	 * 授权确认页显示的语言，缺省为中文简体版，en为英文版。
	 */
	String LANGUAGE = "language";

	/**
	 * 用户唯一标识
	 */
	String UID = "uid";

	/**
	 * 远程地址
	 */
	String REMOTE_ADDRESS = "remote_address";

	/**
	 * Session ID
	 */
	String SESSION_ID = "session_id";

	/**
	 * 是否绑定，需要使用者自己去拓展
	 */
	String BINDING = "binding";

}
