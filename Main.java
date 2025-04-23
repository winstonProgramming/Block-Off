import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void printBlankArray()
	{
	    // creating blank array
	    String[][] blankCoordinatesArray = new String[10][10];
		for (String[] c : blankCoordinatesArray)
		    Arrays.fill(c, " ");
	    printArray(blankCoordinatesArray);
	}
	
	public static void printArray(String[][] coordsArray)
	{
		String[][] array = new String[10][10];

		System.out.print("    ");
		// printing numbers labelling the top
		for (int i=0; i < array[0].length; i++)
		{
			System.out.print(i + "   ");
		}
		System.out.println();
		// printing rows
		for (int i=0; i < array.length; i++)
		{
			System.out.print("  ");
			for (int j=0; j < array[i].length; j++)
			{
				System.out.print("+---");
			}
			System.out.println("+");
			System.out.print(i + " ");
			for (int j=0; j < array[i].length; j++)
			{
			    // using coordinates array to alter the blank array
				System.out.print("| " + coordsArray[j][i] + " ");
			}
			System.out.print("|");
			System.out.println(" " + i);
		}
		System.out.print("  ");
		for (int i=0; i < array[0].length; i++)
		{
			System.out.print("+---");
		}
		System.out.println("+");
		System.out.print("    ");
		for (int i=0; i < array[0].length; i++)
		{
			System.out.print(i + "   ");
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
	    // creating coordinates array
		String[][] coordinatesArray = new String[10][10];
		for (String[] c : coordinatesArray)
		    Arrays.fill(c, " ");
		// spawning players
		coordinatesArray[0][0] = "1";
		coordinatesArray[9][9] = "2";
		// adding random barrier
		coordinatesArray[(int) (Math.random()*8 + 1)][(int) (Math.random()*8 + 1)] = ".";
		
		int turn = 0;
		
		boolean run = true;

		Scanner scanner = new Scanner(System.in);
		
		while (run)
		{
		    // initializing player variables based on turn
			String player;
			String otherPlayer;
			String playerMarker;
			String otherPlayerMarker;
			if (turn%2 == 0)
			{
			    player = "1";
			    otherPlayer = "2";
			    playerMarker = ".";
			    otherPlayerMarker = ".";
			}
			else
			{
			    player = "2";
			    otherPlayer = "1";
			    playerMarker = ".";
			    otherPlayerMarker = ".";
			}
			// getting current coordinates of player
		    int currentX = -1;
		    int currentY = -1;
		    for (int i = 0; i < coordinatesArray.length; i++)
			    for (int j = 0; j < coordinatesArray[i].length; j++)
			        if (coordinatesArray[i][j].equals(player))
			        {
			            currentX = i;
			            currentY = j;
			        }
	        // checking if player is blocked
			boolean blockedUp = false;
            if (currentY != 0)
            {
                if (!coordinatesArray[currentX][currentY - 1].equals(" "))
                    blockedUp = true;
            }
            else
                blockedUp = true;
            boolean blockedDown = false;
            if (currentY != 9)
            {
                if (!coordinatesArray[currentX][currentY + 1].equals(" "))
                    blockedDown = true;
            }
            else
                blockedDown = true;
            boolean blockedLeft = false;
            if (currentX != 0)
            {
                if (!coordinatesArray[currentX - 1][currentY].equals(" "))
                    blockedLeft = true;
            }
            else
                blockedLeft = true;
            boolean blockedRight = false;
            if (currentX != 9)
            {
                if (!coordinatesArray[currentX + 1][currentY].equals(" "))
                    blockedRight = true;
            }
            else
                blockedRight = true;
            if (blockedUp && blockedDown && blockedLeft && blockedRight)
            {
        		System.out.print("\033[H\033[2J");
        		System.out.flush();
    			printArray(coordinatesArray);
                System.out.println("Player " + otherPlayer + " wins!");
                run = false;
            }
            if (run)
            {
                // clearing screen
        		System.out.print("\033[H\033[2J");
        		System.out.flush();
        		// printing
    			printArray(coordinatesArray);
    		    System.out.println("Player " + player + "'s turn");
    			System.out.println("Enter coordinates as 'x y'");
    			String coordinates = scanner.nextLine();
    			int x;
    			int y;
    			// checking for valid user input
    			if (coordinates.length() == 3)
    				if (coordinates.substring(1, 2).equals(" "))
    				{
    				    // getting x and y
        				x = Integer.parseInt(coordinates.substring(0, 1));
        				y = Integer.parseInt(coordinates.substring(2, 3));
        				// checking if player is moving to blank space
        				if (coordinatesArray[x][y].equals(" "))
        				{
        				    // checking if player is moving laterally
        					if ((currentX == x || currentY == y) && (currentX != x || currentY != y))
        					{
        					    boolean blocked = false;
        					    // checking if player is moving up or down
        					    if (currentX == x)
        					    {
        					        // checking if player is moving up
        					        if (currentY > y)
        					        {
        					            for (int i = y; i <= currentY; i++)
        					                if (coordinatesArray[x][i] != " " && coordinatesArray[x][i] != player)
                    			                blocked = true;
                    			        if (!blocked)
                    			        {
            					            for (int i = y; i < currentY; i++)
                        			            coordinatesArray[x][i + 1] = playerMarker;
                        					coordinatesArray[x][y] = player;
                                			turn++;
                    			        }
        					        }
        					        // checking if player is moving down
        					        else
        					        {
        					            for (int i = currentY; i <= y; i++)
        					                if (coordinatesArray[x][i] != " " && coordinatesArray[x][i] != player)
        					                    blocked = true;
                    			        if (!blocked)
                    			        {
            					            for (int i = currentY; i < y; i++)
                        			            coordinatesArray[x][i] = playerMarker;
                        					coordinatesArray[x][y] = player;
                                			turn++;
                    			        }
        					        }
        					    }
                        		else
                        		{
        					        // checking if player is moving left
        					        if (currentX > x)
        					        {
        					            for (int i = x; i <= currentX; i++)
        					                if (coordinatesArray[i][y] != " " && coordinatesArray[i][y] != player)
                    			                blocked = true;
                    			        if (!blocked)
                    			        {
            					            for (int i = x; i < currentX; i++)
                        			            coordinatesArray[i + 1][y] = playerMarker;
                        					coordinatesArray[x][y] = player;
                                			turn++;
                    			        }
        					        }
        					        // checking if player is moving right
        					        else
        					        {
        					            for (int i = currentX; i <= x; i++)
        					                if (coordinatesArray[i][y] != " " && coordinatesArray[i][y] != player)
                    			                blocked = true;
                    			        if (!blocked)
                    			        {
            					            for (int i = currentX; i < x; i++)
                        			            coordinatesArray[i][y] = playerMarker;
                        					coordinatesArray[x][y] = player;
                                			turn++;
                    			        }
        					        }
                        		}
        				    }
        				}
    				}
            }
		}
	}
}
