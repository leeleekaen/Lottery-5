package com.github.lottery.engine;

import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.github.lottery.ConstantValue;
import com.github.lottery.net.HttpClientUtil;
import com.github.lottery.net.protocal.Message;
import com.github.lottery.util.DES;

public class BaseEngine {

	public Message getResult(String xml) {
		// �ڶ����͵�����

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
				//�ȶ�ͨ��
				return result;
			}
		}
		return null ;
	}
}
