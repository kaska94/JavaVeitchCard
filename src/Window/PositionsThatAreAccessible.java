package Window;

import java.awt.Color;
import java.io.ObjectInputStream.GetField;

public class PositionsThatAreAccessible {
	
	/* 
	 * 0 - right
	 * 1 - down
	 * 2 - left
	 * 3 - up
	*/
	
	// ________ y
	/* |
	 * |
	 * |
	 * x
	 */
	private VeitchCard card;
	private byte x,y;
	public void paintButtons(byte[] arrX, byte[] arrY)
	{
		for(int i = 0 ; i < arrX.length; i++)
		{
			card.getButtonWithCordinates((byte)arrX[i],(byte)arrY[i])
			.setBackground(Color.BLUE);
			if(!doAgglutinate((byte)(arrX[i]), (byte)(arrY[i]))) 
			{
				continue;
			}
			else
			{
				System.out.println("Error painting");
				break;
			}
		}
			
	}
	public int checkerOctaVertical(byte x, byte y, String moves)
	{
		int dir = -1;
		byte primeX, primeY;
		byte currentX, currentY;
		boolean paint = false;
		
		primeX = x;
		primeY = y;
		currentX = (byte)(x);
		currentY = (byte)(y);
		
		byte arrXToPaint[];
		byte arrYToPaint[];
		
		
		if(moves.length() == 0 )
		{
			
			arrXToPaint = new byte[1];
			arrYToPaint = new byte[1];
			arrXToPaint[0] = currentX;
			arrYToPaint[0] = currentY;
			
			if(card.getButtonWithCordinates(currentX, currentY).getStatus() == 1)
			{
				paint = true;
			}
			if(paint)
			{
				paintButtons(arrXToPaint , arrYToPaint);
			}
		}
		else
		{
			arrXToPaint = new byte[moves.length()];
			arrYToPaint = new byte[moves.length()];
		} 
		for(int i=0; i < moves.length(); i++)
		{
			if(moves.charAt(i) =='r')
			{
				dir = 0;
			}
			if(moves.charAt(i) =='d')
			{
				dir = 1;
			}
			if(moves.charAt(i) =='l')
			{
				dir = 2;
			}
			if(moves.charAt(i) =='u')
			{
				dir = 3;
			}
			
			arrXToPaint[i] = currentX;
			arrYToPaint[i] = currentY;
			
			if(card.getButtonWithCordinates(currentX, currentY).getStatus() == 1)
			{
				paint = true;
			}
			
			System.out.println(arrXToPaint[i] + " " +arrYToPaint[i] + " dir:" + dir);
			
			if((card.getButtonWithCordinates(currentX, currentY).getStatus() == 1) ||
					(card.getButtonWithCordinates(currentX, currentY).getStatus() == 2))
			{
				//System.out.println(dir + " " + currentX + " " + currentY);
				if(!canIAgglutinate((byte)(currentX),(byte)(currentY)))
				{
					switch(dir){
					case 0:
					{
						if(currentY == (VeitchCard.getMaxY()-1))
						{
							currentY = VeitchCard.getMinY();
						}
						else
						{
							currentY++;
						}
						break;
					}
					case 1:
					{
						if(currentX == (VeitchCard.getMaxX()-1))
						{
							currentX = VeitchCard.getMinX();
						}
						else
						{
							currentX++;
						}
						break;
					}
					case 2:
					{
						if(currentY == (VeitchCard.getMinY()))
						{
							currentY =(byte) (VeitchCard.getMaxY()-1);
						}
						else
						{
							currentY--;
						}
						break;
					}
					case 3:
					{
						if(currentX == (VeitchCard.getMinX()))
						{
							currentX = (byte) (VeitchCard.getMaxX()-1);
						}
						else
						{
							currentX--;
						}
						
						break;
					}
						default:
							return -1;
					}
				}
			}
			
			else
			{
				
			}
		}
		if((currentX == primeX) && (currentY == primeY))
		{
			if(paint)
			{
				paintButtons(arrXToPaint , arrYToPaint);
			}
			
			return 1;
		}
		return 0;
	}
	
	
	//first hardcode example
	/*
	public void checkerDuo(byte x , byte y)
	{
		System.out.println("x=:"+ x + "y=:" +y);
		setX(x);
		setY(y);
		for(int i = 0; i < 4; i++ )
		{
			if(x == VeitchCard.getMinX())
			{
				if(y == VeitchCard.getMinY())  // 0 : 0
				{
					if(!doAgglutinate((byte)(VeitchCard.getMaxX()-1), (byte)VeitchCard.getMinY())) //3:0 // gore
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMaxX()-1),(byte)VeitchCard.getMinY())
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(VeitchCard.getMinX()), (byte)(VeitchCard.getMaxY()-1))) // 0 :3// lqvo
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMinX()),(byte)(VeitchCard.getMaxY()-1))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(VeitchCard.getMinX()+1) , (byte)(VeitchCard.getMinY() )))// dqssno
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMinX()+1),(byte)(VeitchCard.getMinY()))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(VeitchCard.getMinX()) , (byte)(VeitchCard.getMinY()+1 ))) // dolo
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMinX()),(byte)(VeitchCard.getMinY()+1 ))
						.setBackground(Color.BLUE);
					}
				}
				else if(y == (VeitchCard.getMaxY()-1)) // 0: 3 gore dqsno
				{
					if(!doAgglutinate((byte)(VeitchCard.getMinX()), (byte)VeitchCard.getMinY())) //dqsno
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMinX()),(byte)(VeitchCard.getMinY()))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(VeitchCard.getMaxX()-1), (byte)(VeitchCard.getMaxY()-1))) // gore
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMaxX()-1),(byte)(VeitchCard.getMaxY()-1))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(VeitchCard.getMinX()), (byte)(VeitchCard.getMaxY()-2))) //lqvo
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMinX()),(byte)(VeitchCard.getMaxY()-2))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(VeitchCard.getMinX()+1), (byte)(VeitchCard.getMaxY()-1))) //dolo
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMinX()+1),(byte)(VeitchCard.getMaxY()-1))
						.setBackground(Color.BLUE);
					}
				}
				else // up
				{
					if(!doAgglutinate((byte)(getX()), (byte)(getY()-1))) // lqvo na up
					{
						card.getButtonWithCordinates((byte)(getX()),(byte)(getY()-1))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(getX()), (byte)(getY()+1))) // dqsno na up
					{
						card.getButtonWithCordinates((byte)(getX()),(byte)(getY()+1))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(getX()+1), (byte)(getY()))) // dolo na up
					{
						card.getButtonWithCordinates((byte)(getX()+1),(byte)(getY()))
						.setBackground(Color.BLUE);
					}
				}
			}
			else if(y == (VeitchCard.getMinY()) )
			{
				if(x == (VeitchCard.getMaxX()-1))// 3: 0 // dolo
				{
					if(!doAgglutinate((byte)(VeitchCard.getMinX()), (byte)VeitchCard.getMinY()))// dolo
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMinX()),(byte)(VeitchCard.getMinY()))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(VeitchCard.getMaxX()-1), (byte)(VeitchCard.getMaxY()-1))) //lqvo
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMaxX()-1),(byte)(VeitchCard.getMaxY()-1))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(VeitchCard.getMaxX()-1), (byte)(VeitchCard.getMinY()+1))) //dqsno
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMaxX()-1),(byte)(VeitchCard.getMinY()+1))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(VeitchCard.getMaxX()-2), (byte)VeitchCard.getMinY())) //gore
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMaxX()-2),(byte)(VeitchCard.getMinY()))
						.setBackground(Color.BLUE);
					}
				}
				else //lqvo
				{
					if(!doAgglutinate((byte)(getX()), (byte)(getY()-1))) // dqsno na left
					{
						card.getButtonWithCordinates((byte)(getX()),(byte)(getY()+1))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(getX()+1), (byte)(getY()))) //dolo na right
					{
						card.getButtonWithCordinates((byte)(getX()+1),(byte)(getY()))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(getX()-1), (byte)(getY()))) // gore na right
					{
						card.getButtonWithCordinates((byte)(getX()-1),(byte)(getY()))
						.setBackground(Color.BLUE);
					}
				}
			}
			else if(y == (VeitchCard.getMaxY()-1)) // dolo dqsno
			{
				if(x == (VeitchCard.getMaxX()-1))//3:3
				{
					if(!doAgglutinate((byte)(VeitchCard.getMinX()), (byte)(VeitchCard.getMaxY()-1))) // 0 :3// dolo
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMinX()),(byte)(VeitchCard.getMaxY()-1))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(VeitchCard.getMaxX()-1), (byte)VeitchCard.getMinY())) //3:0 // dqsno
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMaxX()-1),(byte)(VeitchCard.getMinY()))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(VeitchCard.getMaxX()-1), (byte)(VeitchCard.getMaxY()-2))) //3:2 // lqvo
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMaxX()-1),(byte)(VeitchCard.getMaxY()-2))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(VeitchCard.getMaxX()-2), (byte)(VeitchCard.getMaxY()-1))) //2:3 // gore
					{
						card.getButtonWithCordinates((byte)(VeitchCard.getMaxX()-2),(byte)(VeitchCard.getMaxY()-1))
						.setBackground(Color.BLUE);
					}
				}
				else // dqsno
				{
					if(!doAgglutinate((byte)(getX()), (byte)(getY()-1))) // lqvo na right
					{
						card.getButtonWithCordinates((byte)(getX()),(byte)(getY()-1))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(getX()-1), (byte)(getY()))) // gore na right
					{
						card.getButtonWithCordinates((byte)(getX()-1),(byte)(getY()))
						.setBackground(Color.BLUE);
					}
					else if(!doAgglutinate((byte)(getX()+1), (byte)(getY()))) // dolo na right
					{
						card.getButtonWithCordinates((byte)(getX()+1),(byte)(getY()))
						.setBackground(Color.BLUE);
					}
				}
			}
			else if(x == (VeitchCard.getMaxX()-1)) // down
			{
				if(!doAgglutinate((byte)(getX()), (byte)(getY()-1))) // lqvo na up
				{
					card.getButtonWithCordinates((byte)(getX()),(byte)(getY()-1))
					.setBackground(Color.BLUE);
				}
				else if(!doAgglutinate((byte)(getX()), (byte)(getY()+1))) // dqsno na up
				{
					card.getButtonWithCordinates((byte)(getX()),(byte)(getY()+1))
					.setBackground(Color.BLUE);
				}
				else if(!doAgglutinate((byte)(getX()-1), (byte)(getY()))) // gore na down
				{
					card.getButtonWithCordinates((byte)(getX()-1),(byte)(getY()))
					.setBackground(Color.BLUE);
				}
			}
			else 
			{
				if(!doAgglutinate((byte)(getX()), (byte)(getY()-1))) // lqvo na up
				{
					card.getButtonWithCordinates((byte)(getX()),(byte)(getY()-1))
					.setBackground(Color.BLUE);
				}
				if(!doAgglutinate((byte)(getX()), (byte)(getY()+1))) // lqvo na up
				{
					card.getButtonWithCordinates((byte)(getX()),(byte)(getY()+1))
					.setBackground(Color.BLUE);
				}
				if(!doAgglutinate((byte)(getX()+1), (byte)(getY()))) // lqvo na up
				{
					card.getButtonWithCordinates((byte)(getX()+1),(byte)(getY()))
					.setBackground(Color.BLUE);
				}
				if(!doAgglutinate((byte)(getX()-1), (byte)(getY()))) // lqvo na up
				{
					card.getButtonWithCordinates((byte)(getX()-1),(byte)(getY()))
					.setBackground(Color.BLUE);
				}
			}
		}
	}
	*/
	
	private boolean canIAgglutinate(byte x , byte y)
	{
		if (card.getButtonWithCordinates((byte)x,(byte)y).isAgglutinate() == false) // checking if the button is agglutinate
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	private boolean doAgglutinate(byte x , byte y)
	{
		if (card.getButtonWithCordinates((byte)x,(byte)y).isAgglutinate() == false) // checking if the button is agglutinate
		{
			card.getButtonWithCordinates((byte)x,(byte)y).setAgglutinate(true);
			return false;
		}
		else 
		{
			return true;
		}
	}
	
	public byte getX() {
		return x;
	}

	public void setX(byte x) {
		this.x = x;
	}

	public byte getY() {
		return y;
	}

	public void setY(byte y) {
		this.y = y;
	}

	public PositionsThatAreAccessible(VeitchCard card)
	{
		this.card = card;
	}

	
}
