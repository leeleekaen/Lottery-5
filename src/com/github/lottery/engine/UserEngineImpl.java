package com.github.lottery.engine;

import java.io.InputStream;
import java.io.StringReader;

import org.apache.commons.codec.digest.DigestUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.R.integer;
import android.util.Xml;

import com.github.lottery.ConstantValue;
import com.github.lottery.bean.User;
import com.github.lottery.net.HttpClientUtil;
import com.github.lottery.net.UserLoginElement;
import com.github.lottery.net.protocal.Message;
import com.github.lottery.util.DES;

public class UserEngineImpl extends BaseEngine implements UserEngine{

	public Message login(User user) {

		// ��һ������ȡ����¼�õ�xml
		// ������¼��Element
		UserLoginElement element = new UserLoginElement();
		// �����û�����
		element.getActpassword().setTagValue(user.getPassword());
		// Message.getXml(element)
		Message message = new Message();
		message.getHeader().getUsername().setTagValue(user.getUsername());
		String xml = message.getXml(element);

		// ����������ȶ�ͨ��result�����򷵻�null
		Message result = getResult(xml);

		// ���Ĳ��������������ݴ���
		// body���ֵĵڶ��ν���������������������

		XmlPullParser parser = Xml.newPullParser();
		try {

			// body���ģ�����+����DES��
			DES des = new DES();
			String body = "<body>"
					+ des.authcode(result.getBody()
							.getServiceBodyInsideDESinfo(), "ENCODE",
							ConstantValue.DES_PASSWORD) + "</body>";

			parser.setInput(new StringReader(body));

			int eventType = parser.getEventType();
			String name;

			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					name = parser.getName();
					if ("errorcode".equals(name)) {
						result.getBody().getOelement()
								.setErrorcode(parser.nextText());
					}
					if ("errormsg".equals(name)) {
						result.getBody().getOelement()
								.setErrormsg(parser.nextText());

					}
					break;
				}
				eventType = parser.next();
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �û���¼
	 * 
	 * @param user
	 */
	public Message login1(User user) {
		// ��һ������ȡ����¼�õ�xml
		// ������¼��Element
		UserLoginElement element = new UserLoginElement();
		// �����û�����
		element.getActpassword().setTagValue(user.getPassword());
		// Message.getXml(element)
		Message message = new Message();
		message.getHeader().getUsername().setTagValue(user.getUsername());
		String xml = message.getXml(element);

		// �ڶ���(���벻��)������xml���������ˣ��ȴ��ظ�
		// HttpClientUtil.sendXml
		// �����д���ǰ��û���ж��������ͣ�
		HttpClientUtil util = new HttpClientUtil();
		InputStream is = util.sendXml(ConstantValue.LOTTERY_URI, xml);
		// �ж��������ǿ�
		if (is != null) {
			Message result = new Message();
			// ������(���벻��)�����ݵ�У�飨MD5����У�飩
			// timestamp+digest+body

			XmlPullParser parser = Xml.newPullParser();
			try {
				parser.setInput(is, ConstantValue.ENCONDING);
				int eventType = parser.getEventType();
				String name;
				while (eventType != XmlPullParser.END_DOCUMENT) {
					switch (eventType) {
					case XmlPullParser.START_TAG:
						name = parser.getName();
						if ("timestamp".equals(name)) {
							result.getHeader().getTimestamp()
									.setTagValue(parser.nextText());
						}
						if ("digest".equals(name)) {
							result.getHeader().getDigest()
									.setTagValue(parser.nextText());

						}
						if ("body".equals(name)) {
							result.getBody().setServiceBodyInsideDESinfo(
									parser.nextText());

						}
						break;
					}
					eventType = parser.next();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			// ԭʼ���ݻ�ԭ��ʱ�����������+���루������+body���ģ�����+����DES��
			// body���ģ�����+����DES��
			DES des = new DES();
			String body = "<body>"
					+ des.authcode(result.getBody()
							.getServiceBodyInsideDESinfo(), "ENCODE",
							ConstantValue.DES_PASSWORD) + "</body>";
			String orgInfo = result.getHeader().getTimestamp().getTagValue()
					+ ConstantValue.DES_PASSWORD + body;

			// ���ù��������ֻ��˵�MD5
			String md5Hex = DigestUtils.md5Hex(orgInfo);

			// ���ֻ�����������Ľ��бȶ�
			if (md5Hex.equals(result.getHeader().getDigest().getTagValue())) {

				// ���Ĳ��������������ݴ���
				// body���ֵĵڶ��ν���������������������

				parser = Xml.newPullParser();
				try {
					parser.setInput(new StringReader(body));

					int eventType = parser.getEventType();
					String name;

					while (eventType != XmlPullParser.END_DOCUMENT) {
						switch (eventType) {
						case XmlPullParser.START_TAG:
							name = parser.getName();
							if ("errorcode".equals(name)) {
								result.getBody().getOelement()
										.setErrorcode(parser.nextText());
							}
							if ("errormsg".equals(name)) {
								result.getBody().getOelement()
										.setErrormsg(parser.nextText());

							}
							break;
						}
						eventType = parser.next();
					}
					return result;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return null;
	}
}
