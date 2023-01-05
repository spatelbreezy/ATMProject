package ATM;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel homePage, creationPage, loggedIn;
	private JLabel title, accountNum, pin, enterName, createTitle, newPin, generatedPin, loggedTitle;
	private JTextField inputAccNum, inputName, desiredPin;
	private JPasswordField inputPin;
	private JButton createAcc, loginButton, finalizeAcc, goBackButton, deposit, logOut, viewBalance, withdraw;
	private AccManager manager;
	private Random rd;
	private Account currAcc;

	public Frame() {
		manager = new AccManager();
		this.setTitle("ATM Machine");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(500, 500);

		ImageIcon image = new ImageIcon("ATM.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(Color.LIGHT_GRAY);

		loginPage();
		creationPage();
		loggedInPage();
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand()); // prints the text value of button

		if (e.getSource() == loginButton) {
			String accNum = inputAccNum.getText();
			char[] before = inputPin.getPassword();
			String pin = new String(before);
			int idx = manager.findIndex(manager.findAccount(accNum, pin));
			currAcc = manager.findAccount(accNum, pin);

			if (idx != -1) {
				homePage.setVisible(false);
				loggedIn.setVisible(true);
				this.add(loggedIn);
				
			}

		} else if (e.getSource() == createAcc) {
			this.add(creationPage);
			homePage.setVisible(false);
			creationPage.setVisible(true);

		} else if (e.getSource() == finalizeAcc) {
			if (!(inputName.getText().isBlank() || desiredPin.getText().isBlank())) {
				rd = new Random();

				String generated = String.valueOf(rd.nextInt(99999));
				generatedPin.setText("Generated Account Number: " + generated);
				generatedPin.setVisible(true);

				String name = inputName.getText();
				Account account = new Account(name, desiredPin.getText(), generated);
				manager.addAccount(account);
			}

		} else if (e.getSource() == goBackButton) {
			generatedPin.setText(null);
			inputName.setText(null);
			desiredPin.setText(null);

			homePage.setVisible(true);
			creationPage.setVisible(false);

		} else if (e.getSource() == deposit) {
			String temp = JOptionPane.showInputDialog("How much would you like to deposit?");
			int amt = -1;
			try {
				amt = Integer.parseInt(temp);
				currAcc.addBalance(amt);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Invalid Amount", "Error", JOptionPane.WARNING_MESSAGE);
			}
		} else if (e.getSource() == logOut) {
			inputAccNum.setText(null);
			inputPin.setText(null);

			homePage.setVisible(true);
			loggedIn.setVisible(false);
		} else if (e.getSource() == viewBalance) {
			JOptionPane.showMessageDialog(null, "Current Account Balance: " + currAcc.balance, "Current Balance", JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == withdraw) {
			String temp = JOptionPane.showInputDialog("How much would you like to withdraw for your balance?");
			int amt = -1;
			
			try {
				amt = Integer.parseInt(temp);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Invalid Amount", "Error", JOptionPane.WARNING_MESSAGE);
			}
			
			if (!currAcc.withdrawBalance(amt)) {
				JOptionPane.showMessageDialog(null, "Withdraw denied: Too large", "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	public void loginPage() {
		homePage = new JPanel();
		this.add(homePage);
		homePage.setLayout(null);

		title = new JLabel("BANKING SERVICES");
		title.setBounds(185, 20, 125, 25);
		homePage.add(title);

		accountNum = new JLabel("Account Number:");
		accountNum.setBounds(10, 50, 125, 25);
		homePage.add(accountNum);

		pin = new JLabel("Enter PIN:");
		pin.setBounds(10, 80, 125, 25);
		homePage.add(pin);

		inputAccNum = new JTextField(25);
		inputAccNum.setBounds(125, 50, 165, 25);
		homePage.add(inputAccNum);

		inputPin = new JPasswordField(25);
		inputPin.setBounds(125, 80, 165, 25);
		homePage.add(inputPin);

		loginButton = new JButton("Login");
		loginButton.setBounds(10, 115, 75, 25);
		loginButton.addActionListener(this);
		homePage.add(loginButton);
	}
	
	public void creationPage() {
		creationPage = new JPanel();
		creationPage.setLayout(null);

		createAcc = new JButton("Create Account");
		createAcc.setBounds(128, 115, 125, 25);
		createAcc.addActionListener(this);
		homePage.add(createAcc);

		enterName = new JLabel("Enter name:");
		enterName.setBounds(10, 50, 125, 25);
		creationPage.add(enterName);

		createTitle = new JLabel("Create an Account");
		createTitle.setBounds(185, 20, 125, 25);
		creationPage.add(createTitle);

		inputName = new JTextField(25);
		inputName.setBounds(125, 50, 165, 25);
		creationPage.add(inputName);

		newPin = new JLabel("Enter pin:");
		newPin.setBounds(10, 80, 125, 25);
		creationPage.add(newPin);

		desiredPin = new JTextField(25);
		desiredPin.setBounds(125, 80, 165, 25);
		creationPage.add(desiredPin);

		finalizeAcc = new JButton("Finish");
		finalizeAcc.setBounds(10, 115, 75, 25);
		finalizeAcc.addActionListener(this);
		creationPage.add(finalizeAcc);

		generatedPin = new JLabel("");
		generatedPin.setBounds(128, 115, 275, 25);
		creationPage.add(generatedPin);
		generatedPin.setVisible(false);

		goBackButton = new JButton("Go Back");
		goBackButton.setBounds(10, 145, 75, 25);
		goBackButton.addActionListener(this);
		creationPage.add(goBackButton);
	}
	
	public void loggedInPage() {
		loggedIn = new JPanel();
		loggedIn.setLayout(null);

		loggedTitle = new JLabel("Choices");
		loggedTitle.setBounds(185, 20, 125, 25);
		loggedIn.add(loggedTitle);

		deposit = new JButton("Deposit");
		deposit.setBounds(10, 50, 75, 25);
		deposit.addActionListener(this);
		loggedIn.add(deposit);

		logOut = new JButton("Log Out");
		logOut.setBounds(10, 150, 75, 25);
		logOut.addActionListener(this);
		loggedIn.add(logOut);
		
		viewBalance = new JButton("View Balance");
		viewBalance.setBounds(110, 50, 120, 25);
		viewBalance.addActionListener(this);
		loggedIn.add(viewBalance);
		
		withdraw = new JButton("Withdraw");
		withdraw.setBounds(250, 50, 90, 25);
		withdraw.addActionListener(this);
		loggedIn.add(withdraw);
	}

}
