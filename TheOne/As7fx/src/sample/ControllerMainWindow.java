package sample;

import Controller.Controller;
import Model.Exceptions.MyException;
import Model.Statement.IStatement;
import Model.Type.IType;
import Model.Value.IValue;
import Model.adt.*;
import Model.ProgramStatement;
import Repository.IRepo;
import Repository.Repo;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ControllerMainWindow {

    private Controller controller;
    private IRepo repository;

    @FXML
    private TextField numberOfProgramStates;

    @FXML
    private TableView<TableRow> HeapTable;

    @FXML
    private ListView out;

    @FXML
    private ListView fileTable;

    @FXML
    private ListView programStateIdentifiers;

    @FXML
    private TableView<TableRow> symbolTable;

    @FXML
    private ListView executionStack;

    @FXML
    private Button switchToPrograms;

    @FXML
    private Button runOneStep;

    private ProgramStatement program;


    @FXML
    public void handleButtonSwitchToPrograms(javafx.event.ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) switchToPrograms.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Programs.fxml"));

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    public void handleButtonRunOneStep(javafx.event.ActionEvent event) throws IOException, InterruptedException, MyException {

        try {
            controller.allStepGUI();
        }catch(Exception exc){
            Alert alert = new Alert(Alert.AlertType.ERROR, exc.getMessage(), ButtonType.OK);
            alert.showAndWait();
            this.handleButtonSwitchToPrograms(null);
        }

        int index;
        if (programStateIdentifiers.getSelectionModel().getSelectedIndex()<0)
            index = 1;
        else
            index = (int) programStateIdentifiers.getSelectionModel().getSelectedItem();
        ProgramStatement currentProgram=repository.getProgramStatementById(index);

        ArrayList<ArrayList<String>> heapTableTemp = new ArrayList<>();
        ArrayList<String> outputTemp = new ArrayList<>();
        ArrayList<ArrayList< String >>fileTableTemp = new ArrayList<>();
        ArrayList<String> fileTableTemp2 = new ArrayList<>();
        ArrayList<Integer> programStateIdentifierTemp = new ArrayList<>();
        ArrayList<ArrayList<String>> symbolTableTemp = new ArrayList<>();
        ArrayList<String> executionStackTemp = new ArrayList<>();

        if(currentProgram!=null) {
            heapTableTemp.addAll(currentProgram.getHeap().getElementsStrings());
            outputTemp.addAll(currentProgram.getOut().getElementsStrings());
            fileTableTemp.addAll(currentProgram.getFileTable().getElementsStrings());
            symbolTableTemp.addAll(currentProgram.getSymTable().getElementsStrings());
            executionStackTemp.addAll(currentProgram.getExeStack().getElementsStrings());
        }
        else{
            heapTableTemp.addAll(program.getHeap().getElementsStrings());
            outputTemp.addAll(program.getOut().getElementsStrings());
            fileTableTemp.addAll(program.getFileTable().getElementsStrings());
            symbolTableTemp.addAll(program.getSymTable().getElementsStrings());
            executionStackTemp.addAll(program.getExeStack().getElementsStrings());
        }

        programStateIdentifiers.getItems().clear();

        for (ProgramStatement state:repository.getAll()) {
            programStateIdentifierTemp.add(state.getId());
        }

        ArrayList<TableRow> row = new ArrayList<>();
        for(var zz:heapTableTemp){
                row.add(new TableRow(zz.get(0), zz.get(1)));
        }

        ArrayList<TableRow> row2 = new ArrayList<>();
        for(var zz:symbolTableTemp){
                row2.add(new TableRow(zz.get(0), zz.get(1)));
        }

        for(var z:fileTableTemp){
            fileTableTemp2.add(z.get(0));
        }

        HeapTable.setItems(FXCollections.observableList(row));
        out.setItems(FXCollections.observableList(outputTemp));
        fileTable.setItems(FXCollections.observableList(fileTableTemp2));
        programStateIdentifiers.setItems(FXCollections.observableList(programStateIdentifierTemp));
        symbolTable.setItems(FXCollections.observableList(row2));
        executionStack.setItems(FXCollections.observableList(executionStackTemp));
        numberOfProgramStates.setText(Integer.toString(repository.getAll().size()));

    }


    public void initData(IStatement selectedItem) {
        try {
            selectedItem.typecheck(new MyDictionary<String, IType>());
        } catch (MyException e) {
            e.printStackTrace();
        }
        program = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(),selectedItem);
        repository = new Repo("log11.txt");
        repository.add(program);
        controller = new Controller(repository);
        numberOfProgramStates.setText(Integer.toString(repository.getAll().size()));
        controller.clearFile();

    }
}
