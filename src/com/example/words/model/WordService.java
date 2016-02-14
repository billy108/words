package com.example.words.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class WordService {
	public SQLiteOpenHelper dbOpenHelper;
	public SQLiteDatabase database;
	public SharedPreferences sharedPreferences;
	public SharedPreferences.Editor editor;

	
	public WordService(Context context){
		sharedPreferences = context.getSharedPreferences("words", context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
	}
	
	public WordService(Context context, String dbName) {
		this(context);
		dbOpenHelper = new DBOpenHelper(context, dbName, null, DBOpenHelper.VERSION);
		database = dbOpenHelper.getWritableDatabase();
	}



	public ListAdapter getStudyAdapter(Activity view) {
		String[] item = {"初中","初中教材配套","高中","高中教材配套","大学","专业英语","出国考试","新概论英语"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(view, android.R.layout.simple_expandable_list_item_1, item);
		
		return adapter;
	}

	public Cursor queryWords(String where, String whereVale) {
		Cursor cursor = database.query(ImportWord.TABLE_WORD_1, null, 
				where + whereVale, 
				null, null, null, null);
		
		return cursor;
	};
	
	public void setUserSharedPreferences(Context v, String userName, String userPassWord) {
		editor.putString("userName", userName);
		editor.putString("userPassWord", userPassWord);
		editor.commit();
	}

}
