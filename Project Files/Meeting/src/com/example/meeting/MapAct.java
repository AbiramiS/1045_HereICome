package com.example.meeting;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;


public class MapAct extends Activity {
	
	double s,s1,lat,lon;
	LocationManager lm;
	LocationListener ll;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        /*MyLocation1 myl=new MyLocation1();
        lat=myl.lat;
        lon=myl.lon;*/
       
        //Test t=new Test();
        lat=getIntent().getExtras().getDouble("lat");
        lon=getIntent().getExtras().getDouble("lon");
        s=getIntent().getExtras().getDouble("latd");
		s1=getIntent().getExtras().getDouble("lond");
        Intent i=new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://maps.google.com/maps?saddr="+lat+","+lon+"&daddr="+s+","+s1));
		startActivity(i);
      
        
    }


    
}
