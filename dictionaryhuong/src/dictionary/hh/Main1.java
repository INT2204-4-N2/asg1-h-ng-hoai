package dictionary.hh;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class Main1 implements Initializable {
    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    @FXML
    private TextArea textArea;
    @FXML
    private TableView<Word> tableView;
    @FXML
    private TableColumn<Word, String> colTarget;
    @FXML
    private TableColumn<Word, String> colExplain;

    private ObservableList<Word> data;

    private IntegerProperty index = new SimpleIntegerProperty();

    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    Dictionary dictionary = new Dictionary();

    public void phuongThucfx(ActionEvent e) {
        String t = text1.getText().toLowerCase();

        if (dictionaryManagement.dictionaryLookup(t) != null) {
            data.add(dictionaryManagement.dictionaryLookup(t));
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dictionary");
            alert.setHeaderText(null);
            alert.setContentText("Khong tim thay tu!!!");
            alert.showAndWait();
        }
    }

    public void xoaFx(ActionEvent e) {
        text1.clear();
        data.clear();
    }

    public void traGanDung(ActionEvent e) {
        String t = text1.getText().toLowerCase();
        dictionaryManagement.traDictionary(t);
        for (Word arr : dictionaryManagement.list1) {
            data.addAll(arr);
        }
    }

    public void xoaTufx(ActionEvent e) {
        String tu = text2.getText().toLowerCase();
        String tv = textArea.getText().toLowerCase();
        if (dictionaryManagement.xoaTu(tu, tv) != 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dictionary");
            alert.setHeaderText(null);
            alert.setContentText("Xoa tu thanh cong...");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dictionary");
            alert.setHeaderText(null);
            alert.setContentText("Khong tim thay tu: " + tu);
            alert.showAndWait();
        }
    }

    public void themTuMoi(ActionEvent e){
        String english = text2.getText().toLowerCase();
        String vietnam = textArea.getText().toLowerCase();
        if (dictionaryManagement.insertFromCommandline(english,vietnam)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dictionary");
            alert.setHeaderText(null);
            alert.setContentText("Them tu thanh cong...");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dictionary");
            alert.setHeaderText(null);
            alert.setContentText("Da co tu nay trong tu dien!!!");
            alert.showAndWait();
        }
        text2.clear();
        textArea.clear();

    }

    public void suaTufx(ActionEvent e){
        String english = text2.getText().toLowerCase();
        String vietnam = textArea.getText().toLowerCase();
        if (!dictionaryManagement.suaTu(english,vietnam)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dictionary");
            alert.setHeaderText(null);
            alert.setContentText("Sua tu thanh cong...");
            alert.showAndWait();
            text2.clear();
            textArea.clear();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dictionary");
            alert.setHeaderText(null);
            alert.setContentText("Khong tim thay tu!!!");
            alert.showAndWait();
            text2.clear();
            textArea.clear();
        }
    }

    public void inDS(ActionEvent e){
        dictionaryManagement.file();
        for (Word arr :dictionaryManagement.list2){
            data.add(arr);
        }
    }

    public void Delete(ActionEvent e){
        int i = index.get();
        if (i > -1){
            data.remove(i);
            tableView.getSelectionModel().clearSelection();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionaryManagement.insertFromFile();
        data = FXCollections.observableArrayList(dictionary.list);
        colTarget.setCellValueFactory(new PropertyValueFactory<Word , String>("target"));
        colExplain.setCellValueFactory(new PropertyValueFactory<Word,String>("explain"));
        tableView.setItems(data);

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Word>() {
            @Override
            public void changed(ObservableValue<? extends Word> observable, Word oldValue, Word newValue) {
                index.set(data.indexOf(newValue));
                System.out.println("Index is: " + data.indexOf(newValue));

            }
        });
    }
}



