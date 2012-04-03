package gui;

import javax.swing.JFrame;

public class MainWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MainWindow() {
		this.setTitle("TwitMiner");
		this.getContentPane().add(new MainPanel());
		this.pack();
		this.setVisible(true);
	}
}
