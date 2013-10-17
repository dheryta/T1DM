package com.t1dm.t1dmanagementapp;


import android.net.Uri;

import android.app.Activity;

import android.app.AlertDialog;

import android.content.BroadcastReceiver;

import android.content.Context;

import android.content.DialogInterface;

import android.content.Intent;

import android.telephony.SmsManager;

import android.util.Log;

import android.view.KeyEvent;

import android.widget.Toast;



public class EmergencyCallReceiver extends BroadcastReceiver {

	private static final String TAG = "T1DM Emergency Call BroadcastReceiver";

	private static int countScreenOn = 0;

	@Override

	public void onReceive(Context context, Intent intent) {

		// TODO Auto-generated method stub

		

		 Toast.makeText(context, TAG, Toast.LENGTH_SHORT).show(); 

		 Log.d(TAG, "Started T1DM Emergency Call Service");

		 

		 String strAction = intent.getAction();

         //strAction.equals(Intent.ACTION_SCREEN_OFF) || 

         if (strAction.equals(Intent.ACTION_SCREEN_ON)) {

        	 countScreenOn++;        	         	 

         }

         

         if (countScreenOn == 2)

         {



        	 Intent i = new Intent();

        	 i.setClassName("com.t1dm.t1dmanagement", "com.t1dm.t1dmanagement.AskUser");

        	 i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        	 context.startActivity(i);

        	 countScreenOn=0;

         }

		Log.d(TAG,strAction);

	}

	

	

}



