package a.com.linshunc.class02;

import java.util.HashMap;
import java.util.Map;

public class SetAll {

    private static class MyHash<K, V> {

        int allTime = -1;
        V allValue = null;
        int times = 0;
        Map<K, ValueObject<V>> map = new HashMap();

        private static class ValueObject<V>{
            int time;
            V value;
        }

        public void put(K key, V value){
            ValueObject<V> obj = new ValueObject<>();
            obj.time = this.times++;
            obj.value = value;
            map.put(key, obj);
        }

        public V get(K key){
            V res = null;
            ValueObject<V> obj = map.get(key);
            if (obj != null) {
                if (obj.time < this.allTime) {
                    res = allValue;
                } else {
                    res = obj.value;
                }
            }
            return res;
        }

        public void setAll(V value){
            this.allTime = this.times++;
            allValue = value;
        }
    }

    public static void main(String[] args) {
        MyHash<Integer, String> map = new MyHash();
        System.out.println(map.get(1));
        map.put(1, "a");
        map.put(2, "b");
        System.out.println(map.get(1));
        map.setAll("c");
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        map.put(4, "d");
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(4));
    }

}
