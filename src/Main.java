import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main (String [] args)
    {
        Integer [] arr1 = {1,2,3,4,5};
        String [] arr2 = {"1","2","3","4","5"};
        switchElements (arr1,4,1);
        switchElements (arr2,4,1);
        // проверка
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

        System.out.println(castArrayList(arr1));
        System.out.println(castArrayList(arr2));

        Box<Apple> box1 = new Box<>();
        Box<Orange> box2 = new Box<>();
        Box<Apple> box3 = new Box<>();
        box1.addFruit(new Apple());
        box1.addFruit(new Apple());
        box1.addFruit(new Apple());
        System.out.println(box1.getWeight());
        box2.addFruit(new Orange());
        box2.addFruit(new Orange());
        System.out.println(box2.getWeight());
        System.out.println(box1.compare(box2));
        box1.replace(box3);
        System.out.println(box3.getWeight());
        System.out.println(box1.getWeight());

    }
    public static void switchElements (Object [] arr, int id1, int id2)
    {
        Object first = arr [id1];
        Object second = arr [id2];
        arr [id1] = second;
        arr [id2] = first;
    }
    public static  <T> ArrayList<T> castArrayList (T [] arr) {
        ArrayList<T> list = new ArrayList<>();
        for (T i : arr) {
            list.add(i);
        }
        return list;
    }
}
