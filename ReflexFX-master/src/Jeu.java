//import javax.swing.event.ChangeListener;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;


import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javafx.stage.Stage;
public class Jeu extends Application {
	private static Circle Cercle;
	private static Pane Panel_Centrale;
	private static Button Commencer;
	private static Button Meilleurs_Scores;
	private static Text T;
	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("Jeu");
		arg0.show();
		
		Base.Connect();
		
        BorderPane borderPane  = new BorderPane();
        Scene scene = new Scene(borderPane,500,500);
        borderPane.setPrefSize(scene.getWidth(), scene.getHeight());
		
        borderPane.setCenter(Cercle);
        
		Commencer = new Button("Commencer");
		Meilleurs_Scores = new Button("Meilleurs Scores");
		
		Cercle = new Circle();
		Cercle.setCenterX(100.0f);
		Cercle.setCenterY(100.0f);
		
		//circle.setRadius(50.0f);
		Panel_Centrale = new Pane();
		//Panel_Centrale.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		Panel_Centrale.getChildren().add(Cercle);
		
		
		
		T = new Text("...");
		T.setFill(Color.GREEN);
		T.setStyle("-fx-font-size: 50px;");
		
		StackPane Pane = new StackPane();
		Pane.setAlignment(T,Pos.CENTER);
		Pane.setAlignment(Panel_Centrale,Pos.CENTER);
		borderPane.setCenter(Pane);
		
		Panel_Centrale.getChildren().add(T);
	    Panel_Centrale.setStyle("-fx-background-color: #fefbd8	; -fx-border-color: green; -fx-border-width: 2");
	    
	    Pane.getChildren().addAll(Panel_Centrale,T);
	    
	    StackPane stack = new StackPane();
	    stack.setStyle("-fx-background-color: #36486b;");
	    
	    stack.getChildren().add(Commencer);
	    stack.setAlignment(Commencer,Pos.BOTTOM_LEFT);
	    stack.setAlignment(Meilleurs_Scores,Pos.BOTTOM_RIGHT);
	    stack.getChildren().add(Meilleurs_Scores);
	    
	    borderPane.setBottom(stack);
	    //Rectangle clip = new Rectangle(P.getWidth(),P.getHeight());
	    arg0.setScene(scene);
//	    System.out.println(Panel_Centrale.getWidth()+" "+Panel_Centrale.getHeight());
	    Rectangle clip = new Rectangle(Panel_Centrale.getWidth()+12,Panel_Centrale.getHeight()+12);
	    Panel_Centrale.setClip(clip);
		Commencer.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		    	if(mouseEvent.getSource() instanceof Button) {
		    		Jeu.changerBouttonCommencer(true);
		    		Jeu.changerTexte("");
		    		Jeu_Code a = new Jeu_Code(Cercle);
		    		new Jeu_Run().start();
		    	}
		    }
		});
		Meilleurs_Scores.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		    	if(mouseEvent.getSource() instanceof Button) {
		    		new Tableau();
		    	}
		    }
		});
		arg0.setResizable(false);
		}
	public static double getX() {
		return Panel_Centrale.getWidth();
	}
	public static double getY() {
		return Panel_Centrale.getHeight();
	}
	public static void changerTexte(String S) {
		T.setText(S);
	}
	public static void changerBouttonCommencer(boolean B) {
		Commencer.setDisable(B);
	}
public static void main(String[] args) {
	launch(args);
}

}

	