package com.github.lottery.view;

import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang3.StringUtils;

import com.github.lottery.ConstantValue;
import com.github.lottery.R;

import android.R.integer;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * ������������Ĺ���
 * 
 * @author LQM
 *
 */
public class TitleManager implements Observer {
	// ��ʾ������

	private static TitleManager instance = new TitleManager();

	private TitleManager() {
	}

	public static TitleManager getInstance() {
		return instance;
	}

	private RelativeLayout commonContainer;
	private RelativeLayout loginContainer;
	private RelativeLayout unLoginContainer;

	private ImageView goback;// ����
	private ImageView help;// ����
	private ImageView login;// ��¼

	private TextView titleContent;// ��������
	private TextView userInfo;// �û���Ϣ

	public void init(Activity activity) {

		commonContainer = (RelativeLayout) activity
				.findViewById(R.id.ii_common_container);
		unLoginContainer = (RelativeLayout) activity
				.findViewById(R.id.ii_unlogin_title);
		loginContainer = (RelativeLayout) activity
				.findViewById(R.id.ii_login_title);

		goback = (ImageView) activity.findViewById(R.id.ii_title_goback);
		help = (ImageView) activity.findViewById(R.id.ii_title_help);
		login = (ImageView) activity.findViewById(R.id.ii_title_login);

		titleContent = (TextView) activity.findViewById(R.id.ii_title_content);
		userInfo = (TextView) activity.findViewById(R.id.ii_top_user_info);

		setListener();
	}

	private void setListener() {

		goback.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("���ؼ�");

			}
		});
		help.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("����");

			}
		});
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("��¼");
				MiddleManager.getInstance().changeUI(SecondUI.class);
			}
		});

	}

	private void initTitle() {

		commonContainer.setVisibility(View.GONE);
		loginContainer.setVisibility(View.GONE);
		unLoginContainer.setVisibility(View.GONE);
	}

	/**
	 * ��ʾͨ�ñ���
	 */
	public void showCommonTitle() {
		initTitle();
		commonContainer.setVisibility(View.VISIBLE);
	}

	/**
	 * ��ʾδ��¼�ı���
	 */
	public void showUnLoginTitle() {
		initTitle();
		unLoginContainer.setVisibility(View.VISIBLE);
	}

	/**
	 * ��ʾ��½�ı���
	 */
	public void showLoginTitle() {
		initTitle();
		loginContainer.setVisibility(View.VISIBLE);

	}

	public void changeTitle(String title) {

		titleContent.setText(title);
	}

	@Override
	public void update(Observable observable, Object data) {
		if (data!=null&&StringUtils.isNumeric(data.toString())) {
			int id = Integer.parseInt(data.toString());
			switch (id) {
			case ConstantValue.VIEW_FIRST:
			case ConstantValue.VIEW_HALL:
				showUnLoginTitle();
				break;
			case ConstantValue.VIEW_SECOND:
				showCommonTitle();
				break;

			}
		}
		
	}

}
