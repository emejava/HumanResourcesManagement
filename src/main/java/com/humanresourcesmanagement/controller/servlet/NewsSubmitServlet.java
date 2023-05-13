package com.humanresourcesmanagement.controller.servlet;


import com.humanresourcesmanagement.controller.NewsController;
import com.humanresourcesmanagement.model.entity.Attachment;
import com.humanresourcesmanagement.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import java.io.IOException;

@WebServlet(urlPatterns = "/application/newsSubmit.do")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
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
        mainPicture.write(mainPicturePath);
        Attachment mainAttachment = new Attachment("Attachment2", doer, mainPicturePath);
        //            -----------------File2
        Part secondPicture = req.getPart("PersonnelPicture");
        String secondPictureName = secondPicture.getSubmittedFileName();
        String secondPicturePath = "c:\\root\\" + secondPictureName;
        secondPicture.write(secondPicturePath);
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
