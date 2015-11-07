package Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;









import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MainWindow extends JFrame{
	
	private byte numberOfVariables;
	
	private JFrame mainWindow; 
	private JPanel westPanel ;
	private JPanel centralPanel ;
	private JPanel northPanel ;
	private JPanel eastPanel;
	
	private JPanel southPanel;
	
	
	public MainWindow(int numberOfVariables)
	{	
		setNumberOfVariables((byte)(numberOfVariables+3));
		createMainWindow();
	}
	public void createMainWindow()
	{
		// MAIN CONFIGURATION
		mainWindow = new JFrame("Name");
		mainWindow.setSize(800,500);
		mainWindow.setLayout(new BorderLayout(25,0));
		mainWindow.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windwoEvent){
			System.exit(0);
			}
		});
		//WEST CONFIGURATION
		westPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		//CENTRAL PANEL 
		switch(getNumberOfVariables())
		{
			case 3:
				centralPanel = new JPanel(new GridLayout(2,4));
				break;
			case 4:
				centralPanel = new JPanel(new GridLayout(4,4));
				break;
		}
		//NORTH PANEL
		northPanel = new JPanel(new FlowLayout());
		
		//SOUTH Panel
		southPanel = new JPanel(new FlowLayout());
		
		//EAST PANEL
		eastPanel = new JPanel(new GridLayout(12,1));
		
		//MAIN WINDOW ADDING
		mainWindow.add(westPanel,BorderLayout.WEST); // west
		mainWindow.add(centralPanel, BorderLayout.CENTER);
		mainWindow.add(northPanel,BorderLayout.NORTH);
		mainWindow.add(southPanel,BorderLayout.SOUTH);
		mainWindow.add(eastPanel,BorderLayout.EAST);
		mainWindow.setVisible(true);
		
		
	}
	
	public void buttonConfiguration()
	{
		// NORTH
		JLabel northArea = new JLabel(" ",JLabel.CENTER);
		northArea.setSize(100,10);
		
		//SOUTH
		JLabel southArea = new JLabel(" ",JLabel.CENTER);
		southArea.setSize(100,50);
				
				
				
				
		//WEST
	
				
			   
		//Veitch Cards
		new  VeitchCard(this);
				
		
		

		
		
		//East 
		JLabel eastArea = new JLabel(" ",JLabel.CENTER);
		southArea.setSize(10,0);
		
		//PANEL ADDING
		southPanel.add(southArea);
		
		northPanel.add(northArea);
		
		mainWindow.setVisible(true);
	}
	
	
	
	
	
	public int getNumberOfVariables() {
		return numberOfVariables;
	}
	public void setNumberOfVariables(byte numberOfVariables) {
		this.numberOfVariables = numberOfVariables;
	}
	public JPanel getWestPanel() {
		return westPanel;
	}
	public void setWestPanel(JPanel westPanel) {
		this.westPanel = westPanel;
	}
	public JPanel getCentralPanel() {
		return centralPanel;
	}
	public void setCentralPanel(JPanel centralPanel) {
		this.centralPanel = centralPanel;
	}
	public JPanel getNorthPanel() {
		return northPanel;
	}
	public void setNorthPanel(JPanel northPanel) {
		this.northPanel = northPanel;
	}
	public JPanel getEastPanel() {
		return eastPanel;
	}
	public void setEastPanel(JPanel eastPanel) {
		this.eastPanel = eastPanel;
	}
	public JPanel getSouthPanel() {
		return southPanel;
	}
	public void setSouthPanel(JPanel southPanel) {
		this.southPanel = southPanel;
	}

	
	
	
}
