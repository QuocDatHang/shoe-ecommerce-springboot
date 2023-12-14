package com.example.service.productImage;

import com.cloudinary.Cloudinary;
import com.example.Model.ProductImage;
import com.example.repository.IProductImageRepository;
import com.example.service.util.UploadUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class ProductImageService {
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private IProductImageRepository productImageRepository;
    @Autowired
    private UploadUtil uploadUtil;

    public ProductImage saveImage(MultipartFile image) throws IOException {
        var file = new ProductImage();
        productImageRepository.save(file);

        var uploadResult = cloudinary.uploader().upload(image.getBytes(), uploadUtil.buildImageUpLoadParams(file));

        String fileUrl = (String) uploadResult.get("secure_url");
        String fileFormat = (String) uploadResult.get("format");
        file.setFileType(fileFormat);
        file.setFileName(file.getId() + "." + fileFormat);
        file.setFileUrl(fileUrl);
        file.setFileFolder(UploadUtil.IMAGE_UPLOAD_FOLDER);
        file.setCloudId(file.getFileFolder() + "/" + file.getId());

        productImageRepository.save(file);
        return file;
    }
}
