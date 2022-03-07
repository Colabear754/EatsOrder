package connectionMgr;
/**
 * Copyright(c) 2001 iSavvix Corporation (http://www.isavvix.com/)
 *
 *                        All rights reserved
 *
 * Permission to use, copy, modify and distribute this material for
 * any purpose and without fee is hereby granted, provided that the
 * above copyright notice and this permission notice appear in all
 * copies, and that the name of iSavvix Corporation not be used in
 * advertising or publicity pertaining to this material without the
 * specific, prior written permission of an authorized representative of
 * iSavvix Corporation.
 *
 * ISAVVIX CORPORATION MAKES NO REPRESENTATIONS AND EXTENDS NO WARRANTIES,
 * EXPRESS OR IMPLIED, WITH RESPECT TO THE SOFTWARE, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR ANY PARTICULAR PURPOSE, AND THE WARRANTY AGAINST
 * INFRINGEMENT OF PATENTS OR OTHER INTELLECTUAL PROPERTY RIGHTS.  THE
 * SOFTWARE IS PROVIDED "AS IS", AND IN NO EVENT SHALL ISAVVIX CORPORATION OR
 * ANY OF ITS AFFILIATES BE LIABLE FOR ANY DAMAGES, INCLUDING ANY
 * LOST PROFITS OR OTHER INCIDENTAL OR CONSEQUENTIAL DAMAGES RELATING
 * TO THE SOFTWARE.
 *
 */


import java.sql.*;
import java.util.Properties;
import java.util.Vector;


/**
 * Manages a java.sql.Connection pool.
 *
 * @author  Anil Hemrajani
 */
public class DBConnectionMgr {

    //커넥션풀을 벡터로 구성
	//private MemberDBMgr mem =null;
    private Vector connections = new Vector(10);
    /*   MySQL
	private String _driver = "org.gjt.mm.mysql.Driver",
    _url = "jdbc:mysql://127.0.0.1:3306/mydb?useUnicode=true&characterEncoding=EUC_KR",
    _user = "root",
    _password = "1234"; */

    private String _driver = "oracle.jdbc.driver.OracleDriver";
    private String _url = "jdbc:oracle:thin:@localhost:1521:orcl";
    private String _user = "delivery";
    private String _password = "1234";

    private boolean _traceOn = false;
    private boolean initialized = false;
	
	//커넥션을 10개 준비
    private int _openConnections = 10;

    //커넥션풀객체를 선언
    private static DBConnectionMgr instance = null;

    public DBConnectionMgr() {
    }

    /** Use this method to set the maximum number of open connections before
     unused connections are closed.
     */
  
    //커넥션풀을 얻어오는 정적메소드
    public static DBConnectionMgr getInstance() {
        //커넥션풀이 생성이 안되어있다면
		if (instance == null) {
            synchronized (DBConnectionMgr.class) {
                //생성이 안되어있다면
				if (instance == null) {
					//객체생성
                    instance = new DBConnectionMgr();
                }
            }
        }
        return instance;//호출한 클래스쪽으로 반환
    }

    public void setOpenConnectionCount(int count) {
        _openConnections = count;
    }


    public void setEnableTrace(boolean enable) {
        _traceOn = enable;
    }


    /** Returns a Vector of java.sql.Connection objects */
    public Vector getConnectionList() {
        return connections;
    }


    /** Opens specified "count" of connections and adds them to the existing pool */
    //초기에 연결객체를 설정해주는 메소드

	public synchronized void setInitOpenConnections(int count)
            throws SQLException {

        Connection c = null;//생성할 객체
        ConnectionObject co = null;//생성한 연결객체
		                           //관리해주는 객체

        for (int i = 0; i < count; i++) {
			//count갯수만큼 연결객체를 생성
            c = createConnection();
			//벡터에 등록할 연결객체,대여유무
            co = new ConnectionObject(c, false);
             //최종적으로 벡터에 연결객체를 추가
            connections.addElement(co);
            trace("ConnectionPoolManager: Adding new DB connection to pool (" + connections.size() + ")");
        }
    }


    /** Returns a count of open connections */
    public int getConnectionCount() {
        return connections.size();
    }


    /** Returns an unused existing or new connection.  */
    //연결객체를 얻어오는 메소드

