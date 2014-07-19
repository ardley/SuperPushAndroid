package com.example.superpushv1;

public class Pushablemsg {
	private String message;
	private String accountid;
	private String extravar;


	public void setMessage(String mess){
		message = mess;
	}
	public void setAcctId(String acctId){
		accountid = acctId;
	}
	public void setExtraVar(String extVar){
		extravar = extVar;
	}
	
	
	public String getMessage(){
		return message;
	}
	public String getAcctId(){
		return accountid;
	}
	public String getExtraVar(){
		return extravar;
	}
	
	
}