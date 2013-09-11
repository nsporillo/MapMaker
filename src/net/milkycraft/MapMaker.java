package net.milkycraft;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import net.milkycraft.choosers.ImageChooser;
import net.milkycraft.choosers.MapChooser;
import net.milkycraft.choosers.SaveChooser;
import net.milkycraft.window.MapEditor;

public class MapMaker {

	private JFrame frame;
	private CanvasPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {					
					MapMaker window = new MapMaker();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MapMaker() {
		MapEditor editor = new MapEditor(MapMaker.this);
		panel = new CanvasPanel(this);
		initialize();
	}
	
	public void nameMap(String name){
		this.panel.getMap().setName(name);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("MilkyRunner Map maker");
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBounds(5, 10, 640, 460);
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(panel);
		this.initActions();
	}

	public void repaint() {
		this.panel.repaint();
	}

	public void save(File f) {
		try {
			MapSaver.saveMap(panel.getMap(), f);
		} catch (Exception i) {
			i.printStackTrace();
		}
	}

	public void open(File f) {
		this.panel.setMap(MapLoader.loadMap(f));
		this.panel.repaint();
	}

	public void newMap() {
		this.panel = new CanvasPanel(this);
		this.initActions();
	}

	public void selectedBackground(File file) {
		try {
			panel.getMap().setBackground(ImageIO.read(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initActions() {
		Action undo = new AbstractAction() {
			private static final long serialVersionUID = -5217980197624149739L;

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.undo();
			}
		};
		Action toggle = new AbstractAction() {
			private static final long serialVersionUID = -5217980197624149739L;

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.toggleMode();
				panel.repaint();
			}
		};
		Action background = new AbstractAction() {
			private static final long serialVersionUID = -52179807624149739L;

			@Override
			public void actionPerformed(ActionEvent e) {
				ImageChooser fc = new ImageChooser(MapMaker.this);
			}
		};
		Action save = new AbstractAction() {
			private static final long serialVersionUID = -52179801976149739L;

			@Override
			public void actionPerformed(ActionEvent e) {
				SaveChooser fc = new SaveChooser(MapMaker.this);
			}
		};
		Action open = new AbstractAction() {
			private static final long serialVersionUID = -521798019762449739L;

			@Override
			public void actionPerformed(ActionEvent e) {
				MapChooser fc = new MapChooser(MapMaker.this);
			}
		};
		Action placeMilk = new AbstractAction() {
			private static final long serialVersionUID = -5217980197624149739L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					panel.getMap().addSprite(new Milk(get("milk.png"), panel.getMousePosition()));
					panel.repaint();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		};
		Action placeAmmo = new AbstractAction() {
			private static final long serialVersionUID = -5217980197624149739L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					panel.getMap().addSprite(new Ammo(get("ammo.png"), panel.getMousePosition()));
					panel.repaint();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		};
		Action replace = new AbstractAction() {
			private static final long serialVersionUID = -5217980197624149739L;

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.a();
			}
		};
		
		Action spawn = new AbstractAction() {
			private static final long serialVersionUID = -5217980197624149739L;

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.getMap().setSpawn(panel.getMousePosition());
				panel.repaint();
			}
		};
		panel.getInputMap().put(KeyStroke.getKeyStroke("U"), "undo");
		panel.getActionMap().put("undo", undo);
		panel.getInputMap().put(KeyStroke.getKeyStroke("T"), "toggle");
		panel.getActionMap().put("toggle", toggle);
		panel.getInputMap().put(KeyStroke.getKeyStroke("B"), "background");
		panel.getActionMap().put("background", background);
		panel.getInputMap().put(KeyStroke.getKeyStroke("S"), "save");
		panel.getActionMap().put("save", save);
		panel.getInputMap().put(KeyStroke.getKeyStroke("O"), "open");
		panel.getActionMap().put("open", open);
		panel.getInputMap().put(KeyStroke.getKeyStroke("A"), "ammo");
		panel.getActionMap().put("ammo", placeAmmo);
		panel.getInputMap().put(KeyStroke.getKeyStroke("M"), "milk");
		panel.getActionMap().put("milk", placeMilk);
		panel.getInputMap().put(KeyStroke.getKeyStroke("Z"), "replace");
		panel.getActionMap().put("replace", replace);
		panel.getInputMap().put(KeyStroke.getKeyStroke("P"), "spawn");
		panel.getActionMap().put("spawn", spawn);
	}

	public BufferedImage get(String s) throws Exception {
		return ImageIO.read(new File(s));
	}
}
