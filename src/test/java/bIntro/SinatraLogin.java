package bIntro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SinatraLogin {

    public static void main(String[] args) {
        WebDriver driver;
        //arrancar el navegador
        driver = new ChromeDriver();
        //inicializar las esperas
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //indicarle la url que se abrira.
        driver.get("https://evening-bastion-49392.herokuapp.com/");


        //verificar que exista la liga de login

        //localizar el elemento de login
        WebElement btn_login = driver.findElement(By.cssSelector("[href=\"/login\"]"));

        //preguntar si esta visible
        if (btn_login.isDisplayed())
            System.out.println("The element exists");
        else
        {
            System.out.println("The button does not exist");
            System.exit(-1);  //termina el programa
        }

        //darle click
        btn_login.click();

        //verificar que existen los campos de username, password, boton login


        //localizar los tres elementos
       // txt_user = driver.findElement(By.cssSelector("#username"));
        //txt_password = driver.findElement(By.cssSelector("#password"));

        //preguntar si estan visibles

        //ingresar los datos en campos de username y password


        //click a login

        //verificar que aparece la pagina de 'Songs'
        //encontar el header
        //encontrar la liga de create a new song
        //preguntar si estan visibles

        //verificar el mensaje de bienvenida
        //encontar el elemento del mensaje
        //preguntar si esta visible, y contiene el texto necesario
    }

}
