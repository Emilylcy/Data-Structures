import java.util.ArrayList;

public class DoubleHashMap<K, V> extends AbstractHashMap<K, V> {
    private MapEntry<K, V>[] table;        
    private MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null); 

    private int totalProbes = 0;
    private int maxProbes = 0;
    private final int primeQ = 1000001; 

    public DoubleHashMap() { super(); }
    public DoubleHashMap(int cap) { super(cap); }
    public DoubleHashMap(int cap, int p) { super(cap, p); }

    @Override
    protected void createTable() {
        table = (MapEntry<K, V>[]) new MapEntry[capacity]; 
    }

    private boolean isAvailable(int j) {
        return table[j] == null || table[j] == DEFUNCT;
    }

    protected int f(int i, K k) {
        int hash2 = primeQ - (k.hashCode() % primeQ);
        return i * hash2;
    }
    
    private int findSlot(int h, K key) {
        int j = h;
        int probeCount = 0;
    
        while (true) { 
            if (isAvailable(j)) {
                if (table[j] == null) {
                    totalProbes += probeCount;
                    maxProbes = Math.max(maxProbes, probeCount);
                    return j; 
                }
            } else if (table[j].getKey().equals(key)) {
                return j; 
            }
            probeCount++;
            j = (h + f(probeCount, key)) % capacity; 
    
            if (probeCount >= capacity) { 
                break;
            }
        }
    
        totalProbes += probeCount;
        maxProbes = Math.max(maxProbes, probeCount);
        return -(j + 1); 
    }

    @Override
    protected V bucketGet(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0) return null;                 
        return table[j].getValue();
      }
    
    @Override
    protected V bucketPut(int h, K k, V v) {
        int j = findSlot(h, k);
        if (j < 0) j = -j - 1; 
        if (table[j] != null) {
            return table[j].setValue(v);
        }
        table[j] = new MapEntry<>(k, v);
        n++;
        if (n > capacity / 2) {
            resize(2 * capacity - 1); 
        }
        return null;
    }

    private void resize(int newCap) {
        ArrayList<MapEntry<K, V>> buffer = new ArrayList<>();
        for (MapEntry<K, V> e : table) {
            if (e != null && e != DEFUNCT) {
                buffer.add(e);
            }
        }
        capacity = newCap;
        createTable();
        n = 0;
        for (MapEntry<K, V> e : buffer) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    protected V bucketRemove(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0) return null;
        V answer = table[j].getValue();
        table[j] = DEFUNCT;
        n--;
        return answer;
    }

    public double getAvgProbes() {
        return totalProbes / (double) n;
    }

    public int getMaxProbes() {
        return maxProbes;
    }

    public double getLoadFactor() {
        return n / (double) capacity;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (MapEntry<K, V> entry : table) {
            if (entry != null && entry != DEFUNCT) {
                buffer.add(entry);
            }
        }
        return buffer;
    }
}
