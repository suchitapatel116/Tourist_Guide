package com.hello.mapsproj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Cinemas extends Activity
{
	ListView lv;
	String[] cinlist={"Inox","Cinemarc","Chandan","PVR","Mukta"};
	String[] listcin={"inox","cinmarc","chandan","pvr2","mukta_a2"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cinemas);
		
		lv=(ListView)findViewById(R.id.listView1);
		
		ArrayAdapter<String> adaptr=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line ,cinlist);
		lv.setAdapter(adaptr);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent ii=new Intent(getApplicationContext(),WebLinkPage.class);
				String selectedFromList = (lv.getItemAtPosition(position)).toString();
				ii.putExtra("imgNa", listcin[position]);
				ii.putExtra("file", "cinema");
				ii.putExtra("mallName", selectedFromList);
				startActivity(ii);
			}
		});

	}
}
