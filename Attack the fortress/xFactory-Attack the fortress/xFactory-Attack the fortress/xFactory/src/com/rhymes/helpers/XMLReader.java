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
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.path.Path;
import com.rhymes.game.entity.elements.path.PathNode;
import com.rhymes.game.entity.elements.path.traversal.TraversePointInfo;
import com.rhymes.game.entity.elements.piku.Boat;
import com.rhymes.game.entity.elements.piku.JointsInfo;
import com.rhymes.game.entity.elements.unsorted.BPath;
import com.rhymes.game.stage.levels.XLevel;
import com.rhymes.ge.core.data.GlobalVars;
import com.rhymes.ge.core.renderer.Point;

public class XMLReader {

	private static final XLevel Xlevel = null;
	public static Array<Path> ropes = new Array<Path>();
	static BPath path;
	public static Point RopeNodeS = new Point();

	public static Array<Joint> joints = new Array<Joint>();
	static Joint joint;

	public static Array<Elements> elements = new Array<Elements>();
	static Elements element;

	public static Array<Transporter> transporters = new Array<Transporter>();
	static Transporter transporter;
	
	public static void reset()
	{
		ropes.clear();
		joints.clear();
		elements.clear();
		transporters.clear();
	}

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
				String Ropecolor = eElement.getAttribute("Color");

				path = new BPath();

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
						float a = (Float.valueOf(attribX)).floatValue();
						float b = Gdx.graphics.getHeight()
								- (Float.valueOf(attribY)).floatValue();

