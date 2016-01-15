package com.yjn.mydemo.adapter;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionDefault;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultActionMoveToSwipedDirection;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractSwipeableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.RecyclerViewAdapterUtils;
import com.yjn.mydemo.R;
import com.yjn.mydemo.bean.swibean;

import java.util.ArrayList;

/**
 * Created by HCH on 2015/12/16.
 */
public class SwipeableItemAdapter extends RecyclerView.Adapter<SwipeableItemAdapter.MyViewHolder>
		implements com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter<SwipeableItemAdapter.MyViewHolder> {

	// NOTE: Make accessible with short name
	private interface Swipeable extends SwipeableItemConstants {
	}

	private ArrayList<swibean> list;
	private EventListener mEventListener;
	private View.OnClickListener mSwipeableViewContainerOnClickListener;
	private View.OnClickListener mUnderSwipeableViewButtonOnClickListener;

	public interface EventListener {
		void onItemPinned(int position);

		void onItemViewClicked(View v);

		void onUnderSwipeableViewButtonClicked(View v);
	}

	public static class MyViewHolder extends AbstractSwipeableItemViewHolder {
		public FrameLayout mContainer;
		public TextView mTextView;
		public Button mButton;

		public MyViewHolder(View v) {
			super(v);
			mContainer = (FrameLayout) v.findViewById(R.id.container);
			mTextView = (TextView) v.findViewById(R.id.text1);
			mButton = (Button) v.findViewById(android.R.id.button1);
		}

		@Override
		public View getSwipeableContainerView() {
			return mContainer;
		}

	}

	public SwipeableItemAdapter(ArrayList<swibean> list) {
		this.list = list;
		mSwipeableViewContainerOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onSwipeableViewContainerClick(v);
			}
		};
		mUnderSwipeableViewButtonOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onUnderSwipeableViewButtonClick(v);
			}
		};

		// SwipeableItemAdapter requires stable ID, and also
		// have to implement the getItemId() method appropriately.
		setHasStableIds(true);
	}

	private void onSwipeableViewContainerClick(View v) {
		if (mEventListener != null) {
			mEventListener.onItemViewClicked(
					RecyclerViewAdapterUtils.getParentViewHolderItemView(v));
		}
	}

	private void onUnderSwipeableViewButtonClick(View v) {
		if (mEventListener != null) {
			mEventListener.onUnderSwipeableViewButtonClicked(
					RecyclerViewAdapterUtils.getParentViewHolderItemView(v));
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		return position;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		final View v = inflater.inflate(R.layout.list_item_with_leave_behind_button, parent, false);
		return new MyViewHolder(v);
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {

		// set listeners
		// (if the item is *pinned*, click event comes to the mContainer)
		holder.mContainer.setOnClickListener(mSwipeableViewContainerOnClickListener);
		holder.mButton.setOnClickListener(mUnderSwipeableViewButtonOnClickListener);

		// set text
		holder.mTextView.setText(list.get(position).getNum() + "");

		// set background resource (target view ID: container)
//		final int swipeState = holder.getSwipeStateFlags();
//
//		if ((swipeState & Swipeable.STATE_FLAG_IS_UPDATED) != 0) {
//			int bgResId;
//
//			if ((swipeState & Swipeable.STATE_FLAG_IS_ACTIVE) != 0) {
//				bgResId = R.drawable.bg_item_swiping_active_state;
//			} else if ((swipeState & Swipeable.STATE_FLAG_SWIPING) != 0) {
//				bgResId = R.drawable.bg_item_swiping_state;
//			} else {
//				bgResId = R.drawable.bg_item_normal_state;
//			}
//
//			holder.mContainer.setBackgroundResource(bgResId);
//		}

		// set swiping properties
		holder.setMaxLeftSwipeAmount(-0.3f);
		holder.setMaxRightSwipeAmount(0);
		holder.setSwipeItemHorizontalSlideAmount(list.get(position).isPinned() ? -0.3f : 0);
	}

	@Override
	public int getItemCount() {
		return list.size();
	}

	@Override
	public int onGetSwipeReactionType(MyViewHolder holder, int position, int x, int y) {
		if (hitTest(holder.getSwipeableContainerView(), x, y)) {
			return Swipeable.REACTION_CAN_SWIPE_BOTH_H;
		} else {
			return Swipeable.REACTION_CAN_NOT_SWIPE_BOTH_H;
		}
	}

	@Override
	public void onSetSwipeBackground(MyViewHolder holder, int position, int type) {
	}

	@Override
	public SwipeResultAction onSwipeItem(MyViewHolder holder, int position, int result) {

		switch (result) {
			// swipe left --- pin
			case Swipeable.RESULT_SWIPED_LEFT:
				return new SwipeLeftResultAction(this, position);
			// other --- do nothing
			case Swipeable.RESULT_SWIPED_RIGHT:
			case Swipeable.RESULT_CANCELED:
			default:
				if (position != RecyclerView.NO_POSITION) {
					return new UnpinResultAction(this, position);
				} else {
					return null;
				}
		}
	}

	public EventListener getEventListener() {
		return mEventListener;
	}

	public void setEventListener(EventListener eventListener) {
		mEventListener = eventListener;
	}

	private static class SwipeLeftResultAction extends SwipeResultActionMoveToSwipedDirection {
		private SwipeableItemAdapter mAdapter;
		private final int mPosition;
		private boolean mSetPinned;

		SwipeLeftResultAction(SwipeableItemAdapter adapter, int position) {
			mAdapter = adapter;
			mPosition = position;
		}

		@Override
		protected void onPerformAction() {
			super.onPerformAction();
			swibean bean = mAdapter.list.get(mPosition);
			if (!bean.isPinned()) {
				bean.setIsPinned(true);
				mAdapter.notifyItemChanged(mPosition);
				mSetPinned = true;
			}
		}

		@Override
		protected void onSlideAnimationEnd() {
			super.onSlideAnimationEnd();

			if (mSetPinned && mAdapter.mEventListener != null) {
				mAdapter.mEventListener.onItemPinned(mPosition);
			}
		}

		@Override
		protected void onCleanUp() {
			super.onCleanUp();
			// clear the references
			mAdapter = null;
		}
	}

	private static class UnpinResultAction extends SwipeResultActionDefault {
		private SwipeableItemAdapter mAdapter;
		private final int mPosition;

		UnpinResultAction(SwipeableItemAdapter adapter, int position) {
			mAdapter = adapter;
			mPosition = position;
		}

		@Override
		protected void onPerformAction() {
			super.onPerformAction();
			swibean bean = mAdapter.list.get(mPosition);
			if (bean.isPinned()) {
				bean.setIsPinned(false);
				mAdapter.notifyItemChanged(mPosition);
			}
		}

		@Override
		protected void onCleanUp() {
			super.onCleanUp();
			// clear the references
			mAdapter = null;
		}
	}

	public static boolean hitTest(View v, int x, int y) {
		final int tx = (int) (ViewCompat.getTranslationX(v) + 0.5f);
		final int ty = (int) (ViewCompat.getTranslationY(v) + 0.5f);
		final int left = v.getLeft() + tx;
		final int right = v.getRight() + tx;
		final int top = v.getTop() + ty;
		final int bottom = v.getBottom() + ty;

		return (x >= left) && (x <= right) && (y >= top) && (y <= bottom);
	}

}
