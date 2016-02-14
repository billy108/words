package com.example.words.view;

import com.example.words.R;
import com.example.words.model.DBOpenHelper;
import com.example.words.model.WordService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SetStudyActivity extends Activity {
	
	
	private ListView setStudy;
	private WordService service;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("设置学习计划");
		setContentView(R.layout.setstudy);
		service = new WordService(this, DBOpenHelper.DB_NAME);
		
		init();
	}

	private void init() {
		setStudy = (ListView) findViewById(R.id.setStudy);
		setStudy.setAdapter(service.getStudyAdapter(this));
		setStudy.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String title = "";
				switch (arg2) {
				case 0:
					title = "初中";
					intentToShowStudy(title, 0);
					break;
				case 1:
					title = "初中教材配套";
					intentToShowStudy(title, 1);
					break;
				case 2:
					title = "高中";
					intentToShowStudy(title, 2);
					break;
				case 3:
					title = "高中教材配套";
					intentToShowStudy(title, 3);
					break;
				case 4:
					title = "大学";
					intentToShowStudy(title, 4);
					break;
				case 5:
					title = "专业英语";
					intentToShowStudy(title, 5);
					break;
				case 6:
					title = "出国英语";
					intentToShowStudy(title, 6);
					break;
				case 7:
					title = "新概念英语";
					intentToShowStudy(title, 7);
					break;
				}
			}
		});
		
		
	}

	protected void intentToShowStudy(final String title,int item) {
				Intent intent = new Intent(SetStudyActivity.this, ShowStudyActivity.class);
				intent.putExtra("title", title);
				intent.putExtra("item", item);
				startActivity(intent);
	}

}
