package com.t1dm.t1dmanagementapp;

/**

 * 

 */

import java.io.File;

import java.util.ArrayList;

import java.util.List;



import android.content.ContentValues;

import android.content.Context;

import android.database.Cursor;

import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import android.os.Environment;



/**

 * @author dheryta

 * 

 */

public class DatabaseHandler  {



	private CommonMethods commonMethods = new CommonMethods();



	// Table Names

	private static final String TBL_USER = "user";

	private static final String TBL_ACTIVITIES = "activities";

	private static final String TBL_ACTIVITY_MEAL = "meals";

	private static final String TBL_ACTIVITY_INSULIN = "insulin";

	private static final String TBL_ACTIVITY_BG = "bg_checking";

	private static final String TBL_ACTIVITY_EXERCISE = "exercise";

	private static final String TBL_ACTIVITY_SLEEP = "sleep";

	private static final String TBL_SUGGESTIONS = "suggestions";



	private static final String TBL_SCHEDULE = "schedule";

	private static final String TBL_MONITORING = "monitoring";

	private static final String TBL_GI_INDEX = "giIndex";

	private static final String TBL_USER_PROFILING = "userProfiling";

	

	private long uid;

	private long aid;

	private long sid;

	private long upid;

	

	private ArrayList<Long> amid = new ArrayList<Long>();

	private ArrayList<Long> aiid = new ArrayList<Long>();

	private ArrayList<Long> abid = new ArrayList<Long>();

	private ArrayList<Long> aeid = new ArrayList<Long>();

	private ArrayList<Long> asid = new ArrayList<Long>();

	

	private static final String CREATE_TBL_USER = "CREATE TABLE  IF NOT EXISTS "

			+ TBL_USER

			+ "(UID INTEGER PRIMARY KEY, NAME TEXT, AGE INTEGER, EMAIL TEXT, DrNAME TEXT, "

			+ "DrPHONE INTEGER, ADDRESS TEXT, EMERGENCY INTEGER)";



	private static final String CREATE_TBL_ACTIVITIES = "CREATE TABLE  IF NOT EXISTS "

			+ TBL_ACTIVITIES + "(AID INTEGER PRIMARY KEY, ACTIVITY_NAME TEXT)";



	private static final String CREATE_TBL_ACTIVITY_MEAL = "CREATE TABLE  IF NOT EXISTS "

			+ TBL_ACTIVITY_MEAL

			+ "(AID INTEGER PRIMARY KEY, ACTIVITY_SUBTYPE TEXT, ACTIVITY_TIME TEXT)";



	private static final String CREATE_TBL_ACTIVITY_INSULIN = "CREATE TABLE  IF NOT EXISTS "

			+ TBL_ACTIVITY_INSULIN

			+ "(AID INTEGER PRIMARY KEY, ACTIVITY_SUBTYPE TEXT, ACTIVITY_TIME TEXT)";



	private static final String CREATE_TBL_ACTIVITY_BG = "CREATE TABLE  IF NOT EXISTS "

			+ TBL_ACTIVITY_BG

			+ "(AID INTEGER PRIMARY KEY, ACTIVITY_SUBTYPE TEXT, ACTIVITY_TIME TEXT)";



	private static final String CREATE_TBL_ACTIVITY_EXERCISE = "CREATE TABLE  IF NOT EXISTS "

			+ TBL_ACTIVITY_EXERCISE

			+ "(AID INTEGER PRIMARY KEY, ACTIVITY_SUBTYPE TEXT, ACTIVITY_TIME TEXT)";



	private static final String CREATE_TBL_ACTIVITY_SLEEP = "CREATE TABLE  IF NOT EXISTS "

			+ TBL_ACTIVITY_SLEEP

			+ "(AID INTEGER PRIMARY KEY, ACTIVITY_SUBTYPE TEXT, ACTIVITY_TIME TEXT)";



	private static final String CREATE_TBL_SUGGESTIONS = "CREATE TABLE  IF NOT EXISTS "

			+ TBL_SUGGESTIONS

			+ "(AID INTEGER PRIMARY KEY, ACTIVITY_SUBTYPE TEXT, ACTIVITY_DAY TEXT, ACTIVITY_TIME TEXT)";



	private static final String CREATE_TBL_SCHEDULE = "CREATE TABLE  IF NOT EXISTS "

			+ TBL_SCHEDULE

