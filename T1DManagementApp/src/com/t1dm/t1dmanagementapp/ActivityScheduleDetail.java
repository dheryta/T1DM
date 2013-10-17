package com.t1dm.t1dmanagementapp;



public class ActivityScheduleDetail {

	private int _Type;

	private String _SubType;

	private String _Time;

	

	///0-Eat, 1-Insulin, 2-BG, 3-Exercise, 4-Sleep, 5-Suggestion

	public int get_Type() {

		return _Type;

	}

	public void set_Type(int _Type) {

		this._Type = _Type;

	}

	public String get_SubType() {

		return _SubType;

	}

	public void set_SubType(String _SubType) {

		this._SubType = _SubType;

	}

	public String get_Time() {

		return _Time;

	}

	public void set_Time(String _Time) {

		this._Time = _Time;

	}

	



}