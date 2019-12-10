package hbi.core.shop.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.core.shop.dto.Shop;

import java.util.List;

public interface IShopService extends IBaseService<Shop>, ProxySelf<IShopService>{

    Integer getNextId();

    Boolean removeShop(List<Shop> list);

    List<Shop> selectShopWithTotalAmount(IRequest requestContext, Shop dto, int page, int pageSize);

    Shop createShop(Shop shop);

    Shop updateShop(Shop shop);
}