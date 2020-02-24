package app.servlets.warehouse;

import app.model.entities.Warehouse;
import app.service.FactoryDao;
import app.service.converter.JsonConverter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "UpdateWarehouseSevlet", urlPatterns = "/warehouse/update")
public class UpdateWarehouseSevlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/update.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String strProduct = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonConverter converter = new JsonConverter();
        Warehouse warehouse = converter.parseWarehouseFromJson(strProduct, false);

        if (warehouse == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
        } else {
            FactoryDao.getInstance().getWarehouseDAO().updateWarehouse(warehouse);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
