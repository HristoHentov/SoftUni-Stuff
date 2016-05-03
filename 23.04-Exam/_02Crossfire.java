import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class _02Final {
    public static int rows = 0;
    public static int cols = 0;
    public static ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();

    public static void main(String[] args) {
        Scanner get = new Scanner(System.in);

        String[] input = get.nextLine().split(" ");
        rows = Integer.parseInt(input[0]);
        cols = Integer.parseInt(input[1]);

        generateMatrix();

        String line = get.nextLine();
        while (!(line.equals("Nuke it from orbit"))) {
            String[] inArgz = line.split(" ");

            int targetRow = Integer.parseInt(inArgz[0]);
            int targetCol = Integer.parseInt(inArgz[1]);
            int targetRad = Integer.parseInt(inArgz[2]);

            shootAtMatrix(targetRow, targetCol, targetRad);
            wipeMatrix();

            line = get.nextLine();
        }

        printMatrix();
    }
    public static void wipeMatrix(){
        for(int currentRow = 0; currentRow < matrix.size(); currentRow++) {
            for (int currentCol = 0; currentCol < matrix.get(currentRow).size(); currentCol++) {
                if (matrix.get(currentRow).get(currentCol) == -1){
                    matrix.get(currentRow).remove(currentCol);
                    currentCol--;
                }
            }
            if(matrix.get(currentRow).size() == 0){
                matrix.remove(currentRow);
                currentRow--;
            }
        }
    }
    public static void shootAtMatrix(int hitRow, int hitCol, int hitRad){
        for(int currentRow = hitRow - hitRad; currentRow <= hitRow + hitRad; currentRow++){
            if(checkElement(currentRow, hitCol)){
                matrix.get(currentRow).set(hitCol, -1);
            }
        }

        for(int currentCol = hitCol - hitRad; currentCol <= hitCol + hitRad; currentCol++){
            if(checkElement(hitRow, currentCol))
                matrix.get(hitRow).set(currentCol, -1);
        }
    }


    public static boolean checkElement(int row, int col){
        if(row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(row).size())
            return true;
        else
            return false;
    }

    public static void generateMatrix() {
        int counter = 1;
        for (int currentRow = 0; currentRow < rows; currentRow++) {
            matrix.add(currentRow, new ArrayList<>());
            for (int currentCol = 0; currentCol < cols; currentCol++) {
                matrix.get(currentRow).add(counter);
                counter++;
            }
        }
    }

    public static void printMatrix(){
        for(int r = 0; r < matrix.size(); r++){
            for (int c = 0; c < matrix.get(r).size(); c++) {
                System.out.print(matrix.get(r).get(c) + " ");
            }
            System.out.println();
        }
    }
}
