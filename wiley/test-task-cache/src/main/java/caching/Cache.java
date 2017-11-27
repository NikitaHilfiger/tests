package caching;

import exception.DoesNotExistException;

import java.io.IOException;

public interface Cache<K, V> {
    void cache(K key, V value) throws IOException, ClassNotFoundException;
    V retrieve(K key) throws DoesNotExistException;
    void remove(K key);
    void clear();
    int size();
}
