
package com.rhymes.helpers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.rhymes.game.entity.elements.path.traversal.Path;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;

public class XMLReaderChain {

//	private static final XLevel Xlevel = null;
	public static Array<Path> ropes = new Array<Path>();
	static Path path;
	public static Point RopeNodeS = new Point();

	public static Array<Joint> joints = new Array<Joint>();
	static Joint joint;

	public static Array<Elements> elements = new Array<Elements>();
	static Elements element;

	public static Array<Transporter> transporters = new Array<Transporter>();
	static Transporter transporter;
	
	

	public static void main(String filePath) {
		ropes.clear();

		try {

//			Helper.printKeyVal("File path", AssetConstants.FILE_LEVEL_INFO);
			Helper.printKeyVal("File path", filePath);
			FileHandle fh = Gdx.files.internal(filePath);
			if (fh == null)
				Helper.println("fh null");

			if (fh.exists())
				Helper.println("\n\n\nfile exists");
			else
				Helper.println("\n\n\nfile does not exists");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(fh.read());

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("Rope");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				String RopeType = eElement.getAttribute("Type");

				path = new Path();

				NodeList RopeNode = doc.getElementsByTagName("RopeNode");

				for (int i = 0; i < RopeNode.getLength(); i++) {
					RopeNodeS = new Point();

					Node SingleNode = RopeNode.item(i);
					Element eElem = (Element) SingleNode.getParentNode();
					String attr = eElem.getAttribute("Number");
					String Num = temp + "";

					if (attr.compareTo(Num) == 0) {
						Element eElemSin = (Element) SingleNode;
						String attribX = eElemSin.getAttribute("x");
						String attribY = eElemSin.getAttribute("y");
						float a = (Float.valueOf(attribX)).floatValue()*1.5f+100f ;
						float b = Gdx.graphics.getHeight()
								- (Float.valueOf(attribY)).floatValue()+330f ;

						RopeNodeS.setX(a);
						RopeNodeS.setY(b);
						path.addLast(RopeNodeS);
						//	

					}
				}
				
				path.pathType=RopeType;
				ropes.add(path);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();

//			ropes.add(new BPath(2));
//			XMLReader.ropes.get(0).getNodes().get(0).getLocation().
		}
	
	}


}
