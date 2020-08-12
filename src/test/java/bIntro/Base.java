package bIntro;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Base {
    private static WebDriverWait wait;
    private static WebDriver driver;

    public  static WebDriver loadPage(String url)
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
        return driver;
    }


    public void wait(WebElement element)
    {
        try {
                 wait.until(ExpectedConditions.visibilityOf(element));

                 //wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        }
        catch(NoSuchElementException ex)
        {
            //System.out.println(ex.getCause());
            System.out.println("Error en el elemento ");
         }
    }




    public void scrollToWebElement(WebElement webElement) throws Exception {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
          } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickElement(WebElement element)
    {
        element.click();
    }

    public void closePage()
    {
        driver.quit();
        wait = null;
    }
}
