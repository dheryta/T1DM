package com.t1dm.t1dmanagementapp;



import android.content.BroadcastReceiver;

import android.content.Context;

import android.content.Intent;



public class SuggestionAlarmReceiver extends BroadcastReceiver{



	@Override

	public void onReceive(Context context, Intent intent) 

	{

		

		Intent i = new Intent();

		i.setClassName("com.t1dm.t1dmanagement", "com.t1dm.t1dmanagement.SuggestionSchedule");

		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		context.startActivity(i);

		

	}



}