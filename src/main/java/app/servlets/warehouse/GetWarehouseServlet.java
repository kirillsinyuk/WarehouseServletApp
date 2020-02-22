package app.servlets.warehouse;

import app.entities.Warehouse;
import app.service.FactoryDao;
import app.servlets.converter.JsonConverter;
import app.util.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetWarehouseServlet", urlPatterns = "/warehouse/get")
public class GetWarehouseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            Long id = ValidateUtil.getIdFromReq(req);
            if(id != null) {
                resp.setContentType("application/json;charset=UTF-8");
                ServletOutputStream out = resp.getOutputStream();

                Warehouse warehouse = FactoryDao.getInstance().getWarehouseDAO().getWarehousetById(id);
                JsonConverter converter = new JsonConverter();
                String output = converter.convertWarehouseToJson(warehouse);

                out.print(output);
            } else {
                resp.sendError(400);
            }
        } else {
            resp.setContentType("application/json;charset=UTF-8");
            ServletOutputStream out = resp.getOutputStream();

            List<Warehouse> warehouses = FactoryDao.getInstance().getWarehouseDAO().getAllWarehouses();

            JsonConverter converter = new JsonConverter();
            String output = converter.convertWarehouseCollectionToJson(warehouses);

            out.print(output);
        }
    }
}
