import java.awt.Color;

import javax.swing.JFrame;

public class Window {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame obj = new JFrame();
		Algorithm algorithm = new Algorithm();

		
		obj.setBounds(0, 0, 1100, 770);
		obj.setBackground(Color.black);
		obj.setResizable(false);
		
		obj.setTitle("Sorting Algorithm Display");
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(algorithm);
		obj.setVisible(true);
	}

}
