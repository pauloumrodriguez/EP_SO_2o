package items;

import java.util.List;

public interface Item { //Interface para cada tipo de Thread
	public void action(List<String> db);
	public void setSolution(Solution slt);
}
