package Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class ButtonForVeitchCards extends JButton{
	// some things may be not necessary
	private ImageIcon 										H,binaryOne,binaryZero;
	private int												x,y,imageNumber;
	private byte 											position;
	private String 											command ;
	private JLabel 											buttonPosition;
	private JLabel 											buttonStatus;
	private boolean 										isAgglutinate 				= false;
	private byte 											type;
	


	private byte 											status;
	private byte[] 											arrrayOfPossitionForChecking;
	
	private VeitchCard theCard;
	

	
	public ButtonForVeitchCards(int x, int y ,VeitchCard theCard)
	{
		super();
		setCordinates(x, y);
		setSize(60,60);
		setTheCard(theCard);
		
		
		
		H = new ImageIcon("D:\\JAVA\\JavaParts\\Veitch\\img\\H.png");
		binaryOne = new ImageIcon("D:\\JAVA\\JavaParts\\Veitch\\img\\One.png");
		binaryZero = new ImageIcon("D:\\JAVA\\JavaParts\\Veitch\\img\\Zero.png");
		
		setPosition(x,y);
		setCommand("" +x+ y+ getPosition());
		
		if(	((this.getXposition() == VeitchCard.getMinX()) || 
			(this.getXposition() == (VeitchCard.getMaxX()-1))) 
			&& 
			((this.getYposition() == VeitchCard.getMinY()) || 
			(this.getYposition() == (VeitchCard.getMaxY()-1))) )
		{
			this.setType((byte) 0);
		}
		else if(	
				((this.getXposition() == VeitchCard.getMinX()) || 
				(this.getXposition() == (VeitchCard.getMaxX()-1))) 
				||
				((this.getYposition() == VeitchCard.getMinY()) || 
				(this.getYposition() == (VeitchCard.getMaxY()-1))) )
					
		{
			this.setType((byte) 1);
		}
		else
		{
			this.setType((byte) 2);
		}
		
		
		this.addActionListener(new ButtonClickListener());
		this.setActionCommand(command);
		validate();
	}
	
	// click magic
	public class ButtonClickListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{	
			
			String s = e.getActionCommand();
			if(s.equals(getCordinatesInStringFormat()))
			{	
				imageNumber++;
				imageNumber%=4;
				switch(imageNumber)
				{
					case 0:
						setIcon(null);
						getTheCard().getButtonPosition().setText("Status : X ="+Integer.toString(getXposition()) 
								+"  Y ="+ Integer.toString(getYposition())
										+"  Position ="+Integer.toString(getPosition())+" "
								);
						getTheCard().getButtonStatus().setText("Status : unknown");
						setStatus((byte)-1);
						break;
					case 1:
						setIcon(H);
						getTheCard().getButtonPosition().setText("Status : X ="+Integer.toString(getXposition()) 
								+"  Y ="+ Integer.toString(getYposition())
										+"  Position ="+Integer.toString(getPosition())+" "
								);
						getTheCard().getButtonStatus().setText("Status : H (0 or 1)");
						setStatus((byte)2);
						break;
					case 2:
						setIcon(binaryZero);
						getTheCard().getButtonPosition().setText("Status : X ="+Integer.toString(getXposition()) 
								+"  Y ="+ Integer.toString(getYposition())
										+"  Position ="+Integer.toString(getPosition())+" "
								);
						getTheCard().getButtonStatus().setText("Status : 0");
						setStatus((byte)0);
						break;
					case 3:
						setIcon(binaryOne);
						getTheCard().getButtonPosition().setText("Status : X ="+Integer.toString(getXposition()) 
								+"  Y ="+ Integer.toString(getYposition())
										+"  Position ="+Integer.toString(getPosition())+" "
								);
						getTheCard().getButtonStatus().setText("Status : 1");
						setStatus((byte)1);
						break;
				}
				
			}
		}
	}
	
	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	
	public boolean isAgglutinate() {
		return isAgglutinate;
	}

	public void setAgglutinate(boolean isAgglutinate) {
		this.isAgglutinate = isAgglutinate;
	}
	public byte[] getArrrayOfPossitionForChecking() {
		return arrrayOfPossitionForChecking;
	}

	public void setArrrayOfPossitionForChecking(byte[] arrrayOfPossitionForChecking) {
		this.arrrayOfPossitionForChecking = arrrayOfPossitionForChecking;
	}

	public VeitchCard getTheCard() {
		return theCard;
	}

	public void setTheCard(VeitchCard theCard) {
		this.theCard = theCard;
	}

	
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
	
	
	
	public int getImageNumber() {
		return imageNumber;
	}

	public void setImageNumber(int imageNumber) {
		this.imageNumber = imageNumber;
	}

	String getCordinatesInStringFormat()
	{
		return "" + this.x + this.y + this.position;
	}
	
	public int getXposition() {
		return x;
	}
	public int getYposition() {
		return y;
	}
	public byte getPosition() {
		return position;
	}
	
	void setCordinates(int x , int y)
	{
		this.x = x;
		this.y = y;
	}
	void setPosition(int x ,int y)
	{
		this.position =(byte)(( x *getTheCard().getMaxX() )+ y);
	}


	
	
	public JLabel getButtonStatus() {
		return buttonStatus;
	}

	public void setButtonStatus(JLabel buttonStatus) {
		this.buttonStatus = buttonStatus;
	}
	public ImageIcon getBinaryOne() {
		return binaryOne;
	}

	public void setBinaryOne(ImageIcon binaryOne) {
		this.binaryOne = binaryOne;
	}

	public ImageIcon getBinaryZero() {
		return binaryZero;
	}

	public void setBinaryZero(ImageIcon binaryZero) {
		this.binaryZero = binaryZero;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	

	public void setbuttonPosition(JLabel buttonStatus) {
		this.buttonPosition = buttonStatus;
	}
}
