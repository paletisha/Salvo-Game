package com.codeoftheweb.salvo;

import java.util.List;

import org.aspectj.apache.bcel.util.Play;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByuserName(String userName);
}

