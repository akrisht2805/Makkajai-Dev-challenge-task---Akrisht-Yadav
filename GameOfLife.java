import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameOfLife {

    // Function to calculate the next state of live cells
    public static Set<String> gameOfLife(List<int[]> initialState) {
        // Set of live cells for quick lookup
        Set<String> liveCells = new HashSet<>();
        for (int[] cell : initialState) {
            liveCells.add(cell[0] + "," + cell[1]);
        }
        
        // Array of all 8 possible directions (neighbors)
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
        };

        // Map to count live neighbors for each cell
        Map<String, Integer> neighborCount = new HashMap<>();

        // Count live neighbors for each live cell and its neighbors
        for (String cell : liveCells) {
            String[] parts = cell.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);

            for (int[] direction : directions) {
                int nx = x + direction[0];
                int ny = y + direction[1];
                String neighbor = nx + "," + ny;
                neighborCount.put(neighbor, neighborCount.getOrDefault(neighbor, 0) + 1);
            }
        }

        // Set to store the next state of live cells
        Set<String> newLiveCells = new HashSet<>();

        // Apply Game of Life rules
        for (Map.Entry<String, Integer> entry : neighborCount.entrySet()) {
            String cell = entry.getKey();
            int count = entry.getValue();

            // Check if the cell is currently live
            boolean isLive = liveCells.contains(cell);

            // Apply rules for live and dead cells
            if (isLive && (count == 2 || count == 3)) {
                newLiveCells.add(cell);
            } else if (!isLive && count == 3) {
                newLiveCells.add(cell);
            }
        }

        return newLiveCells;
    }

    // Helper function to print the set of live cells
    public static void printLiveCells(Set<String> liveCells) {
        for (String cell : liveCells) {
            System.out.println(cell.replace(",", " "));
        }
    }

    // Main function to test the Game of Life logic
    public static void main(String[] args) {

        // Test Input A (Block pattern - Still life)
        List<int[]> inputA = List.of(new int[]{1, 1}, new int[]{1, 2}, new int[]{2, 1}, new int[]{2, 2});
        System.out.println("Output A:");
        printLiveCells(gameOfLife(inputA));

        // Test Input B (Boat pattern - Still life)
        List<int[]> inputB = List.of(new int[]{0, 1}, new int[]{1, 0}, new int[]{2, 1}, new int[]{0, 2}, new int[]{1, 2});
        System.out.println("Output B:");
        printLiveCells(gameOfLife(inputB));

        // Test Input C (Blinker pattern - oscillator)
        List<int[]> inputC = List.of(new int[]{1, 1}, new int[]{1, 0}, new int[]{1, 2});
        System.out.println("Output C:");
        printLiveCells(gameOfLife(inputC));

        // Test Input D (Toad pattern - two-phase oscillator)
        List<int[]> inputD = List.of(new int[]{1, 1}, new int[]{1, 2}, new int[]{1, 3}, new int[]{2, 2}, new int[]{2, 3}, new int[]{2, 4});
        System.out.println("Output D:");
        printLiveCells(gameOfLife(inputD));
        
    }
}
