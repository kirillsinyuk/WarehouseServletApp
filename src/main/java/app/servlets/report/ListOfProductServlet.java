package app.servlets.report;

import app.dao.impl.ProductDAOImpl;
import app.model.entities.Product;
import app.service.FactoryDao;
import app.service.converter.json.JsonProductConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ListProductServlet", urlPatterns = "/product/list")
public class ListOfProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonProductConverter converter = new JsonProductConverter();
        String name = converter.parseName(json);
        List<Product> products;
        if (name == null) {
            products = FactoryDao.getInstance(FactoryDao.DaoType.PRODUCT).getAll(Product.class);
        } else {
            products = ((ProductDAOImpl)FactoryDao.getInstance(FactoryDao.DaoType.PRODUCT)).getProductsByParam("name", name);
        }
        String outputJson = converter.convertProductCollectionToJson(products);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(outputJson);
    }
}
