import java.util.ArrayList;
import java.util.Iterator;

public class Bai1 {
    public static void main(String[] args) {
        ArrayList<Double> list = new ArrayList<>();
        list.add(36.5);
        list.add(40.2);
        list.add(37.0);
        list.add(12.5);
        list.add(39.8);
        list.add(99.9);
        list.add(36.8);
        System.out.println(list);
        Iterator<Double> it = list.iterator();
        while (it.hasNext()) {
            Double temp = it.next();
            if (temp < 34.0 || temp > 42.0) {
                it.remove();
            }
        }

        System.out.println("Danh sách sau khi lọc: " + list);
        double sum = 0;
        for (Double temp : list) {
            sum += temp;
        }

        double average = list.isEmpty() ? 0 : sum / list.size();

        System.out.printf("Nhiệt độ trung bình: %.2f\n", average);
    }
}
