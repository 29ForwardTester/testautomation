package executors;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicBorders;

import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import executors.MainPage;
import executors.SauceDemoTest_edited;
import constants.CredentialsDataProvider;
import constants.CurrentConstants;
import managers.ReportManagerGUI;
import managers.ScreenShotsManager;
import managers.DataManager;
import managers.FunctionsManager;

public class Testsuite_GUI extends JFrame implements ActionListener, ITestResult {
	
	
	JButton sendURLbutton;
	JCheckBox chromeCheckBox;
	JCheckBox mozillaCheckBox;
	
	//**************************
	// test components
	JCheckBox test1checkbox;
	JCheckBox test2checkbox;
	JCheckBox test3checkbox;
	JCheckBox test4checkbox;
	JCheckBox test5checkbox;
	JCheckBox test6checkbox;
	JCheckBox test7checkbox;
	JCheckBox test8checkbox;
	JCheckBox test9checkbox;
	JCheckBox test10checkbox;
	JButton exeTestButton;
	
	public ExtentTest gtest;
	public static ITestResult tResult;
	
	public static ScreenShotsManager screenshot;
	public static ReportManagerGUI reporterM;
	public ReportManagerGUI testM;
	public DataManager dataM = new DataManager();
	public FunctionsManager functionM = new FunctionsManager();
	
	
	private static ArrayList<JCheckBox> selectedTests = new ArrayList<JCheckBox>(10);
	public SauceDemoTest_edited sauceTest = new SauceDemoTest_edited();
	public SauceDemoTest_edited[] testsTBE = new SauceDemoTest_edited[10];
	
	public static CurrentConstants current = new CurrentConstants("chrome");
	
	
	
	//***************************
	JTextField urlText;
		
