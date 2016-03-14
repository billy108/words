package com.example.words.view;

import com.example.words.R;
import com.example.words.model.WordService;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MeFragment extends Fragment {
	private boolean flag;
	
	private TextView mMeLoginTextView;
	
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	private WordService service;
	private Intent intent;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_me, container, false);
		
		initView(v);
		clickIcon();
		
		return v;
	}
	
	private void clickIcon() {
		if (!preferences.getString("userName", "11").equals("11")) {
			mMeLoginTextView.setText(preferences.getString("userName", "11") + "\n点击注销");
			flag = true;
		}
	}

	private void initView(View v){
		mMeLoginTextView = (TextView) v.findViewById(R.id.me_login_textView);
		mMeLoginTextView.setOnClickListener(listener);
		
		service = new WordService(getActivity());
		preferences = service.sharedPreferences;
		editor = service.editor;
	}
	
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.me_login_textView:
				if (flag) {
					editor.putString("userName", "no");
					editor.commit();
				}
				
					intent = new Intent(getActivity(), LogonActivity.class);
					startActivity(intent);
				
				break;
			}
		}
	};
}
