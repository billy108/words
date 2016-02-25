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
import android.widget.Button;
import android.widget.TextView;

public class FirstStudyFragment extends Fragment {
	private Button mToFirstButton;
	private TextView mFirstTextView1, mFirstTextView2;
	private TextView mToSetStudyButton;
	private Button mToArrangStudyTextView;
	
	private SharedPreferences.Editor editor;
	private WordService service;
	
	private int days;
	private int wordsNumber;
	private int perWord ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_firststudy, container, false);
		
		service = new WordService(getActivity());
		editor = service.editor;
		
		Intent intent = getActivity().getIntent();
		
		int flag = intent.getIntExtra("flag", 0);
		days = intent.getIntExtra("days", 0);
		perWord = intent.getIntExtra("perWord", 0);
		wordsNumber = intent.getIntExtra("wordsNumber", 0);
		
		mToFirstButton = (Button) v.findViewById(R.id.first_btn_ok);
		mToFirstButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), FirstViewPagerActivity.class);
				editor.putInt("perWord", perWord);
				editor.putInt("days", days);
				editor.commit();
				
//				i.putExtra("perWord", perWord);
//				i.putExtra("days", days);
//				i.putExtra("wordsNumber", wordsNumber);
				
				startActivity(i);
			}
		});
		
		mFirstTextView1 = (TextView) v.findViewById(R.id.first_tv2);
		mFirstTextView1.setText(wordsNumber + "\n新词总量");
		mFirstTextView2 = (TextView) v.findViewById(R.id.first_tv3);
		
		if (flag == 1) {
			mFirstTextView2.setText(perWord + "\n每日学习新词量");
			days = wordsNumber / perWord;
		}
		if (flag == 2) {
			mFirstTextView2.setText(days + "\n需要学习天数");
		}
		
		mToArrangStudyTextView = (Button) v.findViewById(R.id.first_changeMethod_btn);
		mToArrangStudyTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), ArrangeStudyActivity.class);
				i.putExtra("num", wordsNumber);
				startActivity(i);
			}
		});
		
		mToSetStudyButton = (TextView) v.findViewById(R.id.first_changeStudy_btn);
		mToSetStudyButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), SetStudyActivity.class);
				startActivity(i);
			}
		});
		
		return v;
	}
}
