package ui.managerui.businessProgressui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import ui.managerui.common.treeTableRelated.ChooseColumn;
import ui.managerui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.managerui.common.treeTableRelated.SearchableStringColumn;
import ui.util.ButtonCell;
import ui.util.NodeAnimation;
import ui.util.NodeHolder;
import util.ReceiptState;
import vo.promotionVO.PromotionVO;
import vo.receiptVO.ReceiptVO;

import java.util.List;
import java.util.Set;

public class BusinessProgressTable extends MyTreeTableBorderPane<ReceiptVO> {

    public BusinessProgressTable(Set<ReceiptVO> chosenItems, StringProperty keywordProperty, List<ReceiptVO> keywordFilterList) {
        JFXTreeTableColumn<ReceiptVO, Boolean> choose = new ChooseColumn<>(chosenItems);
        JFXTreeTableColumn<ReceiptVO, String> idColumn = new SearchableStringColumn<>("编号", 200, keywordProperty, ReceiptVO::getId);
        JFXTreeTableColumn<ReceiptVO, String> lastModifiedTimeColumn = new SearchableStringColumn<>("通过时间", 150, keywordProperty, p -> p.getLastModifiedTime().toLocalDate().toString());
        JFXTreeTableColumn<ReceiptVO, String> operatorColumn = new SearchableStringColumn<>("操作员", 100, keywordProperty, p -> String.valueOf(p.getOperatorId()));

        myTreeTable.getColumns().addAll(choose, idColumn, lastModifiedTimeColumn, operatorColumn);
    }

    @Override
    protected void clickTwiceAftermath(JFXTreeTableRow<ReceiptVO> row) {
        row.getTreeItem().getValue().getDetailPane();
    }
}