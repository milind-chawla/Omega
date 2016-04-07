package com.omega.test;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class TestGroup {
	
	public static void main(String[] args) {
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 9, 11, 20, 21, 22);
		
		List<List<Integer>> groups = groupThem(list);
		
		for(List<Integer> group: groups) {
			System.out.println(group);
		}
	}
	
	private static List<List<Integer>> groupThem(final List<Integer> list) {
		final List<Integer> inputList = Collections.unmodifiableList(list);
		final List<List<Integer>> result = Lists.newArrayList();
		
		int i = 0;
		while(i < inputList.size()) {
			i = pickUpNextGroup(i, inputList, result);
		}
		
		return result;
	}

	private static int pickUpNextGroup(int start, List<Integer> inputList, List<List<Integer>> result) {
		final List<Integer> group = Lists.newArrayList();
		
		int currElem = inputList.get(start);
		
		group.add(currElem);
		
		int next = start + 1;
		
		while(next < inputList.size()) {
			final int nextElem = inputList.get(next);
			
			if(nextElem - currElem == 1) {
				group.add(nextElem);
				currElem = nextElem;
			} else {
				break;
			}
			
			++next;
		}
		
		result.add(group);
		
		return next;
	}
}
