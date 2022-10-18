package org.springframework.security.oauth2.server.authorization.exception;

import org.springframework.security.oauth2.core.OAuth2Error;

/**
 * 重定向 异常
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class RedirectWeiBoException extends WeiBoException {

	public RedirectWeiBoException(String errorCode) {
		super(errorCode);
	}

	public RedirectWeiBoException(OAuth2Error error) {
		super(error);
	}

	public RedirectWeiBoException(OAuth2Error error, Throwable cause) {
		super(error, cause);
	}

	public RedirectWeiBoException(OAuth2Error error, String message) {
		super(error, message);
	}

	public RedirectWeiBoException(OAuth2Error error, String message, Throwable cause) {
		super(error, message, cause);
	}

}
