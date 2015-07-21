package com.github.lottery;


import com.github.lottery.view.BottomManager;
import com.github.lottery.view.TitleManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.il_main);
	
//		//commons-codec.jar:������--MD5
//		DigestUtils.md5Hex("");
//		//commons-lang3-3.0-beta.jar:�ַ�������
//		//�ַ����ǿ��жϣ�null;"";"  "
//		StringUtils.isBlank("");//true
//		StringUtils.isNotBlank("");//false
//		//�ַ����滻
//		String info = "dfvNUM1ksdfgdfbhNUM2fnjlssfs";
//		info = StringUtils.replaceEach(info,new String[]{"NUM1","NUM2"},new String[]{"num","num"});
//		//�ַ���ȡ
//		info = "<body>.....</body>";
//		StringUtils.substringBetween(info, "<body>","</body>");
		
		init();
		
	}

	private void init() {
		TitleManager manager = TitleManager.getInstance();
		manager.init(this);
		manager.showUnLoginTitle();
		
		BottomManager.getInstrance().init(this);
		BottomManager.getInstrance().showCommonBottom();
	}
}
