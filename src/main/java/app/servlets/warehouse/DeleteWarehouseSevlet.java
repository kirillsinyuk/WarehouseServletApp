package app.servlets.warehouse;

import app.service.FactoryDao;
import app.service.converter.JsonConverter;

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
        JsonConverter converter = new JsonConverter();
        Long id = converter.parseId(json);
        if (id == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
        }
        FactoryDao.getInstance().getWarehouseDAO().deleteWarehouse(id);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
