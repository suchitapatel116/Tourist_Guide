package com.hello.mapsproj;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity 
{
	Button bfood,bplace,bcin,bmall,bmap,bblack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bfood=(Button)findViewById(R.id.btnfood);
		bplace=(Button)findViewById(R.id.btnplace);
		bcin=(Button)findViewById(R.id.btncin);
		bmall=(Button)findViewById(R.id.btnmall);
		bmap=(Button)findViewById(R.id.btnmap);
		bblack=(Button)findViewById(R.id.btn);
		
		ObjectAnimator anim=ObjectAnimator.ofFloat(bfood, View.SCALE_X, 1.0f,0.0f,1.0f,0.0f,1.0f);	
		anim.setDuration(4000);
		anim.start();
		
		ObjectAnimator anim2=ObjectAnimator.ofFloat(bplace, View.SCALE_X, 1.0f,0.0f,1.0f,0.0f,1.0f);	
		anim2.setDuration(4000);
		anim2.setStartDelay(3500);
		anim2.start();
		
		ObjectAnimator anim3=ObjectAnimator.ofFloat(bcin, View.SCALE_X, 1.0f,0.0f,1.0f,0.0f,1.0f);	
		anim3.setDuration(4000);
		anim3.setStartDelay(3500);
		anim3.start();
		
		ObjectAnimator anim4=ObjectAnimator.ofFloat(bmall, View.SCALE_X, 1.0f,0.0f,1.0f,0.0f,1.0f);	
		anim4.setDuration(4000);
		anim4.start();
		
		ObjectAnimator anim5=ObjectAnimator.ofFloat(bmap, View.SCALE_X, 1.0f,0.0f,1.0f,0.0f,1.0f);	
		anim5.setDuration(4000);
		anim5.start();
		
		ObjectAnimator anim6=ObjectAnimator.ofFloat(bblack, View.SCALE_X, 1.0f,0.0f,1.0f,0.0f,1.0f);	
		anim6.setDuration(4000);
		anim6.setStartDelay(3500);
		anim6.start();
		
		
		bfood.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i1=new Intent(getApplicationContext(),Food.class);
				startActivity(i1);
			}
		});
		bplace.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i2=new Intent(getApplicationContext(),Places.class);
				startActivity(i2);
			}
		});
		bcin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i3=new Intent(getApplicationContext(),Cinemas.class);
				startActivity(i3);
			}
		});
		bmall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i4=new Intent(getApplicationContext(),Malls.class);
				startActivity(i4);
			}
		});
		bmap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i5=new Intent(getApplicationContext(),MyMap.class);
				startActivity(i5);
			}
		});
		bblack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i5=new Intent(getApplicationContext(),MyNote.class);
				startActivity(i5);
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
