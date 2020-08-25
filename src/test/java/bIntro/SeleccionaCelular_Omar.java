package bIntro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class SeleccionaCelular_Omar {
    static WebDriver driver;
    static WebDriverWait wait;



        public static void main(String[] args) throws Exception {
            navegarPagina("https://www.telcel.com/");
            verificarHomePage();
            seleccionarTelefonosYSmartphones();
            verificarModalSeleccionaTuEstado();
            seleccionarEstado("Jalisco");
            verificarListaTelefonos();
            Celular cel = capturarInformacionTelefono(15);
            clickTelefono(15);
            verificarInformacionCelular(cel);
            cerrarBrowser();
        }

        private static void cerrarBrowser() {
            driver.quit();
        }


        private static void navegarPagina(String url) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 30);
            driver.get(url);
        }

        private static void verificarHomePage() {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-nombreboton='Tienda en linea superior']")));
            System.out.println("elemento boton encontrado: tienda en linea");
        }

        private static void seleccionarTelefonosYSmartphones() {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-nombreboton='Tienda en linea superior']"))).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul .dropdown-menu a[data-nombreboton='Telefonos y smartphones']"))).click();
            System.out.println("Si accedio a tienda en linea sup");
        }

        private static void verificarModalSeleccionaTuEstado() {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-single span")));
            System.out.println("Modal presente");
        }

        private static void seleccionarEstado(String estado) {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-single span"))).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-search input"))).sendKeys(estado);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-results li"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#entrarPerfilador"))).click();
        }

        private static void verificarListaTelefonos() {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("[ng-repeat*='devices']"), 10));

        }

        private static Celular capturarInformacionTelefono(int i) {
            List<WebElement> listaCelulares = driver.findElements(By.cssSelector("[ng-repeat*='devices']"));
            WebElement mosaicoN = listaCelulares.get(i - 1);

            WebElement nombreEquipo = mosaicoN.findElement(By.cssSelector(".telcel-mosaico-equipos-nombre-equipo"));
            String nombreEquipoCel = nombreEquipo.getText();

            WebElement elemPrecioEquipo = mosaicoN.findElement(By.cssSelector(".telcel-mosaico-equipos-precio"));
            String precioEquipo = elemPrecioEquipo.getText();
            double precio = Double.parseDouble(precioEquipo.replace("$", "").replace(",", ""));
            //.telcel-mosaico-equipos-capacidad-numero

            WebElement elemCapacidad = mosaicoN.findElement(By.cssSelector(".telcel-mosaico-equipos-capacidad-numero"));

            String capacidad = elemCapacidad.getText();
            int capac = Integer.parseInt(capacidad.split(" ")[0]);

            return new Celular(nombreEquipoCel, capac, precio);
        }

        public static void clickTelefono(int numTelefono) {
            List<WebElement> listaCelulares = driver.findElements(By.cssSelector("[ng-repeat*='devices']"));
            WebElement mosaicoN = listaCelulares.get(numTelefono - 1);
            mosaicoN.click();
        }

        public static void verificarInformacionCelular(Celular cel) throws Exception {
            //.ecommerce-ficha-tecnica-opciones-compra-titulo #ecommerce-ficha-tecnica-nombre

            //.ecommerce-ficha-tecnica-precio-pagos #ecommerce-ficha-tecnica-precio-obj


            //li[ng-repeat*='capacidades'] a

            WebElement nombreEquipo = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra-titulo #ecommerce-ficha-tecnica-nombre"));
            String nombreEquipoCel = nombreEquipo.getText();

            WebElement elemPrecioEquipo = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-precio-pagos #ecommerce-ficha-tecnica-precio-obj"));
            String precioEquipo = elemPrecioEquipo.getText();
            double precio = Double.parseDouble(precioEquipo.replace("$", "").replace(",", ""));
            //.telcel-mosaico-equipos-capacidad-numero

            WebElement elemCapacidad = driver.findElement(By.cssSelector("li[ng-repeat*='capacidades'] a"));

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
        }

    }



