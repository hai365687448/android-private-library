package com.yjn.mydemo;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by HCH on 2015/11/16.
 */
public class TextInputLayoutActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textinput_layout);

		TextInputLayout mTextInputLayoutName = (TextInputLayout) findViewById(R.id.textInput_layout_name);
		TextInputLayout mTextInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInput_layout_password);
		//mTextInputLayoutName.getEditText()返回的是它的子EditText控件，一个TextInputLayout只能包含一个EditText控件
		mTextInputLayoutName.getEditText().addTextChangedListener(new MyTextWatcher(mTextInputLayoutName, "用户名长度不能小于11位"));
		mTextInputLayoutPassword.getEditText().addTextChangedListener(new MyTextWatcher(mTextInputLayoutPassword, "密码长度不能小于11位"));

		SwitchCompat swc = (SwitchCompat)findViewById(R.id.swc);
		swc.setTrackResource(R.drawable.banner_3);
		swc.setThumbResource(R.drawable.btn_qq_select);

	}

	class MyTextWatcher implements TextWatcher {
		private TextInputLayout mTextInputLayout;
		private String errorInfo;

		public MyTextWatcher(TextInputLayout textInputLayout, String errorInfo) {
			this.mTextInputLayout = textInputLayout;
			this.errorInfo = errorInfo;
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {

		}

		@Override
		public void afterTextChanged(Editable s) {
			if (mTextInputLayout.getEditText().getText().toString().length() < 11) {
				mTextInputLayout.setErrorEnabled(true);//是否设置错误提示信息
				mTextInputLayout.setError(errorInfo);//设置错误提示信息
			} else {
				mTextInputLayout.setErrorEnabled(false);//不设置错误提示信息
			}
		}
	}

	// -------------------------------------隐藏输入法-----------------------------------------------------
	// 获取点击事件
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			View view = getCurrentFocus();
			if (isHideInput(view, ev)) {
				HideSoftInput(view.getWindowToken());
				view.clearFocus();
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	// 判定是否需要隐藏
	private boolean isHideInput(View v, MotionEvent ev) {
		if (v != null && (v instanceof EditText)) {
			int[] l = {0, 0};
			v.getLocationInWindow(l);
			int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
			if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	// 隐藏软键盘
	private void HideSoftInput(IBinder token) {
		if (token != null) {
			InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
}
