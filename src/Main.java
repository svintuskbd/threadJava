import core.Weather;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class Main {
    private static Weather weather;

    public static void main(String[] args) {
        weather = new Weather();
        weather.start();

        OneThread oneThread = new OneThread();
        oneThread.start();
    }

    static class OneThread extends Thread {
        private Map<String, String> dictionary = new HashMap<>();

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                initDictionary();
                ConcurrentHashMap<String, String> weatherData = weather.getWeatherData();
                weatherData.forEach((key, value) -> {
                    if (dictionary.get(key) != null) {
                        System.out.println(dictionary.get(key) + " - " + value);
                    }
                });

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void initDictionary() {
            dictionary.put("sity", "город");
            dictionary.put("temp", "температура");
            dictionary.put("humidity", "влажность");
            dictionary.put("pressure", "давление");
        }
    }
}
