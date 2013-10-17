package com.t1dm.t1dmanagementapp;



import android.app.Application;

import android.content.Context;



public class T1DMApplication extends Application {

	private DatabaseHandler dbHandler;

	private Context context;

	

	public Context getContext() {

		return context;

	}



	public void setContext(Context context) {

		this.context = context;

	}



	public DatabaseHandler getDbHandler() {

		return dbHandler;

	}



	public void setDbHandler(DatabaseHandler dbHandler) {

		this.dbHandler = dbHandler;

	}



	private static T1DMApplication singleton;

	 

	public static T1DMApplication getInstance() {

	return singleton;

	}

	 

	@Override

	public void onCreate() {

	super.onCreate();

	singleton = this;

	}



	



}