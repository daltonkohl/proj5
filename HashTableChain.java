import java.util.*;
import java.io.*;

interface KWHashMap<K, V>{
V get (K key);
V put (K key, V value);
}


public class HashTableChain<K,V> implements KWHashMap<K,V>{
	public class Entry<K,V>{
		private final K key;
		private V value;

		public Entry(K k, V v){
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
	}

		private LinkedList<Entry<K,V>>[] table;
		int numKeys;
		private static final int CAPACITY = 101; //**NEEDS CHANGED**//
		private static final double LOAD_THRESHOLD = .7; //**NEEDS CHANGED**//

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
				if(nextItem.getKey().equals(key)){
					return nextItem.getValue();
				}
			}
			return null;
		}
		
		public V put(K key, V value){
			int index = key.hashCode() % table.length;
			if(index < 0){
				index += table.length;
			}
			if(table[index] == null){
				table[index] = new LinkedList<Entry<K,V>>();
			}
			for(Entry<K,V> nextItem: table[index]){
				if(nextItem.getKey().equals(key)){ 
					return nextItem.setValue(value);
				}	
			}
			table[index].push(new Entry<K, V>(key, value)); //i think this needs to be push() -- changed from set
			numKeys++;
			//System.out.println("before rehash");
			if((numKeys / table.length) > LOAD_THRESHOLD){
				//rehash -- probably isn't correct??
				int capacity = table.length;
				System.out.println("rehashed");
				capacity = getNextPrime.getNextPrime(capacity);
				LinkedList<Entry<K, V>>[] temp = table;
				table  = new LinkedList[capacity];
				for(LinkedList<Entry<K,V>> list : temp){
					if(list != null){
					for(Entry<K,V> entry : list){
						//System.out.println(entry.getValue());
						//System.out.println("index: "+index);
						int newIndex = entry.getKey().hashCode() % table.length;
						if(table[newIndex] == null){
							table[newIndex] = new LinkedList<Entry<K,V>>();
						}
						table[newIndex].push(entry);
						//new Entry<K, V>(entry.getKey(), entry.getValue()));
						//System.out.println("DONE");
						}
					}
				}

				
			}
			return null;
		}


		public K remove(K key){
			int index = key.hashCode() % table.length;
			if(index < 0){
				index += table.length;
			}
			if(table[index] == null){
				return null;
			}
			for(Entry<K, V> nextItem: table[index]){
				if(nextItem.getKey().equals(key)){
					table[index].remove(key);
					numKeys--;
					if(table[index].size() == 0){
						table[index] = null;
					}
				}
			}
		return null;
		}

}





