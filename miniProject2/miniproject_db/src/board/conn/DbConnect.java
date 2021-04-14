package board.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
	private static DbConnect db = new DbConnect();
	
	private String driver = "oracle.jdbc.driver.OracleDriver"; // 오라클용 드라이버 명
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 로그인에 필요한 서버 주소 및 sid

	private DbConnect() {
	}

	public static DbConnect getInstance() {
		return db;
	}

	public Connection conn() {
		Connection conn = null;
		try {
			Class.forName(driver); //드라이버 로드
			conn = DriverManager.getConnection(url, "hr", "hr"); //db서버에 접속
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
