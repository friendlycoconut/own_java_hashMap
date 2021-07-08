package ua.eva;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CustomHashMap<Long,String > map = new CustomHashMap();

        Long t1 = System.currentTimeMillis();
        for (long i=0; i<1000;i++) {
            map.put( i, "value" + i);
        }

        for (long i = 0; i < 1000; i++) {
            System.out.println("key: " + "key" + i +"---"+ "value: " + map.get( i));
        }
        Long t2 = System.currentTimeMillis();

        System.out.println("time consumed by MyHashMap："+(t2-t1));
        System.out.println("-------------------HashMap-------------------------" );

        Map<Long,String > hashMap = new HashMap<>();

        Long t3 = System.currentTimeMillis();
        for (long i=0; i<1000;i++) {
            hashMap.put(i, "value" + i);
        }

        for (long i = 0; i < 1000; i++) {
            System.out.println("key: " + "key" + i + "---"+ "value: " + hashMap.get(i));
        }
        Long t4 = System.currentTimeMillis();
        System.out.println("time consumed by HashMap in JDK: "+(t4-t3));

    }
}
