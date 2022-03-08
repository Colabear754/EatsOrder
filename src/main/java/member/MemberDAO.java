package member;

import java.sql.*;

import connectionMgr.DBConnectionMgr;

/*
 * 구현된 기능
 * 로그인, 중복값 체크, 회원가입, 회원탈퇴, 회원수정, 탈퇴한지 30일 지난 회원정보 제거, 회원조회, 아이디/비밀번호 찾기
*/

public class MemberDAO {
	private DBConnectionMgr connectionMgr;
	private Connection connection;
	private PreparedStatement pStatement;
	private ResultSet resultSet;

	public MemberDAO() {
		try {
			connectionMgr = DBConnectionMgr.getInstance();
			System.out.println("connectionMgr : " + connectionMgr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 로그인
	public boolean login(String email, String password) {
		boolean result = false;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from member_login where email=? and password=?");
			pStatement.setString(1, email);
			pStatement.setString(2, password);

			resultSet = pStatement.executeQuery();

			result = resultSet.next();
			System.out.println("로그인 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 중복값 체크
	public boolean checkMemberInfo(String data, int type) {
		// type = 1: 이메일, 2: 전화번호, 3: 닉네임 중복값 체크
		boolean result = false;
		String checkType = "";

		switch (type) {
		case 1:
			checkType = "email";
			break;
		case 2:
			checkType = "phone";
			break;
		case 3:
			checkType = "nickname";
			break;
		default:
			break;
		}

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select ? from member_info where ?=?");
			pStatement.setString(1, checkType);
			pStatement.setString(2, checkType);
			pStatement.setString(3, data);

			resultSet = pStatement.executeQuery();

			result = resultSet.next();

			System.out.println(checkType + " 중복여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}
		return result;
	}

	// 회원가입
	public int insertMember(String email, String password, String phone, String nickname, int receive_marketing) {
		// 1 : 가입 성공, 0 : 가입 실패
		int result = 0;

		try {
			connection = connectionMgr.getConnection();

			pStatement = connection.prepareStatement(
					"insert into member_info(email, phone, nickname, receive_marketing) values(?, ?, ?, ?)");
			pStatement.setString(1, email);
			pStatement.setString(2, phone);
			pStatement.setString(3, nickname);
			pStatement.setInt(4, receive_marketing);

			connection.setAutoCommit(false);

			result = pStatement.executeUpdate();

			if (result > 0) {
				connection.commit();
				pStatement = connection.prepareStatement("insert into member_login values(?, ?)");
				pStatement.setString(1, email);
				pStatement.setString(2, password);

				result = pStatement.executeUpdate();

				if (result > 0) {
					connection.commit();
				}
			}

			System.out.println("회원가입 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	// 회원탈퇴
	public int deleteMember(String email, String password, String reason_withdraw) {
		// result가 0보다 크면 탈퇴 성공
		int result = 0;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select password from member_login where email='" + email + "'");
			resultSet = pStatement.executeQuery();

			connection.setAutoCommit(false);
			if (resultSet.next()) {
				if (password.equals(resultSet.getString("password"))) {
					if (password.equals(resultSet.getString("password"))) {
						pStatement = connection.prepareStatement("delete from member_login where email='" + email + "'");
						result = pStatement.executeUpdate();
						if (result > 0) {
							System.out.println("로그인 정보 삭제 성공");
							pStatement = connection.prepareStatement(
									"update member_info set withdraw_date=sysdate where email='" + email + "'");
							result = pStatement.executeUpdate();
							if (result > 0) {
								System.out.println("회원탈퇴일 수정 성공");
								pStatement = connection
										.prepareStatement("insert into withdraw_member values(member_index_seq.nextval, '"
												+ email + "', sysdate, '" + reason_withdraw + "')");
								result = pStatement.executeUpdate();
							}
						}
					}
				}
			}

			if (result > 0) {
				connection.commit();
				System.out.println("회원탈퇴 성공");
			} else {
				System.out.println("회원탈퇴 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 회원수정
	public int updateMember(String email, String password, String phone, String nickname, int receive_marketing) {
		// result가 0보다 크면 회원수정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select password from member_login where email='" + email + "'");
			resultSet = pStatement.executeQuery();

			connection.setAutoCommit(false);
			if (resultSet.next()) {
				if (resultSet.getString(1).equals(password)) {
					pStatement = connection.prepareStatement(
							"update member_info set phone=?, nickname=?, receive_marketing=? where email='?");
					pStatement.setString(1, phone);
					pStatement.setString(2, nickname);
					pStatement.setInt(3, receive_marketing);
					pStatement.setString(4, email);

					result = pStatement.executeUpdate();
				}
				if (result > 0 && !password.isBlank()) {
					pStatement = connection.prepareStatement("update member_login set password=? where email=?");
					pStatement.setString(1, password);
					pStatement.setString(2, email);

					result = pStatement.executeUpdate();
				}

				if (result > 0) {
					connection.commit();
					System.out.println("회원정보 수정 성공");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 탈퇴한지 30일 지난 회원정보 제거
	public int removeMemberInfo() {
		// 탈퇴 후 30일이 지난 회원정보를 member_info 테이블에서 제거
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("delete from member_info where trunc(sysdate) - withdraw_date > 30");
			connection.setAutoCommit(false);
			result = pStatement.executeUpdate();
			System.out.println("탈퇴 후 30일이 경과한 회원 수 : " + result);

			if (result > 0) {
				connection.commit();
				System.out.println("탈퇴 후 30일이 경과한 회원정보 삭제 완료");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}

	// 회원조회
	public MemberInfoDTO getMember(String email) {
		MemberInfoDTO result = null;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("select * from member_info where email='" + email + "'");
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = new MemberInfoDTO(resultSet.getString("email"), resultSet.getString("phone"),
						resultSet.getString("nickname"), resultSet.getString("membership"), resultSet.getInt("point"),
						resultSet.getDate("join_date"), resultSet.getDate("withdraw_date"),
						resultSet.getInt("receive_marketing"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 아이디 찾기
	public String findEmail(String phone) {
		// 전화번호에 해당하는 email 반환. 해당 정보가 없으면 null 반환
		String result = "";

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement(
					"select email from member_info where phone='" + phone + "' and " + "withdraw_date is null");
			resultSet = pStatement.executeQuery();

			if (resultSet.next()) {
				result = resultSet.getString("email");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 비밀번호 재설정을 위한 정보 화인
	public boolean checkValidMember(String email, String phone) {
		boolean result = false;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection
					.prepareStatement("select * from member_info where email=? and phone=? and withdraw_date is null");
			pStatement.setString(1, email);
			pStatement.setString(2, phone);

			resultSet = pStatement.executeQuery();

			result = resultSet.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement, resultSet);
		}

		return result;
	}

	// 비밀번호 재설정
	public int resetPassword(String email, String password) {
		// 이메일과 전화번호에 해당하는 비밀번호 재설정
		// result = -1 : 해당정보 없음, 0 : 비밀번호 재설정 실패, 1 : 비밀번호 재설정 성공
		int result = -1;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection.prepareStatement("update member_login set password=? where email=?");
			pStatement.setString(1, password);
			pStatement.setString(2, email);
			
			result = pStatement.executeUpdate();
			
			System.out.println("비밀번호 재설정 성공 여부 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connectionMgr.freeConnection(connection, pStatement);
		}

		return result;
	}
}
