package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private WebDriver driver;

    By usernameInputLocator = By.id("user-name");
    By passwordInputLocator = By.id("password");
    By loginButtonLocator = By.id("login-button");

    By titleTextLocator = By.cssSelector("span.title");
    By errorTextLocator = By.xpath("//h3[@data-test='error']");



    @Given("El usuario se encuentra en la pantalla de inicio de la pagina")
    public void el_usuario_se_encuentra_en_la_pantalla_de_inicio_de_la_pagina() {

        System.setProperty("webdriver.chrome.driver", "./src/test/java/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    @When("El usuario introduce sus credenciales correctamente")
    public void el_usuario_introduce_sus_credenciales_correctamente() {
        driver.findElement(usernameInputLocator).sendKeys("standard_user");
        driver.findElement(passwordInputLocator).sendKeys("secret_sauce");
        driver.findElement(loginButtonLocator).click();

    }
    @Then("El usuario visualiza un mensaje de login exitoso")
    public void el_usuario_visualiza_un_mensaje_de_login_exitoso() {
        WebDriverWait ewait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ewait.until(ExpectedConditions.visibilityOf(driver.findElement(titleTextLocator)));

        //assertTrue(driver.findElement(titleTextLocator).getText().contains("You logged into a secure area!"));
        assertEquals("PRODUCTS", driver.findElement(titleTextLocator).getText());
    }

    @When("El usuario introduce credenciales incorrectas")
    public void el_usuario_introduce_credenciales_incorrectas() {
        driver.findElement(usernameInputLocator).sendKeys("prueba");
        driver.findElement(passwordInputLocator).sendKeys("prueba");
        driver.findElement(loginButtonLocator).click();
    }

    @Then("El usuario visualiza un mensaje de login invalido")
    public void el_usuario_visualiza_un_mensaje_de_login_invalido() {
        WebDriverWait ewait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ewait.until(ExpectedConditions.visibilityOf(driver.findElement(errorTextLocator)));
        assertTrue("El mensaje de error no tiene el mensaje correcto", driver.findElement(errorTextLocator).getText().contains("Username and password do not match any user in this service"));
        //assertEquals("Epic sadface: Username and password do not match any user in this service", driver.findElement(errorTextLocator).getText());
    }




}
