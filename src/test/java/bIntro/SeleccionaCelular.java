package bIntro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SeleccionaCelular {
    public static void main(String[] args) throws Exception {

        TiendaenLinea tl = null;
        WebDriver  driver;


        driver = Base.loadPage("https://www.telcel.com/");

        tl = new TiendaenLinea(driver);
        tl.verificarHomePage();

        tl.seleccionarTelefonosYSmartphones();
        tl.verificarModalSeleccionaTuEstado();
        tl.seleccionarEstado("Ciudad de México");

        TelefonosSmartPhones sm = new TelefonosSmartPhones(driver);
        sm.verificarListaTelefonos();
        Celular cel = sm.capturarInformacionTelefono(0);
        if (cel != null)
          sm.clickTelefono(0);

        OpcionCompra compra = new OpcionCompra(driver);
        compra.verificarInformacionCelular(cel);
        //salir();
    }
}
