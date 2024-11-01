package items;

import java.util.List;


public class Writer extends Thread implements Item {//Thread que realiza escrita
	private List<String> db;//Estrutura de palavras
	private Solution slt; //Solução implementada
	
	public Writer(List<String> db, Solution slt) {
		this.db = db;
		this.slt = slt;
	}
	
	public void action(List<String> db) { //Realiza escrita
		slt.write(db);
	}
	
	@Override
	public void run() {
		this.action(db); //Executa a escrita
	}
	
	public void setSolution(Solution slt) { //Altera a solução utilizada
		this.slt = slt;
	}
}