package net.milkycraft.window;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class SpritePanel extends JPanel {

	private static final long serialVersionUID = -1973219070784401999L;
	private Image image;
	public SpritePanel(Image image2 ) {
		this.image = image2;
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);            
    }
}
