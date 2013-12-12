package com.example.alarm2;

import java.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity {
	
	 TimePicker myTimePicker;
	 Button buttonstartSetDialog, cancel;
	 TextView textAlarmPrompt;
	 TimePickerDialog timePickerDialog;
	 
	 final static int RQS_1 = 1;
	 
	 	private final int duration = 10; // seconds
	    private final int sampleRate = 8000;
	    private final int numSamples = duration * sampleRate;
	    private final double sample[] = new double[numSamples];
	    private final double freqOfTone = 440; // hz
	    private final byte generatedSnd[] = new byte[2 * numSamples];

	    Handler handler = new Handler();
	    
	    
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textAlarmPrompt=(TextView)findViewById(R.id.textView1);
		buttonstartSetDialog=(Button)findViewById(R.id.button1);
		cancel=(Button)findViewById(R.id.button2);
				
		buttonstartSetDialog.setOnClickListener(new OnClickListener(){
			   public void onClick(View v) {
			    textAlarmPrompt.setText("");
			    openTimePickerDialog(false);	    
			   }});
		cancel.setOnClickListener(new OnClickListener(){
			   public void onClick(View v) {
			    textAlarmPrompt.setText("");
			   }});
		
	}

	private void openTimePickerDialog(boolean is24r){
		  Calendar calendar = Calendar.getInstance();
		  
		  timePickerDialog = new TimePickerDialog(
		    MainActivity.this, 
		    onTimeSetListener, 
		    calendar.get(Calendar.HOUR_OF_DAY), 
		    calendar.get(Calendar.MINUTE), 
		    is24r);
		  timePickerDialog.setTitle("Set Alarm Time");  
		        
		  timePickerDialog.show();

		 }
	
	OnTimeSetListener onTimeSetListener
    = new OnTimeSetListener(){

  @Override
  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

   Calendar calNow = Calendar.getInstance();
   Calendar calSet = (Calendar) calNow.clone();

   calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
   calSet.set(Calendar.MINUTE, minute);
   calSet.set(Calendar.SECOND, 0);
   calSet.set(Calendar.MILLISECOND, 0);
   
   if(calSet.compareTo(calNow) <= 0){
    //Today Set time passed, count to tomorrow
    calSet.add(Calendar.DATE, 1);
   }
   
   setAlarm(calSet);
  }};
  
  private void setAlarm(Calendar targetCal){

	  textAlarmPrompt.setText(
	    "\n\n\n"
	    + "Alarm is set at " + targetCal.getTime() + "\n"
	    + "\n");
	  
	  Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
	  PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
	  AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
	  alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
	  
	 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
