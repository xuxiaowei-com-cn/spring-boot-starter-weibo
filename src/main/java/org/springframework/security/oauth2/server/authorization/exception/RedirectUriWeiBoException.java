package org.springframework.security.oauth2.server.authorization.exception;

import org.springframework.security.oauth2.core.OAuth2Error;

/**
 * 微博开放平台 redirectUri 异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class RedirectUriWeiBoException extends WeiBoException {

	public RedirectUriWeiBoException(String errorCode) {
		super(errorCode);
	}

	public RedirectUriWeiBoException(OAuth2Error error) {
		super(error);
	}

	public RedirectUriWeiBoException(OAuth2Error error, Throwable cause) {
		super(error, cause);
	}

	public RedirectUriWeiBoException(OAuth2Error error, String message) {
		super(error, message);
	}

	public RedirectUriWeiBoException(OAuth2Error error, String message, Throwable cause) {
		super(error, message, cause);
	}

}
