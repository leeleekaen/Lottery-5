package com.github.lottery.util;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * ���뵭�����л�
 * 
 * @author LQM
 *
 */
public class FadeUtil {

	/**
	 * ��ǰ����չʾ�Ľ��浭��������ִ��ʱ�� �����ִ�й����У��ڶ������洦�ڵȴ�״̬ �ڶ������浭�룬����ִ��ʱ��
	 */

	private static Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			View view = (View) msg.obj;
			ViewGroup parent = (ViewGroup) view.getParent();
			parent.removeView(view);
		}

	};

	public static void fadeOut(final View view, long duration) {

		AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
		alphaAnimation.setDuration(duration);

		// ����ִ�����Ժ�ɾ��view
		// ����d����ִ����ɺ�ļ���
		alphaAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {

				// ViewGroup parent = (ViewGroup) view.getParent();
				// parent.removeView(view);

				Message msg = Message.obtain();
				msg.obj = view;
				handler.sendMessage(msg);

			}
		});

		view.startAnimation(alphaAnimation);
	}

	public static void fadeIn(View view, long delay, long duration) {

		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setStartOffset(delay);
		alphaAnimation.setDuration(duration);
		view.startAnimation(alphaAnimation);
	}
}
