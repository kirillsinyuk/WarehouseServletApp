package app.servlets.product;

import app.model.entities.Product;
import app.service.DaoFactory;
import app.service.converter.json.JsonProductConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "AddProductServlet", urlPatterns = "/product/add")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strProduct = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonProductConverter converter = new JsonProductConverter();
        Product product = converter.parseProductFromJson(strProduct, true);
        if (product == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
            return;
        }
        DaoFactory.getProductDAO().add(product);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
