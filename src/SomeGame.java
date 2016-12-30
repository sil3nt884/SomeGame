import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class SomeGame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameRunner game = new GameRunner();

	public SomeGame() {
		init();

	}

	public void init() {
		this.setSize(0,0);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(game.getC());
		keys();
	
		

	}

	public void keys() {

		game.getC().addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				
				game.KeysPressed(e);
				
			}

			public void keyReleased(KeyEvent e) {
				game.KeysReleased(e);
			}

		});
	}

	public static void main(String[] args) {
		new SomeGame();
	}

}
