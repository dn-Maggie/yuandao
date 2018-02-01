/** 
 * CopyRright (c)2014-2015                       
 * Project:  sn     
 * Title: SMSServiceUtils.java  
 * Package com.suframework.webbase.utils                                                                        
 * JDK version used:  <JDK1.5>   
 * Description: (用一句话描述该文件做什么)                                                        
 * Author: 307806767@qq.com                
 * Create date: Apr 26, 2014 11:05:36 AM 
 * Modified By：   <修改人中文名或拼音缩写>                                         
 * Modified Date:  <修改日期，格式:YYYY-MM-DD>                                    
 * Why & What is modified  <修改原因描述>    
 * Version V1.0                        
 */

package com.dongnao.workbench.common.util.msg;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @ClassName: SMSServiceUtils
 * @Description: TODO(短信服务操作类)
 * @author suyao(QQ307806767)
 * @date Apr 26, 2014 11:06:22 AM
 * 
 */
public class SMSServiceUtils {
	private static Logger log = Logger.getLogger(SMSServiceUtils.class);
	private static String userid = "aft56";
	private static String pass = "aft56smsadmin";

	public String regineUser(String uid, String pwd, String agent_code,
			String co_name, String link_man, String mob) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUserInfo(String uid, String pwd) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public String editUserPwd(String uid, String pwd, String newpwd)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取发送短消息 Soap
	 */
	public static String getSoapSmssend(String mobiles, String msg, String time) {
		try {
			String soap = "";
			soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "<soap:Body>"
					+ "<SendMessages xmlns=\"http://tempuri.org/\">" + "<uid>"
					+ userid + "</uid>" + "<pwd>" + pass + "</pwd>" + "<tos>"
					+ mobiles + "</tos>" + "<msg>" + msg + "</msg>" + "<otime>"
					+ time + "</otime>" + "</SendMessages>" + "</soap:Body>"
					+ "</soap:Envelope>";
			return soap;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取发送短消息 InputStream
	 */
	public static InputStream getSoapInputStream(String mobiles, String msg,
			String time) throws Exception {
		URLConnection conn = null;
		InputStream is = null;
		try {
			String soap = getSoapSmssend(mobiles, msg, time);
			if (soap == null) {
				return null;
			}
			try {
				URL url = new URL("http://service2.winic.org:8003/Service.asmx");
				conn = url.openConnection();
				conn.setUseCaches(false);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Length", Integer.toString(soap
						.length()));
				conn.setRequestProperty("Content-Type",
						"text/xml; charset=utf-8");
				conn.setRequestProperty("HOST", "service2.winic.org");
				conn.setRequestProperty("SOAPAction",
						"\"http://tempuri.org/SendMessages\"");

				OutputStream os = conn.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
				osw.write(soap);
				osw.flush();
			} catch (Exception ex) {
				log.info("SmsSoap.openUrl error:" + ex.getMessage());
			}
			try {
				is = conn.getInputStream();
			} catch (Exception ex1) {
				log.info("SmsSoap.getUrl error:" + ex1.getMessage());
			}

			return is;
		} catch (Exception e) {
			log.info("SmsSoap.InputStream error:" + e.getMessage());
			return null;
		}
	}

	/**
	 * 发送短消息服务
	 */
	public static String sendMessages(String mobiles, String msg, String time)
			throws RemoteException {
		String result = "-12";
		try {
			Document doc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = getSoapInputStream(mobiles, msg, time);
			if (is != null) {
				doc = db.parse(is);
				NodeList nl = doc.getElementsByTagName("SendMessagesResult");
				Node n = nl.item(0);
				result = n.getFirstChild().getNodeValue();
				is.close();
			}
			return result;
		} catch (Exception e) {
			log.info("SmsSoap.sendSms error:" + e.getMessage());
			return "-12";
		}
	}

