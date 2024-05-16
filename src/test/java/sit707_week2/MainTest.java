package sit707_week2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/bharatbhatt/Desktop/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.bunnings.com.au/login");
    }

    @Test
    public void testValidLogin() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.id("okta-signin-username")); // Email locator
        WebElement passwordField = driver.findElement(By.id("okta-signin-password")); // Password locator
        WebElement loginButton = driver.findElement(By.id("okta-signin-submit")); // Login locator

        usernameField.sendKeys("bharatbhatt232@gmail.com");
        passwordField.sendKeys("Bharatbhatt1!");
        loginButton.click();
        Thread.sleep(3000);

        String expectedUrl = "https://www.bunnings.com.au/"; // Update with actual URL
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void testInvalidUsername() throws InterruptedException{
        WebElement usernameField = driver.findElement(By.id("okta-signin-username"));
        WebElement passwordField = driver.findElement(By.id("okta-signin-password"));
        WebElement loginButton = driver.findElement(By.id("okta-signin-submit"));

        usernameField.sendKeys("Albert@gmail.com");
        passwordField.sendKeys("Bharatbhatt1");
        loginButton.click();
        Thread.sleep(3000);

        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"retailLogin\"]/div[1]/div[1]/div/div/p")); // Update with correct locator
        Assert.assertTrue(errorMessage.isDisplayed());
    }

    @Test
    public void testInvalidPassword() throws InterruptedException{
        WebElement usernameField = driver.findElement(By.id("okta-signin-username"));
        WebElement passwordField = driver.findElement(By.id("okta-signin-password"));
        WebElement loginButton = driver.findElement(By.id("okta-signin-submit"));

        usernameField.sendKeys("bharatbhatt232@gmail.com");
        passwordField.sendKeys("hello123");
        loginButton.click();
        Thread.sleep(3000);

        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"retailLogin\"]/div[1]/div[1]/div/div/p"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }
    
    @Test
    public void testBlankPassword() throws InterruptedException {
        WebElement usernameField = driver.findElement(By.id("okta-signin-username"));
        WebElement passwordField = driver.findElement(By.id("okta-signin-password"));
        WebElement loginButton = driver.findElement(By.id("okta-signin-submit"));

        usernameField.sendKeys("bharatbhatt232@gmail.com");
        passwordField.sendKeys("");
        loginButton.click();
        Thread.sleep(3000);

        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"retailLogin\"]/div[1]/div[1]/div/div/p"));
        Assert.assertTrue(errorMessage.isDisplayed());
    }

}
