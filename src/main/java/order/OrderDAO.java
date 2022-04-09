package order;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import connectionMgr.DBConnectionMgr;

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

	// 장바구니에 옵션을 선택하지 않은 메뉴 추가
	public int insertCartItem(String orderer, int menu_id, int quantity) {
		// result가 0보다 크면 추가 성공
		// result가 -1이면 추가하려는 메뉴와 이미 추가된 메뉴가 서로 다른 매장인 경우 
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			// 추가하려는 메뉴의 매장ID 추출
			pStatement = connection.prepareStatement(
					"select rst_id from menu m, menu_category mc " + "where menu_id=? and m.category_id=mc.category_id");
			pStatement.setInt(1, menu_id);
			resultSet = pStatement.executeQuery();

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
					pStatement = connection.prepareStatement("select * from cart where orderer=? and menu_id=?");
					pStatement.setString(1, orderer);
					pStatement.setInt(2, menu_id);
					resultSet = pStatement.executeQuery();

					if (resultSet.next()) {
						// 같은 메뉴가 이미 등록되어 있으면 수량만 변경
						pStatement = connection.prepareStatement("update cart set quantity=? where orderer=? and menu_id=?");
						pStatement.setInt(1, resultSet.getInt("quantity") + quantity);
						pStatement.setString(2, orderer);
						pStatement.setInt(3, menu_id);
					} else {
						// 새로 메뉴를 추가하는 경우
						pStatement = connection
								.prepareStatement("insert into cart(orderer, menu_id, quantity) values(?, ?, ?)");
						pStatement.setString(1, orderer);
						pStatement.setInt(2, menu_id);
						pStatement.setInt(3, quantity);
					}

					result = pStatement.executeUpdate();
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

	// 장바구니에 옵션을 선택한 메뉴 추가
	public int insertCartItem(String orderer, int menu_id, int option_id, int quantity) {
		// result가 0보다 크면 추가 성공
		// result가 -1이면 추가하려는 메뉴와 이미 추가된 메뉴가 서로 다른 매장인 경우 
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			// 추가하려는 메뉴의 매장ID 추출
			pStatement = connection.prepareStatement(
					"select rst_id from menu m, menu_category mc " + "where menu_id=? and m.category_id=mc.category_id");
			pStatement.setInt(1, menu_id);
			resultSet = pStatement.executeQuery();

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
					pStatement = connection.prepareStatement("select * from cart where orderer=? and menu_id=?");
					pStatement.setString(1, orderer);
					pStatement.setInt(2, menu_id);
					resultSet = pStatement.executeQuery();

					if (resultSet.next()) {
						// 같은 메뉴가 이미 등록되어 있으면 수량만 변경
						pStatement = connection.prepareStatement(
								"update cart set quantity=? where orderer=? and menu_id=? and option_id=?");
						pStatement.setInt(1, resultSet.getInt("quantity") + quantity);
						pStatement.setString(2, orderer);
						pStatement.setInt(3, menu_id);
						pStatement.setInt(4, option_id);
					} else {
						// 새로 메뉴를 추가하는 경우
						pStatement = connection.prepareStatement("insert into cart values(?, ?, ?, ?)");
						pStatement.setString(1, orderer);
						pStatement.setInt(2, menu_id);
						pStatement.setInt(3, option_id);
						pStatement.setInt(4, quantity);
					}

					result = pStatement.executeUpdate();
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
						resultSet.getInt("option_id"), resultSet.getInt("quantity")));
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
			pStatement = connection.prepareStatement("delete from cart where orderer=?");
			pStatement.setString(1, orderer);

			result = pStatement.executeUpdate();

			System.out.println("장바구니 비우기 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	// 주문하기
	public int insertOrder(String orderer, String destination, String coupon_id, int used_point, String payment_method,
			String order_request, int payment_status) {
		// result의 값은 주문한 메뉴의 개수 + 1
		int result = -1;
		int[] temp = {};
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MMdd-HHmmssSSS");
		String order_number = format.format(Calendar.getInstance().getTime()); // 주문번호는 현재시간을 밀리초까지 부여

		try {
			connection = connectionMgr.getConnection();
			connection.setAutoCommit(false);
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

			if (result > 0) {
				// 주문정보 추가에 성공하면 장바구니에서 정보를 얻어와서 주문 상세정보에 추가
				result = 0;
				pStatement = connection.prepareStatement("select * from cart where orderer=?");
				pStatement.setString(1, orderer);
				resultSet = pStatement.executeQuery();

				pStatement = connection.prepareStatement("insert into order_detail values(?, ?, ?, ?)");

				while (resultSet.next()) {
					pStatement.setString(1, order_number);
					pStatement.setInt(2, resultSet.getInt("menu_id"));
					pStatement.setInt(3, resultSet.getInt("option_id"));
					pStatement.setInt(4, resultSet.getInt("quantity"));
					pStatement.addBatch();
					pStatement.clearParameters();
				}

				temp = pStatement.executeBatch();

				for (int n : temp) {
					if (n == Statement.SUCCESS_NO_INFO) {
						// excuteBatch()가 성공했을 때 -2만 반환하는 에러가 있어서 result값을 1씩 증가시키도록 함
						result++;
					} else if (n == Statement.EXECUTE_FAILED) {
						// excuteBatch()가 실패하면 result를 -3으로 변경하고 반복 종료
						result = Statement.EXECUTE_FAILED;
						break;
					}
				}

				if (result > 0 && used_point > 0) {
					// 사용 포인트가 있다면 포인트를 차감
					pStatement = connection.prepareStatement("update member_info set point=point-? where email=?");
					pStatement.setInt(1, used_point);
					pStatement.setString(2, orderer);
					result += pStatement.executeUpdate();
				}
			}

			if (result > 0) {
				connection.commit();
			}

			System.out.println("주문 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
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

	// 주문 상세 조회
	public OrderDetailInfoDTO getOrderDetailInfo(String order_number) {
		// 주문상세정보, 주문메뉴 및 옵션 리스트, 총액을 저장한 객체를 반환
		OrderDetailInfoDTO result = null;
		ArrayList<String> menuList = new ArrayList<>();
		ArrayList<String> optionList = new ArrayList<>();
		ArrayList<Integer> quantityList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select oh.*, discount_amount "
					+ "from order_history oh left join valid_coupon vc on oh.coupon_id=vc.coupon_id where order_number=?");
			pStatement.setString(1, order_number);

			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = new OrderDetailInfoDTO();
				result.setOrderHistory(new OrderHistoryDTO(resultSet.getString("order_number"),
						resultSet.getString("orderer"), resultSet.getString("destination"), resultSet.getString("coupon_id"),
						resultSet.getInt("used_point"), resultSet.getString("payment_method"),
						resultSet.getTimestamp("pay_date"), resultSet.getString("order_request"),
						resultSet.getInt("payment_status"), resultSet.getString("reason_cancellation")));
				result.setUsed_point(resultSet.getInt("used_point"));
				result.setDiscount_amount(resultSet.getInt("discount_amount"));

				pStatement = connection
						.prepareStatement("select menu_name, m.price as mp, option_name, oi.price op, quantity "
								+ "from order_detail od join menu m on od.menu_id=m.menu_id "
								+ "left join option_info oi on oi.option_id=od.option_id where od.order_number=?");
				pStatement.setString(1, order_number);
				resultSet = pStatement.executeQuery();

				while (resultSet.next()) {
					menuList.add(resultSet.getString("menu_name"));
					optionList.add(resultSet.getString("option_name"));
					quantityList.add(resultSet.getInt("quantity"));
					result.setMenuList(menuList);
					result.setOptionList(optionList);
					result.setQuantityList(quantityList);
					result.setPrice((resultSet.getInt("mp") + resultSet.getInt("op")) * resultSet.getInt("quantity"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 주문내역 조회
	public ArrayList<OrderBasicInfoDTO> getOrderList(String orderer, int start, int end) {
		// 주문번호, 매장명, 매장로고, 메뉴이름 1개, 그 외에 주문한 메뉴 종류 수, 결제일자를 저장한 객체 리스를 반환
		ArrayList<OrderBasicInfoDTO> resultList = new ArrayList<>();
		String order_number = "";
		ResultSet resultSet2 = null;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(
					"select * from (select oh.order_number, rst_name, rst_logo, count(menu_name) as count, pay_date "
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
					resultList.add(new OrderBasicInfoDTO(order_number, resultSet.getString("rst_name"),
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
}
