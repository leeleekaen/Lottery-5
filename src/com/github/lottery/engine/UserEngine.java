package com.github.lottery.engine;

import com.github.lottery.bean.User;
import com.github.lottery.net.protocal.Message;

/**
 * �û���ص�ҵ������ӿ�
 * @author LQM
 *
 */
public interface UserEngine {

	/**
	 * �û���¼
	 * @param user
	 * @return
	 */
	Message login(User user);
	
}
