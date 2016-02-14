package com.example.words.view;

import java.util.ArrayList;
import java.util.List;

import com.example.words.R;
import com.example.words.model.DBOpenHelper;
import com.example.words.model.ImportWord;
import com.example.words.model.Word;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShowStudyActivity extends Activity {
	private TextView first_item;
	private ListView item_lv;
	private Intent intent;
	private String item_title;
	private int item;
	
	private List<Word> wordList;
	
	private Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("设置学习计划");
		setContentView(R.layout.firstitem);
		init();
	}

	private void init() {
		first_item = (TextView) findViewById(R.id.first_tv);
		item_lv = (ListView) findViewById(R.id.firstitem);
		
		intent = getIntent();
		item_title = intent.getStringExtra("title");
		item = intent.getIntExtra("item", 0);
		first_item.setText(item_title);
		
		setShowStudyAdapter(item);
		
		item_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					Intent intent = new Intent(ShowStudyActivity.this, ArrangeStudyActivity.class);
					intent.putExtra("num", cursor.getCount());
					startActivity(intent);
					break;
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				case 5:
					
					break;
				case 6:
					
					break;
				}
			}
		});
	}
	
	private void setShowStudyAdapter(int item) {
		if (item == 0) {
			SQLiteOpenHelper dbOpenHelper = new ImportWord(this, ImportWord.DB_WORD_NAME, null, DBOpenHelper.VERSION);
			SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
			insertWordTable(database, wordList);
			
			cursor = database.query(ImportWord.TABLE_WORD_1,
					null, null , null, null, null, null);
			
			String[] items = {"中考单词\n" + cursor.getCount() + "个"};
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(ShowStudyActivity.this, 
					android.R.layout.simple_list_item_1, items);
			item_lv.setAdapter(adapter);
		}
	}
	
	private  List<Word> wordDB() {
		wordList = new ArrayList<Word>();
		
		String[] wordName = new String[]{
				"в связи с этим",
		};
		String[] wordContent = new String[]{
				"因而，因此",
		};
		
		for (int i = 0; i < wordName.length; i++) {
			
			int id = 0;
			String name = wordName[i];
			String content = wordContent[i];
			int proficiency = 0;
			
			Word word = new Word(id, name, content, proficiency);
			wordList.add(word);
		}
		return wordList;
	}
	
	public void insertWordTable(SQLiteDatabase db, List<Word> wordList) { 
		ContentValues values = new ContentValues();
		wordList = wordDB();
		
		for (int i = 0; i < wordList.size(); i++) {
			Cursor cursor = db.query(ImportWord.TABLE_WORD_1, 
					null, 
					ImportWord.WORD_NAME + "=?" , 
					new String[]{wordList.get(i).getName()}, 
					null, null, null);
			
			if (cursor.getCount() == 0) {
				values.putNull(ImportWord.WORD_ID);
				values.put(ImportWord.WORD_NAME, wordList.get(i).getName());
				values.put(ImportWord.WORD_CONTENT, wordList.get(i).getContent());
				values.put(ImportWord.WORD_PROFICIENCY, 0);
				
				db.insert(ImportWord.TABLE_WORD_1, null, values);
			}
		}
	}
}
