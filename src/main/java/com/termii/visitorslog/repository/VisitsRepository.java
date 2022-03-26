package com.termii.visitorslog.repository;

import com.termii.visitorslog.domain.Visits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitsRepository extends JpaRepository<Visits, Long> {
}