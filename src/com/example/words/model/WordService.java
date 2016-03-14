package com.example.words.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
	
	public String getNet(String path) {
    	String data = "";
		try {
			URL url = new URL(path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			
			if (connection.getResponseCode() == connection.HTTP_OK) {
				InputStream is = connection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line = "";
				
				while ((line = br.readLine()) != null) {
					data += line;
				}
			}
			
			connection.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
}
