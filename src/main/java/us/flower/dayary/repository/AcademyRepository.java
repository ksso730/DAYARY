package us.flower.dayary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import us.flower.dayary.domain.Academy;

public interface AcademyRepository extends JpaRepository<Academy, Long> {
}