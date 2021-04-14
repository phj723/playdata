package forum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.conn.DbConnect;

public class ForumDaoImpl implements ForumDao {
	private DbConnect db;

	public ForumDaoImpl() {
		db = DbConnect.getInstance();
	}

	@Override
	public void insert(Forum f) {
		Connection conn = db.conn();
		String sql = "insert into forum values(seq_forum.nextval,?,sysdate,?,?,?)";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 물음표 매칭
			pstmt.setString(1, f.getWriter());
			pstmt.setString(2, f.getTitle());
			pstmt.setString(3, f.getContent());
			pstmt.setString(4, f.getCategory());

			// sql 실행
			cnt = pstmt.executeUpdate();
			System.out.println("글 등록 완료");
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
	public ArrayList<Forum> selectAll() {
		ArrayList<Forum> list = new ArrayList<Forum>();
		Connection conn = db.conn();
		String sql = "select * from forum order by article_num";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Forum f = new Forum(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
				list.add(f);
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
	public Forum selectByNum(int num) {
		Connection conn = db.conn();
		String sql = "select * from forum where article_num = ?";
		ResultSet rs = null;
		Forum f = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				f = new Forum(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
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
		return f;

	}

	@Override
	public ArrayList<Forum> selectByWriter(String writer) {
		ArrayList<Forum> list = new ArrayList<Forum>();
		Connection conn = db.conn();
		String sql = "select * from forum where writer = ?";
		Forum f = null;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				f = new Forum(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
				list.add(f);
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
	public ArrayList<Forum> selectByTitle(String title) {
		ArrayList<Forum> list = new ArrayList<Forum>();
		Connection conn = db.conn();
		String sql = "select * from forum where title like ?";
		Forum f = null;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + title + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				f = new Forum(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
				list.add(f);
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
	public ArrayList<Forum> selectByCategory(String category) {
		ArrayList<Forum> list = new ArrayList<Forum>();
		Connection conn = db.conn();
		String sql = "select * from forum where category = ?";
		Forum f = null;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				f = new Forum(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
				list.add(f);
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
	public void update(Forum f) {
		Connection conn = db.conn();
		String sql = "update forum set w_date = sysdate, title = ?, content = ? where article_num = ?";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 물음표 매칭
			pstmt.setString(1, f.getTitle());
			pstmt.setString(2, f.getContent());
			pstmt.setInt(3, f.getArticle_num());

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
	public void updateCat(Forum f) {
		Connection conn = db.conn();
		String sql = "update forum set w_date = sysdate, category = ? where article_num = ?";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 물음표 매칭
			pstmt.setString(1, f.getCategory());
			pstmt.setInt(2, f.getArticle_num());

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
	public void delete(int num) {
		Connection conn = db.conn();
		String sql = "delete forum where article_num = ?";
		int cnt = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 물음표 매칭
			pstmt.setInt(1, num);

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
