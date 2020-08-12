package bIntro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TiendaenLinea extends Base {

    @FindBy(css="[title=\"Telcel\"]")
    WebElement img_login;

    @FindBy(css= "[data-nombreboton=\"Tienda en linea superior\"]")
    WebElement lnk_tiendaenlinea;

    @FindBy(css="[data-nombreboton=\"Tienda en linea superior\"]+ul a[data-submenu=\"Telefonos y smartphones\"]")
    WebElement lnk_smartphone;

    @FindBy(css=".chosen-single>span")
    WebElement select_state;

    @FindBy(css="#entrarPerfilador")
    WebElement btn_entrar ;

    @FindBy(css=".chosen-drop>ul>li")
    List<WebElement> cities;

    public TiendaenLinea(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    public void verificarHomePage() {
        wait(img_login);
        System.out.println("Verificar HomerPage");
    }

    public void seleccionarTelefonosYSmartphones() {
        clickElement(lnk_tiendaenlinea);
        wait(lnk_smartphone);
        clickElement(lnk_smartphone );
        System.out.println("Telefonos y Smart Phones option  was selected ");
    }


    public void verificarModalSeleccionaTuEstado() {
        try {
            wait(select_state);
            wait(btn_entrar);
            System.out.println("Validación ventana modal");
        }
        catch(Exception e)
        {
            System.out.println(e.getCause());
            System.out.println("Failure : Elements for state selection were not displayed");
        }
    }

    public void seleccionarEstado(String estado) {
        boolean encontrado = false;

        clickElement(select_state);

        try {
            wait(cities.get(0));
            scrollToWebElement(cities.get(0));

            for (WebElement city : cities) {
                if (city.getText().equals(estado.trim())) {
                    clickElement(city);
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {
                System.out.println(estado + " was found and selected");
                btn_entrar.click();
            } else {
                System.out.println("Country was not found ");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getCause());
            System.out.println("Failure : Country was not selected");
        }
    }
}
