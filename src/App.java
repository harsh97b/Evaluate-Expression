import java.util.ArrayList;
import java.util.List;
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
        int iterateLoopStart = 0;
        int iterateLoopEnd = 0;


        StoreInArray[] storeInArray = new StoreInArray[coreCount];
        StoreInMapToGroupBy[] storeInMapToGroupBy = new StoreInMapToGroupBy[coreCount];

        ExecutorService service = Executors.newFixedThreadPool(coreCount);
        List<Future> allFutures= new ArrayList<>();
//        Future<Integer future ;
        Future<?> future;
        for (int i = 0; i < 4; i++) {


        }

        if(k>3){
            iterateLoopStart = 0;
            int batchSize = ReadInputs.noOfLines/4;
            int j=0;
            for (j = 1; j < coreCount; j++) {
                iterateLoopEnd = j * batchSize;
                System.out.println("iterateLoopStart: "+iterateLoopStart+"        iterateLoopEnd: "+iterateLoopEnd);

                storeInArray[j-1] = new StoreInArray(iterateLoopStart,iterateLoopEnd);
                iterateLoopStart = iterateLoopEnd+1;
            }
            iterateLoopEnd = ReadInputs.noOfLines-1;
            System.out.println("iterateLoopStart: "+iterateLoopStart+"        iterateLoopEnd: "+iterateLoopEnd);
            storeInArray[j-1] = new StoreInArray(iterateLoopStart,iterateLoopEnd);
//            System.out.println("storeInArray.length: "+storeInArray.length);

            for (int i = 0; i < coreCount; i++) {
                future = service.submit(storeInArray[i]);
                allFutures.add(future);
            }
//            service.execute(new StoreInArrayFirstPart());
//            service.execute(new StoreInArraySecondPart());
//            service.execute(new StoreInArrayThirdPart());
//            service.execute(new StoreInArrayFourthPart());
        }
        else {
            System.out.println("here in else : input lines<=3");
            storeInArray[0] = new StoreInArray(0, k - 1);
            future = service.submit(storeInArray[0]);
            allFutures.add(future);

//            service.execute(new StoreInArrayFull());
        }
//        allFutures.get(0).isDone();
//        allFutures.get(1).isDone();
//        allFutures.get(2).isDone();
//        allFutures.get(3).isDone();
        int noOfTasks = allFutures.size();
        try {
            for (int i = 0; i < noOfTasks; i++) {
                allFutures.get(i).get();
                System.out.println("Task "+(i+1)+" is completed!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //allFutures.clear();
//        StoreInArray.print();

        System.out.println("-------------------------Proceeding to the StoreInMapToGroupBy------------------------------------------");
        //doing StoreInMapToGroupBy by multithreading was giving wrong result, so decided to do it with single thread;
        future = service.submit(new StoreInMapToGroupBy(0,k-1));
        allFutures.add(future);
//        if(k>3){
////            service.execute(new StoreInArray(0,k-1));
//            iterateLoopStart = 0;
//            int batchSize = ReadInputs.noOfLines/4;
//            int j=0;
//
//            for (j = 1; j < coreCount; j++) {
//                iterateLoopEnd = j * batchSize;
//                storeInMapToGroupBy[j-1] = new StoreInMapToGroupBy(iterateLoopStart,iterateLoopEnd);
//                iterateLoopStart = iterateLoopEnd+1;
//            }
//            iterateLoopEnd = ReadInputs.noOfLines-1;
//            storeInMapToGroupBy[j-1] = new StoreInMapToGroupBy(iterateLoopStart,iterateLoopEnd);
//            System.out.println("storeInMapToGroupBy.length: "+storeInMapToGroupBy.length);
//
//            for (int i = 0; i < coreCount; i++) {
//                future = service.submit(storeInMapToGroupBy[i]);
//                allFutures.add(future);
//            }
//        }
//        else {
//            System.out.println("here in else : input lines<=3");
//            storeInMapToGroupBy[0] = new StoreInMapToGroupBy(0, k - 1);
//            future = service.submit(storeInMapToGroupBy[0]);
//            allFutures.add(future);
//
//        }

        // For haulting the program till previious programs execute
        int noOfTasksNew = allFutures.size();
        try {
//            System.out.println("noOfTasks: "+noOfTasks);
//            System.out.println("noOfTasksNew: "+noOfTasksNew);

            for (int i = noOfTasks; i < noOfTasksNew; i++) {
                allFutures.get(i).get();
                System.out.println("Task "+(i+1)+" is completed!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        allFutures.clear();
        System.out.println("-------------------------------Proceeding to the Print Result------------------------------------");


        //submiting last task
        service.execute(new PrintResult());

        service.shutdown();

//        //ending time
//        end = System.currentTimeMillis();
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
