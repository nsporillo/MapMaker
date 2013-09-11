package net.milkycraft.choosers;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import net.milkycraft.MapMaker;
import net.milkycraft.window.SpriteEditor;

public class SpriteChooser extends Chooser{

	private static final long serialVersionUID = 7639447041139168325L;
	private SpriteEditor editor;

	public SpriteChooser(MapMaker maker, String title, int mode, SpriteEditor editor) {
		super(maker, title, mode);
		this.editor = editor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				editor.setSprite(ImageIO.read(file));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		frame.setVisible(false);
	}
}
