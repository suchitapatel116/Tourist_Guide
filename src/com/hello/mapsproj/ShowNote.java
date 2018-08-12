package com.hello.mapsproj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowNote extends Activity
{
	Button btnback,btnedit;
	String notedata="",title="";	
	TextView tvtit,tvshowdata;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_note);
		
		btnback=(Button)findViewById(R.id.btnback);
		btnedit=(Button)findViewById(R.id.btnedit);
		tvtit=(TextView)findViewById(R.id.tvtitle);
		tvshowdata=(TextView)findViewById(R.id.tvshow);
		
		Intent ii=getIntent();
		title=ii.getStringExtra("notetitle");
		
		File path=new File(Environment.getExternalStorageDirectory()+"/NotesData/"+title);
		if(path.exists())
		{
			tvtit.setText("Title: "+title);
			try 
			{
				FileInputStream fIn = new FileInputStream(path);
				BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
				
				String aDataRow = "";
				String aBuffer = "";
				while ((aDataRow = myReader.readLine()) != null) 
				{
					aBuffer += aDataRow + "\n";
				}
				//edtext.setText(aBuffer);
				tvshowdata.setText(aBuffer);
				myReader.close();
			} 
			catch (Exception e) 
			{
				Toast.makeText(getBaseContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
			}			
		}
		else
		{
			Toast.makeText(getApplicationContext(), "file not found", Toast.LENGTH_LONG).show();
		}
		
		btnback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent ii=new Intent(getApplicationContext(),MyNote.class);
				startActivity(ii);
				finish();
			}
		});
		
		btnedit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent iedit=new Intent(getApplicationContext(),EditNote.class);
				iedit.putExtra("notetitle", title);
				startActivity(iedit);
				finish();
			}
		});
		
	}
	


}
