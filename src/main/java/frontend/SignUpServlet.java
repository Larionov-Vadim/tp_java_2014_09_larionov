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
public class SignUpServlet extends HttpServlet {
    private AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Формируем страницу
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("error", "The Registration Form");
        response.getWriter().println(PageGenerator.getPage("sign_up.html", pageVariables));
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Чтение данных из формы
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String sessionId = request.getSession().getId();

        Map<String, Object> pageVariables = new HashMap<>();

        if (accountService.singUp(login, password, email)) {
            accountService.signIn(login, password, sessionId);
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/main");
        }
        else {
            if (login.length() == 0 || password.length() == 0 || email.length() == 0)
                pageVariables.put("error", "There are empty fields");
            else
                pageVariables.put("error", "This login is exist");
            response.getWriter().println(PageGenerator.getPage("sign_up.html", pageVariables));
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
