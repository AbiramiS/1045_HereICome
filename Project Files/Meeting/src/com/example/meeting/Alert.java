package com.example.meeting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.format.Time;
import android.widget.TextView;
import android.widget.Toast;

public class Alert extends Activity{
public class Mylocation implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub

Toast.makeText(getApplicationContext(), "latitude: "+location.getLatitude()+" longitude: "+location.getLongitude(),Toast.LENGTH_LONG).show();
			
			today = new Time(Time.getCurrentTimezone());
			today.setToNow();
			time=today.format("%k:%M:%S");
			
			date=today.format("%h-%d-%Y");
			Toast.makeText(getApplicationContext(),"Time: "+time+" Date: "+date,Toast.LENGTH_LONG).show();
			 
		 double lat=location.getLatitude();
		 double lon=location.getLongitude();
		 //t1.setText("latitude: "+lat+" longitude: "+lon);
			//t1.setText("latitude: "+location.getLatitude()+" longitude: "+location.getLongitude());
		 try {
			JSONObject json = JSONfunctions.getJSONfromURL("http://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lon+"&sensor=false");
			
				//JSONObject obj=json.getJSONObject("results");
				contacts = json.getJSONArray("results");
				for(int i = 0; i < 1; i++){
					 JSONObject c = contacts.getJSONObject(0);
					 String id = c.getString("formatted_address");
					 
					 da=time+" "+date;
					 
					
					//e1.setText(id+"\n"+da);
					SmsManager sms=SmsManager.getDefault();
			    	/*String num2="9600579559";
			    	String num=getIntent().getExtras().getString("num1");*/
			    	sms.sendTextMessage(num, null, id+"\n"+da,null, null);
			    	Toast.makeText(Alert.this,"Message Sent", 300).show();
			    	t1.setText(id+"\n"+da);
				}
			} 
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(Alert.this,"Error"+e, 300).show();
			}

		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub

		}

	}


String num;TextView t1;
LocationManager lm;LocationListener ll;
Time today;
String  time,date;
String da;
JSONArray contacts = null;
//flag for GPS status
	boolean isGPSEnabled = false;

	// flag for network status
	boolean isNetworkEnabled = false;
	// flag for GPS status
		boolean canGetLocation = false;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert);
		Bundle d=new Bundle();
    	d=getIntent().getExtras();
    	num=d.getString("num");
    	 t1=(TextView)findViewById(R.id.textView1);
    	lm=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        ll=new Mylocation();
       // lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, ll);
					isGPSEnabled = lm
							.isProviderEnabled(LocationManager.GPS_PROVIDER);

					// getting network status
					isNetworkEnabled = lm
							.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
					if (!isGPSEnabled && !isNetworkEnabled) {
						// no network provider is enabled
					} else {
						this.canGetLocation = true;
						if (isNetworkEnabled) {
		
		
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,0, ll);
						}
						if (isGPSEnabled) {
							 lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,0, ll);
						}
						
					}

	}
}
