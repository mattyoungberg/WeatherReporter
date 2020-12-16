package com.mattyoungberg.weatherreporter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    public static Config getConfig() throws IOException {
        String result = "";
        Properties properties = new Properties();
        InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("config.properties");
        if(inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("Config file `config.properties` could not be found in the classpath.");
        }
        return new Config(properties.getProperty("IPStackAccessKey"), properties.getProperty("OpenWeatherMapAPIKey"),
                Unit.valueOf(Unit.class, properties.getProperty("Metrics").toUpperCase()));
    }
}
