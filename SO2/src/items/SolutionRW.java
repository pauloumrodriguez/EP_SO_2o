package items;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.*;

public class SolutionRW implements Solution { // Solução Readers and Writers
	
	private final ReentrantReadWriteLock lock; // Variável de trava
	private static Solution obj;
	
	private SolutionRW() {
		lock = new ReentrantReadWriteLock();
	}
	
	public static Solution getInstance() { // Design Patterns Singleton
		if (obj == null) {
			obj = new SolutionRW();
		}
		return obj;
	}
	
	public void read(List<String> db) { // Leitura
		ReadLock key = lock.readLock(); // Adquire a chave de leitura
		key.lock(); // Não permite outras threads escreverem
		try {
			Random random = new Random();
			
			for(int i = 0; i < 100; i++) { //Acessa 100 vezes
				db.get(random.nextInt(db.size())); //Realiza a leitura
			}
			
			// Pausa de 1ms após 100 operações de escrita
			Thread.sleep(1); // Pausa dentro da região crítica
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			key.unlock(); // Destrava região crítica
		}
	}
	
	
	public void write(List<String> db) { // Escrita
		WriteLock key = lock.writeLock(); // Obtêm chave de escrita
		key.lock(); //Não permite outras threads entrarem
		try {
			Random random = new Random();
			
			for(int i = 0; i < 100; i++) { //Acessa 100 vezes
				db.set(random.nextInt(db.size()), "MODIFICADO"); //Realiza a escrita
			}
			
			// Pausa de 1ms após 100 operações de escrita
			Thread.sleep(1); // Pausa dentro da região crítica
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			key.unlock(); // Destrava região crítica
		}
	}
}
