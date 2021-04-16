package com.techelevator.tenmo.model;

import java.util.List;

public class Root {
	private Meta meta;
	private String fl;
	private List<com.techelevator.tenmo.model.List> list;
	
	//private Def[] def;

	
	
	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public String getFl() {
		return fl;
	}

	public void setFl(String fl) {
		this.fl = fl;
	}

	public List<com.techelevator.tenmo.model.List> getList() {
		return list;
	}

	public void setList(List<com.techelevator.tenmo.model.List> list) {
		this.list = list;
	}

//	public List<WordCharacteristic> getList() {
//		return list;
//	}
//
//	public void setList(List<WordCharacteristic> list) {
//		this.list = list;
//	}
	
	
	

	
	
}
