package UIBuilder;

import java.awt.AWTException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIBuilderEditing {

    public static void main(String[] args) throws InterruptedException, AWTException {
        
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        driver.get("https://cms.hudinielevate-stage.io/login");
        driver.manage().window().maximize();
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='80%'"); 
        
        //Enter the credentials and login
        WebElement Email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
        WebElement Password = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement Login = driver.findElement(By.xpath("//button[@type='submit']"));
        Email.sendKeys("sreelakshmi.cv@hudini.io");
        Password.sendKeys("Sree@123");
        Login.click();
        
        // Skip the screens
        WebElement Skip = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='btn']")));
        Skip.click();
        Skip.click();
        Skip.click();
        
        Thread.sleep(5000);
        
        // Find the Design config module and select it 
        WebElement DesConfig = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='Design Configs']")));
        js.executeScript("arguments[0].scrollIntoView(true);", DesConfig);
        wait.until(ExpectedConditions.visibilityOf(DesConfig));
        wait.until(ExpectedConditions.elementToBeClickable(DesConfig));
        DesConfig.click();
        
        WebElement AddedPage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[normalize-space()='Automation1']")));
        AddedPage.click();
        
        // Edit the first component 
        Actions actions = new Actions(driver);
        WebElement H2Title=driver.findElement(By.xpath("(//input[contains(@type,'text')])[2]"));
        actions.moveToElement(H2Title).click().perform(); 
        js.executeScript("arguments[0].value = '';", H2Title); // Then clear the input
        H2Title.sendKeys("About Hotel");
        
        
        WebElement CTAInactive = driver.findElement(By.xpath("(//input[@value='cta_status_inactive'])[1]"));
        js.executeScript("arguments[0].scrollIntoView(true);", CTAInactive);
        actions.moveToElement(CTAInactive).click().perform();
        
        WebElement RedirectInactive = driver.findElement(By.xpath("(//input[@name='row-radio-buttons-group'])[6]"));
        js.executeScript("arguments[0].scrollIntoView(true);", RedirectInactive);
        actions.moveToElement(RedirectInactive).click().perform();

        
        // Edit the second component 
        WebElement H1TitleImg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiFormControl-root MuiTextField-root col-md-12']//input[@maxlength='40']")));
        js.executeScript("arguments[0].scrollIntoView(true);", H1TitleImg); 
        wait.until(ExpectedConditions.elementToBeClickable(H1TitleImg));
        actions.moveToElement(H1TitleImg).click().perform();
        js.executeScript("arguments[0].value = '';", H1TitleImg);
        H1TitleImg.sendKeys("Hotel 1");
      
        
        // Edit the third component 
        WebElement h3TitleAcc = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//textarea[contains(@class,'MuiInputBase-input MuiInput-input')])[3]")));
        js.executeScript("arguments[0].scrollIntoView(true);", h3TitleAcc);
        wait.until(ExpectedConditions.elementToBeClickable(h3TitleAcc));
        actions.moveToElement(h3TitleAcc).click().perform();
        js.executeScript("arguments[0].value = '';", h3TitleAcc);
        h3TitleAcc.sendKeys("City guide 1");
        
        WebElement SaveAndDraft = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[normalize-space()='Save as Draft'])[1]")));
        js.executeScript("arguments[0].scrollIntoView(true);", SaveAndDraft); 
        actions.moveToElement(SaveAndDraft).click().perform();
        SaveAndDraft.click();
    }
}
