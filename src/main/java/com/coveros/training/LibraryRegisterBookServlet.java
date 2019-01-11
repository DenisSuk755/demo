package com.coveros.training;

import com.coveros.training.domainobjects.LibraryActionResults;
import com.coveros.training.persistence.LibraryUtils;
import com.coveros.training.persistence.PersistenceLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LibraryRegisterBookServlet", urlPatterns = {"/registerbook"}, loadOnStartup = 1)
public class LibraryRegisterBookServlet extends HttpServlet {

  private static final Logger logger = LogManager.getLogger();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    final String book = request.getParameter("book");
    request.setAttribute("book", book);

    final PersistenceLayer persistenceLayer = new PersistenceLayer();
    LibraryUtils libraryUtils = new LibraryUtils(persistenceLayer);

    final LibraryActionResults libraryActionResults = libraryUtils.registerBook(book);

    request.setAttribute("result", libraryActionResults.toString());
    forwardToResult(request, response, logger);
  }

  /**
   * Wrapping a static method call for testing.
   */
  void forwardToResult(HttpServletRequest request, HttpServletResponse response, Logger logger) {
    ServletUtils.forwardToResult(request, response, logger);
  }

}
