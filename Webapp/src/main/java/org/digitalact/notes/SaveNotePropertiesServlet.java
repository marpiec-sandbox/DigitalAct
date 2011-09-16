package org.digitalact.notes;

import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang.StringUtils;
import org.digitalact.domain.notes.command.SaveNoteData;
import org.digitalact.domain.notes.command.StickyNoteCommand;
import org.digitalact.utils.MyJsonSerializer;
import org.digitalact.utils.MyServletUtils;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Marcin
 * Date: 02.09.11
 * Time: 23:22
 * To change this template use File | Settings | File Templates.
 */
public class SaveNotePropertiesServlet extends HttpServlet {

     @Inject
     private StickyNoteCommand stickyNoteCommand;

     @Override
     public void init(ServletConfig config) throws ServletException {
        super.init(config);
        MyServletUtils.applyBeanInjection(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idString = request.getParameter("id");
        String dataJson = request.getParameter("data");
        Long id;
        SaveNoteData data;
        if(StringUtils.isBlank(idString) || StringUtils.isBlank(dataJson)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            data = MyJsonSerializer.deserialize(dataJson, SaveNoteData.class);
        } catch (JsonSyntaxException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            stickyNoteCommand.saveNote(id, data);
        } catch (IllegalArgumentException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
