package com.androidfire.andlocker;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DefaultLock extends Activity {
	LinearLayout main;
	String bg_main;
	String TAG_NAME = "AndLocker";
	ImageView mUnloker;
	SharedPreferences sp;
	Editor mEditor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.def_and);
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		
		main = (LinearLayout)findViewById(R.id.linaemain);
		main.setScrollBarStyle(View.GONE);
		mUnloker = (ImageView)findViewById(R.id.unlocker);

		mUnloker.setOnLongClickListener(new OnLongClickListener() {
		    public boolean onLongClick(View arg0) {
		        mUnloker.setImageResource(R.drawable.inactive_lock);
		        UnlockScreen();
		        
				return true;
		    

		    }
		});
		final Handler h = new Handler();
	       h.post(new Runnable() {
	      @Override
	      public void run() {
	    	  updateView();
	          h.postDelayed(this, 1000);
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

	public Boolean getBoolSP (String name,boolean value) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		sp.getBoolean(name, value);
		return true;
		
	}

	public String getSringSP (String name,String value) {
		 SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		sp.getString(name, value);
		return null;
		
		
	}

	private void updateView() {
		animate(mUnloker).rotationBy(360);
	}
}

