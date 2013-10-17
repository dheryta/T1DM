package com.t1dm.t1dmanagementapp;



import java.io.File;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Arrays;

import java.util.Calendar;

import java.util.Date;

import java.util.GregorianCalendar;

import java.util.List;

import java.util.TimeZone;



import android.app.AlarmManager;

import android.app.PendingIntent;

import android.content.Context;

import android.content.Intent;

import android.os.Environment;



public class CommonMethods {

	

	public static boolean USER_PROFILING_COMPLETE = false; 

	public static final int ACTIVITY_MEAL = 0;

	public static final int ACTIVITY_INSULIN = 1;

	public static final int ACTIVITY_BGCHECKING = 2;

	public static final int ACTIVITY_EXERCISE = 3;

	public static final int ACTIVITY_SLEEP = 4;

	public static final int ACTIVITY_SUGGESTION = 5;

	

	public final List<String> MAIN_ACTIVITIES= Arrays.asList("MEAL", "INSULIN", "BGCHECKING", "EXERCISE", "SLEEP", "SUGGESTION"); 

	public final List<String> DAYS_OF_WEEK = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

	

	public final String SUBTYPE_BREAKFAST = "BreakFast";

	public final String SUBTYPE_BRUNCH = "Brunch";

	public final String SUBTYPE_LUNCH = "Lunch";

	public final String SUBTYPE_SNACKS = "Snacks";

	public final String SUBTYPE_DINNER = "Dinner";

	public final String SUBTYPE_MORNING = "Morning";

	public final String SUBTYPE_NOON = "Noon";

	public final String SUBTYPE_EVENING = "Evening";

	public final String SUBTYPE_NIGHT = "Night";

	public final String SUBTYPE_DAILY = "Daily";

	public final String SUBTYPE_WEEKLY = "Weekly";

	public final String SUBTYPE_FORTNIGHTLY = "Fortnightly";

	public final String SUBTYPE_MONTHLY = "Monthly";

	public final String SUBTYPE_EXTRAS = "Other";

	

	public final int DB_VERSION = 1;

	public final String DB_NAME = "T1DM_DB";

	public final File DB_PATH = Environment.getExternalStorageDirectory();

	public final String DB_FOLDER = "T1DMApp/Database";



	public static final DatabaseHandler DATABASE_HANDLER = new DatabaseHandler();

	public boolean decideNextActivity()

	{		

		DatabaseHandler dbHandler = new DatabaseHandler();

		boolean status = false;

		if (dbHandler!=null)

			status = dbHandler.getUserProfiling();

	    return status;

	}

	

	public boolean dropDatabase()

	{		

		DatabaseHandler dbHandler = new DatabaseHandler();

		boolean status = false;

		if (dbHandler!=null)

			status = dbHandler.dropDatabase();

	    return status;

	}

	

	public boolean createDatabaseFolder()

	{

		File dBFolderPath = new File(DB_PATH + File.separator + DB_FOLDER);

		return dBFolderPath.mkdirs();

	}

	

	public long convertDateTimeToEpoch(String dateTime)

	{

		long epoch = 0;

		SimpleDateFormat input_df = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");

		SimpleDateFormat output_df = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");

		

	    

		try {

			output_df.setTimeZone(GregorianCalendar.getInstance().getTimeZone());

			input_df.setTimeZone(GregorianCalendar.getInstance().getTimeZone());

			Date date12hr = input_df.parse(dateTime);;

		    String date24hr_str = output_df.format(date12hr);

		    Date date24hr = output_df.parse(date24hr_str);

			epoch = date24hr.getTime();

		} catch (ParseException e) {

			

			return epoch;			

		}

	    

		return epoch;

	}

	

	public long convertDateTimeToEpoch24Hr(String dateTime)

	{

		long epoch = 0;

		//SimpleDateFormat input_df = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");

		SimpleDateFormat output_df = new SimpleDateFormat("MMM dd, yyyy HH:mm");

		

	    

		try {

			output_df.setTimeZone(GregorianCalendar.getInstance().getTimeZone());

			//input_df.setTimeZone(GregorianCalendar.getInstance().getTimeZone());

			//Date date12hr = input_df.parse(dateTime);;

		    //String date24hr_str = output_df.format(dateTime);

		    Date date24hr = output_df.parse(dateTime);

			epoch = date24hr.getTime();

		} catch (ParseException e) {

			

			return epoch;			

		}

	    

		return epoch;

	}

	

	public String convertEpochToDateTime(long epoch)

	{

		String dateTime = null;

		try{

		Date date = new Date(epoch);

		SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");

        df.setTimeZone(TimeZone.getDefault());

        dateTime = df.format(date);

		}catch(Exception e){

			dateTime = null;

			return dateTime;

		}

		return dateTime;

	}

	

	public void setAlarm(String dateTime, Intent intentSchedule, Context context)

	{

		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

		long alarmTime = convertDateTimeToEpoch24Hr(dateTime);

        		

        alarmManager.set(AlarmManager.RTC_WAKEUP,alarmTime, PendingIntent.getBroadcast(context,1,  intentSchedule, PendingIntent.FLAG_UPDATE_CURRENT));

	}

	

	public void setAlarm(Long alarmTime, Intent intentSchedule, Context context)

	{

		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

		//long alarmTime = convertDateTimeToEpoch24Hr(dateTime);

        		

        alarmManager.set(AlarmManager.RTC_WAKEUP,alarmTime, PendingIntent.getBroadcast(context,1,  intentSchedule, PendingIntent.FLAG_UPDATE_CURRENT));

	}

}