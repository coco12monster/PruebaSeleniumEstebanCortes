package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import utilities.BaseTests;

public class PruebaTest extends BaseTests {


    @Test
    public void accederMercadoTest() {
        String url = "https://www.mercadolibre.com";
        driver.get(url);
        sleep(2000);

    }

    @Test
    public void seleccionarMexicoTest() {
        accederMercadoTest();
        var mexico = driver.findElement(By.id("MX"));
        mexico.click();
        sleep(2000);
        var aceptarCoockies = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[2]/button[1]"));
        aceptarCoockies.click();
        sleep(2000);
    }

    @Test
    public void buscarMercadoTest() {
        seleccionarMexicoTest();
        var busqueda = driver.findElement(By.className("nav-search-input"));
        var btnBuscar = driver.findElement(By.className("nav-search-btn"));
        busqueda.sendKeys("Playstation 5");
        btnBuscar.click();
        sleep(2000);
    }

    @Test
    public void filtrarContenidoTest() {
        buscarMercadoTest();
        sleep(2000);
        var filtroNuevo = driver.findElement(By.xpath("//*[@id='root-app']/div/div[2]/aside/section[2]/div[5]/ul/li[1]/a/span[1]"));
        filtroNuevo.click();

    }

    @Test
    public void filtroLocationTest() {
        filtrarContenidoTest();
        sleep(2000);
        var filtroOrigenLocal = driver.findElement(By.xpath("//*[@id='root-app']/div/div[2]/aside/section[2]/div[11]/ul/li[1]/a/span[1]"));
        new Actions(driver).scrollToElement(filtroOrigenLocal);
        sleep(2000);
        filtroOrigenLocal.click();
        sleep(2000);

    }

    @Test
    public void filtroMenorPrecioTest() {
        filtroLocationTest();
        var btnMasRelevantes = driver.findElement(By.className("andes-dropdown__trigger"));
        btnMasRelevantes.click();
        var btnMenorPrecio = driver.findElement(By.xpath("//*[@id=':R1b55ie:-menu-list-option-price_asc']/div/div/span"));
        btnMenorPrecio.click();

    }

    @Test
    public void nombreProductosdTest() {
        filtroMenorPrecioTest();
        sleep(2000);
        var primerLink = driver.findElement(By.xpath("//*[@id='root-app']/div/div[2]/section/div[5]/ol/li[1]/div/div/div/div[2]/h3/a")).getText();
        var segundoLink = driver.findElement(By.xpath("//*[@id='root-app']/div/div[2]/section/div[5]/ol/li[2]/div/div/div/div[2]/h3/a")).getText();
        var tercerLink = driver.findElement(By.xpath("//*[@id='root-app']/div/div[2]/section/div[5]/ol/li[3]/div/div/div/div[2]/h3/a")).getText();
        var cuartoLink = driver.findElement(By.xpath("//*[@id='root-app']/div/div[2]/section/div[5]/ol/li[4]/div/div/div/div[2]/h3/a")).getText();
        var quintoLink = driver.findElement(By.xpath("//*[@id='root-app']/div/div[2]/section/div[5]/ol/li[5]/div/div/div/div[2]/h3/a")).getText();
        System.out.printf("Primer link: " + primerLink + "/%n");
        System.out.printf("Segundo link: " + segundoLink + "%n");
        System.out.printf("Tercer link: " + tercerLink + "%n");
        System.out.printf("Cuarto link: " + cuartoLink + "%n");
        System.out.printf("Quinto link: " + quintoLink + "%n");
        
    }
}
