package com.example.words.model;
//本类是用于创建单词数据库、建表和存储单词数据。方法比较耗时，后续可再改进
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.words.view.ShowStudyActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ImportWord extends SQLiteOpenHelper{
	public static final String DB_WORD_NAME = "db_word.db";
	public static final String TABLE_WORD_1 = "word_1";
	public static final String WORD_ID = "_id";
	public static final String WORD_NAME = "name";
	public static final String WORD_CONTENT = "content";
	public static final String WORD_PROFICIENCY = "proficiency";
	public static final int VERSON= 1;
	
	private List<Word> wordList;
	
	public ImportWord(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
//		createWordTable(db);
	}
	
	private void createWordTable(SQLiteDatabase db) {
		String sqlStr = "CREATE TABLE " + TABLE_WORD_1 + "(" +
				WORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				WORD_NAME + " CHAR(20)," +
				WORD_CONTENT + " CHAR(20)," +
				WORD_PROFICIENCY + " INT(1)" +
				")";
		
		db.execSQL(sqlStr);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}
