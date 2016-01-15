package com.yjn.mydemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.widget.RelativeLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by HCH on 2015/11/4.
 */
public class SlidingPaneActivity extends Activity {
	SlidingPaneLayout spl = null;
	private RelativeLayout leftrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slidingpane_layout);
		spl = (SlidingPaneLayout) this.findViewById(R.id.slidingpanellayout);
		leftrl = (RelativeLayout) this.findViewById(R.id.leftrl);
		spl.setSliderFadeColor(Color.TRANSPARENT);
		init();
	}

	private void init() {
		spl.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
			@Override
			public void onPanelClosed(View view) {
			}

			@Override
			public void onPanelOpened(View viw) {
			}

			@Override
			public void onPanelSlide(View drawerView, float slideOffset) {//drawerView指的是content,0-1
				View mContent = drawerView;
				View mMenu = spl.getChildAt(0);
				float scale = 1 - slideOffset;
				float rightScale = 0.8f + scale * 0.2f;
				if (mMenu.getTag().equals("LEFT")) {
					float leftScale = 1 - 0.3f * scale;
					ViewHelper.setPivotX(mMenu, 0);
					ViewHelper.setPivotY(mMenu, mMenu.getMeasuredHeight() / 2);
					ViewHelper.setScaleX(mMenu, leftScale);
					ViewHelper.setScaleY(mMenu, leftScale);
					ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
//					ViewHelper.setTranslationX(mMenu, mMenu.getMeasuredWidth() * (1 - scale));
					ViewHelper.setPivotX(mContent, 0);
					ViewHelper.setPivotY(mContent,
							mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				}
			}
		});
	}
}
