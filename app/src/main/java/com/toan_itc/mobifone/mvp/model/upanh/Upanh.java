package com.toan_itc.mobifone.mvp.model.upanh;

public class Upanh{
	private String reason;
	private int error;

	public void setReason(String reason){
		this.reason = reason;
	}

	public String getReason(){
		return reason;
	}

	public void setError(int error){
		this.error = error;
	}

	public int getError(){
		return error;
	}
}
