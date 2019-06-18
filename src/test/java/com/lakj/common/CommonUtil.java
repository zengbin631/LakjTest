package com.lakj.common;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsRegistryException;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * @author Administrator
 *
 */
public class CommonUtil {

	/**
	 * 系统属性的初始化
	 */
	public static void BrowserInit(){
		System.setProperty("webdriver.chrome.driver", "F:\\development_tools\\workspace\\eclipse_workspace\\LakjTest\\src\\test\\resources\\chromedriver.exe");
		try {
			WindowsUtils.tryToKillByName("chromedriver.exe");
		} catch (WindowsRegistryException e) {
			System.out.println("没有可以关闭的chrom浏览器");
		}
	}
	/**
	 * 定义WebDriver并初始化
	 * @return
	 */
	public static WebDriver getChromeDriver(){
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	/**
	 * 获取元素的属性值
	 * @param driver
	 * @param selector
	 * @param str
	 * @return
	 */
	public static String getAttr(WebDriver driver,By selector,String str){
		return getElement(driver,selector).getAttribute(str);
	}

	/**
	 * 获取文本信息
	 * @param driver
	 * @param selector
	 * @return String
	 */
	public static String getText(WebDriver driver,By selector){
		return getElement(driver,selector).getText();
	}

	/**
	 * 操作元素的点击
	 * @param driver
	 * @param selector
	 */
	public static void click(WebDriver driver,By selector ){
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        CommonUtil.sleep(1000);
		getElement(driver,selector).click();
	}

//	public static void Actions_click(WebDriver driver,By selector){
//		WebElement element=getElement(driver,selector);
//		new Actions(driver).click(element).build().perform();
//	}
	/**
	 * 右键点击元素
	 * @param driver
	 * @param selector
	 */
	public static void contextClick(WebDriver driver,By selector){
		WebElement element=getElement(driver,selector);
		new Actions(driver).contextClick(element).build().perform();
	}

	/**
	 * 为元素赋值
	 * @param driver
	 * @param selector
	 * @param input
	 */
	public static void sendKey(WebDriver driver,By selector,String input){
		getElement(driver,selector).clear();
		getElement(driver,selector).sendKeys(input);
	}

	/**
	 * 获取WelEment对象
	 * @param driver
	 * @param selector
	 * @return
	 */
	public static WebElement getElement(WebDriver driver,By selector){
		try{
			WebElement element=driver.findElement(selector);
			return element;
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail("页面元素没有找到");
		}
		return null;
	}
	/**
	 * 获取WelEment对象集合
	 * @param driver
	 * @param selector
	 * @return
	 */
	public static List<WebElement> getElements(WebDriver driver,By selector){
		try{
			List<WebElement> elements=driver.findElements(selector);
			return elements;
		}catch (NoSuchElementException e) {
			return null;
		}
	}

	/**
	 * 线程等待
	 * @param millis
	 */
	public static void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 判断元素是否存在
	 * @param driver
	 * @param selector
	 * @return
	 */
	public static boolean isExistElement(WebDriver driver,By selector){
		try {
			driver.findElement(selector);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
    /**
     * 判断元素是否被选择
     * @param driver
     * @param selector
     * @return
     */
	public static boolean isSelectElement(WebDriver driver,By selector){
	    return getElement(driver,selector).isSelected();
    }
}
