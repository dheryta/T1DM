package com.t1dm.t1dmanagementapp;



import android.os.Bundle;

import android.app.Activity;

import android.app.AlertDialog;

import android.content.DialogInterface;

import android.view.Menu;



public class BGCheckingAlarm extends Activity {



	@Override

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_bgchecking_alarm);

		AlertDialog alertDialog = new AlertDialog.Builder(this)

	    .setTitle("T1DM Schedule Alarm")

	    .setMessage("T1DM says, its blood glucose checking time. You need to check blood glucose.")

	    .setPositiveButton("OK, I am checking", new DialogInterface.OnClickListener() {

	        public void onClick(DialogInterface dialog, int which) { 

	            // continue with delete

	        }

	     })

	    .setNegativeButton("No, I will check later", new DialogInterface.OnClickListener() {

	        public void onClick(DialogInterface dialog, int which) { 

	        	dialog.cancel();

	        }

	     })

	     .show();

	}



	@Override

	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.bgchecking_alarm, menu);

		return true;

	}



}