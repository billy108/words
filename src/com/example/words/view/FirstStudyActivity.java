package com.example.words.view;

import com.example.words.controller.SingleFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;

public class FirstStudyActivity extends SingleFragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
	}

	@Override
	public Fragment creatFragment() {
		return new FirstStudyFragment();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
