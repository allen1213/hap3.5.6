package hbi.core.shop.controllers;

import com.hand.hap.excel.annotation.ExcelExport;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hbi.core.shop.dto.Shop;
import hbi.core.shop.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class ShopController extends BaseController{

    @Autowired
    private IShopService service;


    @RequestMapping(value = "/test/shop/query")
    @ResponseBody
    @ExcelExport(table = Shop.class)
    public ResponseData query(Shop dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request
    , HttpServletResponse response) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectShopWithTotalAmount(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/test/shop/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<Shop> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        List<Shop> list = service.batchUpdate(requestCtx, dto);

        return new ResponseData(list);
    }

    @RequestMapping(value = "/test/shop/remove")
    @ResponseBody
    public ResponseData delete(@RequestBody List<Shop> dto){
        service.removeShop(dto);
        return new ResponseData();
    }

        @ResponseBody
        @RequestMapping(value = "/shop/get_next_id")
        public Integer getNextId() {
            return service.getNextId();
        }

        //public ResponseData saveAsExcel() {}

    }