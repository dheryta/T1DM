package com.t1dm.t1dmanagementapp;


import java.util.ArrayList;

import java.util.Calendar;



import android.os.Bundle;

import android.animation.TimeAnimator;

import android.animation.TimeAnimator.TimeListener;

import android.app.Activity;

import android.app.Dialog;

import android.app.ExpandableListActivity;

import android.app.ListActivity;

import android.app.TimePickerDialog;

import android.app.TimePickerDialog.OnTimeSetListener;

import android.content.Context;

import android.view.LayoutInflater;

import android.view.Menu;

import android.view.View;

import android.widget.ExpandableListView;

import android.widget.ListView;

import android.widget.TimePicker;

import android.widget.Toast;



public class ExerciseSchedule extends ListActivity {



	private ArrayList<String> values = new ArrayList<String>();

	

    private CommonMethods commonMethods = new CommonMethods();	

	private T1DMApplication appContext;

	

	private TimePickerDialog.OnTimeSetListener timePickerListener = null;

	private String timeValue;

	private int hourOfDay, minute;

	

	@Override

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		//setContentView(R.layout.activity_exercise_schedule);

		appContext = ((T1DMApplication) getApplication());

		appContext.setDbHandler( new DatabaseHandler(appContext.getContext()));

		addListValues();

		setListAdapter(new ExerciseScheduleArrayAdapter(this, values));

		

		final Calendar calendar = Calendar.getInstance();

		hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

		minute = calendar.get(Calendar.MINUTE);

		

		timePickerListener = new MyTimePickerListener();

	}



	@Override

	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.exercise_schedule, menu);

		return true;

	}



	@SuppressWarnings("deprecation")

	@Override

	protected void onListItemClick(ListView l, View v, int position, long id) {

 		

		//String selectedValue = (String) getListAdapter().getItem(position);

		//Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

		showDialog(1);

		

		Toast.makeText(this, timeValue, Toast.LENGTH_SHORT).show();

	}

	

	@Override

	protected Dialog onCreateDialog(int id) {

		switch (id) {

		case 1:			

			return new TimePickerDialog(this, timePickerListener, hourOfDay, minute,false);

 

		}

		return null;

	}

	private void addListValues(){

		values.add(commonMethods.SUBTYPE_MORNING);

		values.add(commonMethods.SUBTYPE_EVENING);

		values.add(commonMethods.SUBTYPE_EXTRAS);

	}

	

	private class MyTimePickerListener implements OnTimeSetListener{



		@Override

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

			timeValue = hourOfDay+":"+minute;			

			

		}

	}

	

	private long readAndSaveUI()

	{

		ArrayList<ActivityScheduleDetail> activitySchedules = new ArrayList<ActivityScheduleDetail>();

		ActivityScheduleDetail activitySchedule = new ActivityScheduleDetail();

		TimePicker timePickerValue;

		String value;

		

		activitySchedule.set_Type(3);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(0));

		

		timePickerValue = (TimePicker) findViewById(R.id.timePicker_BGS_Breakfast);

		value = timePickerValue.getCurrentHour().toString()+":"+timePickerValue.getCurrentMinute().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		activitySchedule.set_Type(3);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(1));

		

		timePickerValue = (TimePicker) findViewById(R.id.timePicker_BGS_Brunch);

		value = timePickerValue.getCurrentHour().toString()+":"+timePickerValue.getCurrentMinute().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		activitySchedule.set_Type(3);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(2));

		

		timePickerValue = (TimePicker) findViewById(R.id.timePicker_BGS_Lunch);

		value = timePickerValue.getCurrentHour().toString()+":"+timePickerValue.getCurrentMinute().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		activitySchedule.set_Type(3);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(3));

		

		timePickerValue = (TimePicker) findViewById(R.id.timePicker_BGS_Snacks);

		value = timePickerValue.getCurrentHour().toString()+":"+timePickerValue.getCurrentMinute().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		activitySchedule.set_Type(3);

		activitySchedule.set_SubType(commonMethods.MAIN_ACTIVITIES.get(4));

		

		timePickerValue = (TimePicker) findViewById(R.id.timePicker_BGS_Dinner);

		value = timePickerValue.getCurrentHour().toString()+":"+timePickerValue.getCurrentMinute().toString();

		activitySchedule.set_Time(value);

		

		activitySchedules.add(activitySchedule);

		

		long status = appContext.getDbHandler().insertActivitySchedule(activitySchedules);

		return status;

	}



}