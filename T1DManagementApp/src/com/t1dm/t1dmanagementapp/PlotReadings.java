package com.t1dm.t1dmanagementapp;



import java.text.FieldPosition;

import java.text.Format;

import java.text.ParsePosition;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Calendar;

import java.util.Date;

import java.util.GregorianCalendar;

import java.util.TimeZone;



import android.app.Activity;

import android.os.Bundle;



import com.androidplot.xy.SimpleXYSeries;

import com.androidplot.xy.XYSeries;

import com.androidplot.xy.LineAndPointFormatter;

import com.androidplot.xy.PointLabelFormatter;

import com.androidplot.xy.XYPlot;

import com.androidplot.xy.XYStepMode;



import android.view.Menu;



public class PlotReadings extends Activity {



	private CommonMethods commonMethods = new CommonMethods();

	private T1DMApplication appContext;



	private XYPlot plot;

	@Override

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

				

        setContentView(R.layout.activity_plot_readings);



        plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);



        appContext = ((T1DMApplication) getApplication());

		appContext.setDbHandler(new DatabaseHandler());

		

		ArrayList<MonitoringReadings> readings = appContext.getDbHandler().getMonitoringReadings();

		ArrayList<Long> timestamps = new ArrayList<Long>();

		ArrayList<Integer> glucoseReadings = new ArrayList<Integer>();

	

		for(MonitoringReadings reading:readings){

			timestamps.add(commonMethods.convertDateTimeToEpoch(reading.get_Timestamp()));

			glucoseReadings.add(reading.get_Reading());

		}

        

       

        XYSeries series1 = new SimpleXYSeries(timestamps, glucoseReadings, "");                           



        LineAndPointFormatter series1Format = new LineAndPointFormatter();

        series1Format.setPointLabelFormatter(new PointLabelFormatter());

        series1Format.configure(getApplicationContext(), R.layout.line_point_formatter_with_plf1);



        plot.addSeries(series1, series1Format);



        plot.setTicksPerRangeLabel(3);

        plot.getGraphWidget().setDomainLabelOrientation(-45);

        //plot.setDomainStep(XYStepMode.SUBDIVIDE, timestamps.size());

        plot.setDomainLabel("Date & Time");

        plot.setRangeLabel("Glucose Level");

        plot.setDomainValueFormat(new Format() {

			

			/**

			 * 

			 */

			private static final long serialVersionUID = 1L;



			@Override

			public Object parseObject(String arg0, ParsePosition arg1) {

				// TODO Auto-generated method stub

				return null;

			}

			

			@Override

			public StringBuffer format(Object epoch, StringBuffer appendTo, FieldPosition pos) {

				StringBuffer dateTime;

				

				Date date = new Date(((Number) epoch).longValue());

				SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");

				

				TimeZone phoneTimeZone = GregorianCalendar.getInstance().getTimeZone();

		        df.setTimeZone(phoneTimeZone);

		        dateTime = df.format(date, appendTo, pos);

				return dateTime;

			}

		});

        

        

	}



	@Override

	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.

		getMenuInflater().inflate(R.menu.plot_readings, menu);

		return true;

	}



}