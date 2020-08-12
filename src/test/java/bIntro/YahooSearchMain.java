package bIntro;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class YahooSearchMain {
	
	

	public static void main(String[] args) {
		//INICIALIZACION DE SYSTEM.SETPROPERTY()
	    WebDriver driver;
		driver = new ChromeDriver();  //arranca el navegador, obvio Chrome
		driver.manage().timeouts().implicitlyWait(30,  TimeUnit.SECONDS); //configura las esperas implicitas hasta 30 segundos
		driver.get("http://www.yahoo.com"); //navega a la url que le indiquemos

		//obtiene los elementos de busqueda y de boton de busqueda
		WebElement searchBox = driver.findElement(By.id("header-search-input"));
		WebElement searchButton = driver.findElement(By.id("header-desktop-search-button"));
		
		searchBox.clear();  //limpia la caja de texto
		searchBox.sendKeys("Selenium");  //envia el texto selenium a la caja de texto
		searchButton.click();  // da click en el boton de busqueda

		//encontro un elemento con la palabra selenium
		WebElement seleniumLink = driver.findElement(By.xpath("//h3[.='Selenium' and ./following-sibling::div[contains(.,'selenium.dev')] ] /a"));
		seleniumLink.click();  //da click al elemento
		
		//se obtienen el numero de ventanas
		System.out.println("Number of windows: " + driver.getWindowHandles().size());

		//driver.getWindowHandles() = lista de ventanas
		//busca las ventanas abiertas
		for(String windowId: driver.getWindowHandles()) {
			driver.switchTo().window(windowId);
		}

		//busca el elemento con un link text downloads
		WebElement downloadLink = driver.findElement(By.linkText("Downloads"));
		downloadLink.click();  //da click al elemento
		
		driver.quit();  // cierra el conjunto de todas las ventanas abiertas

	}

}
