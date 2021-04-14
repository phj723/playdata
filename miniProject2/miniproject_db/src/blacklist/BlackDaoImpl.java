package blacklist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.conn.DbConnect;
import forum.Forum;

public class BlackDaoImpl implements BlackDao {
	private DbConnect db;

	public BlackDaoImpl() {
		db = DbConnect.getInstance();
	}

	@Override
	public void insert(BlackList b) {
		Connection conn = db.conn();
		String sql = "insert into blacklist values(?)";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 물음표 매칭
			pstmt.setString(1, b.getId());

			// sql 실행
			cnt = pstmt.executeUpdate();
			System.out.println("blacklist 등록 완료");
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
	public ArrayList<BlackList> selectAll() {
		ArrayList<BlackList> list = new ArrayList<BlackList>();
		Connection conn = db.conn();
		String sql = "select * from blacklist";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BlackList b = new BlackList(rs.getString(1));
				list.add(b);
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
	public BlackList select(String id) {
		Connection conn = db.conn();
		String sql = "select * from blacklist where id = ?";
		ResultSet rs = null;
		BlackList b = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				b = new BlackList(rs.getString(1));
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
		return b;

	}

	@Override
	public void delete(BlackList b) {
		Connection conn = db.conn();
		String sql = "delete blacklist where id = ?";
		int cnt = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 물음표 매칭
			pstmt.setString(1, b.getId());

			// sql 실행
			cnt = pstmt.executeUpdate();
			System.out.println("blacklist 삭제 완료");
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
