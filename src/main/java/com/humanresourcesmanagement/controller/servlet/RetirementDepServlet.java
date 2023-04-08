package com.humanresourcesmanagement.controller.servlet;

import com.humanresourcesmanagement.controller.RetirementController;
import com.humanresourcesmanagement.model.entity.Duty;
import com.humanresourcesmanagement.model.entity.Person;
import com.humanresourcesmanagement.model.entity.Unit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;



@WebServlet(name = "RetirementServlet", urlPatterns = "/retirementDep.do")

public class RetirementDepServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long personId = Long.valueOf(req.getParameter("personId"));
        Boolean retirementStatus =Boolean.valueOf(req.getParameter("retirementStatus")) ;
        Long unitId = Long.valueOf(req.getParameter("UnitId"));
        Long dutyId = Long.valueOf(req.getParameter("DutyId"));
        try {
            Person person = new Person();
            person.setId(personId);
            Unit unit = new Unit();
            unit.setId(unitId);
            Duty duty = new Duty();
            duty.setId(dutyId);

            RetirementController.getRetirementDepController().save(person,
                    retirementStatus,unit,duty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/retirement.do");

    }
    //--------------------------------------------------------------------------------------------------------------------

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getQueryString() != null && req.getParameter("id") != null) {

                Long id = Long.valueOf(req.getParameter("id"));
                RetirementController.getRetirementDepController().findById(id);
                System.out.println("Find by Id " + req.getParameter("id"));


            } else if (req.getQueryString() != null && req.getParameter("personId") != null) {

                Long personId = Long.valueOf(req.getParameter("personId"));

                RetirementController.getRetirementDepController().findByPersonId(personId);
                System.out.println("Find by PersonId " + req.getParameter("personId"));


            } else if (req.getQueryString() != null && req.getParameter("firstname") != null) {

                String firstname = req.getParameter("firstname");
                RetirementController.getRetirementDepController().findByFirstName(firstname);
                System.out.println("Find by FirstName " + req.getParameter("firstname"));

            } else if (req.getQueryString() != null && req.getParameter("firstname") != null) {

                String lastname = req.getParameter("lastname");
                RetirementController.getRetirementDepController().findByLastName(lastname);
                System.out.println("Find by LastName " + req.getParameter("lastname"));

            } else if (req.getQueryString() != null && req.getParameter("nationalcode") != null) {

                String nationalcode = req.getParameter("nationalcode");
                RetirementController.getRetirementDepController().findByNationalCode(nationalcode);
                System.out.println("Find by NationalCode " + req.getParameter("nationalcode"));

            } else if (req.getQueryString() != null && req.getParameter("birthday") != null) {

                Date birthday = Date.valueOf(req.getParameter("birthday"));
                RetirementController.getRetirementDepController().findByBirthDay(birthday);
                System.out.println("Find by BirthDate " + req.getParameter("birthday"));

            }else if (req.getQueryString() != null && req.getParameter("phoneno") != null) {

                String phoneno = req.getParameter("phoneno");
                RetirementController.getRetirementDepController().findByPhoneNo(phoneno);
                System.out.println("Find by Phone Number " + req.getParameter("phoneno"));

            }else{

                Long personId = Long.valueOf(req.getParameter("personId"));
                Boolean retirementStatus =Boolean.valueOf(req.getParameter("retirementStatus")) ;
                Long unitId = Long.valueOf(req.getParameter("Unit"));
                Long dutyId = Long.valueOf(req.getParameter("Duty"));



                RetirementController.getRetirementDepController().findAll();

            }


        }
        catch(
                Exception e)

        {
            e.printStackTrace();
        }
        resp.sendRedirect("/retirement.do");

    }
    //--------------------------------------------------------------------------------------------------------------------


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("type").equals("Deactivate")&& req.getParameter("id") != null) {

                Long id = Long.valueOf(req.getParameter("id"));
                RetirementController.getRetirementDepController().deactivate(id);

                System.out.println("Deactivate " + req.getParameter("id"));

            }else if (req.getParameter("type").equals("Fire")&& req.getParameter("id") != null){

                Long id = Long.valueOf(req.getParameter("id"));
                RetirementController.getRetirementDepController().fire(id);

                System.out.println("Fire " + req.getParameter("id"));


            }else if (req.getParameter("type").equals("Resign")&& req.getParameter("id") != null){

                Long id = Long.valueOf(req.getParameter("id"));
                RetirementController.getRetirementDepController().resign(id);

                System.out.println("Resign " + req.getParameter("id"));
            }else{

                Long id = Long.valueOf(req.getParameter("id"));
                Long personId = Long.valueOf(req.getParameter("personId"));
                Boolean retirementStatus =Boolean.valueOf(req.getParameter("retirementStatus")) ;
                Long unitId = Long.valueOf(req.getParameter("UnitId"));
                Long dutyId = Long.valueOf(req.getParameter("DutyId"));


                Person person = new Person();
                person.setId(personId);
                Unit unit = new Unit();
                unit.setId(unitId);
                Duty duty = new Duty();
                duty.setId(dutyId);

                RetirementController.getRetirementDepController().edit(id,person,
                        retirementStatus,unit,duty);

                System.out.println("Edit " + req.getParameter("id"));

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        resp.sendRedirect("/retirement.do");

    }
}
