package io.mosip.testrig.adminui.utility;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class Commons  extends BaseClass{
	private static final org.slf4j.Logger logger= org.slf4j.LoggerFactory.getLogger(Commons.class);

	public static String appendDate=getPreAppend()+getDateTime();
	
	public static String getDateTime()
	  {
		
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
	   LocalDateTime now = LocalDateTime.now();
	   return dtf.format(now);
	  }
	
	public  static void filter(ExtentTest test,WebDriver driver, By by,String data) {
		try {
		logger.info("Inside Filter " + by + data);
		Commons.click(test,driver, By.id("Filter")); 
		Thread.sleep(3000);
		Commons.enter(test,driver, by, data); 
		Thread.sleep(3000);
		Commons.click(test,driver, By.id("applyTxt"));
		}
		catch (Exception e) {
			try {
				test.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.ClickScreenshot(driver)).build());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));

		}
	}
	
	public  static void filterCenter(ExtentTest test,WebDriver driver, By by,String data) {
		logger.info("Inside filterCenter " + by + data);
		try {
		Commons.click(test,driver, By.id("Filter")); 
	
		Commons.dropdowncenter(test,driver, by, data); 
		
		Commons.click(test,driver, By.id("applyTxt")); 
		}
		catch (Exception e) {
			try {
				test.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.ClickScreenshot(driver)).build());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));

		}
	}
	public  static void click(ExtentTest test,WebDriver driver, By by) throws InterruptedException {
		logger.info("Clicking " + by );
		try {
			(new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(by));
			Thread.sleep(500);
			driver.findElement(by).click();
			Thread.sleep(500);
		}catch (Exception sere) {
			// simply retry finding the element in the refreshed DOM
			sere.printStackTrace();
		}
		}
	
  
	public static void enter(ExtentTest test,WebDriver driver, By by,String value) {
		logger.info("Entering " + by +value);
			try {
				(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOfElementLocated(by));
				driver.findElement(by).clear();
				driver.findElement(by).sendKeys(value);
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch (StaleElementReferenceException sere) {
				// simply retry finding the element in the refreshed DOM
				driver.findElement(by).sendKeys(value);
			}
			catch (TimeoutException toe) {
				driver.findElement(by).sendKeys(value);
				logger.info( "Element identified by " + by.toString() + " was not clickable after 20 seconds");
			} 
			catch (Exception e) {
				try {
					test.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.ClickScreenshot(driver)).build());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", driver.findElement(by));

			}
			
	}
	
	public static void dropdown(ExtentTest test,WebDriver driver, By by)
	  {
		logger.info("Selecting DropDown Index Zero Value " + by );
		  
		 try {
			 Thread.sleep(500);
			 click(test,driver,by);//REGION
				Thread.sleep(500);
			
		   String att= driver.findElement(by).getAttribute("aria-owns");
		   String[] list=att.split(" ");
		    click(test,driver,By.id(list[0]));
		    try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }catch (Exception e) {
				try {
					test.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.ClickScreenshot(driver)).build());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", driver.findElement(by));

			}
	  }
	
	public static void dropdown(ExtentTest test,WebDriver driver, By by,String value)
	  {
		logger.info("Selecting DropDown By Value " + by +value );
		  
		 try {
			 Thread.sleep(500);
			 click(test,driver,by);
				Thread.sleep(500);
			   String val="'"+value +"'";
		   
		    click(test,driver,By.xpath("//span[contains(text(),"+val+")]"));
		    try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }catch (Exception e) {
				try {
					test.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.ClickScreenshot(driver)).build());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", driver.findElement(by));

			}
	  }
	
	public static void dropdowncenter(ExtentTest test,WebDriver driver, By by,String value)
	  {
		logger.info("Selecting DropDown By Value " + by +value );
		  
		 try {
			 Thread.sleep(500);
			 click(test,driver,by);
				Thread.sleep(500);
			   String val="'"+value +"'";
		   
		    click(test,driver,By.id(value));
		    try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }catch (Exception e) {
				try {
					test.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.ClickScreenshot(driver)).build());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", driver.findElement(by));

			}
	  }
	
	public static void dropdown(ExtentTest test,WebDriver driver, By by,By value)
	  {
		logger.info("Selecting DropDown By Value " + by +value );
		 try {  
			 Thread.sleep(500);
			 click(test,driver,by);
			 Thread.sleep(500);
		    click(test,driver,value);
		  
				Thread.sleep(500);
			
		 }catch (Exception e) {
				try {
					test.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.ClickScreenshot(driver)).build());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", driver.findElement(by));

			}
	  }
	public static String getTestData()
	{
		return JsonUtil.readJsonFileText("TestData.json");
	}
	public static String getFieldData(String idfield) throws Exception
	{
		return	JsonUtil.JsonObjSimpleParsing(getTestData(), idfield);
	
	}

	public static void clickSpan(ExtentTest test,WebDriver driver,String key) throws Exception {
		
		String val=Commons.getFieldData(key);
		String var="//span[contains(text(),'"+ val+ "')]";
		  Commons.click(test,driver,By.xpath(var)); 
		  logger.info("clickSpan" + var );
	}

	public static void deactivate(ExtentTest test,WebDriver driver) {
		Commons.click(test,driver,By.id("ellipsis-button0"));
		Commons.click(test,driver, By.id("Deactivate0")); 

	    Commons.click(test,driver,By.id("confirmpopup")); 
		Commons.click(test,driver, By.id("confirmmessagepopup")); 
		 logger.info("Click deactivate and Confirm");
	}

	public static void activate(ExtentTest test,WebDriver driver) {
		Commons.click(test,driver,By.id("ellipsis-button0"));
		Commons.click(test,driver, By.id("Activate0")); 

	    Commons.click(test,driver,By.id("confirmpopup")); 
		Commons.click(test,driver, By.id("confirmmessagepopup")); 
		 logger.info("Click activate and Confirm");
	}

	public static void edit(ExtentTest test,WebDriver driver,String data,By by) {
	
		try {
		Commons.click(test,driver,By.id("ellipsis-button0"));
		Commons.click(test,driver, By.id("Edit0")); 
		
		Assert.assertNotEquals(data,
				driver.findElement(by).getText());
		driver.findElement(by).clear();

		Commons.enter(test,driver, by, data);

		Commons.click(test,driver, By.id("createButton"));
		Commons.click(test,driver, By.id("confirmmessagepopup")); 

		 logger.info("Click Edit and Confirm" + by + data);
		}
		catch (Exception e) {
			try {
				test.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.ClickScreenshot(driver)).build());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));

		}
	}

	public static void editRes(ExtentTest test,WebDriver driver,String data,By by) {
		try {
		Commons.click(test,driver,By.id("ellipsis-button0"));
		Commons.click(test,driver, By.id("Edit0")); 
		Thread.sleep(3000);
		Assert.assertNotEquals(data,
				driver.findElement(by).getText());
		Thread.sleep(3000);
		driver.findElement(by).clear();

		Commons.enter(test,driver, by, data);

		Commons.click(test,driver, By.id("createButton"));

	    Commons.click(test,driver,By.id("confirmpopup")); 
			Commons.click(test,driver, By.id("confirmmessagepopup")); 

			 logger.info("Click Edit and Confirm" + by + data);
			 }catch (Exception e) {
					try {
						test.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.ClickScreenshot(driver)).build());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", driver.findElement(by));

				}
	}
	public static void editCenter(ExtentTest test,WebDriver driver,String data,By by) {
		try {
		Commons.click(test,driver,By.id("ellipsis-button0"));
		Commons.click(test,driver, By.id("Edit0")); 
		
		Assert.assertNotEquals(data,
				driver.findElement(by).getText());
		driver.findElement(by).clear();

		Commons.enter(test,driver, by, data);

		Commons.click(test,driver, By.xpath("(//*[@id='createButton'])[1]"));

	    Commons.click(test,driver,By.id("confirmpopup")); 
			Commons.click(test,driver, By.id("confirmmessagepopup")); 

			Commons.click(test,driver,  By.xpath("(//*[@id='cancel'])[1]"));
			Commons.click(test,driver,  By.xpath("(//*[@id='cancel'])[1]"));
			 logger.info("Click editCenter and Confirm" + by + data);
		}
		catch (Exception e) {
			try {
				test.fail(e.getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(Screenshot.ClickScreenshot(driver)).build());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(by));

		}
	}
	
	public static void create(ExtentTest test,WebDriver driver) {
		
		Commons.click(test,driver, By.xpath("//button[@id='createButton']")); 
		Commons.click(test,driver, By.id("confirmmessagepopup")); 
		
		logger.info("Click create");
	}
	public static void createRes(ExtentTest test,WebDriver driver) {
		 try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Commons.click(test,driver, By.xpath("//button[@id='createButton']")); 
		 Commons.click(test,driver,By.id("confirmpopup")); 
		Commons.click(test,driver, By.id("confirmmessagepopup")); 
		logger.info("Click and confirm");
	}

	public static void decommission(ExtentTest test,WebDriver driver) {
		 Commons.click(test,driver,By.id("ellipsis-button0"));
		    Commons.click(test,driver,By.id("Decommission0"));

		    Commons.click(test,driver,By.id("confirmpopup")); 
			Commons.click(test,driver, By.id("confirmmessagepopup")); 
			logger.info("Click decommission and confirm");
	}
	public static String getPreAppend() 
	  {
	String preappend = null;
	try {
		preappend = JsonUtil.JsonObjParsing(getTestData(),"preappend");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return preappend;
	  }
	
	public static void calendar(String date) {
		String a=date.replaceAll("/","");
	    String mon="";
	    if(a.substring(0,2).equals("10")) {
	    	 mon=	a.substring(0,2);
	    }else {
	    	 mon=a.substring(0,2).replace("0","");
	    }
	  String d="";
	  if(a.substring(2,4).equals("10") || a.substring(2,4).equals("20")||a.substring(2,4).equals("30")) {
		  d=a.substring(2,4);
	  }else {
	 	 d=a.substring(2,4).replace("0","");
	 }

	    int month=Integer.parseInt(mon)  ;
	    int day=Integer.parseInt(d);
	    int year=Integer.parseInt(a.substring(4,8));
	    try {
	    Commons.click(test,driver,By.xpath("//*[@class='mat-datepicker-toggle']//button"));
	    Thread.sleep(500);
	    Commons.click(test,driver,By.xpath("//*[@class='mat-calendar-arrow']"));
	    Thread.sleep(500);
	    Commons.click(test,driver,By.xpath("//*[text()='"+year+"']"));
	    Thread.sleep(500);
	  List<WebElement> cli=  driver.findElements(By.xpath("//*[@class='mat-calendar-body-cell-content']"));
	  cli.get(month-1).click();
	  Thread.sleep(500);
	  Commons.click(test,driver,By.xpath("//*[text()='"+day+"']"));
	    }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
