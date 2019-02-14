package XmlParse2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StartMain {
	public static void main(String[] args) throws FileNotFoundException, DOMException, TransformerException {
		long start = System.currentTimeMillis();
		Parse parse = new Parse();
		String xmlPath = "C:/Users/meta/Desktop/fileA/";
		
		Document tDoc = parse.makeDoc(xmlPath, "T_BASEFILE_TB.xml");
		NodeList tFileIdNodeList = parse.nodeListParsing(tDoc, "//TABLE/ROWS/ROW/FILE_ID/text()");
		try {
			for (int i = 0; i < tFileIdNodeList.getLength(); i++) {
				Document fDoc = parse.makeDoc(xmlPath, "F_" + tFileIdNodeList.item(i).getNodeValue() + "_TB.xml");
				Document pDoc = parse.makeDoc(xmlPath, "P_" + tFileIdNodeList.item(i).getNodeValue() + "_TB.xml");

				NodeList fPidNodeList = parse.nodeListParsing(fDoc, "//TABLE/ROWS/ROW[SIMILAR_RATE div 100>15]/P_ID");
				for (int j = 0; j < fPidNodeList.getLength(); j++) {
					Node fPidNode = fPidNodeList.item(j);
					if(fPidNode.getTextContent()==null || fPidNode.getTextContent()=="" || fPidNode.getTextContent()==" ") {
						continue;
					}else {
						Node pLicenseNode = parse.nodeParsing(pDoc, "//TABLE/ROWS/ROW[P_ID=" + fPidNode.getTextContent() + "]/LICENSE_ID");
						
						NodeList fRowNodeList=fPidNode.getParentNode().getChildNodes();
						Node fComNode=parse.getNode(fRowNodeList, "COMMENT");
						
						fComNode.setTextContent(pLicenseNode.getTextContent());
					}
				}
				parse.makeXmL(fDoc, "C:/Users/meta/Desktop/fileA/RESULT/T_"+tFileIdNodeList.item(i).getNodeValue()+"_TB.xml");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("시간 : " + (end - start)/1000.0 + "초");
	}
}