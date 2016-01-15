package com.yjn.mydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.r0adkll.slidr.Slidr;


/**
 * Created by HCH on 2015/11/19.
 */
public class SlidrActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.percen_layout);
		Slidr.attach(this);
		int primary = getResources().getColor(R.color.yellow);
		int secondary = getResources().getColor(R.color.red);
//		Slidr.attach(this, primary, secondary);

//		SlidrConfig config = new SlidrConfig.Builder()
//				.primaryColor(primary)
//				.secondaryColor(secondary)
//				.position(SlidrPosition.LEFT)
//				.sensitivity(1f)
//				.scrimColor(Color.BLACK)
//				.scrimStartAlpha(0.8f)
//				.scrimEndAlpha(0f)
//				.velocityThreshold(2400)
//				.distanceThreshold(0.30f)
//				.edge(true)
//				.edgeSize(0.18f) // The % of the screen that counts as the edge, default 18%
//				.listener(new SlidrListener() {
//					@Override
//					public void onSlideStateChanged(int state) {
//
//					}
//
//					@Override
//					public void onSlideChange(float percent) {
//						System.out.println("============onSlideChange============="+percent);
//					}
//
//					@Override
//					public void onSlideOpened() {
//						System.out.println("============onSlideOpened=============");
//					}
//
//					@Override
//					public void onSlideClosed() {
//						System.out.println("============onSlideClosed=============");
//					}
//				})
//				.build();
//		Slidr.attach(this, config);
	}
}
//SlidrConfig config = new SlidrConfig.Builder()
//		.primaryColor(getResources().getColor(R.color.primary)
//				.secondaryColor(getResources().getColor(R.color.secondary)
//						.position(SlidrPosition.LEFT|RIGHT|TOP|BOTTOM|VERTICAL|HORIZONTAL)
//						.sensitivity(1f)
//						.scrimColor(Color.BLACK)
//						.scrimStartAlpha(0.8f)
//						.scrimEndAlpha(0f)
//						.velocityThreshold(2400)
//						.distanceThreshold(0.25f)
//						.edge(true|false)
//						.edgeSize(0.18f) // The % of the screen that counts as the edge, default 18%
//						.listener(new SlidrListener(){...})
//						.build();
//
//Slidr.attach(this, config);