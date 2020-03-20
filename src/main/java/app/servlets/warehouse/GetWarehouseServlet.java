package app.servlets.warehouse;

import app.model.entities.Warehouse;
import app.service.DaoFactory;
import app.service.converter.json.JsonWarehouseConverter;
import app.util.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "GetWarehouseServlet", urlPatterns = "/warehouse/get")
public class GetWarehouseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonWarehouseConverter converter = new JsonWarehouseConverter();

        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Long id = converter.parseId(json);
        if (!ValidateUtil.isWarehiuseIdValid(id)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
            return;
        }
        Warehouse warehouse = DaoFactory.getWarehouseDAO().getById(Warehouse.class, id);
        resp.setContentType("application/json;charset=UTF-8");
        String output = converter.convertWarehouseToJson(warehouse);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(output);
    }
}
