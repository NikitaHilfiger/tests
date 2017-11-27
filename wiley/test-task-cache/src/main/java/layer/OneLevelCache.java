package layer;

import caching.Cache;

public class OneLevelCache<K, V> implements LeveledCache<K, V> {

    private final Cache<K, V> cache;

    public OneLevelCache(Cache<K, V> cache) {
        this.cache = cache;
    }

    @Override
    public Cache<K, V> asCache() {
        return cache;
    }

    @Override
    public void recache() {
        // do nothing
    }
}