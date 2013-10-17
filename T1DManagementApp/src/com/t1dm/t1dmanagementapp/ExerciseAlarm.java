package com.t1dm.t1dmanagementapp;


import android.os.Bundle;

import android.app.Activity;

import android.view.Menu;



public class ExerciseAlarm extends Activity {



	@Override

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_exercise_alarm);

	}



	@Override

	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.exercise_alarm, menu);

		return true;

	}



}