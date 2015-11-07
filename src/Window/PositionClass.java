package Window;

import java.awt.Color;

public final class PositionClass {
// THE WAY BUTTONS NEED TO BE SELECTED
	public static class PositionGetter
	{
		private static int counter = 0 ;
		private static int previous;
		private static int mode;
		

		
		//byte[] positionOfButton = 
		
		public static int getPositionToBeFilled( int mode)
		{
			setMode(mode);
			switch(mode)
			{
				case 3:
					return caseThree();
				case 4:
					return caseFour();
				case 5:
					return 0 ;
				default:
					return 0 ;
					
			}
		}
		public static int caseThree()
		{
			switch(counter)
			{
				case 0:
					previous = 0;
					counter++;
					return 7;
				case 1:
					previous = 7;
					counter++;
					return 6;
				case 2:
					previous = 6;
					counter++;
					return 3;
				case 3:
					previous = 3;
					counter++;
					return 2;
				case 4:
					previous = 2;
					counter++;
					return 4;
				case 5:
					previous = 4;
					counter++;
					return 5;
				case 6:
					previous = 5;
					counter++;
					return 0;
				case 7:
					previous = 0;
					counter++;
					return 1;
				default:
					previous = 1;
					return -1;
			}
		}
		public static int caseFour()
		{
			switch(counter)
			{
			case 0:
				previous = 0;
				counter++;
				return 15;
			case 1:
				previous = 15;
				counter++;
				return 11;
			case 2:
				previous = 11;
				counter++;
				return 14;
			case 3:
				previous=14;
				counter++;
				return 10;
			case 4:
				previous= 10;
				counter++;
				return 3;
			case 5:
				previous = 3;
				counter++;
				return 7;
			case 6:
				previous =7;
				counter++;
				return 2;
			case 7:
				previous = 2;
				counter++;
				return 6;
			case 8:
				previous = 6;
				counter++;
				return 12;
			case 9:
				previous =12;
				counter++;
				return 8;
			case 10:
				previous = 8;
				counter++;
				return 13;
			case 11:
				previous =13;
				counter++;
				return 9;
			case 12:
				previous = 9;
				counter++;
				return 0;
			case 13:
				previous = 0;
				counter++;
				return 4;
			case 14:
				previous =4;
				counter++;
				return 1;
			case 15:
				previous =1;
				counter++;
				return 5;
			default:
				previous = 5;
				return -1;
			}
		}
		
		
		
		
		
		public static int getMode() {
			return mode;
		}
		public static void setMode(int mode) {
			PositionGetter.mode = mode;
		}
		public static int getPrevious()
		{
			return previous;
		}
		public static int getCounter() {
			return counter;
		}
		public static void setCounter(int counter) {
			PositionGetter.counter = counter;
		}
		
		
	}
	
}
