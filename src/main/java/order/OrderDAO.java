package order;

import java.sql.*;
import java.util.*;

import connectionMgr.DBConnectionMgr;
import menu.OptionInfoDTO;

/*
 * 작성자 : 정건영
 * 작성일 : 2022/03/14
 * 설명 : 장바구니 및 주문과 관련된 기능에 대한 DAO 클래스
 * 
 * 구현된 기능
 * 장바구니에 메뉴 추가, 장바구니 조회, 장바구니에서 메뉴 삭제, 장바구니 메뉴 수량 변경, 장바구니 비우기
 * 주문하기, 주문취소, 주문 상세 조회, 주문 내역 조회
 * 포인트 사용
*/

public class OrderDAO {
	private DBConnectionMgr connectionMgr;
	private Connection connection;
	private PreparedStatement pStatement;
	private ResultSet resultSet;

	public OrderDAO() {
		try {
			connectionMgr = DBConnectionMgr.getInstance();
			System.out.println("connectionMgr : " + connectionMgr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 장바구니에 메뉴 추가
	public int insertCartItem(String orderer, int menu_id, int[] options, int quantity) {
		// result가 0보다 크면 추가 성공
		// result가 -1이면 추가하려는 메뉴와 이미 추가된 메뉴가 서로 다른 매장인 경우 
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			// 추가하려는 메뉴의 매장ID 추출
			pStatement = connection.prepareStatement(
					"select rst_id from menu m, menu_category mc where menu_id=? and m.category_id=mc.category_id");
			pStatement.setInt(1, menu_id);
			resultSet = pStatement.executeQuery();
			connection.setAutoCommit(false);

			if (resultSet.next()) {
				int rst_id = resultSet.getInt(1);
				// 현재 장바구니에 담긴 메뉴 중 추출된 매장ID와 다른 매장ID를 조회
				pStatement = connection.prepareStatement("select rst_id from cart c, menu m, menu_category mc "
						+ "where orderer=? and c.menu_id=m.menu_id and m.category_id=mc.category_id and rst_id!=?");
				pStatement.setString(1, orderer);
				pStatement.setInt(2, rst_id);
				resultSet = pStatement.executeQuery();

				if (!resultSet.next()) {
					// 다른 매장ID가 없을 경우(장바구니가 비어있거나 같은 매장ID만 존재할 경우)
					String sql;

					if (options.length == 0) {
						sql = "insert into cart values(?, ?, null, ?)";
					} else {
						sql = "insert into cart values(?, ?, selected_option_seq.nextval, ?)";
					}

					pStatement = connection.prepareStatement(sql);
					pStatement.setString(1, orderer);
					pStatement.setInt(2, menu_id);
					pStatement.setInt(3, quantity);

					result = pStatement.executeUpdate();

					if (result > 0) {
						pStatement = connection
								.prepareStatement("insert into selected_option values(selected_option_seq.currval, ?)");
						for (int option_id : options) {
							pStatement.setInt(1, option_id);
							pStatement.addBatch();
							pStatement.clearParameters();
						}

						int[] array = pStatement.executeBatch();

						for (int n : array) {
							if (n == Statement.SUCCESS_NO_INFO) {
								// excuteBatch()가 성공했을 때 -2만 반환하는 에러가 있어서 result값을 1씩 증가시키도록 함
								result++;
							} else if (n == Statement.EXECUTE_FAILED) {
								// excuteBatch()가 실패하면 result를 -3으로 변경하고 반복 종료
								result = Statement.EXECUTE_FAILED;
								break;
							}
						}
					}
				}

				if (result > 0) {
					connection.commit();
				}
			}

			System.out.println("장바구니에 메뉴 추가 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 장바구니 조회
	public ArrayList<CartDTO> getCartItems(String orderer) {
		ArrayList<CartDTO> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from cart where orderer=?");
			pStatement.setString(1, orderer);
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new CartDTO(resultSet.getString("orderer"), resultSet.getInt("menu_id"),
						resultSet.getInt("bundle_id"), resultSet.getInt("quantity")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}

	// 장바구니에서 메뉴 삭제
	public int deleteCartItem(String orderer, int menu_id) {
		// result가 0보다 크면 삭제 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("delete from cart where orderer=? and menu_id=?");
			pStatement.setString(1, orderer);
			pStatement.setInt(2, menu_id);

			result = pStatement.executeUpdate();

			System.out.println("장바구니에서 메뉴 삭제 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	// 장바구니에 추가된 메뉴 수량 변경
	public int updateCartItem(String orderer, int menu_id, int quantity) {
		// result가 0보다 크면 수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("update cart set quantity=? where orderer=? and menu_id=?");
			pStatement.setInt(1, quantity);
			pStatement.setString(2, orderer);
			pStatement.setInt(3, menu_id);

			result = pStatement.executeUpdate();

			System.out.println("장바구니에 추가된 메뉴 수량 변경 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	// 장바구니 비우기
	public int cleanCart(String orderer) {
		// result가 0보다 크면 비우기 성공
		// result의 값은 삭제된 레코드 개수
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			connection.setAutoCommit(false);
			pStatement = connection.prepareStatement("delete from cart where orderer=?");
			pStatement.setString(1, orderer);

			result = pStatement.executeUpdate();
			
			if (result > 0) {
				connection.commit();
			}

			System.out.println("장바구니 비우기 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	// 주문하기
	public String insertOrder(String orderer, String destination, String coupon_id, int used_point, String payment_method,
			String order_request, int payment_status) {
		// 주문 성공 시 주문번호를 반환, 실패하면 null을 반환
		int result = -1;
		String order_number = null;

		try {
			connection = connectionMgr.getConnection();
			connection.setAutoCommit(false);
			pStatement = connection.prepareStatement("select to_char(systimestamp, 'yyyy-mmdd-Hh24missff3') from dual");
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				order_number = resultSet.getString(1);
			}

			pStatement = connection
					.prepareStatement("insert into order_history values(?, ?, ?, ?, ?, ?, systimestamp, ?, ?, ?)");
			pStatement.setString(1, order_number);
			pStatement.setString(2, orderer);
			pStatement.setString(3, destination);
			pStatement.setString(4, coupon_id);
			pStatement.setInt(5, used_point);
			pStatement.setString(6, payment_method);
			pStatement.setString(7, order_request);
			pStatement.setInt(8, payment_status);
			pStatement.setString(9, null);

			result = pStatement.executeUpdate();

			if (result > 0 && used_point > 0) {
				// 주문정보 추가에 성공하고 사용 포인트가 있다면 포인트를 차감
				pStatement = connection.prepareStatement("update member_info set point=point-? where email=?");
				pStatement.setInt(1, used_point);
				pStatement.setString(2, orderer);
				result = pStatement.executeUpdate();
			}

			if (result > 0) {
				// 장바구니에서 정보를 얻어와서 주문 상세정보에 추가
				result = 0;
				pStatement = connection.prepareStatement(
						"insert into order_detail select ?, menu_id, bundle_id, quantity from cart where orderer=?");
				pStatement.setString(1, order_number);
				pStatement.setString(2, orderer);
				result = pStatement.executeUpdate();

				if (result > 0) {
					// 주문 메뉴 추가에 성공하면 옵션 묶음을 추가
					result = 0;
					pStatement = connection.prepareStatement(
							"select s.* from cart c, selected_option s where orderer=? and c.bundle_id=s.bundle_id");
					pStatement.setString(1, orderer);
					resultSet = pStatement.executeQuery();

					if (resultSet.next()) {
						pStatement = connection.prepareStatement("insert into ordered_option "
								+ "select s.* from cart c, selected_option s where orderer=? and c.bundle_id=s.bundle_id");
						pStatement.setString(1, orderer);
						result = pStatement.executeUpdate();
					} else {
						result = 1;
					}
				}
			}

			if (result > 0) {
				connection.commit();
			}

			System.out.println("주문 번호 : " + order_number);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return order_number;
	}

	// 주문취소
	public int cancelOrder(String order_number, String orderer, String resaon_cancellation) {
		// result가 0보다 크면 주문취소 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select orderer from order_history where order_number=?");
			pStatement.setString(1, order_number);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				if (orderer.equals(resultSet.getString(1))) {
					pStatement = connection.prepareStatement(
							"update order_history set payment_status=0, reason_cancellation=? where order_number=?");
					pStatement.setString(1, resaon_cancellation);
					pStatement.setString(2, order_number);

					result = pStatement.executeUpdate();
				}
			}

			System.out.println("주문취소 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 주문한 메뉴 목록 조회
	public ArrayList<OrderDetailDTO> getOrderedItemList(String order_number) {
		// 주문한 메뉴들의 정보가 담긴 리스트 반환
		ArrayList<OrderDetailDTO> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from order_detail where order_number=?");
			pStatement.setString(1, order_number);
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new OrderDetailDTO(resultSet.getString("order_number"), resultSet.getInt("menu_id"),
						resultSet.getInt("bundle_id"), resultSet.getInt("quantity")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}

	// 주문내역 조회
	public OrderHistoryDTO getOrderHistory(String order_number) {
		OrderHistoryDTO result = null;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from order_history where order_number=?");
			pStatement.setString(1, order_number);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = new OrderHistoryDTO(resultSet.getString("order_number"), resultSet.getString("orderer"),
						resultSet.getString("destination"), resultSet.getString("coupon_id"), resultSet.getInt("used_point"),
						resultSet.getString("payment_method"), resultSet.getTimestamp("pay_date"),
						resultSet.getString("order_request"), resultSet.getInt("payment_status"),
						resultSet.getString("reason_cancellation"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 주문내역 목록 조회
	public ArrayList<OrderBasicInfoDTO> getOrderList(String orderer, int start, int end) {
		// 주문번호, 매장명, 매장로고, 메뉴이름 1개, 그 외에 주문한 메뉴 종류 수, 결제일자를 저장한 객체 리스트를 반환
		ArrayList<OrderBasicInfoDTO> resultList = new ArrayList<>();
		String order_number = "";
		ResultSet resultSet2 = null;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(
					"select * from (select oh.order_number, rst.rst_id, rst_name, rst_logo, count(menu_name) as count, pay_date "
							+ "from order_history oh, order_detail od, menu m, menu_category mc, restaurant rst "
							+ "where orderer=? and oh.order_number=od.order_number and od.menu_id=m.menu_id "
							+ "and m.category_id=mc.category_id and mc.rst_id=rst.rst_id "
							+ "group by oh.order_number, rst_name, rst_logo, pay_date order by oh.order_number desc) "
							+ "where rownum>=? and rownum<=?");
			pStatement.setString(1, orderer);
			pStatement.setInt(2, start);
			pStatement.setInt(3, end);
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
							resultSet.getTimestamp("pay_date")));
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

	// 메뉴에 해당하는 매장 배달비 조회
	public int getDelivery_tip(int menu_id) {
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select delivery_tip from restaurant r, menu_category mc, menu m "
					+ "where menu_id=? and m.category_id=mc.category_id and mc.rst_id=r.rst_id");
			pStatement.setInt(1, menu_id);
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

	// 리뷰 번호로 주문한 메뉴 이름 목록 조회
	public ArrayList<String> getOrderedItems(int review_number) {
		ArrayList<String> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select distinct menu_name "
					+ "from menu m, order_detail od, order_history oh, review r "
					+ "where review_number=? and r.order_number=oh.order_number and oh.order_number=od.order_number and od.menu_id=m.menu_id");
			pStatement.setInt(1, review_number);
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(resultSet.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}

	// 주문 번호로 주문한 메뉴 이름 목록 조회
	public ArrayList<String> getOrderedItems(String order_number) {
		ArrayList<String> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection
					.prepareStatement("select distinct menu_name " + "from menu m, order_detail od, order_history oh "
							+ "where oh.order_number=? and oh.order_number=od.order_number and od.menu_id=m.menu_id");
			pStatement.setString(1, order_number);
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(resultSet.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}

	// 선택한 옵션 조회
	public ArrayList<OptionInfoDTO> getSelectedOptions(int bundle_id) {
		ArrayList<OptionInfoDTO> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(
					"select o.* from selected_option s, option_info o where bundle_id=? and s.option_id=o.option_id");
			pStatement.setInt(1, bundle_id);
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new OptionInfoDTO(resultSet.getInt("option_id"), resultSet.getInt("group_id"),
						resultSet.getString("option_name"), resultSet.getInt("price"), resultSet.getInt("enable")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}

	// 주문한 옵션 조회
	public ArrayList<OptionInfoDTO> getOrderedOptions(int bundle_id) {
		ArrayList<OptionInfoDTO> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(
					"select o.* from ordered_option s, option_info o where bundle_id=? and s.option_id=o.option_id");
			pStatement.setInt(1, bundle_id);
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new OptionInfoDTO(resultSet.getInt("option_id"), resultSet.getInt("group_id"),
						resultSet.getString("option_name"), resultSet.getInt("price"), resultSet.getInt("enable")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}
}
