package com.example.words.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

public class DownThread implements Runnable {
	private Context context;
	Handler handler;
	
	public static final String PACKAGE_NAME = "com.example.words";
	public static final String DB_WORDS_NAME = "db_word.db";
	public static final String DB_PATH = "/data" +
						Environment.getDataDirectory().getAbsolutePath() + "/" + 
						PACKAGE_NAME;
	
	
	public DownThread(Context context, Handler handler) {
		super();
		this.context = context;
		this.handler = handler;
	}

	@Override
	public void run() {
		try {
			File newDBFile = new File(DownThread.DB_PATH + "/databases/" + DownThread.DB_WORDS_NAME);
				URL url = new URL("http://words.cooltester.com/static/source/word_1.db");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				InputStream is = connection.getInputStream();
				
				int line;
				byte[] bs = new byte[1024];
				OutputStream os = new FileOutputStream(newDBFile);
				
				while ((line = is.read(bs)) != -1) {
					os.write(bs, 0, line);
				}
				
//				handler.sendEmptyMessage(0);
				os.close();
				is.close();
				
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
