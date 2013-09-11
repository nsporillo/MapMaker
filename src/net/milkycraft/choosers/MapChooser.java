package net.milkycraft.choosers;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import net.milkycraft.MapMaker;

public class MapChooser extends Chooser {

	private static final long serialVersionUID = 7138239537667411655L;

	public MapChooser(MapMaker maker) {
		super(maker, "Select map", JFileChooser.FILES_AND_DIRECTORIES);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			maker.open(file);
		}
		frame.setVisible(false);
	}
}
