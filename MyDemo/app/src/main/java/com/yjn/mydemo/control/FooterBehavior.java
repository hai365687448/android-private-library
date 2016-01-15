package com.yjn.mydemo.control;


import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HCH on 2015/12/3.
 */
public class FooterBehavior extends CoordinatorLayout.Behavior<View> {
	public FooterBehavior(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
		return dependency instanceof AppBarLayout;//当促发的dependency为AppBarLayout时
	}


	@Override
	public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
		float translationY = Math.abs(dependency.getY());
		System.out.println(dependency.getY() + "======================================================" + dependency.getTranslationY());
		child.setTranslationY(translationY);//view滚动AppBarLayout滚动的距离的绝对值
		return true;
	}
}