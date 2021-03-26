
import Model.Statement.FileStatement.CloseRFileStatement;
import Model.Statement.FileStatement.OpenRFileStatement;
import Model.Statement.FileStatement.ReadFileStatement;
import Model.Statement.HeapStatement.NewStatement;
import Model.Statement.HeapStatement.WriteHeapStatement;
import Model.adt.*;
import Controller.*;
import Model.Exceptions.*;
import Model.Expression.*;
import Model.ProgramStatement;
import Repository.*;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.ReferenceType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import View.Command.ExitCommand;
import View.Command.RunExampleCommand;
import View.TextMenu;


public class Interpreter {

    public static void main(String[] args){

        /*System.out.println("Give the path to the file where the data will be printed --> ");
        Scanner scanner = new Scanner(System.in);
        String logFilePath = scanner.nextLine();

        IFileTable<String, BufferedReader> fileTable = new MyFileTable<String,BufferedReader>();
        try {
            File tmpDir = new File(logFilePath);
            if(!tmpDir.exists()){
                Path path = Paths.get(logFilePath);
                Path p= Files.createFile(path);
            }
            BufferedReader br = new BufferedReader(new FileReader(logFilePath));
            fileTable.add(logFilePath,br);
        }
        catch (IOException e) {
            e.printStackTrace();
        }*/


        IStatement ex1 = new CompoundStatement(new VarDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        ProgramStatement p1 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(), ex1);
        IRepo my_repo1 = new Repo("date.txt");
        my_repo1.add(p1);
        Controller controller1 = new Controller(my_repo1);


        IStatement ex2 = new CompoundStatement(new VarDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VarDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a",
                                new ArithmeticExpression(new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression(new ValueExpression(new IntValue(3)),
                                                new ValueExpression(new IntValue(5)),3),1)),
                                new CompoundStatement(new AssignmentStatement("b",
                                        new ArithmeticExpression(new VariableExpression("a"),
                                                new ValueExpression(new IntValue(1)),1)),
                                        new PrintStatement(new VariableExpression("b"))))));
        ProgramStatement p2 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(), ex2);
        IRepo my_repo2 = new Repo("date.txt");
        my_repo2.add(p2);
        Controller controller2 = new Controller(my_repo2);


        IStatement ex3 = new CompoundStatement(new VarDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VarDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BoolValue(false))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
        ProgramStatement p3 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(), ex3);
        IRepo my_repo3 = new Repo("date.txt");
        my_repo3.add(p3);
        Controller controller3 = new Controller(my_repo3);


        IStatement ex4 = new CompoundStatement(new VarDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("a"))));
        ProgramStatement p4 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(), ex4);
        IRepo my_repo4 = new Repo("date.txt");
        my_repo4.add(p4);
        Controller controller4 = new Controller(my_repo4);


        IStatement ex5 = new CompoundStatement(new VarDeclarationStatement("v", new IntType()),
                new AssignmentStatement("v", new ArithmeticExpression
                        (new ValueExpression(new IntValue(2)), new ValueExpression(new IntValue(0)),4)));
        ProgramStatement p5 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(), ex5);
        IRepo my_repo5 = new Repo("date.txt");
        my_repo5.add(p5);
        Controller controller5 = new Controller(my_repo5);


        IStatement ex6 = new CompoundStatement(new VarDeclarationStatement("a", new IntType()), new AssignmentStatement
                ("a",new ValueExpression(new BoolValue(true))));
        ProgramStatement p6 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(), ex6);
        IRepo my_repo6 = new Repo("date.txt");
        my_repo6.add(p6);
        Controller controller6 = new Controller(my_repo6);


        IStatement ex7 = new CompoundStatement(new VarDeclarationStatement("a", new BoolType()),
                new IfStatement(new RelationalExpression(new ValueExpression(new IntValue(4)),
                        new ValueExpression(new IntValue(5)),2),
                        new AssignmentStatement("a",new ValueExpression(new BoolValue(true))),
                        new AssignmentStatement("a",new ValueExpression(new BoolValue(false)))));
        ProgramStatement p7 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(),  new MyLockTable<>(),ex7);
        IRepo my_repo7 = new Repo("date.txt");
        my_repo7.add(p7);
        Controller controller7 = new Controller(my_repo7);


        IStatement ex8 = new CompoundStatement(new VarDeclarationStatement("varf",new StringType()),
                new CompoundStatement(new AssignmentStatement("varf",new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenRFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(new VarDeclarationStatement("varc",new IntType()),
                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"),"varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"),"varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseRFileStatement(new VariableExpression("varf"))))))))));
        ProgramStatement p8 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(),  new MyLockTable<>(),ex8);
        IRepo my_repo8 = new Repo("date.txt");
        my_repo8.add(p8);
        Controller controller8 = new Controller(my_repo8);


        IStatement ex9 = new CompoundStatement(new VarDeclarationStatement("varf",new StringType()),
                new CompoundStatement(new AssignmentStatement("varf",new ValueExpression(new StringValue("date.txt"))),
                        new OpenRFileStatement(new VariableExpression("varf"))));
        ProgramStatement p9 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(), ex9);
        IRepo my_repo9 = new Repo("date.txt");
        my_repo9.add(p9);
        Controller controller9 = new Controller(my_repo9);


        IStatement ex10 = new CompoundStatement(new VarDeclarationStatement("a", new BoolType()),
                new IfStatement(new RelationalExpression(new ValueExpression(new IntValue(4)),
                        new ValueExpression(new BoolValue(false)),3),
                        new AssignmentStatement("a",new ValueExpression(new BoolValue(true))),
                        new AssignmentStatement("a",new ValueExpression(new BoolValue(false)))));
        try {
            ex10.typecheck(new MyDictionary<>());
            System.out.println("P10 type-checked");
        } catch (MyException e) {
            System.out.println("P10 " + e.getMessage());
        }
        ProgramStatement p10 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(), ex10);
        IRepo my_repo10 = new Repo("log10.txt");
        my_repo10.add(p10);
        Controller controller10 = new Controller(my_repo10);

        IStatement ex11 = new CompoundStatement(
                new VarDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VarDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(
                                        new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a")))))));
        try {
            ex11.typecheck(new MyDictionary<>());
            System.out.println("P11 type-checked");
        } catch (MyException e) {
            e.printStackTrace();
        }
        ProgramStatement p11 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(), ex11);
        IRepo my_repo11 = new Repo("log11.txt");
        my_repo11.add(p11);
        Controller controller11 = new Controller(my_repo11);


        IStatement ex12 = new CompoundStatement(
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
        try {
            ex12.typecheck(new MyDictionary<>());
            System.out.println("P12 type-checked");
        } catch (MyException e) {
            e.printStackTrace();
        }
        ProgramStatement p12 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(), ex12);
        IRepo my_repo12 = new Repo("log12.txt");
        my_repo12.add(p12);
        Controller controller12 = new Controller(my_repo12);


        IStatement ex13 = new CompoundStatement(new VarDeclarationStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v",new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VarDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(
                                        new NewStatement("a",new VariableExpression("v")),
                                        new CompoundStatement(
                                                new NewStatement("v",new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))))
                                        )
                                )
                        )
                ));
        try {
            ex13.typecheck(new MyDictionary<>());
            System.out.println("P13 type-checked");
        } catch (MyException e) {
            e.printStackTrace();
        }
        ProgramStatement p13 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(), ex13);
        IRepo my_repo13 = new Repo("log13.txt");
        my_repo13.add(p13);
        Controller controller13 = new Controller(my_repo13);


        IStatement ex14 = new CompoundStatement(new VarDeclarationStatement("v",new IntType()),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"),
                                new ValueExpression(new IntValue(0)),5),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new AssignmentStatement("v",new ArithmeticExpression(new VariableExpression("v"),
                                                new ValueExpression(new IntValue(1)),2)))
                        ), new PrintStatement(new VariableExpression("v")))));
        try {
            ex14.typecheck(new MyDictionary<>());
            System.out.println("P14 type-checked");
        } catch (MyException e) {
            e.printStackTrace();
        }
        ProgramStatement p14 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(),  new MyLockTable<>(),ex14);
        IRepo my_repo14 = new Repo("log14.txt");
        my_repo14.add(p14);
        Controller controller14 = new Controller(my_repo14);


        IStatement ex16 = new CompoundStatement(new WriteHeapStatement("a",new ValueExpression(new IntValue(30))),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))));

        IStatement ex15 = new CompoundStatement(new VarDeclarationStatement("v",new IntType()),
                new CompoundStatement(new VarDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new NewStatement("a",new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(ex16),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));
        try {
            ex15.typecheck(new MyDictionary<>());
            System.out.println("P15 type-checked");
        } catch (MyException e) {
            e.printStackTrace();
        }
        ProgramStatement p15 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(),  new MyLockTable<>(),ex15);
        IRepo my_repo15 = new Repo("log15.txt");
        my_repo15.add(p15);
        Controller controller15 = new Controller(my_repo15);


        IStatement ex17 = new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))));

        IStatement ex18 = new CompoundStatement(new WriteHeapStatement("a",new ValueExpression(new IntValue(30))),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
                        new ForkStatement(ex17)));

        IStatement ex19 = new CompoundStatement(new VarDeclarationStatement("v",new IntType()),
                new CompoundStatement(new VarDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new NewStatement("a",new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(ex18),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));
        try {
            ex19.typecheck(new MyDictionary<>());
            System.out.println("P16 type-checked");
        } catch (MyException e) {
            e.printStackTrace();
        }
        ProgramStatement p16 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(),ex19);
        IRepo my_repo16 = new Repo("log16.txt");
        my_repo16.add(p16);
        Controller controller16 = new Controller(my_repo16);

        IStatement ex20 = null;
        try {
            ex20 = new CompoundStatement(new VarDeclarationStatement("v", new IntType()),
                    new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(20))),
                            new CompoundStatement(new ForStatement("v", new ValueExpression(new IntValue(0)), new ValueExpression(new IntValue(3)), new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 1),
                                    new ForkStatement(new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 1))))),
                                    new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(10)), 3)))));
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            ex20.typecheck(new MyDictionary<>());
            System.out.println("P17 type-checked");
        } catch (MyException e) {
            e.printStackTrace();
        }
        ProgramStatement p17 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(), ex20);
        IRepo my_repo17 = new Repo("log17.txt");
        my_repo17.add(p17);
        Controller controller17 = new Controller(my_repo17);

        IStatement ex21 = new CompoundStatement(
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

        try {
            ex21.typecheck(new MyDictionary<>());
            System.out.println("P18 type-checked");
        } catch (MyException e) {
            e.printStackTrace();
        }
        ProgramStatement p18 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(), new MyLockTable<>(), ex21);
        IRepo my_repo18 = new Repo("date.txt");
        my_repo18.add(p18);
        Controller controller18 = new Controller(my_repo18);

        IStatement ex22 = null;
        try {
            ex22 = new CompoundStatement(new VarDeclarationStatement("v",new IntType()),new CompoundStatement(new VarDeclarationStatement("a", new ReferenceType(new IntType())),
                    new CompoundStatement(new NewStatement("a",new ValueExpression(new IntValue(20))),
                            new CompoundStatement(new ForStatement("v", new ValueExpression(new IntValue(0)), new ValueExpression(new IntValue(3)), new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), 1),
                                    new ForkStatement(new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ReadHeapExpression(new VariableExpression("a")), 3))))),
                                    new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ex22.typecheck(new MyDictionary<>());
            System.out.println("P19 type-checked");
        } catch (MyException e) {
            e.printStackTrace();
        }
        ProgramStatement p19 = new ProgramStatement(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyFileTable<>(), new MyHeap(),  new MyLockTable<>(),ex22);
        IRepo my_repo19 = new Repo("date.txt");
        my_repo19.add(p19);
        Controller controller19 = new Controller(my_repo19);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", p1.presentationInMenu(), controller1));
        menu.addCommand(new RunExampleCommand("2", p2.presentationInMenu(), controller2));
        menu.addCommand(new RunExampleCommand("3", p3.presentationInMenu(), controller3));
        menu.addCommand(new RunExampleCommand("4", p4.presentationInMenu(), controller4));
        menu.addCommand(new RunExampleCommand("5", p5.presentationInMenu(), controller5));
        menu.addCommand(new RunExampleCommand("6", p6.presentationInMenu(), controller6));
        menu.addCommand(new RunExampleCommand("7", p7.presentationInMenu(), controller7));
        menu.addCommand(new RunExampleCommand("8", p8.presentationInMenu(), controller8));
        menu.addCommand(new RunExampleCommand("9", p9.presentationInMenu(), controller9));
        menu.addCommand(new RunExampleCommand("10", p10.presentationInMenu(), controller10));
        menu.addCommand(new RunExampleCommand("11", p11.presentationInMenu(), controller11));
        menu.addCommand(new RunExampleCommand("12", p12.presentationInMenu(), controller12));
        menu.addCommand(new RunExampleCommand("13", p13.presentationInMenu(), controller13));
        menu.addCommand(new RunExampleCommand("14", p14.presentationInMenu(), controller14));
        menu.addCommand(new RunExampleCommand("15", p15.presentationInMenu(), controller15));
        menu.addCommand(new RunExampleCommand("16", p16.presentationInMenu(), controller16));
        menu.addCommand(new RunExampleCommand("17", p17.presentationInMenu(), controller17));
        menu.addCommand(new RunExampleCommand("18", p18.presentationInMenu(), controller18));
        menu.addCommand(new RunExampleCommand("19", p18.presentationInMenu(), controller19));


        try {
            menu.show();
        }
        catch(Exception exc){
            System.out.println(exc.getMessage());
        }


    }

}

