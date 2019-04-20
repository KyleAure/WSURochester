package edu.winona.cs.paging;

public class Page {
	private int value;
	private int lastUsed;
	
	public Page() {
		this.value = -1;
		this.lastUsed = Integer.MAX_VALUE;
	}
	
	public Page(int value) {
		this.value = value;
		this.lastUsed = 0;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(int lastUsed) {
		this.lastUsed = lastUsed;
	}
	
	public void incrementLastUsed() {
		lastUsed++;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
