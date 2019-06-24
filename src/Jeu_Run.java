import javafx.application.Platform;

public class Jeu_Run extends Thread{
	public final static Object Monitor = new Object();
	Jeu_Code Thread_Code;
	public Jeu_Run(Jeu_Code Thread_Code) {
		this.Thread_Code = Thread_Code;
	}
	public  void run() {
		while(Jeu_Code.getI()<10) {
			try {
				sleep((int) (Math.random()*900+250));  //Temps aléatoire pour l'apparition du cercle.
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Jeu_Code.Afficher();
			try {
				synchronized(Monitor) {
					while(Jeu_Code.getOn()) {
						Platform.runLater(Thread_Code);
						sleep(50);
					}
					Monitor.wait();		
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Jeu_Code.I();
		}
		Jeu.changerBouttonCommencer(false);
//		Jeu_Code.verifier();
	}
}
