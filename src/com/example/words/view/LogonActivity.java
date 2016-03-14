package com.example.words.view;

import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.words.R;
import com.example.words.model.DBOpenHelper;
import com.example.words.model.WordService;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogonActivity extends Activity {
	
	private TextView FG_btn, login_tv;
	private EditText logon_name;
	private EditText logon_password;
	private Button mLogin_btn;
	private WordService service;
	
	private String path;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logon);
		setTitle("登录");
		getActionBar().setDisplayShowHomeEnabled(false);
		path = "http://words.cooltester.com/api/user/login?";
		
		init();
	}

	private void init() {
		service = new WordService(this);
		Intent intent = getIntent();
		
		logon_name = (EditText) findViewById(R.id.logon_name);
		logon_name.setText(intent.getStringExtra("name"));
		
		logon_password = (EditText) findViewById(R.id.logon_password);
		
		FG_btn = (TextView) findViewById(R.id.forgetpassword);
		login_tv = (TextView) findViewById(R.id.login_zhuce);
		login_tv.setTextColor(Color.GREEN);
		
		login_tv.setOnClickListener(listener);
		
		mLogin_btn = (Button) findViewById(R.id.login_login_btn);
		mLogin_btn.setOnClickListener(listener);
	}
	
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.login_zhuce:
				Intent intent = new Intent(LogonActivity.this, LoginActivity.class);
				startActivity(intent);
				break;
			case R.id.login_login_btn:
				SQLiteOpenHelper dbOpenHelper = new DBOpenHelper(LogonActivity.this, DBOpenHelper.DB_NAME, null, DBOpenHelper.VERSION);  
				SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
				
				Cursor cursor = database.query(DBOpenHelper.USER_TABLE_NAME, 
						null, 
						DBOpenHelper.USER_EMAIL + "=?" , 
						new String[]{logon_name.getText().toString()}, 
						null, null, null);
				
				if (!Pattern.matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$", logon_name.getText().toString())) {
					Toast.makeText(LogonActivity.this, "手机格式不正确！", 0).show();
				}
				else if(!Pattern.matches("^[a-zA-Z0-9]{6,16}$", logon_password.getText().toString())){
					Toast.makeText(LogonActivity.this, "密码格式不正确！", Toast.LENGTH_LONG).show();
				}else{
					MyThread thread = new MyThread(logon_name.getText().toString(), logon_password.getText().toString());
					thread.start();
//					if (cursor.getCount() == 0) {
//						Toast.makeText(LogonActivity.this, "�û������ڣ�����ע�ᣡ", Toast.LENGTH_SHORT).show();
//					}else{
//						cursor.moveToFirst();
//						System.out.println(logon_password.getText().toString());
//						if (cursor.getString(cursor.getColumnIndex(DBOpenHelper.USER_PASSWORD)) 
//										.equals(logon_password.getText().toString())){
//							Intent intent1 = new Intent(LogonActivity.this, SetStudyActivity.class);
//							startActivity(intent1);
//							
//							service.setUserSharedPreferences(LogonActivity.this, logon_name.getText().toString(),
//									logon_password.getText().toString());
//							
//							Toast.makeText(LogonActivity.this, "��ӭ��������", Toast.LENGTH_SHORT).show();
//						}else{
//							logon_password.setText("");
//							Toast.makeText(LogonActivity.this, "���벻��ȷ����������������", Toast.LENGTH_SHORT).show();
//						}
//					}
				}
				break;
			}
		}
	};
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			String jsonString = (String) msg.obj;
			System.out.println("run()后的json:" + jsonString);
			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				int responseCode = jsonObject.getInt("code");
				if(responseCode == 22000){
					Intent intent = new Intent(LogonActivity.this, SetStudyActivity.class);
					startActivity(intent);
					
					service.setUserSharedPreferences(LogonActivity.this, logon_name.getText().toString(),
							logon_password.getText().toString());
					Toast.makeText(LogonActivity.this, "注册成功！", 0).show();
				}
				else {
					String text = jsonObject.getString("message");
					Toast.makeText(LogonActivity.this, text , 0).show();
				} 
			} catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};
	
	class MyThread extends Thread{
		String name, password;
		String jsonString;
		
		public MyThread(String name, String password){
			this.name = name;
			this.password = password;
		}
    	@Override
    	public void run() {
    		super.run();
    		String userPath = path + "username=" + name +"&password=" + password;
    		System.out.println("url是：" + userPath);
    		jsonString = service.getNet(userPath);
    		System.out.println("run()里的json是：" + jsonString);
    		Message msg = new Message();
    		msg.obj = jsonString;
    		handler.sendMessage(msg);
    		
    	}
    	
    }
	
}
