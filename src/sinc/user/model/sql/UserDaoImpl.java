package sinc.user.model.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import sinc.board.model.vo.BoardVO;
import sinc.user.model.vo.UserVO;

public class UserDaoImpl implements UserDao {

	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	public static final String USER = "hr";
	public static final String PASSWD = "hr";

	public UserDaoImpl() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object loginRow(Object obj) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSql = "SELECT ID, PWD, NAME, POINT, DEPT FROM FRM_USERS_TBL WHERE ID = ? AND PWD = ?"; // * 가능
		Object entity = null; // Object

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			pstmt = conn.prepareStatement(selectSql);
			pstmt.setString(1, ((UserVO) obj).getId());
			pstmt.setString(2, ((UserVO) obj).getPwd());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = new UserVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
				System.out.println(entity);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return entity;
	}

	@Override
	public int joinRow(Object obj) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String insertSql = "INSERT INTO FRM_USERS_TBL(ID, PWD, NAME, DEPT) VALUES (?, ?, ?, ?)";
		int flag = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, ((UserVO) obj).getId());
			pstmt.setString(2, ((UserVO) obj).getPwd());
			pstmt.setString(3, ((UserVO) obj).getName());
			pstmt.setString(4, ((UserVO) obj).getDept());
			flag = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public List<Object> boardSelectList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSql = "SELECT * FROM FRM_BOARD_TBL";
		List<Object> boards = new Vector<>();
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			pstmt = conn.prepareStatement(selectSql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				boards.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return boards;
	}

}
