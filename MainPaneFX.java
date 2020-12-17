


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;


/**
 * This panel is the basic pane, inside which other panes are placed.  
 * @author ralexander
 */
public class MainPaneFX {

	private BorderPane mainPane;
	private HBox buttonPanel, inputBlock;
	private VBox inputLabels, inputPanel;
	private Button readButton, exitButton, resetButton, addButton, printButton, writeButton;
	RadioButton [] shipRadioButtons, warshipRadioButtons;
	private TextField shipField, nameField, yearField, tonsField, pxField, acField, gunsField, torpField;
	private Label shipLabel, nameLabel, yearLabel, tonsLabel, pxLabel, acLabel, gunsLabel, torpLabel;
	private HBox shipRadioBox, warshipRadioBox;
	
	//The manager is the way the GUI communicates with the worker code
	private ShipRegistry ships;
	
	
	
	/**
	 * The MainPanel constructor sets up the GUI with buttons, radio buttons, and a display area.  
	 */
	MainPaneFX(double CANVAS_WIDTH, double CANVAS_HEIGHT) {

		mainPane = new BorderPane();

		//create the dataManager instance
		ships = new ShipRegistry();
		
	    //create the exitButton
	    exitButton = new Button("Exit");
	    exitButton.setTooltip(new Tooltip("Select to close the application"));
	    exitButton.setOnAction(event -> 
			 System.exit(0) );
			 
	    //create the button to read input from a file
	    resetButton = new Button("Reset the Ship Types");
	    resetButton.setTooltip(new Tooltip("Reset the radio buttons for selecting ships"));
	    resetButton.setOnAction(event -> {
    		clearFieldVisibility();
    		shipRadioBox.setVisible(true);
    		warshipRadioBox.setVisible(false);
    		for (int i=0; i < shipRadioButtons.length; i++)
    			{
    				shipRadioButtons[i].setSelected(false);
    			}
    	
	    	});
	    addButton = new Button("Add a ship");
	    addButton.setTooltip(new Tooltip("Add a ship with info entered by user"));
	    addButton.setOnAction(event -> {	   
		    try {
		    	int tons = 0, pax = 0, guns = 0, ac = 0, torps = 0;
		    	
		    	String name = nameField.getText();
		    	if (name.equals("")) name="unknown";
		    	String year = yearField.getText();
		    	if (year.equals("")) year="unknown";
		    	String type = shipField.getText();
		    	if (!tonsField.getText().equals("")) 
		    		tons = Integer.parseInt(tonsField.getText());
		    	if (!pxField.getText().equals("")) 
		    		pax = Integer.parseInt(pxField.getText());
		    	if (!gunsField.getText().equals("")) 
		    		guns = Integer.parseInt(gunsField.getText());
		    	if (!acField.getText().equals("")) 
		    		ac = Integer.parseInt(acField.getText());
		    	if (!torpField.getText().equals("")) 
		    		torps = Integer.parseInt(torpField.getText());		    	

		    	System.out.println("Name: "+name+", Year Built: "+year+", Type: "+type+", "+tons + " Tons, "+pax + " Passengers, "+ac + " Aircraft, "+guns + " Guns, "+torps + " Torpedoes");
		    	ships.addShip(name, type, year, tons, pax, guns, torps, ac);

		    } catch (NumberFormatException e) {
		    	JOptionPane.showMessageDialog(null, e.getMessage()+" expected an integer.");
		    }
	    });
	    //create the button to read input from a file
	    readButton = new Button("Read File");
	    readButton.setTooltip(new Tooltip("Read an input file"));
		/*
		 * When the read button is pushed, user is prompted to select an input file
		 * and ship data is read in.
		 */
	    readButton.setOnAction(event -> {
	    	try {
				readFile();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    });
	    
	    //create the button to display input to the user
	    printButton = new Button("Display Ships");
	    printButton.setTooltip(new Tooltip("Display the Ship Registry"));
		/*
		 * When the read button is pushed, user is prompted to select an input file
		 * and ship data is read in.
		 */
	    printButton.setOnAction(event -> {
			 JOptionPane.showMessageDialog(null, ships.toString());
	    });
	    
	    //create the button to display input to the user
	    writeButton = new Button("Write Ships");
	    writeButton.setTooltip(new Tooltip("Write the Ship Registry to a file"));
		/*
		 * When the read button is pushed, user is prompted to select an input file
		 * and ship data is read in.
		 */
	    writeButton.setOnAction(event -> {
	    	try {
				writeFile();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    });
	    
	    
	  //tonsField, pxField, acField, gunsField, torpField;
	    tonsField =  new TextField();
	    pxField =  new TextField();
	    acField =  new TextField();
	    gunsField =  new TextField();
	    torpField =  new TextField();
	    shipField = new TextField("");
	    
	    nameLabel = new Label("Ship's Name");
	    yearLabel = new Label("Year Built");
	    shipLabel = new Label("Ship Type");
	    tonsLabel = new Label("Tonnage");
	    pxLabel = new Label("Passengers");
	    acLabel = new Label("Aircraft");
	    gunsLabel = new Label("Guns");
	    torpLabel = new Label("Torpedoes");
	    
	     shipRadioBox = new HBox();
	     shipRadioBox.setAlignment(Pos.CENTER);
	     
	     //{ShipType.CARGO.toString(), ShipType.CRUISE.toString(), ShipType.WARSHIP.toString()};
	     String [] shipRadioLabels = ships.getShipDescriptions();
	     ToggleGroup shipRadioGroup = new ToggleGroup();
	     shipRadioButtons = new RadioButton[shipRadioLabels.length];
	     for (int i=0; i < shipRadioLabels.length; i++)
	     {
	    	 int index = i;
	    	 shipRadioButtons[i] = new RadioButton(shipRadioLabels[i]);
	    	 shipRadioButtons[i].setToggleGroup(shipRadioGroup);
	    	 shipRadioButtons[i].setPadding(new Insets(10,10,10,10));
	     }

	     shipRadioBox.getChildren().addAll(shipRadioButtons);
	     
	     //{ShipType.CARRIER.toString(), ShipType.CRUISER.toString(), ShipType.DESTROYER.toString(), ShipType.MINE_SWEEPER.toString(), ShipType.SUBMARINE.toString()};
	     String [] warshipRadioLabels = ships.getWarshipDescriptions();
	     ToggleGroup warshipRadioGroup = new ToggleGroup();
	     warshipRadioButtons = new RadioButton[warshipRadioLabels.length];
	     for (int i=0; i < warshipRadioLabels.length; i++)
	     {
	    	 warshipRadioButtons[i] = new RadioButton(warshipRadioLabels[i]);
	    	 warshipRadioButtons[i].setToggleGroup(warshipRadioGroup);
	    	 warshipRadioButtons[i].setPadding(new Insets(10,10,10,10));
	     }

	     warshipRadioBox = new HBox();
	     warshipRadioBox.setAlignment(Pos.CENTER);
	     warshipRadioBox.getChildren().addAll(warshipRadioButtons);
	     warshipRadioBox.setVisible(false);

	     //CARGO
	   	 shipRadioButtons[0].setOnAction(
	    		 event ->  {
		    		clearFieldVisibility();
		    		inputLabels.getChildren().clear();
		    		inputPanel.getChildren().clear();
		    	    inputLabels.getChildren().addAll(nameLabel, yearLabel, shipLabel, tonsLabel);
		    	    inputPanel.getChildren().addAll(nameField, yearField, shipField, tonsField); 
		    		shipField.setText("Cargo");
		    	    tonsLabel.setVisible(true);
		    	    tonsField.setVisible(true);
		    		 });
	   	 //CRUISE
	   	 shipRadioButtons[1].setOnAction(
	    		 event ->  {
		    		clearFieldVisibility();
		    		inputLabels.getChildren().clear();
		    		inputPanel.getChildren().clear();
		    	    inputLabels.getChildren().addAll(nameLabel, yearLabel, shipLabel, pxLabel);
		    	    inputPanel.getChildren().addAll(nameField, yearField, shipField, pxField);
		    		shipField.setText("Cruise");
		    	    pxLabel.setVisible(true);
		    	    pxField.setVisible(true);
		    		 });
	   	 //WARSHIP
    	 shipRadioButtons[2].setOnAction(
	    		 event ->  {
			    	shipField.setText("Warship");
		    		shipRadioBox.setVisible(false);
		    		warshipRadioBox.setVisible(true);
		    		clearFieldVisibility();
		    		inputLabels.getChildren().clear();
		    		inputPanel.getChildren().clear();
		    	    inputLabels.getChildren().addAll(nameLabel, yearLabel, shipLabel, gunsLabel, acLabel, torpLabel);
		    	    inputPanel.getChildren().addAll(nameField, yearField, shipField, gunsField, acField, torpField);
		    	    shipField.setText("Warship");
		    	    gunsLabel.setVisible(true);
		    	    gunsField.setVisible(true);
		    	    acLabel.setVisible(true);
		    	    acField.setVisible(true);
		    	    torpLabel.setVisible(true);
		    	    torpField.setVisible(true);
		    		 });
    	 //CARRIER
    	 warshipRadioButtons[0].setOnAction(
	    		 event ->  {
		    	    shipField.setText("Carrier");
		    	    gunsField.clear();
		    	    acField.setEditable(true);
		    	    torpField.clear();
		    		inputLabels.getChildren().clear();
		    		inputPanel.getChildren().clear();
		    	    inputLabels.getChildren().addAll(nameLabel, yearLabel, shipLabel, acLabel);
		    	    inputPanel.getChildren().addAll(nameField, yearField, shipField, acField);
		    		 });
    	 //CRUISER
    	 warshipRadioButtons[1].setOnAction(
	    		 event ->  {
		    	    shipField.setText("Cruiser");
		    	    gunsField.setEditable(true);
		    	    acField.clear();
		    	    torpField.clear();
		    		inputLabels.getChildren().clear();
		    		inputPanel.getChildren().clear();
		    	    inputLabels.getChildren().addAll(nameLabel, yearLabel, shipLabel, gunsLabel);
		    	    inputPanel.getChildren().addAll(nameField, yearField, shipField, gunsField);

		    		 });
    	 //DESTROYER
    	 warshipRadioButtons[2].setOnAction(
	    		 event ->  {
		    	    shipField.setText("Destroyer");
		    	    acField.clear();
		    	    torpField.clear();
		    		inputLabels.getChildren().clear();
		    		inputPanel.getChildren().clear();
		    	    inputLabels.getChildren().addAll(nameLabel, yearLabel, shipLabel, gunsLabel);
		    	    inputPanel.getChildren().addAll(nameField, yearField, shipField, gunsField);

		    		 });
    	 //MINE_SWEEPER
    	 warshipRadioButtons[3].setOnAction(
	    		 event ->  {
		    	    shipField.setText("Mine Sweeper");
		    	    acField.clear();
		    	    torpField.clear();
		    		inputLabels.getChildren().clear();
		    		inputPanel.getChildren().clear();
		    	    inputLabels.getChildren().addAll(nameLabel, yearLabel, shipLabel, gunsLabel);
		    	    inputPanel.getChildren().addAll(nameField, yearField, shipField, gunsField);

		    		 });
    	 //SUBMARINE
    	 warshipRadioButtons[4].setOnAction(
	    		 event ->  {
		    	    shipField.setText("Submarine");
		    	    acField.clear();
		    	    gunsField.clear();
		    		inputLabels.getChildren().clear();
		    		inputPanel.getChildren().clear();
		    	    inputLabels.getChildren().addAll(nameLabel, yearLabel, shipLabel, torpLabel);
		    	    inputPanel.getChildren().addAll(nameField, yearField, shipField, torpField);

		    		 });

	     VBox shipInfo = new VBox();
	     shipInfo.setAlignment(Pos.CENTER);
	     shipInfo.setStyle("-fx-border-color: gray;");
	     VBox.setMargin(shipInfo,new Insets(10,40,10,40));
	     shipInfo.getChildren().addAll(shipLabel, shipRadioBox, warshipRadioBox);
	     
		 //add the shipInfo VBox to the top section of the main panel
		 mainPane.setTop(shipInfo);
	     
		//create the buttonPanel
	    buttonPanel = new HBox();
	    buttonPanel.setVisible(true);
	    buttonPanel.setAlignment(Pos.CENTER);
	    HBox.setMargin(exitButton, new Insets(10,10,10,10)); 
	    HBox.setMargin(addButton, new Insets(10,10,10,10)); 
	    HBox.setMargin(resetButton, new Insets(10,10,10,10)); 
	    HBox.setMargin(readButton, new Insets(10,10,10,10)); 
	    HBox.setMargin(printButton, new Insets(10,10,10,10)); 
	    buttonPanel.getChildren().addAll(addButton, resetButton, readButton, printButton, writeButton, exitButton);
	    
	    //add the panel to the bottom section of the main panel
	    mainPane.setBottom(buttonPanel);
	         
	    VBox.setMargin(nameLabel, new Insets(12,10,5,10)); 
	    VBox.setMargin(yearLabel, new Insets(12,10,5,10)); 
	    VBox.setMargin(shipLabel, new Insets(12,10,5,10));
	    VBox.setMargin(gunsLabel, new Insets(12,10,5,10));
	    VBox.setMargin(acLabel, new Insets(12,10,5,10)); 
	    VBox.setMargin(torpLabel, new Insets(12,10,5,10));
	    VBox.setMargin(pxLabel, new Insets(12,10,5,10));
	    VBox.setMargin(tonsLabel, new Insets(12,10,5,10));
	    inputLabels=new VBox();
	    inputLabels.getChildren().addAll(nameLabel, yearLabel, shipLabel, gunsLabel, acLabel, torpLabel, pxLabel, tonsLabel);
	    
	    nameField = new TextField();
	    yearField = new TextField();
	    VBox.setMargin(nameField, new Insets(5,10,5,10)); 
	    VBox.setMargin(yearField, new Insets(5,10,5,10)); 
	    VBox.setMargin(shipField, new Insets(5,10,5,10));
	    VBox.setMargin(gunsField, new Insets(5,10,5,10));
	    VBox.setMargin(acField, new Insets(5,10,5,10)); 
	    VBox.setMargin(torpField, new Insets(5,10,5,10));
	    VBox.setMargin(pxField, new Insets(5,10,5,10));
	    VBox.setMargin(tonsField, new Insets(5,10,5,10));
	    inputPanel=new VBox();
	    inputPanel.getChildren().addAll(nameField, yearField, shipField, gunsField, acField, torpField, pxField, tonsField);
	    
	    HBox.setMargin(inputLabels, new Insets(10,0,10,10)); 
	    HBox.setMargin(inputPanel, new Insets(10,10,10,0)); 
	    inputBlock=new HBox();
	    inputBlock.setAlignment(Pos.CENTER);
	    inputBlock.getChildren().addAll(inputLabels, inputPanel);
	    
	    mainPane.setCenter(inputBlock);
	    
	    clearFieldVisibility();
	   
	}

	private void writeFile() 	   
		{
			//Use JFileChooser instead of FileChooser so selected file can be a new one, as well as an existing one
			JFileChooser cf = new JFileChooser();
			cf.setDialogTitle("Choose a *.csv file to write to");
			cf.showOpenDialog(null);
			File file = cf.getSelectedFile();
			
			if(file != null) 
				ships.writeFile(file);		   
	    }

	private void readFile() throws IOException
	   {
		   File file;
		   FileChooser chooser = new FileChooser();
		   file = chooser.showOpenDialog(null);
		   if(file != null) ships.readFile(file);
		   
	   }

	private void clearFieldVisibility() {
		inputLabels.getChildren().clear();
		inputPanel.getChildren().clear();
	    inputLabels.getChildren().addAll(nameLabel, yearLabel, shipLabel);
	    inputPanel.getChildren().addAll(nameField, yearField, shipField);
	    nameLabel.setVisible(true);
	    nameField.setVisible(true);
	    yearLabel.setVisible(true);
	    yearField.setVisible(true);
	    shipLabel.setVisible(true);
	    shipField.setVisible(true);
	    nameField.setText("");
	    yearField.setText("");
	    shipField.setText("");
	    gunsField.setText("");
	    acField.setText("");
	    torpField.setText("");
	    pxField.setText("");
	    tonsField.setText("");
	}

	public BorderPane getMainPane() {
		return mainPane;
	}

}
