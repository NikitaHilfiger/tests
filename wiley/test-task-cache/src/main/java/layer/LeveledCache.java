package layer;

import caching.Cache;

import java.io.IOException;

public interface LeveledCache<K, V> {

    /**
     * @return Returns all levels of cache wrapped in one {@link Cache} instance.
     */
    Cache<K, V> asCache();

    /**
     * Redistibutes cached objects within levels. Strategy of redistibuting depends on implementation.
     */
    void recache() throws IOException, ClassNotFoundException;

}
