/* 
Mario Soto
Lab 9*/
import java.util.Iterator;
import java.util.LinkedList;

public class Lab9_Soto_Mario {

    public static void main(String[] args) {
        LinkedList<String> employees = new LinkedList<>();
        employees.add("Eduardo");
        employees.add("Emma");
        employees.add("Carlos");
        employees.add("Luis");
        employees.add("Maria");
        employees.add("John");
        
        DownsizeIterator ds = new DownsizeIterator(2);
        ds.print(employees);
        ds.removeElements(employees);
        ds.print(employees);
    }
}
