import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bai3 {

    public static <T> List<T> findPatients(List<T> listA, List<T> listB) {
        List<T> commonList = new ArrayList<>();

        for (T patient : listA) {
            if (listB.contains(patient)) {
                commonList.add(patient);
            }
        }

        return commonList;
    }

    public static void main(String[] args) {
        List<Integer> listAInt = Arrays.asList(101, 102, 105);
        List<Integer> listBInt = Arrays.asList(102, 105, 108);

        List<Integer> resultInt = findPatients(listAInt, listBInt);
        System.out.println("Test Case 1 Output: " + resultInt);

        List<String> listAStr = Arrays.asList("DN01", "DN02", "DN03");
        List<String> listBStr = Arrays.asList("DN02", "DN04");

        List<String> resultStr = findPatients(listAStr, listBStr);
        System.out.println("Test Case 2 Output: " + resultStr);
    }
}