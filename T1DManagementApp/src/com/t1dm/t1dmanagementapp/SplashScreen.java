package com.t1dm.t1dmanagementapp;



import java.io.File;



import android.os.AsyncTask;

import android.os.Bundle;

import android.widget.Toast;

import android.app.Activity;

import android.content.Intent;



public class SplashScreen extends Activity {



	private CommonMethods commonMethods = new CommonMethods();



	private T1DMApplication appContext;



	@Override

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash_screen);

		appContext = ((T1DMApplication) getApplication());

		appContext.setContext(getApplicationContext());

		appContext.setDbHandler(new DatabaseHandler());

		// Thread splashThread = new Thread() {

		// public void run() {



				// }

		// };

		// splashThread.start();



	}



	@Override

	public void onDestroy() {

		super.onDestroy();

	}



	private class AsyncDatabaseOperations extends AsyncTask<Void, Void, Void>{

		@Override

	      protected void onPreExecute(){

			super.onPreExecute();

	      }



	      @Override

	      protected Void doInBackground(Void... voids){

	    	  try {

	  			// sleep(loadingTime);



	  			boolean next = commonMethods.decideNextActivity();

	  			if (next)

	  				startActivity(new Intent(appContext.getContext(),

	  						BeginMonitoring.class));



	  			else {

	  				File dbPath = new File(commonMethods.DB_PATH + File.separator

	  						+ commonMethods.DB_FOLDER);

	  				if (!dbPath.isDirectory())

	  					commonMethods.createDatabaseFolder();



	  				boolean createTableStatus = appContext.getDbHandler()

	  						.insertOneRowInAllTables();

	  				if (createTableStatus) {

	  					// boolean status = commonMethods.dropDatabase();



	  					// if (status) {



	  					startActivity(new Intent(appContext.getContext(),

	  							UserDetailsActivity.class));

	  				} else {

	  					Toast.makeText(

	  							appContext.getContext(),

	  							"T1DM says, oops database inconsistency found, I don't know what to do !!!!\n You can delete database file",

	  							Toast.LENGTH_LONG).show();

	  					finish();

	  				}

	  			}

	  		} catch (Exception e) {

	  			e.printStackTrace();

	  		} finally {

	  			// finish(); //We finish this thread.

	  		}



			return null;

	      }



	      @Override

	      protected void onPostExecute(Void params){

	            

	      }



	}

}