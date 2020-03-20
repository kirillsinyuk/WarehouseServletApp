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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ListOfReceiptsServlet", urlPatterns = "/receipt/list")
public class ListOfReceiptsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonReceiptConverter converter = new JsonReceiptConverter();
        Long id = converter.parseId(json);
        List<Receipt> receipts;
        if (id == null) {
            receipts = DaoFactory.getReceiptDAO().getAll(Receipt.class);
        } else {
            receipts = Collections.singletonList(DaoFactory.getReceiptDAO().getById(Receipt.class,id));
        }
        String outputJson = converter.convertReceiptCollectionToJson(receipts);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(outputJson);
    }
}
