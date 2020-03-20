package app.servlets.product;

import app.model.entities.Product;
import app.service.DaoFactory;
import app.service.converter.json.JsonProductConverter;
import app.util.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "DeleteProductSevlet", urlPatterns = "/product/delete")
public class DeleteProductSevlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonProductConverter converter = new JsonProductConverter();
        Long id = converter.parseId(json);
        if (!ValidateUtil.isProductIdValid(id)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
            return;
        }
        DaoFactory.getProductDAO().delete(Product.class, id);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
