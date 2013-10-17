package com.t1dm.t1dmanagementapp;



import android.os.Bundle;

import android.app.Activity;

import android.content.Intent;

import android.view.Menu;

import android.view.MenuItem;

import android.widget.Toast;



public class BeginMonitoring extends Activity {



	private T1DMApplication appContext;

	

	@Override

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_begin_monitoring);

	}



	@Override

	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.begin_monitoring, menu);

		return true;

	}

	

	@Override

	public boolean onOptionsItemSelected(MenuItem item) {



		switch (item.getItemId()) {

		case R.id.monitoring_Save:

			//if (appContext.getDbHandler().checkExactlyOneRowInDatabase()) {

				long next = readAndSaveUI();

				if (next == -1)

					Toast.makeText(this,

							"T1DM says, something went wrong !!!!",

							Toast.LENGTH_LONG).show();

				else

					startActivity(new Intent(appContext.getContext(),

							InsulinSchedule.class));

			/*} else {

				Toast.makeText(appContext.getContext(),

						"T1DM says, oops please restart", Toast.LENGTH_LONG)

						.show();

				finish();

			}*/



			return true;

		}

		return super.onOptionsItemSelected(item);



	}

	

	private long readAndSaveUI(){

		long status = -1;

		

		return status;

	}





}