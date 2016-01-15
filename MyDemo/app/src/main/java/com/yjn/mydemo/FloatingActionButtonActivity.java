package com.yjn.mydemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yjn.mydemo.adapter.FeedAdapter;

public class FloatingActionButtonActivity extends AppCompatActivity {
	private XRecyclerView rvToDoList;
	private Toolbar mToolbar;
	private TabLayout tabs;
//	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.floating_layout);

		mToolbar = (Toolbar) findViewById(R.id.toolbar);
//		mToolbar.setTitle("sdfsdfs");
//		mToolbar.setSubtitle("fsfsf");
//		mToolbar.setLogo(R.drawable.ic_launcher);
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
				Toast.makeText(FloatingActionButtonActivity.this, item.getTitle(), Toast.LENGTH_SHORT);
				return false;
			}
		});
		setupFeed();
//		initTablayout();
//		initCollapsing();
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.menu_main, menu);
//		return true;
//	}

	private void setupFeed() {
		rvToDoList = (XRecyclerView) findViewById(R.id.rvToDoList);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
			@Override
			protected int getExtraLayoutSpace(RecyclerView.State state) {
				return 300;
			}
		};
		rvToDoList.setLayoutManager(linearLayoutManager);

		FeedAdapter feedAdapter = new FeedAdapter(this);
		rvToDoList.setAdapter(feedAdapter);
		feedAdapter.updateItems();
//		rvToDoList.setPullRefreshEnabled(true);
//		rvToDoList.setLoadingMoreEnabled(true);
//		rvToDoList.setArrowImageView(R.drawable.iconfont_downgrey);
		rvToDoList.setLaodingMoreProgressStyle(ProgressStyle.CubeTransition);
		rvToDoList.setLoadingListener(new XRecyclerView.LoadingListener() {
			@Override
			public void onRefresh() {
				//refresh data here
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						handler.sendEmptyMessage(0);
					}
				}, 3000);
			}

			@Override
			public void onLoadMore() {
				// load more data here
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						handler.sendEmptyMessage(0);
					}
				}, 3000);
			}
		});

	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			rvToDoList.loadMoreComplete();

			rvToDoList.refreshComplete();

		}
	};
	private String[] mTitle = new String[20];
	private String[] mData = new String[20];

	private void initTablayout() {
//		tabs = (TabLayout) findViewById(R.id.tabs);
//		mViewPager = (ViewPager) findViewById(R.id.viewpager);

		for (int i = 0; i < 20; i++) {
			mTitle[i] = "title" + i;
			mData[i] = "data" + i;
		}

		tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
//				mViewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});

		tabs.setTabsFromPagerAdapter(mAdapter);
		final TabLayout.TabLayoutOnPageChangeListener listener =
				new TabLayout.TabLayoutOnPageChangeListener(tabs);
//		mViewPager.addOnPageChangeListener(listener);
//		mViewPager.setAdapter(mAdapter);
	}

	//
	private PagerAdapter mAdapter = new PagerAdapter() {
		@Override
		public CharSequence getPageTitle(int position) {
			return mTitle[position];
		}

		@Override
		public int getCount() {
			return mData.length;
		}

		@Override
		public Object instantiateItem(View container, int position) {
			TextView tv = new TextView(FloatingActionButtonActivity.this);
			tv.setTextSize(30.f);
			tv.setText(mData[position]);
			((ViewPager) container).addView(tv);
			return tv;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	};

	private void initCollapsing() {
//		ActionBar actionBar = getSupportActionBar();
//		if (actionBar != null) {
////			actionBar.setDisplayHomeAsUpEnabled(true);
//		}
//		final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
//				R.id.collapsing_toolbar);
//		collapsingToolbar.setTitle("主页");
//		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.banner_3);
//
//		Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//			@Override
//			public void onGenerated(final Palette palette) {
//				int defaultColor = getResources().getColor(R.color.style_color_primary);
//				int defaultTitleColor = getResources().getColor(R.color.white);
//				int bgColor = palette.getDarkVibrantColor(defaultColor);
//				int titleColor = palette.getLightVibrantColor(defaultTitleColor);
//				collapsingToolbar.setContentScrimColor(bgColor);
//				collapsingToolbar.setCollapsedTitleTextColor(titleColor);//收缩后在Toolbar上显示时的title的颜色
//				collapsingToolbar.setExpandedTitleColor(titleColor);//扩张时候的title颜色
//			}
//		});
	}

//	scroll - 想滚动就必须设置这个。
//	enterAlways - 实现quick return效果, 当向下移动时，立即显示View（比如Toolbar)。
//	exitUntilCollapsed - 向上滚动时收缩View，但可以固定Toolbar一直在上面。
//	enterAlwaysCollapsed - 当你的View已经设置minHeight属性又使用此标志时，你的View只能以最小高度进入，只有当滚动视图到达顶部时才扩大到完整高度。
//	其中还设置了一些属性，简要说明一下：
//	contentScrim - 设置当完全CollapsingToolbarLayout折叠(收缩)后的背景颜色。
//	expandedTitleMarginStart - 设置扩张时候(还没有收缩时)title向左填充的距离。

}
