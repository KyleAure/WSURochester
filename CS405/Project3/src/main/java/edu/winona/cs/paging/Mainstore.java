package edu.winona.cs.paging;

import java.util.HashMap;

public class Mainstore {
	private HashMap<Integer, Integer> map = new HashMap<>();
	private int counter;
		
	public Mainstore(int frames) {
		counter = 0;
		for(int i=0; i < frames; i++) {
			map.put(i, -1);
		}
	}
	
	public boolean get(int page) {
		for(Integer index : map.keySet()) {
			if(map.get(index) == page) {
				return true;
			}
		}
		
		//If we get here that means that the page was not in the map
		replace(page, App.getAlgorithm());
		return false;
	}
	
	private void replace(int page, Replace algo) {
		switch (algo) {
		case LRU:
			break;
		case FIFO:
			map.put(counter, page);
			counter = (counter + 1) % map.size(); 
			break;
		}
	}

	@Override
	public String toString() {
		String result = "Frame\t Page\n";
		for(Integer index : map.keySet()) {
			String value = map.get(index) == -1 ? "EMPTY" : map.get(index).toString();
			result += index + "\t" + value + "\n";
		}
		return result;
	}	
}
