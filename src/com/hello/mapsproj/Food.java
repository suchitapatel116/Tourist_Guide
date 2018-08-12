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
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Food extends Activity
{
	ListView lv1,lv2;
	String[] foodlist2={"Urban Spice","White Patato","Temptations","Tasty Vadapav","Mahakadi Sev Usad","Macdonalds","Dominoz"};
	String[] listrest={"urban_spice","white_potato","temptation","vadapav","usad","macdonald","dominos"};
	
	String[] hotelList={"Surya Place", "Oasis","Royal Orchids","Hotel Surya","The Gateway Hotel","Ginger Hotel" };
	String[] hotel={"suryapalace.com", "theoasishotel.net","royalorchidhotels.com","hotelsurya.com","thegatewayhotels.com","gingerhotels.com" };

	String[] listhtl={"surya_palace","oasis","royal_orchid","hotel_surya","gateway_hotel","ginger_vadodara"};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food);

		lv1=(ListView)findViewById(R.id.listView1);
		lv2=(ListView)findViewById(R.id.listView2);
		
		ArrayAdapter<String> adaptr=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line ,hotelList);
		lv1.setAdapter(adaptr);
		
		ArrayAdapter<String> adaptr2=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line ,foodlist2);
		lv2.setAdapter(adaptr2);
		
		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent ii=new Intent(getApplicationContext(),WebLinkPage.class);
				String selectedFromList = (lv1.getItemAtPosition(position)).toString();
				ii.putExtra("imgNa", listhtl[position]);
				ii.putExtra("file", hotel[position].toString());
				ii.putExtra("mallName", selectedFromList);
				startActivity(ii);
			}
		});
		
		lv2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent ii=new Intent(getApplicationContext(),MallsSecond.class);
				String selectedFromList = (lv2.getItemAtPosition(position)).toString();
				ii.putExtra("imgNa", listrest[position]);
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
