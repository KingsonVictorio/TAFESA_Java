/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Book;
import model.CartItem;
import model.Tbooks;
import utility.AdmitBookStoreDAO;
import utility.BookHelper;

/**
 * Processes requests to add selected items into the shopping cart. It updates the cart data structure with new products and their quantities.
 * @author kings
 */
public class AddCartDispatcher implements IDispatcher {

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(true);

        try {
            Map<String, CartItem> cart =
                    (Map<String, CartItem>) session.getAttribute("cart");

            String[] selectedBooks = request.getParameterValues("add");

            if (selectedBooks == null || selectedBooks.length == 0) {
                return "/jsp/titles.jsp";
            }

            if (cart == null) {
                cart = new HashMap();
            }

            for (String isbn : selectedBooks) {

                int quantity = Integer.parseInt(request.getParameter(isbn));

                Tbooks book = BookHelper.getBookFromList(isbn, session);

                if (book == null) continue;

                if (cart.containsKey(isbn)) {
                    cart.get(isbn).setQuantity(quantity);
                } else {
                    CartItem item = new CartItem(book);
                    item.setQuantity(quantity);
                    cart.put(isbn, item);
                }
            }

            session.setAttribute("cart", cart);

            return "/jsp/titles.jsp";

        } catch (Exception ex) {
            request.setAttribute("result", ex.getMessage());
            return "/jsp/error.jsp";
        }
    }
}
