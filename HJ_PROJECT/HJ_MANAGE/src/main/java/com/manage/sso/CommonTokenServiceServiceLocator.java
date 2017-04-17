/**
 * CommonTokenServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.manage.sso;

public class CommonTokenServiceServiceLocator extends org.apache.axis.client.Service implements CommonTokenServiceService {

    public CommonTokenServiceServiceLocator() {
    }


    public CommonTokenServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CommonTokenServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CheckAiuapTokenSoap
    private java.lang.String CheckAiuapTokenSoap_address = "http://135.129.35.160:9000/uac/services/CheckAiuapTokenSoap";

    public java.lang.String getCheckAiuapTokenSoapAddress() {
        return CheckAiuapTokenSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CheckAiuapTokenSoapWSDDServiceName = "CheckAiuapTokenSoap";

    public java.lang.String getCheckAiuapTokenSoapWSDDServiceName() {
        return CheckAiuapTokenSoapWSDDServiceName;
    }

    public void setCheckAiuapTokenSoapWSDDServiceName(java.lang.String name) {
        CheckAiuapTokenSoapWSDDServiceName = name;
    }

    public CommonTokenService getCheckAiuapTokenSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CheckAiuapTokenSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCheckAiuapTokenSoap(endpoint);
    }

    public CommonTokenService getCheckAiuapTokenSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            CheckAiuapTokenSoapSoapBindingStub _stub = new CheckAiuapTokenSoapSoapBindingStub(portAddress, this);
            _stub.setPortName(getCheckAiuapTokenSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCheckAiuapTokenSoapEndpointAddress(java.lang.String address) {
        CheckAiuapTokenSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (CommonTokenService.class.isAssignableFrom(serviceEndpointInterface)) {
                CheckAiuapTokenSoapSoapBindingStub _stub = new CheckAiuapTokenSoapSoapBindingStub(new java.net.URL(CheckAiuapTokenSoap_address), this);
                _stub.setPortName(getCheckAiuapTokenSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CheckAiuapTokenSoap".equals(inputPortName)) {
            return getCheckAiuapTokenSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://135.129.35.160:9000/uac/services/CheckAiuapTokenSoap", "CommonTokenServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://135.129.35.160:9000/uac/services/CheckAiuapTokenSoap", "CheckAiuapTokenSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CheckAiuapTokenSoap".equals(portName)) {
            setCheckAiuapTokenSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
