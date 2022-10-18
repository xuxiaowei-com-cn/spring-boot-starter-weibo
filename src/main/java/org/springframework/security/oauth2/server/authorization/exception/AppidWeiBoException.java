package org.springframework.security.oauth2.server.authorization.exception;

import org.springframework.security.oauth2.core.OAuth2Error;

/**
 * 微博开放平台 AppID 异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class AppidWeiBoException extends WeiBoException {

	public AppidWeiBoException(String errorCode) {
		super(errorCode);
	}

	public AppidWeiBoException(OAuth2Error error) {
		super(error);
	}

	public AppidWeiBoException(OAuth2Error error, Throwable cause) {
		super(error, cause);
	}

	public AppidWeiBoException(OAuth2Error error, String message) {
		super(error, message);
	}

	public AppidWeiBoException(OAuth2Error error, String message, Throwable cause) {
		super(error, message, cause);
	}

}
