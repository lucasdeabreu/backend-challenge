package com.invillia.acme.storeservice.repositories;

import com.invillia.acme.storeservice.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StoreRepository extends JpaRepository<Store, Long>, JpaSpecificationExecutor {
}
