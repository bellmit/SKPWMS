package com.skpw.common;

//public class WarningModel {

public enum WarningWaySelect {
	MESSAGE(1), EMAIL(2), TELEPHOMNE(3), PRINTORDER(4);

	private int value;

	WarningWaySelect(int value) {

		this.value = value;
	}

}
