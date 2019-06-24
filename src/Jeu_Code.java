
import java.util.ArrayList;

import com.sun.prism.paint.Color;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Jeu_Code extends Thread {
	private static ArrayList<String> Colors = new ArrayList<String>();
	private static ArrayList<Long> Temps = new ArrayList<Long>();
	private static Circle circle;
	private static double dx,dy;
	private static int i;
	private static long startTime;
	private static boolean On = true;
	public Jeu_Code(Circle circle) {
		i=0;
		this.circle = circle;
		circle.setStyle("-fx-fill: lightblue;");
		this.dx = Math.random()*Jeu.getX();
		this.dy = Math.random()*Jeu.getY();
		Colors.add("-fx-fill: lightblue;");
		Colors.add("-fx-fill: red;");
		Colors.add("-fx-fill: black;");
		Colors.add("-fx-fill: green;");
		Colors.add("-fx-fill: purple;");
		Colors.add("-fx-fill: orange;");
		Colors.add("-fx-fill: pink;");
//		circle.setRadius(50f);
		//circle.setStyle("-fx-fill: lightblue;");
		startTime = System.currentTimeMillis();
		
		circle.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		    	if(mouseEvent.getSource() instanceof Circle) {
		    		On = false;
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
	public void run() {
		double j =circle.getRadius()+1;
		if(j>50) {
			j=50;
			On = false;
			}
			circle.setRadius(j);
			System.out.println(j);
    }
	public static void Afficher() {
		On = true;
		circle.setRadius(5f);
		circle.setStyle(Colors.get((int)(Math.random()*Colors.size())));
		startTime = System.currentTimeMillis();
	}
	public static void Cacher() {
		circle.setRadius(0f);
	}
	public static boolean getOn() {
		return On;
	}
}
