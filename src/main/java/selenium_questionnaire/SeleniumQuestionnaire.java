package selenium_questionnaire;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumQuestionnaire {
    String API_BASE_URL = "http://127.0.0.1:8085";

    public void setAPI_BASE_URL(String API_BASE_URL) {
        this.API_BASE_URL = API_BASE_URL;
    }

    /**
     * 初始化WebDriver
     * @param url_driver 驱动的本地路径
     * @return WebDriver对象
     */
    public WebDriver initWebDriver(String url_driver){
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        // 初始化配置Chrome浏览器的WebDriver
        System.setProperty("webdriver.chrome.driver", url_driver.length() > 0 ? url_driver : "/Users/baizihan/Documents/学习空间/Java/selenium_test/src/main/resources/chromedriver");
        // 创建驱动
        WebDriver driver = new ChromeDriver(option);
        // 最大化浏览器
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * 关闭浏览器
     * @param driver WebDriver对象
     * @return 是否关闭成功
     */
    public boolean closeWebDriver(WebDriver driver){
        // 关闭浏览器
        driver.quit();
        return true;
    }

    public boolean testLogin(WebDriver driver){
        // 与将要爬取的网站建立连接
        driver.get(API_BASE_URL + "/pages/login/index.html");
        // 在username输入框中输入测试用户名
        driver.findElement(By.id("username")).sendKeys("Selenium");
        // 在password输入框中输入测试密码
        driver.findElement(By.id("password")).sendKeys("123456789");
        // 点击登录按钮
        driver.findElement(By.className("btn-primary")).click();
        return true;
    }

    public boolean testCreateQuestionnaireStepA(WebDriver driver){
        // 跳转到项目展示首页
        driver.get(API_BASE_URL + "/pages/questionnaire/index.html");
        // 等待页面加载完成，最长等待时间为 5 秒
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement btn_createQuestionnaire = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#content > div > div.list-header > div:nth-child(2) > button:nth-child(1)")));
        btn_createQuestionnaire.click();
//        driver.findElement((By.cssSelector("#content > div > div.list-header > div:nth-child(2) > button:nth-child(1)"))).click();
        driver.findElement(By.cssSelector("#option_project_name")).click();
        driver.findElement(By.cssSelector("#selectLeo2 > option:nth-child(2)")).click();
        return true;
    }
}
