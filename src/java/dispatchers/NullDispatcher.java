/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Book;
import model.CartItem;
import utility.AdmitBookStoreDAO;

/**
 * Handles cases where no action is specified. It acts as a fallback to prevent
 * errors when an empty request parameter is received.
 *
 * @author kings
 */
public class NullDispatcher implements IDispatcher {

    /**
     *
     * @param request
     * @return
     */
    @Override
    public String execute(HttpServletRequest request) {

        AdmitBookStoreDAO dao = new AdmitBookStoreDAO();
        HttpSession session = request.getSession(true);

        try {
            return "/jsp/titles.jsp";

        } catch (Exception ex) {
            request.setAttribute("result", ex.getMessage());
            return "/jsp/error.jsp";
        }
    }
}
