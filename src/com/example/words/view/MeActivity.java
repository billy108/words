package com.example.words.view;

import android.support.v4.app.Fragment;

import com.example.words.controller.SingleFragmentActivity;

public class MeActivity extends SingleFragmentActivity {

	@Override
	public Fragment creatFragment() {
		return new MeFragment();
	}

}
