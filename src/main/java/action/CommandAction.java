package action;

import javax.servlet.http.*;

public interface CommandAction {
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
