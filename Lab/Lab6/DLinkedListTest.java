import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
public class DLinkedListTest {
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Performing the testing for class: DLinkedList");
        System.out.println(new String(new char[80]).replace('\0', '='));
    }
    @Test
    public void testSize() {
        System.out.println("testing ... size()");
        List<String> list = new DLinkedList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("0");
        list.add("1");
        list.remove(1);
        list.add("4");
        assertEquals(6, list.size());
    }
    @Test
    public void testIndexOf() {
        System.out.println("testing ... indexOf()");
        List<String> list = new DLinkedList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("0");
        list.add("1");
        list.add("5");
        assertEquals(-1, list.indexOf("6"));
        assertEquals(0, list.indexOf("0"));
        assertEquals(6, list.indexOf("5"));
        assertEquals(1, list.indexOf("1"));
    }
    @Test
    public void testLastIndexOf() {
        System.out.println("testing ... lastIndexOf()");
        List<String> list = new DLinkedList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("0");
        list.add("1");
        list.add("5");
        assertEquals(-1, list.lastIndexOf("8"));
        assertEquals(6, list.lastIndexOf("5"));
        assertEquals(4, list.lastIndexOf("0"));
        assertEquals(2, list.lastIndexOf("2"));
    }
    @Test public void testRemoval_int(){
        System.out.println("testing ... remove(int)");
        List<String> list = new DLinkedList<>();
        try{
            list.remove(0);
        }
        catch(IndexOutOfBoundsException exp){
            System.out.println("\t empty case: work as expectation");
        }

        list.add("0");
        list.add("1");
        try{
            list.remove(4);
        }
        catch(IndexOutOfBoundsException exp){
            System.out.println("\t IndexOutOfBounds (>=size): work as expectation");
        }

        try{
            list.remove(-1);
        }
        catch(IndexOutOfBoundsException exp){
            System.out.println("\t IndexOutOfBounds (<0): work as expectation");
        }
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
        list.remove(1);
        assertEquals(3, list.size());
        assertEquals(false, list.contains("1"));
        assertEquals("2", list.get(1));
    }
    @Test public void testRemoval_Object(){
        System.out.println("testing ... remove(Object)");
        List<String> list = new DLinkedList<>();

        assertEquals(false, list.remove("10"));
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
        assertEquals(false, list.remove("10"));
        assertEquals(4, list.size());
        assertEquals(true, list.remove("0"));
        assertEquals(3, list.size());
        assertEquals(true, list.remove("3"));
        assertEquals(2, list.size());
        assertEquals(true, list.remove("1"));
        assertEquals(1, list.size());
    }
    @Test public void testClear(){
        System.out.println("testing ... clear()");
        List<String> list = new DLinkedList<>();

        assertEquals(0, list.size());
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
        list.clear();
        assertEquals(0, list.size());
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
    }

    @Test
    public void testGet_int(){
        System.out.println("testing ... get(int index)");
        List<String> list = new DLinkedList<>();
        try{
            list.get(0);
        }
        catch(IndexOutOfBoundsException exp){
            System.out.println("\t empty case: work as expectation");
        }

        list.add("0");
        list.add("1");
        try{
            list.get(4);
        }
        catch(IndexOutOfBoundsException exp){
            System.out.println("\t IndexOutOfBounds (>=size): work as expectation");
        }

        try{
            list.get(-1);
        }
        catch(IndexOutOfBoundsException exp){
            System.out.println("\t IndexOutOfBounds (<0): work as expectation");
        }
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
        assertEquals("2", list.get(2));
    }

    @Test
    public void testSet_int_Object(){
        System.out.println("testing ... set(int index, Object)");
        List<String> list = new DLinkedList<>();
        try{
            list.set(0, "2");
        }
        catch(IndexOutOfBoundsException exp){
            System.out.println("\t empty case: work as expectation");
        }

        list.add("0");
        list.add("1");
        try{
            list.set(3, "2");
        }
        catch(IndexOutOfBoundsException exp){
            System.out.println("\t IndexOutOfBounds (>=size): work as expectation");
        }

        try{
            list.set(-1, "2");
        }
        catch(IndexOutOfBoundsException exp){
            System.out.println("\t IndexOutOfBounds (<0): work as expectation");
        }
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
        list.set(2, "5");
        assertEquals("5", list.get(2));
    }
    @Test
    public void testAdd_int_Object(){
        System.out.println("testing ... add(int index, Object)");
        List<String> list = new DLinkedList<>();
        try{
            list.add(1, "2");
        }
        catch(IndexOutOfBoundsException exp){
            System.out.println("\t empty case: work as expectation");
        }

        list.add("0");
        list.add(0, "10");
        assertEquals(2, list.size());

        assertEquals("10", list.get(0));
        assertEquals("0", list.get(1));
        assertEquals(2, list.size());

        list.add(1, "20");
        assertEquals(3, list.size());

        assertEquals("10", list.get(0));
        assertEquals("20", list.get(1));
        assertEquals("0", list.get(2));

        try{
            list.set(3, "5");
        }
        catch(IndexOutOfBoundsException exp){
            System.out.println("\t IndexOutOfBounds (>=size): work as expectation");
        }
        try{
            list.add(-1, "2");
        }
        catch(IndexOutOfBoundsException exp){
            System.out.println("\t IndexOutOfBounds (<0): work as expectation");
        }
    }
    @Test public void testAdd_Object(){
        System.out.println("testing ... add(Object)");
        List<String> list = new DLinkedList<>();

        assertEquals(0, list.size());
        assertEquals(true, list.isEmpty());
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
        list.clear();
        assertEquals(0, list.size());
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(4, list.size());
    }
    @Test public void testIterator_Remove(){
        System.out.println("testing ... iterator.remove");
        DLinkedList<String> list = new DLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(""+idx);
        }
        assertEquals(10, list.size());
        DLinkedList<String>.DLinkedListIterator it = list.new DLinkedListIterator(0);
        while(it.hasNext()){
            String item = it.next();
            if(Integer.parseInt(item)%2 != 0)
                it.remove();
        }

        assertEquals(5, list.size());
        assertEquals("2", list.get(1));
        assertEquals("8", list.get(4));
    }
    @Test public void testListIterator_Remove(){
        System.out.println("testing ... listIterator.remove");
        DLinkedList<String> list = new DLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(""+idx);
        }
        assertEquals(10, list.size());
        DLinkedList<String>.DLinkedListIterator it = list.new DLinkedListIterator(0);
        while(it.hasNext()){
            String item = it.next();
            if(Integer.parseInt(item)%2 != 0)
                it.remove();
        }
        assertEquals(5, list.size());
        assertEquals("2", list.get(1));
        assertEquals("8", list.get(4));
    }
    @Test public void testListIterator_Set_Next(){
        System.out.println("testing ... listIterator.set with next()");
        DLinkedList<String> list = new DLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(""+idx);
        }
        assertEquals(10, list.size());
        DLinkedList<String>.DLinkedListIterator it = list.new DLinkedListIterator(0);
        while(it.hasNext()){
            String item = it.next();
            if(Integer.parseInt(item)%2 != 0){
                String new_item =  String.format("%d", Integer.parseInt(item)*2);
                it.set(new_item);
            }
        }
        assertEquals(10, list.size());
        assertEquals("0", list.get(0));
        assertEquals("2", list.get(1));
        assertEquals("2", list.get(2));
        assertEquals("6", list.get(3));
        assertEquals("18", list.get(9));
    }

    @Test
    public void testListIterator_Set_Prev(){
        System.out.println("testing ... listIterator.set");
        List<String> list = new DLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(""+idx);
        }
        assertEquals(10, list.size());
        ListIterator<String> it = list.listIterator(list.size());
        while(it.hasPrevious()){
            String item = it.previous();
            if(Integer.parseInt(item)%2 != 0){
                String new_item =  String.format("%d", Integer.parseInt(item)*2);
                it.set(new_item);
            }
        }
        assertEquals(10, list.size());
        assertEquals("0", list.get(0));
        assertEquals("2", list.get(1));
        assertEquals("2", list.get(2));
        assertEquals("6", list.get(3));
        assertEquals("18", list.get(9));
    }

    @Test public void testListIterator_Add_Next(){
        System.out.println("testing ... listIterator.add with next()");
        List<String> list = new DLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(""+idx);
        }
        assertEquals(10, list.size());

        ListIterator<String> it = list.listIterator();
        while(it.hasNext()){
            String item = it.next();
            if(Integer.parseInt(item)%2 != 0){
                String new_item =  String.format("%d", Integer.parseInt(item)*2);
                it.add(new_item);
            }
        }
        assertEquals(15, list.size());

        assertEquals("0", list.get(0));
        assertEquals("2", list.get(1));
        assertEquals("1", list.get(2));
        assertEquals("2", list.get(3));
        assertEquals("18", list.get(13));
        assertEquals("9", list.get(14));
    }
    @Test public void testListIterator_Add_Prev(){
        System.out.println("testing ... listIterator.add with previous()");
        List<String> list = new DLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(""+idx);
        }
        assertEquals(10, list.size());

        ListIterator<String> it = list.listIterator(list.size());
        while(it.hasPrevious()){
            String item = it.previous();
            if(Integer.parseInt(item)%2 != 0){
                String new_item =  String.format("%d", Integer.parseInt(item)*2);
                it.add(new_item);
            }
        }
        assertEquals(15, list.size());

        assertEquals("0", list.get(0));
        assertEquals("2", list.get(1));
        assertEquals("1", list.get(2));
        assertEquals("2", list.get(3));
        assertEquals("18", list.get(13));
        assertEquals("9", list.get(14));
    }
    @Test public void testListIterator_Remove_with_Next(){
        System.out.println("testing ... listIterator.remove with next()");
        DLinkedList<Integer> list = new DLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(idx);
        }
        assertEquals(10, list.size());
        DLinkedList<Integer>.DLinkedListIterator it = list.new DLinkedListIterator(0);
        while(it.hasNext()){
            int item = it.next();
            if(item >= 5) it.remove();
        }
        assertEquals(5, list.size());
        assertEquals(0, (int)list.get(0));
        assertEquals(4, (int)list.get(4));

        it = list.new DLinkedListIterator(0);
        while(it.hasNext()){
            int item = it.next();
            if(item <= 2) it.remove();
        }
        assertEquals(2, list.size());
        assertEquals(3, (int)list.get(0));
        assertEquals(4, (int)list.get(1));
    }
    @Test public void testListIterator_Remove_with_Prev(){
        System.out.println("testing ... listIterator.remove with previous()");
        List<Integer> list = new DLinkedList<>();
        for(int idx=0; idx<10; idx++){
            list.add(idx);
        }
        assertEquals(10, list.size());
        ListIterator<Integer> it = list.listIterator(list.size());
        while(it.hasPrevious()){
            int item = it.previous();
            if(item >= 5) it.remove();
        }
//        //
//        it = list.listIterator(list.size());
//        while(it.hasPrevious()){
//            int item = it.previous();
//            System.out.println("" + item);
//        }
//        //
        assertEquals(5, list.size());
        assertEquals(0, (int)list.get(0));
        assertEquals(4, (int)list.get(4));

        it = list.listIterator(list.size());
        while(it.hasPrevious()){
            int item = it.previous();
            if(item <= 2) it.remove();
        }
        assertEquals(2, list.size());
        assertEquals(3, (int)list.get(0));
        assertEquals(4, (int)list.get(1));
    }
}