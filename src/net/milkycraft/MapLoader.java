package net.milkycraft;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class MapLoader {

	public static Map loadMap(File saveDir){
		Map m = null;
		try {
			for(File f : saveDir.listFiles()){
				if(f.getName().endsWith(".map")) {
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(new FileReader(f));
					org.json.simple.JSONObject json = (org.json.simple.JSONObject) obj;
					JSONObject jso = new JSONObject(json.toJSONString());
					m = new Map(ImageIO.read(new File(saveDir, "assets" + File.separator + jso.getString("asset") + ".png")));
					JSONArray sja = jso.getJSONArray("sprites");
					for(int i = 0; i < sja.length(); i++){
						JSONObject lobj = sja.getJSONObject(i);
						BufferedImage bi = ImageIO.read(new File(saveDir, "assets" + File.separator + lobj.getString("asset") + ".png"));
						Point p = new Point(lobj.getInt("x"), lobj.getInt("y"));
						JSONArray pl = lobj.getJSONArray("payload");
						Sprite s = new Sprite(bi, p, new int[]{pl.getInt(0), pl.getInt(1)});
						m.addSprite(s);
					}
					JSONArray bja = jso.getJSONArray("barriers");
					for(int i = 0; i < bja.length(); i++){
						JSONObject lobj = bja.getJSONObject(i);
						Barrier b = new Barrier(lobj.getInt("x"), lobj.getInt("y"), lobj.getInt("w"), lobj.getInt("h"));
						m.addBarrier(b);
					}
				}
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return m;
	}
}
