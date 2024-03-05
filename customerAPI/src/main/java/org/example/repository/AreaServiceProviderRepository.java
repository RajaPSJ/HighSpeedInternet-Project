package org.example.repository;

import org.example.entity.AreaServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaServiceProviderRepository extends JpaRepository<AreaServiceProvider, Long> {
}
