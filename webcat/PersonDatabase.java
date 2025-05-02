import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
public class PersonDatabase {

	/**
	 * This Node is the root of a tree of Person
	 * objects that is sorted by last
	 * name and then first name (ignoring case).
	 * This tree allows duplicate names (as long as
	 * the birth dates are different).
	 */
	private Node rootOfNameTree;

	/**
	 * This Node is the root of a tree
	 * of Person objects that is sorted by birth
	 * date. This tree allows duplicate
	 * birth dates (as long as the names are
	 * different).
	 */
	private Node rootOfBirthDateTree;

	/**
	 * The number of nodes in the tree.
	 * Both trees should have the same
	 * number of nodes.
	 */
	private int size;

	public static void main(String[] args) {
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		PersonDatabase db = new PersonDatabase();
		while (true) {
			System.out.print("Enter a command: ");
			String command = scanner.nextLine();
			if (command.equals("exit")) {
				break;
			} else if (command.equals("print")) {
				db.printBoth();
			} else if (command.startsWith("add ")) {
				String[] parts = command.split(" ");
				String firstName = parts[1];
				String lastName = parts[2];
				int birthDay = Integer.parseInt(parts[3]);
				int birthMonth = Integer.parseInt(parts[4]);
				int birthYear = Integer.parseInt(parts[5]);
				Person p = new Person(firstName, lastName, birthDay, birthMonth, birthYear);
				db.put(p);
			} else if (command.startsWith("find ")) {
				String[] parts = command.split(" ");
				if (parts.length == 3) {
					String firstName = parts[1];
					String lastName = parts[2];
					List<Person> result = db.find(firstName, lastName);
					System.out.println(result);
				} else if (parts.length == 5) {
					int birthDay = Integer.parseInt(parts[1]);
					int birthMonth = Integer.parseInt(parts[2]);
					int birthYear = Integer.parseInt(parts[3]);
					List<Person> result = db.find(birthDay, birthMonth, birthYear);
					System.out.println(result);
				}
			}
		}
		scanner.close();	
	}

	public void printBoth() {
		printTree(rootOfNameTree, 0);
		System.out.println("-----------------");
		printTree(rootOfBirthDateTree, 0);
	}

	public static void printTree(Node node, int level) {
		if (node == null) {
			return;
		}
		// Print right subtree with increased indentation
		printTree(node.right, level + 1);

		// Print the current node's item with indentation proportional to its level
		String indent = "                  ".repeat(level);
		System.out.println(indent + node.item);

		// Print left subtree
		printTree(node.left, level + 1);
	}

	/**
	 * Returns number of Persons in
	 * the database
	 * 
	 * @return number of Persons
	 */
	public int size() {
		return size;
	}

	/**
	 * Add person to the database unless
	 * a Person object that is equal already
	 * exists. If a node is created, it
	 * should be added to both the name tree
	 * and the birth date tree.
	 * 
	 * @param p a person
	 * @return true if person is added, false otherwise
	 */
	public boolean put(Person p) {
		// hint: create two private put methods
		// for each of the trees and call
		// them here
		if (this.rootOfNameTree == null) {
			this.rootOfNameTree = new Node(p);
			this.rootOfBirthDateTree = new Node(p);
			size += 1;
			return true;
		}
		if (compPut(p, this.rootOfNameTree, (l, r) -> compName(l, r), (l, r) -> compBday(l, r))
				&& compPut(p, this.rootOfBirthDateTree, (l, r) -> compBday(l, r),
						(l, r) -> compName(l, r))) {
			size++;
			return true;
		} else {
			return false;
		}
	}

	private boolean compPut(Person p, Node root, BiFunction<Person, Person, Integer> first,
			BiFunction<Person, Person, Integer> second) {
		if (root == null) {
			return false;
		}
		if (root.item.equals(p)) {
			return false;
		}

		if(first.apply(p, root.item) > 0) {
			if (root.right == null) {
				root.right = new Node(p);
				return true;
			} else {
				return compPut(p, root.right, first, second);
			}
		} else  {
			if (root.left == null) {
				root.left = new Node(p);
				return true;
			} else {
				return compPut(p, root.left, first, second);
			}
		}
		
	}

	private static int compName(Person l, Person r) {
		int comp = l.lastName.compareTo(r.lastName);
		if (comp == 0) {
			return l.firstName.compareTo(r.firstName);
		} else {
			return comp;
		}

	}

	private static int compBday(Person l, Person r) {
		return l.birthYear == r.birthYear
				? (l.birthMonth == r.birthMonth
						? (l.birthDay == r.birthDay ? 0 : l.birthDay - r.birthDay)
						: l.birthMonth - r.birthMonth)
				: l.birthYear - r.birthYear;
	}

	/**
	 * Returns a list of all Person objects in the database with the given name.
	 * This method should search in name tree.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return a list of Person objects (possibly empty)
	 */
	public List<Person> find(String firstName, String lastName) {
		return findInner(rootOfNameTree, firstName, lastName);
	}
public List<Person> findInner(Node root, String firstName, String lastName) {
		ArrayList<Person> acc = new ArrayList<>();
		Person p = new Person(firstName, lastName, 0, 0, 0);
		Node current = root;
		if(current == null) {
			return acc;
		}
			if (current.item.firstName.equals(firstName) && current.item.lastName.equals(lastName)) {
				acc.add(current.item);
			}
			if (compName(p, current.item) > 0) {
				acc.addAll(findInner(current.right, firstName, lastName));
			} else if (compName(p, current.item) < 0) {
				if (current.left != null && compName(p, current.left.item) == 0) {
					acc.addAll(findInner(current.left, firstName, lastName));
				}
			}else{
				acc.addAll(findInner(current.left, firstName, lastName));
				acc.addAll(findInner(current.right, firstName, lastName));
			}
		return acc;
	}

	/**
	 * Returns a list of all Person objects in the database with the given birth
	 * date. This method should search in the birth date tree.
	 * 
	 * @param birthDay
	 * @param birthMonth
	 * @param birthYear
	 * @return a list of Person objects (possibly empty)
	 */
	public List<Person> find(int birthDay, int birthMonth, int birthYear) {
		return findInner(rootOfBirthDateTree, birthDay, birthMonth, birthYear);
		

	}

public List<Person> findInner(Node root,int birthDay, int birthMonth, int birthYear) {
		ArrayList<Person> acc = new ArrayList<>();
		Person p = new Person("", "", birthDay, birthMonth, birthYear);
		Node current = root;
		if(current == null) {
			return acc;
		}
			if (current.item.birthDay == birthDay && current.item.birthMonth == birthMonth
					&& current.item.birthYear == birthYear)
				acc.add(current.item);
			if(compBday(p, current.item) > 0) {
				acc.addAll(findInner(current.right, birthDay, birthMonth, birthYear));
				
			} else {
				if(current.right!=null&&compBday(p,current.right.item) == 0) {
					acc.addAll(findInner(current.right, birthDay, birthMonth, birthYear));
				}
				acc.addAll(findInner(current.left, birthDay, birthMonth, birthYear));
			}
		return acc;

	}
	// ***** For testing purposes
	public Node getNameRoot() {
		return rootOfNameTree;
	}

	public Node getBDayRoot() {
		return rootOfBirthDateTree;
	}

}
