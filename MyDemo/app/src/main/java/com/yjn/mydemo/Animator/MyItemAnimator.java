package com.yjn.mydemo.Animator;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HCH on 2015/12/7.
 */
public class MyItemAnimator extends RecyclerView.ItemAnimator {
	List<RecyclerView.ViewHolder> mAnimationAddViewHolders = new ArrayList<RecyclerView.ViewHolder>();
	List<RecyclerView.ViewHolder> mAnimationRemoveViewHolders = new ArrayList<RecyclerView.ViewHolder>();

	@Override
	public boolean animateDisappearance(RecyclerView.ViewHolder viewHolder, ItemHolderInfo preLayoutInfo, ItemHolderInfo postLayoutInfo) {
		Log.e("================","animateDisappearance");
		return mAnimationRemoveViewHolders.add(viewHolder);
	}

	@Override
	public boolean animateAppearance(RecyclerView.ViewHolder viewHolder, ItemHolderInfo preLayoutInfo, ItemHolderInfo postLayoutInfo) {
		Log.e("================","animateAppearance");
		return mAnimationAddViewHolders.add(viewHolder);
	}

	@Override
	public boolean animatePersistence(RecyclerView.ViewHolder viewHolder, ItemHolderInfo preLayoutInfo, ItemHolderInfo postLayoutInfo) {
		return false;
	}

	@Override
	public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, ItemHolderInfo preLayoutInfo, ItemHolderInfo postLayoutInfo) {
		return false;
	}

	@Override
	public void runPendingAnimations() {
		Log.e("================","runPendingAnimations");
		if (!mAnimationAddViewHolders.isEmpty()) {

			AnimatorSet animator;
			View target;
			for (final RecyclerView.ViewHolder viewHolder : mAnimationAddViewHolders) {
				target = viewHolder.itemView;
				animator = new AnimatorSet();

				animator.playTogether(
						ObjectAnimator.ofFloat(target, "translationX", -target.getMeasuredWidth(), 0.0f),
						ObjectAnimator.ofFloat(target, "alpha", target.getAlpha(), 1.0f)
				);

				animator.setTarget(target);
				animator.setDuration(1000);
				animator.addListener(new Animator.AnimatorListener() {
					@Override
					public void onAnimationStart(Animator animation) {

					}

					@Override
					public void onAnimationEnd(Animator animation) {
						mAnimationAddViewHolders.remove(viewHolder);
						if (!isRunning()) {
							dispatchAnimationsFinished();
						}
					}

					@Override
					public void onAnimationCancel(Animator animation) {

					}

					@Override
					public void onAnimationRepeat(Animator animation) {

					}
				});
				animator.start();
			}
		}
		else if(!mAnimationRemoveViewHolders.isEmpty()){
		}
	}

	@Override
	public void endAnimation(RecyclerView.ViewHolder item) {

	}

	@Override
	public void endAnimations() {

	}
	@Override
	public boolean isRunning() {
		return !(mAnimationAddViewHolders.isEmpty()&&mAnimationRemoveViewHolders.isEmpty());
	}
}