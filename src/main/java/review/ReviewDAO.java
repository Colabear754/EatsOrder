package review;

import java.sql.*;
import java.util.ArrayList;

import connectionMgr.DBConnectionMgr;

/*
 * 구현한 기능
 * 리뷰작성, 리뷰수정, 리뷰삭제, 좋아요, 좋아요개수 조회, 리뷰조회, 리뷰개수 조회, 자신의 리뷰 조회, 리뷰 작성자 조회
 * 댓글작성, 댓글조회, 댓글수정, 댓글삭제, 댓글개수 조회
*/

public class ReviewDAO {
	private DBConnectionMgr connectionMgr;
	private Connection connection;
	private PreparedStatement pStatement;
	private ResultSet resultSet;

	public ReviewDAO() {
		try {
			connectionMgr = DBConnectionMgr.getInstance();
			System.out.println("connectionMgr : " + connectionMgr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 리뷰 작성 메소드는 첨부하는 사진의 개수에 따라 6개의 메소드로 오버로딩함
	public int insertReivew(String order_number, String content, int rating) {
		// result가 0보다 크면 리뷰 작성 성공
		// 사진을 첨부하지 않은 경우
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into review(review_number, order_number, content, rating) "
					+ "values(review_index_seq.nextval, ?, ?, ?)");
			pStatement.setString(1, order_number);
			pStatement.setString(2, content);
			pStatement.setInt(3, rating);

			result = pStatement.executeUpdate();

			System.out.println("리뷰 작성 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	public int insertReivew(String order_number, String content, String photo1, int rating) {
		// result가 0보다 크면 리뷰 작성 성공
		// 사진 1장을 첨부했을 경우
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into review("
					+ "review_number, order_number, photo1, content, rating) values(review_index_seq.nextval, ?, ?, ?, ?)");

			pStatement.setString(1, order_number);
			pStatement.setString(2, content);
			pStatement.setString(3, photo1);
			pStatement.setInt(4, rating);

			result = pStatement.executeUpdate();

			System.out.println("리뷰 작성 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	public int insertReivew(String order_number, String content, String photo1, String photo2, int rating) {
		// result가 0보다 크면 리뷰 작성 성공
		// 사진 2장을 첨부했을 경우
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into review(review_number, order_number, photo1, photo2, "
					+ "content, rating) values(review_index_seq.nextval, ?, ?, ?, ?, ?)");

			pStatement.setString(1, order_number);
			pStatement.setString(2, content);
			pStatement.setString(3, photo1);
			pStatement.setString(4, photo2);
			pStatement.setInt(5, rating);

			result = pStatement.executeUpdate();

			System.out.println("리뷰 작성 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	public int insertReivew(String order_number, String content, String photo1, String photo2, String photo3, int rating) {
		// result가 0보다 크면 리뷰 작성 성공
		// 사진 3장을 첨부했을 경우
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(
					"insert into review(review_number, order_number, photo1, photo2, photo3, content, rating) "
							+ "values(review_index_seq.nextval, ?, ?, ?, ?, ?, ?)");

			pStatement.setString(1, order_number);
			pStatement.setString(2, content);
			pStatement.setString(3, photo1);
			pStatement.setString(4, photo2);
			pStatement.setString(5, photo3);
			pStatement.setInt(6, rating);

			result = pStatement.executeUpdate();

			System.out.println("리뷰 작성 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	public int insertReivew(String order_number, String content, String photo1, String photo2, String photo3, String photo4,
			int rating) {
		// result가 0보다 크면 리뷰 작성 성공
		// 사진 4장을 첨부했을 경우
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(
					"insert into review(review_number, order_number, photo1, photo2, photo3, photo4, content, rating) "
							+ "values(review_index_seq.nextval, ?, ?, ?, ?, ?, ?, ?)");

			pStatement.setString(1, order_number);
			pStatement.setString(2, content);
			pStatement.setString(3, photo1);
			pStatement.setString(4, photo2);
			pStatement.setString(5, photo3);
			pStatement.setString(6, photo4);
			pStatement.setInt(7, rating);

			result = pStatement.executeUpdate();

			System.out.println("리뷰 작성 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	public int insertReivew(String order_number, String content, String photo1, String photo2, String photo3, String photo4,
			String photo5, int rating) {
		// result가 0보다 크면 리뷰 작성 성공
		// 사진 5장을 첨부했을 경우
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection
					.prepareStatement("insert into review values(review_index_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");

			pStatement.setString(1, order_number);
			pStatement.setString(2, content);
			pStatement.setString(3, photo1);
			pStatement.setString(4, photo2);
			pStatement.setString(5, photo3);
			pStatement.setString(6, photo4);
			pStatement.setString(7, photo5);
			pStatement.setInt(8, rating);

			result = pStatement.executeUpdate();

			System.out.println("리뷰 작성 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	// 리뷰 수정
	public int updateReview(int review_number, String content, String email) {
		// result가 0보다 크면 리뷰 수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection
					.prepareStatement("select orderer from review r, order_history oh" + "where review_number="
							+ review_number + " and r.order_number=oh.order_number and sysdate-regist_date < 7");
			connection.setAutoCommit(false);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				if (email.equals(resultSet.getString(1))) {
					pStatement = connection.prepareStatement("update review set content=? where review_number=?");
					pStatement.setString(1, content);
					pStatement.setInt(2, review_number);
					result = pStatement.executeUpdate();

					if (result > 0) {
						System.out.println("리뷰 수정 성공");
						connection.commit();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 리뷰 삭제
	public int deleteReview(int review_number, String email) {
		// result가 1보다 크면 리뷰 삭제 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select orderer from review r, order_history oh"
					+ "where review_number=" + review_number + " and r.order_number=oh.order_number");
			connection.setAutoCommit(false);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				if (email.equals(resultSet.getString(1))) {
					pStatement = connection.prepareStatement("delete from review where review_number=" + review_number);
					result = pStatement.executeUpdate();

					if (result > 0) {
						System.out.println("리뷰 삭제 성공");
						connection.commit();
					} else {
						System.out.println("리뷰 삭제 실패");
					}
				} else {
					System.out.println("리뷰 삭제 실패 : 리뷰 작성자가 아님");
				}
			} else {
				System.out.println("삭제하려는 리뷰 조회 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 리뷰 좋아요
	public int likeReview(int review_number, String email) {
		// result가 0보다 크면 좋아요 처리 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection
					.prepareStatement("select review_number from review where review_number=" + review_number);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				pStatement = connection.prepareStatement("select email from member_info where email='" + email + "'");
				resultSet = pStatement.executeQuery();

				if (resultSet.next()) {
					pStatement = connection.prepareStatement("insert into review_like values(?, ?)");
					pStatement.setInt(1, review_number);
					pStatement.setString(2, email);

					result = pStatement.executeUpdate();

					if (result > 0) {
						System.out.println(email + "사용자가 " + review_number + "번 리뷰에 좋아요 처리 성공");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 리뷰 좋아요 개수 조회
	public int getLikeCount(int review_number) {
		// 리뷰에 등록된 좋아요 개수 반환
		// 조회에 실패하면 -1 반환
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection
					.prepareStatement("select count(*) from review_like where review_number=" + review_number);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = resultSet.getInt(1);
				System.out.println(review_number + "번 리뷰의 좋아요 개수 : " + result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 댓글 작성
	public int insertReply(int review_number, int rst_id, String content) {
		// result가 0보다 크면 리뷰에 댓글 작성 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from v_review_to_rst where rst_id=" + rst_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				pStatement = connection.prepareStatement("insert into reply(reply_number, review_number, content) "
						+ "values(reply_index_seq.nextval, ?, ?)");
				pStatement.setInt(1, review_number);
				pStatement.setString(2, content);

				result = pStatement.executeUpdate();

				if (result > 0) {
					System.out.println(review_number + "번 리뷰에 댓글 등록 성공");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 댓글 수정
	public int updateReply(int reply_number, int rst_id, String content) {
		// result가 0보다 크면 댓글 수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select rp.reply_number, rst_id from reply rp, v_review_to_rst rv "
					+ "where rst_id=? and rp.review_number=rv.review_number and reply_number=?");
			pStatement.setInt(1, rst_id);
			pStatement.setInt(2, reply_number);

			connection.setAutoCommit(false);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				if (rst_id == resultSet.getInt(1)) {
					pStatement = connection.prepareStatement("update reply set content=? where reply_number=?");
					pStatement.setString(1, content);
					pStatement.setInt(2, reply_number);

					result = pStatement.executeUpdate();

					if (result > 0) {
						System.out.println("댓글 수정 성공");
						connection.commit();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 댓글 삭제
	public int deleteReply(int reply_number, int rst_id) {
		// result가 0보다 크면 댓글 수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select rp.reply_number, rst_id from reply rp, v_review_to_rst rv "
					+ "where rst_id=? and rp.review_number=rv.review_number and reply_number=?");
			pStatement.setInt(1, rst_id);
			pStatement.setInt(2, reply_number);

			connection.setAutoCommit(false);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				if (resultSet.getInt(1) == rst_id) {
					pStatement = connection.prepareStatement("delete from reply where reply_number=" + reply_number);
					result = pStatement.executeUpdate();

					if (result > 0) {
						System.out.println("댓글 삭제 성공");
						connection.commit();
					} else {
						System.out.println("댓글 삭제 실패");
					}
				}
			} else {
				System.out.println("삭제하려는 댓글 조회 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 리뷰 목록 조회
	public ArrayList<ReviewDTO> getReviews(int rst_id, boolean onlyPhotoReview, int start, int end) {
		// 리뷰 목록 조회

		ArrayList<ReviewDTO> resultList = new ArrayList<>();
		String sql;

		if (onlyPhotoReview) {
			// 사진이 포함된 리뷰만 조회
			sql = "select * from (select rownum r, review.* from review where photo1 is not null and review_number in "
					+ "(select review_number from v_review_to_rst where rst_id=?) order by review_number desc) "
					+ "where r>=? and r<=?";
		} else {
			// 모든 리뷰 조회
			sql = "select * from (select rownum r, review.* from review where review_number in "
					+ "(select review_number from v_review_to_rst where rst_id=?) order by review_number desc) "
					+ "where r>=? and r<=?";
		}

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setInt(1, rst_id);
			pStatement.setInt(2, start);
			pStatement.setInt(3, end);
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new ReviewDTO(resultSet.getInt("review_number"), resultSet.getString("order_number"),
						resultSet.getDate("regist_date"), resultSet.getString("content"), resultSet.getString("photo1"),
						resultSet.getString("photo2"), resultSet.getString("photo3"), resultSet.getString("photo4"),
						resultSet.getString("photo5"), resultSet.getInt("rating")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}

	// 리뷰 개수 조회
	public int getReviewCount(int rst_id) {
		// 매장ID에 해당하는 리뷰의 개수를 조회
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select count(review_number) from review where review_number in "
					+ "(select review_number from v_review_to_rst where rst_id=?)");
			pStatement.setInt(1, rst_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 댓글 개수 조회
	public int getReplyCount(int rst_id) {
		// 매장ID에 해당하는 리뷰의 개수를 조회
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select count(reply_number) from reply where review_number in "
					+ "(select review_number from v_review_to_rst where rst_id=?)");
			pStatement.setInt(1, rst_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 매장ID에 해당하는 리뷰 댓글 리스트 조회	
	public ArrayList<ReplyDTO> getReply(int rst_id) {
		ArrayList<ReplyDTO> resultList = new ArrayList<>();
		;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from reply where review_number in "
					+ "(select review_number from v_review_to_rst where rst_id=?)");
			pStatement.setInt(1, rst_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				resultList.add(new ReplyDTO(resultSet.getInt("reply_number"), resultSet.getInt("review_number"),
						resultSet.getDate("regist_date"), resultSet.getString("content")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}

	// 자신의 리뷰를 조회
	public ArrayList<ReviewDTO> getMyReviews(String email, int start, int end) {
		ArrayList<ReviewDTO> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from ("
					+ "select rownum rn, rv.* from review rv, order_history oh where rv.order_number=oh.order_number "
					+ "and oh.orderer=? order by rv.review_number desc) where rn>=? and rn<=?");
			pStatement.setString(1, email);
			pStatement.setInt(2, start);
			pStatement.setInt(3, end);
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new ReviewDTO(resultSet.getInt("review_number"), resultSet.getString("order_number"),
						resultSet.getDate("regist_date"), resultSet.getString("content"), resultSet.getString("photo1"),
						resultSet.getString("photo2"), resultSet.getString("photo3"), resultSet.getString("photo4"),
						resultSet.getString("photo5"), resultSet.getInt("rating")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}

	// 리뷰 작성자의 이메일을 조회
	public String getReviewWriter(int review_number) {
		// 조회 성공 시 이메일을, 실패 시 null을 반환
		String result = null;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select orderer from review r, order_history oh "
					+ "where review_number=? and r.order_number=oh.order_number");
			pStatement.setInt(1, review_number);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = resultSet.getString(1);
			}

			System.out.println("리뷰 작성사 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}
}
