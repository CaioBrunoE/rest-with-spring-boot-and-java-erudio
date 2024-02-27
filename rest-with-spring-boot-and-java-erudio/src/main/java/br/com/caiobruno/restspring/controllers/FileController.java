package br.com.caiobruno.restspring.controllers;


import br.com.caiobruno.restspring.data.vo.v1.UploadFileResponseVO;
import br.com.caiobruno.restspring.services.FileStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Tag(name = "File Endpoint")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {

    private Logger logger = Logger.getLogger(FileController.class.getName());

    @Autowired
    private FileStorageService  service;

    @PostMapping("/uploadFile")
    public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile file){

        logger.info("Storing file to disk");

     var filename =   service.storeFile(file);

     String fileDowloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
             .path("/api/file/v1/dowloadFile/")
             .path(filename)
             .toUriString();

        return
                new UploadFileResponseVO(
                        filename , fileDowloadUri,file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFile")
    public List<UploadFileResponseVO> uploadMultipleFile(@RequestParam("files") MultipartFile[] files){

        logger.info("Storing file to disk");

        return Arrays.asList(files)
                .stream().map(file-> uploadFile(file))
                .collect(Collectors.toList());

    }

    @GetMapping("/dowloadFile/{filename:.+}")
    public ResponseEntity<Resource> dowloadFile(@PathVariable String filename, HttpServletRequest request){

        logger.info("Read file on disk");

        Resource resource = service.loadFileAsResource(filename);

        String contentType = "";

        try {

            contentType =  request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());


        }catch (Exception e ){

            logger.info("Could not determane file!");
        }
        if (contentType.isBlank()) contentType = "application/octet-stream";


        return  ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""+ resource.getFilename() + "\"")
                .body(resource);

    }


}