import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Controller implements ActionListener {

	private View view;
	private Model model;
	
	
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		model.InitializeArray(View.getRows(),View.getColumns());
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		 JButton button = (JButton)e.getSource();
		 int row = (int) button.getClientProperty("ROW");
		 int column = (int) button.getClientProperty("COLUMN");
		 view.setButtonText(button);
		 model.updateButton(row,column);
	}

}
