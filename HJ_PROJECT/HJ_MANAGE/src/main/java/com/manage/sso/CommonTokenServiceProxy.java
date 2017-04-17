package com.manage.sso;

public class CommonTokenServiceProxy implements CommonTokenService {
  private String _endpoint = null;
  private CommonTokenService commonTokenService = null;
  
  public CommonTokenServiceProxy() {
    _initCommonTokenServiceProxy();
  }
  
  public CommonTokenServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initCommonTokenServiceProxy();
  }
  
  private void _initCommonTokenServiceProxy() {
    try {
      commonTokenService = (new CommonTokenServiceServiceLocator()).getCheckAiuapTokenSoap();
      if (commonTokenService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)commonTokenService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)commonTokenService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (commonTokenService != null)
      ((javax.xml.rpc.Stub)commonTokenService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public CommonTokenService getCommonTokenService() {
    if (commonTokenService == null)
      _initCommonTokenServiceProxy();
    return commonTokenService;
  }
  
  public java.lang.String checkAiuapTokenSoap(java.lang.String requestInfo) throws java.rmi.RemoteException{
    if (commonTokenService == null)
      _initCommonTokenServiceProxy();
    return commonTokenService.checkAiuapTokenSoap(requestInfo);
  }
  
  
}