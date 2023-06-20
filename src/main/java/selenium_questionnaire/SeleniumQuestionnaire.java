package selenium_questionnaire;

import org.openqa.selenium.Alert;
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
     * 模拟用户创建问卷 - 步骤A
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

        // 等待，确保警告框出现
        wait.until(ExpectedConditions.alertIsPresent());
        // 获取警告框对象
        Alert alert = driver.switchTo().alert();
        // 获取警告框的文本内容
        String alertText = alert.getText();
        System.out.println(date + "  警告框内容：" + alertText);
        // 点击警告框的“确定”按钮
        alert.accept();
        return true;
    }

    /**
     * 模拟用户创建问卷 - 步骤B
     * @param driver WebDriver对象
     * @return 是否创建成功
     */
    public boolean testCreateQuestionnaireStepB(WebDriver driver){
        // 等待页面加载完成，最长等待时间为 5 秒
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("top-content")));

        // 点击"单选"按钮，创建单选问题
        driver.findElement(By.cssSelector("body > div.container > div.top > div > div:nth-child(1)")).click();
        // 等待，确保出现单选问题编辑框
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#question0 #problemName")));
        // 编辑单选问题题目
        driver.findElement(By.cssSelector("#question0 #problemName")).sendKeys("您的性别是？");
        // 点击两次"添加选项"按钮
        driver.findElement(By.cssSelector("#question0 > div.bottom > div:nth-child(3) > button")).click();
        driver.findElement(By.cssSelector("#question0 > div.bottom > div:nth-child(3) > button")).click();
        // 填写选项0
        driver.findElement(By.cssSelector("#question0 > div.bottom > div.option > #optionItem0 > #chooseTerm")).sendKeys("男");
        // 填写选项1
        driver.findElement(By.cssSelector("#question0 > div.bottom > div.option > #optionItem1 > #chooseTerm")).sendKeys("女");
        // 填写选项2
        driver.findElement(By.cssSelector("#question0 > div.bottom > div.option > #optionItem2 > #chooseTerm")).sendKeys("保密");
        // 点击"完成编辑"，保存该单选问题
        driver.findElement(By.cssSelector("#question0 > div.bottom > div.btn-group > #editFinish")).click();

        // 点击"多选"按钮，创建多选问题
        driver.findElement(By.cssSelector("body > div.container > div.top > div > div:nth-child(2)")).click();
        // 等待，确保出现多选问题编辑框
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#question1 #problemName")));
        // 编辑多选问题题目
        driver.findElement(By.cssSelector("#question1 #problemName")).sendKeys("您会使用哪些编程语言？");
        // 点击五次"添加选项"按钮
        driver.findElement(By.cssSelector("#question1 > div.bottom > div:nth-child(3) > button")).click();
        driver.findElement(By.cssSelector("#question1 > div.bottom > div:nth-child(3) > button")).click();
        driver.findElement(By.cssSelector("#question1 > div.bottom > div:nth-child(3) > button")).click();
        driver.findElement(By.cssSelector("#question1 > div.bottom > div:nth-child(3) > button")).click();
        driver.findElement(By.cssSelector("#question1 > div.bottom > div:nth-child(3) > button")).click();
        // 填写选项0
        driver.findElement(By.cssSelector("#question1 > div.bottom > div.option > #optionItem0 > #chooseTerm")).sendKeys("C/C++");
        // 填写选项1
        driver.findElement(By.cssSelector("#question1 > div.bottom > div.option > #optionItem1 > #chooseTerm")).sendKeys("Java");
        // 填写选项2
        driver.findElement(By.cssSelector("#question1 > div.bottom > div.option > #optionItem2 > #chooseTerm")).sendKeys("JavaScript");
        // 填写选项3
        driver.findElement(By.cssSelector("#question1 > div.bottom > div.option > #optionItem3 > #chooseTerm")).sendKeys("Python");
        // 填写选项4
        driver.findElement(By.cssSelector("#question1 > div.bottom > div.option > #optionItem4 > #chooseTerm")).sendKeys("Rust");
        // 填写选项5
        driver.findElement(By.cssSelector("#question1 > div.bottom > div.option > #optionItem5 > #chooseTerm")).sendKeys("其他");
        // 点击"完成编辑"，保存该多选问题
        driver.findElement(By.cssSelector("#question1 > div.bottom > div.btn-group > #editFinish")).click();

        // 点击"填空"按钮，创建填空问题
        driver.findElement(By.cssSelector("body > div.container > div.top > div > div:nth-child(3)")).click();
        // 等待，确保出现填空问题编辑框
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#question2 #problemName")));
        // 编辑填空问题题目
        driver.findElement(By.cssSelector("#question2 #problemName")).sendKeys("您的座右铭是什么？");
        // 点击"完成编辑"，保存该填空问题
        driver.findElement(By.cssSelector("#question2 > div.bottom > div.btn-group > #editFinish")).click();

        // 点击"矩阵"按钮，创建矩阵问题
        driver.findElement(By.cssSelector("body > div.container > div.top > div > div:nth-child(4)")).click();
        // 等待，确保出现矩阵问题编辑框
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#question3 #problemName")));
        // 编辑矩阵问题题目
        driver.findElement(By.cssSelector("#question3 #problemName")).sendKeys("您是否拥有以下兴趣爱好？");
        // 编辑左标题
        driver.findElement(By.cssSelector("#question3 #leftTitle")).sendKeys("运动,摄影,旅行,音乐,阅读,游戏,其他");
        // 点击一次"添加选项"按钮
        driver.findElement(By.cssSelector("#question3 > div.bottom > div:nth-child(5) > button")).click();
        // 填写选项0
        driver.findElement(By.cssSelector("#question3 > div.bottom > div.option > #optionItem0 > #chooseTerm")).sendKeys("有");
        // 填写选项1
        driver.findElement(By.cssSelector("#question3 > div.bottom > div.option > #optionItem1 > #chooseTerm")).sendKeys("没有");
        // 点击"完成编辑"，保存该矩阵问题
        driver.findElement(By.cssSelector("#question3 > div.bottom > div.btn-group > #editFinish")).click();

        // 点击"量表"按钮，创建量表问题
        driver.findElement(By.cssSelector("body > div.container > div.top > div > div:nth-child(5)")).click();
        // 等待，确保出现量表问题编辑框
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#question4 #problemName")));
        // 编辑量表问题题目
        driver.findElement(By.cssSelector("#question4 #problemName")).sendKeys("您是否推荐朋友学习计算机专业？");
        // 点击四次"添加选项"按钮
        driver.findElement(By.cssSelector("#question4 > div.bottom > div:nth-child(3) > button")).click();
        driver.findElement(By.cssSelector("#question4 > div.bottom > div:nth-child(3) > button")).click();
        driver.findElement(By.cssSelector("#question4 > div.bottom > div:nth-child(3) > button")).click();
        driver.findElement(By.cssSelector("#question4 > div.bottom > div:nth-child(3) > button")).click();
        // 填写选项0
        driver.findElement(By.cssSelector("#question4 > div.bottom > div.option > #optionItem0 > #chooseTerm")).sendKeys("非常不推荐");
        // 填写选项1
        driver.findElement(By.cssSelector("#question4 > div.bottom > div.option > #optionItem1 > #chooseTerm")).sendKeys("不推荐");
        // 填写选项2
        driver.findElement(By.cssSelector("#question4 > div.bottom > div.option > #optionItem2 > #chooseTerm")).sendKeys("一般");
        // 填写选项3
        driver.findElement(By.cssSelector("#question4 > div.bottom > div.option > #optionItem3 > #chooseTerm")).sendKeys("推荐");
        // 填写选项4
        driver.findElement(By.cssSelector("#question4 > div.bottom > div.option > #optionItem4 > #chooseTerm")).sendKeys("非常推荐");
        // 填写分数0
        driver.findElement(By.cssSelector("#question4 > div.bottom > div.option > #optionItem0 > #fraction")).sendKeys("0");
        // 填写分数1
        driver.findElement(By.cssSelector("#question4 > div.bottom > div.option > #optionItem1 > #fraction")).sendKeys("1");
        // 填写分数2
        driver.findElement(By.cssSelector("#question4 > div.bottom > div.option > #optionItem2 > #fraction")).sendKeys("2");
        // 填写分数3
        driver.findElement(By.cssSelector("#question4 > div.bottom > div.option > #optionItem3 > #fraction")).sendKeys("3");
        // 填写分数4
        driver.findElement(By.cssSelector("#question4 > div.bottom > div.option > #optionItem4 > #fraction")).sendKeys("4");
        // 点击"完成编辑"，保存该矩阵问题
        driver.findElement(By.cssSelector("#question4 > div.bottom > div.btn-group > #editFinish")).click();

        // 点击"完成编辑"，保存本问卷
        driver.findElement(By.cssSelector("body > div.header > button.btn-success")).click();

        // 等待，确保警告框出现
        wait.until(ExpectedConditions.alertIsPresent());
        // 获取警告框对象
        Alert alert = driver.switchTo().alert();
        // 获取警告框的文本内容
        String alertText = alert.getText();
        System.out.println(new Date() + "  警告框内容：" + alertText);
        // 点击警告框的“确定”按钮
        alert.accept();
        return true;
    }
}