	public Testsuite_GUI() {
		
		
		setTitle("User Interface: Testsuite Lancher");
		setSize(1500,1500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
			
		ImageIcon logo = new ImageIcon("C:\\Users\\User29F\\eclipse-workspace\\Demo\\src\\appFunctionalities\\pituLOGO.png");
		setIconImage(logo.getImage());
		getContentPane().setBackground(new Color(134,85,111));// 0 to 255 : r,g,b
		
		JPanel mainPanel = new JPanel(); // container for the components
		mainPanel.setForeground(Color.pink);
		mainPanel.setBounds(10, 10,1000, 1000);
		add(mainPanel);
			
		JLabel tfLabel = new JLabel();
		tfLabel.setText("enter URL:");
		tfLabel.setBounds(10,-30, 450, 100);
		tfLabel.setVisible(true);
		mainPanel.add(tfLabel);
		
		JLabel checkBLabel = new JLabel();
		checkBLabel.setText("choose your browser(s):");
		checkBLabel.setBounds(500, 10, 300, 20);
		mainPanel.add(checkBLabel);
			
		//sendURLbutton
		sendURLbutton = new JButton();
		sendURLbutton.setBounds(370, 50, 70, 30);
		mainPanel.setBackground(Color.white);
		sendURLbutton.setText("send");
		mainPanel.add(sendURLbutton);
		sendURLbutton.setForeground(Color.blue);
		sendURLbutton.addActionListener(this);
			
		//textfild for url reading
		urlText = new JTextField();
		urlText.setBounds(50,40, 300, 50);
		urlText.setBorder(BorderFactory.createLineBorder(Color.cyan));
		urlText.setBackground(Color.black);
		urlText.setForeground(Color.red);
		urlText.setText("https://");
		mainPanel.add(urlText);
		urlText.addActionListener(this);
		
		//checkbox for browswer
		
		chromeCheckBox = new JCheckBox();
		chromeCheckBox.setText("chrome");
		mainPanel.add(chromeCheckBox);
		chromeCheckBox.setBounds(500,60, 100, 30);
		
		mozillaCheckBox = new JCheckBox();
		mozillaCheckBox.setText("firefox");
		mainPanel.add(mozillaCheckBox);
		mozillaCheckBox.setBounds(500,40, 100, 30);
		//*************************************
		//part for the testsuite : choose testcases
		
		test1checkbox = new JCheckBox();
		test1checkbox.setText("Test1: Simple Login Test: positive");
		mainPanel.add(test1checkbox);
		test1checkbox.setBounds(500,170, 300, 30);
		
		test2checkbox = new JCheckBox();
		test2checkbox.setText("Test2: Simple Login Test: negative");
		mainPanel.add(test2checkbox);
		test2checkbox.setBounds(500,190, 300, 30);
		
		test3checkbox = new JCheckBox();
		test3checkbox.setText("Test3: Multiple Login Test: DataProvider");
		mainPanel.add(test3checkbox);
		test3checkbox.setBounds(500,210, 300, 30);
		
		test4checkbox = new JCheckBox();
		test4checkbox.setText("Test4: Multiple Login Test: From Excel");
		mainPanel.add(test4checkbox);
		test4checkbox.setBounds(500,230, 300, 30);
		
		test5checkbox = new JCheckBox();
		test5checkbox.setText("Test5: Calculations and sum verification");
		mainPanel.add(test5checkbox);
		test5checkbox.setBounds(500,250, 300, 30);
		
		exeTestButton = new JButton();
		exeTestButton.setBounds(370, 120, 200, 30);
		mainPanel.setBackground(Color.white);
		exeTestButton.setText("execute the selected tests");
		mainPanel.add(exeTestButton);
		exeTestButton.setForeground(Color.blue);
		exeTestButton.addActionListener(this);
			
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource() == exeTestButton) {
				try {
					//gtest = testM.testGenerator(getTestName(), getHost());
					//JCheckBox[] allChB = {test1checkbox,test2checkbox,test3checkbox, test4checkbox};
					if(test1checkbox.isSelected()) {
						
						sauceTest.setUp();
					
						sauceTest._Test1();
						
						sauceTest.tearDown(ITestResult tResult1);
					}
					if(test2checkbox.isSelected()) {
						sauceTest.setUp();
						sauceTest._Test2();
						sauceTest.tearDown(tResult);
					}
					if(test3checkbox.isSelected()) {
						sauceTest.setUp();
						sauceTest._Test3("we","ggt");
						sauceTest.tearDown(tResult);
					}
					if(test4checkbox.isSelected()) {
						sauceTest.setUp();
						sauceTest._Test4("UN","PASS");
						sauceTest.tearDown(tResult);
					}
					if(test5checkbox.isSelected()) {
						sauceTest.setUp();
						sauceTest._Test5();
						sauceTest.tearDown(tResult);
					}
					if(test6checkbox.isSelected()) {
						sauceTest.setUp();
						sauceTest._Test6();
						sauceTest.tearDown(tResult);
					}
					if(test7checkbox.isSelected()) {
						sauceTest.setUp();
						sauceTest._Test7();
						sauceTest.tearDown(tResult);
					}
					if(test8checkbox.isSelected()) {
						sauceTest.setUp();
						sauceTest._Test8();
						sauceTest.tearDown(tResult);
					}
					if(test9checkbox.isSelected()) {
						sauceTest.setUp();
						sauceTest._Test9();
						sauceTest.tearDown(tResult);
					}
					if(test10checkbox.isSelected()) {
						sauceTest.setUp();
						sauceTest.test10();
						sauceTest.tearDown(tResult);
					}
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			if (e.getSource() == sendURLbutton) {
				String entry = urlText.getText();
				
				if (chromeCheckBox.isSelected()) {
					MainPage.firstAccess(entry, "chrome");
				}
				if (mozillaCheckBox.isSelected()) {
					MainPage.firstAccess(entry, "firefox");
				}
			
			}
			
		}
		
		public static void main(String[] args) {
			
			new Testsuite_GUI();
			
		}
		@Override
		public ITestNGMethod getMethod() {
			// TODO Auto-generated method stub
			return null;
		//	return tResult.getMethod();
		}

		

		@Override
		public Object getAttribute(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setAttribute(String name, Object value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Set<String> getAttributeNames() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object removeAttribute(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int compareTo(ITestResult o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getStatus() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setStatus(int status) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public Object[] getParameters() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setParameters(Object[] parameters) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public IClass getTestClass() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Throwable getThrowable() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setThrowable(Throwable throwable) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public long getStartMillis() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long getEndMillis() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setEndMillis(long millis) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isSuccess() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String getHost() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getInstance() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] getFactoryParameters() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getTestName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getInstanceName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ITestContext getTestContext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTestName(String name) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean wasRetried() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setWasRetried(boolean wasRetried) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String id() {
			// TODO Auto-generated method stub
			return null;
		}

		

	}



