package businesslogic.goodsbl.goodsSearch.concreteStrategy;

import businesslogic.goodsbl.GoodsController;
import businesslogic.goodsbl.goodsSearch.GoodsSearchStrategy;
import vo.inventoryVO.GoodsVO;

import java.util.List;

public class GoodNameStrategy implements GoodsSearchStrategy {
    String goodsName;
    GoodsController controller;

    public GoodNameStrategy() {
        controller = new GoodsController();
    }

    public GoodNameStrategy(String goodsName) {
        this.goodsName = goodsName;
        controller = new GoodsController();
    }

    @Override
    public List<GoodsVO> getGoods() {
        List<GoodsVO> goodsList = controller.SearchGoods(goodsName);
        return goodsList;
    }
}
