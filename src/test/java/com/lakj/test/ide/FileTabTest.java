package com.lakj.test.ide;

import com.lakj.common.CommonUtil;
import com.lakj.page.ide.PageSource;
import com.lakj.task.ide.Operation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class FileTabTest {
	WebDriver driver;
	@BeforeTest
	public void beforeTest() {
		CommonUtil.BrowserInit();
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = CommonUtil.getChromeDriver();
		Operation.getHome(driver);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	/*
	 * 1.新增welcom.cpp文件，点击tab部分未被激活的文件名(hello.cpp),hello.cpp被激活
	 * 2.点击list部分未被激活的文件，该文件被激活
	 */
	@Test
	public void acFileTabTest() {
		String inputFileName = "welcome.cpp";
		CommonUtil.click(driver, PageSource.Btn_AddFile);
		CommonUtil.sendKey(driver, PageSource.Input_FileName, inputFileName);
		CommonUtil.click(driver, PageSource.Btn_Confirm);
		CommonUtil.sleep(2000);
		String acString=CommonUtil.getAttr(driver, PageSource.Tab_ActiveFile, "title");
		Assert.assertTrue(inputFileName.equals(acString));
		List<WebElement> elements=CommonUtil.getElements(driver, PageSource.Tab_File);
		for(WebElement element:elements){
			String string=element.getAttribute("title");
			if((!string.equals(""))&&(!acString.equals(string))){
				element.click();
				Assert.assertTrue(string.equals(CommonUtil.getAttr(driver, PageSource.Tab_ActiveFile, "title")));
				Assert.assertTrue(string.equals(CommonUtil.getAttr(driver, PageSource.List_ActiveFile, "title")));
			}
		}
		acString=CommonUtil.getAttr(driver, PageSource.List_ActiveFile, "title");
		elements=CommonUtil.getElements(driver, PageSource.List_File);
		for(WebElement element:elements){
			String string=element.findElement(PageSource.BQ_P).getAttribute("title");
			if((!string.equals(""))&&(!acString.equals(string))){
				element.click();
				Assert.assertTrue(string.equals(CommonUtil.getAttr(driver, PageSource.Tab_ActiveFile, "title")));
				Assert.assertTrue(string.equals(CommonUtil.getAttr(driver, PageSource.List_ActiveFile, "title")));
			}
		}
	}
	/*
	 * 1.新增welcome.cpp文件，在filetab处删除该文件
	 * 2.判断filetab是否成功删除，删除后filetab激活文件为hello.cpp
	 * 3.判断filelist是否仍然包含welcome.cpp文件，激活文件为hello.cpp文件
	 */
	@Test
	public void delFileTabTest() {
		String defaultFileName="hello.cpp";
		String inputFileName = "welcome.cpp";
		//新增welcome.cpp文件
		CommonUtil.click(driver, PageSource.Btn_AddFile);
		CommonUtil.sendKey(driver, PageSource.Input_FileName, inputFileName);
		CommonUtil.click(driver, PageSource.Btn_Confirm);
		CommonUtil.sleep(2000);
		Assert.assertTrue(inputFileName.equals(CommonUtil.getAttr(driver, PageSource.Tab_ActiveFile, "title")));
		//删除filetab的welcome.cpp文件
		CommonUtil.click(driver,PageSource.Del_Tab_ActiveFile);
		List<String> list=Operation.getTabFile(driver);
		//判断filetab是否成功删除
		Assert.assertFalse(list.contains(inputFileName));
		//判断filetab的激活文件为默认文件（hello.cpp）
		Assert.assertTrue(defaultFileName.equals(CommonUtil.getAttr(driver, PageSource.Tab_ActiveFile, "title")));
		//判断filelist的激活文件未默认文件（hello.cpp）
		Assert.assertTrue(defaultFileName.equals(CommonUtil.getAttr(driver, PageSource.List_ActiveFile, "title")));
		list=Operation.getListFile(driver);
		//判断filelist是否包含welcome.cpp文件
		Assert.assertTrue(list.contains(inputFileName));
	}
	/*
	 * 1.查看默认version是否为EOS
	 * 2.点击BOS选项，查看显示
	 * 3.点击EOS选项，查看显示
	 */
	@Test
	public void switchVersion() {
		//判断默认版本显示为EOS
		Assert.assertTrue("eos".equals(CommonUtil.getAttr(driver,PageSource.Input_Version,"value")));
		CommonUtil.click(driver,PageSource.Input_Version);
		//下拉显示包括EOS、BOS
		Assert.assertTrue(CommonUtil.isExistElement(driver,PageSource.Btn_EOS));
		Assert.assertTrue(CommonUtil.isExistElement(driver,PageSource.Btn_BOS));
		//点击BOS按钮
		CommonUtil.click(driver,PageSource.Btn_BOS);
		Assert.assertTrue("bos".equals(CommonUtil.getAttr(driver,PageSource.Input_Version,"value")));
		//点击EOS按钮
		CommonUtil.click(driver,PageSource.Input_Version);
		CommonUtil.click(driver,PageSource.Btn_EOS);
		Assert.assertTrue("eos".equals(CommonUtil.getAttr(driver,PageSource.Input_Version,"value")));
	}
}
