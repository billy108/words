package com.example.words.view;

import com.example.words.R;
import com.example.words.model.ImportWord;
import com.example.words.model.WordService;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FirstFragment extends Fragment {
	private Button mbeginStudyButton, mConsolidateButton;
	private TextView mRestOfDay, mWordsNumPerDay;
	private TextView mNewWordNumTextView, mFamiliarwordTextView, mUnLearnWordTextView, mAdjustPlanTextView;
	private ViewPager mViewPager;
	
	private Intent i;
	
	private int unLearnedNum, familiarNum, newNum;
	private int perWord;
	private int wordsNumber;
	
	private WordService service;
	
	private SQLiteDatabase database;
	private SQLiteOpenHelper openHelper;
	private SharedPreferences preferences;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_first, container, false);
		
		initDB();
		init(v);
		
		return v;
	}
	
	private void initDB() {
		service = new WordService(getActivity(), ImportWord.DB_WORD_NAME);
		preferences = service.sharedPreferences;
		
		database = service.database;
		openHelper = service.dbOpenHelper;
		
		unLearnedNum = service.queryWords(ImportWord.WORD_PROFICIENCY, " =0").getCount();
		familiarNum = service.queryWords(ImportWord.WORD_PROFICIENCY, " >5").getCount();
		newNum = service.queryWords(ImportWord.WORD_PROFICIENCY, " between 1 and 4").getCount();
	}

	private void init(View v) {
		i = getActivity().getIntent();
//		wordsNumber = i.getIntExtra("wordsNumber", 0);
		
		mNewWordNumTextView = (TextView) v.findViewById(R.id.newWordNumTextView);
		mNewWordNumTextView.setText("Éú´Ê" + newNum);
		
		mFamiliarwordTextView = (TextView) v.findViewById(R.id.familiarwordTextView);
		mFamiliarwordTextView.setText("Êì´Ê" + familiarNum);
		
		mUnLearnWordTextView = (TextView) v.findViewById(R.id.toLearnWordTextView);
		mUnLearnWordTextView.setText("Î´Ñ§" + unLearnedNum);
		
		
		mRestOfDay = (TextView) v.findViewById(R.id.restOfDayTextView);
		mRestOfDay.setText(preferences.getInt("days", 0) + "");
		
		mWordsNumPerDay = (TextView) v.findViewById(R.id.wordsNumPerDay);
		mWordsNumPerDay.setText(preferences.getInt("perWord", 0) + "");
		
		mAdjustPlanTextView = (TextView) v.findViewById(R.id.adjustPlanTextView);
		mAdjustPlanTextView.setOnClickListener(listener);
		
		mbeginStudyButton = (Button) v.findViewById(R.id.beginStudyButton);
		mbeginStudyButton.setOnClickListener(listener);
		
		mConsolidateButton = (Button) v.findViewById(R.id.ConsolidateWordButton);
		mConsolidateButton.setOnClickListener(listener);
		
	}

	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.adjustPlanTextView:
				i = new Intent(getActivity(), ShowStudyActivity.class);
				startActivity(i);
				break;
			case R.id.beginStudyButton:
				i = new Intent(getActivity(), WordActivity.class);
				i.putExtra("perWord", perWord);
//				i.putExtra("wordsNumber", wordsNumber);
				startActivity(i);
				break;
			case R.id.ConsolidateWordButton:
				i = new Intent(getActivity(), ConsolidateWordActivity.class);
				startActivity(i);
				break;
			}
		}
	};
}
