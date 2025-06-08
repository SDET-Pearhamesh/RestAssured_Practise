package org.practise;

    public class ConfigUtil {

        public static String getApiKey() {
            String apiKey = System.getenv("POSTMAN_API_KEY");

            if (apiKey == null || apiKey.trim().isEmpty()) {
                throw new RuntimeException("API key not configured. Please set the 'POSTMAN_API_KEY' environment variable.");
            }
            return apiKey;
        }
}
