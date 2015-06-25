package com.rhymes.attackTheFortress;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.rhymes.game.data.AssetConstants;
import com.rhymes.game.entity.elements.path.Path;
import com.rhymes.game.entity.elements.path.PathNode;
import com.rhymes.game.entity.elements.path.PathSet;
//import com.rhymes.game.entity.elements.testtile.LevelInfo;
import com.rhymes.helpers.Helper;

public class xmlEnemyReader {
	public static String bkgFile="";
	public static RoadMap road;
	public static ArrayList<LevelEnemyInfo> levelEnemy;
	public static float moneyGold=0f;
	public static Path path = new Path();

	public void readXml(int roundNum){
		path = new Path();
		try {
			levelEnemy=new ArrayList<LevelEnemyInfo>();
			String filePath=	AssetConstants.FILE_LEVEL_ROUND +roundNum+".xml";
			FileHandle fh = Gdx.files.internal(filePath);
			if (fh == null)
				Helper.println("fh null");

			if (fh.exists())
				Helper.println("\n\n\nfile exists");
			else
				Helper.println("\n\n\nfile does not exists");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fh.read());
			doc.getDocumentElement().normalize();
			NodeList levelList = doc.getElementsByTagName("Level");
			for (int temp = 0; temp < levelList.getLength(); temp++) {
				LevelEnemyInfo levelEnemyInfo=new LevelEnemyInfo();
				NodeList RopeNode = doc.getElementsByTagName("Enemy");
				for (int i = 0; i < RopeNode.getLength(); i++) {
					Node SingleNode = RopeNode.item(i);
					Element eElem = (Element) SingleNode.getParentNode();
					String attr = eElem.getAttribute("number");
					//Helper.println("number: "+attr);
					String Num = (temp+1) + "";
					if (attr.compareTo(Num) == 0) {
						EnemyInfo enemy=new EnemyInfo();
						Element eElemSin = (Element) SingleNode;
						enemy.EnemyType=Integer.parseInt(eElemSin.getAttribute("type"));
						enemy.EnemySpeed=Integer.parseInt(eElemSin.getAttribute("speed"));
						enemy.EnemyLife=Integer.parseInt(eElemSin.getAttribute("life"));
						enemy.EnemyDamage=Integer.parseInt(eElemSin.getAttribute("damage"));
						enemy.EnemyRange=Integer.parseInt(eElemSin.getAttribute("range"));
						enemy.EnemyValue=Integer.parseInt(eElemSin.getAttribute("val"));
						enemy.Enemyshooting=Integer.parseInt(eElemSin.getAttribute("shooting"));
						levelEnemyInfo.enemy.add(enemy);
					}
				}
				
				NodeList decsNode = doc.getElementsByTagName("desc");
				for (int i = 0; i < decsNode.getLength(); i++) {
					Node SingleNode = decsNode.item(i);
					Element eElem = (Element) SingleNode.getParentNode();
					String attr = eElem.getAttribute("number");
					String Num = (temp+1) + "";
					if (attr.compareTo(Num) == 0) {
						Element eElemSin = (Element) SingleNode;
						String decs=eElemSin.getAttribute("string");
						levelEnemyInfo.decs=decs;
//						Helper.println("Decs: "+decs);
						}
				}
//				Helper.println("levelEnemyInfo : "+levelEnemyInfo.enemy.size());
				if(levelEnemyInfo.enemy.size()>0){
				levelEnemy.add(levelEnemyInfo);
			}
			
		}
//			Helper.println("level: "+levelEnemy.size());
			NodeList ropeNodeList = doc.getElementsByTagName("RopeNode");
			System.out.println("ropeNodeList.Length: " + ropeNodeList.getLength());
			road=new RoadMap();
			PathNode p;
			for (int tempoo = 0; tempoo < ropeNodeList.getLength(); tempoo++) {
				Node eNode = ropeNodeList.item(tempoo);
				Element eeElement = (Element) eNode;
				int RoadNodeNumber = Integer.parseInt(eeElement.getAttribute("nodeNumber"));
//				float posX = (Gdx.graphics.getHeight()-Float.parseFloat(eeElement.getAttribute("y")))*LevelInfo.ratioY;
				float posX = (Float.parseFloat(eeElement.getAttribute("y")))*LevelInfo.ratioX;
//				Helper.println("XX: "+posX);
//				Helper.println("RatoiX: "+LevelInfo.ratioX);
//				Helper.println("RatoiY: "+LevelInfo.ratioY);
				float posY = (Float.parseFloat(eeElement.getAttribute("x")))*LevelInfo.ratioY;
//				Helper.println("YY: "+posY);
				p=new PathNode();
				p.setX(posX); 
				p.setY(posY);
				road.nodes.addLast(p);
//				Helper.println("node: "+RoadNodeNumber+"X: "+posX+"Y: "+posY);			
			}

			NodeList bkgList = doc.getElementsByTagName("background");
//			System.out.println("bkgList.Length: " + bkgList.getLength());
			for (int ttempoo = 0; ttempoo < bkgList.getLength(); ttempoo++){
				Node tNode = bkgList.item(ttempoo);
				Element tElement = (Element) tNode;
			    bkgFile = tElement.getAttribute("file");
//			    Helper.println("bkg"+bkgFile);
			    }
			
			NodeList mList = doc.getElementsByTagName("money");
//			System.out.println("mList.Length: " + mList.getLength());
			for (int ttempoo = 0; ttempoo < mList.getLength(); ttempoo++){
				Node tNode = mList.item(ttempoo);
				Element tElement = (Element) tNode;
			    moneyGold = Float.parseFloat(tElement.getAttribute("val"));
//			Helper.println("Gold"+moneyGold);    
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		
		for(PathNode p:this.road.nodes)
		{
			path.addLast(p.getLocation());
		}
	}
}
