package com.example.words.view;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.example.words.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FirstViewPagerActivity extends FragmentActivity implements OnClickListener{
	private ViewPager mViewPager;
	private TextView mFirstButton, mPhraseButton, mDiscoveryButton, mMeButton;
	private ImageView mageviewOvertab;
	private View v;
	
	List<Fragment> fragmentList;
	
	private FirstFragment firstFragment;
	private PhraseFragment phraseFragment;
	private DiscoveryFragment discoveryFragment;
	private MeFragment meFragment;
	
	int currenttab = -1;
	int screenWidth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first_viewpager);
		
		screenWidth = getResources().getDisplayMetrics().widthPixels;
		
		initButton();
		initFragment();
		initViewPager();
	}

	private void initFragment() {
		fragmentList = new ArrayList<Fragment>();
		
		firstFragment = new FirstFragment();
		phraseFragment = new PhraseFragment();
		discoveryFragment = new DiscoveryFragment();
		meFragment = new MeFragment();
		
		fragmentList.add(firstFragment);
		fragmentList.add(phraseFragment);
		fragmentList.add(discoveryFragment);
		fragmentList.add(meFragment);
	}

	private void initViewPager() {
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setAdapter(new MyFragmentViewPagerAdapter(getSupportFragmentManager()));
	}

	private void initButton() {
		mFirstButton = (TextView) findViewById(R.id.wordsButton);
		mFirstButton.setOnClickListener(this);
		
		mPhraseButton = (TextView) findViewById(R.id.phraseButton);
		mPhraseButton.setOnClickListener(this);
		mPhraseButton.measure(0, 0);
		
		mageviewOvertab = (ImageView) findViewById(R.id.imgv_overtab);
        RelativeLayout.LayoutParams imageParams=new RelativeLayout.LayoutParams(screenWidth/4, mPhraseButton.getMeasuredHeight()/4);  
        imageParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);  
        mageviewOvertab.setLayoutParams(imageParams);
		
		mDiscoveryButton = (TextView) findViewById(R.id.discoveryButton);
		mDiscoveryButton.setOnClickListener(this);
		
		mMeButton = (TextView) findViewById(R.id.meButton);
		mMeButton.setOnClickListener(this);
	}
	
	class MyFragmentViewPagerAdapter extends FragmentPagerAdapter{

		public MyFragmentViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return fragmentList.get(position);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}
		
		@Override
		public void finishUpdate(ViewGroup container) {
			super.finishUpdate(container);
			int currentItem=mViewPager.getCurrentItem();  
            if (currentItem==currenttab)  
            {  
                return ;  
            }  
            imageMove(mViewPager.getCurrentItem());  
            currenttab=mViewPager.getCurrentItem();
		}
		
	}
	
	private void imageMove(int moveToTab)  
    {  
        int startPosition=0;  
        int movetoPosition=0;  
          
        startPosition=currenttab*(screenWidth/4);  
        movetoPosition=moveToTab*(screenWidth/4);  
        //Æ½ÒÆ¶¯»­  
        TranslateAnimation translateAnimation=new TranslateAnimation(startPosition,movetoPosition, 0, 0);  
        translateAnimation.setFillAfter(true);  
        translateAnimation.setDuration(100);  
        mageviewOvertab.startAnimation(translateAnimation);  
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.wordsButton:
			Toast.makeText(getApplication(), "1111", 0).show();
			changeTab(0);
			break;
		case R.id.phraseButton:
			Toast.makeText(getApplication(), "1111", 0).show();
			changeTab(1);
			break;
		case R.id.discoveryButton:
			changeTab(2);
			break;
		case R.id.meButton:
			changeTab(3);
			break;
		}
	}  

	private void changeTab(int tab) {
		mViewPager.setCurrentItem(tab);
		Toast.makeText(this, tab + "", 0).show();
	}
}
