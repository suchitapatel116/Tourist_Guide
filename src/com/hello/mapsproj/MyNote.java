package com.hello.mapsproj;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MyNote extends Activity 
{
	Button btnadd;
	ListView lv;
	ArrayAdapter<String> adapt;
	//TextView tvtop;
	File path;
	File[] dirFiles;
	String filename[];
	String filepath[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note);
		
		btnadd=(Button)findViewById(R.id.addnotebutton);
		lv=(ListView)findViewById(R.id.listview1);

		//register contextual menu
		registerForContextMenu(lv);
		
	/*
		File path=new File(Environment.getExternalStorageDirectory()+"/NotesData");
		// lists all the files into an array 
		File[] dirFiles = path.listFiles();
		String filename[]=new String[dirFiles.length]; 
		String filepath[]=new String[dirFiles.length];
*/
		path=new File(Environment.getExternalStorageDirectory()+"/NotesData");
		// lists all the files into an array 
		dirFiles = path.listFiles();
		filename=new String[dirFiles.length]; 
		filepath=new String[dirFiles.length];

		if (dirFiles.length != 0) 
		{
		    for (int ii = 0; ii < dirFiles.length; ii++) 
		    {
		    	//will copy the full path
		    	filepath[ii]=dirFiles[ii].toString();
		    	
		        filename[ii]=dirFiles[ii].getName().toString();
		    } 
		} 
		//#FFF9C8-backgnd color of listview
		//ArrayAdapter<String> 
		adapt=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,filename);			
		lv.setAdapter(adapt);
		
		
		
		btnadd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent inew=new Intent(getApplicationContext(),NewNote.class);
				startActivity(inew);
				finish();
			}
		});
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String selectedFromList = (lv.getItemAtPosition(position)).toString();
				
				Intent iedit=new Intent(getApplicationContext(),ShowNote.class);
				iedit.putExtra("notetitle", selectedFromList);
				startActivity(iedit);
				finish();
			}
		});
		

	}
	
	
	
	@Override
	public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.contextual_menu, menu);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) 
	{
		AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

		switch (item.getItemId()) {
		case R.id.id_del:
			
			File file=new File(filepath[info.position]);
			file.delete();
			//arraylist.remove(info.position);
			adapt.notifyDataSetChanged();
			Intent iii=new Intent(MyNote.this,MyNote.class);
			finish();
			startActivity(iii);
			break;
			
		case R.id.id_edit:
			
			String selectedFromList = filename[info.position];	//(lv.getItemAtPosition(position)).toString();
			
			Intent iedit=new Intent(getApplicationContext(),EditNote.class);
			iedit.putExtra("notetitle", selectedFromList);
			startActivity(iedit);
			finish();
			adapt.notifyDataSetChanged();	
			break;

		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	
}
