package lab1.soa.servlets;

import lab1.soa.service.HumanBeingService;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/human_being", "/human_being/new", "/human_being/delete",
        "/human_being/edit", "/human_being/insert", "/human_being/update", "/human_being/list"})

public class HumanBeingServlet extends HttpServlet {
    private final HumanBeingService humanBeingService = new HumanBeingService();

    public void init() {
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/human_being/new":
                    humanBeingService.showNewForm(request, response);
                    break;
                case "/human_being/delete":
                    humanBeingService.deleteUser(request, response);
                    break;
                case "/human_being/edit":
                    humanBeingService.showEditForm(request, response);
                    break;
                default:
                    humanBeingService.listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();
        switch (action) {
            case "/human_being/insert":
                humanBeingService.insertUser(request, response);
                break;
            case "/human_being/update":
                humanBeingService.updateUser(request, response);
                break;
            default:
                humanBeingService.listUser(request, response);
                break;
        }
    }
}
