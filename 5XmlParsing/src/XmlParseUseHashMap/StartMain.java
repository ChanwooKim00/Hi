package XmlParseUseHashMap;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import XmlParseUseHashMap.Parse;

public class StartMain {
	static long startTime;
	static long preUseMemory;

	public static void main(String[] args) {
		start();
		long start = System.currentTimeMillis();
		Parse parse = new Parse();
		String xmlPath = "C:/Users/meta/Desktop/fileA/";
		Map<String, String> fNode = new HashMap<String, String>();

		Document tDoc = parse.makeDoc(xmlPath, "T_BASEFILE_TB.xml");
		NodeList tFileIdNodeList = parse.nodeListParsing(tDoc, "//TABLE/ROWS/ROW/FILE_ID/text()");
		for (int i = 0; i < tFileIdNodeList.getLength(); i++) {
			Document fDoc = parse.makeDoc(xmlPath, "F_" + tFileIdNodeList.item(i).getNodeValue() + "_TB.xml");
			Document pDoc = parse.makeDoc(xmlPath, "P_" + tFileIdNodeList.item(i).getNodeValue() + "_TB.xml");
			fDoc.normalize();
			pDoc.normalize();
			NodeList fPidNodeList = parse.nodeListParsing(fDoc, "//TABLE/ROWS/ROW[SIMILAR_RATE div 100>15]/P_ID");
			NodeList pRowNodeList = parse.nodeListParsing(pDoc, "//TABLE/ROWS/ROW");

			for (int j = 0; j < pRowNodeList.getLength(); j++) {
				Element childPRowElement = (Element) pRowNodeList.item(j).getChildNodes();
				String pId = parse.getValueByTagNameFromElement(childPRowElement, "P_ID");
				String licenseId = parse.getValueByTagNameFromElement(childPRowElement, "LICENSE_ID");
				if (pId == null || licenseId == null) {
					continue;
				} else {
					fNode.put(pId, licenseId);
				}
			}

			for (int j = 0; j < fPidNodeList.getLength(); j++) {
				NodeList fRowNodeList = fPidNodeList.item(j).getParentNode().getChildNodes();
				Node fComNode = parse.getNode(fRowNodeList, "COMMENT");
				String licenseId = fNode.get(fPidNodeList.item(j).getNodeValue());
				fComNode.setTextContent(licenseId);
			}
			parse.makeXmL(fDoc,
					"C:/Users/meta/Desktop/fileA/RESULT/T_" + tFileIdNodeList.item(i).getNodeValue() + "_TB.xml");
		}
		long end = System.currentTimeMillis();
		System.out.println("시간 : " + (end - start) / 1000.0 + "초");
		end();
	}

	public static void start() {
		Runtime.getRuntime().gc();
		startTime = System.nanoTime();
		preUseMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}

	public static void end() {
		long afterUseMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long useMemory = (afterUseMemory - preUseMemory) / 1000;
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println(new Date() + " | Elapsed Time : " + elapsedTime + " | Use Memory : " + useMemory);
	}
}
