package bIntro;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class IMDB {
    static WebDriver driver;
    static WebDriverWait wait;


    public static void main(String[] args) {
        String url = "https://www.imdb.com/";
        entrarPagina(url);
        signIn();
        crearCuenta();
        meterDatos();
    }


    private static void meterDatos() {
        By name = By.cssSelector("#ap_customer_name");
        By email = By.cssSelector("#ap_email");
        By password = By.cssSelector("#ap_password");
        By passwordc = By.cssSelector("#ap_password_check");
        By create = By.cssSelector("#continue");

        meterTexto(name,"Beatriz");
        meterTexto(email,"sharetbap@hotmail.com");
        meterTexto(password,"cray67bap");
        meterTexto(passwordc,"crazy67bap");

        clickElement(create);

        WebElement wb = driver.findElement(By.name("phone"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].value='(222)222-2222';", wb);
        jse.executeScript("document.getElementById('ssn').value='555-55-5555';");
    }

    private static void meterTexto(By locator,String texto) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(texto.trim());
    }

    private static void crearCuenta() {
        By cuentaNueva = By.cssSelector(".create-account");
        clickElement(cuentaNueva);
    }

    public static void waitElementvisible(By locator)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void  clickElement(By locator)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private static void signIn() {
        By signinb = By.cssSelector(".imdb-header__signin-text .ipc-button__text");
        clickElement(signinb);
    }

    private static void entrarPagina(String url) {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,15);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().deleteAllCookies();
    }
}
