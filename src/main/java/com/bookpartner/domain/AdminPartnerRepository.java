package com.bookpartner.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminPartnerRepository extends JpaRepository<AdminPartner, String> {
    Optional<AdminPartner> findByAdmName(String admName);
}
