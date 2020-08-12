package bIntro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SinatraLoginMethods {
    static WebDriver driver;
    private static WebElement btn_login;
    private static WebElement txt_user;
    private static WebElement txt_password;
    private static WebElement btn_loginp;
    private static WebElement lbl_song;
    private static WebElement lbl_logged;
    private static WebElement lnk_link;

    private static String str_user;
    private static String str_password;

    public static void main(String[] args) throws InterruptedException {
           String url = "https://evening-bastion-49392.herokuapp.com/";
           str_user = "frank";
           str_password = "sinatra";

           navegarPagina(url);
           verificarPaginaHome();
           clickLogin();
           verificarPaginaLogin();
           ingresarCredenciales();
           verificarPaginaSongs();
           verificarMensajeBienvenida();
           salir();
    }

    private static void salir() {

        driver.quit();
    }

    private static void navegarPagina(String url) {
         //arrancar el navegador
        System.setProperty("webdriver.chrome.driver", "C://Librerias64//chromedriver.exe");
        driver = new ChromeDriver();
        //inicializar las esperas
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //indicarle la url que se abrira.
        driver.get(url);
        System.out.println("Navigate to Sinatra page");
    }

    private static void verificarPaginaHome() {
        btn_login = driver.findElement(By.cssSelector("[href=\"/login\"]"));
        if (btn_login.isDisplayed())
            System.out.println("The button LOGIN exists on Home Page");
        else
        {
            System.out.println("The button does not exist on Home Page");
            System.exit(-1);  //termina el programa
        }

    }

    private static void clickLogin() {
         btn_login.click();
    }

    private static void verificarPaginaLogin() {
        txt_user = driver.findElement(By.cssSelector("#username"));
        txt_password = driver.findElement(By.cssSelector("#password"));
        btn_loginp = driver.findElement(By.cssSelector("[value='Log In']"));

        if (txt_user.isDisplayed() && txt_password.isDisplayed())
            System.out.println("User and Password fields exist");
    }


    private static void ingresarCredenciales() {
        txt_user.sendKeys(str_user);
        txt_password.sendKeys(str_password);
        btn_loginp.submit();
    }

    private static void verificarPaginaSongs() {
        try {
            //label songs
            lbl_song = driver.findElement(By.cssSelector("section>h1"));
            //Link create a new song
            lnk_link = driver.findElement(By.cssSelector("section>a"));
            //label of message
            lbl_logged = driver.findElement(By.cssSelector(".flash"));
            if (lbl_logged.isDisplayed() && lnk_link.isDisplayed()) {
                System.out.println("You are on : Songs by Sinatra page");
            }
        }
        catch(Exception e)
        {
            lbl_logged = driver.findElement(By.cssSelector(".flash"));
            if (lbl_logged.isDisplayed()) {
                System.out.println("You are not logged : " + lbl_logged.getText());
                salir();
                System.exit(-1);
            }
        }
    }


    private static void verificarMensajeBienvenida() {
      if (lbl_logged.getText().equals("You are now logged in as " + str_user.trim()))
        {
            System.out.println("You are now logged in as : " + str_user.trim());
        }
        else
            System.out.println("You are NOT logged in");
    }
}
