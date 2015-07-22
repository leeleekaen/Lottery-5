package com.github.lottery.view;

import com.github.lottery.ConstantValue;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class FirstUI extends BaseUI {


	public FirstUI(Context context) {
		super(context);
	}
	
	/**
	 * ��ȡ��Ҫ���м��������صĿؼ�
	 * @return
	 */
	public View getChild(){
		//�򵥽���
		TextView textView = new TextView(context);
		
		LayoutParams layoutParams = textView.getLayoutParams();
		layoutParams = new LayoutParams(layoutParams.MATCH_PARENT, layoutParams.MATCH_PARENT);
		textView.setLayoutParams(layoutParams);
		
		textView.setBackgroundColor(Color.BLUE);
		textView.setText("���ǵ�һ���򵥵Ľ���");
		return textView;
		
	}

	@Override
	public int getID() {
		
		return ConstantValue.VIEW_FIRST;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		
	}
	
}
