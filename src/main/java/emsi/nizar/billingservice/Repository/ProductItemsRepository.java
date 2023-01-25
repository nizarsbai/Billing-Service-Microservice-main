package emsi.nizar.billingservice.Repository;

import emsi.nizar.billingservice.entities.ProductItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface ProductItemsRepository extends JpaRepository<ProductItems,Long> {
        public Collection<ProductItems> findByBillId(Long id);
}
