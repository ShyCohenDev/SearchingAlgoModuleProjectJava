package com.hit.algorithm;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class DamerauLevenshtien_contains_AlgoSearchImpl implements IAlgoSearch {
	
	Map<String, String> items;
	
	public DamerauLevenshtien_contains_AlgoSearchImpl(Map<String, String> items) {
		this.items = items;
	}
	
	@Override
	public Set<String> perfectSearch(String text) {
		Set<String> resultSet = new TreeSet<String>();
		for (Map.Entry<String, String> entry: this.items.entrySet()) {
			if (entry.getValue().equals(text))
				resultSet.add(entry.getKey());
		}
		return (resultSet.size() == 0)? null: resultSet;
	}
	
	@Override
	public Set<String> fuzzySearch(String text, int maxDistance) {
		Set<String> resultSet = new TreeSet<String>();
		for (Map.Entry<String, String> entry: this.items.entrySet()) {
			if (damerauLevenshteinDistance(entry.getValue(), text) <= maxDistance)
				resultSet.add(entry.getKey());
		}
		return (resultSet.size() == 0)? null: resultSet;
	}
	
	public int damerauLevenshteinDistance(String s1, String s2) {
	    int[][] distance = new int[s1.length() + 1][s2.length() + 1];

	    for (int i = 0; i <= s1.length(); i++) {
	        distance[i][0] = i;
	    }
	    for (int j = 0; j <= s2.length(); j++) {
	        distance[0][j] = j;
	    }

	    for (int i = 1; i <= s1.length(); i++) {
	        for (int j = 1; j <= s2.length(); j++) {
	            int cost = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
	            distance[i][j] = Math.min(
	                distance[i - 1][j] + 1,
	                Math.min(
	            		distance[i][j - 1] + 1,            
		                distance[i - 1][j - 1] + cost
		                )
	            );
	            if (i > 1 && j > 1 && s1.charAt(i - 1) == s2.charAt(j - 2) && s1.charAt(i - 2) == s2.charAt(j - 1)) {
	                distance[i][j] = Math.min(distance[i][j], distance[i - 2][j - 2] + cost);
	            }
	        }
	    }
	    return distance[s1.length()][s2.length()];
	}

	@Override
	public Set<String> containsSearch(String text) {
		Set<String> resultSet = new TreeSet<String>();
		for (Map.Entry<String, String> entry: this.items.entrySet()) {
			if (entry.getValue().contains(text))
				resultSet.add(entry.getKey());
		}
		return (resultSet.size() == 0)? null: resultSet;
	}
}
