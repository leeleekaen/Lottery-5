package com.github.lottery.net.protocal;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlSerializer;

import com.github.lottery.ConstantValue;
import com.github.lottery.util.DES;

import android.util.Xml;

/**
 * ��Ϣ��ڵ��װ
 * @author LQM
 *
 */
public class Body {
	 
	private List<Element> elements = new ArrayList<Element>();
	
	/********************  ����������ظ�   ***************************/
	private String serviceBodyInsideDESinfo;//����˻ظ���body��des���ܵ���Ϣ
	private Oelement oelement = new Oelement();
	
	
	public Oelement getOelement() {
		return oelement;
	}

	public String getServiceBodyInsideDESinfo() {
		return serviceBodyInsideDESinfo;
	}

	public void setServiceBodyInsideDESinfo(String serviceBodyInsideDESinfo) {
		this.serviceBodyInsideDESinfo = serviceBodyInsideDESinfo;
	}

	/********************  ����������ظ�   ***************************/
	

	public List<Element> getElements() {
		return elements;
	}

	/**
	 * ���л�����
	 */
	public void serializerBody(XmlSerializer serializer) {

		/**
		 * <body>
		            <elements>
		                  <element>
		                         <lotteryid>118</lotteryid>
		                         <issues>1</issues>
		                  </element>
		            </elements> 
		     </body>
		 */
		try {
			serializer.startTag(null, "body");
			serializer.startTag(null, "elements");
			for (Element item : elements) {
				item.serializerElement(serializer);
			}
			serializer.endTag(null, "elements");
			serializer.endTag(null, "body");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * ��ȡ����body
	 * @return
	 */
	public String getWholeBody(){
		
		StringWriter writer = new StringWriter();
		XmlSerializer temp = Xml.newSerializer();
		try {
			temp.setOutput(writer);
			this.serializerBody(temp);
			//serializer.endDocument()������
			//All unclosed start tags will be closed and output will be flushed
			temp.flush();
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getBodyInsideDesInfo(){
		//��������
		String wholeBody = getWholeBody();
		String orgDesInfo = StringUtils.substringBetween(wholeBody, "<body>",  "</body>");

		DES des = new DES();
		return des.authcode(orgDesInfo, "DECODE", ConstantValue.DES_PASSWORD);
	}
	
}
