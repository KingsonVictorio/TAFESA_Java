/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *Allows the user to continue browsing or shopping after performing an action such as adding items to the cart or viewing the cart.
 * @author kings
 */
public class ContinueDispatcher implements IDispatcher{
    
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        return "/jsp/titles.jsp";
    }
}
