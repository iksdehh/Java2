package Java2;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<List<Integer>> listOfLists = new ArrayList<>();
        listOfLists.add(Arrays.asList(2));
        listOfLists.add(Arrays.asList(22, 23, 24));
        listOfLists.add(Arrays.asList(22, 24));

        List<List<Integer>> filteredList = new ArrayList<>();
        System.out.println(listOfLists);

        for (List<Integer> currentList : listOfLists) {
            boolean isSubList = false;

            for (List<Integer> otherList : listOfLists) {
                if (!currentList.equals(otherList) && otherList.containsAll(currentList)) {
                    isSubList = true;
                    break;
                }
            }

            if (!isSubList) {
                filteredList.add(currentList);
            }
        }

        System.out.println(filteredList);

    }
}