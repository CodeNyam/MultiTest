public class VirtualThread {
    public static void main(String[] args) {
        // 버츄얼 스레드 10개 생성
        for (int i = 0; i < 10; i++) {
            Thread.startVirtualThread(() -> {
                System.out.println("Hello from virtual thread: " + Thread.currentThread());
                try {
                    Thread.sleep(1000); // 1초 대기
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 메인 스레드 종료 대기 (실제로는 Thread.join 등을 사용해서 기다리는 게 안전함)
        try {
            Thread.sleep(2000); // 2초 대기
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Main thread ends.");
    }
}

//Hello from virtual thread: VirtualThread[#24]/runnable@ForkJoinPool-1-worker-3
//Hello from virtual thread: VirtualThread[#26]/runnable@ForkJoinPool-1-worker-5

// 버츄얼 스레드?
// java 21버전부터 정식으로 추가된 경량 스레드
// 기존의 플랫폼 스레드와 다르게, OS 스레드에 직접 매핑되지 않고, JVM이 관리하는 경량 스레드
// 즉, 수천 개 이상 생성해도 부담이 적다!

// Q. 플랫폼 스레드 = 스레드?
// Yes!
// 기존에 new Thread()로 만들었던 것은 모두 플랫폼 스레드
// 내부적으로 OS가 제공하는 스레드와 1대1로 매핑되어 사용

// 스레드 매핑이 무슨 말?
// 기존의 스레드 제작 방식
// 1. new Thread()로 스레드 생성
// 2. JVM이 코드에 따라서 OS에서 스레드 요청
// 3. OS가 커널 수준의 스레드를 제작하고, JVM은 내 코드를 돌려줌 -> Runnable 상태
// 스레드가 OS에서 관리되어 context switching 비용이 크다.

// 왜 버츄얼 스레드일까?
// JVM 내부에서만 돌아가는 가짜 스레드이기 때문!
// 실제로 JVM이 하나의 플랫폼 스레드를 재사용하여 수 천개의 스레드처럼 보이는 것임.
// 필요할때만 플랫폼 스레드 위에서 실행되고, 안쓸때는 쉼

// 장점
// 1. 수십만 개 스레드를 동시 작업 가능
// 2. 메모리 사용량 적음
// 블로킹 I/O 작업에서 효율적

// 단점
// 1. CPU 바운드 작업에선 불리
// 2. native 함수에서 블로킹되면, JVM이 감지못할 수 있음
// 3. 기존 라이브러리와 호환성 문제

// native 함수?
// 자바가 아닌 외부 언어로 구현된 함수
// JNI Java Native Interface를 연결하여 호출함
// OS기능 호출, C언어에서 구현된 최적화 코드, 오래된 라이브러리와 연동 시 사용
// 버츄얼 스레드는 비교적 최근 도입된 개념이다.
// native 함수가 호출되면, 버츄얼 스레드가 블록킹될 수 있다.
// 이때 JVM이 감지하지 못해 성능 저하가 일어난다.

// CPU 바운드?
// CPU에서 연산을 사용하는 작업
// 예시로, 대규모 수학 연산, 머신러닝 연산, 이미지 렌더링 등이 있다.
// 버츄얼 스레드는 짧고 많이 분산된 작업에 강해서 CPU를 오래 점유하는 연산에 불리하다.

// Remind. 커널 영역
// OS 핵심 기능이 돌아가는 곳(파일 시스템, 메모리 관리, 스케줄러 등)
// 보호된 영역으로, 사용자가 직접 접근하지 않음.
// 여기서 Os를 호출하여 스레드를 제작
// 이후 이 스레드를 CPU에 언제 올릴지, 컨텍스트 스위칭 등을 고려함


// 버츄얼 스레드는 다음에 적용시키면 좋다.
// 1. API 코드
// 2. 1000명의 유저를 동시에 조회하는 경우
// 3. 수 천개의 사이트를 크롤링





