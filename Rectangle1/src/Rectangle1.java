/**
 * The class containing the main method.
 *
 * @author Patrick Marlow (patrickmarlow)
 * @version 2022-4-28
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class Rectangle1 {

	public static void main(String[] args) {

		// Test insertion and dumps
		SkipList<String, Rect> sl = new SkipList<String, Rect>();

		Rect temp = new Rect(0, 0, 5, 5);

		System.out.println(sl.dump());

		sl.insert("a", temp);
		
		System.out.println(sl.dump());

		temp = new Rect(0, 0, 10, 10);

		sl.insert("b", temp);
		
		System.out.println(sl.dump());
		
		temp = new Rect(10, 10, 20, 20);

		sl.insert("c", temp);
		
		System.out.println(sl.dump());
		
		sl.remove("b");
		
		System.out.println(sl.dump());
		
		sl.remove(temp);
		
		System.out.println(sl.dump());

	}
	
	private void parseandeval(String filename) {
		
	}
	
	private boolean insert(String key, Rect r) {
		return false;
	}
	
	private boolean remove(String key) {
		return true;
	}
	
	private boolean remove(Rect r) {
		return false;
		
	}
	
	private void regionsearch(Rect r) {
		
	}
	
	private boolean intersections() {
		return false;
		
	}
	
	public String dump{
		return null;
	}

}
