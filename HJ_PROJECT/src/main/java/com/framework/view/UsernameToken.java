package com.framework.view;

import org.apache.shiro.authc.UsernamePasswordToken;


public class UsernameToken extends UsernamePasswordToken {
	
	private String token;//验证串
	private String captcha;//验证码
	private Integer userFrom;//用户来源
	
	

	public UsernameToken() {
		super();
	}

	public UsernameToken(String username, char[] password, boolean rememberMe,
			String host) {
		super(username, password, rememberMe, host);
	}

	public UsernameToken(String username, char[] password, boolean rememberMe) {
		super(username, password, rememberMe);
	}

	public UsernameToken(String username, char[] password,String captcha) {
		super(username, password);
		this.captcha=captcha;
	}
	
	public UsernameToken(String username, String password, boolean rememberMe,
			String host) {
		super(username, password, rememberMe, host);
	}

	public UsernameToken(String username, String password, boolean rememberMe) {
		super(username, password, rememberMe);
	}

	public UsernameToken(String username, String password) {
		super(username, password);
	}
	
	public UsernameToken(String username, String password, String captcha,String host) {
		super(username, password,host);
		this.captcha=captcha;
	}
	
	public UsernameToken(String username, String password, String captcha,String host,Integer userFrom) {
		super(username, password,host);
		this.captcha=captcha;
		this.userFrom=userFrom;
	}
	
	public UsernameToken(String username, String password,String token, String captcha,String host,Integer userFrom) {
		super(username, password,host);
		this.captcha=captcha;
		this.userFrom=userFrom;
		this.token=token;
	}
	
	public UsernameToken(String token,String host,Integer userFrom) {
		this.token=token;
		this.userFrom=userFrom;
	}
	
	public UsernameToken(String token) {
		this.token=token;
	}

	@Override
	public void clear() {
		this.captcha="";
		this.token="";
		super.clear();
	}

	@Override
	public Object getCredentials() {
		return super.getCredentials();
	}

	@Override
	public String getHost() {
		return super.getHost();
	}

	@Override
	public char[] getPassword() {
		return super.getPassword();
	}

	@Override
	public Object getPrincipal() {
		return super.getPrincipal();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isRememberMe() {
		return super.isRememberMe();
	}

	@Override
	public void setHost(String host) {
		super.setHost(host);
	}

	@Override
	public void setPassword(char[] password) {
		super.setPassword(password);
	}

	@Override
	public void setRememberMe(boolean rememberMe) {
		super.setRememberMe(rememberMe);
	}

	@Override
	public void setUsername(String username) {
		super.setUsername(username);
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public Integer getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(Integer userFrom) {
		this.userFrom = userFrom;
	}
	

}
