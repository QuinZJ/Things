package com.things.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.things.R;
import com.things.Spider.Spider;

public class MainActivity extends AppCompatActivity {

	public static final String TAG_BAIDU = "baidu";


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Spider().book("doupocangqiong");
	}


}
