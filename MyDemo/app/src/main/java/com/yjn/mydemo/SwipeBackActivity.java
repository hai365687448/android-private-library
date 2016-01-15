package com.yjn.mydemo;

import android.os.Bundle;

import com.liuguangqiang.swipeback.SwipeBackLayout;


/**
 * Created by HCH on 2015/11/21.
 */
public class SwipeBackActivity extends com.liuguangqiang.swipeback.SwipeBackActivity {
	private SwipeBackLayout swipeBackLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.swipe_back_layout);
		swipeBackLayout = (SwipeBackLayout) findViewById(R.id.swipe_layout);
//		swipeBackLayout.setEnableFlingBack(true);
//		swipeBackLayout.setEnablePullToBack(true);
//		swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.BOTTOM);
		setDragEdge(SwipeBackLayout.DragEdge.LEFT);
	}
}