	public String sendFax(String uid, String pwd, String faxno, String men,
			String title, byte[] bytes, String fileName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public String sendMMS(String uid, String pwd, String mobno, String title,
			String content, byte[] bytes, String mmsFileName)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public String sendMMS_Test(String uid, String pwd, String mobno)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取短信发送状态Soap
	 */
	public static String getSoapMessageInfo(String snum) {
		try {
			String soap = "";
			soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "<soap:Body>"
					+ "<GetMessageInfo xmlns=\"http://tempuri.org/\">"
					+ "<snum>" + snum + "</snum>" + "</GetMessageInfo>"
					+ "</soap:Body>" + "</soap:Envelope>";
			return soap;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取短信发送状态Soap错误" + e.getMessage());
			return null;
		}
	}

	/**
	 * 获取短信发送状态InputStream
	 */
	public static InputStream getMessageInfoInputStream(String snum) {
		URLConnection conn = null;
		InputStream is = null;
		try {
			String soap = getSoapMessageInfo(snum);
			if (soap == null) {
				return null;
			}
			try {
				URL url = new URL("http://service2.winic.org/Service.asmx");
				conn = url.openConnection();
				conn.setUseCaches(false);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Length", Integer.toString(soap
						.length()));
				conn.setRequestProperty("Content-Type",
						"text/xml; charset=utf-8");
				conn.setRequestProperty("HOST", "service2.winic.org");
				conn.setRequestProperty("SOAPAction",
						"\"http://tempuri.org/GetMessageInfo\"");

				OutputStream os = conn.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
				osw.write(soap);
				osw.flush();
			} catch (Exception e) {
				log.info("打开URL错误" + e.getMessage());
			}
			try {
				is = conn.getInputStream();
			} catch (Exception e1) {
				log.info("获取URL错误" + e1.getMessage());
			}
			return is;
		} catch (Exception e2) {
			log.info("InputStream错误" + e2.getMessage());
			return null;
		}
	}

	/**
	 * 短信发送状态
	 */
	public static String getMessageInfo(String snum) throws RemoteException {
		String result = "-12";
		try {
			Document doc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = getMessageInfoInputStream(snum);
			if (is != null) {
				doc = db.parse(is);
				NodeList nl = doc.getElementsByTagName("GetMessageInfoResult");
				Node n = nl.item(0);
				result = n.getFirstChild().getNodeValue();
				is.close();
			}
			return result;
		} catch (Exception e) {
			log.info("获取短信发送状态错误" + e.getMessage());
			return "-12";
		}
	}

	public String sendVoice(String uid, String pwd, String vto, String vtxt,
			String mode, byte[] fileBytes, String svrno, String str_time,
			String end_time) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFaxRecord(String uid, String pwd, String num,
			String startDate, String endDate) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getVoiceRecord(String uid, String pwd, String num,
			String startDate, String endDate) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMessageRecord(String uid, String pwd, String num,
			String startDate, String endDate, String isday)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public String GET_SMS_MO(String uid, String pwd, String IDtype)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public String GET_MSP_MO(String uid, String pwd, String IDtype)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public String telCall(String uid, String pwd, String host_call,
			String caller) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	// 获取用户信息
	public static String GetInfo() {
		String result = "-12";
		try {
			Document doc;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = getUserInfoInputStream();
			if (is != null) {
				doc = db.parse(is);
				NodeList nl = doc.getElementsByTagName("GetUserInfoResult");
				Node n = nl.item(0);
				result = n.getFirstChild().getNodeValue();
				is.close();
			}
			return result;
		} catch (Exception e) {
			log.info("SmsSoap.sendSms error:" + e.getMessage());
			return "-12";
		}
	}

	public static InputStream getUserInfoInputStream() throws Exception {
		URLConnection conn = null;
		InputStream is = null;
		try {
			String soap = getSoapUserInfo();
			if (soap == null) {
				return null;
			}
			try {
				URL url = new URL("http://service2.winic.org/Service.asmx");
				conn = url.openConnection();
				conn.setUseCaches(false);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Length", Integer.toString(soap
						.length()));
				conn.setRequestProperty("Content-Type",
						"text/xml; charset=utf-8");
				conn.setRequestProperty("HOST", "service2.winic.org");
				conn.setRequestProperty("SOAPAction",
						"\"http://tempuri.org/GetUserInfo\"");

				OutputStream os = conn.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
				osw.write(soap);
				osw.flush();
			} catch (Exception ex) {
				log.info("SmsSoap.openUrl error:" + ex.getMessage());
			}
			try {
				is = conn.getInputStream();
			} catch (Exception ex1) {
				log.info("SmsSoap.getUrl error:" + ex1.getMessage());
			}

			return is;
		} catch (Exception e) {
			log.info("SmsSoap.InputStream error:" + e.getMessage());
			return null;
		}
	}

	public static String getSoapUserInfo() {
		try {
			String soap = "";
			soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					+ "<soap:Body>"
					+ "<GetUserInfo xmlns=\"http://tempuri.org/\">" + "<uid>"
					+ userid + "</uid>" + "<pwd>" + pass + "</pwd>"
					+ "</GetUserInfo>" + "</soap:Body>" + "</soap:Envelope>";
			return soap;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] aa){
		try {
			SMSServiceUtils.sendMessages("18008444102","cnmd","2014-04-26");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
