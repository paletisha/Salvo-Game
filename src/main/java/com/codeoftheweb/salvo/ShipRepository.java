package com.codeoftheweb.salvo;

import java.util.List;

import org.aspectj.apache.bcel.util.Play;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long> {
    Ship findById(long id);
}
