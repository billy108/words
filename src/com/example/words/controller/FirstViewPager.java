package com.example.words.controller;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

public class FirstViewPager extends ViewPager {
	View wordView, phraseView, discoveryView, meView;
	List<View> mListViews;
	
	public FirstViewPager(Context context, List<View> mListViews) {
		super(context);
		this.mListViews = mListViews;
	}
	
}
