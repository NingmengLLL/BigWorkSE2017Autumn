package businesslogic.goodsbl.goodsUpdate;

import po.GoodsPO;

import java.util.List;

public interface GoodsUpdateInfo {

    /**
     * 提供给当单据审批之后用来修改商品属性的方法
     */

    public void goodsUpdate(List<GoodsPO> goodsList);
}
