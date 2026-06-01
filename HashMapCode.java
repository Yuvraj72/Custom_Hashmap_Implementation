package Hashing.Hashmap;

import java.util.*;

public class HashMapCode {
    static class HashMap<K, V> {
        private class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int N = 4; //number of buckets
        private int n; //number of pairs

          // Array of LinkedLists for collision handling 
        private LinkedList<Node> buckets[];

        @SuppressWarnings("unchecked")
        public HashMap() {
            this.N = 4;
            this.n = 0;
            this.buckets = new LinkedList[N];
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new LinkedList<>();
            }
        }

        // The actuall working of hashmap - Rehashing when load factor becomes large - to maintain time complexity of 0(1)
        private void reHash() {
            LinkedList<Node> oldBucket[] = buckets;

            // Create new bucket array with double capacity
            // @SuppressWarnings("unchecked")
            buckets = new LinkedList[2 * N];
            N *= 2;

            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new LinkedList<>();
            }
            n = 0;

             // Reinsert old nodes into new buckets
            for (int i = 0; i < oldBucket.length; i++) {
                LinkedList<Node> ll = oldBucket[i];
                while (!ll.isEmpty()) {
                    Node node = ll.remove();
                    put(node.key, node.value);
                }
            }
        }

        //converts the hascode to a index
        private int hashFunction(K key) {
            int hc = key.hashCode();

            return Math.abs(hc) % N;
        }

        private int SearchinLL(K key, int bucketIdx) {
            LinkedList<Node> ll = buckets[bucketIdx];

            for (int i = 0; i < ll.size(); i++) {
                Node node = ll.get(i);
                if (node.key.equals(key)) {
                    return i;
                }
            }

            return -1;
        }

        // Insert or update key-value pair
        public void put(K key, V value) {
            int bIdx = hashFunction(key);
            int dataIdx = SearchinLL(key, bIdx);

            // if it exists then just update the value
            if (dataIdx != -1) {
                Node node = buckets[bIdx].get(dataIdx);
                node.value = value;
                return;
            }

            // if doesnt update add the new pair to the bucketIdx
            buckets[bIdx].add(new Node(key, value));
            n++;

            double lambda = (double) n / N;

            if (lambda > 2.0) {
                reHash();
            }

        }

        public boolean containsKey(K key) {
            int bIdx = hashFunction(key);

            LinkedList<Node> list = buckets[bIdx];

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).key.equals(key)) {
                    return true;
                }
            }
            return false;
        }

          // Remove key-value pair
        public V remove(K key) {
            int bIdx = hashFunction(key);

            LinkedList<Node> list = buckets[bIdx];

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).key.equals(key)) {
                    // V val = list.get(i).value;
                    n--;
                    return list.remove(i).value;
                }
            }
            return null;
        }

        public V get(K key) {
            int bIdx = hashFunction(key);

            LinkedList<Node> list = buckets[bIdx];

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).key.equals(key)) {
                    // V val = list.get(i).value;
                    return list.get(i).value;
                }
            }
            return null;
        }

        // Return all keys
        public ArrayList<K> keySet() {

            ArrayList<K> keys = new ArrayList<>();

            // traverse all buckets
            for (int i = 0; i < buckets.length; i++) {
                LinkedList<Node> list = buckets[i];

                // traverse linked list inside bucket
                for (int j = 0; j < list.size(); j++) {
                    Node node = list.get(j);
                    keys.add(node.key);
                }
            }

            return keys;
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("a", 10);
        System.out.println(map.get("a"));
        map.put("a", 12);
        map.put("b", 12);
        map.put("c", 12);
        System.out.println(map.get("a"));
        System.out.println(map.containsKey("s"));
        System.out.println(map.containsKey("a"));

        System.out.println(map.keySet());

    }

}
