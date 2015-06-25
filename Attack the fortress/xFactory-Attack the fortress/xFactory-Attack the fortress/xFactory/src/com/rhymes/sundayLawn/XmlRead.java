package com.rhymes.sundayLawn;

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
import com.rhymes.helpers.Helper;

public class XmlRead {

	/**
	 * @param args
	 */
	
	public static Array<World> worlds = new Array<World>();
    private static World world;
    private static Path pathh;
    private static Switchid switchid;
    public static int TRow=0;
    public static int TCol=0;

    public static Array<Path> paths = new Array<Path>();
    public static Array<World> Elements = new Array<World>();
    public static Array<Switchid> switches = new Array<Switchid>();
    
    
	public static void Read(String filePath) {
		paths.clear();
		Elements.clear();
		worlds.clear();
		switches.clear();
		
		//ropes.clear();

		try {

			Helper.printKeyVal("File path", AssetConstants.FILE_LEVEL_INFO);
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

			NodeList WorldList = doc.getElementsByTagName("StartWorld");
			Node WorldNode = WorldList.item(0);
			Element WorldElement = (Element) WorldNode;
		    TRow = Integer.parseInt(WorldElement.getAttribute("TotalRow"));
			TCol = Integer.parseInt(WorldElement.getAttribute("TotalColumn"));
			
			
			NodeList TileList = doc.getElementsByTagName("Tile");
			for (int temp = 0; temp < TileList.getLength(); temp++) 
		    {
				world =new World();
				Node TileNode = TileList.item(temp);
				Element TileElement = (Element) TileNode;
				//String Number = TileElement.getAttribute("Number");
				world.row =TRow-Integer.parseInt(TileElement.getAttribute("row"))-1;
				world.col = Integer.parseInt(TileElement.getAttribute("col"));
				world.tileTypes = Integer.parseInt(TileElement.getAttribute("tileTypes"));
				world.tileName = TileElement.getAttribute("tileName");
				world.tileImageName = TileElement.getAttribute("tileImageName");
				worlds.add(world);
			}
			NodeList EList = doc.getElementsByTagName("Element");
			for (int tempo = 0; tempo < EList.getLength(); tempo++) 
		    {
				world =new World();
				Node ElementNode = EList.item(tempo);
				Element EElement = (Element) ElementNode;
				//String Number = EElement.getAttribute("Number");
				world.row =TRow-Integer.parseInt(EElement.getAttribute("row"))-1;
				world.col = Integer.parseInt(EElement.getAttribute("col"));
				world.tileTypes = Integer.parseInt(EElement.getAttribute("tileTypes"));
				world.tileName = EElement.getAttribute("tileName");
				world.tileImageName = EElement.getAttribute("tileImageName");
				Elements.add(world);
			}

			//<Path Number="0">
			//<PathTile Number="0" row="2" col="2" tileTypes="5" tileName="Path" tileImageName="1" />

			NodeList PathList = doc.getElementsByTagName("Path");
			//System.out.println("jList.Length: " + nList.getLength());
			for (int tempt = 0; tempt < PathList.getLength(); tempt++) {
				
				NodeList PathTileNode = doc.getElementsByTagName("PathTile");
				pathh=new Path();

				for (int i = 0; i < PathTileNode.getLength(); i++) {
					Node SinglejNode = PathTileNode.item(i);
					Element ejElem = (Element) SinglejNode.getParentNode();
					String jNumber = ejElem.getAttribute("Number");
					String Num = tempt + "";
					if (jNumber.compareTo(Num) == 0) {
						world=new World();
						Element ejElemSin = (Element) SinglejNode;
						//String Number = ejElemSin.getAttribute("Number");
						world.row =TRow-Integer.parseInt(ejElemSin.getAttribute("row"))-1;
						world.col = Integer.parseInt(ejElemSin.getAttribute("col"));
						world.tileTypes = Integer.parseInt(ejElemSin.getAttribute("tileTypes"));
						world.tileName = ejElemSin.getAttribute("tileName");
						world.tileImageName = ejElemSin.getAttribute("tileImageName");

						pathh.path.add(world);
					}
				}
				paths.add(pathh);
				//Helper.println(paths.size+"p");
				
			}
			///////////////////////////////
			NodeList switchList = doc.getElementsByTagName("SwitchTile");
			//System.out.println("jList.Length: " + nList.getLength());
			for (int tempt = 0; tempt < switchList.getLength(); tempt++) {
				switchid=new Switchid();
				Node SinglejSwitch = switchList.item(tempt);
				Element ElemSwitch = (Element) SinglejSwitch;
				//String jNumber = ElemSwitch.getAttribute("Number");
					//String Number = ejElemSin.getAttribute("Number");
					switchid.switchid.row =TRow-Integer.parseInt(ElemSwitch.getAttribute("row"))-1;
					switchid.switchid.col = Integer.parseInt(ElemSwitch.getAttribute("col"));
					switchid.switchid.tileTypes = Integer.parseInt(ElemSwitch.getAttribute("tileTypes"));
					switchid.switchid.tileName = ElemSwitch.getAttribute("tileName");
					switchid.switchid.tileImageName = ElemSwitch.getAttribute("tileImageName");
				
				NodeList PillerList = doc.getElementsByTagName("PillerTile");
				//pathh=new Path();

				for (int i = 0; i < PillerList.getLength(); i++) {
					Node SinglePillerNode = PillerList.item(i);
					Element ejElem = (Element) SinglePillerNode.getParentNode();
					String jNumber = ejElem.getAttribute("Number");
					String Num = tempt + "";
					if (jNumber.compareTo(Num) == 0) {
						world=new World();
						Element ejElemSin = (Element) SinglePillerNode;
						//String Number = ejElemSin.getAttribute("Number");
						world.row =TRow-Integer.parseInt(ejElemSin.getAttribute("row"))-1;
						world.col = Integer.parseInt(ejElemSin.getAttribute("col"));
						world.tileTypes = Integer.parseInt(ejElemSin.getAttribute("tileTypes"));
						world.tileName = ejElemSin.getAttribute("tileName");
						world.tileImageName = ejElemSin.getAttribute("tileImageName");
						switchid.pillers.addLast(world);
						//pathh.path.add(world);
					}
					Helper.println(switchid.pillers.size()+"p");
				}
				switches.add(switchid);
				Helper.println(switches.size+"s");
				
			}

			///////////////////////////////////
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
	}
}
