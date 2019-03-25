package com.java;

import java.util.List;

public class Datas {
	
	private String name;
	
	private String serialNo;
	
	private String bizType;
	
	private String operationType;
	
	//private List<String> info;
	
	private List<Object> bizList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public List<Object> getBizList() {
		return bizList;
	}

	public void setBizList(List<Object> bizList) {
		this.bizList = bizList;
	}
	
	

	/*public List<String> getInfo() {
		return info;
	}

	public void setInfo(List<String> info) {
		this.info = info;
	}*/
	

}
