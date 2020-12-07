package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("No filename passed.");
            return;
        }

        String fileName = args[0];
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {
            ReadabilityCalculator readabilityCalculator = new ReadabilityCalculator(scanner);
            readabilityCalculator.printStats();

            boolean done = false;
            while(!done) {
                Scanner keyboard = new Scanner(System.in);
                System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
                String scoreToCalculate = keyboard.nextLine().toUpperCase();
                System.out.println();
                switch (scoreToCalculate) {
                    case "ARI":
                        readabilityCalculator.printARI();
                        done = true;
                        break;
                    case "FK":
                        readabilityCalculator.printFK();
                        done = true;
                        break;
                    case "SMOG":
                        readabilityCalculator.printSMOG();
                        done = true;
                        break;
                    case "CL":
                        readabilityCalculator.printCL();
                        done = true;
                        break;
                    case "ALL":
                        readabilityCalculator.printAll();
                        done = true;
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + fileName);
        }
    }
}
