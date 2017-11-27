package caching.disk;

import caching.Cache;
import caching.disk.strategy.CustomSerializer;
import exception.DoesNotExistException;

import java.io.File;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.UUID;


public class FileSerializationCache<K extends Serializable, V extends Serializable> implements Cache<K, V> {

    private String DIR = "cache/";

    private int elementsAdded;

    private CustomSerializer<K, V> customSerializer;

    public FileSerializationCache() {
    }

    public FileSerializationCache(CustomSerializer<K, V> customSerializer) {
        this.customSerializer = customSerializer;
    }

    private File lookThroughDir(File dir, K key) {
        File[] files = dir.listFiles();
        for (File f : files) {
            Entry<K, V> entry = customSerializer.deserialize(f);

            if (entry.getKey().equals(key)) {
                return f;
            }
        }

        return null;
    }

    @Override
    public void cache(K key, V value) {
        String hash = String.valueOf(key.hashCode());

        String dirName = getDIR() + hash + "/";
        File dir = new File(dirName);

        File toUse = null;

        if (!dir.exists()) {

            dir.mkdirs();
            toUse = new File(dirName + randomString());
            elementsAdded++;

        } else {

            toUse = lookThroughDir(dir, key);

            if (toUse == null) {
                toUse = new File(dirName + randomString());
                elementsAdded++;
            }

        }

        customSerializer.serialize(key, value, toUse);
    }

    private String randomString() {
        return UUID.randomUUID().toString();
    }

    @Override
    public V retrieve(K key) throws DoesNotExistException {
        String hash = String.valueOf(key.hashCode());

        String dirName = getDIR() + hash + "/";
        File dir = new File(dirName);

        if (!dir.exists()) {
            throw new DoesNotExistException(
                    String.format(
                            "Object with key = [%s] does not exist in disk cache",
                            key
                    )
            );
        }

        File toUse = lookThroughDir(dir, key);

        if (toUse == null) {
            throw new DoesNotExistException(
                    String.format(
                            "Object with key = [%s] does not exist in disk cache",
                            key
                    )
            );
        }

        V result = customSerializer.deserialize(toUse).getValue();

        return result;
    }

    @Override
    public void remove(K key) {
        String hash = String.valueOf(key.hashCode());

        String dirName = getDIR() + hash + "/";
        File dir = new File(dirName);

        if (dir.exists()) {
            File toUse = lookThroughDir(dir, key);

            if (toUse != null) {
                toUse.delete();
                elementsAdded--;
            }
        }
    }

    public void setCustomSerializer(CustomSerializer<K, V> customSerializer) {
        this.customSerializer = customSerializer;
    }

    public CustomSerializer<K, V> getCustomSerializer() {
        return customSerializer;
    }

    public void setDIR(String dIR) {
        DIR = dIR;
    }

    public String getDIR() {
        return DIR;
    }

    @Override
    public void clear() {
        recurseDelete(new File(getDIR()));
    }

    private void recurseDelete(File file) {
        for (File inner : file.listFiles()) {
            if (inner.isDirectory()) {
                recurseDelete(inner);
            } else {
                boolean deleted = inner.delete();
                assert deleted;
            }
        }
    }

    @Override
    public int size() {
        return elementsAdded;
    }

}