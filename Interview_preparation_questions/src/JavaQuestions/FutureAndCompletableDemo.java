package JavaQuestions;

import java.util.concurrent.*;

public class FutureAndCompletableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        Future<Object> future = Executors.newSingleThreadExecutor().submit(() -> {// future is an interface
//            TimeUnit.SECONDS.sleep(2);
//            return "Future Result"; // for example we want to fetch future fom one Future and result from another future but we can't combine it to print future result
//
//        }); /// here is asynchronous task in which one is waiting but other program will perform its task
//        while (!future.isDone()) {
//            System.out.println("still waiting");
//        }
//        System.out.println("result.............." + future.get());
//


//        >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..

        CompletableFuture<String> completableFuture = new CompletableFuture(); // completable is a class and it implements Future interface
        Executors.newSingleThreadExecutor().submit(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            completableFuture.complete("CompletableFuture Result"); //without returning we can complete task
        });

        while (!completableFuture.isDone()){
            System.out.println("still waiting");
        }
        System.out.println("completable result is  :" + completableFuture.get());
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(()-> "hello").thenApply((hello) -> hello + "world"); // it provide two or more future combining feature
//        we can do above by this
        CompletableFuture<CompletableFuture<String>> completableFuture2 = getHello().thenApply((hello) -> getWorld(hello) );//here thenApply return
        // CompletableFuture<CompletableFuture<String>>      so we use thenCompose it return CompletableFuture<String>

        CompletableFuture<String> completableFuture3 = getHello().thenCompose((e) -> getWorld(e) );// it returns result directly rather than nested
//        completableFuture result

        System.out.println(completableFuture1.get());
        System.out.println(completableFuture2.get().get());// they print the same result but it is under CompletableFuture<CompletableFuture<String>> so print hash value so we use double .get() because its nested
        System.out.println(completableFuture3.get());


    }
    private static CompletableFuture<String> getHello(){
        return CompletableFuture.supplyAsync(()-> "Hello");
    }
    private static CompletableFuture<String> getWorld(String hello){
        return CompletableFuture.supplyAsync(()->hello + "World");
    }
}
