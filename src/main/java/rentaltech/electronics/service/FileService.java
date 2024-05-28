package rentaltech.electronics.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 이미지 파일을 등록하기 위해 이미지 파일명을 수정하는 클래스
 */

@Service
@Log
public class FileService {

    public String uploadImg(String uploadPath, String originalFileName, byte[] fileData) throws IOException {

        UUID uuid = UUID.randomUUID();  // 유일한 식별자 생성 (업로드되는 파일마다 고유한 이름 부여)
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));   // 원본 파일의 확장자 추출 -> .을 찾아 잘라냄
        String savedFileName = uuid.toString() + extension; // toString을 통해 문자열 변환 + 파일 확장자

        String fileUploadFullUrl = uploadPath + "/" + savedFileName;    // 실제로 저장될 전체 경로 설정

        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();

        return savedFileName;
    }

    public void deleteFile(String filePath) {
        File deleteFile = new File(filePath);

        if (deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일 삭제 완료");
        } else {
            log.info("파일 존재x");
        }
    }
}
