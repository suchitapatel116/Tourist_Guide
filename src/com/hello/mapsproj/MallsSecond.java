package com.hello.mapsproj;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MallsSecond extends Activity
{
	GoogleMap mMap;
	LatLng latLng;
	MarkerOptions markerOptions;
	
	String index;
	TextView tv1,tvaddr;
	String myadd[];
	Address add;
	ImageView imgv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.malls_second);
		
		tv1=(TextView)findViewById(R.id.tvtitle);
		tvaddr=(TextView)findViewById(R.id.tvaddr);
		imgv=(ImageView)findViewById(R.id.imageView1);
		
		Intent ii=getIntent();
		index=ii.getStringExtra("mallName");
		tv1.setText(index);
		
		int na=getResources().getIdentifier(ii.getStringExtra("imgNa").toString(), "drawable",getPackageName());
		imgv.setBackgroundResource(na);
		imgv.setScaleType(ScaleType.FIT_XY);
				
		if(mMap==null)
	    {
	    	mMap=((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();	    	
	    }
        if(mMap!=null)
        {
        	mMap.getUiSettings().setZoomGesturesEnabled(true);
        	mMap.getUiSettings().setZoomControlsEnabled(true);
        	mMap.setMyLocationEnabled(true);
        }

        
        
        
        try {
			geoLocate();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	public void geoLocate() throws IOException
    {
    	String location=tv1.getText().toString();
    	
    	location+=" vadodara";
    	
    	Geocoder gc=new Geocoder(this,Locale.ENGLISH);
    	List<Address> list=gc.getFromLocationName(location, 10);
    	add=list.get(0);
    	//String locality=add.getLocality();
    
		//get addr
    	int no=0;
		myadd=new String[add.getMaxAddressLineIndex()];
		for(int i=0; i<add.getMaxAddressLineIndex();i++)
		{
			myadd[i]=add.getAddressLine(i);
			no=add.getMaxAddressLineIndex();
		}
		if(no<=2)
			tvaddr.setText(myadd[0]+", "+myadd[1]);	//"+", "+add.getLocality());
		else
			tvaddr.setText(myadd[0]+", "+myadd[1]+", "+myadd[2]);		//+", "+add.getLocality());
		
    	gotoLocation(add.getLatitude(), add.getLongitude(), 15);
    }
	
	private void gotoLocation(double lat, double lng, float zoom)
    {
    	LatLng ll=new LatLng(lat, lng);
    	CameraUpdate update=CameraUpdateFactory.newLatLngZoom(ll,zoom);
    	mMap.moveCamera(update);
    	markerOptions = new MarkerOptions();
		markerOptions.position(ll);						
		mMap.addMarker(markerOptions);
    }
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.maps_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
        {
        case R.id.mapTypeNormal:
        	mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        	break;
        case R.id.mapTypeSatellite:
        	mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        	break;
        case R.id.mapTypeTerrain:
        	mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        	break;
        case R.id.mapTypeHybrid:
        	mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        	break;
        case R.id.mapTypeNone:
        	mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        	break;
        case R.id.gotoLoc:
        	gotoLocation(add.getLatitude(), add.getLongitude(), 15);
        	break;
        default:
        	break;
        }
        return super.onOptionsItemSelected(item);
	}
}
