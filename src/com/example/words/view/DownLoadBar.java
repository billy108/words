package com.example.words.view;

import com.example.words.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Message;

public class DownLoadBar extends Activity {
	private ProgressBar bar;
	private TextView tv;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download_bar);
		setTitle("下载进度");
		
		bar = (ProgressBar) findViewById(R.id.downLoad_bar);
		tv = (TextView) findViewById(R.id.downLoad_num);
	};
	
	public Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			for (int i = 1; i < 6; i++) {
				try {
					bar.setProgress(i * 20);
					tv.setText(i * 20 + "%");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			finish();
			Toast.makeText(DownLoadBar.this, "下载成功", 0).show();
			
		};
	};
	
}
