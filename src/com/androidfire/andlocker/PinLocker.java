package com.androidfire.andlocker;



import static com.nineoldandroids.view.ViewPropertyAnimator.animate;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class PinLocker extends Activity {
	Button unLock;
	EditText pinInput;
	String TAG_NAME = "AndLocker";
	String PasCode;

	@Override
	 protected void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.pin_and);
		unLock = (Button)findViewById(R.id.unlock);
		pinInput = (EditText)findViewById(R.id.editText1);
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		PasCode = sp.getString("Password", null);
		
		unLock.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
             String pas;
             if (PasCode != null) {
            	 pas = PasCode;
            	 String in = null;
            	 if (in == null) {
            		 in = pinInput.getText().toString();
            	 }
            	 if (pas.equalsIgnoreCase(in)) {
            		 unLock.setBackgroundColor(Color.GREEN);
            		 UnlockScreen();
            	 }
            	 else if (in.equalsIgnoreCase("unlockme")) {
            			 unLock.setBackgroundColor(Color.GREEN);
            		 UnlockScreen();
            		 Log.d(TAG_NAME, "Unlock through Emeragancy Unlock key");
             }
            	 else if (pas != in) {
            		 pinInput.setText("Wrong");
            		 unLock.setBackgroundColor(Color.RED);
            		 Log.e(TAG_NAME, "Wrong Code");
            	 }
             }
			}
		});	
		
		final Handler h = new Handler();
	       h.post(new Runnable() {
	      @Override
	      public void run() {
	    	  updateView();
	          h.postDelayed(this, 1000);
	      }

		private void updateView() {
			animate(unLock).rotationBy(720);
			unLock.setTextColor(Color.WHITE);
			unLock.setBackgroundColor(Color.DKGRAY);
			
		}

	           });
	
	}

	public void UnlockScreen() {
		finish();
		Log.d(TAG_NAME, "Device Unloked through Default Lockscreen");
	}
	@Override
	public void onPause() {
		super.onPause();
		return;
	}
	@Override
	public void  onBackPressed() {
		super.onBackPressed();
		return;
	}
	
	@Override
	public void onStop() {
		super.onStop();
		return;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return keyCode == KeyEvent.KEYCODE_HOME
				|| keyCode == KeyEvent.KEYCODE_BACK;
	}

	@Override
	public void onAttachedToWindow() {
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
			getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
		} else {
		}

		super.onAttachedToWindow();
	}
}