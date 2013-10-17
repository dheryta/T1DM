package com.t1dm.t1dmanagementapp;



public class MonitoringReadings {

	private String _Timestamp;

	public String get_Timestamp() {

		return _Timestamp;

	}

	public void set_Timestamp(String _Timestamp) {

		this._Timestamp = _Timestamp;

	}

	public int getReading() {

		return _Reading;

	}

	public void setReading(int reading) {

		this._Reading = reading;

	}

	private int _Reading;

}