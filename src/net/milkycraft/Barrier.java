package net.milkycraft;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.UUID;

public class Barrier implements Movable {

	private String uuid;
	private Rectangle rect;
	
	private int x1,y1,x2,y2;

	public Barrier(Point p1, Point p2) {	
		this.setUuid(UUID.randomUUID().toString());
		this.x1 = p1.x;
		this.y1 = p1.y;
		this.x2 = p2.x;
		this.y2 = p2.y;
		rect = new Rectangle(x1,y1,Math.max(x1, x2) - Math.min(x1, x2),Math.max(y1, y2) - Math.min(y1, y2));
	}
	
	public Barrier(int x, int y, int width, int height){
		this.setUuid(UUID.randomUUID().toString());
		rect = new Rectangle(x,y,width, height);
		this.x1 = (int)rect.getMinX();
		this.x2 = (int)rect.getMaxX();
		this.y1 = (int)rect.getMinY();
		this.y2 = (int)rect.getMaxY();
	}

	public Point getClickP() {
		return new Point(x1,y1);
	}
	
	public Point getPoint2(){
		return new Point(x2,y2);
	}
	
	public Rectangle getRectangle(){
		return rect;
	}

	public void draw(Graphics2D g) {
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
	}
	

	public void setOneX(int x) {
		this.x1 =x;
		rect = new Rectangle(x1,y1,Math.max(x1, x2) - Math.min(x1, x2),Math.max(y1, y2) - Math.min(y1, y2));
	}

	public void setOneY(int y) {
		this.y1 = y;
		rect = new Rectangle(x1,y1,Math.max(x1, x2) - Math.min(x1, x2),Math.max(y1, y2) - Math.min(y1, y2));
	}
	
	public void setTwoX(int x) {
		this.x2 =x; 
		rect = new Rectangle(x1,y1,Math.max(x1, x2) - Math.min(x1, x2),Math.max(y1, y2) - Math.min(y1, y2));
	}

	public void setTwoY(int y) {
		this.y2 = y;
		rect = new Rectangle(x1,y1,Math.max(x1, x2) - Math.min(x1, x2),Math.max(y1, y2) - Math.min(y1, y2));
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}