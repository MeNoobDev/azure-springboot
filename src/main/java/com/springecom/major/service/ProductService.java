package com.springecom.major.service;

import com.springecom.major.model.Product;
import com.springecom.major.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void addProduct(Product productDTO) {
        Product product = new Product();

        // Map fields from productDTO to product (excluding imageFile)
        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        product.setImageName(productDTO.getImageName());

        // Set image data in the product
        product.setImageData(productDTO.getImageData());

        // Remove any manual setting of id

        // Save the product
        productRepository.save(product);
    }



    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProductsByCategoryId(int id) {
        return productRepository.findByCategoryId(id);
    }

    public byte[] saveImage(MultipartFile file) {
        try {
            // Implement the logic to save the file data (e.g., to a byte array)
            return file.getBytes();
        } catch (IOException e) {
            // Handle the exception (e.g., log an error)
            e.printStackTrace();
            return null;
        }
    }

    public void removeProductById(Long id) {
        // Implement the logic to delete a product by ID
        productRepository.deleteById(id);
    }

}
