package com.humanresourcesmanagement.controller.attachmentSaver;

import com.humanresourcesmanagement.controller.exceptions.ExceptionWrapper;
import com.humanresourcesmanagement.model.entity.Attachment;
import com.humanresourcesmanagement.model.entity.User;
import jakarta.servlet.http.*;

public class PartGetter {
    //  ---------SINGLETON---------------------------------------------------------------
    private static final PartGetter partGetter = new PartGetter();

    private PartGetter() {
    }

    public static PartGetter getPartGetter() {
        return partGetter;
    }

    //  ---------GET-PART-AND-SAVE-IT------------------------------------------------------
    String attachmentName;
    String path;
    public Attachment saveAttachment(Part part, User user) {
        try {
            attachmentName = part.getSubmittedFileName();
            path = "c:\\root\\" + attachmentName;
            part.write(path);
        }catch (Exception e){
            ExceptionWrapper.getExceptionWrapper().getMessage(e);
        }
        return new Attachment(attachmentName,user,path);
    }

}
