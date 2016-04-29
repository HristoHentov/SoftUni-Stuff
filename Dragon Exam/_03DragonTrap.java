import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class _03DragonTrap {
    public static List<ArrayList<Character>> matrix = new ArrayList<>();
    public static List<List<Character>> matrixCopy = new ArrayList<>();
    public static List<Character> unwrappedMatrix = new ArrayList<>();
    public static Scanner get = new Scanner(System.in);

    public static void main(String[] args) {
        int matrixSize = get.nextInt();
        get.nextLine(); // set cursor to next line.

        generateMatrix(matrixSize);

        String line = get.nextLine();
        while (!(line.equals("End"))) {
            String[] input = line.split(" ");

            int centerX = Integer.parseInt(input[0].replace('(', '\0').trim());
            int centerY = Integer.parseInt(input[1].replace(')', '\0').trim());
            int radius = Integer.parseInt(input[2]);
            int rotations = Integer.parseInt(input[3]);

            unwrapMatrix(centerX, centerY, radius);
            rotateMatrix(rotations);
            wrapMatrix(centerX, centerY, radius);

            unwrappedMatrix.clear();
            line = get.nextLine();
        }

        printMatrix();
        System.out.println("Symbols changed: " + findDifferentSymbols());
    }

    public static void rotateMatrix(int amount) {
        List<Character> tempArray = new ArrayList<>();

        for (int i = 0; i < unwrappedMatrix.size(); i++) {
            tempArray.add('\0');
        }

        int arraySize = unwrappedMatrix.size();

            for (int i = 0; i < arraySize; i++) {
                if(amount > 0)
                 tempArray.set(Math.abs(i + amount + arraySize) % arraySize, unwrappedMatrix.get(i));
                else {
                    int offset = Math.abs(amount) % arraySize;
                    int index = i - offset;
                    if(index < 0)
                        index = arraySize - Math.abs(index);

                    tempArray.set(index, unwrappedMatrix.get(i));
                }
            }

        for(int i = 0; i < tempArray.size(); i++){
            unwrappedMatrix.set(i, tempArray.get(i));
        }
    }


    public static void wrapMatrix(int y, int x, int radius) {
        int nextFreeIndex = 0;

        for (int currentColumn = x - radius; currentColumn <= x + radius; currentColumn++) { // gets first row (top side of square)
            if (elementIsInMatrix(y - radius, currentColumn)) {
                matrix.get(y - radius).set(currentColumn, unwrappedMatrix.get(nextFreeIndex));
                nextFreeIndex++;
            }
        }

        for (int currentRow = (y - radius) + 1; currentRow <= y + radius; currentRow++) { // gets first column (right side of square)
            if (elementIsInMatrix(currentRow, x + radius)) {
                matrix.get(currentRow).set(x + radius, unwrappedMatrix.get(nextFreeIndex));
                nextFreeIndex++;
            }
        }

        for (int currentColumn = (x + radius) - 1; currentColumn >= x - radius; currentColumn--) { // gets second row (bottom side of square)
            if (elementIsInMatrix(y + radius, currentColumn)) {
                matrix.get(y + radius).set(currentColumn, unwrappedMatrix.get(nextFreeIndex));
                nextFreeIndex++;
            }
        }

        for (int currentRow = (y + radius) - 1; currentRow > y - radius; currentRow--) {
            if (elementIsInMatrix(currentRow, x - radius)) {
                matrix.get(currentRow).set(x - radius, unwrappedMatrix.get(nextFreeIndex));
                nextFreeIndex++;
            }
        }
    }


    public static void unwrapMatrix(int y, int x, int radius) {
        List<Character> tempUnwrap = new ArrayList<>();
        for (int currentColumn = x - radius; currentColumn <= x + radius; currentColumn++) { // gets first row (top side of square)
            if (elementIsInMatrix(y - radius, currentColumn))
                tempUnwrap.add(matrix.get(y - radius).get(currentColumn));
        }

        for (int currentRow = (y - radius) + 1; currentRow <= y + radius; currentRow++) { // gets first column (right side of square)
            if (elementIsInMatrix(currentRow, x + radius))
                tempUnwrap.add(matrix.get(currentRow).get(x + radius));
        }

        for (int currentColumn = (x + radius) - 1; currentColumn >= x - radius; currentColumn--) { // gets second row (bottom side of square)
            if (elementIsInMatrix(y + radius, currentColumn))
                tempUnwrap.add(matrix.get(y + radius).get(currentColumn));
        }

        for (int currentRow = (y + radius) - 1; currentRow > y - radius; currentRow--) {
            if (elementIsInMatrix(currentRow, x - radius))
                tempUnwrap.add(matrix.get(currentRow).get(x - radius));
        }

        for (char element : tempUnwrap) {
            unwrappedMatrix.add(element);
        }
    }

    public static int findDifferentSymbols() {
        int changedSymbols = 0;
        for (int i = 0; i < matrix.size(); i++) {
            for (int c = 0; c < matrix.get(i).size(); c++) {
                if (matrix.get(i).get(c) != matrixCopy.get(i).get(c))
                    changedSymbols++;
            }
        }
        return changedSymbols;
    }

    public static boolean elementIsInMatrix(int x, int y) {
        if (x >= 0 && x < matrix.size() && y >= 0 && y < matrix.get(x).size())
            return true;
        else
            return false;

    }

    public static void generateMatrix(int n) {
        String[] currentRow = get.nextLine().split("");
        for (int r = 0; r < n; r++) {

            matrix.add(new ArrayList<>());
            matrixCopy.add(new ArrayList<Character>());

            for (int c = 0; c < currentRow.length; c++) {
                matrix.get(r).add(currentRow[c].toCharArray()[0]);
                matrixCopy.get(r).add(currentRow[c].toCharArray()[0]);
            }

            if (r < (n - 1))
                currentRow = get.nextLine().split("");

        }

    }

    public static void printMatrix() {
        for (int i = 0; i < matrix.size(); i++) {
            for (int c = 0; c < matrix.get(i).size(); c++) {
                System.out.print(matrix.get(i).get(c));
            }
            System.out.println();
        }
    }
}