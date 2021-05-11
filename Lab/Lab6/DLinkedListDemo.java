public class DLinkedListDemo {
    public static void main(String[] args) {
        DLinkedList<Integer> list = new DLinkedList<>();

        //QUESTION 2:
        //Add elements
        for(int idx=0; idx < 10; idx++){
            list.add(idx, idx);
        }
        //(1)PRINT ELEMENTS - USE INDEX, TRAVEL FORWARD
        System.out.printf("%-25s", "Before modification:");
        for(int idx=0; idx < list.size(); idx++){
            System.out.printf("%s ",list.get(idx));
        }
        System.out.println();

        //(2)REMOVE ODD NUMBERS
        DLinkedList<Integer>.DLinkedListIterator it = list.new DLinkedListIterator(0);

        while(it.hasNext()){
            int item = it.next();
            if(item % 2 != 0) it.remove();
            else it.set(item*10);
        }
        //(3)PRINT AFTER CHANGING
        System.out.printf("%-25s", "After modification:");
        DLinkedList<Integer>.DLinkedListIterator it2 = list.new DLinkedListIterator(0);
        while(it2.hasNext()){
            System.out.printf("%d ", it2.next());
        }
        System.out.println();
    }
}
