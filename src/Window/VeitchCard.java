package Window;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class VeitchCard {
	
	private JLabel buttonStatus;
	private JLabel buttonPosition ;
	private JButton nextButton;
	private byte numberOfValues;
	private static byte minX,minY,maxX, maxY;
	private VeitchCard card;
	
	private byte counterOfOnes = 0;
	private byte counterOfH = 0;
	private MainWindow mainWindow;
	
	public VeitchCard(MainWindow mainWindow)
	{
		setMainWindow(mainWindow);
		setNumberOfValues((byte)mainWindow.getNumberOfVariables());
		buttonForVariables();
		setCard(this);
	}
	void buttonForVariables()
	{
		//SOUTH
			buttonStatus =new JLabel("Status : unknown",BoxLayout.LINE_AXIS);
			buttonStatus.setSize(100, 10);
		//North
			buttonPosition = new JLabel("Status : X =  Y =  Position = ");
			buttonPosition.setSize(50,10);
		// THIS IS TO SET NEW CASE OF VARIABLES
			
		switch(getNumberOfValues())
		{
			case 3:
				
				setMinX((byte)0);
				setMinY((byte)0);
				setMaxX((byte)2);
				setMaxY((byte)4);
				variablesFiller();
				break;
			case 4:
				
				setMinX((byte)0);
				setMinY((byte)0);
				setMaxX((byte)4);
				setMaxY((byte)4);
				variablesFiller();
				break;
			case 5:
				break;
		}
		
		
		//NEXT BUTTON
		nextButton = new JButton("Next Button");
		nextButton.setSize(100,50);
		///////////////////////////////////////////////
		nextButton.addActionListener(new ButtonClickListener());
		// END OF NEXT BUTTON
		
		
		//Check button -> functionality
		JButton optimizeButton = new JButton("Check!");
		optimizeButton.setSize(100,50);
	
		// WAY BUTTONS NEED TO BE SET
		PositionsThatAreAccessible positions = new PositionsThatAreAccessible(this);
		
		// THIS BUTTON DO THE MAGIC
		/* 	arrayOf_X_fromOnes and arrayOf_Y_fromOnes for coordinates of ONES
		 *  arrayOf_X_fromH adn arrayOf_Y_fromH for coordinates of H
		 *  statusByte -1 = empty
		 *  			0 = 0 
		 *  			1 = H
		 *  			2 = 1
		 *  H is one if needed, zero if not;
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * */
		
	    optimizeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
			byte arrayOf_X_fromOnes[] = new byte[getMaxX()*getMaxY()];
			byte arrayOf_Y_fromOnes[] = new byte[getMaxX()*getMaxY()];
			
			byte arrayOf_X_fromH[] = new byte[getMaxX()*getMaxY()];
			byte arrayOf_Y_fromH[] = new byte[getMaxX()*getMaxY()];
			
			byte statusByte ;
			for(int i = 0; i<= ( getMaxX()*getMaxY()-1) ;i++)
			{
				statusByte = ((ButtonForVeitchCards)getMainWindow().getCentralPanel()
						.getComponent(i)).getStatus();
				if (statusByte == 1)
				{
					arrayOf_X_fromOnes[i] =(byte) ((ButtonForVeitchCards)getMainWindow().getCentralPanel()
							.getComponent(i)).getXposition();
					arrayOf_Y_fromOnes[i] =(byte) ((ButtonForVeitchCards)getMainWindow().getCentralPanel()
							.getComponent(i)).getYposition();
					System.out.println(i + " with position "+((ButtonForVeitchCards)getMainWindow().getCentralPanel()
							.getComponent(i)).getPosition() + " is added in 1");
					counterOfOnes++;
					
					arrayOf_X_fromH[i] = -1;
					arrayOf_Y_fromH[i] = -1;
				}
				if(statusByte == 2)
				{
					arrayOf_X_fromH[i] =(byte) ((ButtonForVeitchCards)getMainWindow().getCentralPanel()
							.getComponent(i)).getXposition();
					arrayOf_Y_fromH[i] =(byte) ((ButtonForVeitchCards)getMainWindow().getCentralPanel()
							.getComponent(i)).getYposition();
					System.out.println(i + " with position "+((ButtonForVeitchCards)getMainWindow().getCentralPanel()
							.getComponent(i)).getPosition() + " is added in H");
					counterOfH++;
					
					arrayOf_X_fromOnes[i] = -1;
					arrayOf_Y_fromOnes[i] = -1;
				}
				if((statusByte == -1) || (statusByte == 0))
				{
					arrayOf_X_fromOnes[i] = -1;
					arrayOf_Y_fromOnes[i] = -1;
					arrayOf_Y_fromH[i] = -1;
					arrayOf_X_fromH[i] = -1;
				}
			}
			
			// Cycle all of the arrays, 0,1,2 statusses
			if((arrayOf_X_fromH.length) == (arrayOf_Y_fromH.length) &&
					(arrayOf_X_fromOnes.length) == (arrayOf_Y_fromOnes.length))
			{
				for(int i = 0 ; i < arrayOf_X_fromOnes.length ; i++)
				{
					if((arrayOf_X_fromOnes[i] != -1) || (arrayOf_X_fromH[i] != -1))
					{
						if((((ButtonForVeitchCards)getMainWindow().
								getCentralPanel().getComponent(i)).getXposition() == 0) && 
								(((ButtonForVeitchCards)getMainWindow().
										getCentralPanel().getComponent(i)).getYposition() == 0  ))
						{
							// 0 0 
							positions.checkerOctaVertical((byte)0,(byte)0, "rdddluuu");
							positions.checkerOctaVertical((byte)getCard().getButtonWithNumber((byte) i).getXposition(),
									(byte) getCard().getButtonWithNumber((byte) i).getYposition(), "ldru");
							positions.checkerOctaVertical((byte)getCard().getButtonWithNumber((byte) i).getXposition(),
									(byte) getCard().getButtonWithNumber((byte) i).getYposition(), "urdl");
						}
						if(((ButtonForVeitchCards)getMainWindow().getCentralPanel().getComponent(i)).getXposition() == 0)
						{ 
							positions.checkerOctaVertical((byte)getCard().getButtonWithNumber((byte) i).getXposition(),
									(byte) getCard().getButtonWithNumber((byte) i).getYposition(), "dddd");
							positions.checkerOctaVertical((byte)getCard().getButtonWithNumber((byte) i).getXposition(),
									(byte) getCard().getButtonWithNumber((byte) i).getYposition(), "rdddluuu");
						}
						if(((ButtonForVeitchCards)getMainWindow().getCentralPanel().getComponent(i)).getYposition() == 0)
						{ 
							// any 0
							positions.checkerOctaVertical((byte)getCard().getButtonWithNumber((byte) i).getXposition(),
									(byte) getCard().getButtonWithNumber((byte) i).getYposition(), "rrrr");
							positions.checkerOctaVertical((byte)getCard().getButtonWithNumber((byte) i).getXposition(),
									(byte) getCard().getButtonWithNumber((byte) i).getYposition(), "rrrdlllu");
						}
						if((((ButtonForVeitchCards)getMainWindow().
								getCentralPanel().getComponent(i)).getXposition() <= 2)  ||
								(((ButtonForVeitchCards)getMainWindow().
										getCentralPanel().getComponent(i)).getYposition() <= 2  ))
						{
						positions.checkerOctaVertical((byte)getCard().getButtonWithNumber((byte) i).getXposition(),
								(byte) getCard().getButtonWithNumber((byte) i).getYposition(), "rdlu");
							
						}
						
						if((((ButtonForVeitchCards)getMainWindow().
								getCentralPanel().getComponent(i)).getXposition() == 0)  ||
								(((ButtonForVeitchCards)getMainWindow().
										getCentralPanel().getComponent(i)).getYposition() == 3  ))
						{
							// gore dqsno 0:3
							positions.checkerOctaVertical((byte)getCard().getButtonWithNumber((byte) i).getXposition(),
									(byte) getCard().getButtonWithNumber((byte) i).getYposition(), "uldr");
						}
						if((((ButtonForVeitchCards)getMainWindow().
								getCentralPanel().getComponent(i)).getXposition() == 3)  ||
								(((ButtonForVeitchCards)getMainWindow().
										getCentralPanel().getComponent(i)).getYposition() == 0  ))
						{
							positions.checkerOctaVertical((byte)getCard().getButtonWithNumber((byte) i).getXposition(),
									(byte) getCard().getButtonWithNumber((byte) i).getYposition(), "ldru ");
						}
						
						positions.checkerOctaVertical((byte)getCard().getButtonWithNumber((byte) i).getXposition(),
								(byte) getCard().getButtonWithNumber((byte) i).getYposition(), "rl");
						positions.checkerOctaVertical((byte)getCard().getButtonWithNumber((byte) i).getXposition(),
								(byte) getCard().getButtonWithNumber((byte) i).getYposition(), "du");
						positions.checkerOctaVertical((byte)getCard().getButtonWithNumber((byte) i).getXposition(),
								(byte) getCard().getButtonWithNumber((byte) i).getYposition(), "lr");
						positions.checkerOctaVertical((byte)getCard().getButtonWithNumber((byte) i).getXposition(),
								(byte) getCard().getButtonWithNumber((byte) i).getYposition(), "ud");
						positions.checkerOctaVertical((byte)getCard().getButtonWithNumber((byte) i).getXposition(),
								(byte) getCard().getButtonWithNumber((byte) i).getYposition(), "");
						
						
					}
					else
					{
						continue;
					}
				}
				System.out.println("\n");
				
			}
			
			
				
			}
		});
		
		

		// GO TO ACTION LISTENER IN BUTTON FOR VALUES
		// END BUTTONS
		//
		//
		//
		
		
		//ADDING IN PANELS
	    
		getMainWindow().getEastPanel().add(nextButton);
		getMainWindow().getEastPanel().add(optimizeButton);
		getMainWindow().getSouthPanel().add(buttonStatus);
		getMainWindow().getNorthPanel().add(buttonPosition);
	}
	
	
	// the magic with the listeningn to the events is here
	public class ButtonClickListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			int positionToBeFilled;
			int previousPositionToBeLock;
				positionToBeFilled
				= PositionClass.PositionGetter.getPositionToBeFilled(getNumberOfValues());
				previousPositionToBeLock
				= PositionClass.PositionGetter.getPrevious();
				if(positionToBeFilled != -1)
				{
					getMainWindow().getCentralPanel().getComponent(positionToBeFilled).setBackground(Color.white);
					getMainWindow().getCentralPanel().getComponent(positionToBeFilled).setEnabled(true);
					getMainWindow().getCentralPanel().getComponent(previousPositionToBeLock).setBackground(null);
					getMainWindow().getCentralPanel().getComponent(previousPositionToBeLock).setEnabled(false);
				}
				else
				{
					getMainWindow().getCentralPanel().getComponent(previousPositionToBeLock).setBackground(null);
					for(int i = 0; i<= ( getMaxX()*getMaxY()-1) ;i++)
					{
						getMainWindow().getCentralPanel().getComponent(i).setEnabled(true);
						
					}
				}
			}
		
	}
	// filling the card with buttons of tipe ButtonForVeitchCards
	/*
	 * _______________
	 * |
	 * |
	 * |
	 * |
	 * |
	 * |
	 * 
	 */
	
	public void variablesFiller()
	{
		
		for(int i = 0 ; i < getMaxX() ; i ++)
		{
			for(int j = 0 ; j < getMaxY() ; j++)
			{
				getMainWindow().getCentralPanel().add(new ButtonForVeitchCards(i, j,
						this));
			}
		}
		
		int possitionToUnlock;
		
		possitionToUnlock = PositionClass.PositionGetter.
					getPositionToBeFilled(getNumberOfValues());
		for(int i = 0 ; i < (getMaxX()*getMaxY())-1; i++)
		{
			getMainWindow().getCentralPanel().getComponent(i).setEnabled(false);
		}
		//first to be red and unlock go to ButtonClickListener 
		getMainWindow().getCentralPanel().getComponent(possitionToUnlock).setEnabled(true);
		getMainWindow().getCentralPanel().getComponent(possitionToUnlock).setBackground(Color.white);
		
	}
	
	// set and get shit
	
	public ButtonForVeitchCards  getButtonWithNumber(byte i)
	{
		return (ButtonForVeitchCards)getMainWindow().
				getCentralPanel().getComponent(i);
	}
	public ButtonForVeitchCards  getButtonWithCordinates(byte x , byte y)
	{
		return(ButtonForVeitchCards)getMainWindow().getCentralPanel().getComponent(( x *getMaxY() )+ y);
	}
	public JButton getNextButton() {
		return nextButton;
	}
	public void setNextButton(JButton nextButton) {
		this.nextButton = nextButton;
	}
	public static byte getMinX() {
		return minX;
	}
	public static void setMinX(byte minX) {
		VeitchCard.minX = minX;
	}
	public static byte getMinY() {
		return minY;
	}
	public static void setMinY(byte minY) {
		VeitchCard.minY = minY;
	}
	public static byte getMaxX() {
		return maxX;
	}
	public static void setMaxX(byte maxX) {
		VeitchCard.maxX = maxX;
	}
	public static byte getMaxY() {
		return maxY;
	}
	public static void setMaxY(byte maxY) {
		VeitchCard.maxY = maxY;
	}
	
	public MainWindow getMainWindow() {
		return mainWindow;
	}
	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public int getCounterOfOnes() {
		return counterOfOnes;
	}
	public void setCounterOfOnes(byte counterOfOnes) {
		this.counterOfOnes = counterOfOnes;
	}
	public int getCounterOfH() {
		return counterOfH;
	}
	public void setCounterOfH(byte counterOfH) {
		this.counterOfH = counterOfH;
	}
	
	public int getNumberOfValues() {
		return numberOfValues;
	}
	public void setNumberOfValues(byte numberOfValues) {
		this.numberOfValues = numberOfValues;
	}
	public JLabel getButtonStatus() {
		return buttonStatus;
	}
	public void setButtonStatus(JLabel buttonStatus) {
		this.buttonStatus = buttonStatus;
	}
	public JLabel getButtonPosition() {
		return buttonPosition;
	}
	public void setButtonPosition(JLabel buttonPosition) {
		this.buttonPosition = buttonPosition;
	}
	public VeitchCard getCard() {
		return card;
	}
	public void setCard(VeitchCard card) {
		this.card = card;
	}
	
}
