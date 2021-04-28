package com.spehof.piggy.service.trading;

import com.spehof.piggy.DAO.AssetDao;
import com.spehof.piggy.domain.trading.Asset;
import com.spehof.piggy.domain.trading.Portfolio;
import com.spehof.piggy.exception.AssetConflictTickerException;
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

    public Asset create(String title, String price){
        if (assetDao.getByTicker(title).isPresent())
            throw new AssetConflictTickerException("Asset with name " + title + " already exist");
        Asset asset = new Asset(title, price);
        return assetDao.save(asset);
    }

    public List<Asset> getAllFromPortfolio(Portfolio portfolio){
        return portfolio.getAssets();
    }

    public Asset addToPortfolio(Portfolio portfolio, Asset assetFromApi){
        Asset assetFromDb = assetDao.getByTicker(assetFromApi.getTicker())
                .orElseThrow(() -> new AssetNotFoundException("Asset with this ticker " + assetFromApi.getTicker() + " not found"));
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
        Asset assetFromDb =  assetDao.findById(assetFromApi.getId())
                .orElseThrow(() -> new AssetNotFoundException("Asset with this ID " + assetFromApi.getId() + " not found"));
        if (assetFromApi.getTicker() != null && assetDao.getByTicker(assetFromApi.getTicker()).isPresent())
            throw new AssetConflictTickerException("Asset with ticker " + assetFromApi.getTicker() + " already exist");

        BeanUtils.copyProperties(assetFromApi, assetFromDb, "id");
        return assetDao.save(assetFromDb);
    }

    public Asset getAssetFromDb(String assetName){
        return assetDao.getByTicker(assetName)
                .orElseThrow(() -> new AssetNotFoundException("Asset with this name " + assetName + " not found"));
    }

    public void delete(Asset asset) {
        assetDao.delete(asset);
    }
}
