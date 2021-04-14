package hangman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.conn.DbConnect;
import player.Player;

public class WordDaoImpl implements WordDao {
	private DbConnect db;

	public WordDaoImpl() {
		db = DbConnect.getInstance();
	}

	@Override
	public void insert(Word w) {
		Connection conn = db.conn();
		String sql = "insert into words values(seq_words.nextval,?,?)";
		int cnt = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, w.getCategory());
			pstmt.setString(2, w.getWord());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("단어가 정상적으로 추가되지 않았습니다.");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<String> selectAllCate() {
		ArrayList<String> list = new ArrayList<String>();
		Connection conn = db.conn();
		String sql = "select distinct category from words";
		ResultSet rs = null; 
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) { 
				String c = rs.getString(1);
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("카테고리가 정상적으로 조회되지 않았습니다.");
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
	public ArrayList<String> selectAllWord() {
		ArrayList<String> list = new ArrayList<String>();
		Connection conn = db.conn();
		String sql = "select word from words";
		ResultSet rs = null; 
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) { 
				String c = rs.getString(1);
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("단어가 정상적으로 조회되지 않았습니다.");
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
	public ArrayList<String> selectWordByCate(String category) {
		ArrayList<String> list = new ArrayList<String>();
		Connection conn = db.conn();
		String sql = "select word from words where category = ?";
		ResultSet rs = null; 
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();

			while (rs.next()) { 
				String w = rs.getString(1);
				list.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("단어가 정상적으로 조회되지 않았습니다.");
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
	public void update(String oldWord, String newWord) {
		Connection conn = db.conn();
		String sql = "update words set word = ? where word = ?";
		int cnt=0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newWord);
			pstmt.setString(2, oldWord);
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("단어가 정상적으로 수정되지 않았습니다.");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteWord(String word) {
		Connection conn = db.conn();
		String sql = "delete from words where word = ?";
		int cnt = 0;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, word);

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("단어가 정상적으로 삭제되지 않았습니다.");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteCate(String category) {
		Connection conn = db.conn();
		String sql = "delete from words where category = ?";
		int cnt = 0;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("카테고리가 정상적으로 삭제되지 않았습니다.");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
