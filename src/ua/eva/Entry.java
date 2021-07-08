package ua.eva;

public class Entry<K, V> implements MapInterface.Entry<K, V> {

    K k;

    V v;

    Entry<K,V> next;

    public Entry(K k, V v, Entry next) {
        this.k = k;
        this.v = v;
        this.next = next;
    }


    @Override
    public K getKey() {
        return null;
    }

    @Override
    public V getValue() {
        return null;
    }
}
