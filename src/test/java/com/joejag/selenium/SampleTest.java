package com.joejag.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

public class SampleTest extends SeleniumBase {

    @Test
    public void goToWebPage() throws Exception {
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.partialLinkText("something that isn't there"));
    }
}
