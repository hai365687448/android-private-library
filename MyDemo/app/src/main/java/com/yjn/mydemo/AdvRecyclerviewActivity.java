package com.yjn.mydemo;

import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.ItemShadowDecorator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.SimpleListDividerDecorator;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;
import com.yjn.mydemo.adapter.SwipeableItemAdapter;
import com.yjn.mydemo.bean.swibean;

import java.util.ArrayList;

/**
 * Created by HCH on 2015/12/16.
 */
public class AdvRecyclerviewActivity extends AppCompatActivity {
	private RecyclerView rvToDoList;
	private Toolbar mToolbar;
	private ArrayList<swibean> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advrecyclerview_layout);

		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);//必须设置

		findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Snackbar.LENGTH_INDEFINITE  不消失
				Snackbar.make(view, "FAB", Snackbar.LENGTH_INDEFINITE).setAction("cancel", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						//这里的单击事件代表点击消除Action后的响应事件

					}
				}).show();
			}
		});

		//Toolbar的菜单的点击事件
		mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Toast.makeText(AdvRecyclerviewActivity.this, item.getTitle(), Toast.LENGTH_SHORT);
				return false;
			}
		});
		setupFeed();
	}

	private RecyclerViewTouchActionGuardManager mRecyclerViewTouchActionGuardManager;
	private RecyclerViewSwipeManager mRecyclerViewSwipeManager;
	private SwipeableItemAdapter feedAdapter;
	private RecyclerView.Adapter mWrappedAdapter;

	private void setupFeed() {
		rvToDoList = (RecyclerView) findViewById(R.id.rvToDoList);


		list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			swibean b = new swibean();
			b.setNum(i);
			b.setIsPinned(false);
			list.add(b);
		}
//		rvToDoList.setAdapter(feedAdapter);
//		feedAdapter.updateItems();
//		rvToDoList.setPullRefreshEnabled(true);
//		rvToDoList.setLoadingMoreEnabled(true);
//		rvToDoList.setArrowImageView(R.drawable.iconfont_downgrey);
//		rvToDoList.setLaodingMoreProgressStyle(ProgressStyle.CubeTransition);
//		rvToDoList.setLoadingListener(new XRecyclerView.LoadingListener() {
//			@Override
//			public void onRefresh() {
//				//refresh data here
//				handler.postDelayed(new Runnable() {
//					@Override
//					public void run() {
//						handler.sendEmptyMessage(0);
//					}
//				}, 3000);
//			}
//
//			@Override
//			public void onLoadMore() {
//				// load more data here
//				handler.postDelayed(new Runnable() {
//					@Override
//					public void run() {
//						handler.sendEmptyMessage(0);
//					}
//				}, 3000);
//			}
//		});
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

		mRecyclerViewTouchActionGuardManager = new RecyclerViewTouchActionGuardManager();
		mRecyclerViewTouchActionGuardManager.setInterceptVerticalScrollingWhileAnimationRunning(true);
		mRecyclerViewTouchActionGuardManager.setEnabled(true);

		// swipe manager
		mRecyclerViewSwipeManager = new RecyclerViewSwipeManager();

		feedAdapter = new SwipeableItemAdapter(list);
		//adapter
		feedAdapter.setEventListener(new SwipeableItemAdapter.EventListener() {
			@Override
			public void onItemPinned(int position) {
				AdvRecyclerviewActivity.this.onItemPinned(position);
			}

			@Override
			public void onItemViewClicked(View v) {
				handleOnItemViewClicked(v);
			}

			@Override
			public void onUnderSwipeableViewButtonClicked(View v) {
				handleOnUnderSwipeableViewButtonClicked(v);
			}
		});


		mWrappedAdapter = mRecyclerViewSwipeManager.createWrappedAdapter(feedAdapter);      // wrap for swiping

		final GeneralItemAnimator animator = new SwipeDismissItemAnimator();

		// Change animations are enabled by default since support-v7-recyclerview v22.
		// Disable the change animation in order to make turning back animation of swiped item works properly.
		animator.setSupportsChangeAnimations(false);

		rvToDoList.setLayoutManager(linearLayoutManager);
		rvToDoList.setAdapter(mWrappedAdapter);  // requires *wrapped* adapter
		rvToDoList.setItemAnimator(animator);

		// additional decorations
		//noinspection StatementWithEmptyBody
		if (supportsViewElevation()) {
			// Lollipop or later has native drop shadow feature. ItemShadowDecorator is not required.
		} else {
			rvToDoList.addItemDecoration(new ItemShadowDecorator((NinePatchDrawable) ContextCompat.getDrawable(this, R.drawable.material_shadow_z1)));
		}
		rvToDoList.addItemDecoration(new SimpleListDividerDecorator(ContextCompat.getDrawable(this, R.drawable.list_divider_h), true));
		// NOTE:
		// The initialization order is very important! This order determines the priority of touch event handling.
		//
		// priority: TouchActionGuard > Swipe > DragAndDrop
		mRecyclerViewTouchActionGuardManager.attachRecyclerView(rvToDoList);
		mRecyclerViewSwipeManager.attachRecyclerView(rvToDoList);
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
//			rvToDoList.loadMoreComplete();

//			rvToDoList.refreshComplete();

		}
	};

	/**
	 * This method will be called when a list item is pinned
	 *
	 * @param position The position of the item within data set
	 */
	public void onItemPinned(int position) {
	}

	private void handleOnItemViewClicked(View v) {
		int position = rvToDoList.getChildAdapterPosition(v);
		if (position != RecyclerView.NO_POSITION) {
			AdvRecyclerviewActivity.this.onItemClicked(position);
		}
	}

	/**
	 * This method will be called when a list item is clicked
	 *
	 * @param position The position of the item within data set
	 */
	public void onItemClicked(int position) {
		swibean data = list.get(position);

		if (data.isPinned()) {
			// unpin if tapped the pinned item
			data.setIsPinned(false);
			feedAdapter.notifyItemChanged(position);
		}
		String text = "Button clicked1111111111111111111111111111!";
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	private void handleOnUnderSwipeableViewButtonClicked(View v) {
		int position = rvToDoList.getChildAdapterPosition(v);
		if (position != RecyclerView.NO_POSITION) {
			AdvRecyclerviewActivity.this.onItemButtonClicked(position);
		}
	}

	/**
	 * This method will be called when a "button placed under the swipeable view" is clicked
	 *
	 * @param position The position of the item within data set
	 */
	public void onItemButtonClicked(int position) {
		String text = "Button clicked!";
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	private boolean supportsViewElevation() {
		return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
	}

	public interface EventListener {
		void onNotifyItemPinnedDialogDismissed(int position, boolean ok);
	}
}
