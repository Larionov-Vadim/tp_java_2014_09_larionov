package frontend;

import main.AccountService;
import main.UserProfile;
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
public class SignInServlet extends HttpServlet {
    private AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Формируем страницу
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("error", "Enter your login and password");
        response.getWriter().println(PageGenerator.getPage("login.html", pageVariables));
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Map<String,Object> pageVariables = new HashMap<>();
        // Считываем данные из формы
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String sessionId = request.getSession().getId();

        if (!accountService.signIn(login, password, sessionId)) {
            pageVariables.put("error", "Incorrect Login or Password");
            response.getWriter().println(PageGenerator.getPage("login.html", pageVariables));
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/main");
        }
        response.setContentType("text/html; charset=utf-8");
    }

}
