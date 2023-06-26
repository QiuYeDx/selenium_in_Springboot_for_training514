package selenium_test;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {

    @Test
    public void testAutomation() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        // 初始化配置Chrome浏览器的WebDriver
        System.setProperty("webdriver.chrome.driver","C:\\Users\\26501\\Desktop\\selenium_in_Springboot_for_training514-main\\src\\main\\resources\\chromedriver");
        // 创建驱动
        WebDriver driver = new ChromeDriver(option);
        // 最大化浏览器
        driver.manage().window().maximize();
        // 与将要爬取的网站建立连接
        driver.get("https://www.baidu.com/");

        // 爬取热门搜索信息 并输出至后端控制台
        List<WebElement> elements = driver.findElements(By.className("hotsearch-item"));
        for (WebElement element : elements) {
            System.out.println(element.getText());
        }

        // 定位搜索框并输入搜索项
        driver.findElement(By.xpath("//*[@id=\"kw\"]")).sendKeys("Vue Components");
        // 点击搜索结果链接
        driver.findElement(By.xpath("//*[@id=\"su\"]")).click();

        try {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            Thread.sleep(3000);
            // 关闭浏览器
            driver.quit();
        } catch (InterruptedException e) {
            e.printStackTrace();
            // 关闭浏览器
            driver.quit();
        }
    }
}
