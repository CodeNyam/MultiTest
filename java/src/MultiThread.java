public class MultiThread extends Thread {
    private final int threadNumber;

    public MultiThread(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        System.out.println("🧵 Thread #" + threadNumber + " is running...");
        // 간단한 루프로 시간 지연 (시뮬레이션)
        for (int i = 1; i <= 3; i++) {
            System.out.println("Thread #" + threadNumber + " - Step " + i);
            try {
                Thread.sleep(500); // 0.5초 대기
            } catch (InterruptedException e) {
                System.out.println("Thread #" + threadNumber + " was interrupted.");
            }
        }
        System.out.println("✅ Thread #" + threadNumber + " is done.");
    }

    public static void main(String[] args) {
        // 스레드 3개 생성 및 실행
        for (int i = 1; i <= 3; i++) {
            MultiThread thread = new MultiThread(i);
            thread.start();
        }

        System.out.println("🚀 All threads started from main!");
    }
}

// 위 예제는 멀티스레드를 보여주는 예제이다.
// 인텔리제이라는 프로세스 내에서 3개의 스레드를 동작시키는 예제이다.


// Q. 스레드 작동 순서가 다른 이유가 뭘까?
// OS가 스레드를 스케줄링하여 실행 시점을 예약한다.

// 또, CPU는 자원을 공유하기에 여러 스레드 중에서 하나를 선택해 짧은 시간동안 실행한다.
// 이는 여러 스레드를 동시에 실행하는 것처럼 보이나, 사실 순차적으로 매우 빨리 번갈아가며 실행하는 것이다.
// 참고로 이는 싱글코어일 경우의 이야기이고, 멀티코어라면 병렬로 실행될 수도 있다.

