package com.things.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.things.Net.NetRequest;
import com.things.Net.OnResponse;
import com.things.R;
import com.things.Spider.HTMLGetter.HTMLGetter;

public class MainActivity extends AppCompatActivity {

	public static final String TAG_BAIDU = "baidu";


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new HTMLGetter("tg", this)
				.open(NetRequest.METHOD_POST, "http://192.168.1.101:8080/transportservice/type/jason/action/GetCarSpeed.do")
				.setPostData("{\"CarId\" : 0}")
				.send();
	}

	@OnResponse("tg")
	private void func(HTMLGetter getter) {
		Log.e("ff", getter.getHtml());
	}

}
