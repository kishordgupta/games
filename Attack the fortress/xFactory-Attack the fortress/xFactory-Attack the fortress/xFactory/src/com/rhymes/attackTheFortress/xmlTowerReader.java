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
import com.rhymes.game.entity.elements.path.PathNode;
import com.rhymes.helpers.Helper;

public class xmlTowerReader {
	public static ArrayList<TowerInfo> towers;
	public void readXml(){
		try {
			towers=new ArrayList<TowerInfo>();
//			levelEnemy=new ArrayList<LevelEnemyInfo>();
			String filePath=	AssetConstants.FILE_TOWER;
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
			
			NodeList levelList = doc.getElementsByTagName("Tower");
			for (int temp = 0; temp < levelList.getLength(); temp++) {
				TowerInfo tI=new TowerInfo();
				Node TowerNode=levelList.item(temp);
				Element towerElement=(Element) TowerNode;
				float towerNumber=Float.parseFloat(towerElement.getAttribute("number"));
				float towermaxLife=Float.parseFloat(towerElement.getAttribute("maxLife"));
				float towerSpeed=Float.parseFloat(towerElement.getAttribute("speed"));
				float towerDamage=Float.parseFloat(towerElement.getAttribute("damage"));
				float towerRange=Float.parseFloat(towerElement.getAttribute("range"));
				float towerCost=Float.parseFloat(towerElement.getAttribute("valor"));
				tI.number=towerNumber;
				tI.maxLife=towermaxLife;
				tI.speed=towerSpeed;
				tI.damage=towerDamage;
				tI.range=towerRange;
				tI.cost=towerCost;
				
				NodeList SpeedUpNode = doc.getElementsByTagName("speed_upgrade");
				for (int i = 0; i < SpeedUpNode.getLength(); i++) {
					Node SingleNode = SpeedUpNode.item(i);
					Element eElem = (Element) SingleNode.getParentNode();
					String attr = eElem.getAttribute("number");
					//Helper.println("number: "+attr);
					String Num = (temp+1) + "";
					if (attr.compareTo(Num) == 0) {
						Element eElemSin = (Element) SingleNode;
						int speedUpval=Integer.parseInt(eElemSin.getAttribute("val"));
//						Helper.println("speedUpval: "+Float.parseFloat(eElemSin.getAttribute("val")));
						int speedUpcost=Integer.parseInt(eElemSin.getAttribute("cost"));
//						Helper.println("speedUpcost: "+Float.parseFloat(eElemSin.getAttribute("cost")));
						tI.speedUpgradeValue.addLast(speedUpval);
						tI.speedUpgradeCost.addLast(speedUpcost);
					}
				}
				
				NodeList RangeUpNode = doc.getElementsByTagName("range_upgrade");
				for (int i = 0; i < RangeUpNode.getLength(); i++) {
					Node SingleNode = RangeUpNode.item(i);
					Element eElem = (Element) SingleNode.getParentNode();
					String attr = eElem.getAttribute("number");
					//Helper.println("number: "+attr);
					String Num = (temp+1) + "";
					if (attr.compareTo(Num) == 0) {
						Element eElemSin = (Element) SingleNode;
						int rangeUpval=Integer.parseInt(eElemSin.getAttribute("val"));
//						Helper.println("rangeUpval: "+Float.parseFloat(eElemSin.getAttribute("val")));
						int rangeUpcost=Integer.parseInt(eElemSin.getAttribute("cost"));
//						Helper.println("rangeUpcost: "+Float.parseFloat(eElemSin.getAttribute("cost")));
						tI.rangeUpgradeValue.addLast(rangeUpval);
						tI.rangeUpgradeCost.addLast(rangeUpcost);
					}
				}
				
				NodeList damageUpNode = doc.getElementsByTagName("damage_upgrade");
				for (int i = 0; i < damageUpNode.getLength(); i++) {
					Node SingleNode = damageUpNode.item(i);
					Element eElem = (Element) SingleNode.getParentNode();
					String attr = eElem.getAttribute("number");
					//Helper.println("number: "+attr);
					String Num = (temp+1) + "";
					if (attr.compareTo(Num) == 0) {
						Element eElemSin = (Element) SingleNode;
						int damageUpval=Integer.parseInt(eElemSin.getAttribute("val"));
//						Helper.println("damageUpval: "+Float.parseFloat(eElemSin.getAttribute("val")));
						int damageUpcost=Integer.parseInt(eElemSin.getAttribute("cost"));
//						Helper.println("damageUpcost: "+Float.parseFloat(eElemSin.getAttribute("cost")));
						tI.damageUpgradeValue.addLast(damageUpval);
						tI.damageUpgradeCost.addLast(damageUpcost);
					}
				}
//				Helper.println("Updaterangeval size: "+tI.damageUpgradeValue.size());
//				Helper.println("Updaterangecost size: "+tI.damageUpgradeCost.size());
//				Helper.println("Updatespeedval size: "+tI.speedUpgradeValue.size());
//				Helper.println("Updatespeedcost size: "+tI.speedUpgradeCost.size());
//				Helper.println("Updatedamageval size: "+tI.rangeUpgradeValue.size());
//				Helper.println("Updatedamagecost size: "+tI.rangeUpgradeCost.size());

				towers.add(tI);
				}
//			Helper.println("TNum: "+towers.size());		

			//Helper.println("Tower Number: "+xmlTowerReader.towers.size());
			
			
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
