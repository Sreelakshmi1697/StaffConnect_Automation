package UIBuilder;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

public class UIBuilderWithTestNG {
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
    public void testUIBuilder() throws InterruptedException, AWTException {
        
        js.executeScript("document.body.style.zoom='80%'"); 

        
        WebElement Email = driver.findElement(By.xpath("//input[@id='email']"));
        WebElement Password = driver.findElement(By.xpath("//input[@id='password']"));
        WebElement Login = driver.findElement(By.xpath("//button[@type='submit']"));
        Email.sendKeys(properties.getProperty("email"));
        Password.sendKeys(properties.getProperty("password"));
        Login.click();
       
        WebElement Skip = driver.findElement(By.xpath("//img[@alt='btn']"));
        Skip.click();
        Skip.click();
        Skip.click();

        Thread.sleep(5000);
        
        
        WebElement DesConfig = driver.findElement(By.xpath("//div[@title='Design Configs']"));
        wait.until(ExpectedConditions.visibilityOf(DesConfig));
        wait.until(ExpectedConditions.elementToBeClickable(DesConfig));
        DesConfig.click();
        
        // Find the Add page button and create a page
        WebElement AddPage = driver.findElement(By.xpath("//span[normalize-space()='Add Page']"));
        AddPage.click();
        
        WebElement PageName = driver.findElement(By.xpath("//textarea[@id='name_input']"));
        PageName.sendKeys("Automation1");
        
        WebElement SavePage = driver.findElement(By.xpath("//button[@type='submit']"));
        SavePage.click();
        
        // Select the added page 
        WebElement AddedPage = driver.findElement(By.xpath("//td[normalize-space()='Automation1']"));
        AssertJUnit.assertTrue(AddedPage.isDisplayed());
        AddedPage.click();
        
        // Select the component 1= 1D-Small Hero  and drag and drop the modules
        WebElement Add = driver.findElement(By.xpath("//button[@type='submit']"));
        Add.click();
        
        WebElement Banners = driver.findElement(By.xpath("//div[contains(text(),'Banners')]"));
        Banners.click();
        
        WebElement Component1 = driver.findElement(By.xpath("//img[contains(@alt,'1D - Small Hero, H2, CTA')]"));
        WebElement destination = driver.findElement(By.xpath("//div[@class='droppable page-builder-space']"));
        
        wait.until(ExpectedConditions.elementToBeClickable(Component1));
        AssertJUnit.assertTrue(Component1.isDisplayed());
        js.executeScript("arguments[0].scrollIntoView(true);", Component1);
        
        Actions actions = new Actions(driver);
        
        actions.dragAndDrop(Component1, destination).perform();
        
        // Add the details to the first component 
        WebElement H2Title = driver.findElement(By.xpath("(//input[contains(@type,'text')])[2]"));
        H2Title.sendKeys("Home Page");
        
        WebElement ImageRadioButton = driver.findElement(By.xpath("//input[@value='image']"));
        js.executeScript("arguments[0].scrollIntoView(true);", ImageRadioButton);
        
        Thread.sleep(5000);
        WebElement ImageIcon = driver.findElement(By.xpath("(//div[contains(@class, 'img-drop-zone')]//div[contains(@class, 'img-select')])[1]"));
        ImageIcon.click();
        
        Thread.sleep(3000);
        uploadFileUsingRobot(properties.getProperty("uploadPath1"));
        
        WebElement CTACtive = driver.findElement(By.xpath("//input[@value='cta_status_active']"));
        CTACtive.click();
        
        WebElement CTAValue = driver.findElement(By.xpath("(//input[@id='cta_title_input'])[1]"));
        CTAValue.sendKeys("Book Now");
            
        WebElement RedirectActive = driver.findElement(By.xpath("//input[@value='redirect_status_active']"));
        js.executeScript("arguments[0].scrollIntoView(true);", RedirectActive);
        RedirectActive.click();
        
        WebElement ExtActive = driver.findElement(By.xpath("//input[@value='EXTERNAL']"));
        ExtActive.click();
        
        WebElement RedirectValue = driver.findElement(By.xpath("(//textarea[@id='external_link_input'])[1]"));
        js.executeScript("arguments[0].scrollIntoView(true);", RedirectValue);
        RedirectValue.sendKeys(properties.getProperty("redirectUrl"));
        
        // Select the component 2= 3-DBL IMG and drag and drop 
        WebElement ImageComp = driver.findElement(By.xpath("//div[contains(text(),'Image Components')]"));
        wait.until(ExpectedConditions.elementToBeClickable(ImageComp));
        
        js.executeScript("arguments[0].scrollIntoView(true);", ImageComp);
        ImageComp.click();
        
        WebElement Component2 = driver.findElement(By.xpath("//img[contains(@alt,'3 - DBL IMG w/Title, Body')]"));
        wait.until(ExpectedConditions.visibilityOf(Component2));
        
        AssertJUnit.assertTrue(Component2.isDisplayed());
        
        js.executeScript("arguments[0].scrollIntoView(true);", Component2);
        
        WebElement destination1 = driver.findElement(By.xpath("//div[@class='droppable page-builder-space']"));
        wait.until(ExpectedConditions.visibilityOf(destination1));
        
        Actions actions1 = new Actions(driver);
        actions1.dragAndDrop(Component2, destination1).perform();
        
        WebElement H1TitleImg = driver.findElement(By.xpath("(//input[@type='text'])[4]"));
        js.executeScript("arguments[0].scrollIntoView(true);", H1TitleImg); 
        H1TitleImg.sendKeys("Compendium 1");
        
        WebElement Body1 = driver.findElement(By.cssSelector("div[class='row mb-2 mx-4'] textarea:nth-child(1)"));
        Body1.sendKeys("Hotel compendium- Highliights of your hotel");
        
        WebElement H3TitleImg = driver.findElement(By.xpath("(//input[@type='text'])[5]"));
        js.executeScript("arguments[0].scrollIntoView(true);", H3TitleImg); 
        H3TitleImg.sendKeys("Compendium 1");
        
        Thread.sleep(5000);
        WebElement imageDropimg = driver.findElement(By.xpath("(//div[contains(@class, 'img-drop-zone')]//div[contains(@class, 'img-select')])[2]"));
        js.executeScript("arguments[0].scrollIntoView(true);", imageDropimg);
        imageDropimg.click();
         
        Thread.sleep(3000);
        uploadFileUsingRobot(properties.getProperty("uploadPath2"));
         
        // Add 2nd image of the component 2
        WebElement AddImage = driver.findElement(By.xpath("(//span[normalize-space()='Image 2'])[1]"));
        AddImage.click();
        
        WebElement H3TitleImg2 = driver.findElement(By.xpath("(//input[@type='text'])[5]"));
        js.executeScript("arguments[0].scrollIntoView(true);", H3TitleImg2); 
        H3TitleImg2.sendKeys("Compendium 2");
        
        Thread.sleep(5000);
        WebElement imageDropimg2 = driver.findElement(By.xpath("(//div[contains(@class, 'img-drop-zone')]//div[contains(@class, 'img-select')])[2]"));
        js.executeScript("arguments[0].scrollIntoView(true);", imageDropimg2);
        imageDropimg2.click();
         
        Thread.sleep(3000);
        uploadFileUsingRobot(properties.getProperty("uploadPath2"));  

        // Add the component 3= 9B-Accordion
        WebElement Accordian = driver.findElement(By.xpath("//div[contains(text(),'Accordions')]"));
        wait.until(ExpectedConditions.elementToBeClickable(Accordian));
        js.executeScript("arguments[0].scrollIntoView(true);", Accordian);
        Accordian.click();
        
        WebElement Component3 = driver.findElement(By.xpath("//div[contains(text(),'9B - Accordion w/o Icon')]"));
        wait.until(ExpectedConditions.visibilityOf(Component3));
        
        AssertJUnit.assertTrue(Component3.isDisplayed());
        js.executeScript("arguments[0].scrollIntoView(true);", Component3);
        
        actions1.dragAndDrop(Component3, destination1).perform();
        
        WebElement H3TitleAcc = driver.findElement(By.xpath("(//input[@type='text'])[7]"));
        js.executeScript("arguments[0].scrollIntoView(true);", H3TitleAcc); 
        H3TitleAcc.sendKeys("Accordian 1");
        
        WebElement BodyAcc = driver.findElement(By.cssSelector("div[class='MuiFormControl-root MuiTextField-root col-md-12 mt-3'] textarea:nth-child(1)"));
        BodyAcc.sendKeys("Accordian - Highliights of your hotel");
            
        WebElement SavePublish = driver.findElement(By.xpath("//span[normalize-space()='Save & Publish']"));
        AssertJUnit.assertTrue(SavePublish.isDisplayed());
        js.executeScript("arguments[0].scrollIntoView(true);", SavePublish); 
        SavePublish.click();
   
    }

    private static void uploadFileUsingRobot(String filePath) throws AWTException, InterruptedException {
        
        StringSelection filePathSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathSelection, null);

        // Create a Robot instance
        Robot robot = new Robot();

        // Press CTRL + V (paste the file path)
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Press Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Pause for a while to allow the system to process the file upload
        Thread.sleep(2000);
    }
}
