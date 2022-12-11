package com.example.demo.app.service;

import com.example.demo.app.model.dto.ImgurResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class FileHelperServiceImpl implements IFileHelperService {

    private final static Logger logger = LoggerFactory.getLogger(FileHelperServiceImpl.class);
    private final static String CLIENT_ID = "290f1da67e0abff";
    private final static String CLIENT_SECRET = "21070256f4d7012612f67ac52cce96e0e8dec2e5";
    private final static String ACCESS_TOKEN = "699600bd86940ecb68bd834832cc4c9e0141c745";

    @Override
    public ImgurResponse uploadFile(MultipartFile fileToUpload, String productName) {
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile("temp_file", ".png");
            fileToUpload.transferTo(tempFile);
            HttpResponse<String> response = Unirest.post("https://api.imgur.com/3/upload")
//                    .header(HttpHeaders.AUTHORIZATION, getAuthHeader())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + ACCESS_TOKEN)
                    .multiPartContent()
                    .field("image", new File(tempFile.toString()))
                    .field("title", "testName")
                    .field("name", prepareProductName(productName))
                    .asString();
            return handleReponse(response);
        } catch (IOException e) {
            logger.error("Error while trying to upload file to imgur.", e);
            return null;
        }
        finally {
            try {
                if (Objects.nonNull(tempFile)) {
                    Files.deleteIfExists(tempFile);
                }
            } catch (IOException e) {
                logger.error("Error while trying to delete tmp file", e);
            }
        }
    }

    @Override
    public boolean deleteFile(String deleteHash) {
        try {
            Unirest.delete("https://api.imgur.com/3/image/" + deleteHash)
//                    .header(HttpHeaders.AUTHORIZATION, getAuthHeader())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + ACCESS_TOKEN)
                    .asString();
            return true;
        } catch (Exception e) {
            logger.error("Error while trying to delete file from imgur", e);
            return false;
        }
    }

    private ImgurResponse handleReponse(HttpResponse<String> response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.getBody(), ImgurResponse.class);
        } catch (Exception e) {
            logger.error("Error while mapping value to json", e);
            return null;
        }
    }

    private String prepareProductName(String name) {
        return name + "_" + LocalDateTime.now().toString()
                .replace("-", "_")
                .replace(":", "_")
                .replace(" ", "_")
                .replace(".", "_") + ".png";
    }

    private String getAuthHeader() {
        return "Client-ID " + CLIENT_ID;
    }

}
