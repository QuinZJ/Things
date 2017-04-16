package com.things.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.things.R;
import com.things.Spider.Spider;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Spider().tag("zhiyinmanke");
	}


}
