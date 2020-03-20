package app.servlets.warehouse;

import app.model.entities.Warehouse;
import app.service.DaoFactory;
import app.service.converter.json.JsonWarehouseConverter;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strProduct = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonWarehouseConverter converter = new JsonWarehouseConverter();
        Warehouse warehouse = converter.updateWarehouseFromJson(strProduct, false);

        if (warehouse == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
            return;
        } else {
            DaoFactory.getWarehouseDAO().update(warehouse);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
