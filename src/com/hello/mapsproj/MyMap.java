package com.hello.mapsproj;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
//import com.example.mymaps.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMap extends FragmentActivity
{
	GoogleMap mMap;
	LatLng latLng;
	MarkerOptions markerOptions;
	
	Button bgo;
	EditText et;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mymap);
		
		bgo=(Button)findViewById(R.id.btnGo);
        et=(EditText)findViewById(R.id.etLoc);
		
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

        bgo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					geoLocate(v);
				}
				catch(Exception e){}
			}
		});      
        
        mMap.setOnMapClickListener(new OnMapClickListener() 
	    {
			@Override
			public void onMapClick(LatLng arg0) 
			{
				latLng = arg0;
				mMap.clear();
				mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));				
				markerOptions = new MarkerOptions();
				markerOptions.position(latLng);						
				
				// Placing a marker on the touched position
				//googlemap.addMarker(markerOptions);
		    	new ReverseGeocodingTask(getBaseContext()).execute(latLng);		    				
			}
		});

	}
	
	
    private void gotoLocation(double lat, double lng, float zoom)
    {
    	LatLng ll=new LatLng(lat, lng);
    	CameraUpdate update=CameraUpdateFactory.newLatLngZoom(ll,zoom);
    	mMap.moveCamera(update);
    }

    public void geoLocate(View v) throws IOException
    {
    	hideSoftKeyboard(v);
    	String location=et.getText().toString();
    	
    	Geocoder gc=new Geocoder(this);
    	List<Address> list=gc.getFromLocationName(location, 1);
    	Address add=list.get(0);
    	String locality=add.getLocality();
    	if(locality==null)
    		//
    	
    	Toast.makeText(this,locality,Toast.LENGTH_SHORT).show();
    	gotoLocation(add.getLatitude(), add.getLongitude(), 10);
    }
    private void hideSoftKeyboard(View v)
    {
    	InputMethodManager imm=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
    	imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
    
    private class ReverseGeocodingTask extends AsyncTask<LatLng, Void, String>
    {
		Context mContext;
		
		public ReverseGeocodingTask(Context context){
			super();
			mContext = context;
		}

		@Override
		protected String doInBackground(LatLng... params) {
			Geocoder geocoder = new Geocoder(mContext);
			double latitude = params[0].latitude;
			double longitude = params[0].longitude;
			
			List<Address> addresses = null;
			String addressText="";
			
			try {
				addresses = geocoder.getFromLocation(latitude, longitude,1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(addresses != null && addresses.size() > 0 ){
				Address address = addresses.get(0);
				
				addressText = String.format("%s, %s, %s",
	                    address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
	                    address.getLocality(),	                    
	                    address.getCountryName());				
			}
			return addressText;
		}		
		
		@Override
		protected void onPostExecute(String addressText) {
			markerOptions.title(addressText);
			mMap.addMarker(markerOptions);
		}
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
        default:
        	break;
        }
        return super.onOptionsItemSelected(item);
	}
	
	
}
