package sudokusolver;

/**
 *
 * @author Atul Gupta
 */
public class Helper
{
    public static boolean available(int r , int c , int i)
    {
        for(int j = 0;j < SudokuSolver.grid[0].length;j++)
        {
            if(SudokuSolver.grid[r][j] == i)
                return false;
            if(SudokuSolver.grid[j][c] == i)
                return false;    
        }
        for(int j = 0;j < 3;j++)
            for(int k = 0;k < 3;k++)
                if(SudokuSolver.grid[r-(r%3)+j][c-(c%3)+k] == i)
                    return false;
        return true;
    }
    public static boolean solveSudoku()
    {
        int row = -1;
        int col = -1;
        boolean emptyElement = false;
        for(int i = 0;i < SudokuSolver.grid.length;i++)
        {
            for(int j = 0;j < SudokuSolver.grid[0].length;j++)
            {
                if(SudokuSolver.grid[i][j] == 0)
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
            return true; // Sudoku Solved
        }

        for(int i = 1;i <= 9;i++)
        {
            if(available(row, col, i))
            {
                SudokuSolver.grid[row][col] = i;
                if(solveSudoku())
                    return true;
                else    
                    SudokuSolver.grid[row][col] = 0;
            }
        }
        return false;
    }
}
