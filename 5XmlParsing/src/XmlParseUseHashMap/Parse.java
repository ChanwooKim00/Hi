package XmlParseUseHashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parse {
	XPathFactory xPathFactory = XPathFactory.newInstance();
	XPath xpath = xPathFactory.newXPath();

	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = null;

	/**
	 * file 을 Document 에 담아서 리턴하는 메소드
	 * 
	 * @param filePath
	 * @param fileName
	 * @return Document
	 */
	public Document makeDoc(String filePath, String fileName) {
		Document doc = null;
		File file = new File(filePath + fileName);

		try {
			builder = factory.newDocumentBuilder();
			doc = builder.parse(file);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			System.out.println("document 를 만드는데에 실패했습니다.");
		}
		return doc;
	}

	/**
	 * file 을 Document 에 담아서 리턴하는 메소드
	 * 
	 * @param filePath
	 * @param fileName
	 * @return Document
	 */
	public Document makeDoc() {
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = builder.newDocument();

		return doc;
	}

	/**
	 * Document 를 xpath 를 통해 nodeList 파싱
	 * 
	 * @param xPath(경로)
	 * @param doc(Document)
	 * @return NodeList
	 */
	public NodeList nodeListParsing(Object doc, String xPath) {
		NodeList list = null;
		try {
			list = (NodeList) xpath.evaluate(xPath, doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		if (list == null) {
			System.out.println("xPath 가 잘못 되었거나 doc 에 해당 node가 없습니다.");
		}
		return list;
	}

	/**
	 * Document 를 xpath를 통해 node 파싱
	 * 
	 * @param doc
	 * @param xPath
	 * @return Node
	 */
	public Node nodeParsing(Document doc, String xPath) {
		Node node = null;
		try {
			node = (Node) xpath.evaluate(xPath, doc, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		if (node == null) {
			System.out.println("xPath 가 잘못 되었거나 doc 에 해당 node가 없습니다.");
		}
		return node;
	}

	public Node getNode(NodeList nodeList, String name) {
		Node node = null;
		for (int i = 0; i < nodeList.getLength(); i++) {
			String nodeName = nodeList.item(i).getNodeName();
			if (nodeName.equals(name)) {
				node = nodeList.item(i);
			}
		}
		return node;
	}

	/**
	 * nodeList 를 ArrayList 에 담아주는 메소드
	 * 
	 * @param nodeList
	 * @param arr
	 * @return ArrayList<Object>
	 */
	public ArrayList<String> nodeListToArrayList(NodeList nodeList) {
		ArrayList<String> arr = null;
		for (int a = 0; a < nodeList.getLength(); a++) {
			arr.add(nodeList.item(a).getNodeValue());
		}
		return arr;
	}

	/**
	 * 노드 리스트 콘솔 출력 테스트용
	 * 
	 * @param nodeList
	 */
	public void nodeListConsole(NodeList nodeList, String getTextContentORgetNodeName) {
		if (getTextContentORgetNodeName.equals("getTextContent")) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				System.out.println(nodeList.item(i).getTextContent());
			}
		} else if (getTextContentORgetNodeName.equals("getNodeName")) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				System.out.println(nodeList.item(i).getNodeName());
			}
		}
	}

	/**
	 * xml 파일 만드는 메소드 doc= domsource에 들어가는 document // path= 만들어지는 path + id
	 * 
	 * @param doc
	 * @param path
	 */
	public void makeXmL(Document doc, String path) {
		try {
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer trans = transFactory.newTransformer();
	
			trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileOutputStream(new File(path)));
			trans.transform(source, result);
		} catch (FileNotFoundException | TransformerException | DOMException e) {
			e.printStackTrace();
		}
	}

	/**
	 * element 에 해당 tag 의 value 를 String 으로 리턴 해주는 메소드
	 * 
	 * @param element
	 * @param tag
	 * @return String
	 */
	public String getValueByTagNameFromElement(Element element, String tag) {
		String result = element.getElementsByTagName("LICENSE_ID").item(0).getTextContent();
		return result;
	}
}
