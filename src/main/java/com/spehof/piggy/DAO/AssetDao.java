package com.spehof.piggy.DAO;

import com.spehof.piggy.domain.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Spehof
 * @created 20/04/2021
 */
public interface AssetDao extends JpaRepository<Asset, Long> {

    Optional<Asset> getByTicker(String name);
}
