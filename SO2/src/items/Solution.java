package items;

import java.util.List;

public interface Solution { //Interface para as soluções de exclusão mútua
	public void read(List<String> db);
	public void write(List<String> db);
}
