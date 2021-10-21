import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Main extends Application{

	private TextField txtA;
	private TextField txtB;
	private TextField txtC;
	private TextField txtD;
	private TextField txtResA;
	private TextField txtResB;
	
	private StringProperty cantA = new SimpleStringProperty();
	private StringProperty cantB = new SimpleStringProperty();
	private StringProperty cantC = new SimpleStringProperty();
	private StringProperty cantD = new SimpleStringProperty();
	
	private StringProperty seleccionado = new SimpleStringProperty();

	private DoubleProperty numA = new SimpleDoubleProperty(0);
	private DoubleProperty numB = new SimpleDoubleProperty(0);
	private DoubleProperty numC = new SimpleDoubleProperty(0);
	private DoubleProperty numD = new SimpleDoubleProperty(0);
	private DoubleProperty numResA = new SimpleDoubleProperty(0);
	private DoubleProperty numResB = new SimpleDoubleProperty(0);
	
	private ComboBox<String> operacion;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		txtA = new TextField();
		txtA.setMaxWidth(50);
		
		txtB = new TextField();
		txtB.setMaxWidth(50);
		
		txtC = new TextField();
		txtC.setMaxWidth(50);
		
		txtD = new TextField();
		txtD.setMaxWidth(50);
		
		txtResA = new TextField();
		txtResA.setMaxWidth(50);
		txtResA.setEditable(false);
		
		txtResB = new TextField();
		txtResB.setMaxWidth(50);
		txtResB.setEditable(false);
		
		operacion = new ComboBox<String>();
		operacion.getItems().addAll("+","-","*","/");
		
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets (5));
		root.setHgap(5);
		root.setVgap(5);
		root.add(txtA, 1,0);
		root.add(new Label("+"), 2,0);
		root.add(txtB, 3,0);
		root.add(new Label("i"), 4, 0);
		root.add(operacion, 0,1);
		root.add(txtC, 1,1);
		root.add(new Label("+"), 2,1);
		root.add(txtD, 3,1);
		root.add(new Label("i"), 4, 1);
		root.add(new Separator(), 1, 2);
		root.add(new Separator(), 2, 2);
		root.add(new Separator(), 3, 2);
		root.add(txtResA, 1,3);
		root.add(new Label("+"), 2,3);
		root.add(txtResB, 3, 3);
		root.add(new Label("i"), 4, 3);
		
		Scene scene = new Scene(root, 320, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login.fxml");
		primaryStage.show();
		
		txtA.textProperty().bindBidirectional(cantA);
		txtB.textProperty().bindBidirectional(cantB);
		txtC.textProperty().bindBidirectional(cantC);
		txtD.textProperty().bindBidirectional(cantD);
		
		cantA.bindBidirectional(numA, new NumberStringConverter());
		cantB.bindBidirectional(numB, new NumberStringConverter());
		cantC.bindBidirectional(numC, new NumberStringConverter());
		cantD.bindBidirectional(numD, new NumberStringConverter());
		
		seleccionado.bind(operacion.getSelectionModel().selectedItemProperty());
		operacion.valueProperty().addListener((ov, p1, p2) -> {
			
			if (p2.equals("+")) {
				
				numResA.bind(numA.add(numC));
				numResB.bind(numB.add(numD));
				
				txtResA.textProperty().bindBidirectional(numResA, new NumberStringConverter());
				txtResB.textProperty().bindBidirectional(numResB, new NumberStringConverter());
				
			}else if (p2.equals("-")) {
				
				numResA.bind(numA.add(numC.negate()));
				numResB.bind(numB.add(numD.negate()));
				
				txtResA.textProperty().bindBidirectional(numResA, new NumberStringConverter());
				txtResB.textProperty().bindBidirectional(numResB, new NumberStringConverter());
				
			}else if (p2.equals("*")) {
				
				numResA.bind((numA.multiply(numC)).add((numB.multiply(numD).negate())));
				numResB.bind((numA.multiply(numD)).add((numB.multiply(numC))));
				
				txtResA.textProperty().bindBidirectional(numResA, new NumberStringConverter());
				txtResB.textProperty().bindBidirectional(numResB, new NumberStringConverter());
				
			}else if (p2.equals("/")) {
				
				numResA.bind(((numA.multiply(numC)).add((numB.multiply(numD)))).divide((numC.multiply(numC)).add((numD.multiply(numD)))));
				numResB.bind((numB.multiply(numC)).add((numB.multiply(numC)).negate()).divide((numC.multiply(numC)).add((numD.multiply(numD)))));
				
				txtResA.textProperty().bindBidirectional(numResA, new NumberStringConverter());
				txtResB.textProperty().bindBidirectional(numResB, new NumberStringConverter());
			}
			
		});

	}
	public static void main(String[] args) {
		launch(args);
	}

}
