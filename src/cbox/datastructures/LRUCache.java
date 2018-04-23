package cbox.datastructures;

import java.util.LinkedHashMap;
import java.util.Map;

// Least Recently Used Cache.
// Contains a fixed set of values, removes the oldest first is size is exceeded.
public class LRUCache<T, U> extends LinkedHashMap<T, U> {
    private int size;

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > size;
    }

    public LRUCache(int size) {
        super(size, 0.75f, true);
        this.size = size;
    }
}
