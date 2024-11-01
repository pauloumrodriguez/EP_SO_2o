package items;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class SolutionNoRW implements Solution { // Solução sem Readers and Writers
	
	private final ReentrantLock lock; // Variável de trava
	private static Solution obj;

	private SolutionNoRW() {
		lock = new ReentrantLock();
	}
	
	public static Solution getInstance() { // Design Patterns Singleton
		if (obj == null) {
			obj = new SolutionNoRW();
		}
		return obj;
	}
	
	public void read(List<String> db) { // Leitura
		lock.lock(); // Não permite outras threads escreverem
		try {
			Random random = new Random();
			
			for(int i = 0; i < 100; i++) {//Acessa 100 vezes
				db.get(random.nextInt(db.size()));//Realiza leitura
			}
			
			// Pausa de 1ms após 100 operações de leitura
			Thread.sleep(1); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock(); // Destrava região crítica
		}
	}
	
	
	public void write(List<String> db) { // Escrita
		lock.lock(); // Não permite outras threads entrem
		try {
			Random random = new Random();
			
			for(int i = 0; i < 100; i++) {//Acessa 100 vezes
				db.set(random.nextInt(db.size()), "MODIFICADO"); //Realiza escrita
			}
			
			// Pausa de 1ms após 100 operações de escrita
			Thread.sleep(1); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock(); // Destrava região crítica
		}
	}
}
