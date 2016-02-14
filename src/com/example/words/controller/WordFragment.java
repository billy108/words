package com.example.words.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.example.words.R;
import com.example.words.model.ImportWord;
import com.example.words.model.Word;
import com.example.words.model.WordService;
import com.example.words.view.FirstActivity;

import android.R.integer;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WordFragment extends Fragment {
	private static final String KNOWNWORD = "knownword";
	private static final String UNKNOWNWORD = "unknownword";
	private static final String CREATENEWWORD = "createnewword";
	private static final String REVIEWWORD = "review";
	
	private Button mKnownButton, mUnknownButton;
	private TextView mWordNameTextView, mWordContentTextView;
	
	private SQLiteDatabase database;
	private SQLiteOpenHelper openHelper;
	private Cursor cursor;
	
	private WordService service;
	private Intent intent;
	private List<Word> wordList;
	private List<Integer> wordIDList;
	private Word word;
	private View v;
	
	private int perWord = 5;
	private int wordsNumber = 140;
	private int count;
	int reviewCount = 0;
	private String flag = CREATENEWWORD;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_word, container, false);
		intent = getActivity().getIntent();
//		perWord = intent.getIntExtra("perWord", 10);
		wordIDList = new ArrayList<Integer>();
		wordList = new ArrayList<Word>();
		
		init(v);
		initDB();
		
		return v;
	}
	
	private void initDB() {
		service = new WordService(getActivity(), ImportWord.DB_WORD_NAME);
		database = service.database;
		openHelper = service.dbOpenHelper;
		
		wordsNumber = database.query(ImportWord.TABLE_WORD_1, null, null, null, null, null, null).getCount();
		showWord();
		
	}


	private void nextWord(String selectKnownButton, String flag1) {
		int randomWordID = 0;
		
		if (selectKnownButton.equals(KNOWNWORD)) {
			changeProficiency(word.getId());
			if (word.getProficiency() >= 3) {
				Toast.makeText(getActivity(), "升级为熟词！", 0).show();
			}else{
				Toast.makeText(getActivity(), "熟练值+1", 0).show();
			}
		}
		
		if (flag1 == CREATENEWWORD) {
			
			System.out.println(count);
			count++;
			
			while (count  < perWord) {
				randomWordID = new Random().nextInt(wordsNumber) + 1;
				
				if (!wordIDList.contains(randomWordID)) {
					wordIDList.add(randomWordID);
					cursor = service.queryWords(ImportWord.WORD_ID, "=" + randomWordID);
					cursor.moveToFirst();
					
					word = new Word(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getInt(3));
					wordList.add(word);
					
					mWordNameTextView.setText(cursor.getString(1));
					mWordContentTextView.setText("点击显示释义");
					break;
				}
			}
			
			if (count >= perWord) {
				new AlertDialog.Builder(getActivity())
				.setMessage("恭喜！\n每日单词量已完成！\n建议您继续巩固今日未熟练单词！")
				.setPositiveButton("好滴", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						flag = REVIEWWORD;
						
						word = wordList.get(0);
						mWordNameTextView.setText(word.getName());
						mWordContentTextView.setText("点击显示释义");
					}
				})
				.setNegativeButton("不啦", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent i = new Intent(getActivity(), FirstActivity.class);
						startActivity(i);
					}
				})
				.show();
			}
		} 
		if(flag1 == REVIEWWORD){
			reviewCount++;
			if (reviewCount == 5) {
				reviewCount = 0;
			}
			word = wordList.get(reviewCount);
			mWordNameTextView.setText(word.getName());
			mWordContentTextView.setText("点击显示释义");
		}
	}

	private void changeProficiency(int wordID) {
		ContentValues values = new ContentValues();
		
		Cursor cursor1 = service.queryWords(ImportWord.WORD_ID, "=" + wordID);
		cursor1.moveToFirst();
		
		values.put(ImportWord.WORD_PROFICIENCY, cursor1.getInt(3) + 1);
		database.update(ImportWord.TABLE_WORD_1, values, ImportWord.WORD_ID + "=" + wordID, null);
		
	}

	private void showWord() {
		int randomWordID = new Random().nextInt(wordsNumber) + 1;
//		System.out.println(randomWordID);
		cursor = service.queryWords(ImportWord.WORD_ID, "=" + randomWordID);
		cursor.moveToFirst();
		
		word = new Word(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getInt(3));
		wordList.add(word);
		
		mWordNameTextView.setText(cursor.getString(1));
		
		wordIDList.add(randomWordID);

	}

	private void init(View v) {
		mWordNameTextView = (TextView) v.findViewById(R.id.word_nameTextView);
		mWordContentTextView = (TextView) v.findViewById(R.id.word_contentTextView);
		mWordContentTextView.setOnClickListener(listener);
		
		mKnownButton = (Button)v.findViewById(R.id.word_knownButton);
		mKnownButton.setOnClickListener(listener);
		
		mUnknownButton = (Button)v.findViewById(R.id.word_unknownButton);
		mUnknownButton.setOnClickListener(listener);
	}

	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.word_knownButton:
				nextWord(KNOWNWORD, flag);
				break;
			case R.id.word_unknownButton:
				nextWord(UNKNOWNWORD, flag);
				break;
			case R.id.word_contentTextView:
				mWordContentTextView.setText(word.getContent());
				break;
			}
		}
	};
}
