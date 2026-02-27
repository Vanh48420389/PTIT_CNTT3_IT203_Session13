import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bai2 {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("Paracetamol", "Ibuprofen", "Panadol", "Paracetamol", "Aspirin", "Ibuprofen");
        System.out.println(input);

        List<String> uniqueList = new ArrayList<>();

        for (String medicine : input) {
            if (!uniqueList.contains(medicine)) {
                uniqueList.add(medicine);
            }
        }

        Collections.sort(uniqueList);

        System.out.println("Output: " + uniqueList);
    }
}
