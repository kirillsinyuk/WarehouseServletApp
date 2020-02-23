package app.servlets.product;

import app.entities.Product;
import app.service.FactoryDao;
import app.util.ValidateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteProductSevlet", urlPatterns = "/product/delete")
public class DeleteProductSevlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/delete.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            Long id = ValidateUtil.getIdFromReq(req);
            if (id != null) {
                Product product = FactoryDao.getInstance().getProductDAO().getProductById(id);
                if (product != null) {
                    FactoryDao.getInstance().getProductDAO().deleteProduct(product);
                } else {
                    resp.sendError(400, "No entity to delete");
                    return;
                }
            } else {
                resp.sendError(400, "Bad id");
                return;
            }
            doGet(req, resp);
        }  else {
            resp.sendError(400, "No id");
        }
    }
}