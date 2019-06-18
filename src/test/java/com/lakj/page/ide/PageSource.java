package com.lakj.page.ide;
import org.openqa.selenium.By;
public class PageSource {
	public static final By BQ_P=By.cssSelector("p");
	public static final By AgreeBtn=By.cssSelector(".closeUserBtn");
	public static final By List_ActiveFile=By.cssSelector(".list>.active>p");
	public static final By List_File=By.cssSelector(".list>li");//filelist集合
	public static final By Tab_ActiveFile=By.cssSelector(".file_tabs>li.active");//filetab处的激活文件
	public static final By Del_Tab_ActiveFile=By.cssSelector(".file_tabs>li.active>.closeBtn");//filetab处激活文件的删除按钮
	public static final By Tab_File=By.cssSelector(".file_tabs>li");
	public static final By Btn_AddFile=By.cssSelector(".toggler>svg");
	public static final By Input_FileName=By.cssSelector(".el-message-box__input > div:nth-child(1) > input:nth-child(1)");
	public static final By Btn_Rename=By.id("contextmenuId");//Rename按钮
	public static final By Btn_Delete=By.id("contextmenuDel");//Delete按钮
	public static final By Btn_Cancel=By.cssSelector("button.el-button--small:nth-child(1)");//Cancel按钮
	public static final By Btn_Confirm=By.cssSelector("button.el-button--default:nth-child(2)");//Confirm按钮
	public static final By MessageInfo=By.cssSelector(".el-message-box__errormsg");
	public static final By MessageError=By.cssSelector(".el-message__content");
	public static final By MessagePrompt=By.cssSelector(".el-message-box__message>p");
	//版本选择
	public static final By Input_Version=By.cssSelector(".fl>.version>div>input");//版本显示 EOS/BOS
	public static final By Btn_EOS=By.xpath("//li/span[contains(text(),'EOS')]");//EOS按钮
	public static final By Btn_BOS=By.xpath("//li/span[contains(text(),'BOS')]");//BOS按钮
	//Compile
	public static final By Input_Compiler=By.cssSelector(".boxInfo>.version>div>input");
	public static final By Select_Compiler=By.cssSelector("body>.el-popper>.el-scrollbar>.el-select-dropdown__wrap>.el-select-dropdown__list>li");//编译器版本下拉展示的集合
	public static final By Optimization=By.id("Optimization");
	public static final By Warning=By.id("wainings");
}
