package sample;

import Controller.Controller;
import Model.Exceptions.MyException;
import Model.Expression.*;
import Model.ProgramStatement;
import Model.Statement.*;
import Model.Statement.FileStatement.CloseRFileStatement;
import Model.Statement.FileStatement.OpenRFileStatement;
import Model.Statement.FileStatement.ReadFileStatement;
import Model.Statement.HeapStatement.NewStatement;
import Model.Statement.HeapStatement.WriteHeapStatement;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.ReferenceType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.adt.*;
import Repository.IRepo;
import Repository.Repo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerPrograms {


    @FXML
    private ListView<IStatement> listView;

    @FXML
    private Button switchToMainWindow;


    @FXML
    public void initialize() {

        listView.setItems(getCommands());

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        listView.getSelectionModel().selectIndices(0);

    }

    @FXML
    public void handleButtonSwitchToMainWindow(javafx.event.ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "MainWindow.fxml"
                )
        );
        Stage stage;
        Parent root;
        stage=(Stage) switchToMainWindow.getScene().getWindow();
        root = loader.load();

        ControllerMainWindow controller = loader.getController();
        controller.initData(listView.getSelectionModel().getSelectedItem());

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

    }

    private ObservableList<IStatement> getCommands() {
        IStatement ex1 = new CompoundStatement(
                new VarDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));

