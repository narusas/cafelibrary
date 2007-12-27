package net.narusas.cafelibrary.serial;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import junit.framework.TestCase;

public class LibrarySerializerTest extends TestCase {
	public void testUseJaxp() {
		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Element e = doc.createElement("aa");
		doc.appendChild(e);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t;
		try {
			t = tf.newTransformer();
			t.transform(new DOMSource(doc), new StreamResult(System.out));
		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
		} catch (TransformerException ex) {
			ex.printStackTrace();
		}
		 
	}
}
