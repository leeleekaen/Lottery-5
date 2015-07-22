package com.github.lottery.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

import com.github.lottery.ConstantValue;
import com.github.lottery.R;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

/**
 * �м�����������
 * 
 * @author LQM
 *
 */
public class MiddleManager extends Observable{

	private static final String TAG = "MiddleManager";

	private static MiddleManager instance = new MiddleManager();

	private MiddleManager() {
	};

	private RelativeLayout middle;

	// K(String)Ψһ��ʶ��BaseUI����
	private Map<String, BaseUI> VIEWCACHE = new HashMap<String, BaseUI>();

	// ��ǰ������ʾ�Ľ���
	private BaseUI currentUI;

	// �û���������ʷ��¼
	private LinkedList<String> HISTORY = new LinkedList<String>();

	/**
	 * �л����� ���⣺������������������
	 * 
	 * @param ui
	 */

	public void changeUI(Class<? extends BaseUI> targetClazz) {
		// �жϵ�ǰ����չʾ�Ľ����Ŀ������Ƿ���ͬ
		if (currentUI != null && currentUI.getClass() == targetClazz) {
			return;
		}

		BaseUI targetUI = null;
		// �ж��Ƿ񴴽������������ã����򴴽�
		String key = targetClazz.getSimpleName();
		if (VIEWCACHE.containsKey(key)) {
			targetUI = VIEWCACHE.get(key);
		} else {

			try {
				Constructor<? extends BaseUI> constructor = targetClazz
						.getConstructor(Context.class);

				targetUI = constructor.newInstance(getcontext());

				VIEWCACHE.put(key, targetUI);
			} catch (Exception e) {
				throw new RuntimeException("constructor new instance error");
			}

		}

		Log.i(TAG, targetUI.toString());
		// �л�����ĺ��Ĵ���
		middle.removeAllViews();
		// FadeUtil.fadeOut(child1, 1000);
		View child = targetUI.getChild();
		middle.addView(child);
		// FadeUtil.fadeIn(child, 1000, 1000);
		child.startAnimation(AnimationUtils.loadAnimation(getcontext(),
				R.anim.ia_view_change));
		currentUI = targetUI;
		// ����ǰ����ŵ�ջ��
		HISTORY.addFirst(key);

		// ���м������л��ɹ�ʱ�������������������仯
		changeTitleAndBottom();

	}

	private void changeTitleAndBottom() {
		// 1.����һ��Ӧδ��¼�����ͨ�õ���
		// 2.�������Ӧͨ�õ������淨����

		// ��ǰ����չʾ������ǵ�һ������
		// ����һ���������⣬�ȶԵ����ݣ����� ���� �ֽ���
		// �ڽ��洦����ڣ������еĽ�������ȷ��
		// ������ֽ��룬�����еĽ��涼�Ĵ������
		// if (currentUI.getClass().getSimpleName().equals("FirstUI")) {
		// TitleManager.getInstance().showUnLoginTitle();
		// BottomManager.getInstrance().showCommonBottom();
		// }
		//
		// if (currentUI.getClass().getSimpleName().equals("SecondUI")) {
		// TitleManager.getInstance().showCommonTitle();
		// BottomManager.getInstrance().showGameBottom();
		// }

		/*// �������������ȶ�����
		switch (currentUI.getID()) {
		case ConstantValue.VIEW_FIRST:
			TitleManager.getInstance().showUnLoginTitle();
			BottomManager.getInstrance().showCommonBottom();
			break;
		case ConstantValue.VIEW_SECOND:
			TitleManager.getInstance().showCommonTitle();
			BottomManager.getInstrance().showGameBottom();
			break;

		}*/

		// ����������������϶�
		// ���м������䶯��ʱ���м�������֪ͨ�����������������Ǹñ䶯�ˣ�Ψһ�ı�ʾ���ݣ�������������Ψһ��ʾ�����������ݵ��л�
		// ֪ͨ��
		// �㲥�����Ӧ��
		// Ϊ�м������ı䶯�����˼��������۲������ģʽ

		// �ٽ��м�������ɱ��۲�Ķ���
		// �ڱ���͵ײ�������ɹ۲���
		// �۽����۲��ߺͱ��۲���֮��Ĺ�ϵ������͵ײ�������ӵ��۲��ߵ��������棩
		// ��һ���м������䶯���޸�boolean��Ȼ��֪ͨ���еĹ۲���.updata()
		
		setChanged();
		notifyObservers(currentUI.getID());

	}

