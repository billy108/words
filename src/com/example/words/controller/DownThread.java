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
import android.widget.Toast;

public class DownThread implements Runnable {
	private Context context;
	
	public DownThread(Context context) {
		super();
		this.context = context;
	}

	@Override
	public void run() {
		try {
			URL url = new URL("http://localhost:8080/webapps/db_word.db");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			InputStream is = connection.getInputStream();
			
			int count = is.read();
			Toast.makeText(context, count + "", 0).show();
			
			int line;
			OutputStream os = null;
			
			File file = new File("date/date/com.example.words/datebase/db_word.db");
			if (file.exists()) {
				Toast.makeText(context, "单词库已存在！无须再下载！", Toast.LENGTH_SHORT).show();
				return;
			}else{
				byte[] bs = new byte[1024];
				os = new FileOutputStream("date/date/com.example.words/datebase/db_word.db");
				while ((line = is.read(bs)) != -1) {
					os.write(line);
				}
			}
			
			os.close();
			is.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
