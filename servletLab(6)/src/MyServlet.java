import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by v.apanovich on 25.02.2016.
 */

@WebServlet("/calculate")
public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String xValue = req.getParameter("xValue");
        String accuracy = req.getParameter("accuracy");
        String function = req.getParameter("function");
        String measure = req.getParameter("measure");

        String responseTest = CalculationsHelper.Calculate(xValue, accuracy, function, measure);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(responseTest);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
