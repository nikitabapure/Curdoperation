package Com.nimap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.nimap.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

