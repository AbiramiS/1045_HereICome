package com.example.meeting;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import android.telephony.SmsManager;
import android.text.format.Time;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;


import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class Map1 extends MapActivity{
	double lat,lon;String time,date;
	String da;
	JSONArray contacts = null;
	public class MyLoc implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			
			String coordinates[]={""+location.getLatitude(),""+location.getLongitude()};
			lat=Double.parseDouble(coordinates[0]);
			lon=Double.parseDouble(coordinates[1]);
			Toast.makeText(getBaseContext(),  lat + "," +lon , 3000).show();
		Time today = new Time(Time.getCurrentTimezone());
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
			    	//String num2="9600579559";
			    	
			    	sms.sendTextMessage(num, null, id+"\n"+da,null, null);
			    	Toast.makeText(Map1.this,"Message Sent", 300).show();
			    	//t1.setText(id+"\n"+da);
				}
			} 
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(Map1.this,"Error"+e, 300).show();
			}


		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

	}


	MapView m; GestureDetector mGestureDetector;LocationManager lm;
	LocationListener ll;
	boolean isGPSEnabled = false;
	String num;
	// flag for network status
	boolean isNetworkEnabled = false;
	// flag for GPS status
		boolean canGetLocation = false;
		
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		setContentView(R.layout.map1);
		num=getIntent().getExtras().getString("loc");
		Toast.makeText(Map1.this, num, Toast.LENGTH_LONG).show();
		lm=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        ll=new MyLoc();
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
		
		
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,120000,0, ll);
						}
						if (isGPSEnabled) {
							 lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 120000,0, ll);
						}
						
					}
        
		m=(MapView)findViewById(R.id.mapview);
		m.setBuiltInZoomControls(true);
		 m.setSatellite(true);
	        m.getOverlays().add(new Overlay() {
	            @Override
	            public boolean onTouchEvent(MotionEvent event, MapView mapView) {
	               mGestureDetector.onTouchEvent(event);
	               return super.onTouchEvent(event, mapView);
	            }
	        });
	        mGestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
	        	@Override
	        	public boolean onDoubleTap(MotionEvent e) {
	        		// TODO Auto-generated method stub
	                GeoPoint p = m.getProjection().fromPixels((int)e.getX(), (int)e.getY());
	                Toast.makeText(getBaseContext(),  p.getLatitudeE6() / 1E6 + "," + p.getLongitudeE6() /1E6 , 3000).show();
	                Intent i=new Intent(Map1.this,MapAct.class);
	              i.putExtra("latd", p.getLatitudeE6()/1E6);
	                i.putExtra("lond", p.getLongitudeE6()/1E6);
	                i.putExtra("lat", lat);
	                i.putExtra("lon", lon);
	                startActivity(i);
	             //   Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?saddr="+p.getLatitudeE6() / 1E6+","+p.getLongitudeE6() /1E6+"&daddr="+14+","+80));
	    			//startActivity(i);

	        		return super.onDoubleTap(e);
	        	}
	        });
		}

	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