//        IStatement ex2 = new CompoundStatement(
//                new DeclarationStatement("a", new BooleanType()),
//                new CompoundStatement(
//                        new DeclarationStatement("b", new IntegerType()),
//                        new CompoundStatement(
//                                new AssignmentStatement(
//                                        "a", new ValueExpression(new BooleanValue(true))),
//                                new CompoundStatement(
//                                        new AssignmentStatement("b", new ArithmeticExpression(1, new VariableExpression("a"), new ValueExpression(new IntegerValue(1)))),
//                                        new PrintStatement(new VariableExpression("b"))
//                                )
//                        )
//                )
//        );

        IStatement ex3 = new CompoundStatement(
                new VarDeclarationStatement("a", new BoolType()),
                new CompoundStatement(
                        new VarDeclarationStatement("v", new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(
                                        new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))
                                )
                        )
                )
        );

        IStatement ex4 = new CompoundStatement(
                new VarDeclarationStatement("varf", new StringType()),
                new CompoundStatement(
                        new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(
                                new OpenRFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(
                                        new VarDeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(
                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(
                                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseRFileStatement(new VariableExpression("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )

                )
        );

        IStatement ex5 = new CompoundStatement(
                new VarDeclarationStatement("a", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("a", new ValueExpression(new IntValue(8))),
                        new CompoundStatement(
                                new VarDeclarationStatement("b", new IntType()),
                                new CompoundStatement(
                                        new AssignmentStatement("b", new ValueExpression(new IntValue(10))),
                                        new CompoundStatement(
                                                new VarDeclarationStatement("min", new IntType()),
                                                new CompoundStatement(
                                                        new IfStatement(new RelationalExpression(new VariableExpression("a"), new VariableExpression("b"),1), new AssignmentStatement("min", new VariableExpression("a")), new AssignmentStatement("min", new VariableExpression("b"))),
                                                        new PrintStatement(new VariableExpression("min"))
                                                )
                                        )
                                )
                        )
                )
        );

        IStatement ex6 = new CompoundStatement(
                new VarDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VarDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(
                                        new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a"))
                                        )
                                )
                        )
                )
        );

        IStatement ex7 = new CompoundStatement(
                new VarDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VarDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(
                                        new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5)),1))
                                        )
                                )
                        )
                )
        );

        IStatement ex8 = new CompoundStatement(
                new VarDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompoundStatement(
                                        new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5)),1))

                                )
                        )
                )
        );

        IStatement ex9 = new CompoundStatement(
                new VarDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VarDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(
                                        new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new NewStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))))
                                        )
                                )
                        )
                )
        );

        IStatement ex10 = new CompoundStatement(
                new VarDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(
                                new WhileStatement(
                                        new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)),5),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)),2))
                                        )),
                                new PrintStatement(new VariableExpression("v"))

                        )
                )
        );

        IStatement ex11 = new CompoundStatement(
                new VarDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new VarDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(
                                        new NewStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
                                                                new CompoundStatement(
                                                                        new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableExpression("v")),
                                                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                                        )
                                                                )

                                                        )
                                                ),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                )
                                        )
                                )
                        )
                )
        );

        IStatement ex12 = null;
        try {
            ex12 = new CompoundStatement(new VarDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(new ForStatement("v", new ValueExpression(new IntValue(0)), new ValueExpression(new IntValue(3)), new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 1),
                                        new ForkStatement(new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 1))))),
                                        new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(10)), 3)))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*Ref int a; new(a,20);
        (for(v=0;v<3;v=v+1) fork(print(v);v=v*rh(a)));
        print(rh(a))*/
        IStatement ex13 = null;
        try {
            ex13 = new CompoundStatement(new VarDeclarationStatement("v",new IntType()),new CompoundStatement(new VarDeclarationStatement("a", new ReferenceType(new IntType())),
                    new CompoundStatement(new NewStatement("a",new ValueExpression(new IntValue(20))),
                    new CompoundStatement(new ForStatement("v", new ValueExpression(new IntValue(0)), new ValueExpression(new IntValue(3)), new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 1),
                                    new ForkStatement(new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ReadHeapExpression(new VariableExpression("a")), 3))))),
                                    new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        IStatement ex14 = new CompoundStatement(new VarDeclarationStatement("v1", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v1", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VarDeclarationStatement("v2", new ReferenceType(new IntType())),
                                new CompoundStatement(new NewStatement("v2", new ValueExpression(new IntValue(30))),
                                        new CompoundStatement(new VarDeclarationStatement("x", new IntType()),
                                                new CompoundStatement(new VarDeclarationStatement("q", new IntType()),
                                                new CompoundStatement(new NewLockStatement("x"),
                                                        new CompoundStatement(new ForkStatement(
                                                                new CompoundStatement(new ForkStatement(
                                                                        new CompoundStatement(new LockStatement("x"),
                                                                                new CompoundStatement(new WriteHeapStatement("v1",
                                                                                        new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(1)), 2)),
                                                                                        new UnlockStatement("x")))),
                                                                        new CompoundStatement(new LockStatement("x"),
                                                                                new CompoundStatement(new WriteHeapStatement("v1",
                                                                                        new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)), 3)),
                                                                                        new UnlockStatement("x"))))),
                                                                new CompoundStatement(
                                                                        new CompoundStatement(
                                                                                new CompoundStatement(new NewLockStatement("q"),
                                                                                new ForkStatement(
                                                                                new CompoundStatement(
                                                                                        new ForkStatement(
                                                                                                new CompoundStatement(new LockStatement("q"),
                                                                                                        new CompoundStatement(new WriteHeapStatement("v2",
                                                                                                new ArithmeticExpression(new ReadHeapExpression(new VariableExpression(
                                                                                                        "v2")), new ValueExpression(new IntValue(5)), 1)
                                                                                        ), new UnlockStatement("q")))),
                                                                                        new CompoundStatement( new LockStatement("q"),new CompoundStatement(new WriteHeapStatement("v2",
                                                                                        new ArithmeticExpression(new ReadHeapExpression(new VariableExpression(
                                                                                                "v2")), new ValueExpression(new IntValue(10)), 3)
                                                                                ), new UnlockStatement("q")))) )),
                                                                                new CompoundStatement(
                                                                                        new CompoundStatement(
                                                                                                new NopStatement(), new CompoundStatement(new NopStatement(),
                                                                                                new CompoundStatement(new NopStatement(), new NopStatement()))),
                                                                                new CompoundStatement(new LockStatement("x"),
                                                                                new CompoundStatement(
                                                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))), new UnlockStatement("x"))))),
                                                                        new CompoundStatement( new LockStatement("q"),
                                                                        new CompoundStatement(
                                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("v2"))), new UnlockStatement("q"))))))))))));



        ObservableList<IStatement> commands = FXCollections.observableArrayList(ex1, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12, ex13, ex14);
        return commands;
    }



}
