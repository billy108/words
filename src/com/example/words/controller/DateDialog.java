package com.example.words.controller;

import com.example.words.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class DateDialog extends Dialog {

	public DateDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datedialog);
	}
}
