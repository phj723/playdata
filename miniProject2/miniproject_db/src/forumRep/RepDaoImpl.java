package forumRep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.conn.DbConnect;
import forum.Forum;

public class RepDaoImpl implements RepDao {
	private DbConnect db;

	public RepDaoImpl() {
		db = DbConnect.getInstance();
	}

	@Override
	public void insert(Rep r) {
		Connection conn = db.conn();
		String sql = "insert into rep_forum values(seq_rep_forum.nextval,?,?,sysdate,?)";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 물음표 매칭
			pstmt.setInt(1, r.getArt_num());
			pstmt.setString(2, r.getWriter());
			pstmt.setString(3, r.getContent());

			// sql 실행
			cnt = pstmt.executeUpdate();
			System.out.println("댓글 등록 완료");
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
	public ArrayList<Rep> selectByArtNum(int art_num) {
		ArrayList<Rep> list = new ArrayList<Rep>();
		Connection conn = db.conn();
		String sql = "select * from rep_forum where article_num = ? order by rep_date";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, art_num);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Rep r = new Rep(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5));
				list.add(r);
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
	public ArrayList<Rep> selectByWriter(String writer) {
		ArrayList<Rep> list = new ArrayList<Rep>();
		Connection conn = db.conn();
		String sql = "select * from rep_forum where writer = ?";
		Rep r = null;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				r = new Rep(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5));
				list.add(r);
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
	public void update(Rep r) {
		Connection conn = db.conn();
		String sql = "update rep_forum set rep_date = sysdate, content = ?, where rep_num = ?";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 물음표 매칭
			pstmt.setString(1, r.getContent());
			pstmt.setInt(2, r.getRep_num());

			// sql 실행
			cnt = pstmt.executeUpdate();
			System.out.println("수정 완료");
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
	public void delete(int rep_num) {
		Connection conn = db.conn();
		String sql = "delete rep_forum where rep_num = ?";
		int cnt = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 물음표 매칭
			pstmt.setInt(1, rep_num);

			// sql 실행
			cnt = pstmt.executeUpdate();
			System.out.println("삭제 완료");
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
