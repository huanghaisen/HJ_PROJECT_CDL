package com.utils;

import com.enums.QrcodeType;
import com.framework.security.BlowFish;

import java.util.Map;

/**
 * 二维码工具
 * @author daniel
 *
 */
public class QrcodeUtils {
	
	/**
	 * 
	 * @param encode 是否使用加密
	 * @param qrcodeType 二维码类型
	 * @param model 数据模型
	 * @return
	 */
	public static String preEncoder(boolean encode,QrcodeType qrcodeType,Map<String,Object> map){
		StringBuffer msg=new StringBuffer("cmd:"+qrcodeType.getValue());
		StringBuffer code=new StringBuffer();
		String codeValue="";
		if (qrcodeType.equals(QrcodeType.DEVICE)) {
			code.append(map.get("id")).append("-");
			String deviceCode=map.get("deviceCode").toString();
			deviceCode=deviceCode.lastIndexOf("-")!=-1?deviceCode.replace("-", "##"):deviceCode;
			code.append(deviceCode).append("-");
			String factoryCode=map.get("factoryCode").toString();
			factoryCode=factoryCode.lastIndexOf("-")!=-1?factoryCode.replace("-", "##"):factoryCode;
			code.append(factoryCode).append("-")
			.append(map.get("deviceTypeId")).append("-")
			.append(map.get("deviceModelId"));
			deviceCode=null;
			factoryCode=null;
		}else if (qrcodeType.equals(QrcodeType.OUTADDR)) {
			code.append(map.get("url"));
		}
		msg.append(",encode:");
		if (encode) {
			msg.append(1);
			codeValue=BlowFish.pushEncode(code.toString());
		}else{
			msg.append(0);
			codeValue=code.toString();
		}
		msg.append(",code:").append(codeValue);
		code=null;
		codeValue=null;
		return msg.toString();
	}

}
