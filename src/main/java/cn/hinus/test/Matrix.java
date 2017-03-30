package cn.hinus.test;

import java.util.*;
public class Matrix
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in) ;
        int n = in.nextInt();

        int[][] pos = new int[n][n];
        final int RIGHT = 0;
        final int DOWN = 1;
        final int LEFT = 2;
        final int UP = 3;

        int line = -1;
        int row = 0;
        int direction = RIGHT;

        for (int i = 1; i <= n * n; i++) {
            if (direction == RIGHT) {
                line++;
                pos[row][line] = i;

                if (line + 1 >= n || pos[row][line + 1] != 0) {
                    direction = (direction + 1) & 3;
                }
            }
            else if (direction == DOWN) {
                row++;
                pos[row][line] = i;

                if (row + 1 >= n || pos[row + 1][line] != 0) {
                    direction = (direction + 1) & 3;
                }
            }
            else if (direction == LEFT) {
                line --;
                pos[row][line] = i;

                if (line == 0 || pos[row][line - 1] != 0) {
                    direction = (direction + 1) & 3;
                }
            }
            else if (direction == UP) {
                row --;
                pos[row][line] = i;

                if (row == 0 || pos[row - 1][line] != 0) {
                    direction = (direction + 1) & 3;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(pos[i][j] + " ");
            }
            System.out.println("");
        }
    }
}