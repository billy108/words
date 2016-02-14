package com.example.words.view;

import com.example.words.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;

public class DiscoveryFragment extends Fragment {
	private Gallery mGallery;
	private int[] items = {R.drawable.back, R.drawable.right, R.drawable.start};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_discovery, container, false);
		
//		mGallery = (Gallery) v.findViewById(R.id.discovery_gallery);
//		mGallery.setAdapter(new myAdapter(getActivity()));
		
		return v;
	}
	
	 class myAdapter extends BaseAdapter{
		 private Context mContext;
		 
		 public myAdapter(Context context){
			 mContext = context;
		 }

		@Override
		public int getCount() {
			return items.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			return null;
		}

	}
}
