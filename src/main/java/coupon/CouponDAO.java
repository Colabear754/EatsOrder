package coupon;

import java.sql.*;
import java.util.ArrayList;

import connectionMgr.DBConnectionMgr;

/*
 * 작성자 : 정건영
 * 작성일 : 2022/03/01
 * 설명 : 쿠폰에 관련된 기능에 대한 DAO 클래스
 * 
 * 구현된 기능
 * 유효쿠폰 추가, 사용자쿠폰 등록, 쿠폰 사용, 쿠폰 조회, 유효기간이 만료된 쿠폰 삭제
*/

public class CouponDAO {
	private DBConnectionMgr connectionMgr;
	private Connection connection;
	private PreparedStatement pStatement;
	private ResultSet resultSet;

	public CouponDAO() {
		try {
			connectionMgr = DBConnectionMgr.getInstance();
			System.out.println("connectionMgr : " + connectionMgr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 사이트 관리자가 유효쿠폰 추가
	public int insertValidCoupon(String coupon_id, String coupon_name, String coupon_info, int available_price,
			int discount_amount, Date expiration_date, String admin_id, String password) {
		// result가 0보다 크면 새로운 유효쿠폰 추가 성공
		// expiration_date는 java.sql.Date.valueOf() 메소드를 통해 "yyyy-mm-dd" 형태의 문자열을 Date 타입으로 바꿔야함
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection
					.prepareStatement("select * from administrator where admin_id=? and password=pkg_crypto.encrypt(?)");
			pStatement.setString(1, admin_id);
			pStatement.setString(2, password);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				pStatement = connection.prepareStatement("insert into valid_coupon values(?, ?, ?, ?, ?, ?)");
				pStatement.setString(1, coupon_id);
				pStatement.setString(2, coupon_name);
				pStatement.setString(3, coupon_info);
				pStatement.setInt(4, available_price);
				pStatement.setInt(5, discount_amount);
				pStatement.setDate(6, expiration_date);

				result = pStatement.executeUpdate();
			}

			System.out.println("유효쿠폰 추가 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 고객이 사용자쿠폰 등록
	public int registUserCoupon(String coupon_id, String owner_id, int available_count) {
		// result가 0보다 크면 쿠폰 등록 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into owned_coupon values(?, ?, ?)");
			pStatement.setString(1, coupon_id);
			pStatement.setString(2, owner_id);
			pStatement.setInt(3, available_count);

			result = pStatement.executeUpdate();

			System.out.println("사용자 쿠폰 등록 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 쿠폰 사용
	public int useCoupon(String coupon_id, String owner_id) {
		// result = -1 : 쿠폰 조회 실패 또는 사용 완료된 쿠폰, 0 : 쿠폰 사용 실패, 그 외 값 : 할인 금액
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			connection.setAutoCommit(false);
			pStatement = connection.prepareStatement("select v.* from valid_coupon v, owned_coupon o where "
					+ "v.coupon_id=o.coupon_id and o.coupon_id=? and owner_id=? and available_count > 0");
			pStatement.setString(1, coupon_id);
			pStatement.setString(2, owner_id);

			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				pStatement = connection.prepareStatement(
						"update owned_coupon set available_count=available_count - 1 " + "where coupon_id=? and owner_id=?");
				pStatement.setString(1, coupon_id);
				pStatement.setString(2, owner_id);

				result = pStatement.executeUpdate();

				if (result > 0) {
					System.out.println("사용한 쿠폰 번호 : " + coupon_id);
					connection.commit();
					result = resultSet.getInt("discount_amount");
				}
			} else {
				System.out.println("쿠폰 조회 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 사용 가능한 쿠폰 조회
	public ArrayList<CouponDetailDTO> getCoupons(String owner_id) {
		// 해시의 coupon키값은 쿠폰리스트, available_count키값은 순서대로 해당 쿠폰의 사용가능횟수
		ArrayList<CouponDetailDTO> result = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection
					.prepareStatement("select v.*, o.available_count from valid_coupon v, owned_coupon o where "
							+ "v.coupon_id=o.coupon_id and o.owner_id=? and o.available_count > 0");
			pStatement.setString(1, owner_id);
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				result.add(new CouponDetailDTO(
						new ValidCouponDTO(resultSet.getString("coupon_id"), resultSet.getString("coupon_name"),
								resultSet.getString("coupon_info"), resultSet.getInt("available_price"),
								resultSet.getInt("discount_amount"), resultSet.getDate("expiration_date")),
						resultSet.getInt("available_count")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 쿠폰 개수 조회
	public int getCouponCount(String owner_id) {
		// 매개변수로 받은 사용자가 보유한 쿠폰 개수를 반환
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection
					.prepareStatement("select count(*) from owned_coupon where owner_id=? and available_count > 0");
			pStatement.setString(1, owner_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}

			System.out.println("보유한 쿠폰 개수 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 유효기간이 만료된 쿠폰 삭제
	// 쿠폰 유효기간이 만료된 후 주문 내역을 조회할 경우를 대비하여 메소드 비활성화
	//	public int deleteExpiredCoupon() {
	//		// result가 0보다 크면 만료쿠폰 삭제 성공
	//		int result = -1;
	//
	//		try {
	//			connection = connectionMgr.getConnection();
	//			pStatement = connection.prepareStatement("delete from valid_coupon where expiration_date < trunc(sysdate)");
	//			connection.setAutoCommit(false);
	//			result = pStatement.executeUpdate();
	//			if (result > 0) {
	//				System.out.println("삭제된 유효기간이 만료된 쿠폰 : " + result);
	//				connection.commit();
	//			}
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		} finally {
	//			connectionMgr.freeConnection(connection, pStatement);
	//		}
	//
	//		return result;
	//	}
}