			+ "(SID INTEGER PRIMARY KEY, S_AID INTEGER,  ACTIVITY_TIME DATETIME, "

			+ "FOREIGN KEY(S_AID) REFERENCES " + TBL_ACTIVITIES + "(AID))";



	private static final String CREATE_TBL_MONITORING = "CREATE TABLE  IF NOT EXISTS "

			+ TBL_MONITORING

			+ "(MID INTEGER PRIMARY KEY, TIMESTAMP DATETIME, INSULIN INTEGER, "

			+ "BGLUCOSE INTEGER, FOOD TEXT, MISCELLANEOUS TEXT)";



	private static final String CREATE_TBL_GI_INDEX = "CREATE TABLE  IF NOT EXISTS "

			+ TBL_GI_INDEX

			+ "(GID INTEGER PRIMARY KEY, FOOD_ITEM TEXT, GI_VALUE INTEGER, EMERGENCY BOOLEAN)";

	

	private static final String CREATE_TBL_USER_PROFILING = "CREATE TABLE  IF NOT EXISTS "

			+ TBL_USER_PROFILING

			+ "(UPID INTEGER PRIMARY KEY, PROFILE_LOADED BOOLEAN)";



	private Context context;



	private SQLiteDatabase T1DM_Database;



	public DatabaseHandler() {

		loadAllIDs();

	}



	private void loadAllIDs()

	{

		this.uid = 1;//getPrimaryKeyID(TBL_USER, "uid");

		//this.amid = 1;//getPrimaryKeyID(TBL_ACTIVITY_MEAL, "aid");

		for(int i=1;i<=5;i++)this.amid.add(Long.valueOf(i));

		//this.aiid = 1;//getPrimaryKeyID(TBL_ACTIVITY_INSULIN, "aid");

		for(int i=1;i<=5;i++)this.aiid.add(Long.valueOf(i));

		//this.abid = 1;//getPrimaryKeyID(TBL_ACTIVITY_BG, "aid");

		for(int i=1;i<=5;i++)this.abid.add(Long.valueOf(i));

		//this.aeid = 1;//getPrimaryKeyID(TBL_ACTIVITY_EXERCISE, "aid");

		for(int i=1;i<=5;i++)this.aeid.add(Long.valueOf(i));

		//this.asid = 1;//getPrimaryKeyID(TBL_ACTIVITY_SLEEP, "aid");

		for(int i=1;i<=5;i++)this.asid.add(Long.valueOf(i));

		this.sid = 1;//getPrimaryKeyID(TBL_SUGGESTIONS, "aid");

		this.upid = 1;//getPrimaryKeyID(TBL_USER_PROFILING, "upid");

	}

