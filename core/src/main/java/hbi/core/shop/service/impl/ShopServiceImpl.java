package hbi.core.shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.item.dto.ShopItem;
import hbi.core.item.mapper.ShopItemMapper;
import hbi.core.shop.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.core.shop.dto.Shop;
import hbi.core.shop.service.IShopService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShopServiceImpl extends BaseServiceImpl<Shop> implements IShopService{

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private ShopItemMapper itemMapper;

    @Override
    public Integer getNextId() {
        return shopMapper.getNextId();
    }

    @Override
    public Boolean removeShop(List<Shop> list) {

        int shopCount = 0,itemCount = 0;

        for (Shop shop : list) {
            if (shop.getShopId() != null) {
                itemMapper.deleteItemByShopId(shop.getShopId());
            }
            shopMapper.deleteByPrimaryKey(shop);
            shopCount ++;
        }

        return shopCount == list.size();
    }

    @Override
    public List<Shop> selectShopWithTotalAmount(IRequest requestContext, Shop dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);

        List<Shop> list = shopMapper.selectShopWithTotalAmount(dto);

        for (Shop s : list) {
            if (null == s.getTotalAmount()) {
                s.setTotalAmount(0L);
            }
        }
        return list;
    }

    private boolean handleItemList(Shop shop) {

        boolean isUpdate = false;

        for (ShopItem item : shop.getItemList()) {
            if (item.getShopId() == null) {
                item.setShopId(shop.getShopId());
                itemMapper.insertSelective(item);
            } else {
                checkOvn(itemMapper.updateByPrimaryKeySelective(item),shop);
                isUpdate = true;
            }
        }

        return isUpdate;

    }

    @Override
    public Shop createShop(Shop shop) {

        shopMapper.insertSelective(shop);

        if (shop.getItemList() != null) {
            handleItemList(shop);
        }

        return shop;
    }


    @Override
    public Shop updateShop(Shop shop) {

        shopMapper.updateByPrimaryKeySelective(shop);

        if (shop.getItemList() != null) {
            handleItemList(shop);
        }

        return shop;
    }

    @Override
    public List<Shop> batchUpdate(IRequest request, List<Shop> list) {

        for (Shop shop : list) {
            switch (shop.get__status()) {
                case "add":
                    self().createShop(shop);
                    break;
                case "update":
                    self().updateShop(shop);
                    break;
                case "delete":
                    self().removeShop(list);
                    break;
                default:
                    break;
            }
        }

        return list;
    }

    @Override
    public int deleteByPrimaryKey(Shop record) {

        if (record.getItemList() != null) {
            itemMapper.deleteItemByShopId(record.getShopId());
        }

        return super.deleteByPrimaryKey(record);
    }

}