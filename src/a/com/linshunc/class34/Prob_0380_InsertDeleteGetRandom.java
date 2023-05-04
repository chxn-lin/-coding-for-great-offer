package a.com.linshunc.class34;

import java.util.HashMap;
import java.util.Map;

public class Prob_0380_InsertDeleteGetRandom {

    public class RandomizedSet {

        Map<Integer, Integer> indexMap;
        Map<Integer, Integer> resMap;
        int len;

        public RandomizedSet() {
            indexMap = new HashMap<>();
            resMap = new HashMap<>();
            len = 0;
        }

        public boolean insert(int val) {
            if (!indexMap.containsKey(val)) {
                indexMap.put(val, len);
                resMap.put(len, val);
                len++;
                return true;
            }
            return false;
        }

        public boolean remove(int val) {
            if (indexMap.containsKey(val)) {
                int deleteIndex = indexMap.get(val);
                int lastIndex = --len;
                int lastKey = resMap.get(lastIndex);
                indexMap.put(lastKey, deleteIndex);
                resMap.put(deleteIndex, lastKey);
                indexMap.remove(val);
                resMap.remove(lastIndex);
                return true;
            }
            return false;
        }

        public int getRandom() {
            return resMap.get(Math.random() * len);
        }
    }
}
