import java.util.LinkedList;

class HashMap<K, V> {
    static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] buckets;
    private int capacity;
    private int size;
    private static final double LOAD_FACTOR = 0.75;

    public HashMap() {
        this.capacity = 16; // initial capacity
        this.size = 0;
        this.buckets = (LinkedList<Entry<K, V>>[]) new LinkedList<?>[capacity];
    }

    public int hash(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode()) % capacity;
    }

    public V get(K key) {
        int index = hash(key);
        if (buckets[index] == null)
            return null;
        else {
            for (Entry<K, V> buck : buckets[index]) {
                if (buck.key.equals(key))
                    return buck.value;
            }
        }
        return null;

    }

    public void put(K key, V val) {
        int index = hash(key);
        if (buckets[index] == null)
            buckets[index] = new LinkedList<>();
        for (Entry<K, V> buck : buckets[index]) {
            if (buck.key.equals(key)) {
                buck.value = val;
                return;
            }
        }
        buckets[index].add(new Entry<>(key, val));
        size++;
        if ((double) size / capacity >= LOAD_FACTOR) {
            rehash();
        }
    }

    public void remove(K key) {
        int index = hash(key);
        if (buckets[index] == null) {
            return;
        }
        buckets[index].removeIf(entry -> entry.key.equals(key));
        size--;
    }

    private void rehash() {
        capacity *= 2;
        LinkedList<Entry<K, V>>[] newBuckets = (LinkedList<Entry<K, V>>[]) new LinkedList<?>[capacity];
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    int index = hash(entry.key);
                    if (newBuckets[index] == null) {
                        newBuckets[index] = new LinkedList<>();
                    }
                    newBuckets[index].add(entry);
                }
            }
        }
        buckets = newBuckets;
    }
}
