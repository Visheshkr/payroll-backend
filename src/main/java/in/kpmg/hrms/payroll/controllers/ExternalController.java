package in.kpmg.hrms.payroll.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;
import in.kpmg.hrms.payroll.dtos.ApiResponse2;
import in.kpmg.hrms.payroll.external.dto.responseDto.ApiResponseDto;
import in.kpmg.hrms.payroll.external.services.DocumentService;

@RestController
@RequestMapping("/document")
public class ExternalController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/temp-upload")
    public ResponseEntity<?> uploadParticularAttachment(@RequestParam("file") List<MultipartFile> file) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ApiResponseDto apiResponseDto = this.documentService.getTempFilePath(file);
            return ResponseEntity.ok(apiResponseDto);

        } catch (FeignException ex) {
            try {
                // If FeignException occurs, create ResponseEntity with Feign response details
                ApiResponseDto errorResponse = objectMapper.readValue(ex.contentUTF8(), ApiResponseDto.class);
                return ResponseEntity.status(ex.status()).body(errorResponse);
            } catch (IOException e) {
                // Handle JSON parsing exception
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error parsing JSON response");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse2<>(false, e.getMessage(), null, HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
        }
    }
    
  
}
