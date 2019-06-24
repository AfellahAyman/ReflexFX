import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Tableau  {
	private TableView table = new TableView();
	public Tableau(){
		
		Stage arg0 = new Stage();
		arg0.setTitle("Tableau");
		arg0.show();
		BorderPane borderPane  = new BorderPane();
        Scene scene = new Scene(borderPane);
        arg0.setScene(scene);
        
        Label Top_10 = new Label("Top 10:");
        Top_10.setStyle("-fx-font-size: 25px;");
        borderPane.setTop(Top_10);
        
        TableColumn Pseudo = new TableColumn("Pseudo");
        Pseudo.setMinWidth(150);
        TableColumn Score = new TableColumn("Score");
        Score.setMinWidth(100);
        table.getColumns().addAll(Pseudo,Score);
        table.setMaxWidth(250);
        borderPane.setCenter(table);
        table.setEditable(false);
        
        table.setItems(Base.Top10(Pseudo,Score));
        
        arg0.sizeToScene();
        
	}

}
