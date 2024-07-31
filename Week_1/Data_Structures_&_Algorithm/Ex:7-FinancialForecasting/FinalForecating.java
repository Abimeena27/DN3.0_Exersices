public class FinancialForecasting {

    public static double calculateFV(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        return calculateFV(presentValue * (1 + growthRate), growthRate, years - 1);
    }

    public static void main(String[] args) {
        double presentValue = 1000.0; 
        double growthRate = 0.05;     
        int years = 10;              

        double futureValue = calculateFV(presentValue, growthRate, years);
        System.out.println("Future Value: $" + futureValue);
    }
}
