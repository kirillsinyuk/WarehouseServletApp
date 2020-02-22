package app.servlets.product;

import app.entities.Product;
import app.service.FactoryDao;
import app.util.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateProductSevlet", urlPatterns = "/product/update")
public class UpdateProductSevlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Product product = ValidateUtil.validateAndCreateProduct(req);
        if (product == null){
            resp.sendError(400);
        } else {
            FactoryDao.getInstance().getProductDAO().updateProduct(product);
            resp.setStatus(200);
        }
        doGet(req, resp);
    }
}
