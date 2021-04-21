package com.spehof.piggy.service;

import com.spehof.piggy.DAO.AssetDao;
import com.spehof.piggy.domain.Asset;
import com.spehof.piggy.domain.Portfolio;
import com.spehof.piggy.exception.AssetNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@Service
public class AssetService {

    private final AssetDao assetDao;

    @Autowired
    public AssetService(AssetDao assetDao) {
        this.assetDao = assetDao;
    }

    public Asset create(String name, String price){
        Asset asset = new Asset(name, price);
        return assetDao.save(asset);
    }

    public List<Asset> getAllFromPortfolio(Portfolio portfolio){
        return portfolio.getAssets();
    }

    public Asset addToPortfolio(Portfolio portfolio, Asset assetFromApi){
        Asset assetFromDb = assetDao.getByName(assetFromApi.getName()).orElseThrow(AssetNotFoundException::new);
//        TODO maybe will be problem with saving new portfolio
        portfolio.setAsset(assetFromDb);

        return assetDao.save(assetFromDb);
    }

    public List<Asset> getAll(){
        return assetDao.findAll();
    }

    public void deleteFromPortfolio(Portfolio portfolio, Asset assetFromApi){
        Asset assetFromDb = portfolio.getAsset(assetFromApi.getId());
        portfolio.removeAsset(assetFromDb);
        assetDao.delete(assetFromDb);
    }

    public Asset update(Asset assetFromApi){
        Asset assetFromDb =  assetDao.findById(assetFromApi.getId()).orElseThrow(AssetNotFoundException::new);
        BeanUtils.copyProperties(assetFromApi, assetFromDb, "id");
        return assetDao.save(assetFromDb);
    }

    public Asset getAssetFromDb(String assetName){
        return assetDao.getByName(assetName).orElseThrow(AssetNotFoundException::new);
    }

    public void delete(Asset asset) {
        assetDao.delete(asset);
    }
}
