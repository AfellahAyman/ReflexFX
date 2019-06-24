public class Jeu_Run extends Thread{
	public final static Object Monitor = new Object();
	public  void run() {
		while(Jeu_Code.getI()<10) {
			try {
				sleep((int) (Math.random()*900+250));  //Temps aléatoire pour l'apparition du cercle.
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Jeu_Code.Afficher();
			Jeu_Code.I();
			try {
				synchronized(Monitor) {
					Monitor.wait();		
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Jeu.changerBouttonCommencer(false);
//		Jeu_Code.verifier();
	}
}
