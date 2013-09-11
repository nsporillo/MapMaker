package net.milkycraft.choosers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.milkycraft.MapMaker;

public class Chooser extends JPanel implements ActionListener {

	private static final long serialVersionUID = 7138239537667411655L;
	protected MapMaker maker;
	protected JFrame frame;
	protected JFileChooser fc;

	public Chooser(MapMaker maker, String title, int mode) {
		super(new BorderLayout());
		fc = new JFileChooser();
		fc.setFileSelectionMode(mode);
		this.maker = maker;
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		final JButton jbutt = new JButton("Select");
		JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setLocationRelativeTo(null);
		frame.add(jbutt);		
		frame.pack();
		frame.setVisible(true);		
		;
		jbutt.addActionListener(this);
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		//
	}
	
	public MapMaker getMaker() {
		return maker;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JFileChooser getFileChooser() {
		return fc;
	}
}
