package com.hello.mapsproj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Malls extends Activity
{
	//TextView tvt;
	ListView lv;
	String[] mallist={"Big Bazaar","Center Square","Inorbit","Vadodara Central","7 Seas","Pantaloons"};
	String[] listml={"big_bazar","center_square","inorbit","vadodara_central","mall7_seas","pantaloons"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.malls);

		lv=(ListView)findViewById(R.id.listView1);
		
		ArrayAdapter<String> adaptr=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line ,mallist);
		lv.setAdapter(adaptr);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent ii=new Intent(getApplicationContext(),MallsSecond.class);
				String selectedFromList = (lv.getItemAtPosition(position)).toString();
				
				//tvt=(TextView)findViewById(R.id.tvmalls);
				//tvt.setText(selectedFromList);
				ii.putExtra("imgNa", listml[position]);
				ii.putExtra("mallName", selectedFromList);
				startActivity(ii);
			}
		});
		
	}
}
