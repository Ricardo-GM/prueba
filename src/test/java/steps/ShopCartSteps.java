package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShopCartSteps {
    private WebDriver driver;

    By usernameInputLocator = By.id("user-name");
    By passwordInputLocator = By.id("password");
    By loginButtonLocator = By.id("login-button");
    By titlePageLocator = By.className("title");
    By shoppingCartBadgeLocator = By.cssSelector("a.shopping_cart_link");
    By itemNameLocator = By.className("inventory_item_name");

    By removeButtonLocator = By.className("cart_button");

    String nombreProducto = "";




    @Given("El usuario ha iniciado sesión correctamente")
    public void el_usuario_ha_iniciado_sesión_correctamente() {

        System.setProperty("webdriver.chrome.driver", "./src/test/java/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(usernameInputLocator).sendKeys("standard_user");
        driver.findElement(passwordInputLocator).sendKeys("secret_sauce");
        driver.findElement(loginButtonLocator).click();
    }
    @Given("El usuario se encuentra en la pantalla Inventario")
    public void el_usuario_se_encuentra_en_la_pantalla_inventario() {

        String urlActual = driver.getCurrentUrl();

        assertTrue("No se encuentra en al URl del inventario", urlActual.equals("https://www.saucedemo.com/inventory.html") );

    }
    @When("El usuario añade un producto al carrito de compras")
    public void el_usuario_añade_un_producto_al_carrito_de_compras() {
        List<WebElement> btnInventory = driver.findElements(By.className("btn_inventory"));
        List<WebElement> inventoryName = driver.findElements(By.className("inventory_item_name"));

        btnInventory.get(0).click();

        nombreProducto =  inventoryName.get(0).getText();

        WebDriverWait ewait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ewait.until(ExpectedConditions.visibilityOf(driver.findElement(shoppingCartBadgeLocator)));

        assertTrue("No hay 1 producto en el carrito", driver.findElement(shoppingCartBadgeLocator).getText().equals("1"));
        //assertEquals("1", driver.findElement(shoppingCartBadgeLocator).getText());

    }
    @Then("El usuario visualiza que el producto se añadio al carrito de compras")
    public void el_usuario_visualiza_que_el_producto_se_añadio_al_carrito_de_compras() {
        driver.findElement(shoppingCartBadgeLocator).click();

        WebDriverWait ewait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ewait.until(ExpectedConditions.visibilityOf(driver.findElement(titlePageLocator)));

        assertEquals(nombreProducto, driver.findElement(itemNameLocator).getText());

    }

    @When("El usuario remueve el producto del carrito de compras")
    public void el_usuario_remueve_el_producto_del_carrito_de_compras() {
        driver.findElement(shoppingCartBadgeLocator).click();
        WebDriverWait ewait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ewait.until(ExpectedConditions.visibilityOf(driver.findElement(titlePageLocator)));


        assertTrue("El carrito de compras ya se encuentra vacío o tiene más de 1 producto", driver.findElement(shoppingCartBadgeLocator).getText().equals("1"));

        driver.findElement(removeButtonLocator).click();

    }
    @Then("El usuario visualiza que el producto se removió del carrito de compras")
    public void el_usuario_visualiza_que_el_producto_se_removió_del_carrito_de_compras() {

        assertTrue("El carrito de compras no está vacío", driver.findElement(shoppingCartBadgeLocator).getText().equals(""));
    }




}
