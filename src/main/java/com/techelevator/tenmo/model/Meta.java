package com.techelevator.tenmo.model;

import java.util.List;

public class Meta {
	
	private String id;
	private List<List<String>> syns;
	private String sls;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public List<List<String>> getSyns() {
		return syns;
	}
	public void setSyns(List<List<String>> syns) {
		this.syns = syns;
	}
	
	public String getSls() {
		return sls;
	}
	public void setSls(String sls) {
		this.sls = sls;
	}
	
	
	
	private List<String> getListofSyns(){
		List<String> newSyns = getSyns().get(0);
		
		return newSyns;
	}

}
