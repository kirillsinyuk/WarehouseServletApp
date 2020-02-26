package app.servlets.documents.movement;

import app.model.entities.Product;
import app.service.FactoryDao;
import app.service.converter.JsonConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ListOfMovementsServlet", urlPatterns = "/receipt/list")
public class ListOfMovementsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonConverter converter = new JsonConverter();
        String name = converter.parseName(json);
        List<Product> products;
        if (name == null) {
            products = FactoryDao.getInstance().getProductDAO().getAllProducts();
        } else {
            products = FactoryDao.getInstance().getProductDAO().getProductsByParam("name", name);
        }
        String outputJson = converter.convertProductCollectionToJson(products);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(outputJson);
    }
}
