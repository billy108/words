package com.example.words.view;

import com.example.words.R;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class ConsolidateWordFragment extends Fragment {
	private Button mNoConsolidateWord_Button;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		getActivity().setTitle("巩固熟词");
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_consolidateword, container, false);
		
		new AlertDialog.Builder(getActivity())
		.setView(LayoutInflater.from(getActivity()).inflate(R.layout.hint, null))
		.setTitle("提示")
		.setNegativeButton("确定", null)
		.create()
		.show();
		
		mNoConsolidateWord_Button = (Button) v.findViewById(R.id.noconsolidateWord_Button);
		mNoConsolidateWord_Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), FirstViewPagerActivity.class);
				startActivity(i);
			}
		});
		
		return v;
	}
}
