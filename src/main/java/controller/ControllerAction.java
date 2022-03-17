package controller;

import java.io.*;//FileInputStream
import java.util.*;//Map,Properties
import javax.servlet.*;
import javax.servlet.http.*;

//추가->다른 패키지의 클래스나 인터페이스를 참조
import action.CommandAction;

public class ControllerAction extends HttpServlet {

	//명령어와 명령어 처리클래스를 쌍으로 저장
	private Map<String, Object> commandMap = new HashMap<>();

	//서블릿을 실행시 서블릿의 초기화 작업->생성자
	public void init(ServletConfig config) throws ServletException {

		//경로에 맞는 CommandPro.properties파일을 불러옴
		String props = config.getInitParameter("propertyConfig");
		String path = this.getClass().getResource("/").getPath();	// 상대경로를 사용하기 위해 실제 경로를 저장하는 문자열
		path = path.replaceAll("%20", " ");
		path = path.replace("/classes/", "");
		System.out.println("불러온경로 : " + path + props);

		//명령어와 처리클래스의 매핑정보를 저장할
		//Properties객체 생성
		Properties pr = new Properties();
		FileInputStream f = null;//파일불러올때 

		try {
			//CommandPro.properties파일의 내용을 읽어옴
			f = new FileInputStream(path + props);

			//파일의 정보를 Properties에 저장
			pr.load(f);

		} catch (IOException e) {
			throw new ServletException(e);
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException ex) {
				}
		}

		//객체를 하나씩 꺼내서 그 객체명으로 Properties
		//객체에 저장된 객체를 접근
		Iterator keyiter = pr.keySet().iterator();

		while (keyiter.hasNext()) {
			//요청한 명령어를 구하기위해
			String command = (String) keyiter.next();
			System.out.println("command=" + command);
			//요청한 명령어(키)에 해당하는 클래스명을 구함
			String className = pr.getProperty(command);
			System.out.println("className=" + className);

			try {
				//그 클래스의 객체를 얻어오기위해 메모리에 로드
				Class commandClass = Class.forName(className);
				System.out.println("commandClass=" + commandClass);
				Object commandInstance = commandClass.newInstance();
				System.out.println("commandInstance=" + commandInstance);

				//Map객체 commandMap에 저장
				commandMap.put(command, commandInstance);
				System.out.println("commandMap=" + commandMap);

			} catch (ClassNotFoundException e) {
				throw new ServletException(e);
			} catch (InstantiationException e) {
				throw new ServletException(e);
			} catch (IllegalAccessException e) {
				throw new ServletException(e);
			}
		} //while
	}

	//get방식의 서비스 메소드
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestProcess(request, response);
	}

	//post방식의 서비스 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestProcess(request, response);
	}

	//시용자의 요청을 분석해서 해당 작업을 처리
	private void requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		CommandAction commandAction = null;
		
		try {
			String command = request.getRequestURI();
			System.out.println("request.getRequestURI() : " + request.getRequestURI());
			System.out.println("request.getContextPath() : " + request.getContextPath());
			
			if (command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
				System.out.println("command : " + command);
			}
			commandAction = (CommandAction) commandMap.get(command);
			System.out.println("commandAction : " + commandAction);
			view = commandAction.requestProcess(request, response);
			System.out.println("view : " + view);
		} catch (Throwable throwable) {
			throw new ServletException(throwable);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
