package pers.candyboyou.commodity.crawler;

import com.google.gson.*;
import io.candyboyou.common.framework.model.vo.PageResult;
import io.candyboyou.common.utils.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.candyboyou.commodity.BaseTest;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityMapper;
import pers.candyboyou.commodity.business.model.dto.CommodityOfListDTO;
import pers.candyboyou.commodity.business.model.param.admin.*;
import pers.candyboyou.commodity.business.model.vo.admin.CategoryIdAndNameVO;
import pers.candyboyou.commodity.business.model.vo.admin.CategoryBaseVO;
import pers.candyboyou.commodity.business.service.admin.AttributeService;
import pers.candyboyou.commodity.business.service.admin.CategoryService;
import pers.candyboyou.commodity.business.service.admin.CommodityService;
import pers.candyboyou.commodity.business.service.admin.SkuAttributeService;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlersTest extends BaseTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private SkuAttributeService skuAttributeService;

    @Autowired
    private TCommodityMapper tCommodityMapper;

    Map<String, CommodityOfListDTO> commodityOfListDTOMap = new HashMap<>();

    List<CommodityOfListDTO> commodityOfListDTOS = new ArrayList<>();

    // 保存分类数据
    @Test
    public void saveCategoryInfo() {
        JsonObject result = getResult("https://app.m.mi.com/v2/cate/index", new HashMap<>());
        assert result != null;
        JsonElement data = result.get("data");
        JsonArray jsonArray = data.getAsJsonArray();

//        CommoditySearchParam param = new CommoditySearchParam();
//        param.setPageSize(350);
//        commodityOfListDTOS = tCommodityMapper.selectCommodityOfList(param);

        for (JsonElement jsonElement : jsonArray) {
//            CategorySaveOrUpdateParam param = new CategorySaveOrUpdateParam();
            // 一级分类
            JsonElement category_name = ((JsonObject) jsonElement).get("category_name");
            String name = ((JsonPrimitive) category_name).getAsString();

            // 二级分类
            JsonArray category_list = ((JsonObject) jsonElement).getAsJsonArray("category_list");
            int j = 0;
            for (int i = 1; i < category_list.size(); i+=2) {
                JsonElement curElement = category_list.get(i);
                JsonObject curBody = curElement.getAsJsonObject().getAsJsonObject("body");
                JsonObject body = curBody.getAsJsonObject();
                JsonArray items = body.getAsJsonArray("items");
                int m = 0;
                for (int k = 0; k < items.size(); k++) {
                    JsonObject curItem = items.get(k).getAsJsonObject();

                    JsonObject action = curItem.getAsJsonObject("action");
                    String type = action.getAsJsonPrimitive("type").toString();
                    type = type.replace("\"", "");
                    String path = action.getAsJsonPrimitive("path").toString();
                    path = path.replace("\"", "");
                    String product_name = curItem.getAsJsonPrimitive("product_name").getAsString();

//                    for (CommodityOfListDTO commodityOfListDTO : commodityOfListDTOS) {
//                        if (commodityOfListDTO.getName().equals(product_name)) {
//                            setCommoditySku(path, commodityOfListDTO);
//                            break;
//                        }
//                    }
//
//                    String img_url = curItem.getAsJsonPrimitive("img_url").getAsString();

                    // 三级分类
                    if (type.equals("cate")) {
                        String categoryName = curItem.getAsJsonPrimitive("category_name").getAsString();
                        PageResult<CategoryBaseVO> parentCategory = getParentCategory(categoryName);
                        getThirdCategory(path, parentCategory.getList().get(0).getId());
//                        saveSingleCategoryInfo(curItem, product_name, m);

                    }
                }
            }
        }
    }

    private void getThirdCategory(String categoryId, Long id) {
        Map<String,Object> params = new HashMap<>();
        params.put("cate_id", categoryId);
        JsonObject result = getResult("https://app.m.mi.com/v2/cate/list", params);
        JsonArray data = result.getAsJsonArray("data");

        int m = 0;
        for (JsonElement jsonElement : data) {
            JsonObject curJsonObject = (JsonObject) jsonElement;
            String productName = curJsonObject.getAsJsonPrimitive("name").getAsString();
            saveCommodityInfo(productName, id, m);
            m++;
        }

        int i = 0;
        CommoditySearchParam commoditySearchParam = new CommoditySearchParam();
        commoditySearchParam.setPageSize(1200);
        List<CommodityOfListDTO> commodityOfListDTOS = tCommodityMapper.selectCommodityOfList(commoditySearchParam);
        for (JsonElement jsonElement : data) {
            JsonObject curJsonObject = (JsonObject) jsonElement;
            String productName = curJsonObject.getAsJsonPrimitive("name").getAsString();
            String productId = curJsonObject.getAsJsonPrimitive("product_id").getAsString();
            for (CommodityOfListDTO commodityOfListDTO : commodityOfListDTOS) {
                if (commodityOfListDTO.getName().equals(productName)) {
                    setCommoditySku(productId, commodityOfListDTO.getId());
                    break;
                }
            }
        }
    }

