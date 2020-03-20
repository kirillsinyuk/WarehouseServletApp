package app.servlets.documents.sale;

import app.model.entities.docs.Sale;
import app.service.DaoFactory;
import app.service.converter.json.JsonSaleConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ListOfSalesServlet", urlPatterns = "/sale/list")
public class ListOfSalesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonSaleConverter converter = new JsonSaleConverter();
        Long id = converter.parseId(json);
        List<Sale> sales;
        if (id == null) {
            sales = DaoFactory.getSaleDAO().getAll(Sale.class);
        } else {
            sales = Collections.singletonList(DaoFactory.getSaleDAO().getById(Sale.class,id));
        }
        String outputJson = converter.convertReceiptCollectionToJson(sales);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(outputJson);
    }
}
