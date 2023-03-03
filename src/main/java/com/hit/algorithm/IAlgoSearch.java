package com.hit.algorithm;

import java.util.Set;

public interface IAlgoSearch {
	
	public Set<String> perfectSearch(String text);
	
	// This algorithm enables the user to spell the search request with few typos
	public Set<String> fuzzySearch(String text, int maxDistance);

	// doh -> dog, 2
	// dgo -> dog, 1

	// The Levenshtein distance allows deletion, insertion and substitution.
	// The Damerau–Levenshtein distance allows insertion, deletion, substitution, and the transposition of two adjacent characters.


	public Set<String> containsSearch(String text);
}
