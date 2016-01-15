package com.yjn.mydemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.yjn.mydemo.adapter.FeedAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HCH on 2015/11/12.
 */
public class CollapsingToolbarLayoutActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
		setContentView(R.layout.collapsingtoolbar_layout);

		setupToolbar();

		setupViewPager();

		setupCollapsingToolbar();
	}

	private void setupCollapsingToolbar() {
		final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
				R.id.collapse_toolbar);
		collapsingToolbar.setTitleEnabled(false);

		//使用CollapsingToolbarLayout，只能又CollapsingToolbarLayout设置Title，toolbar设置的title无效
		collapsingToolbar.setTitle("主页");

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.banner_3);

		Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
			@Override
			public void onGenerated(final Palette palette) {
				int defaultColor = getResources().getColor(R.color.style_color_primary);
				int defaultTitleColor = getResources().getColor(R.color.style_color_accent);
				int bgColor = palette.getDarkVibrantColor(defaultColor);
				int titleColor = palette.getLightVibrantColor(defaultTitleColor);
				collapsingToolbar.setContentScrimColor(bgColor);
				collapsingToolbar.setCollapsedTitleTextColor(titleColor);//收缩后在Toolbar上显示时的title的颜色
				collapsingToolbar.setExpandedTitleColor(titleColor);//扩张时候的title颜色
			}
		});
	}

	private void setupViewPager() {
		final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
		setupViewPager(viewPager);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

		//必须在setAdapter之后设置
		tabLayout.setupWithViewPager(viewPager);

		//动态Tab的方法，带图片
		tabLayout.addTab(tabLayout.newTab().setText("tab1").setIcon(R.drawable.btn_qq_select));
		tabLayout.addTab(tabLayout.newTab().setText("tab2").setIcon(R.drawable.btn_qq_select));
	}

	private void setupViewPager(ViewPager viewPager) {
		ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
		adapter.addFrag(new TabFragment(), "Tab 1");
		adapter.addFrag(new TabFragment(), "Tab 2");
		adapter.addFrag(new TabFragment(), "Tab 3");

		viewPager.setAdapter(adapter);
	}

	private void setupToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);//使用toolbar必须要写
		getSupportActionBar().setTitle("TabbedCoordinatorLayout");
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(CollapsingToolbarLayoutActivity.this, "back", Toast.LENGTH_SHORT).show();
			}
		});
		toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Toast.makeText(CollapsingToolbarLayoutActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}


	static class ViewPagerAdapter extends FragmentPagerAdapter {

		private final List<Fragment> mFragmentList = new ArrayList<>();
		private final List<String> mFragmentTitleList = new ArrayList<>();

		public ViewPagerAdapter(FragmentManager manager) {
			super(manager);
		}

		@Override
		public Fragment getItem(int position) {
			return mFragmentList.get(position);
		}

		@Override
		public int getCount() {
			return mFragmentList.size();
		}

		public void addFrag(Fragment fragment, String title) {
			mFragmentList.add(fragment);
			mFragmentTitleList.add(title);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mFragmentTitleList.get(position);
		}
	}

	class TabFragment extends Fragment {

		private FeedAdapter mAdapter;

		private String mItemData = "Lorem Ipsum is simply dummy text of the printing and "
				+ "typesetting industry Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment, container, false);

			RecyclerView recyclerView = (RecyclerView) view.findViewById(
					R.id.fragment_list_rv);

			LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
				@Override
				protected int getExtraLayoutSpace(RecyclerView.State state) {
					return 300;
				}
			};
			recyclerView.setLayoutManager(linearLayoutManager);
			recyclerView.setHasFixedSize(true);


			mAdapter = new FeedAdapter(getActivity());
			recyclerView.setAdapter(mAdapter);
			mAdapter.updateItems();

			return view;
		}
	}
}
