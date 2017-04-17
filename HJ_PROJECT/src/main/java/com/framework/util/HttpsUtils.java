package com.framework.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class HttpsUtils {
	private final static int GETMETHOD_REQUEST_TIMEOUT = 5000;
	
	public String sendHttpsReq(String xml,String url){
		HttpClient httpclient=new HttpClient(new HttpClientParams(),new SimpleHttpConnectionManager(true));
		httpclient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GBK");
//		ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
//		httpclient.getHostConfiguration().setProxy("10.121.2.5",3128);
//		Protocol myhttps=new Protocol("http",fcty,7998);
//		Protocol.registerProtocol("http", myhttps);
		PostMethod postmethod=new PostMethod(url);
		 // 设置httpClient的字符集
		postmethod.addRequestHeader("Content-Type","text/xml; charset=GBK");
		postmethod.setRequestHeader("Accept-Language", "zh-cn");
		postmethod.setRequestHeader("Accept", "text/xml");
		postmethod.setRequestContentLength(xml.length());
		postmethod.setRequestHeader("Keep-Alive", "5");
		postmethod.setRequestHeader("Connection", "close");
		// 设置post方法的请求超时
		postmethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 3000);

		//设置超时时间
        setClientTimeout();
		// 设置post方法请求失败，自动重试一次
		postmethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(1, false));
		
		postmethod.setRequestBody(xml);
//		System.out.println("============>发送报文开始");
		String retValue="";
		try {
			int statusCode=httpclient.executeMethod(postmethod);
			
//			System.out.println("=======返回状态=====>"+statusCode);
			// post方法执行失败
	        if (HttpStatus.SC_OK != statusCode)
	        {
	        	retValue="ERROR:"+statusCode;
	            return retValue;
	        }
			
			retValue=inputStream2String(postmethod.getResponseBodyAsStream());
			
//			Header[] headers = postmethod.getResponseHeaders();
//			for(int i=0;i<headers.length;i++) {    
//				System.out.println(headers[i].getName()+"=="+ headers[i].getValue());   
//			}
		} catch (HttpException e) {
			retValue="ERROR:网络协议异常";
		} catch (IOException e) {
			retValue="ERROR:服务器没有响应";
		}finally{
			postmethod.releaseConnection();
		}
		return retValue;
	}
	
	/**
     * 上传文件到服务器
     * @param fileUrl 上传文件路径
     * @param url	上传服务器
     * @param name 上传文件对应的 标签
     * @return
     * @throws IOException
     */
    public String postUpload(String name,File fileUrl,String url)
    {
        String responseBody = "";
        HttpClient httpclient=new HttpClient(new HttpClientParams(),new SimpleHttpConnectionManager(true));
//        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
//        httpclient.getHostConfiguration().setProxy("10.121.2.5",3128);
//		Protocol myhttps=new Protocol("http",fcty,7998);
//		Protocol.registerProtocol("http", myhttps);
        PostMethod postmethod = new PostMethod(url);
     // 设置post方法的请求超时
        postmethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 15000);
     // 设置post方法请求失败，自动重试一次
		postmethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(1, false));
        try {
            Part[] parts ={ new FilePart(name, fileUrl)};
            postmethod.setRequestEntity(new MultipartRequestEntity(parts,postmethod.getParams()));
            int statusCode=httpclient.executeMethod(postmethod);
			
			responseBody = inputStream2String(postmethod.getResponseBodyAsStream());
		} catch (HttpException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}finally{
			postmethod.releaseConnection();
		}
        return responseBody;
    }
	
	/**
     * 设置client超时时间
     */
    public static void setClientTimeout()
    {
        System.setProperty("sun.net.client.defaultConnectTimeout",
            Long.toString(GETMETHOD_REQUEST_TIMEOUT));
        System.setProperty("sun.net.client.defaultReadTimeout",
            Long.toString(GETMETHOD_REQUEST_TIMEOUT));
    }
    
    public static String inputStream2String(InputStream is) throws IOException{ 
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
        int   i=-1; 
        while((i=is.read())!=-1){ 
        baos.write(i); 
        } 
        is.close();
       return   baos.toString(); 
    } 
}
