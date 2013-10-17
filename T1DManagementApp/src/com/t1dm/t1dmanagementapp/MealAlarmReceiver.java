package com.t1dm.t1dmanagementapp;



import android.content.BroadcastReceiver;

import android.content.Context;

import android.content.Intent;

import android.widget.Toast;



public class MealAlarmReceiver extends BroadcastReceiver{



	@Override

	public void onReceive(Context context, Intent intent) {

		/*Intent i = new Intent();

		i.setClassName("com.t1dm.t1dmanagement", "com.t1dm.t1dmanagement.MealAlarm");

		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		context.startActivity(i);*/

		Toast.makeText(

				this,

				"T1DM says Meal Alarm Fired",

				Toast.LENGTH_LONG).show();

		

	}



}