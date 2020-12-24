package ru.itis.servlets;

import ru.itis.models.FileInfo;
import ru.itis.services.files.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/files")
public class FilesServlet extends HttpServlet {

    private FilesService filesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
         this.filesService = (FilesService) config.getServletContext().getAttribute("filesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileId = req.getParameter("id");
        FileInfo fileInfo = filesService.getFileInfo(Integer.parseInt(fileId));
        resp.setContentType(fileInfo.getType());
        resp.setHeader("Content-Disposition","filename=\"" + fileInfo.getOriginalFileName()+"\"");
        filesService.writeFileFromStorage(Integer.parseInt(fileId),resp.getOutputStream());
        resp.flushBuffer();
    }
}
