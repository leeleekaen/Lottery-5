package com.github.lottery.net;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

public class NetUtils {

	/**
	 * ����û����磺�Ƿ�����
	 */
	public static boolean checkNet(Context context) {
		// �жϣ�WIFI����
		boolean isWIF = isWIFIConnection(context);

		// �жϣ�mobile����
		boolean isMOBILE = isMOBILEConnection(context);

		// �����Mobile�����ĸ�APN
		if (isMOBILE) {
			// �ж����ĸ�APN��ѡ����
			readAPN(context);
		}

		if (!isMOBILE && !isWIF) {
			return false;
		}

		return true;
	}

	/**
	 * APN��ѡ�У���ȡ����Ĵ�����Ϣ���еĻ�����wap����
	 * 
	 * @param context
	 */
	private static void readAPN(Context context) {
		// 4.0ģ�������ε���Ȩ��
		Uri PREFERRED_APN_URI = Uri
				.parse("content://telephony/carriers/preferapn");

		// �������ϵ������
		ContentResolver resolver = context.getContentResolver();
		// �ж��ĸ�APN��ѡ����
		Cursor cursor = resolver.query(PREFERRED_APN_URI, null, null, null, null);

		if (cursor.moveToFirst()) {
			String proxy = cursor.getString(cursor.getColumnIndex("Proxy"));
			int port = cursor.getInt(cursor.getColumnIndex("Port"));
			System.out.println(proxy);
		}
		
	}

	/**
	 * �ж��Ƿ��ƶ���������
	 * 
	 * @param context
	 * @return
	 */
	private static boolean isMOBILEConnection(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (networkInfo != null) {
			return networkInfo.isConnected();
		}

		return false;
	}

	/**
	 * �ж��Ƿ�wifi����
	 * 
	 * @param context
	 * @return
	 */
	private static boolean isWIFIConnection(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (networkInfo != null) {
			return networkInfo.isConnected();
		}

		return false;
	}

}
