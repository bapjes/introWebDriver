package bIntro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleccionarCelular {
    static WebDriver driver;

    //Elementos de la pagina modal
    private static WebElement select_state;
    private static List<WebElement> cities;
    private static WebElement btn_entrar;
    private static List<WebElement> list_telephones;
    private static Celular telefono  = new Celular();
    private static WebDriverWait wait;
    private static Base functions = new Base();


    public static void main(String[] args)  {
        boolean continuar;

        navegarPagina("https://www.telcel.com/");
        verificarHomePage();
        seleccionarTelefonosYSmartphones();
        verificarModalSeleccionaTuEstado();
        seleccionarEstado("Ciudad de México");
        verificarListaTelefonos();
        Celular cel = capturarInformacionTelefono(0);
       /* if (cel != null)
          clickTelefono(0);

       /* verificarInformacionCelular(cel);*/
        //salir();
    }

    private static void salir()
    {
        driver.quit();
    }

    private static void navegarPagina(String url) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(url);
    }

    private static void verificarHomePage() {
        WebElement img_login = driver.findElement(By.cssSelector("[title=\"Telcel\"]"));
        if (img_login.isDisplayed())
        {
            System.out.println("Validate Page : You are on telcel page");
        }
    }

   /* private static void espera(WebElement element)
    {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch(NoSuchElementException ex)
        {
            System.out.println(ex.getCause());
            System.out.println("Error en el elemento ");
            System.exit(-1);
        }
    }*/

    private static void seleccionarTelefonosYSmartphones() {
        WebElement lnk_tiendaenlinea = driver.findElement(By.cssSelector("[data-nombreboton=\"Tienda en linea superior\"]"));
        lnk_tiendaenlinea.click();

       // WebElement lnk_smartphone = driver.findElement(By.cssSelector("[data-nombreboton=\"Tienda en linea superior\"]+ul>.dropdown-content>div>li>[data-submenu=\"Telefonos y smartphones\"]"));
        WebElement lnk_smartphone = driver.findElement(By.cssSelector("[data-nombreboton=\"Tienda en linea superior\"]+ul a[data-submenu=\"Telefonos y smartphones\"]"));

        //functions.waitElement(lnk_smartphone);
        //espera(lnk_smartphone);
        lnk_smartphone.click();
        System.out.println("Telefonos y Smart Phones option  was selected ");
    }

    private static void verificarModalSeleccionaTuEstado() {
        select_state = driver.findElement(By.cssSelector(".chosen-single>span"));
        btn_entrar = driver.findElement(By.cssSelector("#entrarPerfilador"));

        //functions.waitElement(select_state);
        //functions.waitElement(btn_entrar);
        //espera(select_state);
        //espera(btn_entrar);

        if (select_state.isDisplayed() && btn_entrar.isDisplayed())
        {
            System.out.println("Modal window was displayed");
            select_state.click();
        }
        else {
            System.out.println("Modal window was NOT displayed");
            System.exit(-1);
        }
    }

    private static void seleccionarEstado(String estado) {
        boolean encontrado = false;

        //abre el menu
        //select_state.click();
        //selecciona la Ciudad de México
        cities = driver.findElements(By.cssSelector(".chosen-drop>ul>li"));
        //functions.waitElement(cities.get(0));
        //espera(cities.get(0));

        for(WebElement city: cities) {
            if (city.getText().equals(estado.trim())) {
                city.click();
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            System.out.println(estado + " was found and selected");
            btn_entrar.click();
        }
        else
        {
            System.out.println("Country was not selected");
            System.exit(-1);

        }
     }

   /*  public static void semuestra(WebElement element) throws InterruptedException {
         while (!isDisplayed(element))
         {
             Thread.sleep(3000);
             System.out.println("Element is not visible yet");
         }
     }*/


   /* public static boolean isDisplayed(WebElement element) {
        try {
            if(element.isDisplayed())
                return element.isDisplayed();
        }catch (NoSuchElementException ex) {
            return false;
        }
        return false;
    }*/

    private static void  verificarListaTelefonos() {

        try {

            WebElement titulo = driver.findElement(By.cssSelector(".xk-component .active"));
            //functions.waitElement(titulo);
            //espera(titulo);

            list_telephones = driver.findElements(By.cssSelector(".telcel-mosaico-equipos-con-comparador>li"));
            //functions.waitElement(list_telephones.get(0));
            //espera(list_telephones.get(0));
            //semuestra(list_telephones.get(0));
            System.out.println("Hay " + list_telephones.size() + " telefonos");
        }
        catch(Exception e)
        {
            System.out.println("No hay elementos en la lista ");
            e.getStackTrace();
            System.exit(-1);
        }
    }


    private static Celular capturarInformacionTelefono(int i) {
        String txt_name;
        String txt_capacity;
        float flt_price;

        try {
            WebElement primerMosaico = list_telephones.get(i);


            WebElement nombreSmartphone = primerMosaico.findElement(By.cssSelector("p.telcel-mosaico-equipos-nombre-equipo"));
            WebElement capacidad = primerMosaico.findElement(By.cssSelector("span.telcel-mosaico-equipos-capacidad-numero"));
            WebElement precio = primerMosaico.findElement(By.cssSelector("div[ng-hide=\"device.promotion\"]>.telcel-mosaico-equipos-precio"));

            //espera (nombreSmartphone);
            //espera (capacidad);

            if (primerMosaico.isDisplayed()) {

                System.out.println("Nombre del teléfono : " + nombreSmartphone.getText());
                txt_name = nombreSmartphone.getText();
                System.out.println("Capacidad : " + capacidad.getText());
                txt_capacity = capacidad.getText();
                System.out.println("Precio : " + precio.getText().trim());
                String p = precio.getText().trim();
                p.replace("$","");
                //flt_price =  Float.parseFloat(precio.getText().trim());
                telefono.setName(txt_name);
               // telefono.setPrecio(flt_price);
                telefono.setCapacidad(txt_capacity);
                System.out.println("La información del teléfono fué capturada");
            }
        } catch(NumberFormatException ex)
        {
            System.out.println("No se pudo obtener la información");
            System.out.println(ex.getStackTrace());
            return null;
        }
       return telefono;
    }


    public static void clickTelefono(int numTelefono) {

        list_telephones.get(numTelefono).click();

    }

    public static void verificarInformacionCelular(Celular cel) {

        String  txt_telname,txt_telcapacity;

        WebElement telname = driver.findElement(By.cssSelector("#ecommerce-ficha-tecnica-nombre"));
        txt_telname = telname.getText();
        WebElement telcapacity = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra-caracteristicas-etiqueta"));
        txt_telcapacity = telcapacity.getText();
        WebElement telprecio = driver.findElement(By.cssSelector("#ecommerce-ficha-tecnica-precio-obj"));





    }
}
