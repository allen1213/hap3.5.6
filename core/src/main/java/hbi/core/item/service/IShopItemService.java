package hbi.core.item.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.core.item.dto.ShopItem;

import java.util.List;

public interface IShopItemService extends IBaseService<ShopItem>, ProxySelf<IShopItemService>{

    List<ShopItem> selectItemByShopId(IRequest requestContext, ShopItem dto, int page, int pageSize);

    Boolean deleteItemByShopId(Long shopId);

}