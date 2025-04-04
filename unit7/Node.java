public class LinkedListOfStrings {
	// What is our underlying data structure...not a Node[]!  A linked list!
	// Well, what does a LinkedList need? 
	private Node head;

	// iteratively traverse the Linked List, printing out the String + " --> "
	// unless it's the last node, in which case print out the String + " --> null"
	public String toString() {
		Node current = head;
		while(current != null){
			System.out.print(current.getData() + " --> ");
			current = current.getNext();
		}
		System.out.println("null");
	}
	
	// iteratively traverse the Linked List, counting out the number of Nodes
	// whose String contains str
	public int countNodesWithString(String str) {
		int count = 0;
		Node current = head;
		while(current != null){
			if(current.getData().equals(str)){
				count++;
			}
			current = current.getNext();
		}
		return count;
	}
	
	// recursively traverse the Linked List, counting out the number of Nodes
	// whose String contains str
	public int recursivelyCountNodesWithString(Node head, String str) {
		return (head.getData().equals(str) ? 1 : 0 ) + recursivelyCountNodesWithString(head.getNext(), str);
		
	}
	
	// Prints the nodes in reverse, iteratively
	public void printReversed(Node head) {
		for(int i = 0; i < size(); i++){
			System.out.println(get(size()-i).getData());
		}

	}
	
	// Prints the nodes in reverse, recursively
	public void recursivelyPrintReversed(Node head) {
		if(head == null){
			return;
		}
		recursivelyPrintReversed(head.getNext());
		System.out.println(head.getData());
	}
	
	
	
	//********* Some of the LinkedList operations specified by Interface List ********
	// (not all of them, that's why we aren't implementing the actual interface)
	
	
	// Appends the specified element to the end of this list.
	// Returns true if this collection changed as a result of the call. 
	// (Returns false if this collection does not permit duplicates and already contains the specified element.) 
	public boolean add(String s) {
		Node n = new Node(s);
		if(head == null){
			head = n;
			return true;
		}
		Node current = head;
		while(current.getNext() != null){
			current = current.getNext();
		}
		current.setNext(n);
		return true;
	}
	
	// Inserts the specified element at the specified position in this list.
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
	public void add(int index, String s) {
		Node n = new Node(s);
		Node current=head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
			if(current == null){
				throw new IndexOutOfBoundsException();
			}
		}
		n.setNext(current.getNext());
		current.setNext(n);
	}
	
	// Removes all of the elements from this list.
	public void clear() {
		head=null;
	}
	
	// Returns true if this list contains the specified element.
	public boolean contains(String s) {
		for (int i = 0; i < size(); i++) {
			if(get(i).getData().equals(s)){
				return true;
			}
		}
		return false;
	}
	
	// Returns the element at the specified position in this list
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node get(int index) {
		Node current=head;
		for (int i = 0; i < index; i++) {
			if(i == index){
				return current;
			}
			current = current.getNext();
			if(current == null){
				return null;
			}
		}
		return null;


	}
	
	// Removes the element at the specified position in this list.
	// Returns the element previously at the specified position
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node remove(int index) {
		Node current=head;
		for (int i = 0; i < index; i++) {
			if(i == index){
				get(i-1).setNext(get(i+1));
				return true;
			}
			current = current.getNext();
			if(current == null){
				return false;
			}
		}
		return false;

	}
	
	
	// Removes the first occurrence of the specified element from this list, if it is present.
	// Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
	public boolean remove(String s) {
		Node current=head;
		for (int i = 0; i < index; i++) {
			if(current.getData().equals(s)){
				get(i-1).setNext(get(i+1));
				return true;
			}
			current = current.getNext();
			if(current == null){
				return false;
			}
		}
		return false;
	}
	
	// Replaces the element at the specified position in this list with the specified element.
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node set(int index, String s) {
		Node current=head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
			if(current == null){
				throw new IndexOutOfBoundsException();
			}
		}
		Node temp = current;
		current.setData(s);
		return temp;
	}
	
	// Returns the number of elements in this collection.
	public int size() {
		int count = 0;
		Node current = head;
		while(current != null){
			count++;
			current = current.getNext();
		}
		return count;
	}
	

	
}

public class Node{
	private String data;
	private Node next;

	public Node(String data){
		this.data = data;
		this.next = null;
	}
	public Node(String data, Node next){
		this.data = data;
		this.next = next;
	}
	public String getData(){
		return data;
	}
	public Node getNext(){
		return next;
	}
	public void setData(String data){
		this.data = data;
	}
	public void setNext(Node next){
		this.next = next;
	}
}