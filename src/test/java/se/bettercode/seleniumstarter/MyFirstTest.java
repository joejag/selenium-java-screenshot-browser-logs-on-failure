package se.bettercode.seleniumstarter;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class MyFirstTest {

    @Test
    public void goToWebPage() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/joseph.wright/projects/scratch/selenium-starter/chromedrivermac");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");

        driver.quit();
    }

}
