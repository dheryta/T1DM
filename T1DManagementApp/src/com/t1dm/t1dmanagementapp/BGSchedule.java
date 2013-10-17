package com.t1dm.t1dmanagementapp;



import java.util.ArrayList;



import android.os.Bundle;

import android.app.Activity;

import android.content.Intent;

import android.view.Menu;

import android.view.MenuItem;

import android.widget.EditText;

import android.widget.Toast;



public class BGSchedule extends Activity {

	

	   private CommonMethods commonMethods = new CommonMethods();

		

		private T1DMApplication appContext;





	@Override

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_bgschedule);

		appContext = ((T1DMApplication) getApplication());

		appContext.setDbHandler( new DatabaseHandler(appContext.getContext()));

	}



	@Override

	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.bgschedule, menu);

		return true;

	}

	

	@Override

    public boolean onOptionsItemSelected(MenuItem item)

    {

     

     switch(item.getItemId())

     {

     case R.id.bg_schedule_GPSTrack :    	 

    	 Intent service = new Intent(appContext.getContext(), GPSTracker.class);

    	 appContext.getContext().startService(service); 	  

    	 return true;

     case R.id.bg_schedule_Next :

    	 

    	 long next = readAndSaveUI();

    	 if (next == -1)

    		 Toast.makeText(this, "T1DM says, something went wrong !!!!", Toast.LENGTH_LONG).show();       

         else        	 

    	     startActivity(new Intent(appContext.getContext(),ExerciseSchedule.class));

    	 

    	 return true;

     }

     return super.onOptionsItemSelected(item);



    }

	

	private long readAndSaveUI()

	{

		ArrayList<ActivityScheduleDetail> activitySchedules = new ArrayList<ActivityScheduleDetail>();

		ActivityScheduleDetail activitySchedule = new ActivityScheduleDetail();

		EditText editTextValue;

		String value;

		

		activitySchedule.set_Type(2);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(0));

		

		editTextValue = (EditText) findViewById(R.id.editText_Insulin_Breakfast);

		value = editTextValue.getText().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		activitySchedule.set_Type(2);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(1));

		

		editTextValue = (EditText) findViewById(R.id.editText_Insulin_Brunch);

		value = editTextValue.getText().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		activitySchedule.set_Type(2);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(2));

		

		editTextValue = (EditText) findViewById(R.id.editText_Insulin_Lunch);

		value = editTextValue.getText().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		activitySchedule.set_Type(2);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(3));

		

		editTextValue = (EditText) findViewById(R.id.editText_Insulin_Snacks);

		value = editTextValue.getText().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		activitySchedule.set_Type(2);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(4));

		

		editTextValue = (EditText) findViewById(R.id.editText_Insulin_Dinner);

		value = editTextValue.getText().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		long status = appContext.getDbHandler().insertActivitySchedule(activitySchedules);

		return status;

	}



}