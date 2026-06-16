/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.List;
import javax.servlet.http.HttpSession;
import model.Book;
import model.Tbooks;

/**
 * Utility class that provides helper methods for working with Book objects
 * stored in the user's session.
 *
 * This class is used to search and retrieve specific Book instances from a
 * session-based list using the book's ISBN.
 *
 * @author kings
 */
public class BookHelper {

    /**
     *Retrieves a Book object from the session-based book list using its ISBN.
     * 
     * This method accesses the "books" attribute stored in the HttpSession,
     * iterates through the list, and returns the Book that matches the given ISBN.
     * If the list is null or no matching book is found, it returns null.
     * @param isbn
     * @param session
     * @return
     */
    public static Tbooks getBookFromList(String isbn, HttpSession session) {

        List<Tbooks> list = (List<Tbooks>) session.getAttribute("books");

        if (list == null) {
            return null;
        }

        for (Tbooks book : list) {
            if (isbn.equals(book.getIsbn())) {
                return book;
            }
        }
        return null;
    }
}
