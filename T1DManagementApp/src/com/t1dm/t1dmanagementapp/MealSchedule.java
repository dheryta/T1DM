package com.t1dm.t1dmanagementapp;

import java.util.ArrayList;

import java.util.List;



import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;

import android.view.MenuItem;

import android.widget.TimePicker;

import android.widget.Toast;



public class MealSchedule extends Activity {



	private CommonMethods commonMethods = new CommonMethods();

	private DatabaseHandler dbHandler;

	

	@Override

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		dbHandler = new DatabaseHandler(this);

		setContentView(R.layout.activity_meal_schedule);

		Toast.makeText(this, "T1DM needs to know your schedule of meals, insulin, glucose tests, exercise, sleep and dietary suggestions.", Toast.LENGTH_LONG).show();

	}



	@Override

	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.schedules, menu);

		return true;

	}

	

	@Override

    public boolean onOptionsItemSelected(MenuItem item)

    {

     

     switch(item.getItemId())

     {

     case R.id.schedules_GPSTrack :    	 

    	 Intent service = new Intent(getBaseContext(), GPSTracker.class);

 	     getBaseContext().startService(service); 	  

    	 return true;

     case R.id.schedules_Next :

    	 

    	 long next = readAndSaveUI();

    	 if (next == -1)

    		 Toast.makeText(this, "T1DM says, did you miss anything ?", Toast.LENGTH_LONG).show();       

         else        	 

    	     startActivity(new Intent(getApplicationContext(),InsulinSchedule.class));

    	 

    	 return true;

     }

     return super.onOptionsItemSelected(item);



    }



	

	private long readAndSaveUI()

	{

		ArrayList<ActivityScheduleDetail> activitySchedules = new ArrayList<ActivityScheduleDetail>();

		ActivityScheduleDetail activitySchedule = new ActivityScheduleDetail();

		TimePicker timePickerValue;

		String value;

		

		activitySchedule.set_Type(0);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(0));

		

		timePickerValue = (TimePicker) findViewById(R.id.timePicker_Breakfast);

		value = timePickerValue.getCurrentHour().toString()+":"+timePickerValue.getCurrentMinute().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		activitySchedule.set_Type(0);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(1));

		

		timePickerValue = (TimePicker) findViewById(R.id.timePicker_Brunch);

		value = timePickerValue.getCurrentHour().toString()+":"+timePickerValue.getCurrentMinute().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		activitySchedule.set_Type(0);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(2));

		

		timePickerValue = (TimePicker) findViewById(R.id.timePicker_Lunch);

		value = timePickerValue.getCurrentHour().toString()+":"+timePickerValue.getCurrentMinute().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		activitySchedule.set_Type(0);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(3));

		

		timePickerValue = (TimePicker) findViewById(R.id.timePicker_Snacks);

		value = timePickerValue.getCurrentHour().toString()+":"+timePickerValue.getCurrentMinute().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		activitySchedule.set_Type(0);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(4));

		

		timePickerValue = (TimePicker) findViewById(R.id.timePicker_Dinner);

		value = timePickerValue.getCurrentHour().toString()+":"+timePickerValue.getCurrentMinute().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		long status = dbHandler.insertActivitySchedule(activitySchedules);

		return status;

	}

}