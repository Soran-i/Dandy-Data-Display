package loginComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.google.gson.JsonObject;

import statsVisualiser.gui.MainUI;

/**
 * @author Soran
 * @version 1.3 This class represents the GUI componenet of the login system
 * 
 */

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private static LoginGUI instance;
	static JPanel loginPanel;

	/**
	 * @author Soran The constructor is used to set up an instance of the GUI JFrame
	 * @return instance of JFrame
	 */
	public static LoginGUI getInstance() {
		if (instance == null)
			instance = new LoginGUI();
		return instance;
	}

	/**
	 * @author Soran Helper method that setps up all the text fields on the JFrame
	 *         instance
	 */
	private LoginGUI() {
		super("Login");

		final Login login = new LoginProxy("src/test/resources/userinfo.json");
		final JsonObject user = new JsonObject();

		loginPanel = new JPanel();

		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));

		JButton loginButton = new JButton("Submit!");
		loginButton.setFocusable(false);

		JPanel usernamePanel = new JPanel();
		final JTextField tfUsername = new JTextField(15);
		JLabel txtUser = new JLabel("Username: ");

		usernamePanel.add(txtUser);
		usernamePanel.add(tfUsername);

		JPanel passwordPanel = new JPanel();
		final JPasswordField tfPassword = new JPasswordField(15);
		JLabel txtPassword = new JLabel("Password: ");

		passwordPanel.add(txtPassword);
		passwordPanel.add(tfPassword);

		loginPanel.add(usernamePanel);
		loginPanel.add(passwordPanel);
		loginPanel.add(loginButton);

		final JFrame LoginWindowFrame = this;
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String textFieldUsername = tfUsername.getText();
				char[] textFieldPasswordChar = tfPassword.getPassword();
				String textFieldPassword = String.valueOf(textFieldPasswordChar);
				user.addProperty("username", textFieldUsername);
				user.addProperty("password", textFieldPassword);

				try {
					if (!login.verifyUserInfo(user))
						System.exit(0);
					else {
						JFrame frame = MainUI.getInstance();
						frame.setSize(900, 600);
						frame.pack();
						frame.setVisible(true);

						LoginWindowFrame.dispatchEvent(new WindowEvent(LoginWindowFrame, WindowEvent.WINDOW_CLOSING));

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e, "Error In logging in", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

	}
}
