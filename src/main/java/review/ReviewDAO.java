package review;

import java.sql.*;
import java.util.ArrayList;

import connectionMgr.DBConnectionMgr;
import order.OrderBasicInfoDTO;
import restaurant.RestaurantDTO;

/*
 * 작성자 : 정건영
 * 작성일 : 2022/03/02
 * 설명 : 리뷰와 댓글에 관련된 기능에 대한 DAO 클래스
 * 
 * 구현한 기능
 * 리뷰작성, 리뷰수정, 리뷰삭제, 좋아요, 좋아요개수 조회, 리뷰조회, 리뷰목록 조회 리뷰개수 조회, 자신의 리뷰 조회, 리뷰 작성자 조회
 * 댓글작성, 댓글조회, 댓글수정, 댓글목록 조회 댓글삭제, 댓글개수 조회, 리뷰에 해당하는 매장 조회
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

	// 리뷰 작성
	public int insertReivew(String email, String order_number, String content, String[] photos, int rating) {
		// result가 0보다 크면 리뷰 작성 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select orderer from order_history oh where order_number=?");
			pStatement.setString(1, order_number);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				if (resultSet.getString(1).equals(email)) {
					pStatement = connection.prepareStatement("insert into review "
							+ "values(review_index_seq.nextval, ?, sysdate, ?, ?, ?, ?, ?, ?, ?)");
					pStatement.setString(1, order_number);
					pStatement.setString(2, content);
					pStatement.setString(3, photos[0]);
					pStatement.setString(4, photos[1]);
					pStatement.setString(5, photos[2]);
					pStatement.setString(6, photos[3]);
					pStatement.setString(7, photos[4]);
					pStatement.setInt(8, rating);
	
					result = pStatement.executeUpdate();
				}
			}
			
			System.out.println("리뷰 작성 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 리뷰 조회
	public ReviewDTO getReview(int review_number) {
		ReviewDTO result = null;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from review where review_number=?");
			pStatement.setInt(1, review_number);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = new ReviewDTO(resultSet.getInt("review_number"), resultSet.getString("order_number"),
						resultSet.getDate("regist_date"), resultSet.getString("content"), resultSet.getString("photo1"),
						resultSet.getString("photo2"), resultSet.getString("photo3"), resultSet.getString("photo4"),
						resultSet.getString("photo5"), resultSet.getInt("rating"));
			}

			System.out.println("조회된 리뷰 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 리뷰 수정
	public int updateReview(int review_number, String content, String email) {
		// result가 0보다 크면 리뷰 수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select orderer from review r, order_history oh "
					+ "where review_number=? and r.order_number=oh.order_number and sysdate-regist_date < 7");
			pStatement.setInt(1, review_number);
			connection.setAutoCommit(false);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				if (email.equals(resultSet.getString(1))) {
					pStatement = connection.prepareStatement("update review set content=? where review_number=?");
					pStatement.setString(1, content);
					pStatement.setInt(2, review_number);
					result = pStatement.executeUpdate();

					if (result > 0) {
						connection.commit();
					}
				}
			}

			System.out.println("리뷰 수정 결과 : " + result);
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
			pStatement = connection.prepareStatement("select orderer from review r, order_history oh "
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

	// 리뷰 좋아요 등록 또는 취소
	public int likeReview(int review_number, String email) {
		// result가 0보다 크면 좋아요 처리 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from review_like where review_number=? and email=?");
			pStatement.setInt(1, review_number);
			pStatement.setString(2, email);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) { // 좋아요가 이미 등록되어 있으면 좋아요를 취소, 없다면 좋아요 등록
				pStatement = connection.prepareStatement("delete from review_like where review_number=? and email=?");
				pStatement.setInt(1, review_number);
				pStatement.setString(2, email);
				System.out.print("좋아요 취소 ");
			} else {
				pStatement = connection.prepareStatement("insert into review_like values(?, ?)");
				pStatement.setInt(1, review_number);
				pStatement.setString(2, email);
				System.out.print("좋아요 등록 ");
			}

			result = pStatement.executeUpdate();

			System.out.println("결과 : " + result);
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
			pStatement = connection.prepareStatement("select * from v_review_to_rst where rst_id=?");
			pStatement.setInt(1, rst_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				pStatement = connection.prepareStatement("insert into reply(reply_number, review_number, content) "
						+ "values(reply_index_seq.nextval, ?, ?)");
				pStatement.setInt(1, review_number);
				pStatement.setString(2, content);

				result = pStatement.executeUpdate();
			}

			System.out.println("댓글 등록 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 댓글 조회
	public ReplyDTO getReply(int reply_number) {
		ReplyDTO result = null;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from reply where reply_number=?");
			pStatement.setInt(1, reply_number);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = new ReplyDTO(resultSet.getInt("reply_number"), resultSet.getInt("review_number"),
						resultSet.getDate("regist_date"), resultSet.getString("content"));
			}

			System.out.println("조회된 댓글 : " + result);
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
						connection.commit();
					}
				}
			}

			System.out.println("댓글 수정 결과 : " + result);
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
					+ "(select review_number from v_review_to_rst where rst_id=? and review_number is not null)");
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
	public ArrayList<ReplyDTO> getReplies(int rst_id) {
		ArrayList<ReplyDTO> resultList = new ArrayList<>();
		;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from reply where review_number in "
					+ "(select review_number from v_review_to_rst where rst_id=?) order by review_number desc");
			pStatement.setInt(1, rst_id);
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
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

			System.out.println("나의 리뷰 개수 : " + resultList.size());
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 리뷰 번호에 해당하는 매장 정보 조회
	public RestaurantDTO getReviewRst(int review_number) {
		// 매장 DTO를 반환
		RestaurantDTO result = null;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(
					"select r.* from restaurant r, v_review_to_rst vrr where review_number=? and vrr.rst_id=r.rst_id");
			pStatement.setInt(1, review_number);
			resultSet = pStatement.executeQuery();
			
			if (resultSet.next()) {
				result = new RestaurantDTO(resultSet.getInt("rst_id"), resultSet.getInt("category_id"),
						resultSet.getString("rst_name"), resultSet.getString("phone"), resultSet.getString("address"),
						resultSet.getInt("min_order"), resultSet.getString("origin"), resultSet.getString("hours"),
						resultSet.getString("bussiness_number"), resultSet.getString("bussiness_name"),
						resultSet.getString("payments"), resultSet.getInt("delivery_tip"), resultSet.getString("rst_notice"),
						resultSet.getString("estimated_time"), resultSet.getString("rst_photo"),
						resultSet.getString("rst_logo"), resultSet.getInt("enable"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}
	
	// 리뷰를 작성할 수 있는 주문 목록 조회
	public ArrayList<OrderBasicInfoDTO> getReviewToWrite(String orderer) {
		// 주문한지 7일 이내면서 리뷰를 작성하지 않은 주문만 조회
		ArrayList<OrderBasicInfoDTO> resultList = new ArrayList<>();
		String order_number = "";
		ResultSet resultSet2 = null;
		
		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select oh.order_number, rst.rst_id, rst_name, rst_logo, count(menu_name) as count, pay_date "
					+ "from review r, order_history oh, order_detail od, menu m, menu_category mc, restaurant rst "
					+ "where orderer=? and oh.order_number=od.order_number and od.menu_id=m.menu_id "
					+ "and m.category_id=mc.category_id and mc.rst_id=rst.rst_id and r.order_number(+)=oh.order_number "
					+ "and extract(day from systimestamp - pay_date)<7 and review_number is null "
					+ "group by oh.order_number, rst.rst_id, rst_name, rst_logo, pay_date order by oh.order_number desc");
			pStatement.setString(1, orderer);
			resultSet = pStatement.executeQuery();
			
			while (resultSet.next()) {
				order_number = resultSet.getString("order_number");
				pStatement = connection.prepareStatement(
						"select menu_name from order_detail od, menu m where order_number=? and od.menu_id=m.menu_id");
				pStatement.setString(1, order_number);
				resultSet2 = pStatement.executeQuery();

				if (resultSet2.next()) {
					resultList.add(new OrderBasicInfoDTO(order_number, resultSet.getInt("rst_id"), resultSet.getString("rst_name"),
							resultSet.getString("rst_logo"), resultSet2.getString(1), resultSet.getInt("count") - 1,
							resultSet.getTimestamp("pay_date"), resultSet.getInt("payment_status")));
				}
			}
			
			if (resultSet2 != null) {
				resultSet2.close();
			}
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}
		
		return resultList;
	}
}
