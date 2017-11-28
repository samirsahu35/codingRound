import com.sun.javafx.PlatformUtil;
import commonutil.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {
	BasePage basePage;

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		setDriverPath();
		WebDriver driver = new ChromeDriver();
		basePage=new BasePage(driver);
		driver.get("https://www.cleartrip.com/");
		driver.manage().window().maximize();
		basePage.waitFor(2000);
		driver.findElement(By.linkText("Your trips")).click();
		driver.findElement(By.id("SignIn")).click();
		driver.switchTo().frame(driver.findElement(By.id("modal_window")));
		driver.findElement(By.id("signInButton")).click();
		String errors1 = driver.findElement(By.id("errors1")).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));
		driver.quit();
	}

	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}
}
