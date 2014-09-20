package frontend;

import main.AccountService;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vadim on 20.09.14.
 */
public class MainPageServlet extends HttpServlet{
    private AccountService accountService;

    public MainPageServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем login активного пользователя
        String sessionId = request.getSession().getId();
        String login = accountService.getLogin(sessionId);

        // Формируем страницу
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("login", login);
        pageVariables.put("countUsers", accountService.getCountUsers());    // Статистика зарегистрированных user-ов
        response.getWriter().println(PageGenerator.getPage("main.html", pageVariables));
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
