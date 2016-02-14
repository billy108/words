package com.example.words.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.words.R;

public class Pic extends BaseAdapter{
	private Context context;
	private int id;
	private int[] imageList = {R.drawable.right, 
								R.drawable.start, 
								R.drawable.back};
	
	
	public Pic(Context context) {
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return 0;
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
		ImageView i = new ImageView(context);
		i.setImageResource(imageList[position]);
		
		return i;
	}
	
}
