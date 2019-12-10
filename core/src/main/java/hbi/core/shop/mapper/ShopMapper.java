package hbi.core.shop.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.shop.dto.Shop;

import java.util.List;

public interface ShopMapper extends Mapper<Shop>{

    Integer getNextId();

    List<Shop> selectShopWithTotalAmount(Shop dto);

    //  LOV  此处一定要加上参数，否则会空指针异常
    List<Shop> findAll(Shop shop);

    List<Shop> selectByShopId(String shopId);


}