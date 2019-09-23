package co.qa.automation;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class TapUtilityAutomation1 {

	public static void main(String[] args)  throws InterruptedException {
		// TODO Auto-generated method stub
		 System.setProperty("webdriver.chrome.driver","C:\\Users\\utsavsingh\\chromedriver versio 77\\chromedriver_win32 (1)\\chromedriver.exe");
	     WebDriver driver=new ChromeDriver();
	     String baseUrl = "http://10.0.1.86";	
	     driver.get(baseUrl);
	     String boxClass1="";
	     String boxClass2="";
	     String expectedTitle1 = "Welcome - T.A.T.O.C";
	     String expectedTitle2="Grid Gate - Basic Course - T.A.T.O.C";
	     String expectedTitle3="Frame Dungeon - Basic Course - T.A.T.O.C";
	     String actualTitle = "";
	     driver.findElement(By.cssSelector("a[href=\"/tatoc\"]")).click();
	     Thread.sleep(1000);
	     
	     actualTitle = driver.getTitle();
	        if (actualTitle.contentEquals(expectedTitle1)){
	            System.out.println("Test1 Passed");
	            driver.findElement(By.cssSelector("a[href=\"/tatoc/basic\"]")).click();
	            Thread.sleep(1000);
	            
	            if(expectedTitle2.equals(driver.getTitle()))
	              {	
	            	System.out.println("Test2 Passed");
	                driver.findElement(By.cssSelector("div.greenbox")).click();
	                Thread.sleep(1000);
	                
	                if(expectedTitle3.equals(driver.getTitle()))
	                  {
	                	 System.out.println("Test3 Passed"); 
	                	 Thread.sleep(1000);
	                	 driver.switchTo().frame("main");
		                 boxClass1=driver.findElement(By.id("answer")).getAttribute("class");
	                	 do
	                	 { 
	                	   WebElement repaintBox2=driver.findElement(By.linkText("Repaint Box 2"));
	                       repaintBox2.click();
	                       driver.switchTo().frame("child");
	                       boxClass2=driver.findElement(By.id("answer")).getAttribute("class");
	                       driver.switchTo().parentFrame();
	                	 }
	                	 while(!boxClass1.equals(boxClass2));
	                	 Thread.sleep(1000);
	                	 driver.findElement(By.linkText("Proceed")).click();
	                	 System.out.println("Test4 Passed");
	                	 Actions act=new Actions(driver);
	                	 act.dragAndDrop(driver.findElement(By.id("dragbox")),driver.findElement(By.id("dropbox"))).build().perform();
	                	 Thread.sleep(1000);
	                	 driver.findElement(By.linkText("Proceed")).click();
	                	 System.out.println("Test4 Passed");
	                	 String parentWinHandle = driver.getWindowHandle();
	                	 System.out.println("Title of the new window:=====1 " + driver.getTitle());
	                	 driver.findElement(By.linkText("Launch Popup Window")).click();
	                	 Thread.sleep(1000);
	                	 Set<String> winHandles = driver.getWindowHandles();
	                	 Iterator<String> i1=winHandles.iterator();
	                	 while(i1.hasNext()){
	                		 String ChildWindow=i1.next();
	                         if(!parentWinHandle.equalsIgnoreCase(ChildWindow))
	                         {
	                           driver.switchTo().window(ChildWindow);
	                           Thread.sleep(1000);
	                           System.out.println("Title of the new window:=====2 " + driver.getTitle());
	                           driver.findElement(By.id("name")).sendKeys("xyz");
	                           Thread.sleep(500);
		                	   driver.findElement(By.id("submit")).click();
		                	   Thread.sleep(1000);
		                	  
	                         }
	                     }
	                	 
	                	 driver.switchTo().window(parentWinHandle);
	                	 System.out.println("Title of the new window:=====4 " + driver.getTitle());
	                	 driver.findElement(By.linkText("Proceed")).click(); 
	                	 Thread.sleep(500);
	                	 driver.findElement(By.linkText("Generate Token")).click();
	                	 String tokenOpt=driver.findElement(By.id("token")).getText();
	                	 
	                	 tokenOpt=tokenOpt.substring(7);
	                	 System.out.println(tokenOpt);
	                	 Cookie cookie = new Cookie("Token",tokenOpt);
	                	 driver.manage().addCookie(cookie);
	                	 System.out.println(driver.manage().getCookies());
	                	 driver.findElement(By.linkText("Proceed")).click(); 
	                	 
	                	}
	                else
	                	System.out.println("Test2 Failed");
	              }
	            else
	            	System.out.println("Test1 Failed");
	        } 
	        else {
	            System.out.println("Test1 Failed");
	        }
	    
	}

}
