public static double calculateFV(double presentValue, double growthRate, int years) {
    double futureValue = presentValue;
    for (int i = 0; i < years; i++) {
        futureValue *= (1 + growthRate);
    }
    return futureValue;
}
