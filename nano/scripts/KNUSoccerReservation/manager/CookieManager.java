package manager;

import java.util.HashMap;
import java.util.Map;

public class CookieManager {
    private static final Map<String, String> cookieRepository = new HashMap<String, String>();

    public static void add(String key, String value) {
        cookieRepository.put(key, value);
    }

    public static String getCookie(String key) {
        return cookieRepository.get(key);
    }
}
