/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.CartItem;

/**
 *Manages updates to existing cart items, such as changing quantities or removing items from the shopping cart.
 * @author kings
 */
public class UpdateCartDispatcher implements IDispatcher {

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        Map<String, CartItem> cart = null;
        CartItem item = null;
        String isbn = null;
        String nextPage = "/jsp/cart.jsp";
        cart = (Map<String, CartItem>) session.getAttribute("cart");
        String[] booksToRemove = request.getParameterValues("remove");
        if (booksToRemove != null) {
            for (String bookToRemove : booksToRemove) {
                cart.remove(bookToRemove);
            }
        }
        Set<Map.Entry<String, CartItem>> entries = cart.entrySet();
        Iterator<Map.Entry<String, CartItem>> iter = entries.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, CartItem> entry = iter.next();
            isbn = entry.getKey();
            item = entry.getValue();
            int quantity = Integer.parseInt(request.getParameter(isbn));
            item.updateQuantity(quantity);
        }
        return nextPage;
    }
}
