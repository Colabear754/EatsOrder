package review;

import java.sql.*;
import java.util.ArrayList;

import connectionMgr.DBConnectionMgr;

/*
 * 구현한 기능
 * 리뷰작성, 리뷰수정, 리뷰삭제, 좋아요, 좋아요개수 조회, 리뷰조회, 댓글작성, 댓글조회, 댓글수정, 댓글삭제
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
	public int insertReivew(int order_number, String content, int rating) {
		// result가 0보다 크면 리뷰 작성 성공
		// 사진을 첨부하지 않은 경우
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into review(review_number, order_number, content, rating) "
					+ "values(review_index_seq.nextval, " + order_number + ", '" + content + "', " + rating + ")");
			result = pStatement.executeUpdate();

			if (result > 0) {
				System.out.println("리뷰 작성 성공");
			} else {
				System.out.println("리뷰 작성 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	public int insertReivew(int order_number, String content, String photo1, int rating) {
		// result가 0보다 크면 리뷰 작성 성공
		// 사진 1장을 첨부했을 경우
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into review("
					+ "review_number, order_number, photo1, content, rating) values(review_index_seq.nextval, "
					+ order_number + ", '" + photo1 + "', '" + content + "', " + rating + ")");
			result = pStatement.executeUpdate();

			if (result > 0) {
				System.out.println("리뷰 작성 성공");
			} else {
				System.out.println("리뷰 작성 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	public int insertReivew(int order_number, String content, String photo1, String photo2, int rating) {
		// result가 0보다 크면 리뷰 작성 성공
		// 사진 2장을 첨부했을 경우
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into review("
					+ "review_number, order_number, photo1, photo2, content, rating) values(review_index_seq.nextval, "
					+ order_number + ", '" + photo1 + "', '" + photo2 + "', '" + content + "', " + rating + ")");
			result = pStatement.executeUpdate();

			if (result > 0) {
				System.out.println("리뷰 작성 성공");
			} else {
				System.out.println("리뷰 작성 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	public int insertReivew(int order_number, String content, String photo1, String photo2, String photo3, int rating) {
		// result가 0보다 크면 리뷰 작성 성공
		// 사진 3장을 첨부했을 경우
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(
					"insert into review(review_number, order_number, photo1, photo2, photo3, content, rating) "
							+ "values(review_index_seq.nextval, " + order_number + ", '" + photo1 + "', '" + photo2 + "', '"
							+ photo3 + "', '" + content + "', " + rating + ")");
			result = pStatement.executeUpdate();

			if (result > 0) {
				System.out.println("리뷰 작성 성공");
			} else {
				System.out.println("리뷰 작성 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	public int insertReivew(int order_number, String content, String photo1, String photo2, String photo3, String photo4,
			int rating) {
		// result가 0보다 크면 리뷰 작성 성공
		// 사진 4장을 첨부했을 경우
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(
					"insert into review(review_number, order_number, photo1, photo2, photo3, photo4, content, rating) "
							+ "values(review_index_seq.nextval, " + order_number + ", '" + photo1 + "', '" + photo2 + "', '"
							+ photo3 + "', '" + photo4 + "', '" + content + "', " + rating + ")");
			result = pStatement.executeUpdate();

			if (result > 0) {
				System.out.println("리뷰 작성 성공");
			} else {
				System.out.println("리뷰 작성 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	public int insertReivew(int order_number, String content, String photo1, String photo2, String photo3, String photo4,
			String photo5, int rating) {
		// result가 0보다 크면 리뷰 작성 성공
		// 사진 5장을 첨부했을 경우
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into review("
					+ "review_number, order_number, photo1, photo2, photo3, photo4, photo5, content, rating) "
					+ "values(review_index_seq.nextval, " + order_number + ", '" + photo1 + "', '" + photo2 + "', '" + photo3
					+ "', '" + photo4 + "', '" + photo5 + "', '" + content + "', " + rating + ")");
			result = pStatement.executeUpdate();

			if (result > 0) {
				System.out.println("리뷰 작성 성공");
			} else {
				System.out.println("리뷰 작성 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	public int updateReview(int review_number, String content, String email) {
		// result가 0보다 크면 리뷰 수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select review.*, order_history.orderer from review, order_history "
					+ "where review_number=" + review_number + " and sysdate-regist_date < 7");
			connection.setAutoCommit(false);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				if (email.equals(resultSet.getString("orderer"))) {
					pStatement = connection.prepareStatement(
							"update review set content='" + content + "' where review_number=" + review_number);
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

	public int deleteReview(int review_number, String email) {
		// result가 1보다 크면 리뷰 삭제 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select review.*, order_history.orderer from review, order_history "
					+ "where review_number=" + review_number);
			connection.setAutoCommit(false);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				if (email.equals(resultSet.getString("orderer"))) {
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
					pStatement = connection
							.prepareStatement("insert into review_like values(" + review_number + ", '" + email + "')");
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

	public int insertReply(int review_number, int rst_id, String content) {
		// result가 0보다 크면 리뷰에 댓글 작성 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from v_review_to_rst where rst_id=" + rst_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				pStatement = connection.prepareStatement("insert into reply(reply_number, review_number, content) "
						+ "values(reply_index_seq.nextval, " + review_number + ", '" + content + "')");
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

	public int updateReply(int reply_number, int rst_id, String content) {
		// result가 0보다 크면 댓글 수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select rp.reply_number from reply rp, v_review_to_rst rv "
					+ "where rst_id=" + rst_id + " and rp.review_number=rv.review_number");
			connection.setAutoCommit(false);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				pStatement = connection.prepareStatement(
						"update reply set content='" + content + "' where" + " reply_number=" + reply_number);
				result = pStatement.executeUpdate();

				if (result > 0) {
					System.out.println("댓글 수정 성공");
					connection.commit();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	public int deleteReply(int reply_number, int rst_id) {
		// result가 0보다 크면 댓글 수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select rp.reply_number, rst_id from reply rp, v_review_to_rst rv "
					+ "where rst_id=" + rst_id + " and rp.review_number=rv.review_number and reply_number=" + reply_number);
			connection.setAutoCommit(false);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				if (resultSet.getInt("rst_id") == rst_id) {
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

	public ArrayList<ReviewDTO> getReviews(int rst_id, boolean onlyPhotoReview) {
		// 리뷰 목록 조회

		ArrayList<ReviewDTO> resultList = new ArrayList<>();
		String sql;

		if (onlyPhotoReview) {
			// 사진이 포함된 리뷰만 조회
			sql = "select * from review where photo1 is not null and review_number in "
					+ "(select review_number from v_review_to_rst where rst_id=" + rst_id + ") order by regist_date desc";
		} else {
			// 모든 리뷰 조회
			sql = "select * from review where review_number in (select review_number from v_review_to_rst where rst_id="
					+ rst_id + ") order by regist_date desc";
		}

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new ReviewDTO(resultSet.getInt("review_number"), resultSet.getInt("order_number"),
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

	public ReplyDTO getReply(int review_number) {
		// 리뷰 번호에 해당하는 댓글 조회
		// 조회에 실패하거나 댓글이 없을 경우 null 반환
		ReplyDTO result = null;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from reply where review_number=" + review_number);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = new ReplyDTO(resultSet.getInt("reply_number"), resultSet.getInt("review_number"),
						resultSet.getDate("regist_date"), resultSet.getString("content"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}


	public ArrayList<ReviewDTO> getMyReviews(String email) {
		// 자신의 리뷰를 조회
		ArrayList<ReviewDTO> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select review.* from review, order_history oh "
					+ "where review.order_number=oh.order_number and oh.orderer='" + email + "'");
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new ReviewDTO(resultSet.getInt("review_number"), resultSet.getInt("order_number"),
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
}
