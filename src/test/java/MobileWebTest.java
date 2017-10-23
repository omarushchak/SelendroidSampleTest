import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import io.selendroid.standalone.SelendroidConfiguration;
import io.selendroid.standalone.SelendroidLauncher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class MobileWebTest {
    private SelendroidLauncher selendroidServer = null;
    private WebDriver driver = null;
    private final String EMAIL_INPUT_XPATH = "//*[@id=\"identifierId\"]";
    private final String EMAIL_BUTTON_XPATH = "//*[@id=\"identifierNext\"]/content";
    private final String PASSWORD_INPUT_XPATH = "//*[@id=\"password\"]/div[1]/div/div[1]/input";
    private final String PASSWORD_BUTTON_ID = "passwordNext";

    @Before
    public void startSelendroidServer() throws Exception {
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
        SelendroidConfiguration config = new SelendroidConfiguration();

        selendroidServer = new SelendroidLauncher(config);
        selendroidServer.launchSelendroid();

        DesiredCapabilities caps = SelendroidCapabilities.android();

        driver = new SelendroidDriver(caps);
    }

    @Test
    public void gmailTest() {
        driver.get("http://gmail.com");
        WebElement loginInput = driver.findElement(By.xpath(EMAIL_INPUT_XPATH));
        WebElement loginButton = driver.findElement(By.xpath(EMAIL_BUTTON_XPATH));
        loginInput.sendKeys("olegmarushchak.test1@gmail.com");
        loginButton.click();

        WebElement passwordInput = driver.findElement(By.xpath(PASSWORD_INPUT_XPATH));
        WebElement passwordButton = driver.findElement(By.id(PASSWORD_BUTTON_ID));
        passwordInput.sendKeys("1234567890qwerty");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        passwordButton.click();
        driver.quit();
    }


    @After
    public void stopSelendroidServer() {
        if (driver != null) {
            driver.quit();
        }
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
    }
}