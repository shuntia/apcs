public class QueueUsingLinkedList implements QueueBehavior {
	/**
	 * An object of type Node holds one of the items
	 * in the linked list that represents the queue.
	 */
	private static class Node {
		int item;
		Node next; 
		
		private Node(int n) {
			item = n;
		}
	}

	private Node front;  
	
	/**
	 * Add N to the back of the queue.
	 */
	public void enqueue(int num) {
		Node newNode = new Node(num);  // A Node to hold the new item.
		newNode.next = front;
		front=newNode;
	}
	

	/**
	 * Remove and return the front item in the queue.
	 * Throws an IllegalStateException if the queue is empty.
	 */
	public int dequeue() {
		if ( front == null)
			throw new IllegalStateException("Can’t dequeue from an empty queue.");
		int firstItem = front.item;
		front=front.next;
		return firstItem;
	}
	
	
	public boolean isEmpty() {
        return (front == null);
    }
	
	public String toString() {
		String fifoString = "Oldest [";
		Node runner = front;
		if(runner == null)
			return fifoString + "]";
		
		while(runner.next != null) {
			fifoString += runner.item + ", ";
			runner = runner.next;  
		}
		fifoString += runner.item + "] Newest";
		return fifoString;
	}

}
