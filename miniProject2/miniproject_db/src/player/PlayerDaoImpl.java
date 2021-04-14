package player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.conn.DbConnect;

public class PlayerDaoImpl implements PlayerDao {
	private DbConnect db;

	public PlayerDaoImpl() {
		db = DbConnect.getInstance();
	}

	@Override
	public void insert(Player p) {
		Connection conn = db.conn();
		String sql = "insert into players values(?,?,?,sysdate,default)";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql); // 실행할 sql문으로 preparedstatement 객체 생성
			// 물음표 매칭
			pstmt.setString(1, p.getId());
			pstmt.setString(2, p.getPwd());
			pstmt.setString(3, p.getNickName());

			// sql 실행
			cnt = pstmt.executeUpdate(); // executeUpdate() : 쓰기(insert, update, delete) 작업 실행 => 적용된 줄 수 반환(int)
											// executeQuery() : 읽기(select) 작업 실행 => 검색결과 반환(ResultSet)
			System.out.println("회원 가입이 완료되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Player select(String id) {
		Connection conn = db.conn();
		String sql = "select * from players where id = ?";
		ResultSet rs = null; // 검색 결과 담을 변수
		Player p = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // 검색이니까 query()로, 1줄 검색 or 없거나

			if (rs.next()) { // 검색한 한줄이 있나? 있으면 true, 없으면 false
				p = new Player(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return p;
	}

	@Override
	public Player selectnick(String nickname) {
		Connection conn = db.conn();
		String sql = "select * from players where nickname = ?";
		ResultSet rs = null; // 검색 결과 담을 변수
		Player p = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery(); // 검색이니까 query()로, 1줄 검색 or 없거나

			if (rs.next()) { // 검색한 한줄이 있나? 있으면 true, 없으면 false
				p = new Player(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return p;
	}

	@Override
	public ArrayList<Player> getAll() {
		ArrayList<Player> list = new ArrayList<Player>();
		ResultSet rs = null;
		String sql = "select * from players where id <> 'manager' order by points desc";
		Connection conn = db.conn();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Player p = new Player(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public void update(String id, String new_pwd) {
		Connection conn = db.conn();
		String sql = "update players set pwd = ? where id = ?";
		int cnt = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 물음표 매칭
			pstmt.setString(1, new_pwd);
			pstmt.setString(2, id);

			// sql 실행
			cnt = pstmt.executeUpdate();
			System.out.println("회원정보 Update 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateNick(String id, String nickName) {
		Connection conn = db.conn();
		String sql = "update players set nickname = ? where id = ?";
		int cnt = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 물음표 매칭
			pstmt.setString(1, nickName);
			pstmt.setString(2, id);

			// sql 실행
			cnt = pstmt.executeUpdate();
			System.out.println("회원정보 Update 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void getPoint(String id, int points) {
		Connection conn = db.conn();
		String sql = "update players set points = ? where id = ?";
		int cnt = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 물음표 매칭
			pstmt.setInt(1, select(id).getPoints() + points);
			pstmt.setString(2, id);

			// sql 실행
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void lossPoint(String id, int points) {
		Connection conn = db.conn();
		String sql = "update players set points = ? where id = ?";
		int cnt = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 물음표 매칭
			pstmt.setInt(1, select(id).getPoints() - points);
			pstmt.setString(2, id);

			// sql 실행
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(String id) {
		Connection conn = db.conn();
		String sql = "delete players where id = ?";
		int cnt = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 물음표 매칭
			pstmt.setString(1, id);

			// sql 실행
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
