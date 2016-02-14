package com.example.words.view;

import java.util.Calendar;
import java.util.Date;

import com.example.words.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class ArrangeStudyActivity extends Activity {
	
	private static final int TIME_MS = 1;
	private static final int TIME_S = TIME_MS * 1000;
	private static final int TIME_MIN = TIME_S * 60;
	private static final int TIME_H = TIME_MIN * 60;
	private static final int TIME_DAY = TIME_H *24;
	
	private TextView tv1, tv2;
	private Button btn_time, btn_number;
	private NumberPicker numberPicker;
	
	private int wordsNumber;
	private int days;
	int a = 25;;
	int perWord = 140;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arrangestudy);
		setTitle("安排学习计划");
		init();
	}
	
	private void init() {
		wordsNumber = getIntent().getIntExtra("num", 0);
		days = wordsNumber / 25;
		
		
		tv1 = (TextView) findViewById(R.id.ArrangeText1);
		tv1.setText("初中 - 中考单词（" + wordsNumber + "词）");
		tv2 = (TextView) findViewById(R.id.ArrangeText2);
		tv2.setText("选择一种你喜欢的学习安排方式");
		
		btn_time = (Button) findViewById(R.id.Arrange_btn_time);
		btn_time.setText("按时间\n我要X月X日完成");
		
		btn_number = (Button) findViewById(R.id.Arrange_btn_Number);
		btn_number.setText("按数量\n我每天学X个");
		
		
		
		OnClickListener lister = new OnClickListener() {
			Calendar c = Calendar.getInstance();
			 int yearc = c.get(Calendar.YEAR);
			 int monthc = c.get(Calendar.MONTH); 
			 int dayOfMonthc = c.get(Calendar.DAY_OF_MONTH);
			
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.Arrange_btn_time:
					
					LayoutInflater inflater = getLayoutInflater();
					View layout = inflater.inflate(R.layout.datedialog, (ViewGroup) findViewById(R.id.dialog));
					
					new AlertDialog.Builder(ArrangeStudyActivity.this).setTitle("截止日期").setView(layout)
					.setPositiveButton("确定", new DialogInterface.OnClickListener(){
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(ArrangeStudyActivity.this, FirstStudyActivity.class);
							intent.putExtra("flag", 1);
							intent.putExtra("perWord", perWord);
							intent.putExtra("wordsNumber", wordsNumber);
							startActivity(intent);
						}
						
					})
					.setNegativeButton("取消", new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
						
					})
					.show();
					changeDate(layout);
					break;
					
				case R.id.Arrange_btn_Number:
					LayoutInflater inflater1 = getLayoutInflater();
					View layout1 = inflater1.inflate(R.layout.numberdialog, (ViewGroup) findViewById(R.id.nubmerDialog));
					
					new AlertDialog.Builder(ArrangeStudyActivity.this).setTitle("固定词量").setView(layout1)
					.setPositiveButton("确定", new DialogInterface.OnClickListener(){
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(ArrangeStudyActivity.this, FirstStudyActivity.class);
							intent.putExtra("flag", 2);
							intent.putExtra("days", days);
							intent.putExtra("perWord", a);
							intent.putExtra("wordsNumber", wordsNumber);
							startActivity(intent);
						}
						
					})
					.setNegativeButton("取消", new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
						
					})
					.show();
					numberChanged(layout1);
					break;
				}
			}

			private void numberChanged(View layout) {
				final TextView numberChangedTextView = (TextView)layout.findViewById(R.id.numberDiaglo_tv);
				numberPicker = (NumberPicker) layout.findViewById(R.id.numberPicker);
				
				numberChangedTextView.setText("预计完成天数：" + days + "天");
				
				final String[] displayedValues = {"5","15","25","35","45",
						"60","80","100","150","200","250","300"}; 
				numberPicker.setDisplayedValues(displayedValues);
				numberPicker.setMinValue(1);
				numberPicker.setMaxValue(12);
				numberPicker.setValue(3);
				
				numberPicker.setOnValueChangedListener(new OnValueChangeListener() {
					
					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
						a = Integer.parseInt(displayedValues[newVal-1]);
						System.out.println(a);
						days = wordsNumber/a;
						numberChangedTextView.setText("预计完成天数：" + days + "天");
					}
				});
				
				
			}

			private void changeDate(View layout) {
				final TextView dateDialogTextView = (TextView) layout.findViewById(R.id.dateDiaglo_tv);
				DatePicker datePicker1 = (DatePicker)layout.findViewById(R.id.datePicker1); 
				
				long currentTime = c.getTimeInMillis();
				datePicker1.setMinDate(currentTime);
				
				dateDialogTextView.setText("预计每日学习单词数量为：" + wordsNumber);
				
				OnDateChangedListener listen = new OnDateChangedListener() {
					
					@Override
					public void onDateChanged(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						Calendar targetDate = Calendar.getInstance();
						targetDate.set(year, monthOfYear, dayOfMonth);
						long targetMillTime = targetDate.getTimeInMillis();
						long beteenMill = targetMillTime - c.getTimeInMillis();
						int days = (int) (beteenMill / TIME_DAY);
						perWord = wordsNumber / days;
						
						dateDialogTextView.setText("预计每日学习单词数量为：" + perWord);
					}
				};
				datePicker1.init(yearc, monthc, dayOfMonthc, listen);
			}
		};
		
		btn_time.setOnClickListener(lister);
		btn_number.setOnClickListener(lister);
		
	}
}
