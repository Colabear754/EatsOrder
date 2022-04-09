package menu;

import java.sql.*;
import java.util.ArrayList;

import connectionMgr.DBConnectionMgr;

/*
 * 작성자 : 정건영
 * 작성일 : 2022/03/08
 * 설명 : 매장에 등록된 메뉴와 옵션과 관련된 기능에 대한 DAO 클래스
 * 
 * 구현된 기능
 * 메뉴 카테고리 조회, 메뉴 카테고리 추가, 메뉴 카테고리 수정, 메뉴 카테고리 삭제,
 * 메뉴 카테고리에 메뉴 추가, 카테고리별 메뉴 목록 조회, 메뉴 검색, 메뉴 조회, 메뉴 수정, 메뉴 삭제, 
 * 메뉴와 옵션 그룹 연결
 * 매장에 등록된 옵션 그룹 조회, 메뉴별 옵션 그룹 조회, 옵션 그룹 추가, 옵션 그룹 수정, 옵션 그룹 삭제
 * 옵션 그룹에 옵션 추가, 그룹별 옵션 조회, 옵션 수정, 옵션 삭제
*/

public class MenuAndOptionDAO {
	private DBConnectionMgr connectionMgr;
	private Connection connection;
	private PreparedStatement pStatement;
	private ResultSet resultSet;

