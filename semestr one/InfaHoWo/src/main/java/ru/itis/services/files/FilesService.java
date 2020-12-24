package ru.itis.services.files;

import ru.itis.models.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;

public interface FilesService {
    void saveFileToStorage(InputStream file, String originalFileName, String contentType);
    void writeFileFromStorage(Integer fileId, OutputStream outputStream);
    FileInfo getFileInfo(Integer fileId);
}
