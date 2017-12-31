package blServiceStub.billblservice_Stub;

import blService.billblService.PaymentBillReceiptblService;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.billReceiptVO.CashBillReceiptVO;
import vo.billReceiptVO.PaymentBillReceiptVO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentblService_Stub implements PaymentBillReceiptblService{

    public int getDayId() throws RemoteException {
        return 0;
    }

    public ResultMessage insert(PaymentBillReceiptVO receiptVO) throws RemoteException{
        return ResultMessage.SUCCESS;
    }

    public ResultMessage update(PaymentBillReceiptVO receiptVO) throws RemoteException{
        return ResultMessage.SUCCESS;
    }

    public ResultMessage delete(PaymentBillReceiptVO receiptVO) throws RemoteException{
        return ResultMessage.SUCCESS;
    }

    public ArrayList<PaymentBillReceiptVO> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException{
        ArrayList<PaymentBillReceiptVO> list = new ArrayList<>();
        LocalDateTime time = LocalDateTime.MIN;
        //String dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState,int clientID, TransferItemVO[] transferList, double sum
        list.add(new PaymentBillReceiptVO("1111",111,time,time, ReceiptState.APPROVED,111,null,1111));
        list.add(new PaymentBillReceiptVO("1112",111,time,time, ReceiptState.APPROVED,111,null,1111));
        list.add(new PaymentBillReceiptVO("1113",111,time,time, ReceiptState.APPROVED,111,null,1111));
        list.add(new PaymentBillReceiptVO("1114",111,time,time, ReceiptState.APPROVED,111,null,1111));
        list.add(new PaymentBillReceiptVO("1115",111,time,time, ReceiptState.APPROVED,111,null,1111));
        list.add(new PaymentBillReceiptVO("1116",111,time,time, ReceiptState.APPROVED,111,null,1111));
        list.add(new PaymentBillReceiptVO("1117",111,time,time, ReceiptState.APPROVED,111,null,1111));
        list.add(new PaymentBillReceiptVO("1118",111,time,time, ReceiptState.APPROVED,111,null,1111));
        list.add(new PaymentBillReceiptVO("1119",111,time,time, ReceiptState.APPROVED,111,null,1111));
        list.add(new PaymentBillReceiptVO("1120",111,time,time, ReceiptState.APPROVED,111,null,1111));
        list.add(new PaymentBillReceiptVO("1121",111,time,time, ReceiptState.APPROVED,111,null,1111));
        return list;
    }


    public ArrayList<PaymentBillReceiptVO> search(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException{
        return null;
    }
}