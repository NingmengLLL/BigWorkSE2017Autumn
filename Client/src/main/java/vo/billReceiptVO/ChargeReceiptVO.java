package vo.billReceiptVO;

import blService.checkblService.ReceiptblService;
import javafx.scene.Node;
import po.receiptPO.ChargeBillReceiptPO;
import po.receiptPO.PaymentBillReceiptPO;
import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public class ChargeReceiptVO extends ReceiptVO{


    private int clientID;
    private List<TransferItemVO> transferList;
    private double sum;
    private boolean isSell;

    public ChargeReceiptVO(){

    }

    public ChargeReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int clientID, List<TransferItemVO> transferList, double sum,boolean isSell) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);

        this.clientID =clientID;
        this.transferList = transferList;
        this.sum = sum;
        this.isSell = isSell;
    }

    @Override
    protected String getCodeName() {
        return null;
    }

    public ChargeBillReceiptPO toPO(){
        return new ChargeBillReceiptPO();
    }

    @Override
    public ReceiptblService getService() throws RemoteException, NotBoundException, MalformedURLException {
        return null;
    }

    @Override
    public Node getDetailPane() {
        return null;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public List<TransferItemVO> getTransferList() {
        return transferList;
    }

    public void setTransferList(List<TransferItemVO> transferList) {
        this.transferList = transferList;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isSell() {
        return isSell;
    }

    public void setSell(boolean sell) {
        isSell = sell;
    }
}