package sinc.user.model.sql;

import java.util.List;

public interface UserDao {

	public Object 		loginRow(Object obj);
	public int	  		joinRow(Object obj);
	public List<Object> boardSelectList();
}
