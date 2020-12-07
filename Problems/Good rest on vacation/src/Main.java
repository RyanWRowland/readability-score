import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int durationDays = scanner.nextInt();
        int foodCostPerDay = scanner.nextInt();
        int oneWayFlightCost = scanner.nextInt();
        int costOfOneNightHotel = scanner.nextInt();

        int totalCost = durationDays * foodCostPerDay + (durationDays - 1) * costOfOneNightHotel + oneWayFlightCost * 2;
        System.out.println(totalCost);
    }
}