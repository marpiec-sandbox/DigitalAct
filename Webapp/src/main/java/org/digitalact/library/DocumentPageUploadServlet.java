package org.digitalact.library;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.digitalact.configuration.WebappConfiguration;
import org.digitalact.domain.documents.command.DocumentPageCommand;
import org.digitalact.domain.documents.command.UploadedFileParam;
import org.digitalact.utils.MyServletUtils;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet obsługujący upload plików ze stronami sprawy.
 * @author Marcin Pieciukiewicz
 */
public class DocumentPageUploadServlet extends HttpServlet {

    @Inject
    private WebappConfiguration webappConfiguration;

    @Inject
    private DocumentPageCommand documentPageCommand;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        MyServletUtils.applyBeanInjection(this, config.getServletContext());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (ServletFileUpload.isMultipartContent(request)) {
                List<FileItem> files = extractFileList(request);
                UploadedFileParam uploadedFileParam = createUploadedFileParam(files);
                documentPageCommand.storeUploadedFile(webappConfiguration.getUploadedFileDirectory(), uploadedFileParam);
                writeResponse(response);
            }
        } catch (FileUploadException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<FileItem> extractFileList(HttpServletRequest request) throws FileUploadException {
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
        return servletFileUpload.parseRequest(request);
    }

    private void writeResponse(HttpServletResponse response) throws IOException {
        response.getOutputStream().print("[{\"name\":\"picture1.jpg\",\"size\":902604,\"url\":\"\\/\\/example.org\\/files\\/picture1.jpg\",\"thumbnail_url\":\"\\/\\/example.org\\/thumbnails\\/picture1.jpg\",\"delete_url\":\"\\/\\/example.org\\/upload-handler?file=picture1.jpg\",\"delete_type\":\"DELETE\"},{\"name\":\"picture2.jpg\",\"size\":841946,\"url\":\"\\/\\/example.org\\/files\\/picture2.jpg\",\"thumbnail_url\":\"\\/\\/example.org\\/thumbnails\\/picture2.jpg\",\"delete_url\":\"\\/\\/example.org\\/upload-handler?file=picture2.jpg\",\"delete_type\":\"DELETE\"}]");
    }

    private UploadedFileParam createUploadedFileParam(List<FileItem> files) {
        UploadedFileParam uploadedFileParam = new UploadedFileParam();

        for (FileItem fileItem : files) {
            if (fileItem.isFormField()) {
                //to gdyby jakieś paramtery, typu numer sprawy byly przekazywane
            } else {

                uploadedFileParam.setContentType(fileItem.getContentType());
                uploadedFileParam.setFile(fileItem.get());
                uploadedFileParam.setFilename(fileItem.getName());
                uploadedFileParam.setFileSize(fileItem.getSize());
            }
        }
        return uploadedFileParam;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        writeResponse(response);
    }
}
