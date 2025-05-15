package marconiryan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Objects;

public class FakeCameraTest {
    public static void main(String[] args) throws URISyntaxException {
        WebDriver driver = initializeFakeCameraDriver();
        driver.get("https://webrtc.github.io/samples/src/content/devices/input-output/");

        try {
            Thread.sleep(2 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    private static WebDriver initializeFakeCameraDriver() throws URISyntaxException {
        String videoPath = Objects.requireNonNull(BotRunner.class.getResource("/video.y4m")).toURI().getPath();
        String audioPath = Objects.requireNonNull(BotRunner.class.getResource("/audio.wav")).toURI().getPath();

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

