package net.milkycraft.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import net.milkycraft.CanvasPanel;
import net.milkycraft.MapMaker;
import net.milkycraft.Sprite;
import net.milkycraft.choosers.SpriteChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SpriteEditor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Image spr;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the dialog.
	 */
	public SpriteEditor(final Sprite s, final MapMaker mm, final CanvasPanel cp) {
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Sprite Editor");
		JDialog.setDefaultLookAndFeelDecorated(true);
        super.setLocationRelativeTo(null);
		setBounds(100, 100, 270, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JPanel panel = new SpritePanel(s.getImage());
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SpriteChooser sc = new SpriteChooser(mm, "", JFileChooser.FILES_ONLY,
						SpriteEditor.this);
				cp.repaint();
			}
		});
		panel.setBounds(20, 17, 64, 64);
		contentPanel.add(panel);

		textField = new JTextField();
		textField.setBounds(96, 17, 134, 28);
		contentPanel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(96, 53, 134, 28);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (spr != null) {
					s.setImage(spr);
				}
				if (textField.getText() != null && !textField.getText().equals("")) {
					s.setId(0, Integer.valueOf(textField.getText()));
				}
				if (textField_1.getText() != null && !textField_1.getText().equals("")) {
					s.setId(1, Integer.valueOf(textField_1.getText()));
				}
				cp.repaint();
				setVisible(false);
				dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);

		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public void setSprite(Image i) {
		spr = i;
	}
}
