package app.servlets.product;

import app.entities.Product;
import app.service.FactoryDao;
import app.service.converter.*;
import app.util.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetProductServlet", urlPatterns = "/product/get")
public class GetProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            Long id = ValidateUtil.getIdFromReq(req);
            if(id != null) {
                resp.setContentType("application/json;charset=UTF-8");
                ServletOutputStream out = resp.getOutputStream();

                Product product = FactoryDao.getInstance().getProductDAO().getProductById(id);
                JsonConverter converter = new JsonConverter();
                String output = converter.convertProductToJson(product);

                out.print(output);
            } else {
                resp.sendError(400);
            }
        } else {
            resp.setContentType("application/json;charset=UTF-8");
            ServletOutputStream out = resp.getOutputStream();

            List<Product> products = FactoryDao.getInstance().getProductDAO().getAllProducts();

            JsonConverter converter = new JsonConverter();
            String output = converter.convertProductCollectionToJson(products);

            out.print(output);
        }
    }
}
