package items;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BuilderList { //Constroi a estrutura de palavras
	private static BuilderList obj;
	private String url;
	
	private BuilderList() {
		url = "../SO2/src/bd.txt";
	}
	
	public static BuilderList getInstance() { //Design Patterns Singleton
		if(obj == null) {
			obj = new BuilderList();
		}
		return obj;
	}
	
	public List<String> getList(){ //Gera a estrutura
		List<String> list = null;
		try(BufferedReader reader = new BufferedReader(new FileReader(url))){
			String line;
			list = new ArrayList<>();
			while((line = reader.readLine()) != null){
				list.add(line); //Adiciona cada linha na lista
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
		
	public void changeURL(String url) { //Muda o local do database
		this.url = url;
	}
}
