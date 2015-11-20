package com.lenve.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NameFragment extends Fragment {

	private List<String> names;
	private ListView lv;
	private showPro mCallback;
	private ArrayAdapter<String> adapter;

	public interface showPro {
		public void showProByName(String name);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData();
	}

	private void initData() {
		names = new ArrayList<String>();
		names.add("韩愈");
		names.add("柳宗元");
		names.add("苏轼");
		names.add("苏辙");
		names.add("苏洵");
		names.add("欧阳修");
		names.add("曾巩");
		names.add("王安石");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.name_fg, null);
		initLv(v);
		return v;
	}

	private void initLv(View v) {
		lv = (ListView) v.findViewById(R.id.name_lv);
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, names);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView nameTV = (TextView) view;
				String name = nameTV.getText().toString();
				if ("韩愈".equals(name)) {
					mCallback.showProByName(name);
				} else if ("柳宗元".equals(name)) {
					ContentFragment cf = (ContentFragment) getActivity()
							.getFragmentManager().findFragmentById(
									R.id.content_fg);
					cf.showPro(name);
				} else if ("苏轼".equals(name) || "苏辙".equals(name)) {
					Intent intent = new Intent("showPro");
					intent.putExtra("name", name);
					LocalBroadcastManager.getInstance(getActivity())
							.sendBroadcast(intent);
				} else if ("苏洵".equals(name)) {
					((MainActivity) getActivity()).showProByName(name);
				}
			}
		});
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity != null) {
			mCallback = (showPro) activity;
		}
	}

	public void clearData() {
		names = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, names);
		lv.setAdapter(adapter);
	}
}
