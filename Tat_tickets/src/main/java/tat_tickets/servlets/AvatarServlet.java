package tat_tickets.servlets;

import tat_tickets.dto.UserDto;
import tat_tickets.models.FileInfo;
import tat_tickets.services.AvatarService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@WebServlet("/avatarServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AvatarServlet extends HttpServlet {
    private final String uploadPath = "/Users/user/Desktop/папочка/tat_tickets/Tat_tickets/src/main/webapp/avatars/";
    private AvatarService avatarService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        avatarService = (AvatarService) config.getServletContext().getAttribute("avatarService");
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        String avatarFileName;
        if (user.getAvatarId() != 0) {
            FileInfo fileInfo = avatarService.getAvatarById(user.getAvatarId());
            avatarFileName = fileInfo.getStorageFileName();
        } else {
            avatarFileName = "default_avatar.jpg";
        }

        String filePath = uploadPath + avatarFileName;

        String contentType = req.getServletContext().getMimeType(filePath);
        resp.setContentType(contentType);

        try (InputStream input = new FileInputStream(filePath); OutputStream output = resp.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("user");

        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if (filePart != null && filePart.getSize() > 0) {
            InputStream fileContent = filePart.getInputStream();

            String contentType = filePart.getContentType();
            if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
                req.setAttribute("error", "Неверный формат: файл должен быть в формате PNG или JPEG.");
                req.getRequestDispatcher("/tickets/profile").forward(req, resp);
                return;
            }

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String storageFileName = UUID.randomUUID() + "_" + fileName;
            String filePath = uploadPath + storageFileName;

            Files.copy(fileContent, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

            FileInfo fileInfo = FileInfo.builder()
                    .originalFileName(fileName)
                    .storageFileName(storageFileName)
                    .size(filePart.getSize())
                    .type(filePart.getContentType())
                    .build();

            if (user.getAvatarId() != null && user.getAvatarId() != 0) {
                deleteAvatarFile(user);
            }
            req.getSession().setAttribute("user", avatarService.updateAvatar(user, fileInfo));

        } else {
            req.getSession().setAttribute("error", "Вы должны сначала загрузить аватар.");
        }
        resp.sendRedirect("/tickets/profile");
    }

    private void deleteAvatarFile(UserDto user) {
        if (user.getAvatarId() != null) {
            FileInfo fileInfo = avatarService.getAvatarById(user.getAvatarId());
            String filePath = uploadPath + fileInfo.getStorageFileName();

            File fileToDelete = new File(filePath);
            if (fileToDelete.exists()) {
                fileToDelete.delete();
            }

            avatarService.deleteAvatarById(user.getAvatarId());
        }
    }
}
