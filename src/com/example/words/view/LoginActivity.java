package com.example.words.view;

import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.words.R;
import com.example.words.model.DBOpenHelper;
import com.example.words.model.User;
import com.example.words.model.WordService;

public class LoginActivity extends Activity {
	
	private TextView login_tv;
	private EditText login_name,login_password;
	private Button login_btn;
	
	private DBOpenHelper dbOpenHelper;
	private SQLiteDatabase database;
	
	private WordService service;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		setTitle("ע���˺�");
		getActionBar().setDisplayShowHomeEnabled(false);
		
		service = new WordService(this, DBOpenHelper.DB_NAME);
		init();
		
		initDBOpenHelper();
	}
	
	private void initDBOpenHelper() {
		dbOpenHelper = (DBOpenHelper) service.dbOpenHelper;
		database = dbOpenHelper.getWritableDatabase();
	}

	private void init() {
		login_tv = (TextView) findViewById(R.id.login_yiyouzhanghao);
		login_tv.setTextColor(Color.GREEN);
		login_tv.setOnClickListener(listener);
		
		login_btn = (Button) findViewById(R.id.login_zhuce_btn);
		login_btn.setOnClickListener(listener);
		
		login_name = (EditText) findViewById(R.id.login_name);
		
		login_password = (EditText) findViewById(R.id.login_password);
		
	}
	
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.login_yiyouzhanghao:
				Intent intent = new Intent(LoginActivity.this, LogonActivity.class);
				startActivity(intent);
				break;
			case R.id.login_zhuce_btn:
				if(!Pattern.matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", login_name.getText().toString())
					){
					
					Toast.makeText(LoginActivity.this, "�ֻ��Ÿ�ʽ����ȷ�����޸ģ�", 0).show();
					
				}
				else if(!Pattern.matches("^[a-zA-Z0-9]{6,16}$", login_password.getText().toString()) ){
					Toast.makeText(LoginActivity.this, "����Ҫ����6λ", 0).show();
				}
				else{
					showMultiDia();
				}
				break;
				
			}
		}

		private void showMultiDia() {
			AlertDialog.Builder multiDia=new AlertDialog.Builder(LoginActivity.this);  
	        multiDia.setTitle("ȷ���ֻ���");  
	        multiDia.setMessage("�ֻ��������˺ŵ�¼�������һأ��ǳ���Ҫ����ȷ������д���ֻ����Ƿ���ȷ��\n" + 
	        login_name.getText().toString() + "\n");
	        multiDia.setPositiveButton("��ȷ����", new DialogInterface.OnClickListener() {  
	              
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {  
	            	Cursor cursor = database.query(DBOpenHelper.USER_TABLE_NAME, 
	            			new String[]{DBOpenHelper.USER_ID}, 
	            			DBOpenHelper.USER_EMAIL + "=?" , 
	            			new String[]{login_name.getText().toString()}, 
	            			null, null, null);
	            	
	            	if (cursor.getCount() != 0) {
	            		Toast.makeText(LoginActivity.this, "ע���ֻ����Ѵ��ڣ���ֱ�ӵ�¼", Toast.LENGTH_LONG).show();
	            		Intent intent = new Intent(LoginActivity.this, LogonActivity.class);
	            		intent.putExtra("name", login_name.getText().toString());
	            		startActivity(intent);
					}else{
						Intent intent = new Intent(LoginActivity.this, SetStudyActivity.class);
		            	startActivity(intent);
		            	User user = new User(login_name.getText().toString(), login_password.getText().toString());
		            	dbOpenHelper.insertUserDB(database, user);
		            	
		            	service.setUserSharedPreferences(LoginActivity.this, login_name.getText().toString(),
		            			login_password.getText().toString());
		            	
		            	Toast.makeText(LoginActivity.this, "ע��ɹ���", 0).show();
					}
	            }  
	        });  
	        multiDia.setNeutralButton("�����޸�", new DialogInterface.OnClickListener() {  
	              
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {  
	            }  
	        });  
	        multiDia.create().show();  
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("����").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		return super.onCreateOptionsMenu(menu);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(LoginActivity.this, SetStudyActivity.class);
		startActivity(intent);
		return super.onOptionsItemSelected(item);
	}
}
