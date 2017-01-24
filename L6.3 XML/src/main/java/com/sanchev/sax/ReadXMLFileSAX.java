package com.sanchev.sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ReadXMLFileSAX {
	public static Object readXML(String xmlFileName) {
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			
			SAXHandler saxHandler = new SAXHandler();
			saxParser.parse(xmlFileName, saxHandler);
			
			return saxHandler.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}