package XmlParseUseHashMap;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StartMain {
	public static void main(String[] args) throws FileNotFoundException, DOMException, TransformerException {
		long start = System.currentTimeMillis();
		Parse parse = new Parse();
		String xmlPath = "C:/Users/meta/Desktop/fileA/";
		Map<String, String> fNode =new HashMap<String, String>();

		Document tDoc = parse.makeDoc(xmlPath, "T_BASEFILE_TB.xml");
		NodeList tFileIdNodeList = parse.nodeListParsing(tDoc, "//TABLE/ROWS/ROW/FILE_ID/text()");
		try {
			for (int i = 0; i < tFileIdNodeList.getLength(); i++) {
				Document fDoc = parse.makeDoc(xmlPath, "F_" + tFileIdNodeList.item(i).getNodeValue() + "_TB.xml");
				Document pDoc = parse.makeDoc(xmlPath, "P_" + tFileIdNodeList.item(i).getNodeValue() + "_TB.xml");
				fDoc.normalize();
				pDoc.normalize();
				NodeList fPidNodeList = parse.nodeListParsing(fDoc, "//TABLE/ROWS/ROW[SIMILAR_RATE div 100>15]/P_ID");
				NodeList pRowNodeList = parse.nodeListParsing(pDoc, "//TABLE/ROWS/ROW");

				for(int j=0;j<pRowNodeList.getLength();j++) {
					Element childPRowList=(Element)pRowNodeList.item(j).getChildNodes();
					String pId=childPRowList.getElementsByTagName("P_ID").item(0).getTextContent();
					String licenseId=childPRowList.getElementsByTagName("LICENSE_ID").item(0).getTextContent();
					if(pId==null || licenseId==null) {
						continue;
					}else {
						fNode.put(pId, licenseId);
					}
				}
				
				for(int j=0;j<fPidNodeList.getLength();j++) {
					NodeList fRowNodeList=fPidNodeList.item(j).getParentNode().getChildNodes();
					Node fComNode=parse.getNode(fRowNodeList, "COMMENT");
					String licenseId=fNode.get(fPidNodeList.item(j));
					fComNode.setTextContent(licenseId);
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
