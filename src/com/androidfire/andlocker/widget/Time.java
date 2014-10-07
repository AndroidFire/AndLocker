package com.androidfire.andlocker.widget;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

public class Time extends TextView {

	public Time(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		final Handler h = new Handler();
	       h.post(new Runnable() {
	      @Override
	      public void run() {
	          updateTime();
	          h.postDelayed(this, 1000);
	      }

		private void updateTime() {
			Calendar mCalendar = Calendar.getInstance();
			SimpleDateFormat mH = new SimpleDateFormat ("h");
			SimpleDateFormat mM = new SimpleDateFormat ("mm");
			String mHour = mH.format(mCalendar.getTime());
			String mMin = mM.format(mCalendar.getTime());
			  setTextColor(Color.WHITE);	
				
				setTextSize(60);
				setText(" "+mHour+":"+mMin);
				setTypeface(Typeface.SANS_SERIF);
				
		}

	           });
	
	}
	}


