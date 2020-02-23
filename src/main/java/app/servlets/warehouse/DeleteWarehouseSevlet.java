package app.servlets.warehouse;

import app.entities.Warehouse;
import app.service.FactoryDao;
import app.util.ValidateUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteWarehouseSevlet", urlPatterns = "/warehouse/delete")
public class DeleteWarehouseSevlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/delete.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            Long id = ValidateUtil.getIdFromReq(req);
            if (id != null) {
                FactoryDao.getInstance().getWarehouseDAO().deleteWarehouse(id);
            } else {
                resp.sendError(400, "Bad id");
                return;
            }
            doGet(req, resp);
        }  else {
            resp.sendError(400, "No id");
        }
    }
}
