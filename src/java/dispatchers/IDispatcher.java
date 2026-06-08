package dispatchers;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author kings
 */
public interface IDispatcher {

    public String execute(HttpServletRequest request);
}
