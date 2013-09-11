package net.milkycraft;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Map {

	private String name;
	private Point spawn;
	private BufferedImage background;
	private List<Sprite> sprites = new ArrayList<Sprite>();
	private List<Barrier> barriers = new ArrayList<Barrier>();

	public Map(BufferedImage b) {
		this.setBackground(b);
		this.spawn = new Point(0,0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Sprite> getSprites() {
		return sprites;
	}

	public List<Barrier> getBarriers() {
		return barriers;
	}

	public Point getSpawn() {
		return spawn;
	}

	public void setSpawn(Point spawn) {
		this.spawn = spawn;
	}

	public BufferedImage getBackground() {
		return background;
	}

	public void setBackground(BufferedImage background) {
		this.background = background;
	}

	public void addSprite(Sprite s) {
		this.sprites.add(s);
	}
	
	public void addBarrier(Barrier b) {
		this.barriers.add(b);
	}
}
