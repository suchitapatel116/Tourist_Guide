package com.hello.mapsproj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

public class EditNote extends Activity 
{
	Button btnsave,btnback;
	EditText edtext,edtitle;
	String notedata="",title="";	
	TextView tvtit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_note);
		
		btnsave=(Button)findViewById(R.id.btnsave);
		btnback=(Button)findViewById(R.id.btnback);
		edtext=(EditText)findViewById(R.id.editText1);
		edtitle=(EditText)findViewById(R.id.edtitle);
		tvtit=(TextView)findViewById(R.id.tvtitle);
		
		edtitle.setVisibility(View.INVISIBLE);
		
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
				edtext.setText(aBuffer);
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
		
		btnsave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				//set the folder
				File path=new File(Environment.getExternalStorageDirectory()+"/NotesData");
				if(!path.exists())
				{
					path.mkdir();
				}
				
				//get the data
				notedata=edtext.getText().toString();	
				if(!notedata.equals(""))
				{
					try
					{
						File newfile=new File(path,title);
						FileOutputStream fos=new FileOutputStream(newfile);
						OutputStreamWriter outWriter=new OutputStreamWriter(fos);
						
						outWriter.write(notedata);
						
						outWriter.close();
						fos.close();	
					}
					catch(Exception e)
					{
						Log.d("ERROR MSG: ",e.getMessage());
					}
				}
				Intent ii=new Intent(getApplicationContext(),MyNote.class);
				startActivity(ii);
				finish();
			}
		});
		
		btnback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent ii=new Intent(getApplicationContext(),MyNote.class);
				startActivity(ii);
				finish();
			}
		});
		
	}
}
