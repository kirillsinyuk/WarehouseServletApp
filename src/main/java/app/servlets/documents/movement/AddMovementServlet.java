package app.servlets.documents.movement;

import app.model.entities.docs.Movement;
import app.service.DaoFactory;
import app.service.converter.json.JsonMovementConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "AddMovementServlet", urlPatterns = "/movement/add")
public class AddMovementServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strMovement = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonMovementConverter converter = new JsonMovementConverter();
        Movement move = converter.parseMovementFromJson(strMovement);
        if (move == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
            return;
        }
        DaoFactory.getMovementDAO().add(move);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
