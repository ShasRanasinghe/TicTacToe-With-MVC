import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class View extends JFrame implements Observer{

	//Dimensions
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;
	
	//Constants
	private static final String TITLE = "Tic Tac Toe";
	private static final String PLAYER_1_TURN = "PLAYER 1! YOU ARE THE CAPTAIN NOW!!";
	private static final String PLAYER_2_TURN = "PLAYER 2! YOU ARE THE CAPTAIN NOW!!";
	private static final int COLUMNS = 3;
	private static final int ROWS = 3;
	
	//Frame Items
	private JLabel outputArea;
	private JButton[][] button;
	
	public View(){
		super(TITLE);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		makeFrame();
		pack();
		setSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
		setLocation(SCREEN_SIZE.width/2 - getWidth()/2,SCREEN_SIZE.height/2 - getHeight()/2);
		setVisible(true);
		
	}

	private void makeFrame() {
		outputArea = new JLabel(PLAYER_1_TURN);
		outputArea.setHorizontalAlignment(SwingConstants.CENTER);
		add(outputArea, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel,BorderLayout.CENTER);
		panel.setLayout(new GridLayout(ROWS,COLUMNS));
		
		addButtons(panel);
	}

	private void addButtons(JPanel panel) {
		button = new JButton[COLUMNS][ROWS];
		for(int i = 0;i<ROWS;i++){
			for(int j = 0;j<COLUMNS;j++){
				button[i][j] = new JButton("");
				panel.add(button[i][j]);
				button[i][j].addActionListener(new Controller(i,j));
				button[i][j].set
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
