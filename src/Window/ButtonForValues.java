package Window;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.temporal.JulianFields;

import javax.swing.*;


/*
 * BUTTON FOR VARIABLES
 * SIMPLE DEFAULT COMBO  BOX
 */
public class ButtonForValues {
	
	private final JOptionPane ValueDialog = new JOptionPane();
	private Object[] possibleVariables = {"3","4","5","6"};
	private JFrame frame;
	private JPanel northPanel;
	private JPanel centralPanel;
	private JPanel southPanel;
	
	
	
	
	 
	public ButtonForValues() 
	{
		creatingButton();
	}
	public void creatingButton()
	{
		//FRAME CONFIGURATION
		frame = new JFrame();
		frame.setSize(300,300);
		frame.setLayout(new GridLayout(3,2));
		frame.setLocationByPlatform(true);
		frame.setLocation(500, 150);
		frame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
		
		//PANEL NORTH
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		
		//PANEL CENRAL
		centralPanel= new JPanel();
		centralPanel.setLayout(new FlowLayout());
		
		//SOUTH PANEL
		southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		
		//LABEL
		JLabel informationForThisValues = new JLabel();
		informationForThisValues.setText("Enter how many variables you need.");
		
		
		
		
		//COMBOBOX
		final DefaultComboBoxModel values = new DefaultComboBoxModel();
		values.addElement("Values: 3");
		values.addElement("Values: 4");
		//values.addElement("Values: 5");
		//values.addElement("Values: 6");
		JComboBox valuesCombo  = new JComboBox(values);
		
		// JButton for entering Main window
		JButton buttonForNextStep = new JButton("Continue");
		buttonForNextStep.setSize(50,50);
		
		buttonForNextStep.addActionListener(new ActionListener() {
					
				public void actionPerformed(ActionEvent e) {
					String s = "";
					if(valuesCombo.getSelectedIndex()!= -1)
					{
							MainWindow main = 
								new MainWindow(valuesCombo.getSelectedIndex());
							main.buttonConfiguration();
						
						
					}
				}
			});
		
		
		northPanel.add(informationForThisValues);
		centralPanel.add(valuesCombo,JButton.CENTER);
		centralPanel.add(buttonForNextStep);
		
		frame.add(northPanel);
		frame.add(centralPanel);
		frame.add(southPanel);
		frame.setVisible(true);
		
		
		
		
		
	}
}
