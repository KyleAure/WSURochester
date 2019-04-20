package edu.winona.cs.paging;

import java.util.HashMap;

public class Mainstore {
	private HashMap<Integer, Page> map = new HashMap<>();
	private int counter;
		
	public Mainstore(int frames) {
		counter = 0;
		for(int i=0; i < frames; i++) {
			map.put(i, new Page());
		}
	}
	
	public boolean get(int page) {
		boolean result = false;
		
		for(Integer index : map.keySet()) {
			Page temp = map.get(index);
			if(temp.getValue() == page) {
				temp.setLastUsed(-1);
				result = true;
			}
			
			if(temp.getValue() != -1) {
				temp.incrementLastUsed();
			}
			map.put(index, temp);
		}
		
		//If we get here that means that the page was not in the map
		if(!result) {
			replace(page, App.getAlgorithm());
		}
		
		return result;
	}
	
	private void replace(int value, Replace algo) {
		switch (algo) {
		case LRU:
			int highestCount = Integer.MIN_VALUE;
			int highestIndex = Integer.MIN_VALUE;
			for(Integer index : map.keySet()) {
				if(map.get(index).getLastUsed() > highestCount) {
					highestCount = map.get(index).getLastUsed();
					highestIndex = index;
				}
			}
			map.put(highestIndex, new Page(value));
			break;
		case FIFO:
			map.put(counter, new Page(value));
			counter = (counter + 1) % map.size(); 
			break;
		}
	}

	@Override
	public String toString() {
		String result = "Frame\t Page\t lastUsed\n";
		for(Integer index : map.keySet()) {
			String value = map.get(index).getValue() == -1 ? "EMPTY" : map.get(index).toString();
			result += index + "\t" + value + "\t" + map.get(index).getLastUsed() + "\n";
		}
		return result;
	}	
}
