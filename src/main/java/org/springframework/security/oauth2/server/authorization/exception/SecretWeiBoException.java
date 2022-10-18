package org.springframework.security.oauth2.server.authorization.exception;

import org.springframework.security.oauth2.core.OAuth2Error;

/**
 * 微博开放平台 Secret 异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class SecretWeiBoException extends WeiBoException {

	public SecretWeiBoException(String errorCode) {
		super(errorCode);
	}

	public SecretWeiBoException(OAuth2Error error) {
		super(error);
	}

	public SecretWeiBoException(OAuth2Error error, Throwable cause) {
		super(error, cause);
	}

	public SecretWeiBoException(OAuth2Error error, String message) {
		super(error, message);
	}

	public SecretWeiBoException(OAuth2Error error, String message, Throwable cause) {
		super(error, message, cause);
	}

}
