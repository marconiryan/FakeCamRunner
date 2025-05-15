package marconiryan;

public class Main {
    public static void main(String[] args) {
        String roomUrl = "https://collab.work.optidata.cloud/rooms/9ee9fec7-39d9-4072-8e3d-owt1-5e18ab8883b0";
        int botCount = 10;

        for (int i = 0; i < botCount; i++) {
            Thread t = new Thread(new BotRunner(i, roomUrl));
            t.start();
        }
    }
}