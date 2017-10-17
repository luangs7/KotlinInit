package br.com.squarebits.kotlininit.model;

import com.google.gson.annotations.SerializedName;

public class BaseRequest {

	@SerializedName("named")
	private String name;

	@SerializedName("message")
	private String message;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"BaseRequest{" + 
			"name = '" + name + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}