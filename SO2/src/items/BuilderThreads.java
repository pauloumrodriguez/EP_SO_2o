package items;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class BuilderThreads { //Constroi um array de Threads
	
	private Random random; //Gerador de números aleatórios
	private static BuilderThreads obj;
	
	private BuilderThreads() {
		random = new Random();
	}
	
	public static BuilderThreads getInstance() { //Design Patterns Singleton
		if(obj == null) {
			obj = new BuilderThreads();
		}
		return obj;
	}
	
	public List<Item> build(int qWriters, int qReaders, List<String> db, Solution slt){ //Gera o array
		List<Item> list = new ArrayList<>();
		for(int i = 0; i < 100; i++) {
			list.add(null);
		}
		int i = 0;
		int j = 0;
		while (i < qWriters) { //Preenche aleatoriamente a quantidade de Writers
             int num = random.nextInt(100);
             if (list.get(num) == null) {
                 list.set(num, new Writer(db, slt));
                 i++;
             }
         }
         while (j < qReaders) { //Preenche aleatoriamente a quantidade de Readers
             int num = random.nextInt(100); // 0 a 99
             if (list.get(num) == null) {
                 list.set(num, new Reader(db, slt));
                 j++;
             }
         }
         return list;
	}
}
