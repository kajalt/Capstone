package repositories;

import com.capstone.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product save(Product product);

    Optional<Product> findProductById(Long id);

    List<Product> findProductByPriceBetween(Double low, Double high);

    List<Product> findProductByIsPrimeSpecial(Boolean val);
    List<Product> findProductByIsPrimeSpecialTrue();

    //List<Product> findAllOrderByIdDesc();
    List<Product> findAllByOrderByIdDesc();
}