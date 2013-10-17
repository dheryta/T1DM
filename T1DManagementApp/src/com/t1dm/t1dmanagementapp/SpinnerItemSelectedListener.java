package com.t1dm.t1dmanagementapp;



import android.view.View;

import android.widget.AdapterView;

import android.widget.AdapterView.OnItemSelectedListener;



public class SpinnerItemSelectedListener implements OnItemSelectedListener {

	 

	  public string onItemSelected(AdapterView<?> parent, View view, int pos,long id) {

		return parent.getItemAtPosition(pos).toString();

			

	  }

	 

	  @Override

	  public void onNothingSelected(AdapterView<?> arg0) {

		// TODO Auto-generated method stub

	  }

}