package com.example.words.view;

import java.util.ArrayList;
import java.util.List;

import com.example.words.R;
import com.example.words.model.DBOpenHelper;
import com.example.words.model.ImportWord;
import com.example.words.model.Word;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowStudyActivity extends Activity {
	private TextView first_item;
	private ListView item_lv;
	private Intent intent;
	private String item_title;
	private int item;
	
	private List<Word> wordList;
	
	private Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("设置学习计划");
		setContentView(R.layout.firstitem);
		init();
	}

	private void init() {
		first_item = (TextView) findViewById(R.id.first_tv);
		item_lv = (ListView) findViewById(R.id.firstitem);
		
		intent = getIntent();
		item_title = intent.getStringExtra("title");
		item = intent.getIntExtra("item", 0);
		first_item.setText(item_title);
		
		setShowStudyAdapter(item);
		
		item_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					Intent intent = new Intent(ShowStudyActivity.this, ArrangeStudyActivity.class);
					intent.putExtra("num", cursor.getCount());
					startActivity(intent);
					break;
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				case 5:
					
					break;
				case 6:
					
					break;
				}
			}
		});
	}
	
	private void setShowStudyAdapter(int item) {
		if (item == 0) {
			try {
				SQLiteOpenHelper dbOpenHelper = new ImportWord(this, ImportWord.DB_WORD_NAME, null, DBOpenHelper.VERSION);
				SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
				
//				insertWordTable(database, wordList);
				
				cursor = database.query(ImportWord.TABLE_WORD_1,
						null, null , null, null, null, null);
				
				String[] items = {"中考单词\n" + cursor.getCount() + "个"};
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(ShowStudyActivity.this, 
						android.R.layout.simple_list_item_1, items);
				item_lv.setAdapter(adapter);
			} catch (SQLiteException e) {
				Toast.makeText(getApplication(), "无单词库，请返回下载！", 0).show();
			}
		}
	}
	
	private  List<Word> wordDB() {
		wordList = new ArrayList<Word>();
		
		String[] wordName = new String[]{
				"�� ��ӧ�٧� �� ���ڧ�",
				"�� �ڧާ֧ߧߧ�",
				"�� ���",
				"�ҧ֧� �ڧ�ܧݧ��֧ߧڧ�",
				"�ҧ֧� ����է�",
				"�ҧ�ݧ֧� �ӧ�֧ԧ�",
				"�ҧ�ݧ֧� �ڧݧ� �ާ֧ߧ֧�",
				"�ҧ�ݧ֧� ���ԧ�",
				"�ҧ�ݧ֧� ��֧�",
				"�ҧ�ݧ��֧� ��ѧ����",
				"�ҧ�ѧ�� ���� �ߧ� ��֧ҧ�",
				"�ҧ��� �� �������ߧڧ�",
				"�� �ҧ�ݧ��֧� �ڧݧ� �ާ֧ߧ��֧� ���֧�֧ߧ�",
				"�� �ҧ�ݧ��֧� �ާ֧��",
				"�� �ҧ�ݧ��ڧߧ��ӧ� ��ݧ��ѧ֧�",
				"�� �ҧ�ݧ��ڧ� �ާѧ���ѧҧѧ�",
				"�� �ҧ�ݧ���� ���֧�֧ߧ�",
				"�� �ҧ�է��֧�",
				"�ӧӧ֧է֧ߧڧ�(��֧ԧ�)�� ��ܧ��ݧ�ѧ�ѧ�ڧ�",
				"�ӧӧ֧է֧ߧڧ�",
				"��ܧ��ݧ�ѧ�ѧ�ڧ�(",
				"�� �ӧڧէ�(��֧ԧ�)",
				"�ӧӧ�էڧ��(�ӧӧ֧���)(��֧ԧ�)�� �էӧڧا֧ߧڧ�",
				"�ӧӧ�էڧ��(�ӧӧ֧���)(����)�� �է֧ۧ��ӧڧ�",
				"�ӧӧ�էڧ��(�ӧӧ֧���)(����)�� ������",
				"�ӧӧ�էڧ��(�ӧӧ֧���)(����)�� ��ܧ��ݧ�ѧ�ѧ�ڧ�",
				"�� �էѧݧ�ߧ֧ۧ�֧�",
				"�� �էѧߧߧ�� �ӧ�֧ާ�",
				"�� �էѧߧߧ�� ��ݧ��ѧ�",
				"�� �է֧ۧ��ӧڧ�֧ݧ�ߧ����",
				"�ӧ֧��� ��֧ҧ�",
				"�� �٧ѧӧڧ�ڧާ���� ���(��֧ԧ�)",
				"�� �٧ѧܧݧ��֧ߧڧ�",
				"�� �٧ߧѧ�ڧ�֧ݧ�ߧ�� �ާ֧��",
				"�� �ڧߧ�֧�֧�ѧ�(��֧ԧ�)",
				"�� �ڧ��ԧ�",
				"��(�ܧѧܧ��)��ާ��ݧ�",
				"�� �ܧѧ�֧��ӧ�(�ܧ�ԧ�-��֧ԧ�)",
				"�ӧܧݧ��ѧ��(�ӧܧݧ��ڧ��)�� ��֧ҧ�(����)",
				"�� �ܧ�ݧڧ�֧��ӧ֧ߧߧ�� ���ߧ��֧ߧڧ�",
				"�� �ܧ�ߧ֧�ߧ�� ���ק��",
				"�� �ܧ�ߧ�� �ܧ�ߧ���",
				"�� �ܧ�ѧۧߧ֧� �ާ֧��",
				"�� �ܧ�ѧۧߧ֧� ��ݧ��ѧ�",
				"�ӧݧ֧�� �٧� ���ҧ��(����)",
				"�ӧݧ֧�� �ܧ�ԧ�-����",
				"�ӧݧ֧��-�ӧݧ֧ԧѧ��",
				"�ӧݧ֧����",
				"�ӧާ֧��� �ӧ٧����",
				"�ӧާ֧��� �� ��֧�",
				"�� �ߧ֧٧ѧӧڧ�ڧާ���� ���(��֧ԧ�)",
				"�� ��ҧݧѧ���(��֧ԧ�)",
				"�� ��ҧ���(��֧ԧ�)",
				"�� ��ҧ�֧�",
				"�ӧ� �ӧ�֧ާ�(��֧ԧ�)",
				"�ӧ� �ӧ�֧� ��ҧݧѧ����",
				"�ӧ� �ӧ�֧� ���ߧ��֧ߧڧ��",
				"�� ��էߧ� ��֧ݧ��",
				"�ӧ� �ԧݧѧӧ� ��(�ܧ֧�)",
				"�ӧ� �ڧ٧ҧ֧اѧߧڧ�",
				"�ӧ� �ާߧ�ԧ��",
				"�ӧ��ҧ�� �ԧ�ӧ���",
				"�� ���ߧ�ӧ�(��֧ԧ�)",
				"�� ���ߧ�ӧߧ��",
				"�� ����ѧݧ�ߧ��",
				"�� ���ݧڧ�ڧ� ���(��֧ԧ�)",
				"�� ���ߧ��֧ߧڧ�(��֧ԧ�)",
				"�ӧ� ���� �ҧ� ��� �ߧ� �ҧ�ݧ�",
				"�ӧ� ���� �ҧ� ��� �ߧ� ���ѧݧ�",
				"�� ��֧�ӧ�� ���֧�֧է�",
				"�� ���ݧ�٧�(�ܧ�ԧ�-��֧ԧ�)",
				"�� �����էܧ�",
				"�� ����ݧ֧է���֧�",
				"�� ���֧է֧ݧѧ�(��֧ԧ�)",
				"�� �����ڧӧ��֧�ڧ� ��(��֧�)",
				"�����ڧӧ��֧�ڧ�",
				"�����ڧӧ��֧�ڧ�� �ܧ�ާ�-��֧ާ�",
				"�� �����ݧ��",
				"�� ��֧٧�ݧ��ѧ��(��֧ԧ�)",
				"�ӧ�֧ާ� ��� �ӧ�֧ާ֧ߧ�",
				"�ӧ��� �ݧ�",
				"�� ���է�(��֧ԧ�)",
				"�� ��ѧާ�� �է֧ݧ�",
				"�� ��ӧ֧��(��֧ԧ�)",
				"�� ��ӧ�� �ӧ�֧ާ�",
				"�� ��ӧ�� ���֧�֧է�",
				"�� ��ӧ�٧� ��(��֧�)",
				"�� ��ӧ�٧� �� ���ڧ�",
				"�ӧ�֧ԧ� �ݧڧ��",
				"�ӧ�� �ҧ�ݧ֧� �� �ҧ�ݧ֧�",
				"�ӧ�� �ҧ�ݧ��� �� �ҧ�ݧ���",
				"�ӧ�� �֧��",
				"�ӧ�� �ا�",
				"�ӧ�� ��ѧӧߧ�",
				"�ӧ��-��ѧܧ�",
				"�� ��ڧݧ� ���ԧ�,����",
				"�� ��ڧݧ�(��֧ԧ�)",
				"�ӧ�ݧ֧� �٧�(��֧�)",
				"�ӧ�ݧ֧�",
				"�� �����ӧ֧���ӧڧ� ��(��֧�)",
				"�� �������ߧڧ�(�� �ڧߧ�)",
				"�� ���ѧӧߧ֧ߧڧ� ��(��֧�)",
				"�� ���֧էߧ֧�",
				"�� �����",
				"�ӧ����ѧ��(�ӧ����ڧ��)�� ������",
				"�� ����ߧ����",
				"�� ��֧�֧ߧڧ�(��֧ԧ�)",
				"�� ���� ��ڧ�ݧ� ��",
				"�� ��֧ݧ��",
				"�� ��֧ݧ��(��֧ԧ�)",
				"�ӧ�ӧ�էڧ��(�ӧ�ӧ֧���)(����)�ڧ� ������",
				"�ӧ�ӧ֧��� �ܧѧܧ�� �ҧѧݧ�(����ާ֧�ܧ�)",
				"�ӧ�ӧ֧��� �� �ݧ�է� �ܧ�ԧ�",
				"�ӧ�ӧ֧��� �ڧ� ��֧ҧ�",
				"�ӧ�ӧ֧��� �ߧ� �է���ԧ�",
				"�ӧ�ӧ֧��� �ߧ� ��ڧ����(���ӧ֧ا��)�ӧ�է� �ܧ�ԧ�-����",
				"�ӧ�ӧ֧��� �ߧѧ��ا� �����ӧ�ӧ֧��� ���� �ߧ� ��ӧ֧� �ҧ�اڧ�",
				"�ԧݧѧӧߧ�� ��ҧ�ѧ٧��",
				"�էѧӧѧ�� �ӧ�٧ާ�اߧ����(���� �է֧ݧѧ��)",
				"�է֧ߧ� ���� �էߧ�",
				"�է� ��֧� ���⣬���ܧѡ�(�ߧ�)",
				"�է��� �է��ԧ�",
				"�է��ԧڧާ� ��ݧ�ӧѧާ�",
				"�֧էӧ� �ݧ�",
				"�֧էӧ�",
				"�֧էӧ� �ݧ� �ߧ�",
				"�٧� �ڧ�ܧݧ��֧ߧڧ֧�",
				"�٧ѧߧڧާѧ��(�ܧѧܧ��)�ާ֧���",
				"�� �է��ԧڧ�",
				"�ڧ� �ԧ�է� �� �ԧ��",
				"�ڧ٧� �էߧ� �� �է֧ߧ�",
				"�ڧާ֧�� �� �ӧڧէ�",
				"�ڧާ֧�� �է֧ݧ� ��(��֧�)",
				"�ڧާ֧�� �ާ֧���",
				"�ڧާ֧�� ��֧ݧ��(����-�է֧ݧѧ��)",
				"�ڧߧ�ާ� ��ݧ�ӧѧާ�",
				"�� �����֧�",
				"�����ڧ�",
				"�ڧ���է� �ڧ�(��֧ԧ�)",
				"�� ��ѧ� �էѧݧ֧�(�� ��.��.)",
				"�� ���ާ� ���է�ҧߧ��(�� ��.��.)"
		};
		String[] wordContent = new String[]{
				"��������",
				"��������",
				"��Ȼ����������",
				"���������(�ڧ�ܧݧ��֧ߧڧ񿪳���ȡ��/����)",
				"���׵أ���������",
				"����",
				"�������",
				"������ˣ�������",
				"����",
				"��룬�󲿷�",
				"���Σ�������",
				"�ܹ�",
				"��һ���̶��ϣ������ٵ�",
				"�ڽϴ�ĳ̶���",
				"�ڴ��������£������",
				"�ںܴ�ķ�Χ�ڣ����ģ��",
				"�ںܴ�̶���",
				"����",
				"��������ʹ����ת",
				"(��)���룬���룻���ã�ȷ�������ۣ�����",
				"(��)�����������ƶȣ���Ӫ��ʹ�ã����ѣ�����������/��ܧ��ݧ�ѧ�ѧ�ڧ�ߧߧ��(��)",
				"(1)�ɡ���ʽ���ԡ���ʽ(2)��Ϊ��",
				"�𶯣�ʹ������",
				"ʩ����ʹ������������",
				"ʹ������",
				"ʹ������",
				"����Ժ�������",
				"��ʱ",
				"�����������",
				"ʵ���ϣ�ʵ��",
				"����ĳ�֣�̬�ȣ���ֹ���ж�",
				"���ݣ�ȡ���ڣ�Ҫ��������",
				"���",
				"�ںܴ�̶���",
				"Ϊ�ˡ���Ϊ�ˡ�������",
				"��֮���ܹ������",
				"�ڡ����棻�͡���˵",
				"��Ϊ��",
				"������������",
				"�������ϣ�����������",
				"��󣬽����������",
				"��󣬱Ͼ���������",
				"����",
				"������ʱ",
				"�������£�����õ��������",
				"(1)�����ϣ�ǣ��(2)ʹ������������ʹ��Ľ��ʹ����",
				"���룬���룬̽�룻����",
				"(1)(����)����(ĳ��)���棻(�ɣ�ʫ)���ѵ��߶����������ƶ���(ʱ��)�����ţ����úܻ���(2)�� �ܧ�ާ�-��֧ާ���������Ľ������",
				"�ܹ���һ�𣨧ӧ٧�����ǧӧ٧���ı����ζ��ʣ�",
				"ͬʱ������",
				"���ܡ����ԡ�Ϊת��",
				"�ڡ����棬�ڡ���Χ��",
				"���š�(��ҧ��գ�Ѳ�ӣ��Ƶ������У��ػأ��������رܣ����)",
				"һ��أ�һ��˵����������",
				"�ڡ�ʱ��",
				"�ڡ�(��)�����棻�ڡ�(��)��������",
				"�ڸ�����",
				"������",
				"�ڡ��쵼�£��ԡ�Ϊ��(�ԧݧѧӧ��׳�)",
				"Ϊ�˱���",
				"�ںܴ�̶��ϣ��ںܶ෽��",
				"һ��˵��",
				"���ݣ��ڡ�������",
				"������",
				"����������",
				"��ͬ�ڣ��롭��ͬ",
				"�ڡ����棻���ڣ�����",
				"�������",
				"�������",
				"����",
				"�����ڡ����ԡ�����",
				"��������أ��������",
				"���棻�Ժ�",
				"�ڡ�֮�ڣ��ڡ���Χ��",
				"�롭��ִ����͡�ì��",
				"(��) ì�ܣ��ִ����������������",
				"���������ԣ��롭��ì�ܣ��롭��ִ�����һ��",
				"�ڹ�ȥ����ǰ����ǰ",
				"���ڡ��Ľ��������",
				"��ʱ��ż��",
				"δ��",
				"����࡭�У���һϵ�С���",
				"��ʵ�ϣ�����",
				"���ݡ����ɡ��������͡�����",
				"��ʱ",
				"Ҳ����",
				"���ڡ�����Ϊ��",
				"��������",
				"ֻ�ǣ�����",
				"Խ��Խ��",
				"Խ��Խ��",
				"��Ȼ���Ծ�",
				"�Ͼ�����Ȼ",
				"��һ����ͬ��",
				"���ǣ��վ����Ͼ�",
				"���ڣ���Ϊ��",
				"���ڣ���Ϊ��",
				"�ڡ�֮���桭֮�����š�",
				"(��) ���ţ����� (�٧� �ܧ֧�-��֧�)(ǰ)(������) ���ڡ�����桭֮��",
				"���ա������ݡ����롭��Ӧ",
				"�ܹ������п��ܡ�",
				"�롭��ȣ��롭�Ƚ�",
				"ƽ��",
				"����",
				"����������",
				"ʵ���ϣ�ʵ����",
				"�ڡ�֮�ڣ��ڡ��ڼ�",
				"���������ڣ����а�",
				"�ܹ���������",
				"Ϊ�ˣ�Ϊ���ǣ�Ŀ���ǣ��Ա�",
				"ʹʧȥս������ʹʧȥ����������ɱ�ˣ��ٻ���ʹ��������",
				"(��ѧҵ)����ƽ������",
				"Э�����õ�����λ",
				"ʹʧ����������ʹ����ʹ��Ƣ��",
				"������������;",
				"(����)���ƣ��Ҵ�����¶��ʹ��¶�ڹ��컯��֮��",
				"ʹ��¶����¶",
				"��Ҫ(��)",
				"ʹ�п��ܡ�",
				"���棻һ��һ���",
				"һֱ���� ��˼��С����",
				"�˴ˣ��໥",
				"���仰˵",
				"δ�أ�������",
				"(��)��ǿ�أ����ѵأ���һ�㣬��Щ���ոգ�����ǰ(��)��һ���͡�",
				"����",
				"����֮��",
				"ռ�С���λ",
				"�ȵ�",
				"����أ������",
				"һ����أ�ÿ��",
				"���ǵ�������ָ���ǡ�",
				"�롭�йأ��������漰��Ҫ�о�����",
				"����������",
				"Ŀ�����ڡ��������ڡ�",
				"���仰˵",
				"�ȵ�",
				"(��)����ģ������ģ���ģ�(��������)�����ڧ֣�-�ڧ�(��)������ˣ������֧֣�-�֧ԧ�(��)����",
				"���ݡ����ӡ�����",
				"�ȵ�",
				"�ȵ�"
		};
		
		for (int i = 0; i < wordName.length; i++) {
			
			int id = 0;
			String name = wordName[i];
			String content = wordContent[i];
			int proficiency = 0;
			
			Word word = new Word(id, name, content, proficiency);
			wordList.add(word);
		}
		return wordList;
	}
	
	public void insertWordTable(SQLiteDatabase db, List<Word> wordList) { 
		ContentValues values = new ContentValues();
		wordList = wordDB();
		
		for (int i = 0; i < wordList.size(); i++) {
			Cursor cursor = db.query(ImportWord.TABLE_WORD_1, 
					null, 
					ImportWord.WORD_NAME + "=?" , 
					new String[]{wordList.get(i).getName()}, 
					null, null, null);
			
			if (cursor.getCount() == 0) {
				values.putNull(ImportWord.WORD_ID);
				values.put(ImportWord.WORD_NAME, wordList.get(i).getName());
				values.put(ImportWord.WORD_CONTENT, wordList.get(i).getContent());
				values.put(ImportWord.WORD_PROFICIENCY, 0);
				
				db.insert(ImportWord.TABLE_WORD_1, null, values);
			}
		}
	}
}
