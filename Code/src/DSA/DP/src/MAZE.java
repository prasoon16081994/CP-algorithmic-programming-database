import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Shreyans on 4/30/2015 at 10:27 PM using IntelliJ IDEA
 */

class MAZE {
	static int r, c, s1, s2, f1, f2;//Rows, Columns, Start Coordinates, Finish Coordinates
	static int[] dx = {1, -1, 0, 0};//right, left, NA, NA
	static int[] dy = {0, 0, 1, -1};//NA, NA, top, bottom
	static char[][] grid;//Main grid
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);//I suggest using faster IO if you have performance concerns. I did. Scanner is readable hence the choice
		r = sc.nextInt();
		c = sc.nextInt();
		grid = new char[r][c];
		for (int i = 0; i < r; i++) {
			char[] s1 = sc.next().toCharArray();//Reading a line of the Grid
			System.arraycopy(s1, 0, grid[i], 0, c);//Nice inbuilt function to copy contents of an array. Also doable manually
		}
		s1 = sc.nextInt() - 1;
		s2 = sc.nextInt() - 1;
		f1 = sc.nextInt() - 1;
		f2 = sc.nextInt() - 1;
		if (MAZEBFS()) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
	
	private static boolean MAZEBFS() {
		if (s1 == f1 && s2 == f2) {
			int[] curr = {s1, s2};
			for (int i = 0; i < 4; i++)//for each direction
			{
				if ((curr[0] + dx[i] >= 0 && curr[0] + dx[i] < r) && (curr[1] + dy[i] >= 0 && curr[1] + dy[i] < c)) {
					//Checked if x and y are correct. ALL IN 1 GO
					int xc = curr[0] + dx[i];//Setting current x coordinate
					int yc = curr[1] + dy[i];//Setting current y coordinate
					if (grid[xc][yc] == 'E')//Cant jump on same tile so he gotta go and come back. Only possible when adjacent tiles are empty
					{
						//System.out.println(xc+" "+yc);
						return true;
					}
				}
			}
			return false;
		} else {
			grid[f1][f2] = 'G';//goal
			Queue<int[]> q = new LinkedList<int[]>();
			int[] start = {s1, s2};//Start Coordinates
			q.add(start);//Adding start to the queue since we're already visiting it
			grid[s1][s2] = 'X';
			while (q.peek() != null) {
				int[] curr = q.poll();//poll or remove. Same thing
				for (int i = 0; i < 4; i++)//for each direction
				{
					if ((curr[0] + dx[i] >= 0 && curr[0] + dx[i] < r) && (curr[1] + dy[i] >= 0 && curr[1] + dy[i] < c)) {
						//Checked if x and y are correct. ALL IN 1 GO
						int xc = curr[0] + dx[i];//Setting current x coordinate
						int yc = curr[1] + dy[i];//Setting current y coordinate
						if (grid[xc][yc] == 'G')//Destination found
						{
							//System.out.println(xc+" "+yc);
							return true;
						} else if (grid[xc][yc] == '.')//Movable. Can't return here again so setting it to 'X' now
						{
							//System.out.println(xc+" "+yc);
							grid[xc][yc] = 'X';//now BLOCKED
							int[] temp = {xc, yc};
							q.add(temp);//Adding current coordinates to the queue
						}
					}
				}
			}
			return false;//Will return false if nothing satisfies
		}
	}
}