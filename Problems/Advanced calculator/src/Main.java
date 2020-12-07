/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        // write your code here
        switch (operator) {
            case "MAX":
                int max = Integer.MIN_VALUE;
                for (int i = 1; i < args.length; i++){
                    int number = Integer.parseInt(args[i]);
                    if (number > max) {
                        max = number;
                    }
                }
                System.out.println(max);
                break;
            case "MIN":
                int min = Integer.MAX_VALUE;
                for (int i = 1; i < args.length; i++){
                    int number = Integer.parseInt(args[i]);
                    if (number < min) {
                        min = number;
                    }
                }
                System.out.println(min);
                break;
            case "SUM":
                int sum = 0;
                for (int i = 1; i < args.length; i++){
                    int number = Integer.parseInt(args[i]);
                    sum += number;
                }
                System.out.println(sum);
                break;
            default: 
                System.out.println("Unknown operator");
                break;
        }
    }
}