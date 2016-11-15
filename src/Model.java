import java.util.Observable;

public class Model extends Observable {
	
	private GameStatus[][] board;
	private GameStatus gameStatus = GameStatus.PLAYER_1;
	
	private int board_rows;
	private int board_columns;
	
	public Model(){}
	
	/**
	 * Create an array to store the user inputs to test
	 * 
	 * @param rows
	 * @param columns
	 */
	public void InitializeArray(int rows, int columns) {
		board = new GameStatus[rows][columns];
		board_rows = rows;
		board_columns = columns;
	}

	/**
	 * Update the button when it is clicked
	 * 
	 * @param row
	 * @param column
	 */
	public void updateButton(int row, int column) {
		board[row][column] = gameStatus;
		if(isGameOver()){
			if(gameStatus == GameStatus.PLAYER_1){
				gameStatus = GameStatus.PLAYER_1_WON;
			}else if(gameStatus == GameStatus.PLAYER_2){
				gameStatus = GameStatus.PLAYER_2_WON;
			}
			setChanged();
			notifyObservers(gameStatus);
			gameStatus = GameStatus.PLAYER_1;
			clearBoard();
		}else{
			switchPlayer();
			setChanged();
			notifyObservers(gameStatus);
		}
	}

	/**
	 * Choose which player is playing next
	 */
	private void switchPlayer() {
		if(gameStatus == GameStatus.PLAYER_1){
			gameStatus = GameStatus.PLAYER_2;
		}else{
			gameStatus = GameStatus.PLAYER_1;
		}
	}

	/**
	 * Check if the game is over
	 * 
	 * @return true if yes, false otherwise
	 */
	private boolean isGameOver() {
		int sumDiagonalLR = 0;
        int sumDiagonalRL = 0;
        int sumColumns = 0;
        int sumRows = 0;
        GameStatus lastBoardPosition;
        
        lastBoardPosition = board[0][0];
        for (int i = 0; i < board_rows; i++) {
        	if(lastBoardPosition == board[i][i] && board[i][i] != null){
        		sumDiagonalLR ++;
        		if(sumDiagonalLR == 3){
        			gameStatus = board[i][i];
        			return true;
        		}
        		continue;
        	}
        	break;
        }
        
        lastBoardPosition = board[0][2];
        for (int i = 0; i < board_rows; i++) {
        	if(lastBoardPosition == board[i][2 - i] && board[i][2 - i] != null){
        		sumDiagonalRL ++;
        		if(sumDiagonalRL == 3){
        			gameStatus = board[i][2-i];
        			return true;
        		}
        		continue;
        	}
        	break;
        }
        
        for (int i = 0; i < board_rows; i++) {
        	lastBoardPosition = board[i][i];
            for (int j = 0; j < board_columns; j++) {
            	if(lastBoardPosition == board[i][j] && board[i][j] != null){
            		sumRows ++;
            		if(sumRows == 3){
            			gameStatus = board[i][j];
            			return true;
            		}
            		continue;
            	}
                sumRows = 0;
            	break;
            }
        }
        
        for (int i = 0; i < board_rows; i++) {
        	lastBoardPosition = board[i][i];
            for (int j = 0; j < board_columns; j++) {
            	if(lastBoardPosition == board[j][i] && board[j][i] != null){
            		sumColumns ++;
            		if(sumColumns == 3){
            			gameStatus = board[j][i];
            			return true;
            		}
            		continue;
            	}
            	sumColumns = 0;
            	break;
            }
        }
        if(isFull()){
        	gameStatus = GameStatus.DRAW;
			return true;
        }
        return false;
	}
	
	/**
	 * Check if all the buttons have been clicked
	 * 
	 * @return true if full, false otherwise
	 */
	private boolean isFull() {
		for(int i = 0;i<board_rows;i++){
			for(int j = 0;j<board_columns;j++){
				if(board[i][j] == null){return false;}
			}
		}
		return true;
	}

	/**
	 * Clear the results table for the next game
	 */
	private void clearBoard() {
		for(int i = 0;i<board_rows;i++){
			for(int j = 0;j<board_columns;j++){
				board[i][j] = null;
			}
		}
	}

}