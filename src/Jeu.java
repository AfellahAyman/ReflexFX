import javax.swing.event.ChangeListener;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
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
		arg0.show();/*
		VBox layout = new VBox(50);
		layout.setAlignment(Pos.CENTER);
		
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		Scene scene = new Scene(tabPane,300,300);
		
		Tab T = new Tab("A");
		tabPane.getTabs().add(T);
		
		
		Pane P = new Pane();
		P.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		T.setContent(layout);
		arg0.setScene(scene);
		*/
		
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
/*		P.getChildren().add(circle);
		layout.getChildren().addAll(P,b);*/
/*       Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Thread(this);

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }

        });
        thread.start();*/
/*		B C = new B(new A(circle));
		C.start();*/
/*		arg0.widthProperty().addListener((obs, oldVal, newVal) -> {
			System.out.println(P.getWidth()+" "+P.getHeight());
			//System.out.println("Width: " + arg0.getWidth()+"Height: " + arg0.getHeight());
			clip.setHeight(P.getHeight()+1);
			clip.setWidth(P.getWidth()+1);
			P.setClip(clip);
		});

		arg0.heightProperty().addListener((obs, oldVal, newVal) -> {
			System.out.println(P.getWidth()+" "+P.getHeight());
			// System.out.println("Width: " + arg0.getWidth()+" Height: " + arg0.getHeight());
			clip.setHeight(P.getHeight()+1);
			clip.setWidth(P.getWidth()+1);
			 P.setClip(clip);
		});
		arg0.maximizedProperty().addListener(e -> {
			if(true) {
			System.out.println("x: "+P.getWidth()+" y:"+P.getHeight());
			clip.setHeight(P.getHeight()+1);
			clip.setWidth(P.getWidth()+1);
			 P.setClip(clip);}
		});*/
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

	