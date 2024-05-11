package vasilkov.cache;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public final class InMemoryCache<K,V> {

    private static InMemoryCache INSTANCE = null;

    private final Map<K, V> cache;

    private InMemoryCache() {
        cache = new HashMap<>();
    }

    public static InMemoryCache getInstance() {

        if (INSTANCE == null) {
                INSTANCE = new InMemoryCache();
        }

        return INSTANCE;
    }
}
