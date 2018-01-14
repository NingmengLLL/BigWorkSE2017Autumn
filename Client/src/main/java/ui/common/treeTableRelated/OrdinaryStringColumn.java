package ui.common.treeTableRelated;

import com.jfoenix.controls.JFXTreeTableColumn;
import javafx.beans.property.ReadOnlyStringWrapper;

import java.util.function.Function;

public class OrdinaryStringColumn<T> extends JFXTreeTableColumn<T, String> {
    public OrdinaryStringColumn(String text, double width, Function<T,String> getter) {
        super(text);
        setPrefWidth(width);
        setCellValueFactory(param -> {
            return new ReadOnlyStringWrapper(getter.apply(param.getValue().getValue()));
        });
    }
}