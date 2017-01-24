package com.sanchev;

import com.sanchev.resources.DBParametersResource;
import com.sanchev.sax.ReadXMLFileSAX;

public class App {
	public static void main(String[] args) {
		DBParametersResource resource = (DBParametersResource) ReadXMLFileSAX.readXML("./data/MySqlResource.xdb");
		System.out.println(resource);
	}
}