/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatchers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *Handles the checkout process by preparing the cart for order confirmation and final purchase processing.
 * @author kings
 */
public class CheckoutDispatcher implements IDispatcher{
    
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        return "/jsp/checkout.jsp";
    }
}
