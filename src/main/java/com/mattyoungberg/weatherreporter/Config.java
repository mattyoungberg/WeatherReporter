package com.mattyoungberg.weatherreporter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private final String ipStackKey;
    private final String openWeatherKey;
    private final Unit unit;

    public Config() throws IOException {
        String result = "";
        Properties properties = new Properties();
        InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("config.properties");
        if(inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("Config file `config.properties` could not be found in the classpath.");
        }

        this.ipStackKey = properties.getProperty("IPStackAccessKey");
        this.openWeatherKey = properties.getProperty("OpenWeatherMapAPIKey");
        this.unit = Unit.valueOf(Unit.class, properties.getProperty("Metrics").toUpperCase());
    }

    public String getIpStackKey() {
        return ipStackKey;
    }

    public String getOpenWeatherKey() {
        return openWeatherKey;
    }

    public Unit getUnit() {
        return unit;
    }
}
