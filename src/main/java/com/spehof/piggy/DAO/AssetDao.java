package com.spehof.piggy.DAO;

import com.spehof.piggy.domain.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Spehof
 * @created 20/04/2021
 */
public interface AssetDao extends JpaRepository<Asset, Long> {

    Asset getByName(String name);
}
