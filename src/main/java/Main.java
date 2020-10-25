import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\Code\\Java\\AvitoPrinter\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://avito.ru");
        driver.manage().window().maximize();

        WebElement categories = driver.findElement(By.id("category"));
        categories.click();

        Select category = new Select(driver.findElement(By.id("category")));
        category.selectByVisibleText("Оргтехника и расходники");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement search = driver.findElement(By.id("search"));
        search.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        search.sendKeys("Принтер");
        search.sendKeys(Keys.ENTER);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //нажать на выпадающий список городов

        WebElement regions = driver.findElement(By.xpath("//div[@data-marker='search-form/region']/div"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        regions.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement inputRegion = driver.findElement(By.xpath("//input[@data-marker='popup-location/region/input']"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        inputRegion.sendKeys("Владивосток");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement city = driver.findElement(By.xpath("//ul[@data-marker='suggest-list']/li[1]"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        city.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement vlad = driver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']"));
        vlad.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



        WebElement checkbox = driver.findElement(By.xpath("//div[@data-marker='delivery-filter/container']/label/span"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if(!checkbox.isSelected()){
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            checkbox.click();
        }


        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement buttonWithDeliv = driver.findElement(By.xpath("//button[@data-marker='search-filters/submit-button']"));
        buttonWithDeliv.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement sortingBy = driver.findElement(By.xpath("//div[@class='form-select-v2 sort-select-3QxXG']/select"));
        sortingBy.click();

        Select selSort = new Select(driver.findElement(By.xpath("//div[@class='form-select-v2 sort-select-3QxXG']/select")));
        selSort.selectByVisibleText("Дороже");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> printers = driver.findElements(By.xpath("//div[@class='snippet-list']/div[@data-marker='item']"));

        //System.out.println(printers.size());

        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", printers.get(i));
            System.out.println(printers.get(i).findElement(By.xpath(".//div[@class='description item_table-description']//span")).getText());
            System.out.println(printers.get(i).findElement(By.xpath(".//div[@class='snippet-price-row']/span[@itemprop='offers']")).getText() + "\n");
        }
        
        driver.quit();
    }
}
