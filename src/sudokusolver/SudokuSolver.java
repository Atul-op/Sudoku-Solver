/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sudokusolver;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Atul Gupta
 */
public class SudokuSolver extends JFrame{

    /**
     * @param args the command line arguments
     */
     private static final int SIZE = 9;
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private JButton solveButton;
    private boolean[][] isUserInput = new boolean[SIZE][SIZE];
    public static int[][] grid = new int[SIZE][SIZE];

    public SudokuSolver() {
        setTitle("Sudoku Input");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(SIZE, SIZE));

        // Create 9x9 input grid
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("SansSerif", Font.BOLD, 18));
                cells[row][col] = cell;

                // Add border highlight for 3x3 boxes
                if ((row % 3 == 0) && (row != 0)) {
                    cell.setBorder(BorderFactory.createMatteBorder(2, 1, 1, 1, Color.BLACK));
                }
                if ((col % 3 == 0) && (col != 0)) {
                    cell.setBorder(BorderFactory.createMatteBorder(1, 2, 1, 1, Color.BLACK));
                }

                gridPanel.add(cell);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        // Solve Button
        solveButton = new JButton("Solve");
        solveButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        solveButton.addActionListener(e -> {
            getInputGrid();
            if(Helper.solveSudoku(grid))
            {
                displaySolvedGrid(grid);
                JOptionPane.showMessageDialog(this, "Sudoku Solved Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            
            printGrid(grid); // Replace this with actual solver later
        });

        add(solveButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Extract values from the text fields into a 2D array
    private int[][] getInputGrid() {
        int[][] grid = new int[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                String text = cells[row][col].getText().trim();
                if (text.isEmpty()) {
                    grid[row][col] = 0;
                    isUserInput[row][col] = false;
                } else {
                    grid[row][col] = Integer.parseInt(text);
                    isUserInput[row][col] = true;
                    cells[row][col].setForeground(Color.BLACK); // original input in black
                }
            }
        }
        return grid;
    }
    
    private void displaySolvedGrid(int[][] solvedGrid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col].setText(String.valueOf(solvedGrid[row][col]));

                if (!isUserInput[row][col]) {
                    cells[row][col].setForeground(new Color(0, 102, 204)); // solved values in blue
                }
            }
        }
    }


    // For testing: print the grid to console
    private void printGrid(int[][] grid) {
        System.out.println("Sudoku Input:");
        for (int[] row : grid) {
            for (int val : row) {
                System.out.print((val == 0 ? "." : val) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuSolver::new);
    }
    
}
