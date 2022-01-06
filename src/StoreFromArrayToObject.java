import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class StoreFromArrayToObject implements Runnable{
    public static final List<DataStored> list = new ArrayList<>();

    @Override
    public void run() {


        //starting time
        long start = System.currentTimeMillis();

        //Task
        for (int i = 0; i < ReadInputs.noOfLines; i++) {
            list.add(new DataStored(ReadInputs.result[i],ReadInputs.region[i],ReadInputs.country[i]));
        }

        //ending time
        long end = System.currentTimeMillis();
        System.out.println("       Storing "+ReadInputs.noOfLines+" input lines to OBJECT takes: " + (end - start) + "ms");


//        //list.forEach(System.out::println);
//        for(DataStored m : list) {
//            System.out.println(m.getResult()+ "   "+ m.getRegion()+"     "+m.getCountry());
//        }
//        System.out.println("IN StoreFromArrayToObject");
//        System.out.println("------------------------------");
    }

    public static void doItNow(){
        for (int i = 0; i < ReadInputs.noOfLines; i++) {
            list.add(new DataStored(ReadInputs.result[i],ReadInputs.region[i],ReadInputs.country[i]));
        }

//        //list.forEach(System.out::println);


//        for(DataStored m : list) {
//            System.out.println(m.getResult()+ "   "+ m.getRegion()+"     "+m.getCountry());
//        }
//        System.out.println("IN StoreFromArrayToObject");
//        System.out.println("------------------------------");
    }
}
