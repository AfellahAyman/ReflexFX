import java.time.*;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Jeu_Code {
	private static ArrayList<Long> Temps = new ArrayList<Long>();
	private static Circle circle;
	private static double dx,dy;
	private static int i;
	private static long startTime;
	private Text T;
	private int Score = 0;
	public Jeu_Code(Circle circle) {
		i=0;
		this.circle = circle;
		circle.setStyle("-fx-fill: lightblue;");
		this.dx = Math.random()*Jeu.getX();
		this.dy = Math.random()*Jeu.getY();
//		circle.setRadius(50f);
		startTime = System.currentTimeMillis();
		
		circle.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		    	if(mouseEvent.getSource() instanceof Circle) {
		    		//System.out.println("Click !");
		    	    circle.setCenterX(dx); 
		    	    circle.setCenterY(dy); 
		    	    dx = Math.random()*Jeu.getX();
		        	dy = Math.random()*Jeu.getY();
		    		long stopTime = System.currentTimeMillis();
		    		Temps.add(stopTime - startTime);
		    		System.out.println(stopTime - startTime);
		    		synchronized(Jeu_Run.Monitor) {
		    		Jeu_Run.Monitor.notify();
		    		}
		    		Cacher();
		    		if(i==10)
		    			verifier();
		    	}
		    }
		});
		}
	public static void Jeu() {

    }
	public static void verifier() {
		    		if(i==10) {
		    			int Score = 0 ;
		    			for(int j = 0; j < i ; j++) {
		    				Score+=Temps.get(j);
		    			}
		    			
		    			Score/=10;
		    			
		    		System.out.println(Score);
		    		
		    		if(Score < Base.lastScore()) {
		    			
		    			System.out.println("Vous Avez Battu Un Nouveau Record: "+Score+"ms.");
		    			Jeu.changerTexte("Vous Avez\nBattu Un Nouveau\nRecord: "+Score+"ms.");
		    			
		    	        
		    			Base.nouveauRecord(Score);
		    		}
		    		else {
		    			
		    			Jeu.changerTexte("Rejouer ?");
		    			
		    		}
		    		
		    		}
	}
	public static int getI() {
		return i;
	}
	public static void I() {
		i++;
		
	}
	public static void Afficher() {
		circle.setRadius(50f);
		startTime = System.currentTimeMillis();
	}
	public static void Cacher() {
		circle.setRadius(0f);
	}
}
