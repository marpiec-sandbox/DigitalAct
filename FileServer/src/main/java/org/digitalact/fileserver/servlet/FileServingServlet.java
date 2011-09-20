package org.digitalact.fileserver.servlet;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet odpowiedzialny za serwowanie plików.
 */
public class FileServingServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServingServlet.class);

    private static final String FILE_IDENTIFIER_PARAM = "f";
    private static final String FILE_CHECKSUM_PARAM = "c";
    private static final String DEFAULT_CACHE_NAME = "main_cache";

    private FileServerConfiguration configuration;

    private FileLoader fileLoader;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        configuration = new FileServerConfiguration();

        CacheManager cacheManager = CacheManager.create();
        cacheManager.addCache(DEFAULT_CACHE_NAME);
        Cache cache = cacheManager.getCache(DEFAULT_CACHE_NAME);

        fileLoader = new FileLoaderMemoizerImpl(new FileLoaderImpl(), cache);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fileIdentifier = request.getParameter(FILE_IDENTIFIER_PARAM);
        String fileChecksum = request.getParameter(FILE_CHECKSUM_PARAM);

        //Sprawdzanie czy podane odpowiednie parametry
        if(StringUtils.isEmpty(fileIdentifier) ||
           StringUtils.isEmpty(fileChecksum)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //Wczytanie pliku
        FileData fileData = fileLoader.loadFile(configuration.getFilesDirectory() + fileIdentifier);

        if (fileData == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //Sprawdzenie czy suma kontrolna pliku jest poprawna
        if(!StringUtils.equals(fileChecksum, fileData.getChecksum())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            LOGGER.warn("Unauthorized access try to file " + fileIdentifier);
            return;
        }

        //TODO tu może być potencjalnie pole do optymalizacji
        //Wysłanie pliku
        response.setHeader("Content-Length", String.valueOf(fileData.getFileData().length));
        response.setContentType(getServletContext().getMimeType(fileIdentifier));
        response.getOutputStream().write(fileData.getFileData());
        response.getOutputStream().flush();

    }
}
