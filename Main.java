import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

class Main {
  public static void main(String args[]) throws Exception {
    char[][] maze;

    Scanner file = new Scanner(new BufferedReader(new FileReader("maze.txt")));
    int rows = 0;
    int cols = 0;
    int currX;
    int currY;

    Stack<Integer> posX = new Stack<Integer>();
    Stack<Integer> posY = new Stack<Integer>();
    boolean[][] visited;

    while(file.hasNext()) {
      String line = file.next();
      rows++;
      cols = line.length();
    }

    maze = new char[rows][cols];
    visited = new boolean[rows][cols];

    file = new Scanner(new BufferedReader(new FileReader("maze.txt")));

    for(int r = 0; r < rows; r++) {
      String thisRow = file.next();
      for(int c = 0; c < cols; c++) {
        maze[r][c] = thisRow.charAt(c);
        visited[r][c] = false;
      }
    }

    // find the start
    
    for(int r = 0; r < rows; r++) {
      for(int c = 0; c < cols; c++) {
        if(maze[r][c] == '@') {
          posX.push(r);
          posY.push(c);
          visited[r][c] = true;
        }
      }
    }

    while(!posX.empty() && !posY.empty()) {
      currX = posX.peek();
      currY = posY.peek();

      if(maze[currX][currY] == '$') {
        // find the end
        break;
      }

      // move to next unvisited neighbor
      if(currX > 0 && maze[currX-1][currY] != '#' && !visited[currX-1][currY]) {
        posX.push(currX-1);
        posY.push(currY);
        visited[currX-1][currY] = true;
      }
      else if(currX < rows-1 && maze[currX+1][currY] != '#' && !visited[currX+1][currY]) {
        posX.push(currX+1);
        posY.push(currY);
        visited[currX+1][currY] = true;
      }
      else if(currY > 0 && maze[currX][currY-1] != '#' && !visited[currX][currY-1]) {
        posX.push(currX);
        posY.push(currY-1);
        visited[currX][currY-1] = true;
      }
      else if(currY < cols-1 && maze[currX][currY+1] != '#' && !visited[currX][currY+1]) {
        posX.push(currX);
        posY.push(currY+1);
        visited[currX][currY+1] = true;
      }
      else {
        // Backtrack
        posX.pop();
        posY.pop();
      }
    }

    Stack<Integer> solutionX = new Stack<Integer>();
    Stack<Integer> solutionY = new Stack<Integer>();


    while(!posX.empty() && !posY.empty()) {
      solutionX.push(posX.pop());
      solutionY.push(posY.pop());
    }

    // Mixed up X and Y so just swapped them here.

    while(!solutionX.empty() && !solutionY.empty()) {
      System.out.println("[" + solutionY.pop() + "," + solutionX.pop() + "]");
    }
  }
}