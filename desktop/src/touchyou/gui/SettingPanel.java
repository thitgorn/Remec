package touchyou.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import touchyou.Command;
import touchyou.util.Controller;
import touchyou.util.GUIUtil;

/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class SettingPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -5468288983318943380L;
    private JTextField iconpath;
    private JTextField combination;
    private JLabel profilename;
    private JRadioButton rdbtnImportFromComputer;
    private JRadioButton rdbtnSingleTouch;
    private JRadioButton rdbtnFollow;
    private JRadioButton rdbtnIconNone;
    private JLabel lblText;
    private JRadioButton rdbtnCommandAsLabel;
    private JRadioButton rdbtnCustomLabel;
    private JTextField customLabel;
    private JRadioButton rdbtnTextNone;
    private JButton btnDeleteButton;

    /**
     * Create the panel.
     */
    public SettingPanel() {
	setBackground(GUIUtil.getBackgroundColor());
	// setBorder(GuiUtil.getBorder());
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 0, 28, 90, 72, 0 };
	gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
	gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		0.0, 0.0, 0.0, Double.MIN_VALUE };
	setLayout(gridBagLayout);

	JLabel lblProfile = new JLabel("Profile:");
	lblProfile.setOpaque(false);
	lblProfile.setForeground(GUIUtil.getForegroundColor());
	GridBagConstraints gbc_lblProfile = new GridBagConstraints();
	gbc_lblProfile.anchor = GridBagConstraints.WEST;
	gbc_lblProfile.insets = new Insets(0, 0, 5, 5);
	gbc_lblProfile.gridx = 0;
	gbc_lblProfile.gridy = 0;
	add(lblProfile, gbc_lblProfile);

	profilename = new JLabel(Controller.getInstance().getProfileName());
	profilename.setOpaque(false);
	profilename.setForeground(GUIUtil.getForegroundColor());
	GridBagConstraints gbc_profilename = new GridBagConstraints();
	gbc_profilename.gridwidth = 2;
	gbc_profilename.fill = GridBagConstraints.VERTICAL;
	gbc_profilename.anchor = GridBagConstraints.EAST;
	gbc_profilename.insets = new Insets(0, 0, 5, 0);
	gbc_profilename.gridx = 2;
	gbc_profilename.gridy = 0;
	add(profilename, gbc_profilename);

	JLabel lblNewLabel = new JLabel("Command:");
	lblNewLabel.setOpaque(false);
	lblNewLabel.setForeground(GUIUtil.getForegroundColor());
	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel.gridx = 0;
	gbc_lblNewLabel.gridy = 1;
	add(lblNewLabel, gbc_lblNewLabel);

	combination = new JTextField();
	combination.setHorizontalAlignment(SwingConstants.TRAILING);
	combination.setEditable(false);
	combination.addKeyListener(new KeyListener() {
	    private List<String> key = new ArrayList<>();

	    @Override
	    public void keyPressed(KeyEvent e) {
		/*
		 * ignore unknown key code (ex. Fn key in Macbook w/ Touchbar
		 * model).
		 */
		if (e.getKeyCode() == 0)
		    return;
		key.add(String.valueOf(e.getKeyCode()));
		Controller.getInstance().getCurrentCommand().setCombination(String.join(":", key));
		Controller.getInstance().updateCurrentCommand();
		e.consume();
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
		key.clear();
		e.consume();
	    }

	    @Override
	    public void keyTyped(KeyEvent e) {
	    }
	});
	GridBagConstraints gbc_combination = new GridBagConstraints();
	gbc_combination.gridwidth = 2;
	gbc_combination.insets = new Insets(0, 0, 5, 0);
	gbc_combination.fill = GridBagConstraints.BOTH;
	gbc_combination.gridx = 2;
	gbc_combination.gridy = 1;
	add(combination, gbc_combination);
	combination.setColumns(10);

	lblText = new JLabel("Text:");
	lblText.setOpaque(false);
	lblText.setForeground(Color.WHITE);
	GridBagConstraints gbc_lblText = new GridBagConstraints();
	gbc_lblText.anchor = GridBagConstraints.WEST;
	gbc_lblText.insets = new Insets(0, 0, 5, 5);
	gbc_lblText.gridx = 0;
	gbc_lblText.gridy = 2;
	add(lblText, gbc_lblText);

	ButtonGroup labelGroup = new ButtonGroup();

	rdbtnTextNone = new JRadioButton("None");
	rdbtnTextNone.setForeground(Color.WHITE);
	GridBagConstraints gbc_rdbtnTextNone = new GridBagConstraints();
	gbc_rdbtnTextNone.fill = GridBagConstraints.HORIZONTAL;
	gbc_rdbtnTextNone.gridwidth = 2;
	gbc_rdbtnTextNone.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnTextNone.gridx = 2;
	gbc_rdbtnTextNone.gridy = 2;
	add(rdbtnTextNone, gbc_rdbtnTextNone);
	labelGroup.add(rdbtnTextNone);

	rdbtnCommandAsLabel = new JRadioButton("Use command as label");
	rdbtnCommandAsLabel.setForeground(Color.WHITE);
	GridBagConstraints gbc_rdbtnCommandAsLabel = new GridBagConstraints();
	gbc_rdbtnCommandAsLabel.fill = GridBagConstraints.HORIZONTAL;
	gbc_rdbtnCommandAsLabel.gridwidth = 2;
	gbc_rdbtnCommandAsLabel.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnCommandAsLabel.gridx = 2;
	gbc_rdbtnCommandAsLabel.gridy = 3;
	add(rdbtnCommandAsLabel, gbc_rdbtnCommandAsLabel);
	labelGroup.add(rdbtnCommandAsLabel);

	rdbtnCustomLabel = new JRadioButton("Custom label");
	rdbtnCustomLabel.setForeground(Color.WHITE);
	GridBagConstraints gbc_rdbtnCustomLabel = new GridBagConstraints();
	gbc_rdbtnCustomLabel.fill = GridBagConstraints.HORIZONTAL;
	gbc_rdbtnCustomLabel.gridwidth = 2;
	gbc_rdbtnCustomLabel.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnCustomLabel.gridx = 2;
	gbc_rdbtnCustomLabel.gridy = 4;
	add(rdbtnCustomLabel, gbc_rdbtnCustomLabel);
	labelGroup.add(rdbtnCustomLabel);

	customLabel = new JTextField();
	GridBagConstraints gbc_customLabel = new GridBagConstraints();
	gbc_customLabel.insets = new Insets(0, 0, 5, 0);
	gbc_customLabel.gridwidth = 2;
	gbc_customLabel.fill = GridBagConstraints.BOTH;
	gbc_customLabel.gridx = 2;
	gbc_customLabel.gridy = 5;
	add(customLabel, gbc_customLabel);
	customLabel.setColumns(10);

	rdbtnTextNone.addItemListener(e -> {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
		customLabel.setEnabled(false);
		Command command = Controller.getInstance().getCurrentCommand();
		command.setLabel("");
		command.setLableMode(0);
		Controller.getInstance().updateCurrentCommand();
	    }
	});
	rdbtnCommandAsLabel.addItemListener(e -> {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
		customLabel.setEnabled(false);
		Command command = Controller.getInstance().getCurrentCommand();
		command.setLabel(command.toString());
		command.setLableMode(1);
		Controller.getInstance().updateCurrentCommand();
	    }
	});
	rdbtnCustomLabel.addItemListener(e -> {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
		customLabel.setEnabled(true);
		Command command = Controller.getInstance().getCurrentCommand();
		command.setLabel(customLabel.getText());
		command.setLableMode(2);
		Controller.getInstance().updateCurrentCommand();
	    }
	});
	customLabel.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyTyped(KeyEvent e) {
		Command command = Controller.getInstance().getCurrentCommand();
		command.setLabel(customLabel.getText());
		Controller.getInstance().updateCurrentCommand();
	    }
	});

	JLabel lblIcon = new JLabel("Icon:");
	lblIcon.setOpaque(false);
	lblIcon.setForeground(GUIUtil.getForegroundColor());
	GridBagConstraints gbc_lblIcon = new GridBagConstraints();
	gbc_lblIcon.anchor = GridBagConstraints.WEST;
	gbc_lblIcon.insets = new Insets(0, 0, 5, 5);
	gbc_lblIcon.gridx = 0;
	gbc_lblIcon.gridy = 6;
	add(lblIcon, gbc_lblIcon);

	ButtonGroup iconGroup = new ButtonGroup();

	rdbtnIconNone = new JRadioButton("None");
	rdbtnIconNone.setForeground(Color.WHITE);
	GridBagConstraints gbc_rdbtnIconNone = new GridBagConstraints();
	gbc_rdbtnIconNone.fill = GridBagConstraints.HORIZONTAL;
	gbc_rdbtnIconNone.gridwidth = 2;
	gbc_rdbtnIconNone.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnIconNone.gridx = 2;
	gbc_rdbtnIconNone.gridy = 6;
	add(rdbtnIconNone, gbc_rdbtnIconNone);

	rdbtnImportFromComputer = new JRadioButton("Import From Computer");
	rdbtnImportFromComputer.setForeground(GUIUtil.getForegroundColor());
	GridBagConstraints gbc_rdbtnImportFromComputer = new GridBagConstraints();
	gbc_rdbtnImportFromComputer.fill = GridBagConstraints.HORIZONTAL;
	gbc_rdbtnImportFromComputer.gridwidth = 2;
	gbc_rdbtnImportFromComputer.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnImportFromComputer.gridx = 2;
	gbc_rdbtnImportFromComputer.gridy = 7;
	add(rdbtnImportFromComputer, gbc_rdbtnImportFromComputer);

	iconpath = new JTextField();
	iconpath.setEditable(false);
	GridBagConstraints gbc_iconpath = new GridBagConstraints();
	gbc_iconpath.insets = new Insets(0, 0, 5, 5);
	gbc_iconpath.fill = GridBagConstraints.BOTH;
	gbc_iconpath.gridx = 2;
	gbc_iconpath.gridy = 8;
	add(iconpath, gbc_iconpath);
	iconpath.setColumns(10);

	JButton btnBrowse = new JButton("Browse");
	btnBrowse.addActionListener(new Browse());
	GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
	gbc_btnBrowse.fill = GridBagConstraints.BOTH;
	gbc_btnBrowse.insets = new Insets(0, 0, 5, 0);
	gbc_btnBrowse.gridx = 3;
	gbc_btnBrowse.gridy = 8;
	add(btnBrowse, gbc_btnBrowse);

	CaptureButton btnCapture = new CaptureButton();
	GridBagConstraints gbc_btnCapture = new GridBagConstraints();
	gbc_btnCapture.insets = new Insets(0, 0, 5, 0);
	gbc_btnCapture.fill = GridBagConstraints.BOTH;
	gbc_btnCapture.weightx = 10.0;
	gbc_btnCapture.gridwidth = 2;
	gbc_btnCapture.gridx = 2;
	gbc_btnCapture.gridy = 9;
	add(btnCapture, gbc_btnCapture);

	iconGroup.add(rdbtnIconNone);
	iconGroup.add(rdbtnImportFromComputer);
	rdbtnIconNone.addItemListener(e -> {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
		iconpath.setEnabled(false);
		btnBrowse.setEnabled(false);
		btnCapture.setEnabled(false);
		Controller.getInstance().getCurrentCommand().setImage(Command.BLANK_IMAGE);
		Controller.getInstance().updateCurrentCommand();
		System.out.println("None clicked");
	    }
	});
	rdbtnImportFromComputer.addItemListener(e -> {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
		iconpath.setEnabled(true);
		btnBrowse.setEnabled(true);
		btnCapture.setEnabled(false);
		System.out.println("Import clicked");
	    }
	});

	JLabel lblMode = new JLabel("Mode:");
	lblMode.setOpaque(false);
	lblMode.setForeground(GUIUtil.getForegroundColor());
	GridBagConstraints gbc_lblMode = new GridBagConstraints();
	gbc_lblMode.anchor = GridBagConstraints.WEST;
	gbc_lblMode.insets = new Insets(0, 0, 5, 5);
	gbc_lblMode.gridx = 0;
	gbc_lblMode.gridy = 10;
	add(lblMode, gbc_lblMode);

	rdbtnSingleTouch = new JRadioButton("Tap");
	rdbtnSingleTouch.addActionListener(e -> Controller.getInstance().getCurrentCommand().setMode(0));
	rdbtnSingleTouch.setForeground(GUIUtil.getForegroundColor());
	GridBagConstraints gbc_rdbtnSingleTouch = new GridBagConstraints();
	gbc_rdbtnSingleTouch.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnSingleTouch.anchor = GridBagConstraints.WEST;
	gbc_rdbtnSingleTouch.gridwidth = 2;
	gbc_rdbtnSingleTouch.gridx = 2;
	gbc_rdbtnSingleTouch.gridy = 10;
	add(rdbtnSingleTouch, gbc_rdbtnSingleTouch);

	rdbtnFollow = new JRadioButton("Follow");
	rdbtnFollow.addActionListener(e -> Controller.getInstance().getCurrentCommand().setMode(1));
	rdbtnFollow.setForeground(GUIUtil.getForegroundColor());
	GridBagConstraints gbc_rdbtnFollow = new GridBagConstraints();
	gbc_rdbtnFollow.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnFollow.gridwidth = 2;
	gbc_rdbtnFollow.anchor = GridBagConstraints.WEST;
	gbc_rdbtnFollow.gridx = 2;
	gbc_rdbtnFollow.gridy = 11;
	add(rdbtnFollow, gbc_rdbtnFollow);

	ButtonGroup modeGroup = new ButtonGroup();
	modeGroup.add(rdbtnSingleTouch);
	modeGroup.add(rdbtnFollow);

	btnDeleteButton = new JButton("DELETE BUTTON");
	GridBagConstraints gbc_btnDeleteButton = new GridBagConstraints();
	gbc_btnDeleteButton.insets = new Insets(0, 0, 0, 5);
	gbc_btnDeleteButton.gridx = 2;
	gbc_btnDeleteButton.gridy = 15;
	add(btnDeleteButton, gbc_btnDeleteButton);
	btnDeleteButton.addActionListener((e) -> Controller.getInstance().removeCurrentCommand());
    }

    public void setProfileName(String profileName) {
	profilename.setText(profileName);
    }

    public void update(Command command) {
	this.combination.setText(command.toString());
	this.iconpath.setText(command.getImagePath());
	if (command.getImage() == Command.BLANK_IMAGE) {
	    rdbtnIconNone.setSelected(true);
	} else {
	    rdbtnImportFromComputer.setSelected(true);
	}
	switch (command.getLableMode()) {
	case 0:
	    rdbtnTextNone.setSelected(true);
	    break;
	case 1:
	    rdbtnCommandAsLabel.setSelected(true);
	    break;
	case 2:
	    rdbtnCustomLabel.setSelected(true);
	    break;
	default:
	    break;
	}
	switch (command.getMode()) {
	case 0:
	    rdbtnSingleTouch.setSelected(true);
	    break;
	case 1:
	    rdbtnFollow.setSelected(true);
	    break;
	default:
	    break;
	}
    }

    private class Browse implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    JFileChooser fc = new JFileChooser();
	    fc.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png"));
	    int returnVal = fc.showOpenDialog(SettingPanel.this);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
		File file = fc.getSelectedFile();
		BufferedImage image = null;
		try {
		    image = ImageIO.read(file);
		} catch (IOException e1) {
		    e1.printStackTrace();
		}
		Command current = Controller.getInstance().getCurrentCommand();
//		image = (GUIUtil.resize(image, current.getWidth(), current.getHeight()));
		System.out.println(image.getClass());
		current.setImage(image);
		Controller.getInstance().updateCurrentCommand();
	    }
	}
    }

    public void clear() {
	for (Component c : this.getComponents()) {
	    c.setEnabled(false);
	}
    }

    public void removeCommand(Command currentCommand) {
	this.clear();
    }
}
