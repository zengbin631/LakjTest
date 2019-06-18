package com.lakj.task.ide;


import com.lakj.common.CommonUtil;
import com.lakj.page.ide.PageSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Operation {
	public static void getHome(WebDriver driver){
		driver.get("https://beosin.com/BEOSIN-IDE/index.html#/");
		  CommonUtil.getElement(driver,PageSource.AgreeBtn).click();
		  CommonUtil.sleep(3000);
	}
	public static String getMessage(WebDriver driver,String level){
		WebElement element;
		if("info".equals(level)){
			element=CommonUtil.getElement(driver, PageSource.MessageInfo);
		}else if("error".equals(level)){
			element=CommonUtil.getElement(driver, PageSource.MessageError);
		}else if("prompt".equals(level)){
			element=CommonUtil.getElement(driver, PageSource.MessagePrompt);
			return element.getText();
		}else{
			return null;
		}
		if(element.isDisplayed()){
			return element.getText();
		}
		return null;
	}
	public static List<String> getListFile(WebDriver driver){
		List<String> list=new ArrayList<String>();
		List<WebElement> elements=CommonUtil.getElements(driver, PageSource.List_File);
		if(elements!=null){
			for(WebElement element:elements){
				String text=element.findElement(PageSource.BQ_P).getAttribute("title");
				list.add(text);
			}
			return list;
		}
		return list;
	}
	public static List<String> getTabFile(WebDriver driver){
		List<String> list=new ArrayList<String>();
		List<WebElement> elements=CommonUtil.getElements(driver, PageSource.Tab_File);
		if(elements!=null){
			for(WebElement element:elements){
				String text=element.getAttribute("title");
				list.add(text);
			}
			return list;
		}
		return list;
	}
	public static List<String> getCompiler(WebDriver driver){
		CommonUtil.sleep(2000);
		List<String> list=new ArrayList<String>();
		List<WebElement> elements=CommonUtil.getElements(driver,PageSource.Select_Compiler);
		for(WebElement element:elements){
			list.add(element.getText());
		}
		return list;
	}
}
