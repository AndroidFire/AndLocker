package com.androidfire.andlocker;


import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;

@SuppressWarnings("deprecation")
public class LockerService extends Service {
	
	/**
	 * @author SpaceCaker
	 */

	private KeyguardLock mKeyguardLock;
	private static boolean mEnabled;
	SharedPreferences sp;
	String TAG_NAME = "AndLocker";
	String mStyle;

	@Override
	public void onStart(Intent intent, int startId) {
		
		Log.d(TAG_NAME, "Starting LockedService");
		
		sp = PreferenceManager
				.getDefaultSharedPreferences(this);
	    
	    Editor mEditor = sp.edit();
		mEditor.putBoolean("AndLocker", true);
		mEditor.commit();
		mEnabled = sp.getBoolean("AndLocker", false);
		mStyle = sp.getString("style", null);
		
		
		if (mEnabled == false) {
			stopSelf();
			Log.d(TAG_NAME, "Stoped Locked Service");
			return;
		}
		if (mEnabled == true) {
		mKeyguardLock = ((KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE))
				.newKeyguardLock("AndLocker");
		mKeyguardLock.disableKeyguard();

		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		intentFilter.setPriority(32000);
		registerReceiver(mReceiver, intentFilter);
		}
		
	}

	@Override
	public  void onDestroy() {
		if (!mEnabled)
			return;

		unregisterReceiver(mReceiver);
		mKeyguardLock.reenableKeyguard();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			TelephonyManager ts = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			int callState = ts.getCallState();
			if (callState == TelephonyManager.CALL_STATE_IDLE) {
				Log.d(TAG_NAME, ""+mStyle);
				
				if (mStyle == null) {
					context.startActivity(new Intent(context,
							DefaultLock.class)
					.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
				}
				else if (mStyle == "Def") {
					context.startActivity(new Intent(context,
							DefaultLock.class)
					.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
				}
				else if (mStyle == "Pin") {
					context.startActivity(new Intent(context,
							PinLocker.class)
					.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
				}
			}
		}

	};

}
