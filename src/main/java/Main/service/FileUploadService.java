package Main.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {
    public boolean uploadFile(MultipartFile picture, String path) throws IOException {

        StorageClient storageClient = StorageClient.getInstance(FirebaseApp.getInstance());
        String blobString = path;
        storageClient.bucket("connectville-88fb1.appspot.com").create(blobString, picture.getInputStream(), Bucket.BlobWriteOption.userProject("connectville-88fb1"));
        System.out.println(FirebaseApp.getInstance().getName());
        return true;
    }

    public ByteArrayOutputStream downloadFile(String fileName) throws IOException {

        StorageClient storageClient = StorageClient.getInstance(FirebaseApp.getInstance());
        Blob pic = storageClient.bucket("connectville-88fb1.appspot.com").get(fileName);

        Path path = Paths.get(fileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(16536);
        try{pic.downloadTo(outputStream);}
        catch (Exception e){
            throw new IOException("File not found");
        }
        return outputStream;
    }
}
