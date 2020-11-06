package BagelShop;

/**
 * @author Abhinav Girdhar
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;


public class FrameController {

    @FXML
    private RadioButton radWhiteBgl;
    
    @FXML
    private VBox vboxToppings;

    @FXML
    private TextField txtRegCffe;

    @FXML
    private CheckBox chkPeach;

    @FXML
    private Button btnCalcTotal;

    @FXML
    private TextField txtCappaccino;

    @FXML
    private RadioButton radNoCffe;

    @FXML
    private RadioButton radWheatBgl;

    @FXML
    private CheckBox chkBlueberry;

    @FXML
    private CheckBox chkRaspberry;

    @FXML
    private Label lblSalesTax;

    @FXML
    private TextField txtWhiteBgl;

    @FXML
    private RadioButton radNoBgl;

    @FXML
    private TextField txtWheatBgl;

    @FXML
    private RadioButton radCafeAuLait;

    @FXML
    private CheckBox chkButter;

    @FXML
    private RadioButton radCappuccinno;

    @FXML
    private CheckBox chkCreamCheese;

    @FXML
    private Label lblTotalSale;

    @FXML
    private TextField txtCafeAuLait;

    @FXML
    private Label lblSubtotal;

    @FXML
    private RadioButton radRegCffe;

    @FXML
    private Button btnExit;
    @FXML
    private ToggleGroup bagel;
    @FXML
    private ToggleGroup coffee;

    @FXML
    void optSave_Click(ActionEvent event) throws FileNotFoundException {
    	saveToFile();
    }

    /*
     * Code adopted from Codota.com
     */
    @FXML
    void optPrint_Click(ActionEvent event) {

    	try {
			saveToFile();
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist.");
		}
        HashPrintRequestAttributeSet setPrinter = new HashPrintRequestAttributeSet();
        DocFlavor.INPUT_STREAM sensePrinter = DocFlavor.INPUT_STREAM.AUTOSENSE;
        PrintService printDialog = javax.print.ServiceUI.printDialog(null, 400, 300, PrintServiceLookup.lookupPrintServices(sensePrinter, setPrinter), PrintServiceLookup.lookupDefaultPrintService(), sensePrinter, setPrinter);
        if (printDialog != null) {
            try {
                DocPrintJob printJob = printDialog.createPrintJob();
                SimpleDoc simpleDoc = new SimpleDoc(new FileInputStream("receipt.txt"), sensePrinter, new HashDocAttributeSet());
                try {
                    printJob.print(simpleDoc, setPrinter);
                    System.out.print("Printer has been setup.\n");
                }
                catch (PrintException obj) {
                    System.out.println("Error: Cannot Print" + obj.getMessage());
                }
            }
            catch (FileNotFoundException obj) {
                System.out.println("File does not exist." + obj.getMessage());
            }
        }
    }
    	
  


    @FXML
    void btnCalculate_Click(ActionEvent event) {
    	try {
    	calculateTotal();
    	}
    	catch (NumberFormatException e)
    	{
    		JOptionPane.showMessageDialog(null, "Please enter a valid quantity");
    	}
    }

    @FXML
    void btnReset_Click(ActionEvent event) {
    	reset();
    }

    @FXML
    void btnExit_Click(ActionEvent event) {
    	Platform.exit();
    }
    
    @FXML
    void radNoneBagel_click(ActionEvent event) {
    	vboxToppings.setDisable(true);
    	txtWheatBgl.setEditable(false);
    	txtWhiteBgl.setEditable(false);
    	txtWhiteBgl.setText("");
    	txtWheatBgl.setText("");
    }
    
    @FXML
    void radWhite_selected(ActionEvent event) {
    	txtWhiteBgl.setDisable(false);
    	txtWhiteBgl.setText("1");
    	txtWhiteBgl.requestFocus();
    	
    	txtWheatBgl.setDisable(true);
    	txtWheatBgl.setText("");
    	
    	vboxToppings.setDisable(false);
    }
    
    @FXML
    void radWheat_selected(ActionEvent event) {
    	txtWheatBgl.setDisable(false);
    	txtWheatBgl.setText("1");
    	txtWheatBgl.requestFocus();
    	
    	txtWhiteBgl.setDisable(true);
    	txtWhiteBgl.setText("");
    	
    	vboxToppings.setDisable(false);
    }
    
    @FXML
    void radCafeAuLait_Click(ActionEvent event) {
    	txtCafeAuLait.setDisable(false);
    	txtCafeAuLait.setText("1");
    	txtCafeAuLait.requestFocus();
    	
    	txtRegCffe.setDisable(true);
    	txtRegCffe.setText("");
    	txtCappaccino.setDisable(true);
    	txtCappaccino.setText("");	
    }

    @FXML
    void radCappaccino_Click(ActionEvent event) {
    	txtCappaccino.setDisable(false);
    	txtCappaccino.setText("1");
    	txtCappaccino.requestFocus();
    	
    	txtCafeAuLait.setDisable(true);
    	txtCafeAuLait.setText("");
    	txtRegCffe.setDisable(true);
    	txtRegCffe.setText("");
    }

    @FXML
    void radRegCoffee_Click(ActionEvent event) {
    	txtRegCffe.setDisable(false);
    	txtRegCffe.setText("1");
    	txtRegCffe.requestFocus();
    	
    	txtCafeAuLait.setDisable(true);
    	txtCafeAuLait.setText("");
    	txtCappaccino.setDisable(true);
    	txtCappaccino.setText("");
    }

    @FXML
    void radNoneCoffee_Click(ActionEvent event) {
    	txtCafeAuLait.setDisable(true);
    	txtCappaccino.setDisable(true);
    	txtRegCffe.setDisable(true);
    	txtCafeAuLait.setText("");
    	txtCappaccino.setText("");
    	txtRegCffe.setText("");
    }
    
    final double priceWhiteBgl = 1.25, priceWheatBgl = 1.50, priceRegCffe = 1.25, priceCappaccino = 2.0, priceCafeAuLait = 1.75, priceCreamCheeseTopping = 0.5, priceButterTopping = 0.25, priceBlueberryTopping = 0.75, priceRaspberryTopping = 0.75, pricePeachToppinng = 0.75;
    double subtotal;
    double total;
    double salesTax;
    final double HST = 0.13;

    
    void calculateTotal() {
    	subtotal = 0;
    	salesTax = 0;
    	total = 0;
    	salesTax = 0;
    	int num;
    if(radWhiteBgl.isSelected())
    {
    	num = Integer.parseInt(txtWhiteBgl.getText());
    	subtotal += priceWhiteBgl*num;
    }
    if (radWheatBgl.isSelected())
    {
    	num = Integer.parseInt(txtWheatBgl.getText());
    	subtotal += priceWheatBgl*num;
    }
    if(radRegCffe.isSelected())
    {
    	num = Integer.parseInt(txtRegCffe.getText());
    	subtotal += priceRegCffe*num;
    }
    if(radCappuccinno.isSelected())
    {
    	num = Integer.parseInt(txtCappaccino.getText());
    	subtotal += priceCappaccino*num;
    }
    if(radCafeAuLait.isSelected())
    {
    	num = Integer.parseInt(txtCafeAuLait.getText());
    	subtotal += priceCafeAuLait*num;
    }
    if(chkCreamCheese.isSelected())
    {
    	subtotal += priceCreamCheeseTopping;
    }
    if(chkButter.isSelected())
    {
    	subtotal += priceButterTopping;
    }
    if(chkBlueberry.isSelected())
    {
    	subtotal += priceBlueberryTopping;
    }
    if(chkRaspberry.isSelected())
    {
    	subtotal += priceRaspberryTopping;
    }
    if(chkPeach.isSelected())
    {
    	subtotal += pricePeachToppinng;
    }
    	salesTax = subtotal*HST;
    	total = subtotal + salesTax;
    	String formatsubTot = String.format("%.2f", subtotal);
    	String formatsaleTax = String.format("%.2f", salesTax);
    	String formatTot = String.format("%.2f", total);
    	lblSubtotal.setText("$ "+ formatsubTot);
    	lblSalesTax.setText("$ "+formatsaleTax);
    	lblTotalSale.setText("$ "+ formatTot);	
    }

    
    void reset() {
    	txtCafeAuLait.setDisable(true);
    	txtCappaccino.setDisable(true);
    	txtRegCffe.setDisable(true);
    	txtWheatBgl.setDisable(true);
    	txtWhiteBgl.setDisable(true);
    	vboxToppings.setDisable(true);
    	
    	radCafeAuLait.setSelected(false);
    	radCappuccinno.setSelected(false);
    	radNoBgl.setSelected(false);
    	radNoCffe.setSelected(false);
    	radRegCffe.setSelected(false);
    	radWheatBgl.setSelected(false);
    	radWhiteBgl.setSelected(false);
    
    	txtCafeAuLait.setText("");
    	txtCappaccino.setText("");
    	txtRegCffe.setText("");
    	txtWheatBgl.setText("");
    	txtWhiteBgl.setText("");
    	
    	lblSalesTax.setText("$ 0.0");
    	lblSubtotal.setText("$ 0.0");
    	lblTotalSale.setText("$ 0.0");
    	
    	
    	chkBlueberry.setSelected(false);
    	chkButter.setSelected(false);
    	chkCreamCheese.setSelected(false);
    	chkPeach.setSelected(false);
    	chkRaspberry.setSelected(false);
    }

    
    void exit() {
    	Platform.exit();
    }
    
    void saveToFile() throws FileNotFoundException
    {
    	File file = new File("receipt.txt");	
		PrintWriter outputFile = new PrintWriter(file);
		outputFile.print("*************SHERIDAN BAGEL SHOP************* \n");
		Date dateobj = new Date();
		outputFile.print(""+dateobj+"\n");
		String format = String.format("%-40s%-20s%-20s", "Item", "Quantity", "Amount");
		outputFile.println(format);
    	int num;
        if(radWhiteBgl.isSelected())
        {
        	num = Integer.parseInt(txtWhiteBgl.getText());
        	format = String.format("%-40s%-20d$%-20.2f", "White Bagel: ", num, priceWhiteBgl);
        	outputFile.println(format);
        	
        }
        if (radWheatBgl.isSelected())
        {
        	num = Integer.parseInt(txtWheatBgl.getText());
        	format = String.format("%-40s%-20d$%-20.2f", "Whole Wheat Bagel: ", num, priceWheatBgl);
        	outputFile.println(format);
        }
        if(radRegCffe.isSelected())
        {
        	num = Integer.parseInt(txtRegCffe.getText());
        	format = String.format("%-40s%-20d$%-20.2f", "Regular Coffee: ", num, priceRegCffe);
        	outputFile.println(format);
        }
        if(radCappuccinno.isSelected())
        {
        	num = Integer.parseInt(txtCappaccino.getText());
        	format = String.format("%-40s%-20d$%-20.2f", "Cappaccino: ", num, priceCappaccino);
        	outputFile.println(format);
        }
        if(radCafeAuLait.isSelected())
        {
        	num = Integer.parseInt(txtCafeAuLait.getText());
        	format = String.format("%-40s%-20d$%-20.2f", "Cafe Au Lait: ", num, priceCafeAuLait);
        	outputFile.println(format);
        }
        if(chkCreamCheese.isSelected())
        {
        	format = String.format("%-40s%-20s$%-20.2f", "Cream Cheese Topping: ", "", priceCreamCheeseTopping);
        	outputFile.println(format);
        }
        if(chkButter.isSelected())
        {
        	format = String.format("%-40s%-20s$%-20.2f", "Butter Topping: ", "", priceButterTopping);
        	outputFile.println(format);
        }
        if(chkBlueberry.isSelected())
        {
        	format = String.format("%-40s%-20s$%-20.2f", "Blueberry Jam Topping: ", "", priceBlueberryTopping);
        	outputFile.println(format);
        }
        if(chkRaspberry.isSelected())
        {
        	format = String.format("%-40s%-20s$%-20.2f", "Raspberry Jam Topping: ", "", priceRaspberryTopping);
        	outputFile.println(format);
        }
        if(chkPeach.isSelected())
        {
        	format = String.format("%-40s%-20s%-20.2f", "Peach Jelly Topping: ", "", pricePeachToppinng);
        	outputFile.println(format);
        }
        format = String.format("%-40s%-20s%-20s", "", "", "-----------");
    	outputFile.println(format);
    	format = String.format("%-40s%-20s$%-20.2f", "Pretax Total", "", subtotal);
    	outputFile.println(format);
    	format = String.format("%-40s%-20s$%-20.2f", "Sales Tax", "", salesTax);
    	outputFile.println(format);
    	format = String.format("%-40s%-20s%-20.2f", "Total", "", total);
    	outputFile.println(format);
    	outputFile.println("*********THANK YOU FOR YOUR ORDER*********");
    	outputFile.close();
    }
    
    

}
