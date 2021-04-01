package loginComponent;

import javax.swing.JFrame;

public class App {

	public static void main(String args[]) {
		
    	JFrame frame = LoginGUI.getInstance();
    	frame.setSize(300, 130);
    	frame.add(LoginGUI.loginPanel);
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	frame.setVisible(true);
	}
}
