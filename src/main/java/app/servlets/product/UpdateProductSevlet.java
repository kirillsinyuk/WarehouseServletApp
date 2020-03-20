package app.servlets.product;

import app.dao.interfaces.CrudDAO;
import app.model.entities.Product;
import app.service.FactoryDao;
import app.service.converter.json.JsonProductConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "UpdateProductSevlet", urlPatterns = "/product/update")
public class UpdateProductSevlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strProduct = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonProductConverter converter = new JsonProductConverter();
        Product product = converter.updateProductFromJson(strProduct, false);

        if (product == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
            return;
        } else {
            ((CrudDAO)FactoryDao.getInstance(FactoryDao.DaoType.PRODUCT)).update(product);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
