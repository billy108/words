package com.example.words.view;

import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
	public static final String TAG = "loginActivity";
	
	private TextView login_tv;
	public EditText login_name,login_password;
	private Button login_btn;
	
	private DBOpenHelper dbOpenHelper;
	private SQLiteDatabase database;
	
	private WordService service;
	
	String path;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		setTitle("注册");
		getActionBar().setDisplayShowHomeEnabled(false);
		
		service = new WordService(this, DBOpenHelper.DB_NAME);
		path = "http://words.cooltester.com/api/user/register?";
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
					
					Toast.makeText(LoginActivity.this, "手格式不正确", 0).show();
					
				}
				else if(!Pattern.matches("^[a-zA-Z0-9]{6,16}$", login_password.getText().toString()) ){
					Toast.makeText(LoginActivity.this, "密码最少6位", 0).show();
				}
				else{
					showMultiDia();
				}
				break;
				
			}
		}

		private void showMultiDia() {
			AlertDialog.Builder multiDia=new AlertDialog.Builder(LoginActivity.this);  
	        multiDia.setTitle("确认用户信息");  
	        multiDia.setMessage("请仔细确认手机号是否正确，以后用于账号的找回\n" + 
	        login_name.getText().toString() + "\n");
	        multiDia.setPositiveButton("正确继续", new DialogInterface.OnClickListener() {  
	              
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {
	            	MyThread thread = new MyThread(login_name.getText().toString(), login_password.getText().toString());
	            	thread.start();
	            	
//	            	Cursor cursor = database.query(DBOpenHelper.USER_TABLE_NAME, 
//	            			new String[]{DBOpenHelper.USER_ID}, 
//	            			DBOpenHelper.USER_EMAIL + "=?" , 
//	            			new String[]{login_name.getText().toString()}, 
//	            			null, null, null);
//	            	
//	            	if (cursor.getCount() != 0) {
//	            		Toast.makeText(LoginActivity.this, "注锟斤拷锟街伙拷锟斤拷锟窖达拷锟节ｏ拷锟斤拷直锟接碉拷录", Toast.LENGTH_LONG).show();
//	            		Intent intent = new Intent(LoginActivity.this, LogonActivity.class);
//	            		intent.putExtra("name", login_name.getText().toString());
//	            		startActivity(intent);
//					}else{
//						Intent intent = new Intent(LoginActivity.this, SetStudyActivity.class);
//		            	startActivity(intent);
//		            	User user = new User(login_name.getText().toString(), login_password.getText().toString());
//		            	dbOpenHelper.insertUserDB(database, user);
//		            	
//		            	service.setUserSharedPreferences(LoginActivity.this, login_name.getText().toString(),
//		            			login_password.getText().toString());
//		            	
//		            	Toast.makeText(LoginActivity.this, "注锟斤拷晒锟斤拷锟�", 0).show();
//					}
	            	}
	        });  
	        multiDia.setNeutralButton("返回修改", new DialogInterface.OnClickListener() {  
	              
	            @Override  
	            public void onClick(DialogInterface dialog, int which) {  
	            }  
	        });  
	        multiDia.create().show();  
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
					Intent intent = new Intent(LoginActivity.this, SetStudyActivity.class);
					startActivity(intent);
					
					service.setUserSharedPreferences(LoginActivity.this, login_name.getText().toString(),
							login_password.getText().toString());
					Toast.makeText(LoginActivity.this, "注册成功！", 0).show();
				}
				else {
					String text = jsonObject.getString("message");
					Toast.makeText(LoginActivity.this, text , 0).show();
				} 
			} catch (JSONException e) {
				e.printStackTrace();
			}
		};
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		menu.add("锟斤拷锟斤拷").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		return super.onCreateOptionsMenu(menu);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(LoginActivity.this, SetStudyActivity.class);
		startActivity(intent);
		return super.onOptionsItemSelected(item);
	}
	
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
