package net.milkycraft.choosers;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;

import net.milkycraft.MapMaker;

public class SaveChooser extends Chooser {

	private static final long serialVersionUID = 7138239537667411655L;

	public SaveChooser(MapMaker maker) {
		super(maker, "Select save file", JFileChooser.DIRECTORIES_ONLY);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int returnVal = fc.showOpenDialog(SaveChooser.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			maker.save(file);
		}
		frame.setVisible(false);
	}
}
