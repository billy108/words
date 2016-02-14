package com.example.words.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
//创建数据库及表
public class DBOpenHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "words.db";
	public static final int VERSION = 1;
	
	public static final String USER_TABLE_NAME = "user";
	public static final String USER_ID = "_id";
	public static final String USER_EMAIL = "email";
	public static final String USER_PASSWORD = "password";
	
	

	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createUserTable(db);//创建用户的数据表
	}

	private void createUserTable(SQLiteDatabase db) {
		String sqlStr = "CREATE TABLE " + USER_TABLE_NAME + "(" +
				USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				USER_EMAIL + " CHAR(20)," +
				USER_PASSWORD + " CHAR(20)" +
				")";
		
		db.execSQL(sqlStr);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	public void insertUserDB(SQLiteDatabase db, User user){
		ContentValues values = new ContentValues();
		
		values.putNull(USER_ID);
		values.put(USER_EMAIL, user.getUser_name());
		values.put(USER_PASSWORD, user.getUser_password());
		
		db.insert(USER_TABLE_NAME, null, values);
	}

}
