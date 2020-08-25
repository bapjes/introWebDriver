package bIntro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.lang.Float.parseFloat;

public class TelefonosSmartPhones extends Base{
    @FindBy (css=".xk-component .active")
    WebElement titulo;

    @FindBy (css = ".telcel-mosaico-equipos-con-comparador>li")
    List<WebElement> list_telephones;


    public TelefonosSmartPhones(WebDriver driver)
    {
        PageFactory.initElements(driver, this);

    }


    public void  verificarListaTelefonos(){

        try {
           wait(titulo);
           wait(list_telephones.get(0));
           System.out.println("Hay " + list_telephones.size() + " telefonos");
        }
        catch(Exception e)
        {
            System.out.println("No hay elementos en la lista ");
            System.out.println(e.getStackTrace());
         }
    }

  /*  public  Celular capturarInformacionTelefono(int i) {
        String txt_name;
        String txt_capacity;
        float flt_price;
        //Celular telefono  = new Celular();

        try {

             WebElement primerMosaico = list_telephones.get(i);


             WebElement nombreSmartphone = primerMosaico.findElement(By.cssSelector("p.telcel-mosaico-equipos-nombre-equipo"));
             WebElement capacidad = primerMosaico.findElement(By.cssSelector("span.telcel-mosaico-equipos-capacidad-numero"));
             WebElement precio = primerMosaico.findElement(By.cssSelector("div[ng-hide=\"device.promotion\"]>.telcel-mosaico-equipos-precio"));

             wait(nombreSmartphone);
             wait(capacidad);

            if (primerMosaico.isDisplayed()) {

                System.out.println("Nombre del teléfono : " + nombreSmartphone.getText());
                txt_name = nombreSmartphone.getText();
                System.out.println("Capacidad : " + capacidad.getText());
                txt_capacity = capacidad.getText();
                System.out.println("Precio : " + precio.getText().trim());
                String costo = precio.getText().trim();
                flt_price = parseFloat((costo.replace("$", "").replace(",","")));

                //asigna los valores a la clase celular
               telefono.setName(txt_name);
                telefono.setPrecio(flt_price);
                telefono.setCapacidad(txt_capacity);
                System.out.println("La información del teléfono fué capturada");
            }
        } catch(NumberFormatException ex)
        {
            System.out.println("No se pudo obtener la información");
            System.out.println(ex.getStackTrace());
            //telefono = null;
            return null;
        }
        return telefono;
    }*/

    public void clickTelefono(int telefono) throws Exception {
        scrollToWebElement(list_telephones.get(telefono));
        clickElement(list_telephones.get(telefono));
        System.out.println("Se seleccionó el teléfono ");
     }




}
