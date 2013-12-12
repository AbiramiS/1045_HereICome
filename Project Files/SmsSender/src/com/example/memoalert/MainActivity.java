package com.example.memoalert;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button send, clear;
	String num, txt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		send=(Button)findViewById(R.id.button1);
		clear=(Button)findViewById(R.id.button2);
		
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EditText ph=(EditText)findViewById(R.id.et1);
				EditText msg=(EditText)findViewById(R.id.editText1);
				num = ph.getText().toString();
				txt = msg.getText().toString();
				SmsManager sms=SmsManager.getDefault();
		    	sms.sendTextMessage(num, null,txt,null, null);
		    	Toast.makeText(MainActivity.this,"Message Sent", 300).show();
			}
		});

		clear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				((EditText)findViewById(R.id.et1)).setText("");
				((EditText)findViewById(R.id.editText1)).setText("");
				
				Toast.makeText(MainActivity.this,"Message Cleared", 300).show();
			}
		});

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
