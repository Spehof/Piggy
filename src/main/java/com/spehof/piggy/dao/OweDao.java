package com.spehof.piggy.dao;

import com.spehof.piggy.domain.Owe;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 10/04/2021
 */
public interface OweDao extends JpaRepository<Owe, Long> {
}
