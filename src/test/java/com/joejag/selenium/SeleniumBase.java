package com.joejag.selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class SeleniumBase {

    @Rule
    public FailureTestWatcher testWatcher = new FailureTestWatcher();

    protected static WebDriver driver;

    @BeforeClass
    public static void setupChrome(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedrivermac");
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void closeChrome(){
        driver.quit();
    }

    private void takeScreenshot(String fileName) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File("build/screenshot-" + fileName + ".jpg");
            Files.copy(scrFile.toPath(), destination.toPath(), REPLACE_EXISTING);

            System.out.println("=================== SCREENSHOT ========================");
            System.out.println("Saved to: " + destination.getAbsolutePath());
            System.out.println("=======================================================");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void logBrowserConsoleLogs() {
        System.out.println("================== BROWSER LOGS =======================");
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
        System.out.println("=======================================================");
    }

    public class FailureTestWatcher extends TestWatcher {

        protected void failed(Throwable e, Description description) {
            // Make the filename safe to write to disk
            String testName = description.getMethodName();
            String safeFileName = testName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
            takeScreenshot(safeFileName);

            logBrowserConsoleLogs();
        }
    }
}
