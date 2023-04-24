package com.humanresourcesmanagement.controller.servlet;

import com.humanresourcesmanagement.model.entity.File;
import com.humanresourcesmanagement.model.entity.enums.Gender;
import com.humanresourcesmanagement.model.entity.enums.MaritalStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;



@WebServlet(urlPatterns = "/signUp.do")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class SignUp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String nationalCode = req.getParameter("nationalCode");
        String birthCertificateCode = req.getParameter("birthCertificateCode");
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
        Gender gender = Gender.valueOf(req.getParameter("gender"));
        Integer age = Integer.valueOf(req.getParameter("age"));
        String father = req.getParameter("father");
        String state = req.getParameter("state");
        String city = req.getParameter("city");
        String address = req.getParameter("address");
        String postCode = req.getParameter("postCode");
        MaritalStatus maritalStatus = MaritalStatus.valueOf(req.getParameter("maritalStatus"));
        String phoneNo = req.getParameter("phoneNo");
        String landLineNo = req.getParameter("landLineNo");
        Short children = Short.valueOf(req.getParameter("children"));
        String email = req.getParameter("email");
        Part nationalCardPicture = req.getPart("nationalCardPicture");
        String nationalCardPictureName = nationalCardPicture.getSubmittedFileName();
        for (Part part : req.getParts()) {
            String filePath = "c:\\root\\"+ nationalCardPictureName;

        }
        Part personnelPicture = req.getPart("personnelPicture");
    }
}
