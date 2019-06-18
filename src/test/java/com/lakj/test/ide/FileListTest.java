package com.lakj.test.ide;

import com.lakj.common.CommonUtil;
import com.lakj.page.ide.PageSource;
import com.lakj.task.ide.Operation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class FileListTest {
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

	/* DEFAULT
	 * 1.首次打开界面filelist默认显示为hello。cpp文件;
	 * 2.filetab默认展开hello。cpp文件
	 */
	@Test
	public void defaultTest() {
		Assert.assertTrue("hello.cpp".equals(CommonUtil.getAttr(driver, PageSource.List_ActiveFile, "title")));
		Assert.assertTrue("hello.cpp".equals(CommonUtil.getAttr(driver, PageSource.Tab_ActiveFile, "title")));

	}

	/* ADDFILE
	 * 1.点击"+"新增文件,弹出文件名录入框,名称默认为空;
	 * 2.文件名称为空时,点击Confirm,弹出提示"Please enter a file name.";
	 * 3。录入filelist已经存在的文件名,点击Confirm,弹出提示"File name already exists.";
	 * 4.录入正确的文件名,点击Confirm,查看filelist和filetab展示
	 * 5。录入正确的文件名,点击Cancel,查看filelist展示
	 */
	@Test
	public void addFileTest1() {
		String message = "Please enter a file name.";
		String error = "File name already exists.";
		String inputFileName = "welcome.cpp";
		CommonUtil.click(driver, PageSource.Btn_AddFile);
		String fileName = CommonUtil.getAttr(driver, PageSource.Input_FileName, "value");
		Assert.assertTrue("".equals(fileName));
		CommonUtil.sleep(2000);
		// 文件名为空校验
		CommonUtil.click(driver, PageSource.Btn_Confirm);
		Assert.assertTrue(message.equals(Operation.getMessage(driver, "info")));
		// 录入已经存在的文件名
		CommonUtil.sendKey(driver, PageSource.Input_FileName, "hello.cpp");
		CommonUtil.click(driver, PageSource.Btn_Confirm);
		CommonUtil.sleep(1000);
		Assert.assertTrue(error.equals(Operation.getMessage(driver, "error")));
		// 录入正确的文件名,点击Confirm
		CommonUtil.sendKey(driver, PageSource.Input_FileName, inputFileName);
		CommonUtil.click(driver, PageSource.Btn_Confirm);
		Assert.assertTrue(inputFileName.equals(CommonUtil.getAttr(driver, PageSource.List_ActiveFile, "title")));
		Assert.assertTrue(inputFileName.equals(CommonUtil.getAttr(driver, PageSource.Tab_ActiveFile, "title")));

		// 录入正确的文件名,点击Cancel
		CommonUtil.sleep(2000);
		CommonUtil.click(driver, PageSource.Btn_AddFile);
		CommonUtil.sendKey(driver, PageSource.Input_FileName, "welcome1.cpp");
		CommonUtil.click(driver, PageSource.Btn_Cancel);
		List<String> list = Operation.getListFile(driver);
		Assert.assertFalse(list.contains("welcome1.cpp"));
	}
	/* DELETE
	 * 选择任意文件,点击右键选择Delete后点击Cancel,文件未被删除
	 * 选择任意文件,点击右键选择Delete后点击Confirm,文件被删除(filelist和filetab均被删除)
	 */
	@Test
	public void deleteFileTest1() {
		// 删除文件点击Cancel
		CommonUtil.contextClick(driver, PageSource.List_ActiveFile);
		CommonUtil.click(driver, PageSource.Btn_Delete);
		CommonUtil.click(driver, PageSource.Btn_Cancel);
		List<String> list = Operation.getListFile(driver);
		Assert.assertTrue(list.contains("hello.cpp"));
		// 删除文件点击Confirm
		CommonUtil.sleep(1000);
		CommonUtil.contextClick(driver, PageSource.List_ActiveFile);
		CommonUtil.click(driver, PageSource.Btn_Delete);
		CommonUtil.click(driver, PageSource.Btn_Confirm);
		list = Operation.getListFile(driver);
		Assert.assertFalse(list.contains("hello.cpp"));
		list = Operation.getTabFile(driver);
		Assert.assertFalse(list.contains("hello.cpp"));
	}
	/*
	 * RENAME
	 * 选择文件，点击右键选择Rename后，查看录入框默认值
	 * 清空默认值，点击Confirm，弹出提示:"Please enter a file name."
	 * 录入已经存在的文件名，点击Confirm，弹出提示:"File name already exists."
	 * 录入正确的文件名，点击Cancel,文件没有被重命名(filelist和filetab)
	 * 录入正确的文件名，点击Confirm，文件重命名成功。
	 */
	@Test
	public void renameFileTest1() {
		String message = "Please enter a file name.";
		String error = "File name already exists.";
		//查看默认文件名
		CommonUtil.contextClick(driver, PageSource.List_ActiveFile);
		CommonUtil.click(driver, PageSource.Btn_Rename);
		Assert.assertTrue("hello.cpp".equals(CommonUtil.getAttr(driver, PageSource.Input_FileName, "value")));
		//清空默认值,点击Confirm
		delInputValue(driver,PageSource.Input_FileName);
		CommonUtil.click(driver, PageSource.Btn_Confirm);
		Assert.assertTrue(message.equals(Operation.getMessage(driver, "info")));
		//录入已经存在的文件名,点击Confirm
		CommonUtil.sendKey(driver, PageSource.Input_FileName, "hello.cpp");
		CommonUtil.click(driver, PageSource.Btn_Confirm);
		CommonUtil.sleep(1000);
		Assert.assertTrue(error.equals(Operation.getMessage(driver, "error")));
		//录入正确的文件名,点击Cancel
		delInputValue(driver,PageSource.Input_FileName);
		CommonUtil.sendKey(driver, PageSource.Input_FileName, "hello1.cpp");
		CommonUtil.click(driver, PageSource.Btn_Cancel);
		List<String> list=Operation.getListFile(driver);
		Assert.assertFalse(list.contains("hello1.cpp")||list.size()!=1);
		Assert.assertFalse(Operation.getTabFile(driver).contains("hello1.cpp"));
		//录入正确的文件名,点击Confirm
		CommonUtil.sleep(2000);
		CommonUtil.contextClick(driver, PageSource.List_ActiveFile);
		CommonUtil.click(driver, PageSource.Btn_Rename);
		delInputValue(driver,PageSource.Input_FileName);
		CommonUtil.sendKey(driver, PageSource.Input_FileName, "hello1.cpp");
		CommonUtil.click(driver, PageSource.Btn_Confirm);
		CommonUtil.sleep(1000);
		list=Operation.getListFile(driver);
		Assert.assertTrue(list.contains("hello1.cpp")&&list.size()==1);
		Assert.assertTrue(Operation.getTabFile(driver).contains("hello1.cpp"));
	}


	public void delInputValue(WebDriver driver,By by){
		int textSize=CommonUtil.getAttr(driver, by, "value").length();
		CommonUtil.click(driver, by);
		try {
			Robot robot=new Robot();
			for(int i=0;i<textSize;i++){
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
