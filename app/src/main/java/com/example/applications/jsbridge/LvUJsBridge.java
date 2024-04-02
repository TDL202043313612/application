package com.example.applications.jsbridge;


public interface LvUJsBridge {
	
	public void send(String data);
	public void send(String data, CallBackFunction responseCallback);
	
	

}
