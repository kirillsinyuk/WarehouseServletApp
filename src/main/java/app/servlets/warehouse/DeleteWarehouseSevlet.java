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

@WebServlet(name = "DeleteWarehouseSevlet", urlPatterns = "/warehouse/delete")
public class DeleteWarehouseSevlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonWarehouseConverter converter = new JsonWarehouseConverter();
        Long id = converter.parseId(json);
        if (!ValidateUtil.isWarehiuseIdValid(id)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
            return;
        }
        DaoFactory.getWarehouseDAO().delete(Warehouse.class, id);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
