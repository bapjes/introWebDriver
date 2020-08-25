package bIntro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Float.parseFloat;

public class OpcionCompra extends Base{
    @FindBy(css= ".ecommerce-ficha-tecnica-opciones-compra-capacidad")
    WebElement capacity;

    @FindBy (css= "[ng-if = \"productoSeleccionado.noPromotion\"]>.ecommerce-ficha-tecnica-precio")
    WebElement precio;

    @FindBy(css="header>#ecommerce-ficha-tecnica-nombre")
    WebElement name;

    @FindBy(css="#Azul")
    WebElement circulo;

    public OpcionCompra(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    public void verificarInformacionCelular(Celular telcelular)
    {
        double flt_price;
        String capacityC, nameC;
        float precioC;
        String precioE, capacityE, nameE;


     /*   try
        {
             //wait(circulo);
            wait(capacity);
            wait(precio);
           // wait(name);

            //datos de la clase
         capacityC = telcelular.getCapacidad();
            precioC = telcelular.getPrecio();
            nameC = telcelular.getName();


            capacityE = capacity.getText();
            precioE = precio.getText();
            //nameE = name.getText();

            flt_price = parseFloat((precioE.replace("$", "").replace(",","")));
            if (capacityC.equals(capacityE) && precioC == flt_price)
            {
                System.out.println("El telefono seleccionado es el correcto");
            }
         else
             System.out.println("La info no es la misma");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("No se pudo obtener la información del teléfono");
        }*/
    }

}
