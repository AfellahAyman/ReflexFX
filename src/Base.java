import java.sql.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
public class Base {
	static Connection c = null;
	public static void Connect() {
		try{ 
		Class.forName("com.mysql.jdbc.Driver");  
		c = DriverManager.getConnection( "jdbc:mysql://sql2.freemysqlhosting.net/sql2296463","sql2296463","kF4*bZ6*");   
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public static int lastScore() {
		try{  
		Statement St = c.createStatement(); 
			ResultSet Rs = St.executeQuery("SELECT * FROM Tableau ORDER BY Score DESC "
					+ "LIMIT 1;"); 
			Rs.next();
//				System.out.println(Rs.getString(1)+"  "+Rs.getInt(2)); 
			return Rs.getInt(2);
		}
			catch(Exception e){
				System.out.println(e);
			}
		return -1;
	} 
	public static void nouveauRecord(int Score) {
		Stage arg0 = new Stage();
		arg0.setTitle("Tableau");
		arg0.show();
		
		VBox VBox  = new VBox(5);
		HBox HBox  = new HBox(5);
		HBox Ok_HBox  = new HBox(5);
        Scene scene = new Scene(VBox);
        
        arg0.setScene(scene);
        
        Label Label_Pseudo = new Label("Pseudo :");
        TextField Pseudo = new TextField();
        Button Ok = new Button("Ok");
        
        HBox.getChildren().addAll(Label_Pseudo,Pseudo);
        Ok_HBox.setAlignment(Pos.CENTER_RIGHT);
        Ok_HBox.getChildren().add(Ok);
        VBox.getChildren().addAll(HBox,Ok_HBox);
        arg0.sizeToScene();
        arg0.setResizable(false);
        
        Ok.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
		try{  
			String req = "INSERT INTO Tableau VALUES(?,?)";
			PreparedStatement St = c.prepareStatement(req);
			St.setString(1,Pseudo.getText());
			St.setInt(2,Score);
			St.executeUpdate();
			arg0.close();
		}
			catch(Exception e){
				System.out.println(e);
			}
	}
        });
}
	public static ObservableList Top10(TableColumn Pseudo, TableColumn Score) {
		ObservableList<ObservableList> data = FXCollections.observableArrayList();
		try {
		ResultSet Rs = c.createStatement().executeQuery("SELECT * FROM Tableau ORDER BY Score ASC "
				+ "LIMIT 10;");

		Pseudo.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(0).toString());
            }
        });
		Score.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(1).toString());
            }
        });
        while (Rs.next()) {
            ObservableList<String> ligne = FXCollections.observableArrayList();
            ligne.add(Rs.getString(1));
            ligne.add(Rs.getString(2));
            data.add(ligne);

        }
		} catch (Exception e) {
            e.printStackTrace();
        }
		return data;
	}
}