	public void onCreate(SQLiteDatabase T1DM_Database) {



		this.T1DM_Database = T1DM_Database;

		T1DM_Database.execSQL(CREATE_TBL_USER);

		T1DM_Database.execSQL(CREATE_TBL_ACTIVITIES);

		T1DM_Database.execSQL(CREATE_TBL_ACTIVITY_MEAL);

		T1DM_Database.execSQL(CREATE_TBL_ACTIVITY_INSULIN);

		T1DM_Database.execSQL(CREATE_TBL_ACTIVITY_BG);

		T1DM_Database.execSQL(CREATE_TBL_ACTIVITY_EXERCISE);

		T1DM_Database.execSQL(CREATE_TBL_ACTIVITY_SLEEP);

		T1DM_Database.execSQL(CREATE_TBL_SUGGESTIONS);

		T1DM_Database.execSQL(CREATE_TBL_SCHEDULE);

		T1DM_Database.execSQL(CREATE_TBL_MONITORING);

		T1DM_Database.execSQL(CREATE_TBL_GI_INDEX);

		T1DM_Database.execSQL(CREATE_TBL_USER_PROFILING);

	}



	

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		// TODO Auto-generated method stub



	}



	public boolean dropDatabase() {

		boolean status = false;

		File dbFile = new File(commonMethods.DB_PATH + File.separator + commonMethods.DB_FOLDER + File.separator + commonMethods.DB_NAME); 

		if (dbFile.exists())

		    status = dbFile.delete();

		else

			status=true;

		return status;

	}



	// Returns 1 on success else 0

	public long insertUser(UserDetails userDetails) {

		SQLiteDatabase db = this.getWritableDatabase();

		long user_id = -1;

		if (db != null) {

			ContentValues values = new ContentValues();



			values.put("NAME", userDetails.get_NAME());

			values.put("AGE", userDetails.get_AGE());

			values.put("EMAIL", userDetails.get_EMAIL());

			values.put("DrNAME", userDetails.get_DrNAME());

			values.put("DrPHONE", userDetails.get_DrPHONE());

			values.put("ADDRESS", userDetails.get_ADDRESS());

			values.put("EMERGENCY", userDetails.get_EMERGENCY());



			try {

				user_id = db.update(TBL_USER, values, " uid="+this.uid,null);

			} catch (Exception e) {

				user_id = -1;

				return user_id;

			} finally {

				db.close();

			}



		}



		return user_id;

	}



	public List<UserDetails> getUsers() {

		List<UserDetails> users = new ArrayList<UserDetails>();



		String selectQuery = "SELECT  * FROM " + TBL_USER;



		SQLiteDatabase db = this.getReadableDatabase();



		if (db != null) {

			Cursor cursor = db.rawQuery(selectQuery, null);



			// looping through all rows and adding to list

			if (cursor.moveToFirst()) {

				do {

					UserDetails user = new UserDetails();

					user.set_NAME(cursor.getString(1));

					user.set_AGE(Integer.parseInt(cursor.getString(2)));

					user.set_EMAIL(cursor.getString(3));

					user.set_DrNAME(cursor.getString(4));

					user.set_DrPHONE(Long.parseLong(cursor.getString(5)));

					user.set_ADDRESS(cursor.getString(6));

					user.set_EMERGENCY(Long.parseLong(cursor.getString(7)));

					users.add(user);

				} while (cursor.moveToNext());

			}

			cursor.close();

			db.close();

		}

		return users;

	}



	public boolean checkExactlyOneRowInDatabase()

	{

		boolean status = true;

		boolean cu = ((getRecordCount(TBL_USER) > -1) ? true:false);

		boolean ca = ((getRecordCount(TBL_ACTIVITIES) > -1) ? true:false);

		boolean cam = ((getRecordCount(TBL_ACTIVITY_MEAL) > -1) ? true:false);

		boolean cai = ((getRecordCount(TBL_ACTIVITY_INSULIN) > -1) ? true:false);

		boolean cab = ((getRecordCount(TBL_ACTIVITY_BG) > -1) ? true:false);

		boolean cae = ((getRecordCount(TBL_ACTIVITY_EXERCISE) > -1) ? true:false);

		boolean cas = ((getRecordCount(TBL_ACTIVITY_SLEEP) > -1) ? true:false);

		boolean cs = ((getRecordCount(TBL_SUGGESTIONS) > -1) ? true:false);

		

		if (cu || ca || cam || cai || cab || cae || cas || cs)

		status = false;

		

		return status;

	}

	

	public int getRecordCount(String TABLE_NAME) {

		int c = -1;

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = null;

		if (db != null) {

			try {

				String count = "SELECT  * FROM " + TABLE_NAME;



				cursor = db.rawQuery(count, null);



				// return count

				c = cursor.getCount();

				

			} catch (Exception e) {

				c = -1;

				return c;

			} finally {

				if (cursor!=null)

					cursor.close();

				db.close();

			}

		}

		return c;

	}



	// Returns 1 on success else 0

	public long insertActivitySchedule(

			List<ActivityScheduleDetail> activityDetail) {



		SQLiteDatabase db = this.getWritableDatabase();



		ContentValues values = new ContentValues();



		long activity_id = -1;



		try {

			for (int i = 0; i < activityDetail.size(); i++) {

				activity_id = -1;

				values = new ContentValues();

				values.put("ACTIVITY_SUBTYPE", activityDetail.get(i)

						.get_SubType());

				values.put("ACTIVITY_TIME", activityDetail.get(i).get_Time());



				switch (activityDetail.get(i).get_Type()) {

				case 0:					

					activity_id = db.update(TBL_ACTIVITY_MEAL, values, " aid="+this.amid.get(i),null);

					break;

				case 1:

					activity_id = db.update(TBL_ACTIVITY_INSULIN, values, " aid="+this.aiid.get(i),null);

					break;

				case 2:

					activity_id = db.update(TBL_ACTIVITY_BG, values, " aid="+this.abid.get(i),null);

					break;

				case 3:

					activity_id = db

							.update(TBL_ACTIVITY_EXERCISE, values, " aid="+this.aeid.get(i),null);

					break;

				case 4:

					activity_id = db.update(TBL_ACTIVITY_SLEEP, values, " aid="+this.asid.get(i),null);

					break;

				}

			}

		}



		catch (Exception e) {

			activity_id = -1;

			return activity_id;

		} finally {

			db.close();

		}

		return activity_id;

	}



	// Returns 1 on success else 0

	public long insertSuggestionSchedule(ActivityScheduleDetail activityDetail) {

		SQLiteDatabase db = this.getWritableDatabase();



		ContentValues values = new ContentValues();



		long activity_id = -1;

		if (db != null) {

			try {

				values = new ContentValues();

				values.put("ACTIVITY_SUBTYPE", activityDetail.get_SubType());

				values.put("ACTIVITY_DAY", activityDetail.get_Day());

				values.put("ACTIVITY_TIME", activityDetail.get_Time());



				activity_id = db.update(TBL_SUGGESTIONS, values, " aid="+this.sid,null);

			}



			catch (Exception e) {

				activity_id = -1;

				return activity_id;

			} finally {

				db.close();

			}

		}

		return activity_id;

	}

	

	// Returns 1 on success else 0

		public long updateUserProfiling(boolean profileLoaded) {

			SQLiteDatabase db = this.getWritableDatabase();



			ContentValues values = new ContentValues();



			long pid = -1;

			if (db != null) {

				try {

					values = new ContentValues();

					values.put("PROFILE_LOADED", profileLoaded);

					pid = db.update(TBL_USER_PROFILING, values, " upid="+this.upid,null);

				}



				catch (Exception e) {

					pid = -1;

					return pid;

				} finally {

					db.close();

				}

			}

			return pid;

		}

	

		public boolean getUserProfiling()

		{

			SQLiteDatabase db = this.getReadableDatabase();

			String status = null;

			Cursor cursor = null;

			boolean retVal = false;

			if (db != null) {

				try {

					String query = "SELECT * FROM " + TBL_USER_PROFILING;

					

					cursor = db.rawQuery(query, null);		

					int c = cursor.getCount();

					if (cursor.moveToFirst()) 					    

						status = cursor.getString(cursor.getColumnIndex("PROFILE_LOADED"));					    

					  

					

				} catch (Exception e) {

					

					return false;

				} finally {

					if (cursor!=null)

						cursor.close();

					db.close();

				}

			}

			

			if (status!=null && status.equals("1"))

				retVal = true;

			

			return retVal;

		}

		

		public int getPrimaryKeyID(String TABLE_NAME, String Primary_Key)

		{

			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = null;

			int keyID = -1;

			if (db != null) {

				db.close();

				try {

					String query = "SELECT "+Primary_Key+" FROM " + TABLE_NAME;



					cursor = db.rawQuery(query, null);					

					if (cursor.moveToFirst()) 					    

						keyID = cursor.getInt(1);					    

					  

					

				} catch (Exception e) {

					keyID = -1;

					return keyID;

				} finally {

					if (cursor!=null)

					cursor.close();

					db.close();					

				}

			}

			return keyID;

		}

	// Returns 1 on success else 0

		public long insertBGReading(String timeStamp, int insulin, int reading, String food, String misc) {

			SQLiteDatabase db = this.getWritableDatabase();



			ContentValues values = new ContentValues();



			long reading_id = -1;

			if (db != null) {

				try {

					values = new ContentValues();

					//TIMESTAMP DATETIME, INSULIN INTEGER, BGLUCOSE INTEGER, FOOD TEXT, MISCELLANEOUS TEXT

                    values.put("TIMESTAMP", timeStamp);

                    values.put("INSULIN", insulin);

                    values.put("BGLUCOSE", reading);

                    values.put("FOOD", food);

                    values.put("MISCELLANEOUS", misc);

                    reading_id = db.insert(TBL_MONITORING, null, values);

				}



				catch (Exception e) {

					reading_id = -1;

					return reading_id;

				} finally {

					db.close();

				}

			}

			return reading_id;

		}



		

		public boolean insertOneRowInAllTables()

		{

			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values;

			boolean status = false;

			if (db != null) {

				try {

					if (getRecordCount(TBL_USER)<1)

					{

						values = new ContentValues();

						values.put("NAME", "Dummy");

						uid = db.insert(TBL_USER, null, values);

					}

					

					if (getRecordCount(TBL_ACTIVITIES)<1)

					{

						values = new ContentValues();

						values.put("ACTIVITY_NAME", "Dummy");

						aid = db.insert(TBL_ACTIVITIES, null, values);

					}

					

					if (getRecordCount(TBL_ACTIVITY_MEAL)<1)

					{

						values = new ContentValues();

						values.put("ACTIVITY_SUBTYPE", "Dummy");

						for(int i=0;i<5;i++)

					    amid.add(db.insert(TBL_ACTIVITY_MEAL, null, values));

					}

					

					if (getRecordCount(TBL_ACTIVITY_INSULIN)<1)

					{

						values = new ContentValues();

						values.put("ACTIVITY_SUBTYPE", "Dummy");

						for(int i=0;i<5;i++)

						    aiid.add(db.insert(TBL_ACTIVITY_INSULIN, null, values));

					}

					if (getRecordCount(TBL_ACTIVITY_BG)<1)

					{

						values = new ContentValues();

						values.put("ACTIVITY_SUBTYPE", "Dummy");

						for(int i=0;i<5;i++)

						abid.add(db.insert(TBL_ACTIVITY_BG, null, values));

					}

					

					if (getRecordCount(TBL_ACTIVITY_EXERCISE)<1)

					{

						values = new ContentValues();

						values.put("ACTIVITY_SUBTYPE", "Dummy");

						for(int i=0;i<5;i++)

						aeid.add(db.insert(TBL_ACTIVITY_EXERCISE, null, values));

					}

					

					if (getRecordCount(TBL_ACTIVITY_SLEEP)<1)

					{

						values = new ContentValues();

						values.put("ACTIVITY_SUBTYPE", "Dummy");

						for(int i=0;i<5;i++)

						asid.add(db.insert(TBL_ACTIVITY_SLEEP, null, values));

					}

					

					if (getRecordCount(TBL_SUGGESTIONS)<1)

					{

						values = new ContentValues();

						values.put("ACTIVITY_SUBTYPE", "Dummy");

						sid = db.insert(TBL_SUGGESTIONS, null, values);

					}

					

					if (getRecordCount(TBL_USER_PROFILING)<1)

					{

						values = new ContentValues();

						values.put("PROFILE_LOADED", 0);

						upid = db.insert(TBL_USER_PROFILING, null, values);

					}

					

					if (uid>0 && aid>0 && amid.size()>0 && aiid.size() >0 && abid.size() >0 && aeid.size() >0 

							&& asid.size() >0 && sid>0 && upid>0)

						status=true;

				}



				catch (Exception e) {

					return status;

				} finally {

					db.close();

				}

			}

			return status;

		}

		

	public ArrayList<MonitoringReadings> getMonitoringReadings(){

		SQLiteDatabase db = this.getReadableDatabase();

		MonitoringReadings reading = null;

		ArrayList<MonitoringReadings> readings = new ArrayList<MonitoringReadings>();

		Cursor cursor = null;

		

		if (db != null) {

			try {

				String query = "SELECT * FROM " + TBL_MONITORING;

				

				cursor = db.rawQuery(query, null);		

				

				if (cursor.moveToFirst()){ 

					reading = new MonitoringReadings();

					reading.set_Timestamp(cursor.getString(cursor.getColumnIndex("TIMESTAMP")));

					reading.set_Reading(cursor.getInt(cursor.getColumnIndex("BGLUCOSE")));

					readings.add(reading);

				}

				

			} catch (Exception e) {

				

				return readings;

			} finally {

				if (cursor!=null)

					cursor.close();

				db.close();

			}

			

		}

		

		return readings;

	}

	public SQLiteDatabase getReadableDatabase() {

		SQLiteDatabase db = null;

		try{

		db = SQLiteDatabase.openDatabase(commonMethods.DB_PATH + File.separator + commonMethods.DB_FOLDER

				+ File.separator + commonMethods.DB_NAME, null, SQLiteDatabase.OPEN_READONLY);

		}

		catch(Exception e){

			

		}

		finally{

			//if (db!=null)

			//db.close();

		}

		return db;

	}



	public SQLiteDatabase getWritableDatabase() {

		SQLiteDatabase db = null; 

		

		try{

		db = SQLiteDatabase.openOrCreateDatabase(commonMethods.DB_PATH + File.separator + commonMethods.DB_FOLDER

				+ File.separator + commonMethods.DB_NAME, null);

		if (db!=null)

		onCreate(db);

		}catch(Exception e){

			

		}finally{

			//if (db!=null)

			//db.close();

		}

		return db;

	}



}