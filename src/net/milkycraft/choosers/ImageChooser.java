package net.milkycraft.choosers;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;

import net.milkycraft.MapMaker;

public class ImageChooser extends Chooser {
	
	private static final long serialVersionUID = -873480181270176074L;

	public ImageChooser(MapMaker maker) {
		super(maker, "Select background", JFileChooser.FILES_ONLY);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int returnVal = fc.showOpenDialog(ImageChooser.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			maker.selectedBackground(file);
			maker.repaint();
		}
		frame.setVisible(false);
	}
}
