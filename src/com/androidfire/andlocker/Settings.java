package com.androidfire.andlocker;



import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;


public class Settings extends PreferenceActivity  {
	String TAG_NAME = "AndLocker";
	boolean AFToggle;
	Editor mEditor;
	CheckBoxPreference mAndToggle;
	String color;
	Preference setPin;
	Preference Owner;
	ListPreference mStyle;
	String pas;
	Preference Ver;
	Preference Author;
	/**
	 * @author AndroidFire
	 */
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle mBundle) {
		super.onCreate(mBundle);
		addPreferencesFromResource(R.xml.settings_view);
		final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		if (Settings.class != null) {
			Log.d(TAG_NAME, "Loaded AndLocker");
			Log.d(TAG_NAME, "AndLocker is on Settings");
			/** If the Settings is not equal zero*/
			 
		}
		else {
			Log.e(TAG_NAME, "Something Went Wrong");
			Log.e(TAG_NAME, "Exiting Class");
			finish();
			/**Yet Something Went wrong with the 
			 * settings class now it will finish the class*/
		}
		 mAndToggle = (CheckBoxPreference) findPreference("and_toggle");

		  mStyle = (ListPreference) findPreference("style");
		  
		  setPin = (Preference) findPreference("pin");
		  
		  Author = (Preference) findPreference("author");
		  Ver = (Preference) findPreference("ver");
		  Author.setSummary("AndroidFire");
		  Ver.setSummary("1.1");
		  

		  Author.setOnPreferenceClickListener(new OnPreferenceClickListener() {
	            public boolean onPreferenceClick(Preference preference) {
						Uri mAuthor = Uri.parse("http://forum.xda-developers.com/member.php?u=5930923");
						 Intent click_me =  new Intent(Intent.ACTION_VIEW, mAuthor);
						click_me.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(click_me);
						
						return true;
	            }
	        });	     
		  Ver.setSelectable(false);
		  
		  
		  Owner = (Preference) findPreference("owner");
			 mAndToggle.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

					@Override
					public boolean onPreferenceChange(Preference preference, Object newValue) {
					if (mAndToggle.isChecked()) {
						Intent i = new Intent(getBaseContext(), LockerService.class);
					    stopService(i);


					}
					else {
						Intent i = new Intent(getBaseContext(), LockerService.class);
					    startService(i);
					  
					}
					return true;

					}

		        });
			 mStyle.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

					@Override
					public boolean onPreferenceChange(Preference preference, Object newValue) {
						String SY = (String.valueOf(newValue));
						Intent i = new Intent();
						i.setAction("com.androidfire.andlocker.CHANGE_STYLE");
						i.putExtra("STYLE",SY);
						sendBroadcast(i);
						return true;
					}
		        });
		       
			 setPin.setOnPreferenceClickListener(new OnPreferenceClickListener() {
		            public boolean onPreferenceClick(Preference preference) {
							final String getPas = sp.getString("Password", null);
							
		                AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
		               final EditText input = new EditText(getBaseContext());
		               // builder.setTitle("Enter New Password Code");
		                if (getPas == null) {
		                	 builder.setTitle("Enter New Password Code");
		                	 Log.d(TAG_NAME, "Password is null");
		                }
		                else if (getPas != null) {
		                	 builder.setTitle("Enter Old Password Password Code");
		                	 Log.d(TAG_NAME, "There old Password");
		                }
		                builder.setView(input);
		                
		                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		                @Override
		                  public void onClick(DialogInterface arg0, int arg1) {
		             if (getPas == null) {
		            	 Toast.makeText(getApplicationContext(), "Password Has Saved", Toast.LENGTH_LONG).show();
		             
		                    String passCode = input.getText().toString();
		                    Editor mEditor;
		                    mEditor = sp.edit();
		                    mEditor.putString("Password", passCode);
		                    mEditor.commit();
		                    Log.d(TAG_NAME, "Password Saved "+passCode);
		             
		             
		             }
		             else if (getPas != null) {
		            	 AlertDialog.Builder builderfinal = new AlertDialog.Builder(Settings.this);
		            	 builderfinal.setTitle("Enter Your New Password");
		            	 final EditText input2 = new EditText(getBaseContext());
		            	 builderfinal.setView(input2);
		            	 String in = input.getText().toString();
		            	 

		            	 
		            	 builderfinal.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
		                  @Override
		                  public void onClick(DialogInterface arg0, int arg1) {
		                	  Toast.makeText(getApplicationContext(), "Password Has Saved", Toast.LENGTH_LONG).show();
		 		             
			                    String passCode = input2.getText().toString();
			                    Editor mEditor;
			                    mEditor = sp.edit();
			                    mEditor.putString("Password", passCode);
			                    mEditor.commit();
			                    
			                    Log.d(TAG_NAME, "Password Saved "+passCode);
			             
		                  }
		                });
		            	 builderfinal.setNegativeButton("No", new DialogInterface.OnClickListener() {
			                  @Override
			                  public void onClick(DialogInterface arg0, int arg1) {
			                	  Toast.makeText(getApplicationContext(), "Close", Toast.LENGTH_LONG).show();
			 		             
				                  
				             
			                  }
			                });
	            	 final String getPas = sp.getString("Password", null);
		            	 
		            	 if (getPas.equalsIgnoreCase(in)) {
			            		builderfinal.show();
			            		Log.d(TAG_NAME, "Password has Match to old password");
			            	}
			            	else if (getPas != in) {
			            		Toast.makeText(getApplicationContext(), "Does Not Match", Toast.LENGTH_LONG).show();
			            		Log.e(TAG_NAME, "Old Password does not Match");
			            	}
		         
		             
		        
		                }
		                }
		                
		                });
		                
		                
		                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
		                  @Override
		                  public void onClick(DialogInterface arg0, int arg1) {
		                    Toast.makeText(getApplicationContext(), "Close", Toast.LENGTH_LONG).show();
		                  }
		                });
		                builder.show(); //To show the AlertDialog
		    		  
							return false;
		            }
		        });	   
			 	 
			 Owner.setOnPreferenceClickListener(new OnPreferenceClickListener() {
		            public boolean onPreferenceClick(Preference preference) {
							
		                AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
		               final EditText input = new EditText(getBaseContext());
		                builder.setTitle("Enter Owner Name");
		             
		                builder.setView(input);
		                String getOwn = sp.getString("Owner", null);
		                input.setText(getOwn);
		                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		                @Override
		                  public void onClick(DialogInterface arg0, int arg1) {
		             
		            	 Toast.makeText(getApplicationContext(), "Owner Has Saved", Toast.LENGTH_LONG).show();
		            	String own = input.getText().toString();
		            	 Editor mEditor;
		                    mEditor = sp.edit();
		                    mEditor.putString("Owner", own);
		                    mEditor.commit();
		                    
		                }
		                
		                });
		                
		                
		                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
		                  @Override
		                  public void onClick(DialogInterface arg0, int arg1) {
		                    Toast.makeText(getApplicationContext(), "Close", Toast.LENGTH_LONG).show();
		                  }
		                });
		                builder.show(); //To show the AlertDialog
		    		  
							return false;
		            }
		        });	   
			 	 

			 
			 BroadcastReceiver StyleBR = new BroadcastReceiver() {

					@Override
					public void onReceive(Context context, Intent intent) {
						String message = intent.getStringExtra("STYLE");
						if ("Default Lockscreen".equals(message)) {
							Editor mEditor = sp.edit();
							mEditor.putString("style", "Def");
							mEditor.commit();
							Intent i = new Intent(getBaseContext(), LockerService.class);
						    stopService(i);
						    startService(i);
						    
						}
						else if ("Pin or Password Lockscreen".equals(message)) {
							Editor mEditor = sp.edit();
							mEditor.putString("style", "Pin");
							mEditor.commit();
							Intent i = new Intent(getBaseContext(), LockerService.class);
							stopService(i);
						    startService(i);
						}
					}
				};
			
				getBaseContext().registerReceiver(StyleBR, new IntentFilter("com.androidfire.andlocker.CHANGE_STYLE"));
			
	}
    public  boolean GetStringSP(String name, boolean value) {
    	SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		sp.getBoolean(name, value);
		return true;
    }

	public 	String getSringSP (String name,String value) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		sp.getString(name, value);
		return null;
		
		
	}
	
	
}
