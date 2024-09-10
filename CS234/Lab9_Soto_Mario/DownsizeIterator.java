/* 
Mario Soto
Lab 9
*/
import java.util.Iterator;
import java.util.LinkedList;

public class DownsizeIterator {
    public int nth;
	//Constructor
    public DownsizeIterator(int nth) {
       this.nth = nth;
    }
	//Method to remove every nth element
    public void removeElements(LinkedList<String> employeeNames) {
        Iterator<String> it = employeeNames.listIterator();
            int i=0;
			System.out.println("Removing every nth (" + nth +") element");
        while (it.hasNext()) {
            it.next();
            if ((i + 1) % nth == 0) {
                it.remove();
            }
            i++;
        }
    }
	//Method to print Linked List
    public void print(LinkedList<String> employeeNames) {
        Iterator<String> it = employeeNames.iterator();
        System.out.println("The content of the LinkedList is: ");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }
}

