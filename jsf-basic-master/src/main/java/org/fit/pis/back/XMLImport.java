/**
 * 
 */
package org.fit.pis.back;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author burgetr
 *
 */
public class XMLImport 
{
	
	public void readXML()
	{
		try {
		   File fXmlFile = new File("file.xml");
		   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		   DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		   Document doc = dBuilder.parse(fXmlFile);
		   doc.getDocumentElement().normalize();
		   
		   NodeList list = doc.getElementsByTagName("item");
		   for (int i = 0; i < list.getLength(); i++) 
		   {
			   Node n = list.item(i);
			   //... etc. ...
		   }
		   
		} catch (Exception e) {
		}
	}

}
