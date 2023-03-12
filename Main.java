import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

class Main {
  public static void main(String args[]) throws Exception {
    char[][] maze;

    Scanner file = new Scanner(new BufferedReader(new FileReader("maze.txt")));
    int rows = 0;
    int cols = 0;
    int lastX;
    int lastY;
    int currX;
    int currY;
    boolean solved = false;

    Stack posX = new Stack();
    Stack posY = new Stack();

    while(file.hasNext()) {
      String line = file.next();
      rows++;
      cols = line.length();
    }

    maze = new char[rows][cols];

    file = new Scanner(new BufferedReader(new FileReader("maze.txt")));

    for(int r = 0; r < rows; r++) {
      String thisRow = file.next();
      for(int c = 0; c < cols; c++) {
        maze[r][c] = thisRow.charAt(c);
      }
    }

    // After reading the file, we need to find the start.
    
    for(int r = 0; r < rows; r++) {
      for(int c = 0; c < cols; c++) {
        if(maze[r][c] == '@') {
          posX.push(r);
          posY.push(c);
        }
      }
    }
    

    while(solved == false) {
      currX = posX.peek();
      currY = posY.peek();
      if(maze[][posY.peek()] == '$') {
        solved = true;
      }
    }
  }
}
