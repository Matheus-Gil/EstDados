package hashing;

import java.util.LinkedList;

public class TestHashTable {

	public static void main(String[] args) {
		HashTable<Integer, String> testHash = new HashTable<Integer, String>(10);
		testHash.put(1, "Bruno");
		testHash.put(2, "Bruno");
		testHash.put(1, "Lucas");
		testHash.put(3, "José");
		testHash.put(4, "Vanessa");
		testHash.put(5, "Alice");
		testHash.put(6, "Fernando");
		testHash.put(6, "Fernando");
		
		testHash.print();
		
		LinkedList<String> returnedValues = testHash.get(1);
		for (int i = 0; i < returnedValues.size(); i++)
			System.out.println(returnedValues.get(i));

	}
}
