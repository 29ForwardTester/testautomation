package executors;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicBorders;

import executors.MainPage;

public class GUI_initialAttempt extends JFrame implements ActionListener{
	
	
	JButton sendURLbutton;
	JCheckBox chromeCheckBox;
	JCheckBox mozillaCheckBox;
	JTextField urlText;
		
	public GUI_initialAttempt() {
		setTitle("Test Interface");
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
			
		ImageIcon logo = new ImageIcon("C:\\Users\\User29F\\eclipse-workspace\\Demo\\src\\appFunctionalities\\pituLOGO.png");
		setIconImage(logo.getImage());
		getContentPane().setBackground(new Color(54,85,111));// 0 to 255 : r,g,b
		
		JPanel mainPanel = new JPanel(); // container for the components
		mainPanel.setForeground(Color.pink);
		mainPanel.setBounds(10, 10, 900, 900);
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
			
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
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
			
			new GUI_initialAttempt();
			
		}

		

	}



