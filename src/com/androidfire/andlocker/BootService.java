package com.androidfire.andlocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootService extends BroadcastReceiver {
	
	String mScreen; 
	 String TAG_NAME = "AndLocker";
	/**
	 * @author AndroidFire
	 * @AppProperty AndLocker
	 */

	  @Override
	  public void onReceive(Context context, Intent intent) {
	    Intent i = new Intent(context, LockerService.class);
	    context.startService(i);
	    Log.d(TAG_NAME, "BootService has Started");
		  if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			  mScreen = "On";
		  }
		  else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
			  mScreen = "Off";
		  }
		  Log.d(TAG_NAME, "Screen is "+mScreen);
	    
	  }
	} 