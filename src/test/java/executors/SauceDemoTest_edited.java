package executors;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import constants.CredentialsDataProvider;
import constants.CurrentConstants;
import managers.ReportManager;
import managers.ScreenShotsManager;
import managers.DataManager;
import managers.FunctionsManager;

import org.apache.commons.io.FileUtils;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;




//the test results will not show failure on the console or on the testNG Interface anymore, since the run is clotured in try-catch blocks,
//that will report the failures to the extentreporter, and ignore displaying it on the console output
//so dont flip out if you dont get a test failure on the console or testNG-output. Consider only extentreports
public class SauceDemoTest_edited {
	
	// P:positive, N:negative, F:functionality (is differenciated to tech and logic), NF:non-functional, T:technical, L:logic
	//about saucedemo:
//			U1: standard_user: correct functionality until the end
//			U2: locked_out_user: cannot login, because locked out
//			U3: problem_user: all products with same wrong picture, only backpack light and Onsie can be added to cart, but not removed
//			U4: performance_glitch_user: correct functionality, but slow response
//			U5: error_user: only backpack light and Onsie can be added to cart, but not removed
//			U6: visual_user: first pic item wrong, prices wrong
//			UX: non-existing user
	
	//constructor of constants, URL to be configured in constants class
	public static CurrentConstants current = new CurrentConstants("chrome");
	public static ReportManager reporterM;
	public ReportManager testM;
	public static ScreenShotsManager screenshot;
	public static ExtentTest test;
	
	public DataManager dataM = new DataManager();
	public FunctionsManager functionM = new FunctionsManager();
	
	
//log the single steps?
	
	@BeforeMethod
	public void setUp() throws Exception {
		
		ReportManager.reportGenerator(); //a report has been generated / initialized
		DataManager.setDataFile(CurrentConstants.path+CurrentConstants.fileName,CurrentConstants.sheet);//test-data file being recognized
		current.cd.manage().window().maximize();
		current.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		current.cd.get(current.cURL);
		current.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	
	}
	@Test(groups="GSgroup")
	public  void _Test1() {
		//P+T login
		String login = "standard_user";
		String password = "secret_sauce";
		ExtentTest test1 = ReportManager.testGenerator("Test1: functional positive login test with a single data set", "LOGIN INFORMATION[ username:  <b>" + login + "</b> , password:  <b>"+ password+ "</b>]");
		test1.assignAuthor("Achouak Hassini");
		try {
			functionM.inputByID(current.cd, "user-name", login);
			functionM.inputByID(current.cd, "password", password);
			functionM.clickByID(current.cd, "login-button");
			if(functionM.selectorIdExists(current.cd, "react-burger-menu-btn")) {
				test1.pass("login successeful");
			} else {
				test1.fail("login information incorrect");
			}

		}
		catch (Exception e) {
			test1.fail("login information incorrect"); //catch the test code execution failure
			
		}
//		if(current.cd.findElement(By.xpath("//a[@data-test=\"shopping-cart-link\"]")).isDisplayed()) {
//			test1.pass("test successful");
//		}
//		
//		else if(!current.cd.findElement(By.xpath("//a[@data-test=\"shopping-cart-lnk\"]")).isDisplayed()) {
//			test1.fail("test failed");
//		}
		
	}
	@Test(groups="GSgroup")
	public  void _Test2() {
		
		String login = "sauce_user";
		String password = "secret_sauce";
		ExtentTest test2 = ReportManager.testGenerator("Test2: functional negative login test with a single data set", "LOGIN INFORMATION[ username:  <b>" + login + "</b> , password:  <b>"+ password+ "</b>]");
		test2.assignAuthor("Achouak Hassini");
		current.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		try {
			functionM.inputByID(current.cd, "user-name", login);
			functionM.inputByID(current.cd, "password", password);
			functionM.clickByID(current.cd, "login-button");
			if(!functionM.selectorXPATHExists(current.cd, "//*[@id=\"login_button_container\"]/div/form/div[3]/h3")) {
				test2.fail("login incorrectly successeful!");
			} else {
				test2.pass("login unsuccesseful: excpected");
			}
		}
		catch(Exception e) {
			test2.fail("execution of test failed"); 
		}
	}
	
