package strategy;

import caching.Cache;
import layer.MultipleLevelCache;
import layer.OneLevelCache;
import caching.disk.FileSerializationCache;
import caching.disk.strategy.StandardCustomSerializer;
import caching.ram.MemoryCache;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serializable;

@Slf4j
public class CacheStrategies<K extends Serializable, V extends Serializable> {

    public final CacheHolder<K, V> ONE_LEVEL_MEMORY = new CacheHolder<K, V>() {

        private OneLevelCache<K, V> cacheHolder;

        {
            this.cacheHolder = new OneLevelCache<K, V>(new MemoryCache<K, V>());
        }

        @Override
        public Cache<K, V> getCache() {
            return cacheHolder.asCache();
        }

        @Override
        public void recache() {
            this.cacheHolder.recache();
        }
    };

    public final CacheHolder<K, V> ONE_LEVEL_HARDDISK = new CacheHolder<K, V>() {

        private OneLevelCache<K, V> cacheHolder;

        {
            FileSerializationCache<K, V> cache = new FileSerializationCache<K, V>();
            cache.setCustomSerializer(new StandardCustomSerializer<K, V>());
            this.cacheHolder = new OneLevelCache<K, V>(cache);
        }

        @Override
        public Cache<K, V> getCache() {
            return cacheHolder.asCache();
        }

        @Override
        public void recache() {
            this.cacheHolder.recache();
        }
    };

    public final CacheHolder<K, V> TWO_LEVELS_MEMORY_HARDDISK = new CacheHolder<K, V>() {

        private MultipleLevelCache<K, V> cacheHolder;

        {
            // using default constructor of MultipleLevelCache which provides
            // needed two levels caching
            this.cacheHolder = new MultipleLevelCache<K, V>();
        }

        @Override
        public Cache<K, V> getCache() {
            return cacheHolder.asCache();
        }

        @Override
        public void recache() throws IOException, ClassNotFoundException {
            this.cacheHolder.recache();
        }
    };

}

