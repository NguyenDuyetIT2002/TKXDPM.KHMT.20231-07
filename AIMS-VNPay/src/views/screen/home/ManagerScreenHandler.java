package views.screen.home;

import controller.ManagerHomeController;
import entity.media.Book;
import entity.media.Media;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.Optional;


import static java.lang.Integer.parseInt;

public class ManagerScreenHandler extends BaseScreenHandler implements Initializable {

    public ManagerScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
	}
    
	boolean isInfoDisplayed = false;

	public static Logger LOGGER = Utils.getLogger(LoginScreenHandler.class.getName());

    @FXML
    private Button homeBtn;

    @FXML
    private Button bookBtn;

    @FXML
    private Button cdBtn;

    @FXML
    private Button dvdBtn;

    @FXML
    private AnchorPane homeForm;

    @FXML
    private AnchorPane bookForm;

    @FXML
    private AnchorPane cdForm;

    @FXML
    private AnchorPane dvdForm;

    // Media Table

    @FXML
    private TableView<Media> mediaTableView;

    @FXML
    private TableColumn<Media, Integer> mediaIDCol;

    @FXML
    private TableColumn<Media, Integer> mediaValueCol;

    @FXML
    private TableColumn<Media, Integer> mediaPriceCol;

    @FXML
    private TableColumn<Media, Integer> mediaQuantityCol;

    @FXML
    private TableColumn<Media, String> mediaTypeCol;

    @FXML
    private TableColumn<Media, String> mediaCategoryCol;

    @FXML
    private TableColumn<Media, String> mediaTitleCol;

    // Book Table

    @FXML
    private TableView<Book> bookTableView;

    @FXML
    private TableColumn<Book, Integer> bookIDCol;

    @FXML
    private TableColumn<Book, Integer> bookValueCol;

    @FXML
    private TableColumn<Book, Integer> bookPriceCol;

    @FXML
    private TableColumn<Book, Integer> bookQuantityCol;

    @FXML
    private TableColumn<Book, Integer> bookNumPagesCol;

    @FXML
    private TableColumn<Book, String> bookCategoryCol;

    @FXML
    private TableColumn<Book, String> bookTitleCol;

    @FXML
    private TableColumn<Book, String> bookAuthorCol;

    @FXML
    private TableColumn<Book, String> bookCoverTypeCol;

    @FXML
    private TableColumn<Book, String> bookPublisherCol;

    @FXML
    private TableColumn<Book, Date> bookPublishDateCol;

    @FXML
    private TableColumn<Book, String> bookLanguageCol;

    @FXML
    private TextField bookId, bookTitle, bookValue, bookPrice, bookQuantity, 
                      bookAuthor, bookPublisher, bookPages, bookLanguage, bookCategory;

    @FXML
    private ComboBox<String> bookCover;
    @FXML
    private DatePicker bookPubDate;
    @FXML
    private Label totalBook;

    @FXML
    private Label totalCD;

    @FXML
    private Label totalDVD;
    
    @FXML
    private Label labelBookId, labelBookTitle, labelBookValue, labelBookPrice, 
                  labelBookQuantity, labelBookAuthor, labelBookCover, 
                  labelBookPublisher, labelBookPubDate, labelBookPages, labelBookLanguage, labelBookCategory;

    public ManagerHomeController getBController() { return (ManagerHomeController) super.getBController(); }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setBController(new ManagerHomeController());
        try {
            showAllMedia();
            displayTotalMedia();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigate(ActionEvent e) throws SQLException {
        if(e.getSource() == homeBtn) {
            LOGGER.info("Navigate to home");
            homeForm.setVisible(true);
            bookForm.setVisible(false);
            cdForm.setVisible(false);
            dvdForm.setVisible(false);
            showAllMedia();
            displayTotalMedia();
        } else if(e.getSource() == bookBtn) {
            homeForm.setVisible(false);
            bookForm.setVisible(true);
            cdForm.setVisible(false);
            dvdForm.setVisible(false);
            showAllBook();
        } else if(e.getSource() == cdBtn) {
            homeForm.setVisible(false);
            bookForm.setVisible(false);
            cdForm.setVisible(true);
            dvdForm.setVisible(false);
        } else if(e.getSource() == dvdBtn) {
            homeForm.setVisible(false);
            bookForm.setVisible(false);
            cdForm.setVisible(false);
            dvdForm.setVisible(true);
        }
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    private void setupTableViewSelection() {
        bookTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Book selectedBook = bookTableView.getSelectionModel().getSelectedItem();
               
            }
        });
    }
    
    public void hideBookFields() {
        bookId.setVisible(false);
        bookTitle.setVisible(false);
        bookValue.setVisible(false);
        bookPrice.setVisible(false);
        bookQuantity.setVisible(false);
        bookAuthor.setVisible(false);
        bookPublisher.setVisible(false);
        bookPages.setVisible(false);
        bookLanguage.setVisible(false);
        bookCategory.setVisible(false);

        labelBookId.setVisible(false);
        labelBookTitle.setVisible(false);
        labelBookValue.setVisible(false);
        labelBookPrice.setVisible(false);
        labelBookQuantity.setVisible(false);
        labelBookAuthor.setVisible(false);
        labelBookCover.setVisible(false);
        labelBookPublisher.setVisible(false);
        labelBookPubDate.setVisible(false);
        labelBookPages.setVisible(false);
        labelBookLanguage.setVisible(false);
        labelBookCategory.setVisible(false);

        bookCover.setVisible(false);
        bookPubDate.setVisible(false);
        isInfoDisplayed = false;
        resetBookData();
    }
    
    public void showBookFields() throws SQLException {
        Book selectedBook = bookTableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            bookId.setText(String.valueOf(selectedBook.getId()));
            bookTitle.setText(selectedBook.getTitle());
            bookValue.setText(String.valueOf(selectedBook.getValue()));
            bookPrice.setText(String.valueOf(selectedBook.getPrice()));
            bookQuantity.setText(String.valueOf(selectedBook.getQuantity()));
            bookAuthor.setText(selectedBook.getAuthor());
            bookPublisher.setText(selectedBook.getPublisher());
            bookPages.setText(String.valueOf(selectedBook.getNumOfPages()));
            bookLanguage.setText(selectedBook.getLanguage());
            bookCategory.setText(selectedBook.getCategory());
        }
        bookId.setVisible(true);
        bookTitle.setVisible(true);
        bookValue.setVisible(true);
        bookPrice.setVisible(true);
        bookQuantity.setVisible(true);
        bookAuthor.setVisible(true);
        bookPublisher.setVisible(true);
        bookPages.setVisible(true);
        bookLanguage.setVisible(true);
        bookCategory.setVisible(true);

        labelBookId.setVisible(true);
        labelBookTitle.setVisible(true);
        labelBookValue.setVisible(true);
        labelBookPrice.setVisible(true);
        labelBookQuantity.setVisible(true);
        labelBookAuthor.setVisible(true);
        labelBookCover.setVisible(true);
        labelBookPublisher.setVisible(true);
        labelBookPubDate.setVisible(true);
        labelBookPages.setVisible(true);
        labelBookLanguage.setVisible(true);
        labelBookCategory.setVisible(true);

        bookCover.setVisible(true);
        bookPubDate.setVisible(true);
    }
    

    public void showAllMedia() throws SQLException {
        List<Media> listMedia = getBController().getAllMedia();
//        for (Media media : listMedia) {
//            LOGGER.info("Media ID: " + media.getId() + ", Quantity: " + media.getQuantity());
//        }

        mediaIDCol.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id"));
        mediaValueCol.setCellValueFactory(new PropertyValueFactory<Media, Integer>("value"));
        mediaPriceCol.setCellValueFactory(new PropertyValueFactory<Media, Integer>("price"));
        mediaTypeCol.setCellValueFactory(new PropertyValueFactory<Media, String>("type"));
        mediaCategoryCol.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        mediaTitleCol.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        mediaQuantityCol.setCellValueFactory(new PropertyValueFactory<Media, Integer>("quantity"));

        mediaTableView.getItems().setAll(listMedia);
    }

    public void showAllBook() throws SQLException {
        List<Book> listBook = getBController().getAllBook();
        bookIDCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        bookValueCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("value"));
        bookPriceCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("price"));
        bookQuantityCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("quantity"));
        bookNumPagesCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("numOfPages"));
        bookTitleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        bookCategoryCol.setCellValueFactory(new PropertyValueFactory<Book, String>("bookCategory"));
        bookAuthorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        bookCoverTypeCol.setCellValueFactory(new PropertyValueFactory<Book, String>("coverType"));
        bookPublisherCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        bookPublishDateCol.setCellValueFactory(new PropertyValueFactory<Book, Date>("publishDate"));
        bookLanguageCol.setCellValueFactory(new PropertyValueFactory<Book, String>("language"));

        bookTableView.getItems().setAll(listBook);
    }


    	
    	public void createBook(ActionEvent event) throws SQLException {
    	    if (!isInfoDisplayed) {
    	        // Display information (first click)
    	        showBookFields();

    	        isInfoDisplayed = true;
    	    } else {
    	        // Create book (second click)
    	        try {
    	            int bookIdValue = safeParseInt(bookId.getText());
    	            int bookPriceValue = safeParseInt(bookPrice.getText());
    	            int bookValueValue = safeParseInt(bookValue.getText());
    	            int bookQuantityValue = safeParseInt(bookQuantity.getText());
    	            int bookPagesValue = safeParseInt(bookPages.getText());

    	            getBController().createBook(bookIdValue, bookTitle.getText(), bookCategory.getText(),
    	                bookPriceValue, bookValueValue, bookQuantityValue, "book", bookAuthor.getText(),
    	                (String) bookCover.getValue(), bookPublisher.getText(), java.sql.Date.valueOf(bookPubDate.getValue()),
    	                bookPagesValue, bookLanguage.getText(), bookCategory.getText(), "assets/images/book/book3.jpg");
    	            System.out.println("Publish Date Value: " + bookPubDate.getValue());

    	            hideBookFields();
    	            showAllBook();

    	            // Reset state for the next click
    	            isInfoDisplayed = false;
    	        } catch (NumberFormatException e) {
    	            // Handle the exception (e.g., show error message)
    	            e.printStackTrace();
    	        }
    	    }
    	}

    private int safeParseInt(String value) throws NumberFormatException {
        if (value == null || value.trim().isEmpty()) {
            throw new NumberFormatException("Input string is null or empty");
        }
        return Integer.parseInt(value.trim());
    }

    public void updateBook() throws SQLException {
        // Get the selected book from the table view
        Book selectedBook = bookTableView.getSelectionModel().getSelectedItem();

        // Check if a book is selected
        if (selectedBook == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "No Book Selected", "Please select a book in the table.");
            return;
        } else

        try {
        	if (!isInfoDisplayed) {
        		showBookFields();
        		isInfoDisplayed = true;
        		
        	} else {
                // Parse input values
        		int bookPriceValue = safeParseInt(bookPrice.getText());
        	    int bookValueValue = safeParseInt(bookValue.getText());
        	    int bookQuantityValue = safeParseInt(bookQuantity.getText());
        	    int bookPagesValue = safeParseInt(bookPages.getText());
        	    java.sql.Date sqlPubDate = java.sql.Date.valueOf(bookPubDate.getValue()); // Chuyển LocalDate thành java.sql.Date

        	    // Gọi phương thức updateBook với các giá trị đã được chuyển đổi
        	    getBController().updateBook(
        	        selectedBook.getId(),
        	        bookTitle.getText(),
        	        bookCategory.getText(),
        	        bookPriceValue,
        	        bookValueValue,
        	        bookQuantityValue,
        	        "book",
        	        bookAuthor.getText(),
        	        (String) bookCover.getValue(),
        	        bookPublisher.getText(),
        	        sqlPubDate,
        	        bookPagesValue,
        	        bookLanguage.getText(),
        	        bookCategory.getText()
        	    );

                // Hide book fields after update
                hideBookFields();

                // Refresh the book table
                showAllBook();
        	}
        } catch (NumberFormatException e) {
            // Handle number format exception (e.g., show an alert)
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Input Error", "Please enter valid numeric values.");
            e.printStackTrace(); // Log the exception for debugging
        }
    }


    public void deleteBook() throws SQLException {
        Book selectedBook = bookTableView.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "No Book Selected", "Please select a book in the table.");
            return;
        }
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this book?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.get() == ButtonType.YES) {
            try {
                getBController().deleteBook(selectedBook.getId());
                showAllBook();
                showAlert(Alert.AlertType.INFORMATION, "Deletion Successful", "Book Deleted", "Book has been deleted successfully.");
            } catch (SQLException ex) {
                showAlert(Alert.AlertType.ERROR, "Deletion Failed", "Error Deleting Book", "There was an error deleting the book.");
            }
        }
    }

    public void resetBookData() {
        bookId.clear();
        bookTitle.clear();
        bookCategory.clear();
        bookPrice.clear();
        bookValue.clear();
        bookQuantity.clear();
        bookAuthor.clear();
        bookCover.getSelectionModel().clearSelection();
        bookPublisher.clear();
        bookPubDate.getEditor().clear(); // Clear the date picker text field
        bookPages.clear();
        bookLanguage.clear();
    }

    public void displayTotalMedia() throws SQLException {
        int totalBookCount = getBController().getCountMedia("book");
        int totalCDCount = getBController().getCountMedia("cd");
        int totalDVDCount = getBController().getCountMedia("dvd");
        totalBook.setText(String.valueOf(totalBookCount));
        totalCD.setText(String.valueOf(totalCDCount));
        totalDVD.setText(String.valueOf(totalDVDCount));
    }


    @FXML
    void logout() throws IOException, InterruptedException, SQLException {
        this.homeScreenHandler.show();
    }
}