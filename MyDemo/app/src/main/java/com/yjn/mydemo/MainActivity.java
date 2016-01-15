package com.yjn.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	TextView btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11;
	LinearLayout rootlayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

	}


	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
			case R.id.btn1:
				intent = new Intent(MainActivity.this, AnimationActivity.class);
				startActivity(intent);
				break;
			case R.id.btn2:
				intent = new Intent(MainActivity.this, DrawerLayoutActivity.class);
				startActivity(intent);
				break;
			case R.id.btn3:
				intent = new Intent(MainActivity.this, PercentActivity.class);
				startActivity(intent);
				break;
			case R.id.btn4:
				intent = new Intent(MainActivity.this, SlidingPaneActivity.class);
				startActivity(intent);
				break;
			case R.id.btn5:
				intent = new Intent(MainActivity.this, FloatingActionButtonActivity.class);
				startActivity(intent);
				break;
			case R.id.btn6:
				intent = new Intent(MainActivity.this, CollapsingToolbarLayoutActivity.class);
				startActivity(intent);
				break;
			case R.id.btn7:
				intent = new Intent(MainActivity.this, TextInputLayoutActivity.class);
				startActivity(intent);
				break;
			case R.id.btn8:
				intent = new Intent(MainActivity.this, SlidrActivity.class);
				startActivity(intent);
				break;
			case R.id.btn9:
				intent = new Intent(MainActivity.this, SwipeBackActivity.class);
				startActivity(intent);
				break;
			case R.id.btn10:
				intent = new Intent(MainActivity.this, DraggerActivity.class);
				startActivity(intent);
				break;
			case R.id.btn11:
				intent = new Intent(MainActivity.this, AdvRecyclerviewActivity.class);
				startActivity(intent);
				break;
		}
	}

	private void initView() {
		btn1 = (TextView) findViewById(R.id.btn1);
		btn2 = (TextView) findViewById(R.id.btn2);
		btn3 = (TextView) findViewById(R.id.btn3);
		btn4 = (TextView) findViewById(R.id.btn4);
		btn5 = (TextView) findViewById(R.id.btn5);
		btn6 = (TextView) findViewById(R.id.btn6);
		btn7 = (TextView) findViewById(R.id.btn7);
		btn8 = (TextView) findViewById(R.id.btn8);
		btn9 = (TextView) findViewById(R.id.btn9);
		btn10 = (TextView) findViewById(R.id.btn10);
		btn11 = (TextView) findViewById(R.id.btn11);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btn10.setOnClickListener(this);
		btn11.setOnClickListener(this);
	}
}
