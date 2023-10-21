package exercises;
//Written by Su Win

import java.util.*;

//Perform Set operations on hash sets
public class Exercise21_1 {

	public static void main(String[] args) {
		
		//create two linked hash sets
		Set<String> linkedHashSet1 = new LinkedHashSet<>(Arrays.asList("George", "Jim", "John", "Blake", "Kevin", "Michael"));
		
		Set<String> linkedHashSet2 = new LinkedHashSet<>();
		linkedHashSet2.add("George");
		linkedHashSet2.add("Katie");
		linkedHashSet2.add("Kevin");
		linkedHashSet2.add("Michelle");
		linkedHashSet2.add("Ryan");
		
		System.out.println("Linked Hash Set 1: " + linkedHashSet1);
		System.out.println("Linked Hash Set 2: " + linkedHashSet2);
		
		//Union set 1 and set 2
		Set<String> unionSet = new LinkedHashSet<>(linkedHashSet1);
		unionSet.addAll(linkedHashSet2);
		System.out.println("\nUnion Set:" + unionSet);
		
		//Difference
		Set<String> newSet1 = new LinkedHashSet<>(linkedHashSet1);
		newSet1.removeAll(linkedHashSet2);
		System.out.println("\nRemoved all elements of set2 from set1. Set1 becomes:  " + newSet1);
		
		Set<String> newSet2 = new LinkedHashSet<>(linkedHashSet2);
		newSet2.removeAll(linkedHashSet1);
		System.out.println("Removed all elements of set1 from set2. Set2 becomes: " + newSet2);
		
		//Intersection between set 1 and set 2
		Set<String> intersectionSet = new LinkedHashSet<>(linkedHashSet1);
		intersectionSet.retainAll(linkedHashSet2);
		System.out.println("\nIntersection Set: " + intersectionSet);
	}

}
