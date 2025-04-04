import java.util.ArrayList;

public class BinaryTreeOfStrings {
	// Every Binary Tree object needs a reference to its root
	TreeNode root;

	// Insert a TreeNode into the tree in the proper location to
	// maintain a sorted order. If there is a tie, you can insert it to the right.
	// Consider different cases the tree might be in.
	// str.compareTo(anotherStr) > 0 if str is "later" anotherStr lexigraphically,
	// based on the Unicode.
	// str.compareTo(anotherStr) < 0 if str is "before" anotherStr
	// str.compareTo(anotherStr) == 0 if they are equal -- or you can use .equals()
	public void insert(String str) {
		TreeNode n = new TreeNode(str);
		if (root == null) {
			root = n;
			return;
		}
		TreeNode runner = root; // Traverse iteratively
		while (true) {
			if (runner.name.compareTo(str) < 0) {
				if (runner.left == null) {
					runner.left = n;
					return;
				} else {
					runner = runner.left;
				}
			} else {
				if (runner.right == null) {
					runner.right = n;
					return;
				} else {
					runner = runner.right;
				}
			}
		}

	}

	// Return true if the nameToFind is in the tree somewhere.
	// Return false otherwise.
	// Traverse the tree iteratively.
	public boolean contains(String nameToFind) {
		TreeNode runner = root;
		while (true) {
			if (runner == null)
				return false;
			if (runner.name.equals(nameToFind))
				return true;
			if (runner.name.compareTo(nameToFind) < 0) {
				runner = runner.left;
				continue;
			} else {
				runner = runner.right;
				continue;
			}
		}
	}

	// Return true if the nameToFind is in the tree somewhere.
	// Return false otherwise.
	// Implement recursively.
	// Note: this is a 'helper' for the actual recursive method -- outside class
	// can't access root
	public boolean containsRecursive(String nameToFind) {
		return containsRecursive(root, nameToFind);
	}

	private boolean containsRecursive(TreeNode root, String nameToFind) {
		return root != null && (root.name.equals(nameToFind)
				|| (root.name.compareTo(nameToFind) < 0 ? containsRecursive(root.left, nameToFind)
						: containsRecursive(root.right, nameToFind)));
	}

	// HUGE thanks to Michal Kreuzman for writing this print method!
	// http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
	public void print() {
		BTreePrinter.printNode(root);
	}

	// helper for recursive method -- outside class can't access root
	public int getSize() {
		return getSize(root);
	}

	// recursively get the number of nodes in the tree
	private int getSize(TreeNode root) {
		if (root == null) // Base Case
			return 0;
		// Otherwise add this node to the sum of all the nodes
		// in the left subtree + all nodes in right subtree
		return 1 + getSize(root.left) + getSize(root.right);
	}

}
