import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

public class GroupBy implements Runnable{
//    private static final List<DataStored> data = Arrays.asList(
//            new DataStored(4.0f,"asia","india"),
//            new DataStored(8.0f,"europe","france"),
//            new DataStored(12.0f,"asia","india"),
//            new DataStored(16.0f,"europe","germany"),
//            new DataStored(20.0f,"europe","france"));
    private static List<DataStored> data =new ArrayList<>();

    @Override
    public void run() {

        //starting time
        long start = System.currentTimeMillis();

        //Task
        data = StoreFromArrayToObject.list;
//        data.forEach(System.out::println);
//        System.out.println("          groupby");
//        System.out.println("----------------------------------");
        Map<DataStored.RegionCountry, Double> postsPerRegionAndCountry = data.stream()
                .collect(groupingBy(data -> new DataStored.RegionCountry(data.getRegion(),data.getCountry()),summingDouble(DataStored::getResult)));
        postsPerRegionAndCountry.forEach((key, value) -> System.out.println(key + ":" + value));

        //ending time
        long end = System.currentTimeMillis();
        System.out.println("       GroupBy the "+ReadInputs.noOfLines+" input lines takes: " + (end - start) + "ms");



    }

    public static void  print(){
        data = StoreFromArrayToObject.list;
//        data.forEach(System.out::println);
        for(DataStored m : data) {
            System.out.println(m.getResult()+ "   "+ m.getRegion()+"     "+m.getCountry());
        }
        System.out.println("         groupby");
        System.out.println("----------------------------------");
        Map<DataStored.RegionCountry, Double> postsPerRegionAndCountry = data.stream()
                .collect(groupingBy(data -> new DataStored.RegionCountry(data.getRegion(),data.getCountry()),summingDouble(DataStored::getResult)));
        postsPerRegionAndCountry.forEach((key, value) -> System.out.println(key + ":" + value));
        System.out.println("____________________________________________________________________________");
    }
}
