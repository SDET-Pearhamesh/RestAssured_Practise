package org.practise;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

    public class ConfigUtil {

        private static Properties properties;

        static {
            loadProperties();
        }

        private static void loadProperties() {
            properties = new Properties();
            try (InputStream input = ConfigUtil.class.getClassLoader()
                    .getResourceAsStream("config.properties")) {

                if (input == null) {
                    throw new RuntimeException("config.properties file not found in src/test/resources");
                }

                properties.load(input);

            } catch (IOException e) {
                throw new RuntimeException("Failed to load config.properties", e);
            }
        }

        public static String getApiKey() {
            String apiKey = properties.getProperty("api.key");
            if (apiKey == null || apiKey.trim().isEmpty()) {
                throw new RuntimeException("API key not configured. Please check config.properties file");
            }
            return apiKey;
        }

        public static String getBaseUri() {
            return properties.getProperty("base.uri", "https://api.postman.com");
        }

        public static String getProperty(String key) {
            return properties.getProperty(key);
        }

        public static String getProperty(String key, String defaultValue) {
            return properties.getProperty(key, defaultValue);
        }


}
