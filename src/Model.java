import java.util.Observable;

public class Model extends Observable {
	
	private GameStatus[][] board;
	private GameStatus gameStatus = GameStatus.PLAYER_1;
	
	private int board_rows;
	private int board_columns;
	
	public Model(){}
	
	public void InitializeArray(int rows, int columns) {
		board = new GameStatus[rows][columns];
		board_rows = rows-1;
		board_columns = columns-1;
	}

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

	private void switchPlayer() {
		if(gameStatus == GameStatus.PLAYER_1){
			gameStatus = GameStatus.PLAYER_2;
		}else{
			gameStatus = GameStatus.PLAYER_1;
		}
	}

	private boolean isGameOver() {
		int sumDiagonalLR = 0;
        int sumDiagonalRL = 0;
        int sumColumns = 0;
        int sumRows = 0;
        GameStatus lastBoardPosition = board[0][0];
        
        for (int i = 0; i <= board_rows; i++) {
        	if(lastBoardPosition == board[i][i] && board[i][i] != null){
        		lastBoardPosition = board[i][i];
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
        for (int i = 0; i <= board_rows; i++) {
        	if(lastBoardPosition == board[i][2 - i] && board[i][2 - i] != null){
        		lastBoardPosition = board[i][2 - i];
        		sumDiagonalRL ++;
        		if(sumDiagonalRL == 3){
        			gameStatus = board[i][2-i];
        			return true;
        		}
        		continue;
        	}
        	break;
        }
        
        for (int i = 0; i <= board_rows; i++) {
        	lastBoardPosition = board[i][i];
            for (int j = 0; j <= board_columns; j++) {
            	if(lastBoardPosition == board[i][j] && board[i][j] != null){
            		lastBoardPosition = board[i][j];
            		sumColumns ++;
            		if(sumColumns == 3){
            			gameStatus = board[i][j];
            			return true;
            		}
            	}
            	if(lastBoardPosition == board[j][i] && board[j][i] != null){
            		lastBoardPosition = board[j][i];
            		sumRows ++;
            		if(sumRows == 3){
            			gameStatus = board[j][i];
            			return true;
            		}
            		continue;
            	}
            	sumColumns = 0;
                sumRows = 0;
            	break;
            }
        }
        if(isFull()){
        	gameStatus = GameStatus.DRAW;
			return true;
        }
        return false;
	}
	
	private boolean isFull() {
		for(int i = 0;i<board_rows;i++){
			for(int j = 0;j<board_columns;j++){
				if(board[i][j] != null){return false;}
			}
		}
		return true;
	}

	private void clearBoard() {
		for(int i = 0;i<board_rows;i++){
			for(int j = 0;j<board_columns;j++){
				board[i][j] = null;
			}
		}
	}

}