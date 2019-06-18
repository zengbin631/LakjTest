package com.lakj.test.ide;

import com.lakj.common.CommonUtil;
import com.lakj.page.ide.PageSource;
import com.lakj.task.ide.Operation;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;
//acd
public class CompilePageTest {
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
	 *
	 *
	 */
	@Test
	public void selectCompilerTest () {
//		//EOS编译器版本默认值
		String eosDefaultCompiler="eosio.cdt_1.5.x";
		String bosDefaultCompiler="bos.cdt";
		String eosCompiler[]={"eosio.cdt_1.2.x","eosio.cdt_1.3.x","eosio.cdt_1.4.x","eosio.cdt_1.5.x","eosio.cdt_1.6.x"};
		Assert.assertTrue(eosDefaultCompiler.equals(CommonUtil.getAttr(driver, PageSource.Input_Compiler,"value")));
		//验证EOS编译器可选版本
		CommonUtil.click(driver,PageSource.Input_Compiler);
		List<String> list=Operation.getCompiler(driver);
		Assert.assertTrue(Arrays.equals(eosCompiler,list.toArray()));
		CommonUtil.click(driver,PageSource.Input_Compiler);
		//验证EOS的Optimization 、warning是否默认勾选;是否可去勾选
		Assert.assertTrue(CommonUtil.isSelectElement(driver,PageSource.Optimization));
		CommonUtil.click(driver,PageSource.Optimization);
		Assert.assertFalse(CommonUtil.isSelectElement(driver,PageSource.Optimization));
		Assert.assertTrue(CommonUtil.isSelectElement(driver,PageSource.Warning));
		CommonUtil.click(driver,PageSource.Warning);
		Assert.assertFalse(CommonUtil.isSelectElement(driver,PageSource.Warning));
		//验证BOS编译器可选版本
//		CommonUtil.click(driver,PageSource.Input_Version);
//		CommonUtil.click(driver,PageSource.Btn_BOS);
//		Assert.assertTrue(eosDefaultCompiler.equals(CommonUtil.getAttr(driver, PageSource.Input_Compiler,"value")));

	}
}