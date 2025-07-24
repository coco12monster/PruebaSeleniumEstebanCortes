package utilities;

import listeners.SuiteListeners;
import listeners.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;


@Listeners({TestListeners.class, SuiteListeners.class})
public class BaseTests {
    protected SoftAssert softAssert = new SoftAssert();
    protected final String regression = "regression";
    protected final String smoke = "smoke";
    protected WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {
        softAssert = new SoftAssert();

        Logs.debug("Iniciando el driver");
        driver = new ChromeDriver();

        Logs.debug("maxixmizando la pantalla");
        driver.manage().window().maximize();

        Logs.debug("borrando las coockies");
        driver.manage().deleteAllCookies();


        new WebDriverProvider().set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void masterTearDown() {
        Logs.debug("Matando el driver");
        driver.quit();
    }

    protected void sleep(int timeMs) {
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException interruptedException) {
            Logs.error("InterruptedException: %s", interruptedException.getLocalizedMessage());
        }
    }


}
