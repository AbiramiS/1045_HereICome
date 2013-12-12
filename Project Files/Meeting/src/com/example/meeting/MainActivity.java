package com.example.meeting;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
Button b1,b2,b3;TextView t1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		t1=(TextView)findViewById(R.id.textView1);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		Bundle extras=getIntent().getExtras();
	    String value1 = extras.getString("tag_person_name");
	    String value2 = extras.getString("tag_person_pin");
	    String value3 = extras.getString("e1");
	    String value4 = extras.getString("tag_person_pin3");

		//String i4=getIntent().getExtras().getString("tag_person_name");
		//String i1=getIntent().getExtras().getString("tag_person_pin");
		//String i2=getIntent().getExtras().getString("tag_person_pin2");
		//String i3=getIntent().getExtras().getString("tag_person_pin3");
		t1.setText(value1+"\n"+value2+"\n"+value3+"\n"+value4);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button1:
		Intent i=new Intent(MainActivity.this,Enter2.class);
		startActivity(i);
		break;
		case R.id.button2:
			t1.setText("");
			break;
		case R.id.button3:
			Intent i1=new Intent(MainActivity.this,Map1.class);
			i1.putExtra("loc",t1.getText().toString());
			startActivity(i1);	
			break;
		}
	}
	
}