//    private void saveSingleCommodityInfo(JsonObject curJsonObject, Long categoryId, int sort) {
//        CommoditySaveParam commoditySaveParam = new CommoditySaveParam();
//        commoditySaveParam.setCategoryId(categoryId);
//        commoditySaveParam.setName(curJsonObject.getAsJsonPrimitive("name").getAsString());
//        commoditySaveParam.setIsPublish(1);
//        commoditySaveParam.setVerifyStatus(1);
//        commoditySaveParam.setSort(sort);
//        commoditySaveParam.setDetailDesc(curJsonObject.getAsJsonPrimitive("product_desc").getAsString());
//        i++;
//        commodityService.newCommodity(commoditySaveParam);
//    }

    private PageResult<CategoryBaseVO> getParentCategory(String categoryName) {
        CategorySearchParam categorySearchParam = new CategorySearchParam();
        categorySearchParam.setName(categoryName);
        categorySearchParam.setIsShow(1);
        return categoryService.getCategories(categorySearchParam);
    }

    private Long saveSingleCategoryInfo(JsonObject curItem, String product_name, int m, Long parentId) {
//        String categoryName = curItem.getAsJsonPrimitive("category_name").getAsString();
//        PageResult<CategoryBaseVO> categories = getParentCategory(categoryName);
        CategorySaveOrUpdateParam saveParam = new CategorySaveOrUpdateParam();

        saveParam.setName(product_name);
        saveParam.setLevel(2);
        saveParam.setDescription("三级分类");
        saveParam.setSort(m);
        saveParam.setParentId(parentId);
        return categoryService.saveOrUpdateCategory(saveParam);
    }

    private JsonObject getResult(String url, Map<String, Object> params) {
        JsonObject jsonObject = HttpsBerBer.doPost(url, params);
        return jsonObject;
    }

    @Test
    public void setCommoditySku(String productId, Long dataBaseId) {
        String url = "https://app.m.mi.com/v2/product/view2";
        Map<String,Object> params = new HashMap<>();
        params.put("product_id", productId);
        JsonObject jsonObject = HttpsBerBer.doPost(url, params);
        assert jsonObject != null;
        if (jsonObject.get("data") == JsonNull.INSTANCE) {
            System.out.println("+++++++++++++++++++++" + productId);
            return;
        }
        JsonObject result = jsonObject.get("data").getAsJsonObject();
//        JsonArray batch_buy_option = result.get("batch_buy_option").getAsJsonArray();
//        JsonArray batch_info = result.get("batch_info").getAsJsonArray();
        // 购买选项 版本+颜色
//        JsonArray buy_option = result.get("buy_option").getAsJsonArray();
        // sku级别的商品信息
        JsonArray goods_info = result.get("goods_info").getAsJsonArray();

        // 将对应的sku值保存起来
        for (int i = 0; i < goods_info.size(); i++) {
//            skuSaveParam.setSkuAttributeValueSaveParam();


            JsonObject curGoodInfo = goods_info.get(i).getAsJsonObject();
            Set<String> keySet = curGoodInfo.keySet();

            SkuSaveParam skuSaveParam = new SkuSaveParam();

            skuSaveParam.setId(dataBaseId);

            for (String key : keySet) {
                if (StringUtils.isBlank(key)) continue;
                if (key.equals("name")) skuSaveParam.setName(removeMark(curGoodInfo.get("name").toString()));
                if (key.equals("price")) skuSaveParam.setPrice(new BigDecimal(removeMark(curGoodInfo.get("price").toString())));
                if (key.equals("vip_price")) {
                    String vip_price = removeMark(curGoodInfo.get("vip_price").toString());
                    if (StringUtils.isNotBlank(vip_price))
                        skuSaveParam.setVipPrice(new BigDecimal(vip_price));
                }
                if (key.equals("market_price")) {
                    String market_price = removeMark(curGoodInfo.get("market_price").toString());
                    if (StringUtils.isNotBlank(market_price))
                        skuSaveParam.setMarketPrice(new BigDecimal(market_price));
                }
                if (key.equals("buy_limit")) {
                    if (curGoodInfo.get("buy_limit") != null)
                        skuSaveParam.setBuyLimit(Integer.valueOf(removeMark(curGoodInfo.get("buy_limit").toString())));
                }
                if (key.equals("reduce_price")) {
                    if (curGoodInfo.get("reduce_price") != null) {
                        String reduce_price = getAmount(removeMark(curGoodInfo.get("reduce_price").toString()));
                        if (StringUtils.isNotBlank(reduce_price)) {
                            skuSaveParam.setPromotionPrice(new BigDecimal(reduce_price));
                        }
                    }
                }


//                if (key.equals("price")) skuSaveParam.setIsPromotion(curGoodInfo.get("").toString());
            }

            skuAttributeService.saveSkuAttributeValue(Arrays.asList(skuSaveParam), dataBaseId);

//            attributeService.saveOrUpdateSkuAttr(skuSaveParam);
        }
    }

    public String removeMark(String str) {
        return str.replace("\"", "");
    }

    public String getAmount(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(str);
        return matcher.replaceAll("").trim();
    }

    @Test
    public Long saveCommodityInfo(String productName, Long parentId2, int sort) {
        CommoditySaveParam commoditySaveParam = new CommoditySaveParam();
        commoditySaveParam.setIsPublish(1);
        commoditySaveParam.setVerifyStatus(1);
        commoditySaveParam.setSort(sort);
        commoditySaveParam.setName(productName);
        Long id = commodityService.newCommodity(commoditySaveParam);
        categoryService.saveOrUpdateCategoryWithCommodityRelation(null, parentId2, id);
        return id;
    }

    @Test
    public void getAllCategoryIdAndName() {
        List<CategoryIdAndNameVO> allCategoryIdAndName = categoryService.getAllCategoryIdAndName();
    }

    @Test
    public void saveCategoryInfo2() {
        JsonObject result = getResult("https://app.m.mi.com/v2/cate/index", new HashMap<>());
        assert result != null;
        JsonElement data = result.get("data");
        JsonArray jsonArray = data.getAsJsonArray();

        int i = 0;
        for (JsonElement jsonElement : jsonArray) {
            // 一级分类
            JsonElement category_name = ((JsonObject) jsonElement).get("category_name");
            String name = ((JsonPrimitive) category_name).getAsString();
            name = removeMark(name);
            CategorySaveOrUpdateParam param = new CategorySaveOrUpdateParam();
            param.setName(name);
            param.setParentId(0L);
            param.setSort(i++);
            param.setDescription("一级分类");
            param.setIsDelete(0);
            param.setLevel(0);
            Long parentId1 = categoryService.saveOrUpdateCategory(param);


            // 二级分类
            JsonArray category_list = ((JsonObject) jsonElement).getAsJsonArray("category_list");
            int j = 0;
            for (int m = 1; m < category_list.size(); m+=2) {
                JsonElement curElement = category_list.get(m);
                JsonObject curBody = curElement.getAsJsonObject().getAsJsonObject("body");
                JsonObject body = curBody.getAsJsonObject();
                JsonArray items = body.getAsJsonArray("items");

                JsonPrimitive category_name2 = items.get(0).getAsJsonObject().getAsJsonPrimitive("category_name");
                param = new CategorySaveOrUpdateParam();
                String category_name_str2 = removeMark(category_name2.toString());
                param.setName(category_name_str2);
                param.setParentId(parentId1);
                param.setSort(j);
                param.setDescription("二级分类");
                param.setIsDelete(0);
                param.setLevel(1);
                Long parentId2 = categoryService.saveOrUpdateCategory(param);

                int n = 0;
                int o = 0;
                for (int k = 0; k < items.size(); k++) {
                    JsonObject curItem = items.get(k).getAsJsonObject();

                    JsonObject action = curItem.getAsJsonObject("action");
                    String type = action.getAsJsonPrimitive("type").toString();
                    type = type.replace("\"", "");
                    String path = action.getAsJsonPrimitive("path").toString();
                    path = path.replace("\"", "");
                    String product_name = curItem.getAsJsonPrimitive("product_name").getAsString();

                    // 三级分类
                    if (type.equals("cate")) {
                        String categoryName = curItem.getAsJsonPrimitive("category_name").getAsString();
                        Long parentId3 = saveSingleCategoryInfo(curItem, product_name, n, parentId2);
                        getThirdCategory(path, parentId3);
                        n++;
                    } else {
                        Long id = saveCommodityInfo(product_name, parentId2, o);
                        setCommoditySku(path, id);
                        o++;
                    }
                }
                j++;
            }
        }
    }



}
