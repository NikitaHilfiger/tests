package caching.ram;

import caching.Cache;
import exception.DoesNotExistException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryCache<K, V> implements Cache<K, V> {

    private final Map<K, V> map = new HashMap<>();

    @Override
    public void cache(K key, V value) throws IOException, ClassNotFoundException {
        map.put(key, value);
    }

    @Override
    public V retrieve(K key) throws DoesNotExistException {
        return Optional.ofNullable(
                map.get(key)
        ).orElseThrow(
                () -> new DoesNotExistException(
                        String.format(
                                "Object with key = [%s] does not exit in memory cache",
                                key
                        )
                )
        );
    }

    @Override
    public void remove(K key) {
        map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }
}
