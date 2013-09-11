package net.milkycraft;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import net.milkycraft.window.SpriteEditor;

public class CanvasPanel extends JPanel {

	private static final long serialVersionUID = 2333538490364764898L;
	private MapMaker mm;
	private Map map;
	private String message = "";
	private boolean mode;
	private Barrier curShape;
	private Movable obj;

	public CanvasPanel(MapMaker mm) {
		mode = true;
		this.mm = mm;
		map = new Map(null);
		setBackground(Color.WHITE);
		addMouseListener(clickListener);
		addMouseMotionListener(moveListener);
	}

	public CanvasPanel(Map map, MapMaker mm) {
		this.map = map;
		this.mode = true;
		this.mm = mm;
		setBackground(Color.WHITE);
		addMouseListener(clickListener);
		addMouseMotionListener(moveListener);
	}

	@Override
	protected void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2;
		if (this.getMap().getBackground() != null) {
			g.drawImage(map.getBackground(), 0, 0, null);
		}
		g.drawString(message, 10, 20);
		g.drawImage(Utility.getImage("x", 13), map.getSpawn().x, map.getSpawn().y, null);
		for (Sprite s : map.getSprites()) {
			g.drawImage(s.getImage(), s.getPoint().x, s.getPoint().y, null);
		}
		for (Barrier barr : map.getBarriers()) {
			barr.draw(g);
		}
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		if (curShape == null)
			return;
		curShape.draw(g);
	}

	public void a() {
		obj = null;
	}

	private MouseListener clickListener = new MouseAdapter() {

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.isAltDown()) {
				for (Barrier b : map.getBarriers()) {
					if (b.getRectangle().contains(e.getPoint())) {
						obj = b;
					}
				}
				for (Sprite s : map.getSprites()) {
					if (s.getRectangle().contains(e.getPoint())) {
						obj = s;
					}
				}
				return;
			} else if (e.isShiftDown()) {
				for (Sprite s : map.getSprites()) {
					if (s.getRectangle().contains(e.getPoint())) {
						SpriteEditor editor = new SpriteEditor(s, mm, CanvasPanel.this);
						editor.setVisible(true);
					}
				}
				return;
			}
			curShape = new Barrier(e.getPoint(), e.getPoint());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(curShape == null) return;
			Barrier b = new Barrier(curShape.getClickP(), e.getPoint());
			correct(b);
			map.getBarriers().add(b);
			curShape = null;
		}
	};

	public void undo() {
		int size = map.getBarriers().size();
		if (size > 0) {
			map.getBarriers().remove(size - 1);
		}
		this.message = "Undid action";
		super.repaint();
	}

	public void toggleMode() {
		this.message = "Toggled correction mode";
		this.mode = !mode;
	}

	public Map getMap() {
		return map;
	}
	
	public void setMap(Map map){
		this.map = map;
	}

	public void correct(Barrier b) {
		if (mode) {
			b.setTwoY(b.getClickP().y + 10);

		} else {
			b.setTwoX(b.getClickP().x + 10);
		}
		super.repaint();
	}

	private MouseMotionListener moveListener = new MouseMotionListener() {

		@Override
		public void mouseDragged(MouseEvent e) {
			if (e.isAltDown()) {
				return;
			}
			curShape = new Barrier(curShape.getClickP(), e.getPoint());
			message = "Added new barrier: " + curShape.getClickP().x + ", "
					+ curShape.getClickP().y + " - " + e.getPoint().x + ", "
					+ e.getPoint().y;
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (obj != null) {
				if (obj instanceof Barrier) {
					Barrier b = (Barrier) obj;
					b.getRectangle().setLocation(e.getPoint());
					repaint();
				} else if (obj instanceof Sprite) {
					Sprite spr = (Sprite) obj;
					spr.setPoint(e.getPoint());
					repaint();
				}
			}
		}
	};
}