	@SuppressWarnings("deprecation")
	public synchronized Connection getConnection()
            throws Exception {
        if (!initialized) {
			//접속할 DB의 드라이버를 메모리에 로드
            Class c = Class.forName(_driver);
			//자동 등록(드라이버클래스)
            DriverManager.registerDriver((Driver) c.newInstance());

            initialized = true;//접속상태 
        }


        Connection c = null;
        ConnectionObject co = null;
		//사용중이지 않은 연결객체
        boolean badConnection = false;


        for (int i = 0; i < connections.size(); i++) {
			//벡터에 들어간 연결객체를 꺼내온다.
            co = (ConnectionObject) connections.elementAt(i);

            // If connection is not in use, test to ensure it's still valid!
            if (!co.inUse) {//빌려주지 않은 상태라면 
                try {
					//쓰지않은 상태의 연결객체 얻어옴
                    badConnection = co.connection.isClosed();
                    if (!badConnection)
                        badConnection = (co.connection.getWarnings() != null);
                } catch (Exception e) {
                    badConnection = true;
                    e.printStackTrace();
                }

                // Connection is bad, remove from pool
                if (badConnection) { //쓰지않고 있으면
				    //벡터에서 제거하라
                    connections.removeElementAt(i);
                    trace("ConnectionPoolManager: Remove disconnected DB connection #" + i);
                    continue;
                }

                c = co.connection;
                co.inUse = true;//빌려준 상태

                trace("ConnectionPoolManager: Using existing DB connection #" + (i + 1));
                break;
            }
        }
        //요청이 들어온다면(부족하다면)
        if (c == null) {
            c = createConnection();
            co = new ConnectionObject(c, true);
            connections.addElement(co);//벡터에추가

            trace("ConnectionPoolManager: Creating new DB connection #" + connections.size());
        }

        return c;//반환
    }


    /** Marks a flag in the ConnectionObject to indicate this connection is no longer in use */
    //반납해주는 메소드
	public synchronized void freeConnection(Connection c) {
        if (c == null)//반납해주는 연결객체가 없으면
            return;

        ConnectionObject co = null;

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            //반납한 연결객체==메모리상의 찾은 객체
			if (c == co.connection) {
                co.inUse = false;//반납처리
                break;
            }
        }

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            //10이상을 넘거나 
			//대기중인 상태의 연결객체가 있으면
			if ((i + 1) > _openConnections && !co.inUse)
                removeConnection(co.connection);
        }
    }

    public void freeConnection(Connection c, PreparedStatement p, ResultSet r) {
        try {
            if (r != null) r.close();
            if (p != null) p.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, Statement s, ResultSet r) {
        try {
            if (r != null) r.close();
            if (s != null) s.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, PreparedStatement p) {
        try {
            if (p != null) p.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, Statement s) {
        try {
            if (s != null) s.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /** Marks a flag in the ConnectionObject to indicate this connection is no longer in use */
    public synchronized void removeConnection(Connection c) {
        if (c == null)
            return;

        ConnectionObject co = null;
        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            if (c == co.connection) {
                try {
                    c.close();
                    connections.removeElementAt(i);
                    trace("Removed " + c.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }


    private Connection createConnection()
            throws SQLException {
        Connection con = null;

        try {
            if (_user == null)
                _user = "";
            if (_password == null)
                _password = "";

            Properties props = new Properties();
            props.put("user", _user);
            props.put("password", _password);

            con = DriverManager.getConnection(_url, props);
        } catch (Throwable t) {
            throw new SQLException(t.getMessage());
        }

        return con;
    }


    /** Closes all connections and clears out the connection pool */
    public void releaseFreeConnections() {
        trace("ConnectionPoolManager.releaseFreeConnections()");

        Connection c = null;
        ConnectionObject co = null;

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            if (!co.inUse)
                removeConnection(co.connection);
        }
    }


    /** Closes all connections and clears out the connection pool */
    public void finalize() {
        trace("ConnectionPoolManager.finalize()");

        Connection c = null;
        ConnectionObject co = null;

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            try {
                co.connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            co = null;
        }

        connections.removeAllElements();
    }


    private void trace(String s) {
        if (_traceOn)
            System.err.println(s);
    }

}

//inner class로 선언
class ConnectionObject {
	//생성된 연결객체
    public java.sql.Connection connection = null;
    public boolean inUse = false;//대여유무

    public ConnectionObject(Connection c, boolean useFlag) {
        connection = c;
        inUse = useFlag;
    }
}
