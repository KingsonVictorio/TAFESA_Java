/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.CartItem;

/**
 *Displays the current contents of the user’s shopping cart, including items, quantities, and subtotal information.
 * @author kings
 */
public class ViewCartDispatcher implements IDispatcher {

    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        // Redirect to the cart page
        String nextPage = "/jsp/cart.jsp";
        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            nextPage = "/jsp/titles.jsp";
        }
        return nextPage;
    }

}
