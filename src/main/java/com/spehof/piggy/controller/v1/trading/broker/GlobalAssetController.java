package com.spehof.piggy.controller.v1.trading.broker;

import com.spehof.piggy.domain.trading.Asset;
import com.spehof.piggy.service.trading.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@RestController
@RequestMapping("/api/v1/assets")
public class GlobalAssetController {

    private final AssetService assetService;

    @Autowired
    public GlobalAssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public List<Asset> getAll(){
        return assetService.getAll();
    }

    @PostMapping
    public Asset create(@RequestBody Asset assetFromApi){
        return assetService.create(assetFromApi.getTicker(), assetFromApi.getPrice());
    }

    @DeleteMapping
    public void delete(@RequestBody Asset assetFromApi){
        assetService.delete(assetFromApi);
    }

    @PutMapping
    public Asset update(@RequestBody Asset assetFromApi){
        return assetService.update(assetFromApi);
    }
}
