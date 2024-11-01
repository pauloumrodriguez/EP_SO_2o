package items;

import java.util.List;


public class Reader extends Thread implements Item { //Thread que realiza leitura
	private List<String> db; //Estrutura de palavras
	private Solution slt; //Tipo de solução
	
	public Reader(List<String> db, Solution slt) {
		this.db = db;
		this.slt = slt;
	}
	
	public void action(List<String> db) {
		slt.read(db); //realiza a leitura
	}
	
	@Override
	public void run() {
		this.action(db); //Executa a leitura
	}
	
	public void setSolution(Solution slt) { //Muda a solução utilizada
		this.slt = slt;
	}
}