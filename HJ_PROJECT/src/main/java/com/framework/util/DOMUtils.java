package com.framework.util;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DOMUtils {

	public static Document getDocument() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	    DocumentBuilder db;
	    
		try {
			db = dbf.newDocumentBuilder();
		    return db.newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
}
