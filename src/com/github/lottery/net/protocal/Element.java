package com.github.lottery.net.protocal;


import org.xmlpull.v1.XmlSerializer;

/**
 * �������ݵķ�װ
 * 
 * @author LQM
 *
 */
public abstract class Element {
	/**
	 * Element��Ϊ�������֣�����������Ҫ�Լ�ʵ��
	 * 1.���л��Լ�
	 * 2.���Լ��ı�ʶ
	 */
	//���л��Լ�
	public abstract void serializerElement(XmlSerializer serializer);
	
	//���Լ��ı�ʶ
	public abstract String getTransactionType();
	
	/**
	 * ��������
	 * ���л�
	 * ���У������ʶ
	 */

//	// <lotteryid>118</lotteryid>
//	private Leaf lotteryid = new Leaf("lotteryid");
//
//	// <issues>1</issues>
//	private Leaf issues = new Leaf("issues", "1");
//
//	public Leaf getLotteryid() {
//		return lotteryid;
//	}

//	/**
//	 * ���л�����
//	 */
//	public void serializerElement(XmlSerializer serializer) {
//
//		try {
//			serializer.startTag(null, "element");
//			lotteryid.serializerLeaf(serializer);
//			issues.serializerLeaf(serializer);
//			serializer.endTag(null, "element");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//	/**
//	 * ��ȡ�����ʶ
//	 */
//	public String getTransactionType(){
//		return "12002";
//		
//	}
	
}
