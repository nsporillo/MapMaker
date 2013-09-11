package net.milkycraft;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class MapSaver {

	public static void saveMap(Map m, File file)  {
		if (!file.isDirectory()) {
			return;
		}		
		try {
			File sprdir = new File(file, "assets" + File.separator);
			if(!sprdir.exists()){
				sprdir.mkdir();
			}
			String uuid = UUID.randomUUID().toString().substring(16);
			File bc = new File(sprdir, uuid + ".png");
			if (m.getBackground() != null) {
				ImageIO.write(m.getBackground(), "png", bc);
			} else {
				ImageIO.write(Utility.getImage("NaN", Color.GRAY, 60), "png", bc);
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(new File(file, "level.map")));
			JSONObject json = new JSONObject();
			JSONArray barr = new JSONArray();
			for(Barrier b : m.getBarriers()) {
				JSONObject bobj = new JSONObject();
				bobj.put("x", b.getRectangle().x);
				bobj.put("y", b.getRectangle().y);
				bobj.put("w", b.getRectangle().width);
				bobj.put("h", b.getRectangle().height);
				barr.put(bobj);
			}
			JSONArray sprs = new JSONArray();
			for(Sprite spr : m.getSprites()) {
				ImageIO.write((BufferedImage) spr.getImage(), "png", new File(sprdir, spr.getUuid() + ".png"));
				JSONObject bobj = new JSONObject();
				bobj.put("type", spr.getClass().getSimpleName().toLowerCase());
				bobj.put("asset", spr.getUuid());
				bobj.put("x", spr.getRectangle().x);
				bobj.put("y", spr.getRectangle().y);
				//bobj.put("w", spr.getRectangle().width);
				//bobj.put("h", spr.getRectangle().height);
				JSONArray inner = new JSONArray();
				inner.put(spr.getId(0));
				inner.put(spr.getId(1));
				bobj.put("payload", inner);
				sprs.put(bobj);
			}
			json.put("name", m.getName());
			JSONArray spawn = new JSONArray();
			spawn.put(m.getSpawn().x);
			spawn.put(m.getSpawn().y);
			json.put("spawn", spawn);
			json.put("asset", uuid);
			json.put("barriers", barr);
			json.put("sprites",sprs);			
			out.write(json.toString());
			out.flush();
			out.close();			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	private static String getPrettyJSON(JSONObject json){
		JSONTokener tk = new JSONTokener(json.toString());
		JSONObject f = new JSONObject(tk);
		return f.toString(4);
	}
	
	
}
