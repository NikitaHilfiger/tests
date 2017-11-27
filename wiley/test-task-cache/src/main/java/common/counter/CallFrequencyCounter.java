package common.counter;

import exception.DoesNotExistException;

import java.util.List;

public interface CallFrequencyCounter<K> {

    void register(K key);
    void call(K key) throws DoesNotExistException;
    int getFrequency(K key) throws DoesNotExistException;
    List<K> getMostFrequent(int amount);
    List<K> getMostFrequent();
    void reset();
    void removeElement(K key);
}
