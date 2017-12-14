package com.example.lenovo.otp;

import java.io.Serializable;

public class AtomPayment implements Serializable {
	private static final long serialVersionUID = -5435670920302756945L;
	
	private String name = "";
	private String value = "0";
	boolean box;

	public AtomPayment(String name, String value,boolean box) {
		this.setName(name);
		this.setValue(value);
		this.setBox(box);
	}

	public AtomPayment(String name, int i) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean getBox() {
		return box;
	}

	public void setBox(Boolean box) {
		this.box=box;
	}
}