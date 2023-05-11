package com.humanresourcesmanagement.controller.servlet;


import com.humanresourcesmanagement.controller.NewsController;
import com.humanresourcesmanagement.model.entity.Attachment;
import com.humanresourcesmanagement.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;

@WebServlet(urlPatterns = "/application/newsSubmit.do")
public class NewsSubmitServlet extends HttpServlet {

    //      ---------INSERT-DATA-------------------------------------doPOST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User doer = (User) req.getSession().getAttribute("user");
        String subject = req.getParameter("Subject");
        String title = req.getParameter("Title");
        String newsReport = req.getParameter("NewsReport");

        //      doPOST---------SAVE-FILES---------
        //            -----------------File1
        Part mainPicture = req.getPart("Attachment1");
        String mainPictureName = mainPicture.getSubmittedFileName();
        String mainPicturePath = "c:\\root\\" + mainPictureName;
        for (Part part : req.getParts()) {
            part.write(mainPicturePath);
        }
        Attachment mainAttachment = new Attachment("Attachment2", doer, mainPicturePath);
        //            -----------------File2
        Part secondPicture = req.getPart("PersonnelPicture");
        String secondPictureName = secondPicture.getSubmittedFileName();
        String secondPicturePath = "c:\\root\\" + secondPictureName;
        for (Part part : req.getParts()) {
            part.write(secondPicturePath);
        }
        Attachment secondAttachment = new Attachment("personnelPic", doer, secondPicturePath);

        //  -----VALIDATE-AND-SAVE-DATA-WITH-CONTROLLER-----
        NewsController.getNewsController().save(
                subject,
                title,
                newsReport,
                mainAttachment,
                secondAttachment,
                doer
        );

        //  doPOST------RESPONSE---------------------
        resp.sendRedirect("/application/News.jsp");
    }
}
