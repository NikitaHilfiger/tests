package caching.disk.strategy;

import java.io.File;
import java.io.Serializable;
import java.util.Map;

public interface CustomSerializer<K extends Serializable, V extends Serializable> {

    Map.Entry<K, V> deserialize(File file);

    void serialize(K key, V value, File file);

}