import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class CalculatorTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up WebDriverManager
        WebDriverManager.chromedriver().driverVersion("131").setup();

        // Initialize ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        driver.get("http://localhost:3000");
    }

    @Test
    public void testAdditionDynamic() {
        System.out.println("Inside testAdditionDynamic");
        Random random = new Random();
        int number1 = random.nextInt(100);
        int number2 = random.nextInt(100);
        System.out.println("number1: " + number1);
        System.out.println("number2: " + number2);

        WebElement buttonAdd = driver.findElement(By.cssSelector("button[name = '+']"));
        WebElement buttonEqual = driver.findElement(By.cssSelector("button[name = '=']"));
        WebElement resultDisplay = driver.findElement(By.cssSelector(".answer-field"));

        // Perform the addition operation
        clickNumber(number1);
        buttonAdd.click();
        clickNumber(number2);
        buttonEqual.click();

        // Get the result and verify the assertion
        String result = resultDisplay.getText();
        System.out.println("Result: " + result);
        String total = String.valueOf(number1 + number2);
        Assert.assertEquals(result, total, "The result should be " + total);
    }

    @Test
    public void testDivisionByZero() {
        System.out.println("Inside testDivisionByZero");

        Random random = new Random();
        int number1 = random.nextInt(100);
        int number2 = 0;
        System.out.println("number1: " + number1);
        System.out.println("number2: " + number2);

        WebElement buttonDivision = driver.findElement(By.cssSelector("button[name = '/']"));
        WebElement buttonEqual = driver.findElement(By.cssSelector("button[name = '=']"));
        WebElement resultDisplay = driver.findElement(By.cssSelector(".answer-field"));

        // Perform the addition operation
        clickNumber(number1);
        buttonDivision.click();
        clickNumber(number2);
        buttonEqual.click();

        // Get the result and verify the assertion
        String result = resultDisplay.getText();
        System.out.println("Result: " + result);
        Assert.assertEquals(result, "Infinity", "Can not be divisible by zero");
    }

    // Helper method to click a number dynamically
    private void clickNumber(int number) {

        String numStr = String.valueOf(number);

        for (char digit : numStr.toCharArray()) {
            WebElement digitButton = driver.findElement(By.cssSelector("button[name = '" + digit + "']"));
            digitButton.click();
        }
    }

    // After the tests are complete, close the browser
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}