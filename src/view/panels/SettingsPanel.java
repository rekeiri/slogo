package view.panels;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import controller.Controller;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import view.turtle.Turtle;

/**
 * 
 * @author Katherine Van Dyk
 * @date 2/24/18
 * 
 * Class that generates the stylistic settings panel at the top of the turtle screen.
 * 
 */
public class SettingsPanel {
    private Controller CONTROLLER;
    private TurtlePanel TURTLE_PANEL;
    private Turtle TURTLE;
    private final String LANGUAGES = "./src/resources/languages";
    private final String IMAGES = "./src/resources/turtles";

    private ColorPicker BackgroundChooser;
    private ComboBox<String> ImageChooser;
    private ComboBox<String> LanguageChooser;

    /**
     * Constructs settings panel 
     * 
     * @param c: Controller for changing style features
     * @param tp: Controller for changing turtle image/pen color
     */
    public SettingsPanel(Controller c, TurtlePanel tp, Turtle t) {
	CONTROLLER = c;
	TURTLE_PANEL = tp;
	TURTLE = t;
	initializeObjects();
    }

    public void setTP(TurtlePanel TP) {
	TURTLE_PANEL = TP;
    }

    /**
     * @return HBox containing settings panels
     */
    public HBox construct() {
	return new HBox(12, ImageChooser, LanguageChooser,  BackgroundChooser);
    }

    /**
     * Factory method for constructing drop down boxes 
     * 
     * @param text
     * @param options
     * @return
     */
    private ComboBox<String> chooserFactory(String text, Set<String> options) {
	ComboBox<String> chooser = new ComboBox<String>();
	chooser.getStyleClass().add("combo-box");
	chooser.getItems().addAll(options);
	chooser.setPromptText(text);
	return chooser;
    }

    /**
     * Initializes all chooser objects
     */
    private void initializeObjects() {
	BackgroundChooser = new ColorPicker();
	BackgroundChooser.getStyleClass().add("combo-box");
	BackgroundChooser.setOnAction(click ->{
	    TURTLE_PANEL.changeBack(BackgroundChooser.getValue());});
	
	ImageChooser = chooserFactory("Image", getFiles(IMAGES));
	ImageChooser.setOnAction(click->{ TURTLE.changeImage("resources/turtles/" + ImageChooser.getValue() + ".png");});

	LanguageChooser = chooserFactory("Languages", getFiles(LANGUAGES));
	LanguageChooser.setOnAction(click->{CONTROLLER.updateLanguage("resources/languages/" + LanguageChooser.getValue() + ".properties");});
    }

    /**
     * Gets all files in directory and takes away file type for display
     * @param directory
     * @return
     */
    private Set<String> getFiles(String directory) {
	File[] files = new File(directory).listFiles();
	Set<String> ret = new HashSet<String>();
	for(File file : files){
	    ret.add(file.getName().substring(0, file.getName().indexOf(".")));
	}
	return ret;
    }
}
