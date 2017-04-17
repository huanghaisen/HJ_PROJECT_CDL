package com.utils;

import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.framework.util.TimeUtils;
import com.view.UserSPView;

/**
 * sso登录包装工具类
 * @author daniel
 *
 */
public class LoginSsoUtils {

	
	public static String ssocode(String appAcctId,String token){
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("USERREQ");  
		Element HEAD = root.addElement("HEAD");
		Element CODE = HEAD.addElement("CODE");
		Element SID = HEAD.addElement("SID");
		Element TIMESTAMP = HEAD.addElement("TIMESTAMP");
		TIMESTAMP.setText(TimeUtils.getTimeLong(new Date()));
		Element SERVICEID = HEAD.addElement("SERVICEID");
		SERVICEID.setText("GSYDJKPT");
		Element BODY = root.addElement("BODY");
		Element APPACCTID = BODY.addElement("APPACCTID");
		APPACCTID.setText(appAcctId);
		Element TOKEN = BODY.addElement("TOKEN");
		TOKEN.setText(token);
		return document.asXML();
	}
	
	public static UserSPView getUserSp(UserSPView user,String text){
		try {
			Document document = DocumentHelper.parseText(text);
			Node nodecode = document.selectSingleNode("USERRSP/HEAD/CODE");
			Node nodesid = document.selectSingleNode("USERRSP/HEAD/SID");
			Node nodetime = document.selectSingleNode("USERRSP/HEAD/TIMESTAMP");
			Node nodeserviceid = document.selectSingleNode("USERRSP/HEAD/SERVICEID");
			
			Node nodersp = document.selectSingleNode("USERRSP/BODY/RSP");
			Node nodemid = document.selectSingleNode("USERRSP/BODY/MAINACCTID");
			Node nodeappid = document.selectSingleNode("USERRSP/BODY/APPACCTID");
			
			user.setCode(nodecode.getText());
			user.setSid(nodesid.getText());
			user.setTimestamp(nodetime.getText());
			user.setServiceid(nodeserviceid.getText());
			user.setRsp(Integer.valueOf(nodersp.getText()));
			user.setMainacctid(nodemid.getText());
			user.setAppacctid(nodeappid.getText());
			nodecode=null;
			nodesid=null;
			nodetime=null;
			nodeserviceid=null;
			nodersp=null;
			nodemid=null;
			nodeappid=null;
		} catch (DocumentException e) {
//			e.printStackTrace();
		}
		
		return user;
	}
	
	
	public static void main(String[] args) {
		String token="32|47|94|-29|-118|-14|93|-41|-58|27|-123|-4|-120|50|-23|-35|119|-123|82|-98|7|66|-36|83|0|22|-49|50|-1|-5|86|-64|89";
		String doc=ssocode("2000191606", token);
		System.out.println(doc);
	}
}
