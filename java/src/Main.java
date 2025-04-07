public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Hello from a new thread!");
        });
        thread.start();

        System.out.println("Hello from the main thread!");
    }
}
