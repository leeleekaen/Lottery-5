package com.github.lottery.net.protocal;

import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

/**
 * �򵥵�Ҷ��
 * @author LQM
 *
 */
public class Leaf {
	//<agenterid>889931</agenterid>
	//˼·��1.���������ݣ�2�����л�Xml
	private String tagName;
	private String tagValue;
	
	//ÿ��Ҷ�Ӷ���Ҫָ����ǩ����
	public Leaf(String tagName) {
		super();
		this.tagName = tagName;
	}
	
	//������
	public Leaf(String tagName, String tagValue) {
		super();
		this.tagName = tagName;
		this.tagValue = tagValue;
	}

	
	
	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	/**
	 * ���л�Ҷ��
	 * @param serializer
	 */
	public void serializerLeaf(XmlSerializer serializer){
		
		try {
			serializer.startTag(null, tagName);
			if (tagValue==null) {
				tagValue = "";
			}
			serializer.text(tagValue);
			serializer.endTag(null, tagName);
			//System.out.println("Ҷ��");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
