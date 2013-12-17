/**
 * Copyright (c) 2010-2012, Benjamin Marwell.  This file is
 * licensed under the Affero General Public License version 3 or later.  See
 * the COPYRIGHT file.
 */
package de.bmarwell.j9kwsolver.request;

public class CaptchaNewOk implements CaptchaRequestInterface {
	private static final String url = "http://www.9kw.eu/index.cgi"; 
	private static final String action = "usercaptchanewok";
	
	private String apikey;
	private String source;
	
	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUrl() {
		return url;
	}
	public String getAction() {
		return action;
	}
	
	
	
}
