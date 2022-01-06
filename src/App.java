import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread name: "+ Thread.currentThread().getName());
    }
}
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
        ExecutorService service = Executors.newFixedThreadPool(coreCount);
        if(k>3){
            service.execute(new StoreInArrayFirstPart());
            service.execute(new StoreInArraySecondPart());
            service.execute(new StoreInArrayThirdPart());
            service.execute(new StoreInArrayFourthPart());
        }
        else{
            service.execute(new StoreInArrayFull());
        }
        service.shutdown();

        //ending time
        end = System.currentTimeMillis();
        System.out.println("Running StoreInArray takes: " + (end - start) + "ms");

//        System.out.println("no of values in array : "+ReadInputs.noOfLines);
//        System.out.println("last value of arr1: "+ReadInputs.arr1[ReadInputs.noOfLines-1]);

    }
}
