package com.philomath.huffmancoding;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {

	static Map<Character,String> map = new HashMap<>();
	
	public static void main(String[] args) {
		int n = 6;
		char[] charArray = {'a','b','c','d','e','f'};
		int[] charFrequency = {5,9,12,13,16,45};
		// create a min-heap
		PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(n,new MinComparator());
		for (int i = 0; i < n; i++) {
			HuffmanNode hn = new HuffmanNode();
			hn.c = charArray[i];
			hn.data= charFrequency[i];
			hn.left = null;
			hn.right = null;
			queue.add(hn);
		}
		
		// create a root node
		HuffmanNode root = null;
		while(queue.size() > 1) {
			HuffmanNode x = queue.peek();
			queue.poll();
			HuffmanNode y = queue.peek();
			queue.poll();
			HuffmanNode f = new HuffmanNode();
			f.data = x.data+y.data;
			f.c = '-';
			f.left = x;
			f.right = y;
			root = f;
			queue.add(f);
			
			
		}
		printCode(root,"");
		String msgToBeEncoed = "abcccddeeeeeff";
		System.out.println("Message to be encoded is : "+msgToBeEncoed);
		String encodedMsg = encodeMessage(msgToBeEncoed);
		System.out.println("Encoded Message is : "+encodedMsg);
		StringBuilder result = new StringBuilder();
		decodeMessage(encodedMsg,root,result);
		System.out.println("Decoded Message is : "+result.toString());
	}

	private static void decodeMessage(String encodedMsg,HuffmanNode root,StringBuilder result) {
		HuffmanNode current = root;
		for (int i = 0; i < encodedMsg.length(); i++) {		
			if(encodedMsg.charAt(i) == '0') {
				current = current.left;
			}
			else {
				current = current.right;
			}
			if(current != null && Character.isLetter(current.c)) {
				//System.out.println(current.c);
				result.append(current.c);
				current = root;
			}
		}
	}

	private static String encodeMessage(String msg) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < msg.length(); i++) {
			builder.append(map.get(msg.charAt(i)));
		}
		return builder.toString();
	}
	

	private static void printCode(HuffmanNode root, String s) {
		if(root.left == null && root.right == null & Character.isLetter(root.c)) {
			System.out.println(root.c+" : "+s);
			map.put(root.c,s);
			return;
		}
		
		printCode(root.left, s+"0");
		printCode(root.right, s+"1");
	}
	
	

	
	
}

class HuffmanNode{
	int data;
	char c;
	HuffmanNode left;
	HuffmanNode right;
}

class MinComparator implements Comparator<HuffmanNode>{

	@Override
	public int compare(HuffmanNode first, HuffmanNode second) {
		return first.data-second.data;
	}
	
}
