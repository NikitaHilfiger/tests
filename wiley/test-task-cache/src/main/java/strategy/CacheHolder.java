package strategy;

import caching.Cache;

import java.io.IOException;

public interface CacheHolder<K, V> {

    Cache<K, V> getCache();

    void recache() throws IOException, ClassNotFoundException;

}
