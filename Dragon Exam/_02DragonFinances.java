import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

@SuppressWarnings("ALL")
public class _02DragonFinances {
    public static TreeMap<Integer, Double> salarySheet = new TreeMap<>();
    public static ArrayList<Integer> workers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner get = new Scanner(System.in);
        double capital = Double.parseDouble(get.nextLine());
        String line = get.nextLine();

        int currentDay = 1;
        while (!(line.equals("END"))) {
            String[] input = line.split(";");

            workers.add(Integer.parseInt(input[0])); // ADD Hired workers to list
            fireWorkers(Integer.parseInt(input[1])); // FIRE WORKERS
            salarySheet.put(currentDay, Double.parseDouble(input[2])); // ADD SALARY FOR WORKER

            capital -= giveSalaries(salarySheet, workers, capital);

            for(int i = 3; i < input.length; i++) {
                String[] moneyEvent = input[i].split(":");
                switch (moneyEvent[0]) {
                    case "Taxes" : capital -= Double.parseDouble(moneyEvent[1]); break;
                    case "Machines" : capital -= Double.parseDouble(moneyEvent[1]); break;
                    case "Previous years deficit " : capital -= Double.parseDouble(moneyEvent[1]);break;

                    case "Product development" : capital += Double.parseDouble(moneyEvent[1]); break;
                    case "Unconditional funding" : capital += Double.parseDouble(moneyEvent[1]); break;
                }
            }

            currentDay++;
            line = get.nextLine();
        }
        int employeesLeft = 0;
        for(int i = 0; i < workers.size(); i++) {
            employeesLeft += workers.get(i);
        }
        System.out.println("\nEMPLOYEES: " + employeesLeft);
        System.out.printf("CAPITAL: %.4f", capital); // not proper...makes 9.58927298 equal to 95892.7298
        System.out.println("\n");


    }

    public static void fireWorkers(int amount) {
        int currWorker = 0;
        for (int i = 0; i < amount; i++) {
            if (workers.get(currWorker) != 0)
                workers.set(currWorker, workers.get(currWorker) - 1);
            else {
                currWorker++;
                workers.set(currWorker, workers.get(currWorker) - 1);
            }
        }
    }

    public static double giveSalaries(TreeMap<Integer, Double> salries, ArrayList<Integer> workers, double capital) {
        double salaryAmount = 0;
        for(int i = 0; i < workers.size(); i++) {
            salaryAmount += workers.get(i) * (salries.get(i + 1) / 30);
        }

        return salaryAmount;
    }
}

