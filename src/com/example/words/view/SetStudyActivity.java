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
		setTitle("����ѧϰ�ƻ�");
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
					title = "����";
					intentToShowStudy(title, 0);
					break;
				case 1:
					title = "���н̲�����";
					intentToShowStudy(title, 1);
					break;
				case 2:
					title = "����";
					intentToShowStudy(title, 2);
					break;
				case 3:
					title = "���н̲�����";
					intentToShowStudy(title, 3);
					break;
				case 4:
					title = "��ѧ";
					intentToShowStudy(title, 4);
					break;
				case 5:
					title = "רҵӢ��";
					intentToShowStudy(title, 5);
					break;
				case 6:
					title = "����Ӣ��";
					intentToShowStudy(title, 6);
					break;
				case 7:
					title = "�¸���Ӣ��";
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
