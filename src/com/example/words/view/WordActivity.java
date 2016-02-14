package com.example.words.view;

import com.example.words.controller.SingleFragmentActivity;
import com.example.words.controller.WordFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;

public class WordActivity extends SingleFragmentActivity {
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setTitle("ѧϰ");
	}
	
	@Override
	public Fragment creatFragment() {
		return new WordFragment();
	}

}
