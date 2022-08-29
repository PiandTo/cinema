package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import static edu.school21.cinema.models.Avatar.getUploadFolderImages;

@Controller
public class UploadController {
    @Autowired
    FilmService filmService;

    @Autowired
    UserService userService;

    @RequestMapping("/uploadImage/{id}")
    public String uploadImage(@RequestParam("image_to_upload") MultipartFile multipartFile,
                              @PathVariable("id") Long id) {
        if (!multipartFile.isEmpty()) {
            BufferedOutputStream bos = null;
            String name = getUploadFolderImages() + multipartFile.getOriginalFilename();
            try {
                byte[] fileBytes = multipartFile.getBytes();
                bos = new BufferedOutputStream(new FileOutputStream(name));
                bos.write(fileBytes);
                String shortName = multipartFile.getOriginalFilename();
                Film film = filmService.findById(id);
                film.setPoster(shortName);
                filmService.update(film);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/admin/panel/films";
    }

    @Transactional
    @RequestMapping("/uploadAvatar/{id}")
    public String uploadAvatar(@RequestParam("avatar_to_upload") MultipartFile multipartFile,
                              @PathVariable("id") Long id) {
        if (!multipartFile.isEmpty()) {
            BufferedOutputStream bos = null;

            String folderName = getUploadFolderImages() + id;
            File f = new File(folderName);
            if (!f.exists()  || !f.isDirectory()) {
                new File(folderName).mkdirs();
            }
            String name = folderName + '/' + multipartFile.getOriginalFilename();
            try {
                byte[] fileBytes = multipartFile.getBytes();
                bos = new BufferedOutputStream(new FileOutputStream(name));
                bos.write(fileBytes);
                String shortName = multipartFile.getOriginalFilename();
                System.out.println("Id=" + id);
                User user = userService.findById(id);
                user.setAvatar(shortName);
                System.out.println("User:" + user);
                userService.update(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/profile";
    }

    @GetMapping("/avatars/{filename}")
    @ResponseBody
    public void getPoster(@PathVariable("filename") String filename, HttpServletResponse response) throws IOException {
        File imgFile = new File(getUploadFolderImages() + filename);
        responseFile(response, imgFile);
    }

    @GetMapping("/avatars/{id}/{filename}")
    @ResponseBody
    public void getAvatar(@PathVariable("filename") String filename, @PathVariable("id") Long id, HttpServletResponse response) {
        String avatarPath = getUploadFolderImages() + id;
        File imgFile = new File(avatarPath + "/" + filename);
        responseFile(response, imgFile);
    }

    private void responseFile(HttpServletResponse response, File imgFile) {
        try (InputStream is = new FileInputStream(imgFile);
             OutputStream os = response.getOutputStream();) {
            byte[] buffer = new byte[1024]; // пул буферов потока файлов изображений
            while (is.read(buffer) != -1) {
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
