package com.example.words.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.words.R;
import com.example.words.model.DBOpenHelper;
import com.example.words.model.WordService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SetStudyActivity extends Activity {
	
	
	private ListView setStudy;
	private WordService service;
	
	private ArrayList<HashMap<String, Object>> listItem;
	private String[] itemTV = {"����","���н̲�����","����","���н̲�����","��ѧ","רҵӢ��","��������","�¸���Ӣ��"};
	
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
		MyAdapter adapter = new MyAdapter(this);
		setStudy.setAdapter(adapter);
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
	
	private ArrayList<HashMap<String, Object>> getDate(){
		ArrayList<HashMap<String, Object>> item = new ArrayList<HashMap<String,Object>>();
		
		for (int i = 0; i < itemTV.length; i++) {
			HashMap<String , Object> map = new HashMap<String, Object>();
			map.put("ItemTitle", itemTV[i]);
			item.add(map);
		}
		return item;
	}

	protected void intentToShowStudy(final String title,int item) {
				Intent intent = new Intent(SetStudyActivity.this, ShowStudyActivity.class);
				intent.putExtra("title", title);
				intent.putExtra("item", item);
				startActivity(intent);
	}
	
	class MyAdapter extends BaseAdapter{
		private LayoutInflater miInflater;
		
		public MyAdapter(Context context){
			this.miInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return getDate().size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			final String titleItem;
			
			if (convertView == null) {
				convertView = miInflater.inflate(R.layout.setstudy_item, null);
				holder = new ViewHolder();
				
				holder.title = (TextView) convertView.findViewById(R.id.setStudy_tv);
				holder.bt = (Button) convertView.findViewById(R.id.setStudy_btn);
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			titleItem = getDate().get(position).get("ItemTitle").toString();
			holder.title.setText(titleItem);
			
			holder.bt.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(getApplication(), titleItem, 0).show();
				}
			});
			
			return convertView;
		}
		
	}
	
	public final class ViewHolder{
		public TextView title;
		public Button bt;
	}
	
}
