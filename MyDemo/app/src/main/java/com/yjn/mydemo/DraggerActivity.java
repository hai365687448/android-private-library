package com.yjn.mydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.ppamorim.dragger.DraggerCallback;
import com.github.ppamorim.dragger.DraggerPosition;
import com.github.ppamorim.dragger.DraggerView;

/**
 * Created by HCH on 2015/11/21.
 */
public class DraggerActivity extends AppCompatActivity {
	private DraggerView draggerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		overridePendingTransition(0, 0);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dragger_layout);
		draggerView = (DraggerView) findViewById(R.id.dragger_view);

		draggerView.setDraggerPosition(DraggerPosition.LEFT);//方向
		//滑动的监听
		draggerView.setDraggerCallback(new DraggerCallback() {
			@Override
			public void onProgress(double progress) {
				System.out.println("==================" + progress);
			}

			@Override
			public void notifyOpen() {
				System.out.println("=======notifyOpen=========");
			}

			@Override
			public void notifyClose() {
				System.out.println("=======notifyClose=========");
			}
		});
		//阀值
		draggerView.setDraggerLimit(0.5f);
		//是否能滑动返回
		draggerView.setSlideEnabled(false);
	}

	@Override protected void onPause() {
		overridePendingTransition(0, 0);
		super.onPause();
	}
}
