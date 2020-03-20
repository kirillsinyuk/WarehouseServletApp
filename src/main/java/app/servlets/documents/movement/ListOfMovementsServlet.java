package app.servlets.documents.movement;

import app.model.entities.docs.Movement;
import app.service.FactoryDao;
import app.service.converter.json.JsonMovementConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ListOfMovementsServlet", urlPatterns = "/receipt/list")
public class ListOfMovementsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonMovementConverter converter = new JsonMovementConverter();
        Long id = converter.parseId(json);
        List<Movement> movements;
        if (id == null) {
            movements = FactoryDao.getInstance(FactoryDao.DaoType.MOVEMENT).getAll(Movement.class);
        } else {
            movements = Collections.singletonList((Movement)FactoryDao.getInstance(FactoryDao.DaoType.MOVEMENT).getById(Movement.class,id));
        }
        String outputJson = converter.convertMovementCollectionToJson(movements);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(outputJson);
    }
}
