package items;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SolutionRW implements Solution { //Solução Readers and Writers
	
	private final ReentrantReadWriteLock lock; //Varíavel de trava
	private static Solution obj;
	
	private SolutionRW() {
		lock = new ReentrantReadWriteLock();
	}
	
	public static Solution getInstance() { //Design Patterns Singleton
		if(obj == null) {
			obj = new SolutionRW();
		}
		return obj;
	}
	
	public void read(List<String> db) { //Leitura
		lock.readLock().lock(); //Não permite writers entrar
		Random random = new Random();
		db.get(random.nextInt(100));
		lock.readLock().unlock(); //Destrava para writers
	}
	
	public void write(List<String> db) { //Escrita
		lock.writeLock().lock(); //Não permite outras threads entrar
		Random random = new Random();
		db.set(random.nextInt(100), "MODIFICADO");
		lock.writeLock().unlock(); //Destrava região crítica
	}
}
