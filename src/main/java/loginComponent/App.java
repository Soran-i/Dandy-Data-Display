package loginComponent;

import javax.swing.JFrame;

/**
 * @author Soran
 * The main class creted a login JFrame that is used to start the program
 */
public class App {

	/**
	 * the main method for running the program
	 */
	public static void main(String args[]) {
		
    	JFrame frame = LoginGUI.getInstance();
    	frame.setSize(300, 130);
    	frame.add(LoginGUI.loginPanel);
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	frame.setVisible(true);
	}
}
