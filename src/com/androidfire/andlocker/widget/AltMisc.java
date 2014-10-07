package com.androidfire.andlocker.widget;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

public class AltMisc extends TextView {

	public AltMisc(Context context, AttributeSet attrs) {
		super(context, attrs);
		final Handler h = new Handler();
	       h.post(new Runnable() {
	      @Override
	      public void run() {
	    	  updateView();
	          h.postDelayed(this, 1000);
	      }

		private void updateView() {
			Calendar mCalendar = Calendar.getInstance();
			SimpleDateFormat mDay = new SimpleDateFormat("EEE");
			SimpleDateFormat mDate = new SimpleDateFormat("dd");
			SimpleDateFormat	mMonth = new SimpleDateFormat ("MMMM");
			String mSDay = mDay.format(mCalendar.getTime());
			String Date = mDate.format(mCalendar.getTime());
			String mSMonth = mMonth.format(mCalendar.getTime());
		    setTextColor(Color.WHITE);
			
			
			setText(mSDay+" , "+mSMonth+" "+Date);
			setTypeface(Typeface.DEFAULT_BOLD);
			
		}

	           });
	
	
	}

}
