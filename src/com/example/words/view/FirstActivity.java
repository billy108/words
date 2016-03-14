package com.example.words.view;

import java.util.Timer;
import java.util.TimerTask;

import com.example.words.controller.SingleFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.widget.Toast;

public class FirstActivity extends SingleFragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_fragment);
//		
//		FragmentManager fm = getSupportFragmentManager();
//		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
//		
//		if (fragment != null) {
//			fm.beginTransaction()
//				.add(R.id.fragmentContainer, fragment)
//				.commit();
//		}
	}

	@Override
	public Fragment creatFragment() {
		return new FirstFragment();
	}
}
