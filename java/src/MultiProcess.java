import java.io.IOException;

public class MultiProcess {
    public static void main(String[] args) {
        try {
            // 첫 번째 사이트: Google
            ProcessBuilder google = new ProcessBuilder("open", "https://www.google.com");
            google.start();
            System.out.println("✅ Google 열기 완료");

            // 두 번째 사이트: Naver
            ProcessBuilder naver = new ProcessBuilder("open", "https://www.naver.com");
            naver.start();
            System.out.println("✅ Naver 열기 완료");


            // 프로세스들이 독립적으로 동작함

        } catch (IOException e) {
            System.err.println("❌ 프로세스 실행 중 오류 발생");
            e.printStackTrace();
        }
    }
}

// 크롬을 통해 구글과 네이버 2개 사이트를 동시에 여는 것도 멀티 프로세스의 일종이다.
// 즉, 메모리에 구글을 이루는 프로세스들, 네이버를 이루는 프로세스들끼리 영역을 침범하지 않고 존재한다.