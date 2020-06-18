package xmlDOM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;

import resources.SimpleErrorHandler;

public class Main {
	
	public static String description(Node n, String tab) {
		
		String str = new String();
		
		if (n instanceof Element) {
			Element element = (Element)n;
			
			
			str += "<" + n.getNodeName();
			
			if(n.getAttributes() != null && n.getAttributes().getLength() > 0) {
				NamedNodeMap att = n.getAttributes();
				int nAtt = att.getLength();
				
				for(int i = 0; i<nAtt;i++) {
					Node noeud = att.item(i);
					str+= " "+noeud.getNodeName()+"=\"" +noeud.getNodeValue()+"\" ";
				}
			}
			
			str+=">";
			
			if(n.getChildNodes().getLength() == 1) {
				str+=n.getTextContent();
			}
			
			int nbchild = n.getChildNodes().getLength();
			
			NodeList list = n.getChildNodes();
			
			String tab2 = tab + "\t";
			
			for(int j = 0;j<nbchild;j++){
				Node noeud2 = list.item(j);
				if(noeud2 instanceof Element) {
					str+="\n"+tab2+description(noeud2,tab2);
					
				}
			}
			if(n.getChildNodes().getLength() < 2){
				str+="</"+n.getNodeName()+">";
			}else {
				str += "\n"+tab+"</"+n.getNodeName()+">";
			}
		}
		
		return str;
	}
	


	public static void main(String[] args) {
		
		DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			File xmlFile = new File("src/resources/test.xml");
			
			Document doc;
			Element root;
		
			ErrorHandler errorHandler = new SimpleErrorHandler();
			builder.setErrorHandler(errorHandler);
			System.out.println("***********************************");
			doc = builder.parse(xmlFile);
			
			
			doc = builder.parse(xmlFile);
			root = doc.getDocumentElement();
            System.out.println(description(root, ""));
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