	public MenuAndOptionDAO() {
		try {
			connectionMgr = DBConnectionMgr.getInstance();
			System.out.println("connectionMgr : " + connectionMgr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int insertMenuCategory(int rst_id, String category_name) {
		// result가 0보다 크면 메뉴 카테고리 추가 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into menu_category values(mc_index_seq.nextval, ?, ?)");
			pStatement.setString(1, category_name);
			pStatement.setInt(2, rst_id);

			result = pStatement.executeUpdate();

			System.out.println(rst_id + "번 매장에 " + category_name + " 카테고리 추가 성공 여부 : " + result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	// 메뉴 카테고리 조회
	public MenuCategoryDTO getMenuCategory(int category_id) {
		MenuCategoryDTO result = null;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from menu_category where category_id=?");
			pStatement.setInt(1, category_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = new MenuCategoryDTO(resultSet.getInt("category_id"), resultSet.getString("category_name"),
						resultSet.getInt("rst_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 메뉴 카테고리 리스트 조회
	public ArrayList<MenuCategoryDTO> getMenuCategories(int rst_id) {
		// 매장ID에 해당하는 메뉴 카테고리 리스트 조회
		ArrayList<MenuCategoryDTO> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from menu_category where rst_id=" + rst_id);
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new MenuCategoryDTO(resultSet.getInt("category_id"), resultSet.getString("category_name"),
						resultSet.getInt("rst_id")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}

	public int updateMenuCategory(int category_id, String category_name) {
		// result가 0보다 크면 메뉴 카테고리 수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from menu_category where category_id=" + category_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				pStatement = connection.prepareStatement(
						"update menu_category set category_name='" + category_name + "' where category_id=" + category_id);
				result = pStatement.executeUpdate();
			}

			System.out.println("메뉴 카테고리 수정 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	public int deleteMenuCategory(int rst_id, int category_id) {
		// result가 0보다 크면 메뉴 카테고리 삭제 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select rst_id from menu_category where category_id=" + category_id);
			resultSet = pStatement.executeQuery();
			connection.setAutoCommit(false);

			if (resultSet.next()) {
				if (rst_id == resultSet.getInt("rst_id")) {
					pStatement = connection.prepareStatement("delete from menu_category where category_id=" + category_id);
					result = pStatement.executeUpdate();
				}
			}

			System.out.println("메뉴 카테고리 삭제 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	public int insertMenu(int category_id, String menu_name, String menu_info, String menu_photo, int price, int enable) {
		// 설명과 사진이 포함된 메뉴 추가
		// result가 0보다 크면 메뉴 추가 성공
		int result = -1;
		String sql;

		if (menu_info.isBlank() && menu_photo.isBlank()) {
			// 메뉴 정보와 사진 모두 없는 메뉴
			sql = "insert into menu(menu_id, category_id, menu_name, price, enable) values(menu_index_seq.nextval, "
					+ category_id + ", '" + menu_name + "', " + price + ", " + enable + ")";
		} else if (menu_info.isBlank()) {
			// 메뉴 정보는 없지만 사진은 있는 메뉴
			sql = "insert into menu(menu_id, category_id, menu_name, menu_photo, price, enable) values(menu_index_seq.nextval, "
					+ category_id + ", '" + menu_name + "', '" + menu_photo + "', " + price + ", " + enable + ")";
		} else if (menu_photo.isBlank()) {
			// 메뉴 사진은 없지만 정보는 있는 메뉴
			sql = "insert into menu(menu_id, category_id, menu_name, menu_info, price, enable) values(menu_index_seq.nextval, "
					+ category_id + ", '" + menu_name + "', '" + menu_info + "', " + price + ", " + enable + ")";
		} else {
			// 메뉴 정보와 사진 모두 있는 메뉴
			sql = "insert into menu values(menu_index_seq.nextval, " + category_id + ", '" + menu_name + "', '" + menu_info
					+ "', '" + menu_photo + "', " + price + ", " + enable + ")";
		}

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(sql);

			result = pStatement.executeUpdate();

			System.out.println(category_id + "번 카테고리에" + menu_name + " 메뉴 추가 성공 여부 : " + result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	// 카테고리별 메뉴 목록 조회
	public ArrayList<MenuDTO> getMenuList(int rst_id, int category_id) {
		// 매장ID와 메뉴 카테고리 ID에 해당하는 메뉴 리스트 조회
		ArrayList<MenuDTO> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select menu.* from menu, menu_category mc where menu.category_id=? "
					+ "and menu.category_id=mc.category_id and mc.rst_id=?");
			pStatement.setInt(1, category_id);
			pStatement.setInt(2, rst_id);
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new MenuDTO(resultSet.getInt("menu_id"), resultSet.getInt("category_id"),
						resultSet.getString("menu_name"), resultSet.getString("menu_info"),
						resultSet.getString("menu_photo"), resultSet.getInt("price"), resultSet.getInt("enable")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}
		return resultList;
	}

	// 메뉴 검색
	public ArrayList<MenuRstDTO> getMenuList(String searchText, String sido, String sigungu, String bname) {
		ArrayList<MenuRstDTO> resultList = new ArrayList<>();
		ResultSet resultSet2 = null;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(
					"select m.menu_id, r.rst_id, rst_name, round(avg(rating), 1) as rating, count(review_number) as count"
							+ "from menu m, menu_category mc, restaurant r, review, order_history oh, order_detail od "
							+ "where menu_name like ? and sido=? and sigungu=? and bname=? and m.category_id=mc.category_id and "
							+ "mc.rst_id=r.rst_id and od.menu_id=m.menu_id and od.order_number=oh.order_number and oh.order_number=review.order_number "
							+ "group by od.menu_id, m.menu_id, menu_name, r.rst_id, rst_name");
			pStatement.setString(1, "%" + searchText + "%");
			pStatement.setString(2, sido);
			pStatement.setString(3, sigungu);
			pStatement.setString(4, bname);

			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				pStatement = connection.prepareStatement("select * from menu where menu_id=?");
				pStatement.setInt(1, resultSet.getInt("menu_id"));
				resultSet2 = pStatement.executeQuery();

				if (resultSet2.next()) {
					resultList.add(new MenuRstDTO(new MenuDTO(resultSet2.getInt("menu_id"), resultSet2.getInt("category_id"),
							resultSet2.getString("menu_name"), resultSet2.getString("menu_info"),
							resultSet2.getString("menu_photo"), resultSet2.getInt("price"), resultSet2.getInt("enable")),
							resultSet.getInt("rst_id"), resultSet.getString("rst_name"), resultSet.getDouble("rating"),
							resultSet.getInt("count")));
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

	// 메뉴 조회
	public MenuDTO getMenu(int menu_id) {
		MenuDTO result = null;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from menu where menu_id=?");
			pStatement.setInt(1, menu_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = new MenuDTO(resultSet.getInt("menu_id"), resultSet.getInt("category_id"),
						resultSet.getString("menu_name"), resultSet.getString("menu_info"),
						resultSet.getString("menu_photo"), resultSet.getInt("price"), resultSet.getInt("enable"));
			}

			System.out.println("조회한 메뉴 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 메뉴 수정
	public int updateMenu(int menu_id, int category_id, String menu_name, String menu_info, String menu_photo, int price,
			int enable) {
		// result가 0보다 크면 메뉴 수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from menu where menu_id=" + menu_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				pStatement = connection.prepareStatement("update menu set "
						+ "category_id=?, menu_name=?, menu_info=?, menu_photo=?, price=?, enable=? where menu_id=?");
				pStatement.setInt(1, category_id);
				pStatement.setString(2, menu_name);
				pStatement.setString(3, menu_info);
				pStatement.setString(4, menu_photo);
				pStatement.setInt(5, price);
				pStatement.setInt(6, enable);
				pStatement.setInt(7, menu_id);

				result = pStatement.executeUpdate();

				System.out.println("메뉴 수정 성공 여부 : " + result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	public int deleteMenu(int rst_id, int menu_id) {
		// result가 0보다 크면 메뉴 카테고리 삭제 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select rst_id from menu, menu_category mc where menu_id=" + menu_id
					+ " and menu.category_id=mc.category_id");
			resultSet = pStatement.executeQuery();
			connection.setAutoCommit(false);

			if (resultSet.next()) {
				if (rst_id == resultSet.getInt(1)) {
					pStatement = connection.prepareStatement("delete from menu where menu_id=" + menu_id);
					result = pStatement.executeUpdate();
				}
			}

			System.out.println("메뉴 삭제 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	public int setMenuOptionGroup(int menu_id, int group_id) {
		// result가 0보다 크면 메뉴와 옵션 그룹 연결 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into menu_option values(?, ?)");
			pStatement.setInt(1, menu_id);
			pStatement.setInt(2, group_id);

			result = pStatement.executeUpdate();

			System.out.println("메뉴와 옵션 그룹 연결 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	public int insertOptionGroup(int rst_id, String group_name, int essential) {
		// result가 0보다 크면 옵션그룹 추가 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into option_group values(og_index_seq.nextval, ?, ?, ?)");
			pStatement.setInt(1, rst_id);
			pStatement.setString(2, group_name);
			pStatement.setInt(3, essential);

			result = pStatement.executeUpdate();

			System.out.println("옵션그룹 추가 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	// 옵션그룹 조회
	public OptionGroupDTO getOptionGroup(int group_id) {
		OptionGroupDTO result = null;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from option_group where group_id=?");
			pStatement.setInt(1, group_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = new OptionGroupDTO(resultSet.getInt("group_id"), resultSet.getInt("rst_id"),
						resultSet.getString("group_name"), resultSet.getInt("essential"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 옵션그룹 리스트 조회
	public ArrayList<OptionGroupDTO> getMenuOptionGroups(int menu_id) {
		// 메뉴ID에 해당하는 옵션그룹 조회
		ArrayList<OptionGroupDTO> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select og.* from option_group og, menu_option mo where mo.menu_id="
					+ menu_id + " and mo.group_id=og.group_id");
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new OptionGroupDTO(resultSet.getInt("group_id"), resultSet.getInt("rst_id"),
						resultSet.getString("group_name"), resultSet.getInt("essential")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}

	public ArrayList<OptionGroupDTO> getRstOptionGroups(int rst_id) {
		// 매장ID에 해당하는 옵션그룹 조회
		ArrayList<OptionGroupDTO> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from option_group where rst_id=" + rst_id);
			resultSet = pStatement.executeQuery();

			while (resultSet.next()) {
				resultList.add(new OptionGroupDTO(resultSet.getInt("group_id"), resultSet.getInt("rst_id"),
						resultSet.getString("group_name"), resultSet.getInt("essential")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return resultList;
	}

	public int updateOptionGroup(int group_id, String group_name, int essential) {
		// result가 0보다 크면 옵션그룹 수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from option_group where group_id=" + group_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				pStatement = connection
						.prepareStatement("update option_group set group_name=?, essential=? where group_id=?");
				pStatement.setString(1, group_name);
				pStatement.setInt(2, essential);
				pStatement.setInt(3, group_id);

				result = pStatement.executeUpdate();
			}

			System.out.println("옵션그룹 수정 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	public int deleteOptionGroup(int group_id, int rst_id) {
		// result가 0보다 크면 옵션그룹 추가 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select rst_id from option_group where group_id=" + group_id);
			resultSet = pStatement.executeQuery();
			connection.setAutoCommit(false);

			if (resultSet.next()) {
				if (rst_id == resultSet.getInt(1)) {
					pStatement = connection.prepareStatement("delete from option_group where group_id=" + group_id);
					result = pStatement.executeUpdate();

					connection.commit();
				}
			}

			System.out.println("옵션그룹 삭제 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	public int insertOption(int group_id, String option_name, int price, int enable) {
		// result가 0보다 크면 옵션 추가 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("insert into option_info values(option_index_seq.nextval, ?, ?, ?, ?)");
			pStatement.setInt(1, group_id);
			pStatement.setString(2, option_name);
			pStatement.setInt(3, price);
			pStatement.setInt(4, enable);

			result = pStatement.executeUpdate();

			System.out.println("옵션 추가 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	// 옵션 조회
	public OptionInfoDTO getOption(int option_id) {
		OptionInfoDTO result = null;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from option_info where option_id=?");
			pStatement.setInt(1, option_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = new OptionInfoDTO(resultSet.getInt("option_id"), resultSet.getInt("group_id"),
						resultSet.getString("option_name"), resultSet.getInt("price"), resultSet.getInt("enable"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 옵션 리스트 조회
	public ArrayList<OptionInfoDTO> getOptionList(int group_id) {
		// 옵션그룹ID에 해당하는 옵션 리스트 조회
		ArrayList<OptionInfoDTO> resultList = new ArrayList<>();

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from option_info where group_id=" + group_id);
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

	public int updateOption(int option_id, int group_id, String option_name, int price, int enable) {
		// result가 0보다 크면 옵션 수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select option_id from option_info where group_id=" + option_id);
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				if (option_id == resultSet.getInt(1)) {
					pStatement = connection.prepareStatement(
							"update option_info set group_id=?, option_name=?, price=?, enable=? where option_id=?");
					pStatement.setInt(1, group_id);
					pStatement.setString(2, option_name);
					pStatement.setInt(3, price);
					pStatement.setInt(4, enable);
					pStatement.setInt(5, option_id);

					result = pStatement.executeUpdate();
				}
			}

			System.out.println("옵션 수정 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	public int deleteOption(int option_id, int rst_id) {
		// result가 0보다 크면 옵션그룹 추가 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select rst_id from option_info o, option_group og "
					+ "where option_id=" + option_id + " and o.group_id=og.group_id and rst_id=" + rst_id);
			resultSet = pStatement.executeQuery();
			connection.setAutoCommit(false);

			if (resultSet.next()) {
				if (rst_id == resultSet.getInt(1)) {
					pStatement = connection.prepareStatement("delete from option_info where option_id=" + option_id);
					result = pStatement.executeUpdate();

					connection.commit();
				}
			}

			System.out.println("옵션 삭제 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}
}
