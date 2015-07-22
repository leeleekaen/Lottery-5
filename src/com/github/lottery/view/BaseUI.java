package com.github.lottery.view;

import android.R.integer;
import android.content.Context;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * ͨ�ý���Ļ���
 * 
 * @author LQM
 *
 */
public abstract class BaseUI implements View.OnClickListener {

	protected Context context;

	// ��ʾ���м�����
	protected ViewGroup showInMiddle;

	public BaseUI(Context context) {
		this.context = context;

		init();

		setListener();
	}

	/**
	 * ��ʼ��
	 * 
	 * @return
	 */
	public abstract void init();

	/**
	 * ���ü���
	 * 
	 * @return
	 */
	public abstract void setListener();

	/**
	 * ��ȡ��Ҫ���м��������ص�����
	 * 
	 * @return
	 */
	public View getChild() {
		// ����layout����
		// root=null
		// showInMiddle.getLayoutParams() = null
		// root!=null
		// return root

		if (showInMiddle.getLayoutParams() == null) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT);
			showInMiddle.setLayoutParams(params);
		}
		return showInMiddle;
	}

	/**
	 * ��ȡÿ������ı�ʶ--��������ʱ�ȶԵ�����
	 * 
	 * @return
	 */
	public abstract int getID();

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
	
	public View findViewById(int id){
		return showInMiddle.findViewById(id);
		
	}
}
