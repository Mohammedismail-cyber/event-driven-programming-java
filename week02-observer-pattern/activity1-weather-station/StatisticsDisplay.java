public class StatisticsDisplay implements Observer {

    // NOTE: Float.MIN_VALUE is the smallest POSITIVE float (not the most
    // negative), so -Float.MAX_VALUE is the correct starting maximum.
    private float maxTemp = -Float.MAX_VALUE;
    private float minTemp = Float.MAX_VALUE;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        maxTemp = Math.max(maxTemp, temperature);
        minTemp = Math.min(minTemp, temperature);
        System.out.println("  [Statistics] Max: " + maxTemp
                + " C, Min: " + minTemp + " C");
    }
}
