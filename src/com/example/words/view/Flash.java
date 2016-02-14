package com.example.words.view;

import com.example.words.R;
import com.example.words.model.WordService;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Flash extends Activity {
	private SharedPreferences preferences;
	private Intent intent;
	private WordService service;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.flash);
		
		service = new WordService(this);
		preferences = service.sharedPreferences;
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				if (preferences.getString("userName", "no").equals("no")) {
					intent = new Intent(Flash.this, MainActivity.class);
				}else{

					intent = new Intent(Flash.this,FirstActivity.class);
				}
				
				startActivity(intent);
				finish();
			}
		}, 2000);
	}
}
