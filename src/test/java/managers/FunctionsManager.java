package managers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FunctionsManager {


	public boolean itemIsInCart(WebDriver webd, String kword) {
		
		WebElement clickedItem = webd.findElement(By.xpath("//button[@id=\"add-to-cart-sauce-labs-bike-light\"]"));
		String clickedTxt = clickedItem.getDomAttribute("name") ;//save for later comparison with keyword
		
		//System.out.println("clicked: " + clickedTxt);//for visualization temporary only
//		test4.info("clicked item  :" + clickedTxt);
//		
//		clickedItem.click();
//	
//		current.cd.findElement(By.xpath("//a[@data-test=\"shopping-cart-link\"]")).click(); // go to the cart
//		WebElement currentItem = current.cd.findElement(By.xpath("//div[@data-test=\"inventory-item-name\"]"));//check the added item name
//		
//		//System.out.println("current: " + currentItem.getText());
//		test4.info("current item in shopping cart :" + currentItem.getText());
//		
//		if (currentItem.getText().contains(keyWord)) {
//			//Assert.assertTrue(true);
//			test4.pass("correct item in the cart, test successful");
//		}
//		else if (!currentItem.getText().contains(keyWord)){
//			//Assert.assertTrue(true);
//			test4.fail("wrong item in the cart, test failed");
//		}
//
//		current.cd.findElement(By.id("react-burger-menu-btn")).click();
//		current.cd.findElement(By.id("logout_sidebar_link")).click();
		return false;
	}
	public void clickByID(WebDriver webd, String id) {
		try {
			WebElement click = webd.findElement(By.id(id));
			click.click();
			
		}catch(Exception e) {
			e.getMessage();
		}
		
	}
	public void clickByXPATH(WebDriver webd, String xpath) {
		try {
			WebElement click = webd.findElement(By.xpath(xpath));
			click.click();
			
		}catch(Exception e) {
			e.getMessage();
		}
		
	}
	public void inputByID(WebDriver webd, String id, String input) {
		try {
			WebElement typein = webd.findElement(By.id(id));
			typein.sendKeys(input);
		}catch(Exception e) {
			e.getMessage();
		}
	}
	public void inputByXPATH(WebDriver webd, String xpath, String input) {
		try {
			WebElement typein = webd.findElement(By.xpath(xpath));
			typein.sendKeys(input);
		}catch(Exception e) {
			e.getMessage();
		}
	}
	public boolean selectorIdExists(WebDriver webd, String id) {
		if(webd.findElement(By.id(id)).isDisplayed())
			return true;
		return false;
		
	}
	public boolean selectorXPATHExists(WebDriver webd, String xpath) {
		if(webd.findElement(By.xpath(xpath)).isDisplayed())
			return true;
		return false;
		
	}
	


}
