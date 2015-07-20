package com.github.lottery.net.protocal;

import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.github.lottery.ConstantValue;

/**
 * ��װЭ��
 * @author LQM
 *
 */
public class Message {

	private Header header = new Header();
	private Body body = new Body();
	

	/**
	 * ���л�Э��
	 */
	public void serializerMessage(XmlSerializer serializer) {

		try {
			//<message version = "1.0">
			serializer.startTag(null, "message");
			// MUST follow a call to startTag() immediately
			serializer.attribute(null, "version", "1.0");
			header.serializerHeader(serializer, body.getWholeBody());//��ȡ������body
			//body.serializerBody(serializer);
			serializer.startTag(null, "body");
			serializer.text(body.getBodyInsideDesInfo());
			serializer.endTag(null, "body");
			serializer.endTag(null, "message");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * ��ȡ�����Xml�ļ�
	 * @return
	 */
	public String getXml(Element element){
		if (element == null) {
			throw new IllegalArgumentException("element is null");
		}
		
		//��Ҫ���������ʶ����������
		header.getTransactiontype().setTagValue(element.getTransactionType());
		body.getElements().add(element);
		
		// ���л�
				XmlSerializer serializer = Xml.newSerializer();
				StringWriter writer = new StringWriter();
				try {
					serializer.setOutput(writer);
					// This method can only be called just after setOutput
					serializer.startDocument(ConstantValue.ENCONDING, null);
					
					this.serializerMessage(serializer);

					serializer.endDocument();
					return writer.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
	}
	
}
