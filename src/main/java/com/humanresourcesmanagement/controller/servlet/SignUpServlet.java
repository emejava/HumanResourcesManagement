package com.humanresourcesmanagement.controller.servlet;

import com.humanresourcesmanagement.controller.*;
import com.humanresourcesmanagement.model.entity.*;
import com.humanresourcesmanagement.model.entity.enums.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/application/signUp.do")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class SignUpServlet extends HttpServlet {

    //      ---------INSERT-DATA-------------------------------------doPOST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //    doPOST---------PERSON-DATA---------
        User doer = (User) req.getSession().getAttribute("user");
        String firstName = req.getParameter("FirstName");
        String lastName = req.getParameter("LastName");
        String nationalCode = req.getParameter("NationalCode");
        String birthCertificateCode = req.getParameter("BirthCertificateCode");
        LocalDate birthday = LocalDate.parse(req.getParameter("Birthday"));
        Integer age = Integer.valueOf(req.getParameter("Age"));
        String father = req.getParameter("FatherName");
        String state = req.getParameter("State");
        String city = req.getParameter("City");
        String postCode = req.getParameter("PostCode");
        MaritalStatus maritalStatus = MaritalStatus.valueOf(req.getParameter("maritalStatus"));
        Gender gender = Gender.valueOf(req.getParameter("gender"));
        String address = req.getParameter("Address");
        String landLineNo = req.getParameter("LandLineNo");
        String phoneNo = req.getParameter("PhoneNo");
        String email = req.getParameter("Email");
        Short children = Short.valueOf(req.getParameter("ChildrenCount"));
        //      doPOST---------SAVE-FILES---------
        //            -----------------File1
        Part nationalCardPicture = req.getPart("NationalCardPicture");
        String nationalCardPicName = nationalCardPicture.getSubmittedFileName();
        String nationalCardPicPath = "c:\\root\\" + nationalCardPicName;
        nationalCardPicture.write(nationalCardPicPath);

        Attachment nationalCardPic = new Attachment("nationalCardPic", doer, nationalCardPicPath);

        //            -----------------File2
        Part personnelPicture = req.getPart("PersonnelPicture");
        String personnelPicName = nationalCardPicture.getSubmittedFileName();
        String personnelPicPath = "c:\\root\\" + personnelPicName;
        personnelPicture.write(personnelPicPath);
        Attachment personnelPic = new Attachment("personnelPic", doer, personnelPicPath);

        //    doPOST---------USER-DATA-----------
        String username = req.getParameter("Username");
        String password = req.getParameter("Password");
        AccessLevel accessLevel = new AccessLevel();
        accessLevel.setTitle(req.getParameter("AccessLevel"));
        Role role = new Role();
        role.setRoleName(req.getParameter("RoleName"));


        //  -----VALIDATE-AND-SAVE-DATA-WITH-CONTROLLER-----
        Person person = (Person) PersonController.getPersonController().save(
                firstName,
                lastName,
                nationalCode,
                birthCertificateCode,
                birthday,
                gender,
                age,
                father,
                state,
                city,
                address,
                postCode,
                maritalStatus,
                phoneNo,
                landLineNo,
                children,
                email,
                nationalCardPic,
                personnelPic,
                doer
        ).get(true);
        UserController.getUserController().save(
                username,
                password,
                person,
                role,
                accessLevel,
                doer
        );
        //  doPOST------RESPONSE---------------------
        resp.sendRedirect("/application/signUp.jsp");
    }
}
