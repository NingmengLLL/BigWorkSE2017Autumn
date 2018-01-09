package vo.receiptVO;

import blService.checkblService.CheckInfo;
import blService.checkblService.ReceiptblService;
import javafx.beans.property.*;
import javafx.scene.Node;
import po.ReceiptGoodsItemPO;
import po.receiptPO.ReceiptPO;
import po.receiptPO.StockPurReceiptPO;
import po.receiptPO.StockReceiptPO;
import util.ReceiptState;
import vo.ListGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class StockReceiptVO extends ReceiptVO {

    protected int memberId;
    protected String memberName;
    protected String stockName;
    protected double sum;
    protected ArrayList<ListGoodsItemVO> items = new ArrayList<>();
    protected String comment;

    public StockReceiptVO() {
    }
    public StockReceiptVO(StockReceiptPO stockReceiptPO){
        super(stockReceiptPO);
        this.memberId = stockReceiptPO.getMemberid();
        this.stockName =stockReceiptPO.getStockName();
        this.sum =stockReceiptPO.getOriginalSum();
        this.items = toGoodsList(stockReceiptPO.getGoodsList());
        this.comment = stockReceiptPO.getComment();
    }

    public StockReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String memberName, String stockName, double sum, ArrayList<ListGoodsItemVO> items, String comment) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.memberId = memberId;
        this.memberName =memberName;
        this.stockName = stockName;
        this.sum = sum;
        this.items = items;
        this.comment = comment;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public ArrayList<ListGoodsItemVO> getItems() {
        return items;
    }

    public void setItems(ArrayList<ListGoodsItemVO> items) {
        this.items = items;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public <T extends StockReceiptPO> T toStockReceiptPO(Class<T> receiptClass) {
        T result = toReceiptPO(receiptClass);
        result.setMemberid(memberId);
        result.setStockName(stockName);
        result.setOriginalSum(sum);
        result.setGoodsList(toGoodsArray(items));
        result.setComment(comment);
        return result;
    }


    public ReceiptGoodsItemPO[] toGoodsArray(ArrayList<ListGoodsItemVO> items){
        List<ReceiptGoodsItemPO> receiptGoodsItemPOs = items.stream().map(t->t.toPo()).collect(Collectors.toList());
        ReceiptGoodsItemPO[] goodsItemPOs = (ReceiptGoodsItemPO[])receiptGoodsItemPOs.toArray();
        return goodsItemPOs;
    }

    public ArrayList<ListGoodsItemVO> toGoodsList(ReceiptGoodsItemPO[] array){
        ArrayList<ListGoodsItemVO> list = new ArrayList<>();
        if (array != null) {
            for (ReceiptGoodsItemPO receiptGoodsItemPO : array) {
                list.add(new ListGoodsItemVO(receiptGoodsItemPO));
            }
        }
        return list;
    }

//    public StockReceiptListVO toListVO(){
//        StockReceiptListVO stockReceiptListVO = new StockReceiptListVO();
//        stockReceiptListVO.setId(getId());
//        stockReceiptListVO.setMemberName(getMemberName());
//        stockReceiptListVO.setReceiptState(getReceiptState());
//        stockReceiptListVO.setPur(id.contains("JHD"));
//        stockReceiptListVO.setSum(getSum());
//        stockReceiptListVO.setCreateTime(getCreateTime());
//        return stockReceiptListVO;
//    }

}
