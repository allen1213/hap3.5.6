package hbi.core.item.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.item.dto.ShopItem;

import java.util.List;

public interface ShopItemMapper extends Mapper<ShopItem>{

    List<ShopItem> selectItemByShopId(ShopItem dto);

    Boolean deleteItemByShopId(Long shopId);

}