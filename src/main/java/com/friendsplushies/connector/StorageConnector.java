package com.friendsplushies.connector;

import com.amazonaws.HttpMethod;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.stereotype.Component;


public interface StorageConnector {

/**
     * upload file to local
     *
     * @param file
     * @throws Exception
     */
    SavingResult createFile(InputStream file, String filePath, String fileName, Long fileSize, Boolean isPublic) throws Exception;

    void removeFile(String filePath) throws Exception;

    InputStream downloadFile(String filePath) throws IOException;

    String getFileUrl(String filePath);

    String fetchFileFromUrl(String fetchedUrl, String filePath);

    String getPresignedUrl(String key, HttpMethod method);
}