	@Test(dataProvider="credentials", dataProviderClass=CredentialsDataProvider.class, groups="ISgroup")
	public  void _Test3(String username, String password) throws IOException{
		
		//multiple from provider
		ExtentTest test3 = ReportManager.testGenerator("Test3: functional login test with multiple data sets", "6 Login data sets");
		test3.assignAuthor("Achouak Hassini");
		
		current.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		try {
			
				
				ExtentTest childtest3 = test3.createNode("data set: username <b>" + username + "</b> , password:  <b>" + password +"</b>");
				functionM.inputByID(current.cd, "user-name", username);
				functionM.inputByID(current.cd, "password", password);
				functionM.clickByID(current.cd, "login-button");
				if(functionM.selectorXPATHExists(current.cd, "//*[@id=\"login_button_container\"]/div/form/div[3]/h3")) {
					test3.fail("login incorrect: expected?");
				} 
				String path = ScreenShotsManager.takeScreenshot(current.cd, username);//generate a screenpath
				childtest3.addScreenCaptureFromPath(path);
			//the try-catch is necessary, since in case of successful login the selector will not be found, an exception will be launched
				//to announce test success.
				//the only thing that is not catched is the test execution exception!
				//this test will not verify if the correct behavior is shown. It rather helps generate a sorted data of correct and incorrect credentials.
				//this data behavior is then compared to what is expected, from an already existing database that describes the correct behavior.
		} catch(Exception e) {
			test3.pass("test execution success " );
		}
		
		
		
	}
	@DataProvider(name="myData")
	public Object[][] dataProvider(){
		Object[][] testData = managers.DataManager.getTestData("test_data");
		return testData;
	}
	@Test(dataProvider="myData", groups="GSgroup")
	public  void _Test4(String username, String pass) throws Exception {
		
		
		ExtentTest test4 = ReportManager.testGenerator("Test 4: functional login test. Multiple data sets from external excel file", "4 data sets");
		test4.assignAuthor("Achouak Hassini");
		
		current.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		try {
			
			
			ExtentTest childtest4 = test4.createNode("data set: username: <b>" + username + "</b> , password:  <b>" + pass +"</b>");
			functionM.inputByID(current.cd, "user-name", username);
			functionM.inputByID(current.cd, "password", pass);
			functionM.clickByID(current.cd, "login-button");
			if(functionM.selectorXPATHExists(current.cd, "//*[@id=\"login_button_container\"]/div/form/div[3]/h3")) {
				test4.fail("login incorrect");
			} else {
				test4.pass("login successeful");
			}
//			this code didnt make each login be logged and screenshot separately!!
			String path = ScreenShotsManager.takeScreenshot(current.cd, username);//generate a screenpath
			childtest4.addScreenCaptureFromPath(path);
			
	
		
		} catch(Exception e) {
			test4.pass("test execution success " );
		}
		
		
	}
	@Test(groups="GSgroup")
	public  void _Test5() {
		
		//F,L, verify cart contains the one correct item
		//tester provided data
		
		String keyWord = "Backpack"; // examples: "Backpack", "Light", "Onesie", "T-Shirt", "Jacket", "T-Shirt (Red)"
		String username = "standard_user";
		String password ="secret_sauce";
		
		ExtentTest test5 = ReportManager.testGenerator("Test5: functional test. Single item Verification: Item in cart vs selected item", "LOGIN INFORMATION: [ username: <b>"+ username + "</b>"+" , password: <b>"+password+"]</b>");
		
		test5.assignAuthor("Achouak Hassini");
		current.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		try {
			functionM.inputByID(current.cd, "user-name", username);
			functionM.inputByID(current.cd, "password", password);
			functionM.clickByID(current.cd, "login-button");
			//Tester to modify the ID corresponding to the keyword!
			WebElement clickedItem = current.cd.findElement(By.xpath("//button[@id=\"add-to-cart-sauce-labs-backpack\"]"));
			String clickedTxt = clickedItem.getDomAttribute("name") ;//save for later comparison with keyword
			
			//System.out.println("clicked: " + clickedTxt);//for visualization temporary only
			test5.info("clicked item: <b>" + clickedTxt.substring(23)+"</b>");
			
			clickedItem.click();
		
			current.cd.findElement(By.xpath("//a[@data-test=\"shopping-cart-link\"]")).click(); // go to the cart
			WebElement currentItem = current.cd.findElement(By.xpath("//div[@data-test=\"inventory-item-name\"]"));//check the added item name
			
			//System.out.println("current: " + currentItem.getText());
			test5.info("current item in shopping cart: <b>" + currentItem.getText()+"</b>");
			
			if (currentItem.getText().contains(keyWord)) {
				//Assert.assertTrue(true);
				test5.pass("correct item in the cart, test successful");
			}
			else if (!currentItem.getText().contains(keyWord)){
				//Assert.assertTrue(true);
				test5.fail("wrong item in the cart, test failed");
			}
			
		} catch(Exception e) {
			test5.fail("test execution failed because " + e.getMessage());
		}
		
		//even after logging out, the item was not removed, which means data remains safe after the logout
		
	}
	@Test(groups="GSgroup")
	public  void _Test6() {
		
		String username = "problem_user";
		String password ="secret_sauce";
		
		ExtentTest test6 = ReportManager.testGenerator("Test6: functional test. Add all items to the cart then delete some and see what remains", "LOGIN INFORMATION: [ username: <b>"+ username + "</b>"+" , password: <b>"+password+"]</b>");
		
		test6.assignAuthor("Achouak Hassini");
		current.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		
		try {
			//remove items from last test
			functionM.clickByID(current.cd,"react-burger-menu-btn");
			functionM.clickByID(current.cd,"logout_sidebar_link");
			functionM.inputByID(current.cd, "user-name", "standard_user");
			functionM.inputByID(current.cd, "password", "secret_sauce");
			functionM.clickByID(current.cd, "login-button");
			functionM.clickByXPATH(current.cd, "//button[@id=\"remove-sauce-labs-backpack\"]");
			functionM.clickByID(current.cd,"react-burger-menu-btn");
			functionM.clickByID(current.cd,"logout_sidebar_link");
					//page now reset
			functionM.inputByID(current.cd, "user-name", username);
			functionM.inputByID(current.cd, "password", password);
			functionM.clickByID(current.cd, "login-button");
		
			WebElement clickedItem = current.cd.findElement(By.xpath("//button[@id=\"add-to-cart-sauce-labs-backpack\"]"));
			WebElement clickedItem1 = current.cd.findElement(By.xpath("//button[@id=\"add-to-cart-sauce-labs-fleece-jacket\"]"));
			WebElement clickedItem2 = current.cd.findElement(By.xpath("//button[@id=\"add-to-cart-sauce-labs-bike-light\"]"));
			WebElement clickedItem3 = current.cd.findElement(By.xpath("//button[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]"));
			WebElement clickedItem4 = current.cd.findElement(By.xpath("//button[@id=\"add-to-cart-sauce-labs-onesie\"]"));
			WebElement clickedItem5 = current.cd.findElement(By.xpath("//button[@id=\"add-to-cart-test.allthethings()-t-shirt-(red)\"]"));
			String clickedTxt = clickedItem.getDomAttribute("name") ;//save for later comparison 
			test6.info("clicked item: <b>" + clickedTxt.substring(23)+"</b>");
			String clickedTxt1 = clickedItem1.getDomAttribute("name") ;
			test6.info("clicked item: <b>" + clickedTxt1.substring(23)+"</b>");
			String clickedTxt2 = clickedItem2.getDomAttribute("name") ;
			test6.info("clicked item: <b>" + clickedTxt2.substring(23)+"</b>");
			String clickedTxt3 = clickedItem3.getDomAttribute("name") ;
			test6.info("clicked item: <b>" + clickedTxt3.substring(23)+"</b>");
			String clickedTxt4 = clickedItem4.getDomAttribute("name") ;
			test6.info("clicked item: <b>" + clickedTxt4.substring(23)+"</b>");
			String clickedTxt5 = clickedItem5.getDomAttribute("name") ;
			test6.info("clicked item: <b>" + clickedTxt5.substring(12)+"</b>");
			
			clickedItem.click();
			clickedItem1.click();
			clickedItem2.click();
			clickedItem3.click();
			clickedItem4.click();
			clickedItem5.click();
			
		
			functionM.clickByXPATH(current.cd,"//a[@data-test=\"shopping-cart-link\"]" ); // go to the cart
			//WebElement currentItem = current.cd.findElement(By.xpath("//div[@data-test=\"inventory-item-name\"]"));//check the added item name
			List<WebElement> currentItems = current.cd.findElements(By.xpath("//div[@data-test=\"inventory-item-name\"]"));//check the added item name
			
			//System.out.println("current: " + currentItem.getText());
			for (WebElement element : currentItems) {
				test6.info("current items in shopping cart: <b>" + element.getText() + "</b>");
			}
			if(currentItems.size() != 6) {
				test6.fail("Number of selected items (6) doesnt match the number of items in the cart: " + currentItems.size());
			}
			//test6.info("current items in shopping cart: <b>" + ((WebElement) currentItems).getText()+"</b>");
			
		} catch(Exception e) {
			test6.fail("test execution failed because " + e.getMessage());
		}
		current.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	}
	
	

	
	@Test(groups="GSgroup")
	public  void _Test7() {
		//P, L verify sum
		//U1 {lisa Simpson, 1289}
		//remove items from last test
		functionM.clickByID(current.cd,"react-burger-menu-btn");
		functionM.clickByID(current.cd,"logout_sidebar_link");
		functionM.inputByID(current.cd, "user-name", "standard_user");
		functionM.inputByID(current.cd, "password", "secret_sauce");
		functionM.clickByID(current.cd, "login-button");
		functionM.clickByXPATH(current.cd, "//button[@id=\"remove-sauce-labs-backpack\"]");
		functionM.clickByXPATH(current.cd, "//button[@id=\"remove-sauce-labs-bike-light\"]");
		functionM.clickByXPATH(current.cd, "//button[@id=\"remove-sauce-labs-onesie\"]");
		functionM.clickByID(current.cd,"react-burger-menu-btn");
		functionM.clickByID(current.cd,"logout_sidebar_link");
				//page now reset
		String username = "standard_user";
		String password ="secret_sauce";
		ExtentTest test7 = ReportManager.testGenerator("Test7: Price variations and sum calculation test Nr1", "LOGIN INFORMATION: [ username: <b>"+ username + "</b>"+" , password: <b>"+password+"]</b>");
		test7.assignAuthor("Achouak Hassini");
		current.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		try {
			current.cd.findElement(By.id("user-name")).sendKeys(username);
			current.cd.findElement(By.id("password")).sendKeys(password);
			current.cd.findElement(By.id("login-button")).click();
			
			WebElement clicked1 = current.cd.findElement(By.id("add-to-cart-sauce-labs-backpack"));
			WebElement cprice1 = current.cd.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div"));
			double d1 = Double.parseDouble(cprice1.getText().substring(1, 6));//extract the price
			System.out.println("price of backpack: "+ d1);
			test7.info("price of backpak: " + d1);
			
			WebElement clicked2 = current.cd.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
			WebElement cprice2 = current.cd.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[4]/div[2]/div[2]/div"));
			double d2 = Double.parseDouble(cprice2.getText().substring(1, 6));//extract the price
			System.out.println("price of fleece jacket: "+ d2);
			test7.info("price of fllece jacket: " + d2);
			
			WebElement clicked3 = current.cd.findElement(By.id("add-to-cart-sauce-labs-onesie"));
			WebElement cprice3 = current.cd.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[5]/div[2]/div[2]/div"));
			double d3 = Double.parseDouble(cprice3.getText().substring(1, 5));//extract the price
			System.out.println("price of onesie: "+ d3);
			test7.info("price of onesie: " + d3);
			
			clicked1.click();
			clicked2.click();
			clicked3.click();
			
			current.cd.findElement(By.xpath("//a[@data-test=\"shopping-cart-link\"]")).click();
			
			WebElement cprice11 = current.cd.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div"));
			double d11 = Double.parseDouble(cprice11.getText().substring(1, 6));//extract the price from cart page
			System.out.println("price in the cart of backpack: "+ d11);
			test7.info("price in the cart of th backpack: " + d11);
			
			WebElement cprice22 = current.cd.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[4]/div[2]/div[2]/div"));
			double d22 = Double.parseDouble(cprice22.getText().substring(1, 6));//extract the price from cart page
			System.out.println("price in the cart of fleecejackt: "+ d22);
			test7.info("price in the cart of fleecejackt: "+ d22);
			
			WebElement cprice33 = current.cd.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[5]/div[2]/div[2]/div"));
			double d33 = Double.parseDouble(cprice33.getText().substring(1, 5));//extract the price from cart page
			System.out.println("price in the cart of onsie: "+ d33);
			test7.info("price in the cart of onsie: "+ d33);
			
			//the checkout function to be simplified!!
			current.cd.findElement(By.id("checkout")).click();
			current.cd.findElement(By.id("first-name")).sendKeys("Lisa");	
			current.cd.findElement(By.id("last-name")).sendKeys("Simpson");	
			current.cd.findElement(By.id("postal-code")).sendKeys("1289");	
			current.cd.findElement(By.id("continue")).click();
			
			WebElement cprice111 = current.cd.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]/div[2]/div[2]/div"));
			double d111 = Double.parseDouble(cprice111.getText().substring(1, 6));//extract the price from overview page
			System.out.println("price in overview of backpack: "+ d111);
			test7.info("price in overview of backpack: "+ d111);
			
			WebElement cprice222 = current.cd.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[4]/div[2]/div[2]/div"));
			double d222 = Double.parseDouble(cprice222.getText().substring(1, 6));//extract the price from overview page
			System.out.println("price in overview of fleece jacket: "+ d222);
			test7.info("price in overview of fleece jacket: "+ d222);
			
			WebElement cprice333 = current.cd.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[5]/div[2]/div[2]/div"));
			double d333 = Double.parseDouble(cprice333.getText().substring(1, 5));//extract the price from overview page
			System.out.println("price in overview of onesie: "+ d333);
			test7.info("price in overview of onesie: "+ d333);
			
			WebElement priceTotal = current.cd.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[6]"));
			double dTotal = Double.parseDouble(priceTotal.getText().substring(13, (priceTotal.getText()).length()));//extract the price from overview page
			System.out.println("total price displayed : "+ dTotal);
			double sum = d1+d2+d3;
			System.out.println("total price calculated :"+ sum);
			test7.info("total price displayed : "+ dTotal);
			test7.info("total price calculated :"+ sum);


			
			//checking changing prices
			boolean c3 = true;
			boolean  c2 = true;
			boolean c1 = true;
			boolean coherence = true;
			if ((d1 != d11) || (d1 != d111) ) {
				System.out.println("first price changed");
				test7.info("first price changed");
				c1 = false;
			}
			if ((d2 != d22) && (d2 != d222)) {
				System.out.println("second price changed");
				test7.info("second price changed");
				c2 = false;
			}
			if ((d3 != d33) && (d3 != d333)) {
				System.out.println("third price changed");
				test7.info("third price changed");
				c3 = false;
			}
			if (!c1 || !c2 || !c3) 
				coherence = false;
			if (coherence)
			System.out.println("prices remained unchanged");
			test7.info("prices remained unchanged");
			
			current.cd.findElement(By.id("finish")).click();
			current.cd.findElement(By.id("back-to-products"));
			
			
		} catch(Exception e) {
			test7.fail("test7 failed because : " + e.getMessage());
		}
		
	}
	@Test(groups="GSgroup")
	public  void _Test8() {
		String username = "visual_user";
		String password ="secret_sauce";
 		ExtentTest test8 = ReportManager.testGenerator("Test8: Price variations and sum calculation test Nr2", "LOGIN INFORMATION: [ username: <b>"+ username + "</b>" + " password: <b>"+password+"]</b>");
 		test8.assignAuthor("Achouak Hassini");
		current.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		try {
			current.cd.findElement(By.id("user-name")).sendKeys(username);
			current.cd.findElement(By.id("password")).sendKeys(password);
			current.cd.findElement(By.id("login-button")).click();
			
			WebElement clicked1 = current.cd.findElement(By.id("add-to-cart-sauce-labs-backpack"));
			WebElement cprice1 = current.cd.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div"));
			int len1 = cprice1.getText().length();
			double d1 = Double.parseDouble(cprice1.getText().substring(1, len1));//extract the price
			System.out.println("price of backpack: "+ d1);
			test8.info("price of backpak: " + d1);
			
			WebElement clicked2 = current.cd.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
			WebElement cprice2 = current.cd.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[4]/div[2]/div[2]/div"));
			int len2 = cprice2.getText().length();
			double d2 = Double.parseDouble(cprice2.getText().substring(1, len2));//extract the price
			System.out.println("price of fleece jacket: "+ d2);
			test8.info("price of fllece jacket: " + d2);
			
			WebElement clicked3 = current.cd.findElement(By.id("add-to-cart-sauce-labs-onesie"));
			WebElement cprice3 = current.cd.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[5]/div[2]/div[2]/div"));
			int len3 = cprice3.getText().length();
			double d3 = Double.parseDouble(cprice3.getText().substring(1, len3));//extract the price
			System.out.println("price of onesie: "+ d3);
			test8.info("price of onesie: " + d3);
			
			clicked1.click();
			clicked2.click();
			clicked3.click();
			
			current.cd.findElement(By.xpath("//a[@data-test=\"shopping-cart-link\"]")).click();
			
			WebElement cprice11 = current.cd.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div"));
			int len11 = cprice11.getText().length();
			double d11 = Double.parseDouble(cprice11.getText().substring(1, len11));//extract the price from cart page
			System.out.println("price in the cart of backpack: "+ d11);
			test8.info("price in the cart of th backpack: " + d11);
			
			WebElement cprice22 = current.cd.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[4]/div[2]/div[2]/div"));
			int len22 = cprice22.getText().length();
			double d22 = Double.parseDouble(cprice22.getText().substring(1, len22));//extract the price from cart page
			System.out.println("price in the cart of fleecejackt: "+ d22);
			test8.info("price in the cart of fleecejackt: "+ d22);
			
			WebElement cprice33 = current.cd.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[5]/div[2]/div[2]/div"));
			int len33 = cprice33.getText().length();
			double d33 = Double.parseDouble(cprice33.getText().substring(1, len33));//extract the price from cart page
			System.out.println("price in the cart of onsie: "+ d33);
			test8.info("price in the cart of onsie: "+ d33);
			
			//the checkout function to be simplified!!
			current.cd.findElement(By.id("checkout")).click();
			current.cd.findElement(By.id("first-name")).sendKeys("Lisa");	
			current.cd.findElement(By.id("last-name")).sendKeys("Simpson");	
			current.cd.findElement(By.id("postal-code")).sendKeys("1289");	
			current.cd.findElement(By.id("continue")).click();
			
			WebElement cprice111 = current.cd.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]/div[2]/div[2]/div"));
			int len111 = cprice111.getText().length();
			double d111 = Double.parseDouble(cprice111.getText().substring(1, len111));//extract the price from overview page
			System.out.println("price in overview of backpack: "+ d111);
			test8.info("price in overview of backpack: "+ d111);
			
			WebElement cprice222 = current.cd.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[4]/div[2]/div[2]/div"));
			int len222 = cprice222.getText().length();
			double d222 = Double.parseDouble(cprice222.getText().substring(1, len222));//extract the price from overview page
			System.out.println("price in overview of fleece jacket: "+ d222);
			test8.info("price in overview of fleece jacket: "+ d222);
			
			WebElement cprice333 = current.cd.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[5]/div[2]/div[2]/div"));
			int len333 = cprice333.getText().length();
			double d333 = Double.parseDouble(cprice333.getText().substring(1, len333));//extract the price from overview page
			System.out.println("price in overview of onesie: "+ d333);
			test8.info("price in overview of onesie: "+ d333);
			
			WebElement priceTotal = current.cd.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[6]"));
			double dTotal = Double.parseDouble(priceTotal.getText().substring(13, (priceTotal.getText()).length()));//extract the price from overview page
			System.out.println("total price displayed : "+ dTotal);
			double sum = d1+d2+d3;
			System.out.println("total price calculated :"+ sum);
			test8.info("total price displayed : "+ dTotal);
			test8.info("total price calculated :"+ sum);
	
	
			
			//checking changing prices
			boolean c3 = true;
			boolean  c2 = true;
			boolean c1 = true;
			boolean coherence = true;
			if ((d1 != d11) || (d1 != d111) ) {
				System.out.println("first price changed");
				test8.warning("first price changed");
				c1 = false;
			}
			if ((d2 != d22) && (d2 != d222)) {
				System.out.println("second price changed");
				test8.warning("second price changed");
				c2 = false;
			}
			if ((d3 != d33) && (d3 != d333)) {
				System.out.println("third price changed");
				test8.warning("third price changed");
				c3 = false;
			}
			if (!c1 || !c2 || !c3) 
				coherence = false;
			if (coherence == false)
				test8.fail("prices changed");
			else
				test8.pass("passed");
			
		} catch(Exception e) {
			test8.fail("test7 failed because : " + e.getMessage());
		}
	
	}
	@Test(groups="GSgroup")
	public  void _Test9() {
		// verify a purchase process till the end with 2 items
		String username = "standard_user";
		String password ="secret_sauce";
		ExtentTest test9 = ReportManager.testGenerator("Test9: functional test. Full purchase with 2 items", "LOGIN INFORMATION: [ username: <b>"+ username + "</b>"+" , password: <b>"+password+"]</b>");
		test9.assignAuthor("Achouak Hassini");
		current.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		try {
			current.cd.findElement(By.id("user-name")).sendKeys(username);
			current.cd.findElement(By.id("password")).sendKeys(password);
			current.cd.findElement(By.id("login-button")).click();
			//remove items from last test
			current.cd.findElement(By.xpath("//button[@id=\"remove-sauce-labs-backpack\"]")).click();
			//page reset
			functionM.clickByID(current.cd,"add-to-cart-sauce-labs-bike-light");;
			test9.info("first product added to cart successfully");
			functionM.clickByID(current.cd,"add-to-cart-sauce-labs-backpack");
			test9.info("second product added to cart successfully");
			
			functionM.clickByXPATH(current.cd,"//a[@data-test=\"shopping-cart-link\"]");
			test9.info("shopping cart clickable");
			//the checkout function to be simplified!!
			functionM.clickByID(current.cd, "checkout");
			functionM.inputByID(current.cd, "first-name", "Mike");
			functionM.inputByID(current.cd, "last-name", "Tyson");
			functionM.inputByID(current.cd, "postal-code", "299117");
	
			test9.info("checkout area filled");
			functionM.clickByID(current.cd, "continue");
			functionM.clickByID(current.cd, "finish");
			List<WebElement> finishItem = current.cd.findElements(By.id("back-to-products"));
			if (finishItem.isEmpty()) {
				test9.fail("purchase not successeful: something was wrong");
			} else {
				test9.pass("purchase successful");
			}
		
			
		} catch(Exception e) {
			test9.fail("test execution failed because " + e.getMessage());
			test9.generateLog(Status.FAIL,"log for test9");
		}
	}
	@Test(groups="GSgroup")
	public  void test10() {
	
		// verify a purchase process till the end with 2 items
		String username = "error_user";
		String password ="secret_sauce";
		ExtentTest test10 = ReportManager.testGenerator("Test10: functional test. Full purchase with 2 items. Delete one item", "LOGIN INFORMATION: [ username: <b>"+ username + "</b>"+" , password: <b>"+password+"]</b>");
		test10.assignAuthor("Achouak Hassini");
		current.cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		try {
			current.cd.findElement(By.id("user-name")).sendKeys(username);
			current.cd.findElement(By.id("password")).sendKeys(password);
			current.cd.findElement(By.id("login-button")).click();
			
			functionM.clickByID(current.cd,"add-to-cart-sauce-labs-bike-light");;
			test10.info("first product added to cart successfully");
			functionM.clickByID(current.cd,"add-to-cart-sauce-labs-backpack");
			test10.info("second product added to cart successfully");
			functionM.clickByXPATH(current.cd, "//button[@id=\"remove-sauce-labs-backpack\"]");
			test10.info("second product remove-button clicked");
			
			
			functionM.clickByXPATH(current.cd,"//a[@data-test=\"shopping-cart-link\"]");
			test10.info("shopping cart clickable");
			
			functionM.clickByID(current.cd, "checkout");
			functionM.inputByID(current.cd, "first-name", "Mike");
			functionM.inputByID(current.cd, "last-name", "Tyson");
			functionM.inputByID(current.cd, "postal-code", "299117");
	
			test10.info("checkout area filled");
			functionM.clickByID(current.cd, "continue");
			functionM.clickByID(current.cd, "finish");
			List<WebElement> finishItem = current.cd.findElements(By.id("back-to-products"));
			if (finishItem.isEmpty()) {
				test10.fail("purchase not successeful: something was wrong");
			}
			
		} catch(Exception e) {
			test10.fail("step execution failed because " + e.getMessage());
			test10.generateLog(Status.FAIL,"log for test10");
		}
	}
	
	@AfterMethod(onlyForGroups="GSgroup")
	public void tearDown(ITestResult testResult) throws IOException {
		
//		 
//		String filename = testResult.getMethod().getMethodName() + ".png";
//		String dir = System.getProperty("user.dir")	+ "//SCREENSHOTS//";
//		System.out.println(dir);
//		File sourcefile = ((TakesScreenshot)current.cd).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(sourcefile, new File(dir + filename));
		test = ReportManager.testGenerator( testResult.getMethod().getMethodName() + ": screenshot", "screenshot taken at the end of execution of the testmethod");
		String screenpath = ScreenShotsManager.takeScreenshot(current.cd, testResult.getMethod().getMethodName());//generate a screenpath
		test.addScreenCaptureFromPath(screenpath);
		
		ReportManager.reportFlush();
		
		//important not to close the driver, otherwise invalid id session
		
	 }
		
		
}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		setUp();
//		loginTest1();
//		
//
//	}


