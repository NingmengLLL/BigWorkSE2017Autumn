package businesslogic.goodsClassificationbl;

import blService.goodsClassificationblService.GoodsClassificationblService;
import exception.ExistException;
import util.ResultMessage;
import vo.inventoryVO.GoodsClassificationVO;

import java.util.List;
import java.util.Set;

public class GoodsClassificationController implements GoodsClassificationblService {
    GoodsClassification goodsClassification = new GoodsClassification();
    @Override
    public Set<GoodsClassificationVO> show() {
        return null;//goodsClassification.show();
    }

    @Override
    public String getID(String fatherId,int order) {
        String id = goodsClassification.getID(fatherId,order);
        return id;
    }

    @Override
    public ResultMessage addGoodsClassification(GoodsClassificationVO vo) throws ExistException {
        return goodsClassification.addGoodsClassification(vo);
    }

    @Override
    public ResultMessage deleteGoodsClassification(GoodsClassificationVO vo) {
        ResultMessage resultMessage = goodsClassification.deleteGoodsClassification(vo);
        return resultMessage;
    }

    @Override
    public ResultMessage updateGoodsClassification(GoodsClassificationVO vo) {
        return goodsClassification.updateGoodsClassification(vo);
    }
}
