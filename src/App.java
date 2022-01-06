import java.util.concurrent.*;

public class App {
    public static void main(String[] args) {
        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println("core count: "+coreCount);
//        ExecutorService service = Executors.newFixedThreadPool(coreCount);
//        for (int i = 0; i < 10; i++) {
//            service.execute(new Task());
//        }

        long start=0, end=0;
        String expr = "a1+a2+a3+a4";

//        JavaGroupingByCollectorUnitTest.print();
//        StoreFromArrayToObject.doItNow();
//        GroupBy.print();


        //measuring time to read inputs
        //starting time
        start = System.currentTimeMillis();

        //Task
        ReadInputs ri = new ReadInputs(expr);
        ReadInputs.readInputs();

        //ending time
        end = System.currentTimeMillis();
        System.out.println("Running ReadInputs takes: " + (end - start) + "ms");


        int k = ReadInputs.noOfLines;
        int tillIndex = 0;
        int iterateLoopStart = 0;
        int iterateLoopEnd = 0;




        //starting time
        start = System.currentTimeMillis();

        //Task
        StoreInArray[] sia = new StoreInArray[coreCount];

        ExecutorService service = Executors.newFixedThreadPool(coreCount);
//        service.execute(new StoreInMapToGroupBy());

        if(k>3){
//            service.execute(new StoreInArray(0,k-1));
            int batchSize = ReadInputs.noOfLines/4;
            int j=0;
            for (j = 1; j < coreCount; j++) {
                iterateLoopEnd = j * batchSize;
//                iterateLoopStart = 3 * batchSize;
//                iterateLoopEnd = ReadInputs.noOfLines-1;
                System.out.println("iterateLoopStart: "+iterateLoopStart+"        iterateLoopEnd: "+iterateLoopEnd);

                sia[j-1] = new StoreInArray(iterateLoopStart,iterateLoopEnd);
                iterateLoopStart = iterateLoopEnd;
            }
            iterateLoopEnd = ReadInputs.noOfLines-1;
            System.out.println("iterateLoopStart: "+iterateLoopStart+"        iterateLoopEnd: "+iterateLoopEnd);
            sia[j-1] = new StoreInArray(iterateLoopStart,iterateLoopEnd);
            System.out.println("sia.length: "+sia.length);

            for (int i = 0; i < coreCount; i++) {
                service.execute(sia[i]);
            }

//            service.execute(new StoreInArrayFirstPart());
//            service.execute(new StoreInArraySecondPart());
//            service.execute(new StoreInArrayThirdPart());
//            service.execute(new StoreInArrayFourthPart());
        }
        else {
            System.out.println("here in else : input lines<=3");
            sia[0] = new StoreInArray(0, k - 1);
            service.execute(sia[0]);
//            service.execute(new StoreInArrayFull());
        }
        service.execute(new StoreInMapToGroupBy());
        service.shutdown();

        //ending time
        end = System.currentTimeMillis();
//        System.out.println("Running StoreInArray takes: " + (end - start) + "ms");


//        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//
//        executorService.schedule(new StoreInMapToGroupBy(), 10, TimeUnit.SECONDS);
//        executorService.shutdown();







//        ExecutorService service1 = Executors.newFixedThreadPool(coreCount);
//        service1.execute(new StoreFromArrayToObject());
//        service1.shutdown();
//
//
//
//        ExecutorService service2 = Executors.newFixedThreadPool(coreCount);
//        service2.execute(new GroupBy());
//        service2.shutdown();


//        StoreFromArrayToObject.doItNow();
//        GroupBy.print();

//        System.out.println("no of values in array : "+ReadInputs.noOfLines);
//        System.out.println("last value of arr1: "+ReadInputs.arr1[ReadInputs.noOfLines-1]);



    }
}
