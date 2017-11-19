
public class Node {

    int t[];
    String maze[][];
    int start_x = 0, start_y = 0, end_x = -1, end_y = -1;
    boolean mine;

    public Node(int height,int width, String input[],boolean mine)
    {
        t=new int[input.length];
        for (int i = 0; i <input.length ; i++) {
            t[i]=Integer.parseInt(input[i]);
        }
        this.mine=mine;
        // Each alternate extra row and cloumn to maintain the data direction between two node
        maze=new String[2*height-1][2*width-1];

                    for(int row=0;row<maze.length;row++)
                    {
                        for(int col=0;col<maze[0].length;col++)
                        {
                            maze[row][col]="1";
                        }
                    }
    }

    public String[][] toBinaryArray()
    {
        // Converting Input data to binary for direction of each node
        int count = 0;
        for (int x = 0; x < maze.length; x += 2)
            for (int y = 0; y < maze[0].length; y += 2) {

                String s = String.format("%7s", Integer.toBinaryString(t[count])).replace(' ', '0');
                maze[x][y] = "0";
                if ((int) s.charAt(6) - 48 == 1)
                    maze[x - 1][y] = "0";
                if ((int) s.charAt(5) - 48 == 1)
                    maze[x][y + 1] = "0";
                if ((int) s.charAt(4) - 48 == 1)
                    maze[x + 1][y] = "0";
                if ((int) s.charAt(3) - 48 == 1)
                    maze[x][y - 1] = "0";
                if ((int) s.charAt(2) - 48 == 1) {
                    start_x = x;
                    start_y = y;
                    maze[x][y] = "s";
                }
                if ((int) s.charAt(1) - 48 == 1) {
                    end_x = x;
                    end_y = y;
                    maze[x][y] = "e";
                }
                if ((int) s.charAt(0) - 48 == 1) {
                 if(mine)
                    maze[x][y] = "1";
                 else
                     maze[x][y] = "m";
                }
                count++;
            }
        return maze;
    }
}



