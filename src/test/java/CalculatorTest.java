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

public class CalculatorTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up WebDriverManager to automatically manage ChromeDriver
        WebDriverManager.chromedriver().driverVersion("131").setup();

        // Initialize ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Optional: Runs tests in headless mode (no UI)
        driver = new ChromeDriver(options);

        // Navigate to the app (ensure the app is running at localhost:3000)
        driver.get("http://localhost:3000");
    }

    @Test
    public void testAddition() {
        // Find the input elements for the calculator (use proper selectors)
        WebElement button1 = driver.findElement(By.cssSelector("button[name = '1']"));
        WebElement buttonAdd = driver.findElement(By.cssSelector("button[name = '+']"));
        WebElement button2 = driver.findElement(By.cssSelector("button[name = '2']"));
        WebElement buttonEqual = driver.findElement(By.cssSelector("button[name = '=']"));
        WebElement resultDisplay = driver.findElement(By.cssSelector(".answer-field"));

        // Perform the addition operation: 1 + 2 = 3
        button1.click();
        buttonAdd.click();
        button2.click();
        buttonEqual.click();

        // Get the result and verify it
        String result = resultDisplay.getText();
        System.out.println("Result: " + result);
        Assert.assertEquals(result, "3", "The result of 1 + 2 should be 3");
    }

    // Additional test cases can be written here

    // After the tests are complete, close the browser
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
