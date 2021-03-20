import java.util.ArrayList;

public class Box<T extends Fruit> {

    public ArrayList <T> fruits = new ArrayList<>();
    public void addFruit (T fruit)
    {
        fruits.add(fruit);
    }
     public float getWeight()
     {
         if (fruits.size()==0) return 0f;
         return fruits.size()*fruits.get(0).weight;
     }
     public boolean compare (Box box)
     {
         if (this.getWeight() == box.getWeight()) return true;
         return false;
     }
     public  void replace (Box box)
     {
         int size = this.fruits.size();
         for (int i =0; i<size;i++)
         {
           box.addFruit(this.fruits.remove(0));

         }

     }

}
