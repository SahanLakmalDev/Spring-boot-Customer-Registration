package lk.ijse.dep11.SpringAngular.repository;

import lk.ijse.dep11.SpringAngular.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByContact(String contact);
}
