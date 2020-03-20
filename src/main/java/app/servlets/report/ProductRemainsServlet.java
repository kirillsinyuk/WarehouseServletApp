package app.servlets.report;

import app.dao.impl.ProductDAOImpl;
import app.model.entities.Product;
import app.model.entities.dto.RemainsDto;
import app.service.FactoryDao;
import app.service.converter.json.JsonProductConverter;
import app.service.converter.dto.ProductToDtoConverter;
import app.util.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@WebServlet(name = "RemainsProductServlet", urlPatterns = "/product/remains")
public class ProductRemainsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JsonProductConverter converter = new JsonProductConverter();
        Long id = converter.parseId(json);
        List<Product> products;
        if (!ValidateUtil.isWarehiuseIdValid(id)) {
            products = FactoryDao.getInstance(FactoryDao.DaoType.PRODUCT).getAll(Product.class);
        } else {
            products = ((ProductDAOImpl)FactoryDao.getInstance(FactoryDao.DaoType.PRODUCT)).getProductsByWarehouse(id);
        }
        Map<RemainsDto, Long> result = products.stream().map(ProductToDtoConverter::toRemainsDto).collect(Collectors.groupingBy(identity(), Collectors.counting()));
        String outputJson = converter.convertRemainsMapToDto(result);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(outputJson);
    }
}
