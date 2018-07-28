import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;


public class Training {

    public static WebDriver driver;
    public static JavascriptExecutor js;
    public static Alert alert;
    public static WebDriverWait wait;
    public static Actions actions;




    @BeforeTest
    public void start() {
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized", "--ignore-certificate-errors");
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.merge(capability);
        options.setCapability("acceptSslCerts", true);
        driver = new ChromeDriver(options);
        driver.manage().window().fullscreen();
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver,5);
        System.out.print(((HasCapabilities)driver).getCapabilities());
        System.out.print("New");
        System.out.print("New");
    }



    @Test
    public void startTimer() throws InterruptedException {

        driver.get("http://qa.jpstyle.us/form/all-action-types");
        driver.findElement(By.xpath("//*[@href=\"http://qa.jpstyle.us/content/example-execute-action\"]")).click();
        String CodeText = wait.withMessage("Element was not found").until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'function setTime()')]"))).getText();
        driver.navigate().back();
        js.executeScript(CodeText);
        Thread.sleep(1000);
        String value1 = driver.findElement(By.id("seconds")).getText();
        int seconds = Integer.parseInt(value1);
        assertTrue("Timer didn't start!",seconds > 0);


    }

    @Test
    public  void study() {
        driver.get("https://formy-project.herokuapp.com/switch-window");
        String originalHandle = driver.getWindowHandle();
        WebElement newTabButton = driver.findElement(By.id("new-tab-button"));
        newTabButton.click();
        driver.switchTo().window(originalHandle);
    }

    @Test
    public void study2() {

        driver.get("https://formy-project.herokuapp.com/switch-window");

        WebElement alertButton = driver.findElement(By.id("alert-button"));
        alertButton.click();
        alert = driver.switchTo().alert();
        alert.accept();

    }







    @Test
    public void study3() {

        driver.get("https://formy-project.herokuapp.com/modal");
        WebElement modalButton = driver.findElement(By.id("modal-button"));
        modalButton.click();
        WebElement closeButton = driver.findElement(By.id("close-button"));
        js.executeScript("arguments[0].click();",closeButton);
    }




    @Test
    public void study4() {

        driver.get("https://formy-project.herokuapp.com/scroll");
        WebElement name = driver.findElement(By.id("name"));
        actions.moveToElement(name);
        name.sendKeys("KRASAVA");
        name.sendKeys("KRASAVA");

    }









    @AfterTest
    public void stop() {

        driver.quit();


    }


}






