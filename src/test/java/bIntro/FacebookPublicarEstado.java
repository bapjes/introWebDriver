package bIntro;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class FacebookPublicarEstado {

    static WebDriver driver;

    public static void main(String[] args) {
        navegarFacebook();
        verificarHomePage();
        iniciarSesion(Constants.USUARIO, Constants.PASSWORD);
        verificarPaginaPrincipal();
        publicarEstado();
        verificarEstado();
    }



    private static void navegarFacebook() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com");
    }

    private static void verificarHomePage() {
        //logoFacebook css ".fb_logo"
        WebElement logoFacebook = driver.findElement(By.cssSelector(".fb_logo"));
        //campoUsuario css "#email"
        WebElement campoUsuario = driver.findElement(By.cssSelector("#email"));
        //campoContrasena css "#pass"
        WebElement campoContrasena = driver.findElement(By.cssSelector("#pass"));
        //botonIniciarSesion  css "[value='Iniciar sesión']"
        WebElement botonIniciarSesion = driver.findElement(By.cssSelector("[data-testid='royal_login_button']"));

        if(logoFacebook.isDisplayed()
                && campoUsuario.isDisplayed()
                && campoContrasena.isDisplayed()
                && botonIniciarSesion.isDisplayed()) {
            System.out.println("La pagina de Home cargo correctamente");
        }
        else {
            System.out.println("La pagina de Home no cargo correctamente");
            System.exit(-1);
        }
    }

    private static void iniciarSesion(String usuario, String password) {
        //campoUsuario css "#email"
        WebElement campoUsuario = driver.findElement(By.cssSelector("#email"));
        campoUsuario.sendKeys(usuario);
        //campoContrasena css "#pass"
        WebElement campoContrasena = driver.findElement(By.cssSelector("#pass"));
        campoContrasena.sendKeys(password);
        //botonIniciarSesion  css "[value='Iniciar sesión']"
        WebElement botonIniciarSesion = driver.findElement(By.cssSelector("[data-testid='royal_login_button']"));
        botonIniciarSesion.click();
    }

    private static void verificarPaginaPrincipal() {
        //iconoBusqueda css "[placeholder*='Facebook']"

        //quePasa css "h3 + a + div"

        //campoQuePasa css "form[method='POST'] .q5bimw55"

        //botonPublicar css "form[method='POST'] .oajrlxb2"
        /*List<WebElement> elementitos = driver.findElements(By.cssSelector("form[method='POST'] .oajrlxb2"));
        int numeroElementitos = elementitos.size();
        WebElement publicar = elementitos.get(numeroElementitos - 1);*/

    }



    private static void publicarEstado() {
    }

    private static void verificarEstado() {
    }
}
