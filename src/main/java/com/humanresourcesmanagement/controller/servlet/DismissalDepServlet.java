package com.humanresourcesmanagement.controller.servlet;
import com.humanresourcesmanagement.controller.DismissalDepController;
import com.humanresourcesmanagement.model.entity.Person;
import com.humanresourcesmanagement.model.entity.enums.Status;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "DismissalDep", urlPatterns = "/dismissalDep.do")
public class DismissalDepServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long personId = Long.valueOf(req.getParameter("personId"));
        Long personnelCode = Long.valueOf(req.getParameter("personnelCode"));
        String reasonOfDismissal = String.valueOf(req.getParameter("reasonOfDismissal"));
        Timestamp timestamp = Timestamp.valueOf(req.getParameter("timestamp"));
        Status status = Status.valueOf(req.getParameter("status"));
        Long checkOut = Long.valueOf(req.getParameter("checkOut"));
        Person person = new Person();

        DismissalDepController.getDismissalDepController().save(personId,personnelCode,
                reasonOfDismissal,timestamp,status,checkOut);
        resp.sendRedirect("/dismissalDep.do");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute("dismissalDep",DismissalDepController.getDismissalDepController().findAll());
        resp.sendRedirect("dismissalDep.jsp");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long personId = Long.valueOf(req.getParameter("personId"));
        Long personnelCode = Long.valueOf(req.getParameter("personnelCode"));
        String reasonOfDismissal = String.valueOf(req.getParameter("reasonOfDismissal"));
        Timestamp timestamp = Timestamp.valueOf(req.getParameter("timestamp"));
        Status status = Status.valueOf(req.getParameter("status"));
        Long checkOut = Long.valueOf(req.getParameter("checkOut"));
        Person person = new Person();
        DismissalDepController.getDismissalDepController().save(personId,personnelCode,
                reasonOfDismissal,timestamp,status,checkOut);
        resp.sendRedirect("/dismissalDep.do");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long personnelCode = Long.valueOf(req.getParameter("personnelCode"));
        DismissalDepController.getDismissalDepController().remove(personnelCode);

        resp.sendRedirect("/employment.do");
    }
}