	/**
	 * �л����� ���⣺���м������У�ÿ���л�û���жϵ�ǰ����չʾ����Ҫ�л���Ŀ���ǲ���ͬһ����
	 * 
	 * @param ui
	 */
	public void changeUI3(Class<? extends BaseUI> targetClazz) {
		// �жϵ�ǰ����չʾ�Ľ����Ŀ������Ƿ���ͬ
		if (currentUI != null && currentUI.getClass() == targetClazz) {
			return;
		}

		BaseUI targetUI = null;
		// �ж��Ƿ񴴽������������ã����򴴽�
		String key = targetClazz.getSimpleName();
		if (VIEWCACHE.containsKey(key)) {
			targetUI = VIEWCACHE.get(key);
		} else {

			try {
				Constructor<? extends BaseUI> constructor = targetClazz
						.getConstructor(Context.class);

				targetUI = constructor.newInstance(getcontext());

				VIEWCACHE.put(key, targetUI);
			} catch (Exception e) {
				throw new RuntimeException("constructor new instance error");
			}

		}

		Log.i(TAG, targetUI.toString());
		// �л�����ĺ��Ĵ���
		middle.removeAllViews();
		// FadeUtil.fadeOut(child1, 1000);
		View child = targetUI.getChild();
		middle.addView(child);
		// FadeUtil.fadeIn(child, 1000, 1000);
		child.startAnimation(AnimationUtils.loadAnimation(getcontext(),
				R.anim.ia_view_change));
		currentUI = targetUI;
		// ����ǰ����ŵ�ջ��
		HISTORY.addFirst(key);
	}

	/**
	 * �л����� ���⣺ÿ�ε���������µĽ���
	 * 
	 * @param ui
	 */
	public void changeUI2(Class<? extends BaseUI> targetClazz) {
		BaseUI targetUI = null;
		// �ж��Ƿ񴴽������������ã����򴴽�
		String key = targetClazz.getSimpleName();
		if (VIEWCACHE.containsKey(key)) {
			targetUI = VIEWCACHE.get(key);
		} else {

			try {
				Constructor<? extends BaseUI> constructor = targetClazz
						.getConstructor(Context.class);

				targetUI = constructor.newInstance(getcontext());

				VIEWCACHE.put(key, targetUI);
			} catch (Exception e) {
				throw new RuntimeException("constructor new instance error");
			}

		}

		Log.i(TAG, targetUI.toString());
		// �л�����ĺ��Ĵ���
		middle.removeAllViews();
		// FadeUtil.fadeOut(child1, 1000);
		View child = targetUI.getChild();
		middle.addView(child);
		// FadeUtil.fadeIn(child, 1000, 1000);
		child.startAnimation(AnimationUtils.loadAnimation(getcontext(),
				R.anim.ia_view_change));
		currentUI = targetUI;

	}

	/**
	 * �л�����
	 * 
	 * @param ui
	 */
	public void changeUI1(BaseUI ui) {
		// �л�����ĺ��Ĵ���
		middle.removeAllViews();
		// FadeUtil.fadeOut(child1, 1000);
		View child = ui.getChild();
		middle.addView(child);
		// FadeUtil.fadeIn(child, 1000, 1000);
		child.startAnimation(AnimationUtils.loadAnimation(getcontext(),
				R.anim.ia_view_change));
	}

	public Context getcontext() {
		return middle.getContext();

	}

	public static MiddleManager getInstance() {
		return instance;
	}

	public void setMiddle(RelativeLayout middle) {
		this.middle = middle;
	}

	public boolean goBack() {
		// ��¼�û�������ʷ
		// Ƶ������ջ������ӣ�
		// ��ȡջ��
		// ɾ��ջ��
		// ���򼯺�

		if (HISTORY.size() > 0) {
			// ���û�����������ؼ������˳�Ӧ��
			if (HISTORY.size() == 1) {
				return false;
			}

			// NoSuchElementException - if this LinkedList is empty.
			HISTORY.removeFirst();
			if (HISTORY.size() > 0) {
				// NoSuchElementException - if this LinkedList is empty.
				String key = HISTORY.getFirst();

				BaseUI targetUI = VIEWCACHE.get(key);
				middle.removeAllViews();
				middle.addView(targetUI.getChild());
				currentUI = targetUI;

				changeTitleAndBottom();
				return true;
			}
		}

		return false;
	}
}
