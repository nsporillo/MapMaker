package net.milkycraft;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.UUID;

public class Sprite implements Movable {
	
	private String uuid;
	private Rectangle rect;
	private Image i;
	private int x,y;
	private int[] payload; //0 = Ammo, 1 = Points
	
	public Sprite(Image i, Point p, int[] id) {
		this.setUuid(UUID.randomUUID().toString().substring(16));
		this.i = i;
		this.x = p.x;
		this.y = p.y;
		this.payload = id;
		rect = new Rectangle(x,y, i.getWidth(null), i.getHeight(null));
	}

	public Image getImage() {
		return i;
	}

	public void setImage(Image i) {
		this.i = i;
		rect = new Rectangle(x,y, i.getWidth(null), i.getHeight(null));
	}

	public Point getPoint() {
		return new Point(x,y);
	}
	
	public Rectangle getRectangle(){
		return rect;
	}

	public void setPoint(Point p) {
		this.x = p.x;
		this.y = p.y;
		rect = new Rectangle(x,y, i.getWidth(null), i.getHeight(null));
	}
	
	public int getId(int i) {
		return payload[i];
	}
	
	public void setId(int k, int v){
		this.payload[k] = v;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}	
}
