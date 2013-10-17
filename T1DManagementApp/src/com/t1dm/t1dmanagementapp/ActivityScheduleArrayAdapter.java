package com.t1dm.t1dmanagementapp;



import java.util.ArrayList;

import android.app.Activity;

import android.content.Context;

import android.view.LayoutInflater;

import android.view.View;

import android.view.View.OnClickListener;

import android.view.ViewGroup;

import android.widget.ArrayAdapter;

import android.widget.BaseExpandableListAdapter;

import android.widget.CheckedTextView;

import android.widget.TextView;

import android.widget.TimePicker;

import android.widget.Toast;



public class ActivityScheduleArrayAdapter extends ArrayAdapter<String> {

	

	private Activity activity;

	

	private ArrayList<String> values;

	

	private Context context;

	 public LayoutInflater minflater;

	 

	 public ActivityScheduleArrayAdapter(Context context, ArrayList<String> values) {

			super(context, R.layout.activity_exercise_schedule, values);

			this.context = context;

			this.values = values;

	 }

	 

	 @Override

		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = (LayoutInflater) context

				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	 

			View rowView = inflater.inflate(R.layout.activity_exercise_schedule, parent, false);

			TextView textView = (TextView) rowView.findViewById(R.id.lvExerciseSchedule);

			

			textView.setText(values.get(position));

	  

			return rowView;

		}

}