package com.hashtabledemo;

import java.util.Scanner;

public class HashTableDS {

	int maxsize = 16;
	Node[] hashtable = new Node[maxsize];

	public static void main(String[] args) {
		HashTableDS hash = new HashTableDS();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Choose the following: 1) Add 2) Remove 3) Display 4)search 5) Exit");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter a number to add:");
				int numberToAdd = scanner.nextInt();
				hash.add(numberToAdd);
				break;
			case 2:
				hash.removeAfterLocation();
				break;
			case 3:
				hash.display();
				break;
			case 4:
				System.out.println("Enter a number to search:");
				int numberToSearch = scanner.nextInt();
				hash.search(numberToSearch);
				break;
			case 5:
				System.out.println("Exit:");
				scanner.close();
				return;
			default:
				System.out.println("Invalid choice, please try again.");
			}
		}
	}

	void display() {
		System.out.println("Display Elements:");
		for (int i = 0; i < maxsize; i++) {
			Node bucketIndex = hashtable[i];
			printList(bucketIndex);
		}
	}

	private void printList(Node bucketIndex) {
		Node traveler = bucketIndex;
		while (traveler != null) {
			System.out.print(traveler.data + " ");
			traveler = traveler.next;
		}
		if (bucketIndex != null) {
			System.out.println(); // to move to a new line after printing all elements in the bucket

		}
	}

	void add(int data) {
		int index = data % 16;
		Node newNode = new Node(data);

		// Check if the index is empty
		if (hashtable[index] == null) {
			hashtable[index] = newNode;
		} else {
			Node temp = hashtable[index];
			while (temp.next != null) {
				if (temp.data == newNode.data) {
					System.out.println("Element is already present: " + data);
					return;
				}
				temp = temp.next;
			}
			/*
			 * if (temp.data == newNode.data) {
			 * System.out.println("Element is already present: " + data); return; }
			 */
			temp.next = newNode;
		}
		System.out.println("Added: " + data + " at index: " + index);
	}

	void removeAfterLocation() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the index of the bucket (0 to " + (maxsize - 1) + "):");
		int index = scan.nextInt();
		System.out.println("Enter the position of the node after which you want to perform deletion (0-based):");
		int location = scan.nextInt();

		if (index < 0 || index >= maxsize) {
			System.out.println("Index out of bounds: " + index);
			return;
		}

		Node traveler = hashtable[index];
		for (int i = 0; i < location; i++) {
			if (traveler == null || traveler.next == null) {
				System.out.println("Can't delete: Location is out of bounds.");
				return;
			}
			traveler = traveler.next;
		}

		if (traveler == null || traveler.next == null) {
			System.out.println("No node to delete after location " + location + " at index " + index);
			return;
		}

		traveler.next = traveler.next.next;
		System.out.println("Node deletion after location " + location + " at index " + index);
	}

	boolean search(int data) {
		int index = data % maxsize; // calculate the index using the hash function
		Node traveler = hashtable[index]; // get the bucket

		if (traveler == null) {
			System.out.println("Bucket is empty. Item not found.");
			return false; // bucket is empty, item not found
		}

		int position = 0; // to track the position of the node in the bucket
		boolean found = false;
		while (traveler != null) {
			if (traveler.data == data) {
				System.out.println("Item found at bucket index " + index + ", position " + (position + 1));
				found = true;
				break;
			}
			traveler = traveler.next; // move to the next node
			position++;
		}

		if (!found) {
			System.out.println("Item not found in the hash table.");
		}
		return found;
	}
}

class Node {
	int data;
	Node next;

	public Node(int data) {
		this.data = data;
		this.next = null;
	}
}
