package com.example.words.view;

import android.support.v4.app.Fragment;

import com.example.words.controller.SingleFragmentActivity;

public class ConsolidateWordActivity extends SingleFragmentActivity {
	
	
	@Override
	public Fragment creatFragment() {
		return new ConsolidateWordFragment();
	}

}
