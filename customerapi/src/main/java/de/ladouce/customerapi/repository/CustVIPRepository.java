package de.ladouce.customerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.ladouce.customerapi.entity.CustVPI;

public interface CustVIPRepository extends JpaRepository<CustVPI,Long> {
}
