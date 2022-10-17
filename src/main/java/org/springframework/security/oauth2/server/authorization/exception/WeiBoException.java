package org.springframework.security.oauth2.server.authorization.exception;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

/**
 * 微博开放平台父异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class WeiBoException extends OAuth2AuthenticationException {

	public WeiBoException(String errorCode) {
		super(errorCode);
	}

	public WeiBoException(OAuth2Error error) {
		super(error);
	}

	public WeiBoException(OAuth2Error error, Throwable cause) {
		super(error, cause);
	}

	public WeiBoException(OAuth2Error error, String message) {
		super(error, message);
	}

	public WeiBoException(OAuth2Error error, String message, Throwable cause) {
		super(error, message, cause);
	}

	@Override
	public OAuth2Error getError() {
		return super.getError();
	}

}
