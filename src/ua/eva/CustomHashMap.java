package ua.eva;

import java.util.ArrayList;
import java.util.List;

public class CustomHashMap<K,V> implements MapInterface<K, V>{


    //Default length of the array, initial value is 16
    private static int defaultLength = 16;
    //Loading factor, 0.75 by default
    private static double defaultLoader = 0.75;
    //Entry array
    private ua.eva.Entry<K, V>[] table = null;
    //The size of this hashmap
    private int size = 0;




    public CustomHashMap(int length, double loader) {
        defaultLength = length;
        defaultLoader = loader;
        //initialize entry array
        table = new ua.eva.Entry[defaultLength];
    }


    public CustomHashMap() {
        this(defaultLength, defaultLoader);
    }



    private int getKey(K k) {
        int m = defaultLength;
        int index = k.hashCode() % m;
        return index >= 0 ? index : -index;
    }


    @Override
    public V put(K k, V v) {

        int index = getKey(k);
        ua.eva.Entry<K, V> entry = table[index];

        if (entry == null) {
            table[index] = new ua.eva.Entry(k, v, null);
            size++;
        } else {
            table[index] = new ua.eva.Entry<K, V>(k, v, entry);
        }
        return table[index].getValue();
    }


    private void expand() {

        ua.eva.Entry<K, V>[] newTable = new ua.eva.Entry[2 * defaultLength];

        rehash(newTable);
    }
    //the process of rehash
    private void rehash(ua.eva.Entry<K,V>[] newTable) {

        List<ua.eva.Entry<K, V>> list = new ArrayList<ua.eva.Entry<K, V>>();

        for(int i=0; i<table.length;i++) {

            if (table[i] == null) {
                continue;
            }

            findEntryByNext(table[i],list);
            if (list.size() > 0) {

                size = 0;

                defaultLength = 2 * defaultLength;

                table = newTable;
                for (ua.eva.Entry<K, V> entry : list) {
                    if (entry.next != null) {
                        //set next pointer of all entries to null
                        entry.next = null;
                    }

                    put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    private void findEntryByNext(ua.eva.Entry<K, V> entry,List<ua.eva.Entry<K, V>> list ) {
        if (entry != null && entry.next != null) {
            list.add(entry);
            //call recursive function
            findEntryByNext(entry.next,list);
        }else {
            list.add(entry);
        }
    }

    @Override
    public V get(K k) {

        int index = getKey(k);

        if (table[index] == null) {
            return null;
        }
        return findValueByEqualKey(k,table[index]);
    }

    public V findValueByEqualKey(K k , ua.eva.Entry<K,V> entry) {

        if (k == entry.getKey() || k.equals(entry.getKey())) {
            return entry.getValue();
        } else {

            if (entry.next != null) {
                return findValueByEqualKey(k, entry.next);
            }

        }
        return entry.getValue();
    }

    @Override

    public int size() {
        return size;
    }
}
