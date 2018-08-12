package com.hello.mapsproj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Places extends Activity
{
	ListView lv;
	String[] placelist={"Laxmi Vilas Palace","Sayaji Baug","EME Temple","Nazarbaug Palace","Baroda Museum & Picture Gallery","Chhota Udaipur"};
	String[] listpl={"laxmi_vilas_palace","vadodara_sayaji_baug","eme_temple","nazarbaugh_palace","maharaja_fateh_singh_museum","chhota_udepur"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.places);
		
		lv=(ListView)findViewById(R.id.listView1);
		
		ArrayAdapter<String> adaptr=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line ,placelist);
		lv.setAdapter(adaptr);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent ii=new Intent(getApplicationContext(),MallsSecond.class);
				String selectedFromList = (lv.getItemAtPosition(position)).toString();
				ii.putExtra("imgNa", listpl[position]);
				ii.putExtra("mallName", selectedFromList);
				startActivity(ii);
			}
		});
		
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}