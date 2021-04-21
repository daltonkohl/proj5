import java.util.*;
import java.io.*;

interface KWHashMap<K, V>{
	V get (K key);
	boolean isEmpty();
	V put (K key, V value);
}


public class Compressor{
	public class HashTableChain<K,V> implements KWHashMap<K,V>{
		public static class Entry<K,V>{
			private final K key;
			private V value;

			public Entry<K k, V v>{
				key = k;	
				value = v;
			}

			public K getKey(){
				return key;
			}

			public V getValue(){
				return value;
			}

			public V setValue(V v){
				V old = value;
				value = v;
				return old;
			}

			public String toString(){
				return key.toString() + " = " + value.toString();
			}

			private LinkedList<Entry<K,V>[] table;
			private int numKeys;
			private static final int CAPACITY = 0; //**NEEDS CHANGED**//
			private static final double LOAD_THRESHOLD = 0; //**NEEDS CHANGED**//

			public HashTableChain(){
				table = new LinkedList[CAPACITY];
				numKeys = 0;
			}

			public HashTableChain(int capacity){
				table = new LinkedList[capacity];
				numKeys = 0;
			}

			public V get(Object key){
				int index = key.hashCode() % table.length;
				if(index < 0){
					index += table.length;
				}
				if(table[index] == null){
					return null;
				}
				for(Entry<K,V> nextItem: table[index]){
					if(nextItem.getKey().equals(key){
						return nextItem.getValue();
					}
				}
				return null
			}
			
			public V put(K key, V value){
				int index = key.hashcode() % table.length;
				if(index < 0){
					index += table.length;
				}
				if(table[index] == null){
					table[index] = new LinkedList<Entry<K,V>>;
				}
				for(Entry<K,V> nextItem: table[index]){
					if(nextItem.getKey() == key){
						return nextItem.setValue(value);
					}	
				}
				table[index].set(new Entry<K key, V value>);
				numKeys++;
				if(load_factor > LOAD_THRESHOLD){
					//rehash
				}
				return null;
			}

			public K remove(K key){
				int index = key.hashcode() % table.length;
				if(index < 0){
					index += table.length;
				}
				if(table[index] == null){
					return null;
				}
				for(Entry<K, V> nextItem: table[index]){
					if(nextItem.getKey() == key){
						table[index].remove(key)
						numKeys--;
						if(table[index].size == 0){
							table[index = null;
						}
					}
				}
			}





