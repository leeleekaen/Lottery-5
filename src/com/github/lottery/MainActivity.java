package com.github.lottery;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//commons-codec.jar:������--MD5
		DigestUtils.md5Hex("");
		//commons-lang3-3.0-beta.jar:�ַ�������
		//�ַ����ǿ��жϣ�null;"";"  "
		StringUtils.isBlank("");//true
		StringUtils.isNotBlank("");//false
		//�ַ����滻
		String info = "dfvNUM1ksdfgdfbhNUM2fnjlssfs";
		info = StringUtils.replaceEach(info,new String[]{"NUM1","NUM2"},new String[]{"num","num"});
		//�ַ���ȡ
		info = "<body>.....</body>";
		StringUtils.substringBetween(info, "<body>","</body>");
		
	}
}
