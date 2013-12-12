package com.example.meeting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Enter2 extends Activity implements OnClickListener {
	EditText e1,e2,e3,e4,e5,e6;String persondate,min;Button b1,b2,b3;TextView t1;String loc;String personno;String hours,minutes;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.enterdata);
	 e1 = (EditText) findViewById(R.id.et_person_name);
     e2 = (EditText) findViewById(R.id.et_person_pin);
    e3=(EditText)findViewById(R.id.editText1);
    //e4 = (EditText) findViewById(R.id.editText2);
    //e6=(EditText)findViewById(R.id.editText3);
     e5=(EditText)findViewById(R.id.et_personno);
     b1=(Button)findViewById(R.id.button1);
     b2=(Button)findViewById(R.id.button2);
     b3=(Button)findViewById(R.id.button3);
     b1.setOnClickListener(this);
     b2.setOnClickListener(this);
     b3.setOnClickListener(this);
     
     
}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch(v.getId()){
	case R.id.button1:
		 String personName = e1.getText().toString();
		    String personPIN = e2.getText().toString();
		     //hours= e4.getText().toString();
		    //minutes= e6.getText().toString();
		  loc=e3.getText().toString();
		  // String persondate = e4.getText().toString();
		    personno=e5.getText().toString();
		    if ( personName.length() != 0 && personPIN.length() != 0 ) {

		        Intent newIntent =new Intent(Enter2.this,MainActivity.class);
		     
		        newIntent.putExtra("tag_person_name", personName);
		        newIntent.putExtra("tag_person_pin", personPIN);
		       // newIntent.putExtra("tag_person_pin2", persondate);
		        newIntent.putExtra("e1", loc);
		        newIntent.putExtra("tag_person_pin3", personno);


		       startActivity(newIntent);
		        finish();}
		break;
		case R.id.button2:
			Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
		    //i.putExtra(AlarmClock.EXTRA_MESSAGE,e1.getText().toString());
		    //i.putExtra(AlarmClock.EXTRA_HOUR, hours);
		    //i.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
		    startActivity(i);
			break;
		case R.id.button3:
			Intent i1=new Intent(Enter2.this,Map1.class);
			i1.putExtra("loc",e5.getText().toString());
			startActivity(i1);	
			break;
			
	}
	
}
}
