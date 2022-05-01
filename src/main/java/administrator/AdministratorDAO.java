package administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connectionMgr.DBConnectionMgr;

public class AdministratorDAO {
	private DBConnectionMgr connectionMgr;
	private Connection connection;
	private PreparedStatement pStatement;
	private ResultSet resultSet;
	
	public AdministratorDAO() {
		try {
			connectionMgr = DBConnectionMgr.getInstance();
			System.out.println("connectionMgr : " + connectionMgr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원 로그인
	public boolean adminLogin(String admin_id, String password) {
		boolean result = false;

		try {
			connection = connectionMgr.getConnection();
			pStatement = connection
					.prepareStatement("select admin_id from administrator where admin_id=? and password=pkg_crypto.encrypt(?)");
			pStatement.setString(1, admin_id);
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
}
