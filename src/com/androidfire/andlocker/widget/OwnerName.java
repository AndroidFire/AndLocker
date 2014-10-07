package com.androidfire.andlocker.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OwnerName extends TextView {
	String OwnerText;
	String message;

	public OwnerName(Context context, AttributeSet attrs) {
		super(context, attrs);
		final SharedPreferences sp = getContext().getSharedPreferences("com.androidfire.andlocker_preferences", Context.MODE_PRIVATE);
		setOnLongClickListener(new OnLongClickListener() {
		    public boolean onLongClick(View arg0) {
		        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
	               final EditText input = new EditText(getContext());
	                builder.setTitle("Enter Owner Name");
	             
	                builder.setView(input);
	                String getOwn = sp.getString("Owner", null);
	                input.setText(getOwn);
	                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	                @Override
	                  public void onClick(DialogInterface arg0, int arg1) {
	             
	            	 Toast.makeText(getContext(), "Owner Has Saved", Toast.LENGTH_LONG).show();
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
	                    Toast.makeText(getContext(), "Close", Toast.LENGTH_LONG).show();
	                  }
	                });
	                builder.show(); //To show the AlertDialog
	    		  
		        
				return true;
		    

		    }
		});
		final Handler h = new Handler();
	       h.post(new Runnable() {
	      @Override
	      public void run() {
	          updateOwner();
	          h.postDelayed(this, 1000);
	      }


		private void updateOwner() {

			
			OwnerText = sp.getString("Owner", null);
			if (OwnerText == null) {
				setText("Owner");
			}
			else if (OwnerText != null) {
				setText(OwnerText);
			}
			setTextColor(Color.WHITE);
			
			
		}

	       });
	}
}
