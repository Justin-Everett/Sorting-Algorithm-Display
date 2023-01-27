import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Algorithm extends JPanel implements KeyListener, ActionListener {

	private Timer timer;
	private int delay = 0;

	private int[] array = new int[100];

	private boolean stopped;
	private boolean running;

	private int counter;
	private int steps;
	
	private int width = 1000/array.length;

	public Algorithm() {
		timer = new Timer(delay, this);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer.start();
	}

	/*
	 * Starts the processes
	 */
	public void start() {
		this.steps = 0;
		this.counter = 0;
		assignRandomNumbers();
		running = true;
		stopped = false;
		repaint();
	}

	/*
	 * Converts a coordinate value into a pixel value for the x-axis
	 */
	public int coordsX(int x) {
		return (x * width)+50;
	}
		// array.length = 1000
	// 
	/*
	 * Converts a coordinate value into a pixel value for the y-axis
	 */
	public int coordsY(int y) {
		return 650 - (y * 20);
	}

	/*
	 * Fill the array with randomly generated numbers
	 */
	public void assignRandomNumbers() {
		for (int i = 0; i < array.length; i++) {
			this.array[i] = (int) (Math.random() * 25)+1;
		}
	}

	
	public void checkSorted() {
		stopped = true;
		for (int i = 0; i < array.length-1; i++) {
			if (array[i] > array[i+1]) {
				stopped = false;
			}
		}
		
	}
	public void runAlgorithmStep() {
		int temp;
		if (array[counter] > array[counter + 1]) {
			temp = this.array[counter + 1];
			array[counter + 1] = array[counter];
			array[counter] = temp;
		}
	}

	public void paint(Graphics g) {
		if (!stopped) {
			super.paint(g);
		}
		
		
		// Display the number in the array as a bar constructed of red boxes
		if (!stopped) {
			g.setColor(Color.red);
			for (int x = 0; x < this.array.length; x++) {
				g.fillRect(coordsX(x), coordsY(array[x]), width, (array[x])*20);
					//redBox.paintIcon(this, g, coordsX(x), coordsY(y));
			}
		}
		if (stopped) {
			g.setColor(Color.GREEN);
			for (int y = 0; y < array[counter]; y++) {
				g.fillRect(coordsX(counter), coordsY(y)-20, width, (y+1)*20);
			}
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			if (!stopped) {
				if (counter < array.length -steps- 1) {
					runAlgorithmStep();
					counter++;
				} else {
					steps++;
					counter = 0;
					checkSorted();
				}
				
				repaint();
			} else {
				if (counter < array.length - 1) {
					counter++;
					repaint();
				} else {
					start();
				}	
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == (KeyEvent.VK_SPACE)) {
			start();
		}

	}

}
