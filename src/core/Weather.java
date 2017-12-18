package core;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Weather extends Thread {
    private ConcurrentHashMap<String, String> weatherData = new ConcurrentHashMap<>();

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            setWeatherData("Kharkiv");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setWeatherData(String name) {

        this.weatherData.put("sity", name);
        this.weatherData.put("temp", this.getRandom(10));
        this.weatherData.put("humidity", this.getRandom(50));
        this.weatherData.put("pressure", this.getRandom(100));
    }

    public ConcurrentHashMap<String, String> getWeatherData() {
        return this.weatherData;
    }

    private String getRandom(int i) {
        Random r = new Random(System.currentTimeMillis());
        return String.valueOf(r.nextInt(i));
    }
}
