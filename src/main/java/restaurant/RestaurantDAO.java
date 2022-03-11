package restaurant;

import java.sql.*;
import java.util.ArrayList;

import connectionMgr.DBConnectionMgr;

/*
 * 구현된 기능
 * 사장님 로그인, 매장 분류별 매장 목록 조회, 매장 검색, 매장 정보 수정, 사장님 정보 수정, 매장 삭제, 
 * 매장 찜하기, 찜한 매장 조회, 배달지역 추가, 배달지역 삭제,
 * 매장ID 찾기
*/

public class RestaurantDAO {
	private DBConnectionMgr connectionMgr;
	private Connection connection;
	private PreparedStatement pStatement;
	private ResultSet resultSet;

	public RestaurantDAO() {
		try {
			connectionMgr = DBConnectionMgr.getInstance();
			System.out.println("connectionMgr : " + connectionMgr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 사장님 로그인
	public boolean rstManagerLogin(int rst_id, String password) {
		// 로그인 성공 시 true를 반환
		boolean result = false;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from restaurant_manager where rst_id=? and password=?");
			pStatement.setInt(1, rst_id);
			pStatement.setString(2, password);
			resultSet = pStatement.executeQuery();

			result = resultSet.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 신규 매장 추가
	public int insertRestaurant(RestaurantDTO data, String password, String phone) {
		// result가 0보다 크면 매장 추가 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into restaurant "
					+ "values(rst_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pStatement.setInt(1, data.getCategory_id());
			pStatement.setString(2, data.getRst_name());
			pStatement.setString(3, data.getPhone());
			pStatement.setString(4, data.getAddress());
			pStatement.setInt(5, data.getMin_order());
			pStatement.setString(6, data.getOrigin());
			pStatement.setString(7, data.getHours());
			pStatement.setString(8, data.getBussiness_number());
			pStatement.setString(9, data.getBussiness_name());
			pStatement.setString(10, data.getPayment());
			pStatement.setInt(11, data.getDelivery_tip());
			pStatement.setString(12, data.getRst_notice());
			pStatement.setString(13, data.getEstimated_time());
			pStatement.setString(14, data.getRst_photo());
			pStatement.setString(15, data.getRst_logo());
			pStatement.setInt(16, data.is_enable());

			result = pStatement.executeUpdate();

			if (result > 0) {
				// 매장 정보 추가에 성공했으면 사장님 로그인 정보 추가
				pStatement = connection.prepareStatement("insert into restaurant_manager values(rst_id_seq.currval, ?, ?)");
				pStatement.setString(1, password);
				pStatement.setString(2, phone);

				result = pStatement.executeUpdate();
			}

			System.out.println("신규 매장 추가 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	// 매장 목록 조회
	public ArrayList<RestaurantDTO> getRestaurants(int category_id, int orderBy, String sido, String sigungu, String bname,
			int start, int end) {
		// 현재 시/도, 시/군/구, 동/리에 해당하는 매장 분류 및 정렬 기준별 매장 목록을 조회하여 리스트에 추가 후 반환
		// category_id = 0일 경우 전체 매장 목록 조회
		// orderBy 값에 따라 정렬 기준 변경
		ArrayList<RestaurantDTO> resultList = new ArrayList<>();
		String sql;

		switch (orderBy) {
		case 1:
			// 기본 정렬
			sql = "select * from (select rownum r, rst.* from restaurant rst, delivery_zone dz "
					+ "where rst.rst_id=dz.rst_id and sido=? and sigungu=? and bname=?) where r>=? and r<=?";
			break;
		case 2:
			// 별점순
			sql = "select * from (select rownum r, rst.* from restaurant rst, delivery_zone dz, v_rst_rating vrr "
					+ "where rst.rst_id=vrr.rst_id and rst.rst_id=dz.rst_id and sido=? and sigungu=? and bname=? "
					+ "order by avg_rating desc) where r>=? and r<=?";
			break;
		case 3:
			// 리뷰 많은 순
			sql = "select * from (select rownum r, rst.* from restaurant rst, delivery_zone dz, v_rst_review_count vrrc "
					+ "where rst.rst_id=vrrc.rst_id and rst.rst_id=dz.rst_id and sido=? and sigungu=? and bname=? "
					+ "order by review_count desc) where r>=? and r<=?";
			break;
		case 4:
			// 최소 주문 금액 순
			sql = "select * from (select rownum r, rst.* from restaurant rst, delivery_zone dz "
					+ "where rst.rst_id=dz.rst_id and sido=? and sigungu=? and bname=? order by min_order asc) where r>=? and r<=?";
		default:
			sql = "select * from (select rownum r, rst.* from restaurant rst, delivery_zone dz "
					+ "where rst.rst_id=dz.rst_id and sido=? and sigungu=? and bname=?) where r>=? and r<=?";
			break;
		}

		if (category_id > 0) {
			// 전체보기가 아닐 경우 SQL에 where 조건 추가
			StringBuffer strBuffer = new StringBuffer(sql);

			strBuffer.insert(sql.indexOf("order"), "and category_id=? ");
			sql = strBuffer.toString();
		}

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, sido);
			pStatement.setString(2, sigungu);
			pStatement.setString(3, bname);
			
			if (sql.indexOf("category_id") > 0) {
				pStatement.setInt(4, category_id);
				pStatement.setInt(5, start);
				pStatement.setInt(6, end);
			} else {
				pStatement.setInt(4, start);
				pStatement.setInt(5, end);
			}
			
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new RestaurantDTO(resultSet.getInt("rst_id"), resultSet.getInt("category_id"),
						resultSet.getString("rst_name"), resultSet.getString("phone"), resultSet.getString("address"),
						resultSet.getInt("min_order"), resultSet.getString("origin"), resultSet.getString("hours"),
						resultSet.getString("bussiness_number"), resultSet.getString("bussiness_name"),
						resultSet.getString("payments"), resultSet.getInt("delivery_tip"), resultSet.getString("rst_notice"),
						resultSet.getString("estimated_time"), resultSet.getString("rst_photo"),
						resultSet.getString("rst_logo"), resultSet.getInt("enable")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}

	// 매장 목록 검색
	public ArrayList<RestaurantDTO> getRestaurants(int category_id, int orderBy, String sido, String sigungu, String bname,
			int start, int end, String searchText) {
		// 현재 시/도, 시/군/구, 동/리 및 검색어에 해당하는 매장 분류 및 정렬 기준별 매장 목록을 조회하여 리스트에 추가 후 반환
		// category_id = 0일 경우 전체 매장 목록 조회
		// orderBy 값에 따라 정렬 기준 변경
		ArrayList<RestaurantDTO> resultList = new ArrayList<>();
		String sql;

		switch (orderBy) {
		case 1:
			// 기본 정렬
			sql = "select * from (select rownum r, rst.* from restaurant rst, delivery_zone dz "
					+ "where rst.rst_id=dz.rst_id and sido=? and sigungu=? and bname=? and rst_name like ?) where r>=? and r<=?";
			break;
		case 2:
			// 별점순
			sql = "select * from (select rownum r, rst.* from restaurant rst, delivery_zone dz, v_rst_rating vrr "
					+ "where rst.rst_id=vrr.rst_id and rst.rst_id=dz.rst_id and sido=? and sigungu=? and bname=? and rst_name like ? "
					+ "order by avg_rating desc) where r>=? and r<=?";
			break;
		case 3:
			// 리뷰 많은 순
			sql = "select * from (select rownum r, rst.* from restaurant rst, delivery_zone dz, v_rst_review_count vrrc "
					+ "where rst.rst_id=vrrc.rst_id and rst.rst_id=dz.rst_id and sido=? and sigungu=? and bname=? and rst_name like ? "
					+ "order by review_count desc) where r>=? and r<=?";
			break;
		case 4:
			// 최소 주문 금액 순
			sql = "select * from (select rownum r, rst.* from restaurant rst, delivery_zone dz "
					+ "where rst.rst_id=dz.rst_id and sido=? and sigungu=? and bname=? and rst_name like ? "
					+ "order by min_order asc) where r>=? and r<=?";
		default:
			sql = "";
			break;
		}

		if (category_id > 0) {
			// 전체보기가 아닐 경우 SQL에 where 조건 추가
			StringBuffer strBuffer = new StringBuffer(sql);

			strBuffer.insert(sql.indexOf("order"), "and category_id=? ");
			sql = strBuffer.toString();
		}

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, sido);
			pStatement.setString(2, sigungu);
			pStatement.setString(3, bname);
			pStatement.setString(4, "%" + searchText + "%");
			
			if (sql.indexOf("category_id") > 0) {
				pStatement.setInt(5, category_id);
				pStatement.setInt(6, start);
				pStatement.setInt(7, end);
			} else {
				pStatement.setInt(5, start);
				pStatement.setInt(6, end);
			}
			
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new RestaurantDTO(resultSet.getInt("rst_id"), resultSet.getInt("category_id"),
						resultSet.getString("rst_name"), resultSet.getString("phone"), resultSet.getString("address"),
						resultSet.getInt("min_order"), resultSet.getString("origin"), resultSet.getString("hours"),
						resultSet.getString("bussiness_number"), resultSet.getString("bussiness_name"),
						resultSet.getString("payments"), resultSet.getInt("delivery_tip"), resultSet.getString("rst_notice"),
						resultSet.getString("estimated_time"), resultSet.getString("rst_photo"),
						resultSet.getString("rst_logo"), resultSet.getInt("enable")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}

	// 매장정보 수정
	public int updateRestaurant(RestaurantDTO data, String password) {
		// result가 0보다 크면 매장정보 수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select rst.rst_id from restaurant rst, restaurant_manager rm "
					+ "where rst.rst_id=rm.rst_id and rst.rst_id=? and password=?");
			pStatement.setInt(1, data.getRst_id());
			pStatement.setString(2, password);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				pStatement = connection.prepareStatement("update restaurant "
						+ "set category_id=?, rst_name=?, phone=?, address=?, min_order=?, origin=?, hours=?, "
						+ "bussiness_number=?, bussiness_name=?, payments=?, delivery_tip=?, rst_notice=?, "
						+ "estimated_time=?, rst_photo=?, rst_logo=?, enable=? where rst_id=?");
				pStatement.setInt(1, data.getCategory_id());
				pStatement.setString(2, data.getRst_name());
				pStatement.setString(3, data.getPhone());
				pStatement.setString(4, data.getAddress());
				pStatement.setInt(5, data.getMin_order());
				pStatement.setString(6, data.getOrigin());
				pStatement.setString(7, data.getHours());
				pStatement.setString(8, data.getBussiness_number());
				pStatement.setString(9, data.getBussiness_name());
				pStatement.setString(10, data.getPayment());
				pStatement.setInt(11, data.getDelivery_tip());
				pStatement.setString(12, data.getRst_notice());
				pStatement.setString(13, data.getEstimated_time());
				pStatement.setString(14, data.getRst_photo());
				pStatement.setString(15, data.getRst_logo());
				pStatement.setInt(16, data.is_enable());
				pStatement.setInt(17, data.getRst_id());

				result = pStatement.executeUpdate();
			}

			System.out.println("매장정보 수정 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 사장님정보 수정
	public int updateRstManager(int rst_id, String password, String newPassword, String phone) {
		// result가 0보다 크면 수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select rst_id from restaurant_manager where rst_id=? and password=?");
			pStatement.setInt(1, rst_id);
			pStatement.setString(2, password);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				if (phone.isBlank()) {
					pStatement = connection.prepareStatement("update restaurant_manager set password=? where rst_id=?");
					pStatement.setString(1, newPassword);
					pStatement.setInt(2, rst_id);
				} else {
					pStatement = connection
							.prepareStatement("update restaurant_manager set password=?, phone=? where rst_id=?");
					pStatement.setString(1, newPassword);
					pStatement.setString(2, phone);
					pStatement.setInt(3, rst_id);
				}

				result = pStatement.executeUpdate();
			}

			System.out.println("사장님정보 수정 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 매장 삭제
	public int deleteRestaurant(int rst_id, String password) {
		// result가 0보다 크면 삭제 성공
		// 매장정보와 사장님 로그인정보를 함께 삭제
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select rst_id from restaurant_manager where rst_id=? and password=?");
			pStatement.setInt(1, rst_id);
			pStatement.setString(2, password);
			resultSet = pStatement.executeQuery();
			connection.setAutoCommit(false);

			if (resultSet.next()) {
				pStatement = connection.prepareStatement("delete from restaurant where rst_id=" + rst_id);
				result = pStatement.executeUpdate();

				// 이 if구문은 외래키 설정 이후에는 삭제할 것
				if (result > 0) {
					pStatement = connection.prepareStatement("delete from restaurant_manager where rst_id=" + rst_id);
					result = pStatement.executeUpdate();

					if (result > 0) {
						connection.commit();
					}
				}

				// connection.commit();	// 외래키 설정 이후에 주석 해제
			}

			System.out.println("매장 삭제 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 매장 찜하기
	public int insertFavoriteRestaurant(String email, int rst_id) {
		// result가 0보다 크면 찜하기 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into favorite_restaurant values(?, ?)");
			pStatement.setString(1, email);
			pStatement.setInt(2, rst_id);

			result = pStatement.executeUpdate();

			System.out.println("매장 찜하기 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	// 찜한 매장 조회
	public ArrayList<RestaurantDTO> getFavoriteRestaurants(String email) {
		ArrayList<RestaurantDTO> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(
					"select rst.* from restaurant rst, favorite_restaurant fr " + "where email=? and rst.rst_id=fr.rst_id");
			pStatement.setString(1, email);

			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new RestaurantDTO(resultSet.getInt("rst_id"), resultSet.getInt("category_id"),
						resultSet.getString("rst_name"), resultSet.getString("phone"), resultSet.getString("address"),
						resultSet.getInt("min_order"), resultSet.getString("origin"), resultSet.getString("hours"),
						resultSet.getString("bussiness_number"), resultSet.getString("bussiness_name"),
						resultSet.getString("payments"), resultSet.getInt("delivery_tip"), resultSet.getString("rst_notice"),
						resultSet.getString("estimated_time"), resultSet.getString("rst_photo"),
						resultSet.getString("rst_logo"), resultSet.getInt("enable")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}

	// 배달지역 추가
	public int insertDeliveryZone(int rst_id, String sido, String sigungu, String bname) {
		// result가 0보다 크면 추가 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into delivery_zone values(dz_index_seq.nextval, ?, ?, ?, ?)");
			pStatement.setInt(1, rst_id);
			pStatement.setString(2, sido);
			pStatement.setString(2, sigungu);
			pStatement.setString(2, bname);

			result = pStatement.executeUpdate();

			System.out.println("배달지역 추가 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	// 배달지역 삭제
	public int deleteDeliveryZone(int zone_index, int rst_id, String password) {
		// result가 0보다 크면 삭제 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select dz.rst_id from restaurant_manager rm, delivery_zone dz "
					+ "where rm.rst_id=dz.rst_id and rm.rst_id=? and password=?");
			pStatement.setInt(1, rst_id);
			pStatement.setString(2, password);

			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				pStatement = connection.prepareStatement("delete from delivery_zone where zone_index=?");
				pStatement.setInt(1, zone_index);

				result = pStatement.executeUpdate();
			}

			System.out.println("배달지역 삭제 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 매장ID 찾기
	public ArrayList<Integer> findRst_id(String phone) {
		// 매장ID를 찾지 못하면 null을, 찾으면 매장ID를 배열로 반환
		ArrayList<Integer> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select rst_id from restaurant_manager where phone='" + phone + "'");
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(resultSet.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}
}
