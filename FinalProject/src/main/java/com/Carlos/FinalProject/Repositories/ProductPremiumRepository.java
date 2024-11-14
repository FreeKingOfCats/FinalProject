package com.Carlos.FinalProject.Repositories;

import com.Carlos.FinalProject.ECommerce.ProductPremium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPremiumRepository extends JpaRepository<ProductPremium, Long> {
    Optional<ProductPremium> findByName(String name);
}