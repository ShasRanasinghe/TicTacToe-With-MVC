
public enum GameStatus {
	PLAYER_1("X","PLAYER 1! YOU ARE THE CAPTAIN NOW!!"), 
	PLAYER_2("O","PLAYER 2! YOU ARE THE CAPTAIN NOW!!"), 
	PLAYER_1_WON("WINNER IS PLAYER 1!!!!"), 
	PLAYER_2_WON("WINNER IS PLAYER 2"), 
	DRAW("THE GAME IS A DRAW");
	
	private String enumString;
	private String labelText;

	GameStatus(String enumString){
		this.enumString = enumString;
		labelText = " ";
	}
	
	GameStatus(String enumString, String labelText){
		this.enumString = enumString;
		this.labelText = labelText;
		
	}
	
	public String getEnumString() {
		return enumString;
	}

	public String getLabelString() {
		return labelText;
	}
}
