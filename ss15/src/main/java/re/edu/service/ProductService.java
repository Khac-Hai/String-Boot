package re.edu.service;

import re.edu.model.dto.request.ProductCreateDTO;
import re.edu.model.dto.request.ProductUpdateDTO;
import re.edu.model.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> findAll();
    ProductResponseDTO create(ProductCreateDTO req);
    ProductResponseDTO update(Long id, ProductUpdateDTO req);
    void delete(Long id);
}