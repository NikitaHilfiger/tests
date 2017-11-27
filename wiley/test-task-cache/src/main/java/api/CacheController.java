package api;

import caching.Cache;
import exception.DoesNotExistException;

import java.io.IOException;
import java.util.List;

public class CacheController<K, V> implements Cache<K, V> {

    private List<Cache<K, V>> caches;

    @Override
    public void cache(K key, V value) throws IOException, ClassNotFoundException {
        // caching to the first cache
        caches.get(0).cache(key, value);
    }

    @Override
    public V retrieve(K key) throws DoesNotExistException {

        V value = null;

        for (Cache<K, V> cache : caches) {
            value = cache.retrieve(key);
            return value;
        }

        throw new DoesNotExistException(
                String.format(
                        "Object with key = [%s] does not exist caches",
                        key
                )
        );
    }

    public void setCaches(List<Cache<K, V>> caches) {
        this.caches = caches;
    }

    public List<Cache<K, V>> getCaches() {
        return caches;
    }

    @Override
    public void clear() {
        caches.forEach(
                Cache::clear
        );
    }

    @Override
    public void remove(K key) {
        caches.forEach(
                cache -> cache.remove(key)
        );
    }

    @Override
    public int size() {
        int size = 0;

        for (Cache<K, V> cache : caches) {
            size = size + cache.size();
        }

        return size;
    }

}
