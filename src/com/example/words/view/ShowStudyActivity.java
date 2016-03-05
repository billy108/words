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
				Toast.makeText(getApplication(), "请先下载单词库！", 0).show();
			}
		}
	}
	
	private  List<Word> wordDB() {
		wordList = new ArrayList<Word>();
		
		String[] wordName = new String[]{
				"в связи с этим",
				"а именно",
				"а то",
				"без исключения",
				"без труда",
				"более всего",
				"более или менее",
				"более того",
				"более чем",
				"большей частью",
				"брать что на себя",
				"быть в состоянии",
				"в большей или меньшей степени",
				"в большей мере",
				"в большинстве случаев",
				"в больших масштабах",
				"в большой степени",
				"в будущем",
				"введение(чего)в эксплуатацию",
				"введение",
				"эксплуатация(",
				"в виде(чего)",
				"вводить(ввести)(чего)в движение",
				"вводить(ввести)(что)в действие",
				"вводить(ввести)(что)в строй",
				"вводить(ввести)(что)в эксплуатацию",
				"в дальнейшем",
				"в данное время",
				"в данном случае",
				"в действительности",
				"вести себя",
				"в зависимости от(чего)",
				"в заключение",
				"в значительной мере",
				"в интересах(чего)",
				"в итоге",
				"в(каком)смысле",
				"в качестве(кого-чего)",
				"включать(включить)в себя(что)",
				"в количественном отношении",
				"в конечном счёте",
				"в конце концов",
				"в крайней мере",
				"в крайнем случае",
				"влечь за собой(что)",
				"влечь кого-что",
				"влечь-влегать",
				"влечься",
				"вместе взятые",
				"вместе с тем",
				"в независимости от(чего)",
				"в областе(чего)",
				"в обход(чего)",
				"в общем",
				"во время(чего)",
				"во всех областях",
				"во всех отношениях",
				"в одно целое",
				"во главе с(кем)",
				"во избежание",
				"во многом",
				"вообще говоря",
				"в основе(чего)",
				"в основном",
				"в остальном",
				"в отличие от(чего)",
				"в отношении(чего)",
				"во что бы то ни было",
				"во что бы то ни стало",
				"в первую очередь",
				"в пользу(кого-чего)",
				"в порядке",
				"в последующем",
				"в пределах(чего)",
				"в противоречии с(чем)",
				"противоречие",
				"противоречить кому-чему",
				"в прошлом",
				"в результате(чего)",
				"время от времени",
				"вряд ли",
				"в ряде(чего)",
				"в самом деле",
				"в свете(чего)",
				"в своё время",
				"в свою очередь",
				"в связи с(чем)",
				"в связи с этим",
				"всего лишь",
				"всё более и более",
				"всё больше и больше",
				"всё ещё",
				"всё же",
				"всё равно",
				"всё-таки",
				"в силу того,что",
				"в силу(чего)",
				"вслед за(чем)",
				"вслед",
				"в соответствии с(чем)",
				"в состоянии(с инф)",
				"в сравнении с(чем)",
				"в среднем",
				"в срок",
				"вступать(вступить)в строй",
				"в сущности",
				"в течение(чего)",
				"в том числе и",
				"в целом",
				"в целях(чего)",
				"выводить(вывести)(что)из строя",
				"вывести какой балл(或отметку)",
				"вывести в люди кого",
				"вывести из себя",
				"вывести на дорогу",
				"вывести на чистую(或свежую)воду кого-что",
				"вывести наружу что或вывести что на свет божий",
				"главным образом",
				"давать возможность(что делать)",
				"день ото дня",
				"до тех пор，пока…(не)",
				"друг друга",
				"другими словами",
				"едва ли",
				"едва",
				"едва ли не",
				"за исключением",
				"занимать(какое)место",
				"и другие",
				"из года в год",
				"изо дня в день",
				"иметь в виду",
				"иметь дело с(чем)",
				"иметь место",
				"иметь целью(что-делать)",
				"иными словами",
				"и прочее",
				"прочий",
				"исходя из(чего)",
				"и так далее(и т.д.)",
				"и тому подобное(и т.п.)"
		};
		String[] wordContent = new String[]{
				"因而，因此",
				"即，即是",
				"不然，否则，甚至",
				"毫无例外地(исключения开除，取消/例外)",
				"容易地，不费力地",
				"尤其",
				"多多少少",
				"不但如此，尤其是",
				"多于",
				"大半，大部分",
				"担任，负担起",
				"能够",
				"在一定程度上，或多或少地",
				"在较大的程度上",
				"在大多数情况下；大半是",
				"在很大的范围内，大规模地",
				"在很大程度上",
				"将来",
				"开动…；使…运转",
				"(中)引入，列入；采用，确立；引论，绪论",
				"(阴)剥削，剥削制度；经营，使用，开垦，开发，开采/эксплуатационный(形)",
				"(1)成…形式；以…形式(2)作为…",
				"起动，使…开动",
				"施工，使…开工，开动",
				"使…开工",
				"使…运行",
				"今后，以后，在下面",
				"现时",
				"在这种情况下",
				"实际上，实际",
				"（持某种）态度；举止，行动",
				"根据；取决于；要看…而定",
				"最后",
				"在很大程度上",
				"为了…，为了…的利益",
				"总之；总共；结果",
				"在…方面；就…来说",
				"作为…",
				"包含…，包括",
				"在数量上，在数量方面",
				"最后，结果，归根结底",
				"最后，毕竟，归根结底",
				"至少",
				"不得已时",
				"引起，招致；因而得到；结果是",
				"(1)拉，拖，牵引(2)使向往，吸引；使爱慕，使爱好",
				"躺入，伸入，探入；插入",
				"(1)(文语)拖在(某物)后面；(旧，诗)艰难地走动，缓慢地移动；(时间)拖延着，过得很缓慢(2)к кому-чему向往，爱慕，渴望",
				"总共，一起（взятые是взять的被动形动词）",
				"同时，并且",
				"不管…不以…为转移",
				"在…方面，在…范围内",
				"绕着…(обход：巡视；绕道，绕行；迂回，包抄；回避，规避)",
				"一般地，一般说来，大体上",
				"在…时候",
				"在…(的)各方面；在…(的)各个领域",
				"在各方面",
				"成整体",
				"在…领导下；以…为首(глава首长)",
				"为了避免",
				"在很大程度上，在很多方面",
				"一般说来",
				"根据；在…基础上",
				"基本上",
				"在其他方面",
				"不同于；与…不同",
				"在…方面；对于；关于",
				"无论如何",
				"无论如何",
				"首先",
				"有利于…，对…有利",
				"整整齐齐地，有条理地",
				"后面；以后…",
				"在…之内，在…范围内",
				"与…相抵触；和…矛盾",
				"(中) 矛盾，抵触；反对意见，反驳",
				"反驳，反对；与…相矛盾，与…相抵触，不一致",
				"在过去；以前；从前",
				"由于…的结果；由于",
				"有时，偶尔",
				"未必",
				"在许多…中；在一系列…中",
				"事实上，果真",
				"根据…；由…看来；就…而论",
				"当时",
				"也，又",
				"由于…，因为…",
				"因而，因此",
				"只是，不过",
				"越来越…",
				"越来越多",
				"仍然，仍旧",
				"毕竟；仍然",
				"都一样，同样",
				"还是；终究，毕竟",
				"由于；因为…",
				"由于；因为…",
				"在…之后；随…之后；随着…",
				"(副) 跟着，随着 (за кем-чем)(前)(第三格) 跟在…身后，随…之后",
				"按照…，根据…；与…相应",
				"能够…，有可能…",
				"与…相比；与…比较",
				"平均",
				"按期",
				"开工，动用",
				"实际上；实质上",
				"在…之内；在…期间",
				"包括…在内；其中包",
				"总共；整个地",
				"为了，为的是；目的是，以便",
				"使失去战斗力，使失去工作能力，杀伤；毁坏，使发生故障",
				"(对学业)评定平均分数",
				"协助…得到社会地位",
				"使失掉自制力，使发火，使发脾气",
				"帮助…走上正途",
				"(口语)揭破，揭穿，揭露，使暴露在光天化日之下",
				"使暴露，揭露",
				"主要(地)",
				"使有可能…",
				"日益；一天一天地",
				"一直到… 芳思·小语种",
				"彼此，相互",
				"换句话说",
				"未必，不见得",
				"(副)勉强地，困难地；差一点，险些；刚刚，不久前(连)刚一…就…",
				"几乎",
				"除…之外",
				"占有…地位",
				"等等",
				"逐年地，年年地",
				"一天天地，每天",
				"考虑到…；所指的是…",
				"与…有关；遇到，涉及；要研究的是",
				"发生，产生",
				"目的在于…；可用于…",
				"换句话说",
				"等等",
				"(形)其余的，其他的，别的；(用作名词)прочие，-их(复)其余的人；прочее，-его(中)其他",
				"根据…，从…出发",
				"等等",
				"等等"
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
