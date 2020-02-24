package app.servlets.product;

import app.service.FactoryDao;
import app.service.converter.JsonConverter;
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
        JsonConverter converter = new JsonConverter();
        Long id = converter.parseId(json);
        if (!ValidateUtil.isProductIdValid(id)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
            return;
        }
        FactoryDao.getInstance().getProductDAO().deleteProduct(id);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
