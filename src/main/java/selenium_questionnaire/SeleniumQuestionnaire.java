package selenium_questionnaire;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Date;

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

    /**
     * 模拟用户登录
     * @param driver WebDriver对象
     * @return 是否登录成功
     */
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

    /**
     * 模拟用户创建问卷
     * @param driver WebDriver对象
     * @return 是否创建成功
     */
    public boolean testCreateQuestionnaireStepA(WebDriver driver){
        // 跳转到项目展示首页
        driver.get(API_BASE_URL + "/pages/questionnaire/index.html");
        // 等待页面加载完成，最长等待时间为 5 秒
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("list-header")));

        // 点击"创建问卷"按钮
        driver.findElement((By.cssSelector("#content > div > div.list-header > div:nth-child(2) > button:nth-child(1)"))).click();
        // 等待页面加载完成，最长等待时间为 5 秒
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        // 选择所属项目为当前项目
        driver.findElement(By.cssSelector("#option_project_name")).click();
        // 选择"学生"调查类型
        driver.findElement(By.cssSelector("#selectLeo2 > option:nth-child(2)")).click();
        // 点击空白模板的"创建"
        driver.findElement(By.cssSelector("body > div.container > div:nth-child(3) > div.template > div:nth-child(1) > div.item-b > button")).click();

        Date date = new Date();
        // 等待页面加载完成，最长等待时间为 5 秒
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        // 填写调查问卷名称
        driver.findElement(By.cssSelector("#surveyName")).sendKeys("Selenium测试问卷 - " + date);
        // 填写调查问卷说明
        driver.findElement(By.cssSelector("#surveyDescription")).sendKeys("用于Selenium测试，创建时间为：" + date);
        // 选择开始时间
        driver.findElement(By.cssSelector("#startTime > span")).click();
        driver.findElement(By.cssSelector("body > div:nth-child(7) > div.datetimepicker-days > table > tbody > tr:nth-child(1) > td:nth-child(1)")).click();
        // 选择结束时间
        driver.findElement(By.cssSelector("#endTime > span")).click();
        driver.findElement(By.cssSelector("body > div:nth-child(8) > div.datetimepicker-days > table > tbody > tr:nth-child(6) > td:nth-child(7)")).click();
        // 点击"立即创建"按钮
        driver.findElement(By.cssSelector("body > div.container > form > div:nth-child(4) > div > button")).click();
        return true;
    }
}
