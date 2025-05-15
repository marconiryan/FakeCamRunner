package marconiryan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FakeCameraTest {
    public static void main(String[] args) {
        WebDriver driver = initializeFakeCameraDriver();
        driver.get("https://webrtc.github.io/samples/src/content/devices/input-output/");

        try {
            Thread.sleep(2 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    private static WebDriver initializeFakeCameraDriver() {
        String videoPath = "/home/null/IdeaProjects/FakeCamRunner/src/main/resources/video.y4m";
        String audioPath = "/home/null/IdeaProjects/FakeCamRunner/src/main/resources/audio.wav";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--use-fake-device-for-media-stream");
        options.addArguments("--use-file-for-fake-video-capture=" + videoPath);
        options.addArguments("--use-file-for-fake-audio-capture=" + audioPath);
        options.addArguments("--autoplay-policy=no-user-gesture-required");
        options.addArguments("--window-size=800,600");
        options.addArguments("--no-sandbox");

        return new ChromeDriver(options);
    }
}

