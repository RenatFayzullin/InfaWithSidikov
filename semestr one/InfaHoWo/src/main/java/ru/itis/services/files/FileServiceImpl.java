package ru.itis.services.files;

import ru.itis.models.FileInfo;
import ru.itis.repositories.files.FilesRepository;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FileServiceImpl implements FilesService {

    private FilesRepository fileRepository;

    public FileServiceImpl(FilesRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    @Override
    public void saveFileToStorage(InputStream file, String originalFileName, String contentType) {
        FileInfo fileInfo = FileInfo.builder()
                .originalFileName(originalFileName)
                .storageFileName(originalFileName)
                .type(contentType)
                .build();


        try {
            Files.copy(file, Paths.get("C://files/" + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
//            Files.copy(file, Paths.get("C://files/" + fileInfo.getStorageFileName()));
            System.out.println(fileInfo.getStorageFileName());
            System.out.println(fileInfo.getOriginalFileName());
            System.out.println(fileInfo.getType());
            fileRepository.save(fileInfo);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void writeFileFromStorage(Integer fileId, OutputStream outputStream) {
        FileInfo fileInfo = fileRepository.findById(fileId);
        File file = new File("C://files/" + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);
        try {
            Files.copy(file.toPath(),outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FileInfo getFileInfo(Integer fileId) {
        return fileRepository.findById(fileId);
    }
}
