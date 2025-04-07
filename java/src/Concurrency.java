import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Concurrency {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3); // 스레드 3개로 동시성 처리

        for (int i = 1; i <= 10; i++) {
            final int taskNumber = i;
            executor.submit(() -> {
                System.out.println("작업 " + taskNumber + " 시작 - " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // 작업 수행 (지연)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("작업 " + taskNumber + " 종료 - " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }
}

// 동시성 Concurrency
// 여러 작업이 동시에 일어나는 것처럼 보이는 처리 능력
// 순차적으로 번갈아가며 실행
// 실제로 한 코어에서 작업을 번갈아가며 수행할 수 있음

//










