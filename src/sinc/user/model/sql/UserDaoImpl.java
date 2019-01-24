package sinc.user.model.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import sinc.board.model.vo.BoardVO;
import sinc.user.model.vo.UserVO;

public class UserDaoImpl implements UserDao {

	private static SqlSessionFactory factory;
	SqlSession session;
	static {
		System.out.println("------------------- mybatis loading --------------------");
		try {
			factory = new SqlSessionFactoryBuilder()
					.build(Resources.getResourceAsReader("resource/config/configuration.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserDaoImpl() {
		session = factory.openSession(true); // DML auto-commit
	}

	@Override
	public Object loginRow(Object obj) {
		return session.selectOne("sinc.test.mybatis.loginRow", obj); // testDQM에 정의된 tag id
	}

	@Override
	public int joinRow(Object obj) {
		return session.insert("sinc.test.mybatis.joinRow", obj);
	}

	@Override
	public List<Object> boardSelectList() {
		return session.selectList("sinc.test.mybatis.boardListRow");
	}

}
