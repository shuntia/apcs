import java.util.ArrayList;
import java.util.Iterator;

public class LinkedListOfStrings implements Iterable<Node> {

	Node head;

	// What is our underlying data structure...not a Node[]! A linked list!
	// Well, what does a LinkedList need?

	// iteratively traverse the Linked List, printing out the String + " --> "
	// unless it's the last node, in which case print out the String + " --> null"
	public String toString() {
		return "";
	}

	// iteratively traverse the Linked List, counting out the number of Nodes
	// whose String contains str
	public int countNodesWithString(String str) {
		return 0;
	}

	// recursively traverse the Linked List, counting out the number of Nodes
	// whose String contains str
	public int recursivelyCountNodesWithString(Node head, String str) {
		return head == null ? 0
				: ((head.name.equals(str) ? 1 : 0) + recursivelyCountNodesWithString(head.next, str));
	}

	// Prints the nodes in reverse, iteratively
	public void printReversed(Node head) {
		ArrayList<Node> stack = new ArrayList<>();
		Node current = head;
		while (current.next != null) {
			stack.add(current);
			current = current.next;
		}
		System.out.print("null");
		for (Node node : stack) {
			System.out.print(" <-- " + node);
		}
		System.out.println();
	}

	// Prints the nodes in reverse, recursively
	public void recursivelyPrintReversed(Node head) {
		System.out.print(head + " <-- ");
		if (head != null)
			recursivelyPrintReversed(head.next);
	}

	// ********* Some of the LinkedList operations specified by Interface List
	// ********
	// (not all of them, that's why we aren't implementing the actual interface)

	// Appends the specified element to the end of this list.
	// Returns true if this collection changed as a result of the call.
	// (Returns false if this collection does not permit duplicates and already
	// contains the specified element.)
	public boolean add(String s) {
		Node n = new Node(s);
		Node last = null;
		for (Node i : this) {
			last = i;
		}
		last.next = n;
	}

	// Inserts the specified element at the specified position in this list.
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 ||
	// index > size())
	public void add(int index, String s) {
		Node n = new Node(s);
	}

	// Removes all of the elements from this list.
	public void clear() {

	}

	// Returns true if this list contains the specified element.
	public boolean contains(String s) {

		return false;
	}

	// Returns the data at the specified position in this list
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 ||
	// index >= size())
	public String get(int index) {
		return null;
	}

	// Removes the Node at the specified position in this list.
	// Returns the data previously at the specified position
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 ||
	// index >= size())
	public String remove(int index) {
		return null;
	}

	// Removes the first occurrence of the specified element from this list, if it
	// is present.
	// Returns true if this list contained the specified element (or equivalently,
	// if this list changed as a result of the call).
	public boolean remove(String s) {

		return false;
	}

	// Replaces the data at the specified position in this list with the specified
	// element.
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 ||
	// index >= size())
	// Returns the data that was previously at the specified position
	public String set(int index, String s) {
		return null;
	}

	// Returns the number of elements in this collection.
	public int size() {
		return 0;
	}

	@Override
	public Iterator<Node> iterator() {
		return new Iterator<Node>() {
			Node current = head;

			@Override
			public Node next() {
				current = current.next;
				return current;
			}

			@Override
			public boolean hasNext() {
				return current.next != null;
			}
		};
	}
}
