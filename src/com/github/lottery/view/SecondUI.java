package com.github.lottery.view;

import com.github.lottery.ConstantValue;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class SecondUI extends BaseUI {

	private TextView textView;

	public SecondUI(Context context)  {
		super(context);
	}
	
	/**
	 * ��ʼ��������һ��
	 */
	public void init(){
		
		//�ڶ�������
				textView = new TextView(context);
				
				LayoutParams layoutParams = textView.getLayoutParams();
				layoutParams = new LayoutParams(layoutParams.MATCH_PARENT, layoutParams.MATCH_PARENT);
				textView.setLayoutParams(layoutParams);
				
				textView.setBackgroundColor(Color.RED);
				textView.setText("���ǵڶ����Ľ���");
				
	}
	
	/**
	 * ��ȡ��Ҫ���м��������صĿؼ�
	 * @return
	 */
	public View getChild(){
		
		return textView;
	}

	@Override
	public int getID() {
		
		return ConstantValue.VIEW_SECOND;
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		
	}
	
}