						RopeNodeS.setX(a);
						RopeNodeS.setY(b);
						path.addLast(RopeNodeS);
						//	

					}
				}
				int Color = 2;
				if (Ropecolor.compareTo("Red") == 0) {
					Color = Boat.PLANE_RED;
				} else if (Ropecolor.compareTo("Green") == 0) {
					Color = Boat.PLANE_GREEN;
				}

				((BPath) path).setColor(Color);
				// path.ropeColor=Ropecolor;
				ropes.add(path);
				for (int c = 0; c < ropes.size; c++) {
					System.out.println("rope: " + c + " color: "
							+ ((BPath) ropes.get(c)).getColor());
				}
				path.startRendering();
			}
			NodeList jList = doc.getElementsByTagName("Joint");
			System.out.println("jList.Length: " + nList.getLength());
			for (int tempo = 0; tempo < jList.getLength(); tempo++) {
				Node jNode = jList.item(tempo);
				Element ejElement = (Element) jNode;
				int JointInitRopeID = Integer.parseInt(ejElement
						.getAttribute("JointInitRopeID"));
				float JointInitRopeDistance = Float.parseFloat(ejElement
						.getAttribute("JointInitRopeDistance"));
				joint = new Joint();

				System.out.println("JointInitRopeID: " + JointInitRopeID);
				System.out.println("JointInitRopeDistance: "
						+ JointInitRopeDistance);
				joint.JointInitRopeID = JointInitRopeID;
				joint.JointInitRopeDistance = JointInitRopeDistance;
				NodeList jointNode = doc.getElementsByTagName("JointNode");

				for (int i = 0; i < jointNode.getLength(); i++) {
					Node SinglejNode = jointNode.item(i);
					Element ejElem = (Element) SinglejNode.getParentNode();
					String jNumber = ejElem.getAttribute("Number");
					String Num = tempo + "";

					if (jNumber.compareTo(Num) == 0) {
						Element ejElemSin = (Element) SinglejNode;
						Integer JointNodeRopeID = Integer.parseInt(ejElemSin
								.getAttribute("JointNodeRopeID"));
						Float JointNodeRopeDistance = Float
								.parseFloat(ejElemSin
										.getAttribute("JointNodeRopeDistance"));
						joint.JointNodeRopeID.addLast(JointNodeRopeID);
						joint.JointNodeRopeDistance
								.addLast(JointNodeRopeDistance);
						System.out.println("JointNodeRopeID: "
								+ JointNodeRopeID);
						System.out.println("JointNodeRopeDistance: "
								+ JointNodeRopeDistance);
					}
				}
				joints.add(joint);
			}

			NodeList eList = doc.getElementsByTagName("Element");
			System.out.println("eList.Length: " + eList.getLength());
			for (int tempoo = 0; tempoo < eList.getLength(); tempoo++) {
				Node eNode = eList.item(tempoo);
				Element eeElement = (Element) eNode;
				int ElementRopeID = Integer.parseInt(eeElement
						.getAttribute("ElementRopeID"));
				float ElementRopeDistance = Float.parseFloat(eeElement
						.getAttribute("ElementRopeDistance"));
				String elementName = eeElement.getAttribute("elementName");
				element = new Elements();
				element.elementName = elementName;
				element.ElementRopeID = ElementRopeID;
				element.ElementRopeDistance = ElementRopeDistance;
				elements.add(element);
			}

			NodeList tList = doc.getElementsByTagName("Transporter");
			System.out.println("tList.Length: " + tList.getLength());
			for (int ttempoo = 0; ttempoo < tList.getLength(); ttempoo++) {
				Node tNode = tList.item(ttempoo);
				Element tElement = (Element) tNode;
				int TransporterInitRopeID = Integer.parseInt(tElement
						.getAttribute("TransporterInitRopeID"));
				float TransporterInitRopeDistance = Float.parseFloat(tElement
						.getAttribute("TransporterInitRopeDistance"));
				int TransporterDestRopeID = Integer.parseInt(tElement
						.getAttribute("TransporterDestRopeID"));
				float TransporterDestRopeDistance = Float.parseFloat(tElement
						.getAttribute("TransporterDestRopeDistance"));

				transporter = new Transporter();
				transporter.TransporterInitRopeID = TransporterInitRopeID;
				transporter.TransporterInitRopeDistance = TransporterInitRopeDistance;
				transporter.TransporterDestRopeID = TransporterDestRopeID;
				transporter.TransporterDestRopeDistance = TransporterDestRopeDistance;
				transporters.add(transporter);
			}
		} catch (Exception e) {
			e.printStackTrace();

			ropes.add(new BPath(2));
		}
		((XLevel) (GlobalVars.ge.getCurrentStage())).getPlanePathSet()
				.addPathSet(ropes);
	}

	public void AddElementS() {
		addJoints();
		addElements();
		AddTransporter();
	}

	public void addJoints() {
		for (int i = 0; i < joints.size; i++) {
			((XLevel) (GlobalVars.ge.getCurrentStage())).addJoint(
					((XLevel) (GlobalVars.ge.getCurrentStage()))
							.getPlanePathSet().getPaths().get(
									joints.get(i).JointInitRopeID)
							.startTraverse(joints.get(i).JointInitRopeDistance,
									Path.METHOD_RIGHT), constructJointInfo(i));
		}
	}

	private Array<JointsInfo> constructJointInfo(int jointID) {
		Array<JointsInfo> tinfosArray = new Array<JointsInfo>();
		for (int i = 0; i < joints.get(jointID).JointNodeRopeID.size(); i++) {
			tinfosArray.add(new JointsInfo(((XLevel) (GlobalVars.ge
					.getCurrentStage())).getPlanePathSet().getPaths().get(
					joints.get(jointID).JointNodeRopeID.get(i))
					.startTraverse(
							joints.get(jointID).JointNodeRopeDistance
									.get(i), Path.METHOD_RIGHT),
					Path.METHOD_RIGHT));
//			if (joints.get(jointID).JointNodeRopeDistance.get(i) == 0f) {
//				// if(i == 0)
//				// continue;
//
//				tinfosArray.add(new JointsInfo(((XLevel) (GlobalVars.ge
//						.getCurrentStage())).getPlanePathSet().getPaths().get(
//						joints.get(jointID).JointNodeRopeID.get(i))
//						.startTraverse(
//								joints.get(jointID).JointNodeRopeDistance
//										.get(i), Path.METHOD_RIGHT),
//						Path.METHOD_RIGHT));
////				System.out.println("arif1");
//			}
//			// else
////			System.out.println("A:"
////					+ joints.get(jointID).JointNodeRopeDistance.get(i)
////							.intValue()
////					+ " B:"
////					+ (int) (nodeDistance(joints.get(jointID).JointNodeRopeID
////							.get(i))));
//
//			if (joints.get(jointID).JointNodeRopeDistance.get(i).intValue() == (int) (nodeDistance(joints
//					.get(jointID).JointNodeRopeID.get(i)))) {
//				// if(joints.get(jointID).JointNodeRopeDistance.get(i)==ropes.get(joints.get(jointID).JointNodeRopeID.get(i)).calculateTotalDistance()){
//
//				tinfosArray.add(new JointsInfo(((XLevel) (GlobalVars.ge
//						.getCurrentStage())).getPlanePathSet().getPaths().get(
//						joints.get(jointID).JointNodeRopeID.get(i))
//						.startTraverse(
//								joints.get(jointID).JointNodeRopeDistance
//										.get(i), Path.METHOD_LEFT),
//						Path.METHOD_LEFT));
////				System.out.println("arif2");
//			} else {
//				for (int j = 0; j < joints.get(jointID).JointNodeRopeID.size(); j++) {
//					if (i == j)
//						continue;
//					if (joints.get(jointID).JointNodeRopeID.get(i) == joints
//							.get(jointID).JointNodeRopeID.get(j)) {
//						if (joints.get(jointID).JointNodeRopeDistance.get(i) > joints
//								.get(jointID).JointNodeRopeDistance.get(j)) {
//							tinfosArray
//									.add(new JointsInfo(
//											((XLevel) (GlobalVars.ge
//													.getCurrentStage()))
//													.getPlanePathSet()
//													.getPaths()
//													.get(
//															joints.get(jointID).JointNodeRopeID
//																	.get(i))
//													.startTraverse(
//															joints.get(jointID).JointNodeRopeDistance
//																	.get(i),
//															Path.METHOD_RIGHT),
//											Path.METHOD_RIGHT));
////							System.out.println("arif3");
//						} else {
//							tinfosArray
//									.add(new JointsInfo(
//											((XLevel) (GlobalVars.ge
//													.getCurrentStage()))
//													.getPlanePathSet()
//													.getPaths()
//													.get(
//															joints.get(jointID).JointNodeRopeID
//																	.get(i))
//													.startTraverse(
//															joints.get(jointID).JointNodeRopeDistance
//																	.get(i),
//															Path.METHOD_LEFT),
//											Path.METHOD_LEFT));
//							;
////							System.out.println("arif4");
//						}
//					}
//				}
//			}
		}

		return tinfosArray;
	}

	public float nodeDistance(int n) {
		float Distance = 0;
		for (int i = 0; i < (ropes.get(n).getNodes().size() - 1); i++) {
			Point A = ropes.get(n).getNodes().get(i).getLocation();
			Point B = ropes.get(n).getNodes().get(i + 1).getLocation();
			Distance += (float) Math.sqrt(((B.x - A.x) * (B.x - A.x))
					+ ((B.y - A.y) * (B.y - A.y)));
		}
		return Distance;
	}

	public void addElements() {
		for (int i = 0; i < elements.size; i++) {
			if (elements.get(i).elementName.compareTo("Destroyer") == 0) {
				((XLevel) (GlobalVars.ge.getCurrentStage()))
						.addDestroyer(((XLevel) (GlobalVars.ge
								.getCurrentStage())).getPlanePathSet()
								.getPaths().get(elements.get(i).ElementRopeID)
								.startTraverse(
										elements.get(i).ElementRopeDistance,
										Path.METHOD_RIGHT));
			} else if (elements.get(i).elementName.compareTo("Star") == 0) {
				((XLevel) (GlobalVars.ge.getCurrentStage()))
						.addStar(((XLevel) (GlobalVars.ge.getCurrentStage()))
								.getPlanePathSet().getPaths().get(
										elements.get(i).ElementRopeID)
								.startTraverse(
										elements.get(i).ElementRopeDistance,
										Path.METHOD_RIGHT));
			} else if (elements.get(i).elementName.compareTo("TransformerRed") == 0) {
				((XLevel) (GlobalVars.ge.getCurrentStage())).addTransformer(
						((XLevel) (GlobalVars.ge.getCurrentStage()))
								.getPlanePathSet().getPaths().get(
										elements.get(i).ElementRopeID)
								.startTraverse(
										elements.get(i).ElementRopeDistance,
										Path.METHOD_RIGHT), Boat.PLANE_RED);
			} else if (elements.get(i).elementName
					.compareTo("TransformerGreen") == 0) {
				((XLevel) (GlobalVars.ge.getCurrentStage())).addTransformer(
						((XLevel) (GlobalVars.ge.getCurrentStage()))
								.getPlanePathSet().getPaths().get(
										elements.get(i).ElementRopeID)
								.startTraverse(
										elements.get(i).ElementRopeDistance,
										Path.METHOD_RIGHT), Boat.PLANE_GREEN);
			}
			// else
			// if(elements.get(i).elementName.compareTo("TransformerBlue")==0){
			// ((XLevel)(GlobalVars.ge.getCurrentStage())).addTransformer(((XLevel)(GlobalVars.ge.getCurrentStage())).getPlanePathSet().getPaths().get(elements.get(i).ElementRopeID).startTraverse(elements.get(i).ElementRopeDistance,
			// Path.METHOD_RIGHT),Boat.PLANE_BLUE);
			// }

		}
	}

	public void AddTransporter() {
		for (int i = 0; i < transporters.size; i++) {
			((XLevel) (GlobalVars.ge.getCurrentStage()))
					.addTransporter(
							((XLevel) (GlobalVars.ge.getCurrentStage()))
									.getPlanePathSet()
									.getPaths()
									.get(
											transporters.get(i).TransporterInitRopeID)
									.startTraverse(
											transporters.get(i).TransporterInitRopeDistance,
											Path.METHOD_RIGHT),
							((XLevel) (GlobalVars.ge.getCurrentStage()))
									.getPlanePathSet()
									.getPaths()
									.get(
											transporters.get(i).TransporterDestRopeID)
									.startTraverse(
											transporters.get(i).TransporterDestRopeDistance,
											Path.METHOD_LEFT));
		}
	}
	// addTransporter(pathSet.getPaths().get(0).startTraverse(300,
	// Path.METHOD_RIGHT) , pathSet.getPaths().get(1).startTraverse());

}
