package bIntro;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleccionarCelular {
    static WebDriver driver;
    static WebDriverWait wait;
    static WebElement select_state ;
    static WebElement btn_entrar;


    public static void main(String[] args) throws Exception {
        navegarPagina("https://www.telcel.com/");
        verificarHomePage();
        seleccionarTelefonosYSmartphones();
        verificarModalSeleccionaTuEstado();
        seleccionarEstado("Ciudad");
        verificarListaTelefonos();

        int numeroCel = 6;
        Celular cel = capturarInformacionTelefono(numeroCel);
        clickTelefono(numeroCel);
        verificarInformacionCelular(cel);
        cerrarBrowser();
    }


    public boolean isItempresent (By locator)
    {
        boolean isItemPresent = driver.findElements(locator).size() > 0;
        return isItemPresent;
    }

    public static void waitF(By locator) {
       Wait < WebDriver > wait = new FluentWait < > (driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(locator));
    }


   public static void wait(By locator)
    {
        boolean isItemPresent = false;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            }
        catch(NoSuchElementException ex)
        {
            System.out.println(ex.getCause());
            System.out.println("Error en el elemento ");
        }
    }

    private static void cerrarBrowser() {
        driver.quit();

    }

    private static void navegarPagina(String url) {
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 15);
        driver.get(url);
        driver.manage().deleteAllCookies();
    }


    private static void verificarHomePage() {
        By locator = By.cssSelector("[title=\"Telcel\"]");
        WebElement element = driver.findElement(locator);
        wait(locator);
        System.out.println("Verificar HomerPage");
    }

    private static void seleccionarTelefonosYSmartphones() {

        By locator = By.cssSelector("[data-nombreboton='Tienda en linea superior']");

        WebElement lnk_tiendaenlinea = driver.findElement(locator);
        wait(locator);
        lnk_tiendaenlinea.click();

        locator = By.cssSelector("[data-nombreboton=\"Tienda en linea superior\"]+ul a[data-submenu=\"Telefonos y smartphones\"]");
        WebElement lnk_smartphone=driver.findElement(locator);
        wait(locator);
        lnk_smartphone.click();
        System.out.println("Telefonos y Smart Phones option  was selected ");
    }

    private static void verificarModalSeleccionaTuEstado() {
       By locator = By.cssSelector(".chosen-single span");
       select_state = driver.findElement(locator);
       wait(locator);

       locator = By.cssSelector("#entrarPerfilador");
       btn_entrar = driver.findElement(locator);
       wait(locator);
       System.out.println("Validación ventana modal");
    }

    private static void seleccionarEstado(String estado) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-single span"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-search input"))).sendKeys(estado);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-results li"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#entrarPerfilador"))).click();
        System.out.println("Selecciona estado");
     }

    private static void verificarListaTelefonos() {
        try {
            By locator = By.cssSelector("[ng-repeat*='devices']");
            wait(locator);

            System.out.println("Verifica lista de telefonos");
        }
        catch(Exception e)
        {
            System.out.println("Falló en verificarListaTelefonos");
            System.out.println((e.getCause()));
        }
    }

    private static Celular capturarInformacionTelefono(int i) {
       try {
           By locator = By.cssSelector("[ng-repeat*='devices']");
           wait(locator);

           List<WebElement> list_telephones = driver.findElements(locator);
           WebElement primerMosaico = list_telephones.get(i);

           locator = By.cssSelector(".telcel-mosaico-equipos-nombre-equipo");
           wait(locator);

           WebElement nombreSmartphone = primerMosaico.findElement(locator);
           WebElement capacidadSmart = primerMosaico.findElement(By.cssSelector(".telcel-mosaico-equipos-capacidad-numero"));
           WebElement precioSmart = primerMosaico.findElement(By.cssSelector(".telcel-mosaico-equipos-precio"));

           System.out.println("Nombre del teléfono : " + nombreSmartphone.getText());
           String nombre = nombreSmartphone.getText();
           System.out.println("Capacidad : " + capacidadSmart.getText());
           String capacidad = capacidadSmart.getText();
           int capac = Integer.parseInt(capacidad.split(" ")[0]);
           System.out.println("Precio : " + precioSmart.getText().trim());
           String costo = precioSmart.getText().trim();
           double price = Double.parseDouble((costo.replace("$", "").replace(",", "")));
           System.out.println("La información del teléfono fué capturada");
           Celular telefono = new Celular(nombre, capac, price);
           return telefono;
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Falló en capturarInformacionTelefono");
            return null;
        }
     }

    public static void clickTelefono(int numTelefono) {
        By locator = By.cssSelector("[ng-repeat*='devices']");
        wait(locator);

        List<WebElement> list_telephones = driver.findElements(locator);
        WebElement primerMosaico = list_telephones.get(numTelefono);

        primerMosaico.click();
        System.out.println("Se seleccionó el teléfono : " + numTelefono);
    }

    public static void verificarInformacionCelular(Celular cel) throws Exception {
        By locator = By.cssSelector("header>#ecommerce-ficha-tecnica-nombre");
        wait(locator);
        WebElement nombreEquipo = driver.findElement(locator);
        String nombreEquipoCel = nombreEquipo.getText();

        locator =By.cssSelector(".ecommerce-ficha-tecnica-precio-pagos #ecommerce-ficha-tecnica-precio-obj");
        wait(locator);
        WebElement elemPrecioEquipo = driver.findElement(locator);
        String precioEquipo = elemPrecioEquipo.getText();
        double precio = Double.parseDouble(precioEquipo.replace("$", "").replace(",", ""));

        locator = By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra-capacidad");
        wait(locator);
        WebElement elemCapacidad = driver.findElement(locator);
        String capacidad = elemCapacidad.getText();
        int capac = Integer.parseInt(capacidad.split(" ")[0]);

        if(cel.getNombre().equals(nombreEquipoCel)
                && cel.getPrecio() == precio
                && cel.getCapacidad() == capac) {
            System.out.println("Los datos coinciden");
        } else {
            System.out.println("Los datos  no coinciden");
            throw new Exception("Los datos  no coinciden");
        }
        cel = null;
    }
}

