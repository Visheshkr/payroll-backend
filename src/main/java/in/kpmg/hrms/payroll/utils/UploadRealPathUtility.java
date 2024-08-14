package in.kpmg.hrms.payroll.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.kpmg.hrms.payroll.exceptions.InternalServerErrorException;
import in.kpmg.hrms.payroll.external.dto.requestDto.UploadRealAttachmentRequestDto;
import in.kpmg.hrms.payroll.external.services.DocumentService;

@Component
public class UploadRealPathUtility {

    @Autowired
    private DocumentService documentService;

    static private final String hashPassword = "JaiShreeRam";

    AES256TextEncryptor aesEncryptor = new AES256TextEncryptor();

    public UploadRealPathUtility() {
        this.aesEncryptor.setPassword(hashPassword);
    }

    public HashMap<String, String> getFileNameAndPath(String encryptedTempPath, String id, String module) throws IOException {
        HashMap<String, String> response = new HashMap<>();

        String pathDirectory = System.getProperty("java.io.tmpdir");
        String tempDocPath = this.aesEncryptor.decrypt(encryptedTempPath);

        Path tempPath = Paths.get(tempDocPath);

        if (!Files.exists(tempPath)) {
            throw new InternalServerErrorException("File Path doesn't exists!");
        }

        if (!tempPath.startsWith(pathDirectory)) {

            String fileName = tempPath.getFileName().toString();

            response.put("fileName", fileName);
            response.put("filePath", encryptedTempPath);

        } else {
            UploadRealAttachmentRequestDto uploadRealAttachmentRequestDto = new UploadRealAttachmentRequestDto();
            uploadRealAttachmentRequestDto.setId(id);
            uploadRealAttachmentRequestDto.setModule(module);
            uploadRealAttachmentRequestDto.setVirtualPath(encryptedTempPath);

            HashMap<String, String> realFilePath = this.documentService.getRealFilePath(uploadRealAttachmentRequestDto);

            String realPath = this.aesEncryptor.decrypt(realFilePath.get("realPath"));
            String fileName = Paths.get(realPath).getFileName().toString();

            response.put("fileName", fileName);
            response.put("filePath", encryptedTempPath);
        }

        return response;
    }
}
