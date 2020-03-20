package app.servlets.warehouse;

import app.model.entities.Warehouse;
import app.service.FactoryDao;
import app.service.converter.json.JsonWarehouseConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "AddWarehouseServlet", urlPatterns = "/warehouse/add")
public class AddWarehouseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String strWarehouse = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonWarehouseConverter converter = new JsonWarehouseConverter();
        Warehouse warehouse = converter.parseWarehouseFromJson(strWarehouse, true);
        if (warehouse == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
            return;
        }
        FactoryDao.getInstance(FactoryDao.DaoType.WAREHOUSE).add(warehouse);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
