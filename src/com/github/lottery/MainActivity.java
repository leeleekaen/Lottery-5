package com.github.lottery;

import com.github.lottery.util.FadeUtil;
import com.github.lottery.util.PromptManager;
import com.github.lottery.view.BaseUI;
import com.github.lottery.view.BottomManager;
import com.github.lottery.view.FirstUI;
import com.github.lottery.view.Hall;
import com.github.lottery.view.MiddleManager;
import com.github.lottery.view.SecondUI;
import com.github.lottery.view.TitleManager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	// �м�ռλ����
	private RelativeLayout middle;
	// ��һ������
	private View child1;

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			//changeUI();
			changeUI(new SecondUI(MainActivity.this));
			super.handleMessage(msg);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.il_main);

		// //commons-codec.jar:������--MD5
		// DigestUtils.md5Hex("");
		// //commons-lang3-3.0-beta.jar:�ַ�������
		// //�ַ����ǿ��жϣ�null;"";"  "
		// StringUtils.isBlank("");//true
		// StringUtils.isNotBlank("");//false
		// //�ַ����滻
		// String info = "dfvNUM1ksdfgdfbhNUM2fnjlssfs";
		// info = StringUtils.replaceEach(info,new String[]{"NUM1","NUM2"},new
		// String[]{"num","num"});
		// //�ַ���ȡ
		// info = "<body>.....</body>";
		// StringUtils.substringBetween(info, "<body>","</body>");

		init();

	}

	private void init() {
		TitleManager manager = TitleManager.getInstance();
		manager.init(this);
		manager.showUnLoginTitle();

		BottomManager.getInstrance().init(this);
		BottomManager.getInstrance().showCommonBottom();

		middle = (RelativeLayout) findViewById(R.id.ii_middle);
		MiddleManager.getInstance().setMiddle(middle);
		//loadaFirstUI();
		//MiddleManager.getInstance().changeUI(FirstUI.class);
		MiddleManager.getInstance().changeUI(Hall.class);
		
		// �����۲��ߺͱ��۲���֮��Ĺ�ϵ������͵ײ�������ӵ��۲��ߵ��������棩
		MiddleManager.getInstance().addObserver(TitleManager.getInstance());
		MiddleManager.getInstance().addObserver(BottomManager.getInstrance());

		// ����һ����������������Ժ󣬵ڶ���������ʾ
		//handler.sendEmptyMessageDelayed(0, 2000);
	}

	private void loadaFirstUI() {
		FirstUI firstUI = new FirstUI(this);
		child1 = firstUI.getChild();
		middle.addView(child1);
	}

	protected void loadSecondUI() {
		SecondUI secondUI = new SecondUI(this);
		View child = secondUI.getChild();
		middle.addView(child);
		FadeUtil.fadeIn(child, 1000, 1000);

		// ִ���л�����
		// child.startAnimation(AnimationUtils.loadAnimation(this,
		// R.anim.ia_view_change));
	}

	/**
	 * �л�����
	 * 
	 * @param ui
	 */
	protected void changeUI(BaseUI ui) {
		//�л�����ĺ��Ĵ���
		middle.removeAllViews();
		//FadeUtil.fadeOut(child1, 1000);
		View child = ui.getChild();
		middle.addView(child);
		//FadeUtil.fadeIn(child, 1000, 1000);
		child.startAnimation(AnimationUtils.loadAnimation(this,
				R.anim.ia_view_change));
	}

	protected void changeUI() {
		// �л�����ʱ������һ����ʾ����
		// middle.removeAllViews();
		// middle.removeView(child1);
		FadeUtil.fadeOut(child1, 1000);
		loadSecondUI();

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			boolean reuslt = MiddleManager.getInstance().goBack();
			//���ؼ�����ʧ��
			if (!reuslt) {
			//	Toast.makeText(this, "ȷ��Ҫ�˳���", 0).show();
			PromptManager.showExitSystem(this);
			}
			return false;
		}
		
		return super.onKeyDown(keyCode, event);
	}
}
