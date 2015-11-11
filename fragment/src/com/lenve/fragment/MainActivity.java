package com.lenve.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lenve.fragment.NameFragment.showPro;

public class MainActivity extends Activity implements showPro {

	private ContentFragment cf;
	private NameFragment nf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().hide();
		cf = (ContentFragment) getFragmentManager().findFragmentById(
				R.id.content_fg);
		nf = (NameFragment) getFragmentManager().findFragmentById(R.id.name_fg);
	}

	@Override
	public void showProByName(String name) {
		cf.showPro(name);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.clear_data:
			nf.clearData();
			break;

		default:
			break;
		}
	}
}
