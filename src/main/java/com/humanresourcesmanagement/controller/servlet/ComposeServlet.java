package com.humanresourcesmanagement.controller.servlet;


import com.google.gson.Gson;
import com.humanresourcesmanagement.controller.MessageController;
import com.humanresourcesmanagement.controller.session.SessionManager;
import com.humanresourcesmanagement.controller.websocket.WsServer;
import com.humanresourcesmanagement.model.entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import jakarta.validation.*;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = "/composeMessage.do")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class ComposeServlet extends HttpServlet {

    //      ---------INSERT-MESSAGE------------------------------------doPOST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //    doPOST---------MESSAGE-DATA---------
        String subject = req.getParameter("subject");
        User sender = (User) req.getSession().getAttribute("user");
        String receiverUsername = req.getParameter("receiverUsername");
        User receiver = new User();
        receiver.setUserName(receiverUsername);
        String receiverRole = req.getParameter("receiverRole");
        String msg = req.getParameter("message");
        Part file1 = req.getPart("file1");
        Part file2 = req.getPart("file2");
        Part file3 = req.getPart("file3");
        Part file4 = req.getPart("file4");
        Part file5 = req.getPart("file5");

        //    doPOST----VALIDATE-AND-COUNT-FILES----
        List<Part> fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);
        fileList.add(file3);
        fileList.add(file4);
        fileList.add(file5);
        fileList.removeIf(Objects::isNull);


        //  ---------VALIDATING-DATA---------------
        Message message = new Message(
                subject,
                sender,
                receiver,
                receiverRole,
                msg
        );
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Map<Object, String> errors = new HashMap<>();
        for (ConstraintViolation<Message> personConstraintViolation : validator.validate(message)) {
            errors.put(personConstraintViolation.getPropertyPath().toString(), personConstraintViolation.getMessage());
        }
        resp.setContentType(MediaType.APPLICATION_JSON);
        if (errors.isEmpty()) {

            //      doPOST---------SAVE-FILES---------
            List<MessageFile> mfdList = new ArrayList<>();
            for (Part part : fileList) {
                String fileName = part.getSubmittedFileName();
                for (Part part1 : req.getParts()) {
                    String fileLocation = "c:\\root\\" + fileName;
                    part1.write(fileLocation);
                    MessageFile messageFile = new MessageFile(
                            sender,
                            fileLocation,
                            message
                    );
                    mfdList.add(messageFile);
                }
            }

            //      doPOST----------SAVE-DATA----------
            MessageController.getMessageController().save(
                    subject,
                    sender,
                    receiver,
                    receiverRole,
                    msg,
                    mfdList,
                    sender
            );
            //      doPOST---------REDIRECT-------------
            WsServer.send(SessionManager.findHttpSessionByUserName(receiverUsername),req.getParameter("msg"));
            resp.sendRedirect("/composeMessage.jsp");
        } else {
            resp.getWriter().write(new Gson().toJson(errors));
        }
    }
}
