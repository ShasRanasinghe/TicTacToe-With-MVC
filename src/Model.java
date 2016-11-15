import java.util.Observable;

public class Model extends Observable {

	private GameStatus[][] board;
	private GameStatus gameStatus = GameStatus.PLAYER_1;
	
	public Model(){}
	
	public void InitializeArray(int rows, int columns) {
		board = new GameStatus[rows][columns];
	}

	public void updateButton(int row, int column) {
		board[row][column] = gameStatus;
		switchPlayer();
		setChanged();
		notifyObservers(gameStatus);
	}

	private void switchPlayer() {
		if(isGameOver()){
			return;
		}else if(gameStatus == GameStatus.PLAYER_1){
			gameStatus = GameStatus.PLAYER_2;
		}else{
			gameStatus = GameStatus.PLAYER_1;
		}
	}

	private boolean isGameOver() {
		gameStatus = GameStatus.PLAYER_1_WON;
		return true;
	}

}