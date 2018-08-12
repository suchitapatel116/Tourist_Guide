package com.hello.mapsproj;

import java.io.File;
import java.io.FileOutputStream;
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

public class NewNote extends Activity 
{
	Button btnsave,btnback;
	EditText edtext,edtitle;
	String notedata="",title="";	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_note);
		
		btnsave=(Button)findViewById(R.id.btnsave);
		btnback=(Button)findViewById(R.id.btnback);
		edtext=(EditText)findViewById(R.id.editText1);
		edtitle=(EditText)findViewById(R.id.edtitle);
		
		btnsave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				//set the folder
				File path=new File(Environment.getExternalStorageDirectory()+"/NotesData");
				if(!path.exists())
				{
					path.mkdir();
				}

				//get the title in title
				title=edtitle.getText().toString();
				if(title.equals(""))
				{
					DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
					String date = df.format(Calendar.getInstance().getTime());
					title=date;
				}
				else
				{
					title=edtitle.getText().toString();
				}
				
				//get the data
				notedata=edtext.getText().toString();	
				if(!notedata.equals(""))
				{
					try
					{
						File newfile=new File(path,title+".txt");
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
				//finish();
			}
		});
		
		btnback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent ii=new Intent(getApplicationContext(),MyNote.class);
				startActivity(ii);
				//finish();
			}
		});
		
	}
}
