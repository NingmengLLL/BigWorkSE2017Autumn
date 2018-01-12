package ui.accountantui;

import blService.accountblService.AccountblService;
import blService.blServiceFactory.ServiceFactory_Stub;
import blService.memberblService.MemberblService;
import businesslogic.accountbl.Accountbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import ui.userui.usermanagerui.UserDetailPane;
import ui.util.*;
import util.MemberCategory;
import vo.AccountListVO;
import vo.MemberVO;
import vo.UserVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.function.Predicate;

import static ui.util.ValidatorDecorator.DoubleValid;
import static ui.util.ValidatorDecorator.RequireValid;

public class AccountDetailPane1 extends ReceiptDetailPane<AccountListVO>{



    private AccountblService accountblService;

    private AccountListVO accountListVO;

    @FXML
    JFXTextField name;

    @FXML
    JFXTextField id;

    @FXML
    JFXTextField balance;


    @FXML
    MaterialDesignIconView pen;

    SimpleBooleanProperty modifyState = new SimpleBooleanProperty(false);

    boolean isAdd = false;

    public AccountDetailPane1(AccountListVO accountListVO){
        this(false);
        this.accountListVO = accountListVO;
        delete.setVisible(true);
        modify.setVisible(true);
        save.setText("Save");
        this.modifyState.bind(modify.modifyProperty());
        this.modifyState.addListener((b, o, n) -> {
            if (!n) {
                if (valid()) {
                    modify.modifyProperty().set(false);
                } else {
                    modify.modifyProperty().set(true);
                }
            }
        });

        name.disableProperty().bind(modifyState.not());
        balance.disableProperty().bind(modifyState.not());


    }


    public AccountDetailPane1(boolean isAdd) {
        super("/accountantui/accountdetail.fxml");
        try{
            this.accountblService = new Accountbl();

        }catch (Exception e){
            e.printStackTrace();
        }
        this.isAdd = isAdd;

        id.setText("auto");
        id.setDisable(true);
        delete.setVisible(false);
        DoubleValid(balance);

        updateState.set(false);
        if (isAdd) {
            updateState.set(true);
            switchPane(true);
        }


    }

    @Override
    public void delete() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Delete", "sabi", "Yes", "No");
        doubleButtonDialog.setButtonOne(() -> {
        });
        doubleButtonDialog.setButtonTwo(() -> {
            setBack();
        });
        doubleButtonDialog.show();
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try {
            if (!updateState.get()) {
                DoubleButtonDialog buttonDialog =
                        new DoubleButtonDialog(mainpane, "Wrong", "sabi", "Last", "Ret");
                buttonDialog.setButtonTwo(() -> boardController.Ret());
                buttonDialog.setButtonTwo(() -> refresh(false));
                Predicate<Integer> p = (i) -> {
                    if((vo=accountListVO)!=null) {
                        System.out.println("11111");
                        return true;
                    }
                    else
                        return false;
                };
                GetTask task =
                        new GetTask(() -> {
                            id.setText(String.valueOf(vo.getID()));
                            name.setText(vo.getName());
                            balance.setText(String.valueOf(vo.getBalance()));
                            switchPane(toSwitch);
                        }, buttonDialog, p);

                new Thread(task).start();
            } else {
                switchPane(toSwitch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save() {
        if (valid()) {
            modify.modifyProperty().set(false);
            DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Pending?", "sabi", "Yes", "No");
            doubleButtonDialog.setButtonTwo(() -> {
            });
            doubleButtonDialog.setButtonOne(() -> {

                try {
                    if(isAdd){
                        accountblService.add(new AccountListVO(
                                0,
                                name.getText(),
                                Double.parseDouble(balance.getText())
                        ));
                    }else{
                        accountblService.update(new AccountListVO(
                                Integer.parseInt(id.getText()),
                                name.getText(),
                                Double.parseDouble(balance.getText())
                        ));
                    }

                    boardController.switchTo(this);
                    OneButtonDialog oneButtonDialog = new OneButtonDialog(mainpane, "???", "Success", "Accept");
                    oneButtonDialog.setButtonOne(() -> {
                    });
                    oneButtonDialog.show();
                    //setBack();
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            });
            doubleButtonDialog.show();

        } else {
            OneButtonDialog oneButtonDialog = new OneButtonDialog(mainpane, "???", "Stupid!", "Accept");
            oneButtonDialog.setButtonOne(() -> {
            });
            oneButtonDialog.show();
        }
    }


    @Override
    public void savePendingReceipt() {
    }

    @Override
    public void saveDraftReceipt() {
    }


    @Override
    public boolean valid() {
        if (!name.getText().equals("") && !id.getText().equals("")&&!balance.getText().equals(""))
            return true;
        return false;
    }

}
