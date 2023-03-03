package com.hit.algorithm;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Levenshtein_KMP_AlgoSearchImpl implements IAlgoSearch {
	
	Map<String, String> items;
	
	public Levenshtein_KMP_AlgoSearchImpl(Map<String, String> items) {
		this.items = items;
	}
	
	@Override
	public Set<String> perfectSearch(String text) {
		Set<String> resultSet = new TreeSet<String>();
		for (Map.Entry<String, String> entry: this.items.entrySet()) {
			if (compareStrings(entry.getValue(), text))
				resultSet.add(entry.getKey());
		}
		return (resultSet.size() == 0)? null: resultSet;
	}
	
	private boolean compareStrings(String a, String b) {
		if (a.length() != b.length()) 
			return false;
		for (int i = 0; i < a.length(); i++)
			if (a.charAt(i) != b.charAt(i))
				return false;
		return true;
	}

	@Override
	public Set<String> fuzzySearch(String text, int maxDistance) {
		Set<String> resultSet = new TreeSet<String>();
		for (Map.Entry<String, String> entry: this.items.entrySet()) {
			if (levenshteinDistance(entry.getValue(), text) <= maxDistance)
				resultSet.add(entry.getKey());
		}
		return (resultSet.size() == 0)? null: resultSet;
	}
	
	private static int levenshteinDistance(String s, String t) {
	    if (s.length() == 0) return t.length();
	    if (t.length() == 0) return s.length();

	    int[][] distances = new int[s.length() + 1][t.length() + 1];

	    for (int i = 0; i <= s.length(); i++) distances[i][0] = i;
	    for (int j = 0; j <= t.length(); j++) distances[0][j] = j;

	    for (int i = 1; i <= s.length(); i++) {
	        for (int j = 1; j <= t.length(); j++) {
	            int substitutionCost = (s.charAt(i - 1) == t.charAt(j - 1)) ? 0 : 1;

	            distances[i][j] = Math.min(Math.min(distances[i - 1][j] + 1, distances[i][j - 1] + 1), distances[i - 1][j - 1] + substitutionCost);
	        }
	    }
	    return distances[s.length()][t.length()];
	}

	@Override
	public Set<String> containsSearch(String text) {
		Set<String> resultSet = new TreeSet<String>();
		for (Map.Entry<String, String> entry: this.items.entrySet()) {
			if (kmpMatchFound(entry.getValue(), text))
				resultSet.add(entry.getKey());
		}
		return (resultSet.size() == 0)? null: resultSet;
	}
	
	private static boolean kmpMatchFound(String a, String b) {
		int n = a.length();
	    int m = b.length();
	    int[] lps = createLpsArray(b);

	    int i = 0;
	    int j = 0;
	    while (i < n) {
	        if (a.charAt(i) == b.charAt(j)) {
	            i++;
	            j++;
	        }
	        if (j == m) {
	            return true;
	        } else if (i < n && a.charAt(i) != b.charAt(j)) {
	            if (j != 0) {
	                j = lps[j - 1];
	            } else {
	                i++;
	            }
	        }
	    }
	    return false;
	}
	
	private static int[] createLpsArray(String b) {
	    int m = b.length();
	    int[] lps = new int[m];

	    int i = 1;
	    int j = 0;
	    while (i < m) {
	        if (b.charAt(i) == b.charAt(j)) {
	            lps[i] = j + 1;
	            i++;
	            j++;
	        } else {
	            if (j != 0) {
	                j = lps[j - 1];
	            } else {
	                lps[i] = 0;
	                i++;
	            }
	        }
	    }
	    return lps;
	}

}
