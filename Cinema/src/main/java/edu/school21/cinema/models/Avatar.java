package edu.school21.cinema.models;

import lombok.Data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class Avatar {
    private String avatarUrl;
    private Long avatarFileSize;
    private String avatarMimeType;

   public static List<Avatar> createAvatarList(User user) {
        String avatarPath = getUploadFolderImages() + user.getId();
        File dir = new File(avatarPath); //path указывает на директорию
        File[] arrFiles = dir.listFiles();
        List<Avatar> lstAvatar = new ArrayList<>();
        if (arrFiles != null) {
            for (int i = 0; i < Objects.requireNonNull(arrFiles).length; i++) {
                Avatar avatar = new Avatar();
                avatar.setAvatarUrl(arrFiles[i].getName());
                avatar.setAvatarFileSize(arrFiles[i].length() / 1024);
                avatar.setAvatarMimeType(URLConnection.guessContentTypeFromName(arrFiles[i].getName()));
                lstAvatar.add(avatar);
            }
        }
        return lstAvatar;
    }

    public static String getUploadFolderImages() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cinemaPersistanceUnit");
        Map<String, Object> properties = emf.getProperties();
        return properties.get("images.upload.path").toString();
    }
}


