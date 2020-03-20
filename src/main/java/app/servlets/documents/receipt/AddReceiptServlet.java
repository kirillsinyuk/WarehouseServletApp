package app.servlets.documents.receipt;

import app.model.entities.docs.Receipt;
import app.service.DaoFactory;
import app.service.converter.json.JsonReceiptConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "AddReceiptServlet", urlPatterns = "/receipt/add")
public class AddReceiptServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strProduct = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonReceiptConverter converter = new JsonReceiptConverter();
        Receipt receipt = converter.parseReceiptFromJson(strProduct);
        if (receipt == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
            return;
        }
        DaoFactory.getReceiptDAO().add(receipt);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
