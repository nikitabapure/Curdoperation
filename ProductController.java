package Com.nimap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import Com.nimap.entity.Product;
import Com.nimap.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController
{

    @Autowired
    private ProductRepository productRepository;
    @GetMapping
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") int id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") int id, @RequestBody Product updatedProduct) throws NotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException());
        product.setName(updatedProduct.getName());
        product.setCategory(updatedProduct.getCategory());
        // Update other fields as needed		`

        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int id) throws NotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException());

        productRepository.delete(product);
        
        
        
}
}

