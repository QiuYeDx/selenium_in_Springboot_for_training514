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
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
//        // 初始化Chrome浏览器的WebDriver
        //设置驱动
        System.setProperty("webdriver.chrome.driver","/Users/baizihan/Documents/学习空间/Java/selenium_test/src/main/resources/chromedriver");
//
        //创建驱动
        WebDriver driver = new ChromeDriver(option);
//
//        //与将要爬取的网站建立连接
//        driver.get("https://www.baidu.com/");
////        driver.get("http://127.0.0.1:8085/pages/login/index.html");
//        // 执行操作和断言
//        // ...
//
//        List<WebElement> elements = driver.findElements(By.className("hotsearch-item"));
//        for (WebElement element : elements) {
//            System.out.println(element.getText());
//        }
//
//        // 关闭浏览器
//        driver.quit();

        // 最大化浏览器
        driver.manage().window().maximize();
        // 打开百度
        driver.get("https://www.baidu.com/");
        // 定位搜索框并输入搜索项
        driver.findElement(By.xpath("//*[@id=\"kw\"]")).sendKeys("Vue Components");
        // 点击搜索结果链接
        driver.findElement(By.xpath("//*[@id=\"su\"]")).click();
        try {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
