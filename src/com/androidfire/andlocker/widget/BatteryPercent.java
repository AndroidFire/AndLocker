package com.androidfire.andlocker.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.util.AttributeSet;
import android.widget.TextView;

public class BatteryPercent extends TextView {

	public BatteryPercent(Context context, AttributeSet attrs) {
		super(context, attrs);
		BroadcastReceiver BatteryPercent = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				int level = intent.getIntExtra("level", 0);
				int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
				boolean batteryCharge = status == BatteryManager.BATTERY_STATUS_CHARGING;

				int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,
						-1);
				boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
				boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
				

				if ((acCharge) || (usbCharge) || (batteryCharge)) {
					setText(level + "% " + "Charging");
					setTextColor(Color.GREEN);
				} else {
					if (level < 20) {
						setText(level + "% " + "Low Battery");
						setTextColor(Color.RED);

					} else {
						setText(level + "% " + "Battery");
						setTextColor(Color.WHITE);
					}

				}

			}

		};
		getContext().registerReceiver(BatteryPercent, new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED));
	}



}
