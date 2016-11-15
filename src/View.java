import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class View extends JFrame implements Observer{

	//Dimensions
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;
	
	//Constants
	private static final String TITLE = "Tic Tac Toe";
	private static final int COLUMNS = 3;
	private static final int ROWS = 3;
	
	//Variables
	private GameStatus gameStatus = GameStatus.PLAYER_1;
	
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
		outputArea = new JLabel(gameStatus.getLabelString());
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
				button[i][j] = new JButton();
				panel.add(button[i][j]);
				button[i][j].putClientProperty("ROW",new Integer(i));
				button[i][j].putClientProperty("COLUMN",new Integer(j));
			}
		}
	}
	
	public void setController(Controller controller){
		for(int i = 0;i<ROWS;i++){
			for(int j = 0;j<COLUMNS;j++){
				button[i][j].addActionListener(controller);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		gameStatus = (GameStatus)arg;
		outputArea.setText(gameStatus.getLabelString());
		if(gameStatus == GameStatus.DRAW 
				|| gameStatus == GameStatus.PLAYER_1_WON
				||gameStatus == GameStatus.PLAYER_2_WON){
			JOptionPane.showMessageDialog(this,
					gameStatus.getEnumString(),
					"GAME OVER",
					JOptionPane.INFORMATION_MESSAGE);
			
			reset();
		}
	}

	public static int getColumns() {
		return COLUMNS;
	}

	public static int getRows() {
		return ROWS;
	}

	public void setButtonText(JButton button) {
		button.setText(gameStatus.getEnumString());
		button.setEnabled(false);
	}
	
	private void reset() {
		gameStatus = GameStatus.PLAYER_1;
		outputArea.setText(gameStatus.getLabelString());
		for(int i = 0;i<ROWS;i++){
			for(int j = 0;j<COLUMNS;j++){
				button[i][j].setText("");
				button[i][j].setEnabled(true);
			}
		}
		
	}
}
