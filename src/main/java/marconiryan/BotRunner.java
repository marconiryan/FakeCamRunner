package marconiryan;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BotRunner implements Runnable {
    private final int index;
    private final String roomUrl;

    public BotRunner(int index, String roomUrl) {
        this.index = index;
        this.roomUrl = roomUrl;
    }

    @Override
    public void run() {
        WebDriver driver = null;
        try {
            String botName = "Bot" + (index + 1);

            driver = initializeWebDriver();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[data-testid='join-room-username-input']")
            ));
            nameInput.sendKeys(botName);

            WebElement joinButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[data-testid='join-room-submit-button']")
            ));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", joinButton);

            Thread.sleep(1000);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", joinButton);

            System.out.println("[" + botName + "] Entrou na sala!");

            Thread.sleep(10 * 60 * 1000); // 10 minutos

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private WebDriver initializeWebDriver() {
        String videoPath = "/home/null/Documents/git/FakeCamRunner/src/main/resources/video.y4m";
        String audioPath = "/home/null/Documents/git/FakeCamRunner/src/main/resources/audio.wav";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--use-fake-device-for-media-stream");
        options.addArguments("--use-file-for-fake-video-capture=" + videoPath);
        options.addArguments("--use-file-for-fake-audio-capture=" + audioPath);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-setuid-sandbox");
        options.addArguments("--autoplay-policy=no-user-gesture-required");
        options.addArguments("--window-size=800,600");
        options.addArguments(String.format("--window-position=%d,%d", index * 50, index * 50));

        WebDriver driver = new ChromeDriver(options);
        driver.get(roomUrl);
        return driver;
    }
}
