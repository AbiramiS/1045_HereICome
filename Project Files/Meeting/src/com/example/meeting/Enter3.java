package com.example.meeting;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Enter3 extends Activity{
	TextView t1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.abc);
		t1=(TextView)findViewById(R.id.textView1);
		
		 double s=getIntent().getExtras().getDouble("latd");
			double s1=getIntent().getExtras().getDouble("lond");
			String s4=s+""+s1;
			t1.setText(s4);
		
	}

}
