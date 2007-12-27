package net.narusas.cafelibrary.serial;

import java.io.IOException;

public class LoginFailException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5027135775353113302L;
	private final String id;
	private final String passwd;

	public LoginFailException(String id, String passwd) {
		super("Fail to login");
		this.id = id;
		this.passwd = passwd;
	}

	public String getId() {
		return id;
	}

	public String getPasswd() {
		return passwd;
	}

}
