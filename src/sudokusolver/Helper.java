/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sudokusolver;

/**
 *
 * @author Atul Gupta
 */
public class Helper
{
    public static boolean available(int[][] grid , int r , int c , int i)
    {
        for(int j = 0;j < grid[0].length;j++)
        {
            if(grid[r][j] == i)
                return false;
            if(grid[j][c] == i)
                return false;    
        }
        for(int j = 0;j < 3;j++)
            for(int k = 0;k < 3;k++)
                if(grid[r-(r%3)+j][c-(c%3)+k] == i)
                    return false;
        return true;
    }
    public static boolean solveSudoku(int[][] grid)
    {
        printGrid(grid);
        int row = -1;
        int col = -1;
        boolean emptyElement = false;
        for(int i = 0;i < grid.length;i++)
        {
            for(int j = 0;j < grid[0].length;j++)
            {
                if(grid[i][j] == 0)
                {
                    row = i;
                    col = j;
                    emptyElement = true;
                    break;
                }
            }
            if(emptyElement)
                break;
        } //we get the r and c of empty cell

        if(emptyElement == false)
        {
            printGrid(grid);
            return true; // Sudoku Solved
        }

        for(int i = 1;i <= 9;i++)
        {
            if(available(grid , row, col, i))
            {
                grid[row][col] = i;
                if(solveSudoku(grid))
                    return true;
                else    
                    grid[row][col] = 0;
            }
        }
        return false;
    }
    static void printGrid(int[][] grid)
    {
        for(int[] row : grid)
        {
            for(int element : row)
            {
                System.out.print(element+" ");
            }
            System.out.println();
        }
    }
    public static int[][] copy2DArray(int[][] original) {
        int[][] copy = new int[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }
}
