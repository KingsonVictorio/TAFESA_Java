package controller;

import dispatchers.AddCartDispatcher;
import dispatchers.CheckoutDispatcher;
import dispatchers.ContinueDispatcher;
import dispatchers.IDispatcher;
import dispatchers.NullDispatcher;
import dispatchers.UpdateCartDispatcher;
import dispatchers.ViewCartDispatcher;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import model.Book;
import model.CartItem;
import utility.AdmitBookStoreDAO;

/**
 * FrontController class to handle HTTP requests and responses.
 */
public class FrontController extends HttpServlet {

    private final HashMap actions = new HashMap();

    /**
     * Initialize global variables.
     *
     * @param config ServletConfig object
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        actions.put("", new NullDispatcher());
        actions.put(null, new NullDispatcher());
        actions.put("add_to_cart", new AddCartDispatcher());
        actions.put("checkout", new CheckoutDispatcher());
        actions.put("continue", new ContinueDispatcher());
        actions.put("update_cart", new UpdateCartDispatcher());
        actions.put("view_cart", new ViewCartDispatcher());
        // Additional initialization code can be added here
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String next_page = "";

        // GET THE action parameter to determine what action is required
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        try {
            IDispatcher dispatcher = (IDispatcher) actions.get(action);
            if (dispatcher == null) {
                throw new ServletException("Unknown action: " + action);
            }
            next_page = dispatcher.execute(request);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        } finally {
            this.dispatch(request, response, next_page);
        }
    }

    /**
     * Process the HTTP GET request.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("doGet()");
        // Forward GET requests to doPost method
        processRequest(request, response);
    }

    /**
     * Process the HTTP POST request.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Retrieve a book from the list of books stored in the session.
     *
     * @param isbn ISBN of the book
     * @param session HttpSession object
     * @return Book object
     */
    private Book getBookFromList(String isbn, HttpSession session) {
        List<Book> list = (List<Book>) session.getAttribute("books");
        Book aBook = null;
        for (Book book : list) {
            if (isbn.equals(book.getIsbn())) {
                aBook = book;
                break;
            }
        }
        return aBook;
    }

    /**
     * Forward the request to the specified page.
     *
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @param page Page to forward to
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    /**
     * Get Servlet information.
     *
     * @return Servlet information
     */
    public String getServletInfo() {
        return "controller.FrontController Information";
    }
}
