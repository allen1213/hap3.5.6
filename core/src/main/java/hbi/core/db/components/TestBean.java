package hbi.core.db.components;

import hbi.core.item.dto.ShopItem;
import hbi.core.item.mapper.ShopItemMapper;
import hbi.core.shop.dto.Shop;
import hbi.core.shop.mapper.ShopMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class TestBean {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private ShopItemMapper itemMapper;


    public List<Shop> loadData(String dsName, String datasetName, Map<String,Object> parameters){

        Object shopId = parameters.get("shopId");
        if (shopId == null)
            return null;

        List<Shop> shops = shopMapper.selectByShopId(shopId.toString());
        return shops;
    }

    public List<ShopItem> loadItemData(String dsName, String datasetName, Map<String,Object> parameters) {
        return itemMapper.selectAll();
    }

    public List<ShopItem> selectItemByShopId(String dsName, String datasetName, Map<String,Object> parameters) {

        String shopId = parameters.get("shopId").toString();
        if (StringUtils.isEmpty(shopId))
            return null;
        ShopItem item = new ShopItem(Long.parseLong(shopId));

        return itemMapper.selectItemByShopId(item);

    }

}
