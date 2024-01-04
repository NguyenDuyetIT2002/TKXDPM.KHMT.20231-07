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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class ManagerScreenHandler extends BaseScreenHandler implements Initializable {

    public ManagerScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
	}

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
    private TextField bookId;

    @FXML
    private TextField bookCategory;

    @FXML
    private TextField bookTitle;

    @FXML
    private TextField bookValue;

    @FXML
    private TextField bookPrice;

    @FXML
    private TextField bookQuantity;

    @FXML
    private TextField bookAuthor;

    @FXML
    private ComboBox bookCover;

    @FXML
    private TextField bookPublisher;

    @FXML
    private DatePicker bookPubDate;

    @FXML
    private TextField bookPages;

    @FXML
    private TextField bookLanguage;

    @FXML
    private Label totalBook;

    @FXML
    private Label totalCD;

    @FXML
    private Label totalDVD;


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
//        for (Book book : listBook) {
//            LOGGER.info("Media ID: " + book.getId() + ", Date: " + book.getPublishDate());
//        }
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

    public void insertBook() throws SQLException {
        getBController().createBook(parseInt(bookId.getText()), bookTitle.getText(), bookCategory.getText(), parseInt(bookPrice.getText()), parseInt(bookValue.getText()), parseInt(bookQuantity.getText()), "book", bookAuthor.getText(), (String) bookCover.getValue(), bookPublisher.getText(), java.sql.Date.valueOf(bookPubDate.getValue()), parseInt(bookPages.getText()), bookLanguage.getText(), bookCategory.getText(), "assets/images/book/book3.jpg");
        showAllBook();
    }

    public void updateBook() throws SQLException {
        getBController().updateBook(parseInt(bookId.getText()), bookTitle.getText(), bookCategory.getText(), parseInt(bookPrice.getText()), parseInt(bookValue.getText()), parseInt(bookQuantity.getText()), "book", bookAuthor.getText(), (String) bookCover.getValue(), bookPublisher.getText(), java.sql.Date.valueOf(bookPubDate.getValue()), parseInt(bookPages.getText()), bookLanguage.getText(), bookCategory.getText());
        showAllBook();
    }

    public void deleteBook() throws SQLException {
        getBController().deleteBook(parseInt(bookId.getText()));
        showAllBook();
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

//    @FXML
//    void uploadMediaImage(ActionEvent event) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
//        );
//
//        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//        File selectedFile = fileChooser.showOpenDialog(stage);
//
//        if (selectedFile != null) {
//            String imagePath = selectedFile.getAbsolutePath();
//            // Save the imagePath to the database
//            saveImagePathToDatabase(imagePath);
//            System.out.println("Image Path: " + imagePath);
//        }
//    }

    @FXML
    void logout() throws IOException, InterruptedException, SQLException {
        this.homeScreenHandler.show();
    }
}
