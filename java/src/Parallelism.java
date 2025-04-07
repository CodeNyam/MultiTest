import java.util.stream.IntStream;

public class Parallelism {
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 10)
                .parallel() // 병렬 스트림: 내부적으로 여러 스레드 사용
                .forEach(i -> {
                    System.out.println("작업 " + i + " 처리 중 - " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000); // 작업 수행 (지연)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }
}

// 병렬성 Parallelism
// 여러 작업을 동시에 한꺼번에 처리함 -> 동시성과 달리 이건 진짜임(물리적)
// 여러 CPU 코어가 스레드 작업을 동시에 수행 -> 동시성보다 빠름
// CPU 바운드 작업일수록 병렬처리가 효과적임