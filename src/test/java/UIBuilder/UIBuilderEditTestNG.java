package UIBuilder;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UIBuilderEditTestNG {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Properties properties;

    @BeforeMethod
    public void setup() throws IOException {
        
        properties = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\sreelakshmi\\eclipse-workspace\\Assignment\\AssignmentUI\\src\\test\\java\\UIBuilder\\MyData.properties");
        properties.load(fis);  
        
        
        driver = new ChromeDriver();  
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        driver.get("https://cms.hudinielevate-stage.io/login");
    }

    @Test
    public void editUIBuilderComponents() throws InterruptedException, AWTException {
    	
    	js.executeScript("document.body.style.zoom='80%'"); 
    	
        
		WebElement Email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		WebElement Password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']")));
		WebElement Login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));

		Email.sendKeys(properties.getProperty("email"));
		Password.sendKeys(properties.getProperty("password"));
		Login.click();

		WebElement Skip = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='btn']")));
		Skip.click();
		Skip.click();
		Skip.click();
		
		
		WebElement loader = driver.findElement(By.xpath("//div[contains(@class, 'loader-container')]"));
        wait.until(ExpectedConditions.invisibilityOf(loader)); 
        

		WebElement DesConfig = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='Design Configs']")));
		js.executeScript("arguments[0].scrollIntoView(true);", DesConfig);
		DesConfig.click();
		
		WebElement AddedPage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[normalize-space()='Automation1']")));
		AddedPage.click();
		
		Actions actions = new Actions(driver);
		
		// Edit the first component 
		WebElement H2Title = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[contains(@type,'text')])[2]")));
		actions.moveToElement(H2Title).click().perform(); 
		js.executeScript("arguments[0].value = '';", H2Title); 
		H2Title.sendKeys("About Hotel");
		
		WebElement CTAInactive = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='cta_status_inactive']")));
		js.executeScript("arguments[0].scrollIntoView(true);", CTAInactive);
		actions.moveToElement(CTAInactive).click().perform();
		
		WebElement RedirectInactive = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@name='row-radio-buttons-group'])[6]")));
		js.executeScript("arguments[0].scrollIntoView(true);", RedirectInactive);
		actions.moveToElement(RedirectInactive).click().perform();

		// Edit the second component 
		WebElement H1TitleImg = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='MuiFormControl-root MuiTextField-root col-md-12']//input[@maxlength='40']")));
		js.executeScript("arguments[0].scrollIntoView(true);", H1TitleImg); 
		actions.moveToElement(H1TitleImg).click().perform();
		js.executeScript("arguments[0].value = '';", H1TitleImg);
		H1TitleImg.sendKeys("Hotel 1");
		
		// Edit the third component 
		WebElement h3TitleAcc = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//textarea[contains(@class,'MuiInputBase-input MuiInput-input')])[3]")));
		js.executeScript("arguments[0].scrollIntoView(true);", h3TitleAcc);
		actions.moveToElement(h3TitleAcc).click().perform();
		js.executeScript("arguments[0].value = '';", h3TitleAcc);
		h3TitleAcc.sendKeys("City guide 1");
		
		WebElement SaveAndDraft = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Save as Draft']")));
		js.executeScript("arguments[0].scrollIntoView(true);", SaveAndDraft); 
		actions.moveToElement(SaveAndDraft).click().perform();
		SaveAndDraft.click();
    }

    @AfterMethod
    public void close() {
        
        if (driver != null) {
            driver.quit();
        }
    }
}
