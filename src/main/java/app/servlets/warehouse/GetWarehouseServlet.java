package app.servlets.warehouse;

import app.model.entities.Warehouse;
import app.service.FactoryDao;
import app.service.converter.*;
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
        JsonConverter converter = new JsonConverter();

        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Long id = converter.parseId(json);
        if (ValidateUtil.isProductIdValid(id)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
        }
        Warehouse warehouse = FactoryDao.getInstance().getWarehouseDAO().getWarehouseById(id);
        resp.setContentType("application/json;charset=UTF-8");
        String output = converter.convertWarehouseToJson(warehouse);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(output);
    }
}
