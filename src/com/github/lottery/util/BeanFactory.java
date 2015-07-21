package com.github.lottery.util;

import java.io.IOException;
import java.util.Properties;

import com.github.lottery.engine.UserEngine;

/**
 * ������
 * @author LQM
 *
 */
public class BeanFactory {
	//���������ļ�����ʵ��
	
	private static Properties properties;
	static{
		properties = new Properties();
		//bean.properties������srcĿ¼��
		try {
			properties.load(BeanFactory.class.getResourceAsStream("bean.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ������Ҫ��ʵ����
	 * @param clazz
	 * @return
	 */
	public static<T> T getImpl(Class<T> clazz){
		
		String key = clazz.getSimpleName();
		String className = properties.getProperty(key);
		try {
			return (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
