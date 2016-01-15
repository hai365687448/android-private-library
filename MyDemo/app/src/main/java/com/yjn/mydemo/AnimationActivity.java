package com.yjn.mydemo;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HCH on 2015/11/5.
 */
public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

	TextView text;
	TextView text2;
	TextView text3;
	LinearLayout rootlayout;
	private GridLayout mGridLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_layout);
		initView();
		//初始化
//        Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
//        alphaAnimation.setDuration(3000);
//        rootlayout.startAnimation(alphaAnimation);
//        Animation rotateAnimation = new RotateAnimation(0f, 360f);
//        rotateAnimation.setDuration(3000);
//        rootlayout.startAnimation(rotateAnimation);

		//初始化
//        Animation scaleAnimation = new ScaleAnimation(0.1f, 0.5f, 0.1f, 1.5f);
////设置动画时间
//        scaleAnimation.setDuration(5000);
//        scaleAnimation.setFillAfter(true);
//        scaleAnimation.setInterpolator(new AccelerateInterpolator());
//        rootlayout.startAnimation(scaleAnimation);

//        Animation rotateAnimation = new RotateAnimation(0f, 360f);
//        rotateAnimation.setDuration(1000);
//        text.startAnimation(rotateAnimation);

		//初始化
//        Animation translateAnimation = new TranslateAnimation(50.1f, 100.0f, 0.1f, 100.0f);
////设置动画时间
//        translateAnimation.setDuration(3000);
//        translateAnimation.setFillAfter(true);
//        rootlayout.startAnimation(translateAnimation);

//        ValueAnimator colorAnim = ObjectAnimator.ofInt(text, "backgroundColor", /*Red*/0xFFFF8080, /*Blue*/0xFF8080FF);
//		colorAnim.setDuration(3000);
//		colorAnim.setEvaluator(new ArgbEvaluator());
//		colorAnim.setInterpolator(new LinearInterpolator());
//		colorAnim.setRepeatCount(ValueAnimator.INFINITE);
//		colorAnim.setRepeatMode(ValueAnimator.REVERSE);
//		colorAnim.start();

//        AnimatorSet set = new AnimatorSet();
//        set.playTogether(
//                ObjectAnimator.ofFloat(text, "rotationX", 0, 360),
//                ObjectAnimator.ofFloat(text, "rotationY", 0, 180),
//                ObjectAnimator.ofFloat(text, "rotation", 0, -90),
//                ObjectAnimator.ofFloat(text, "translationX", 0, 90),
//                ObjectAnimator.ofFloat(text, "translationY", 0, 90),
//                ObjectAnimator.ofFloat(text, "scaleX", 1, 1.5f),
//                ObjectAnimator.ofFloat(text, "scaleY", 1, 0.5f)
//                ObjectAnimator.ofFloat(text, "alpha", 1, 0.25f)
//        );
//        set.setDuration(5 * 1000).start();

		text2.setVisibility(View.GONE);

		// 创建一个GridLayout
//        mGridLayout = new GridLayout(this);
		// 设置每列5个按钮
//        mGridLayout.setColumnCount(5);
		// 添加到布局中
//        rootlayout.addView(mGridLayout);

		//默认动画全部开启
//        ValueAnimator colorAnim = ObjectAnimator.ofInt(text2, "backgroundColor", /*Red*/0xFFFF8080, /*Blue*/0xFF8080FF);
		PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 0f, 300f);
		PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 0f, 300f);
		ValueAnimator colorAnim = ObjectAnimator.ofPropertyValuesHolder(pvhX, pvhX, pvhY);
		colorAnim.setTarget(text);
		colorAnim.setDuration(2000);
		colorAnim.start();
//        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(text2, "rotation", 0, -90);
//        LayoutTransition mTransition = new LayoutTransition();
//        mTransition.setAnimator(LayoutTransition.APPEARING, mTransition.getAnimator(LayoutTransition.APPEARING));
//        mTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, mTransition.getAnimator(LayoutTransition.CHANGE_APPEARING));
//        mTransition.setAnimator(LayoutTransition.DISAPPEARING, mTransition.getAnimator(LayoutTransition.DISAPPEARING));
//        mTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, mTransition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING));

//        mTransition.setAnimator(LayoutTransition.APPEARING, colorAnim);
//        mTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, null);
//        mTransition.setAnimator(LayoutTransition.DISAPPEARING, scaleAnimator);
//        mTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, null);

//        rootlayout.setLayoutTransition(mTransition);
//        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(text, "translationY", 0, 300);
//        LayoutTransition layoutTransition = new LayoutTransition();
//        layoutTransition.setDuration(5000);
//        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, scaleAnimator);
//        layoutTransition.setStagger(LayoutTransition.CHANGE_APPEARING, 300);
//        rootlayout.setLayoutTransition(layoutTransition);

		text.setOnClickListener(this);

		//通用方法
		//setDuration设置动画时间
		//setFillAfter如果为true，则动画执行之后停留在执行之后的位置
		//setFillBefore如果为true，则动画执行之后回到动画执行之前的位置
		//setStartOffset动画执行之前的等待时间
		//setRepeatCount动画重复执行的次数

		//插值器
		//AccelerateDecelerateInterpolator开始与结束的地方速率改变比较慢，在中间的时候加速
		//AccelerateInterpolator开始的地方速率改变比较慢，然后开始加速
		//AnticipateInterpolator开始的时候向后然后向前甩
		//AnticipateOvershootInterpolator开始的时候向后然后向前甩一定值后返回最后的值
		//BounceInterpolator动画结束的时候弹起
		//CycleInterpolator循环播放特定的次数，速率改变沿着正弦曲线
		//DecelerateInterpolator在开始的地方快然后慢
		//LinearInterpolator以常量速率改变
		//OvershootInterpolator向前甩一定值后再回到原来位置

	}


	@Override
	public void onClick(View v) {
		Toast.makeText(AnimationActivity.this, "dianji", Toast.LENGTH_SHORT).show();
//        final Button button = new Button(this);
//        button.setText("哈哈");
//        button.setWidth(200);
//        rootlayout.addView(button, Math.min(1, rootlayout.getChildCount()));
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                rootlayout.removeView(button);
//            }
//        });
		text3.measure(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		final int targetHeight = text3.getMeasuredWidth();
		text3.getLayoutParams().width = 0;
		ValueAnimator animation = ValueAnimator.ofFloat(1f, 3f);
		animation.setDuration(500);
		animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				Float a = Float.parseFloat(animation.getAnimatedValue().toString());
				text3.getLayoutParams().width = (int) (targetHeight * a);
				text3.requestLayout();
			}
		});
		animation.start();
	}

	private void initView() {
		text = (TextView) findViewById(R.id.text);
		text2 = (TextView) findViewById(R.id.text2);
		text3 = (TextView) findViewById(R.id.text3);
		rootlayout = (LinearLayout) findViewById(R.id.root_layout);
	}
}
