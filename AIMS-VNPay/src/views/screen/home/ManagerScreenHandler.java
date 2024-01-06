package views.screen.home;

import controller.ManagerHomeController;
import entity.media.Book;
import entity.media.CD;
import entity.media.Media;
import entity.user.User;
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

	boolean isBookInfoDisplayed = false;
	boolean isCDInfoDisplayed = false;

	public static Logger LOGGER = Utils.getLogger(LoginScreenHandler.class.getName());

	@FXML
	private Button userBtn;

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

	@FXML
	private AnchorPane userForm;

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
	private TextField bookId, bookTitle, bookValue, bookPrice, bookQuantity, bookAuthor, bookPublisher, bookPages,
			bookLanguage, bookCategory;

	@FXML
	private ComboBox<String> bookCover;
	@FXML
	private DatePicker bookPubDate;
	@FXML
	private Label totalBook;

	// CD
	@FXML
	private TableView<CD> cdTableView;

	@FXML
	private TableColumn<CD, Integer> cdIDCol, cdValueCol, cdPriceCol, cdQuantityCol;

	@FXML
	private TableColumn<CD, String> cdTypeCol, cdCategoryCol, cdTitleCol, cdArtistCol, cdRecordCol, cdMusicTypeCol;

	@FXML
	private TableColumn<CD, Date> cdReleasedCol;

	@FXML
	private Label totalCD;

	@FXML
	private Label labelCdId, labelCdTitle, labelCdCategory, labelCdPrice, labelCdValue, labelCdQuantity,
			labelCdArtist, labelCdRecordLabel, labelCdReleasedDate, labelCdMusicType;

	@FXML
	private TextField cdId, cdTitle, cdCategory, cdPrice, cdValue, cdQuantity, cdArtist, cdRecordLabel, cdMusicType;

	@FXML
	private DatePicker cdReleasedDate;

	@FXML
	private Label totalDVD;

	@FXML
	private Label labelBookId, labelBookTitle, labelBookValue, labelBookPrice, labelBookQuantity, labelBookAuthor,
			labelBookCover, labelBookPublisher, labelBookPubDate, labelBookPages, labelBookLanguage, labelBookCategory;

	@FXML
	private  TableView<User> userTableView;
	@FXML
	private TableColumn<User, Integer> userIDCol;
	@FXML
	private TableColumn<User, String> userNameCol, userAddressCol, userPhoneNumberCol, userEmailCol;
	@FXML
	private TextField userAddressField, userEmailField, userPhoneNumberField, userNameField;
	@FXML
	private Label userLabelForm;
	@FXML
	private Button saveCreateUserBtn, saveUpdateUserBtn, banUserBtn, createUserBtn, updateUserBtn, deleteUserBtn;
	@FXML
	private AnchorPane subUserForm;

	public ManagerHomeController getBController() {
		return (ManagerHomeController) super.getBController();
	}

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
		if (e.getSource() == homeBtn) {
			LOGGER.info("Navigate to home");
			homeForm.setVisible(true);
			bookForm.setVisible(false);
			cdForm.setVisible(false);
			dvdForm.setVisible(false);
			userForm.setVisible(false);
			showAllMedia();
			displayTotalMedia();
		} else if (e.getSource() == bookBtn) {
			homeForm.setVisible(false);
			bookForm.setVisible(true);
			cdForm.setVisible(false);
			dvdForm.setVisible(false);
			userForm.setVisible(false);
			showAllBook();
		} else if (e.getSource() == cdBtn) {
			homeForm.setVisible(false);
			bookForm.setVisible(false);
			cdForm.setVisible(true);
			dvdForm.setVisible(false);
			userForm.setVisible(false);
			showAllCD();
		} else if (e.getSource() == dvdBtn) {
			homeForm.setVisible(false);
			bookForm.setVisible(false);
			cdForm.setVisible(false);
			dvdForm.setVisible(true);
			userForm.setVisible(false);
		}else if (e.getSource() == userBtn) {
			homeForm.setVisible(false);
			bookForm.setVisible(false);
			cdForm.setVisible(false);
			dvdForm.setVisible(false);
			userForm.setVisible(true);
			showAllUser();
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
		isBookInfoDisplayed = false;
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

	public void showCDFields() {
		CD selectedCD = cdTableView.getSelectionModel().getSelectedItem();
		if (selectedCD != null) {
			cdId.setText(String.valueOf(selectedCD.getId()));
			cdTitle.setText(selectedCD.getTitle());
			cdCategory.setText(selectedCD.getCategory());
			cdPrice.setText(String.valueOf(selectedCD.getPrice()));
			cdValue.setText(String.valueOf(selectedCD.getValue()));
			try {
				cdQuantity.setText(String.valueOf(selectedCD.getQuantity()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cdArtist.setText(selectedCD.getArtist());
			cdRecordLabel.setText(selectedCD.getRecordLabel());
			cdMusicType.setText(selectedCD.getMusicType());
			// Đối với cdReleasedDate, bạn cần chuyển đổi ngày từ java.sql.Date hoặc java.util.Date sang LocalDate
			// Ví dụ: cdReleasedDate.setValue(selectedCD.getReleasedDate().toLocalDate());
		}

		cdId.setVisible(true);
		cdTitle.setVisible(true);
		cdCategory.setVisible(true);
		cdPrice.setVisible(true);
		cdValue.setVisible(true);
		cdQuantity.setVisible(true);
		cdArtist.setVisible(true);
		cdRecordLabel.setVisible(true);
		cdMusicType.setVisible(true);
		cdReleasedDate.setVisible(true);

		labelCdId.setVisible(true);
		labelCdTitle.setVisible(true);
		labelCdCategory.setVisible(true);
		labelCdPrice.setVisible(true);
		labelCdValue.setVisible(true);
		labelCdQuantity.setVisible(true);
		labelCdArtist.setVisible(true);
		labelCdRecordLabel.setVisible(true);
		labelCdMusicType.setVisible(true);
		labelCdReleasedDate.setVisible(true);

		isCDInfoDisplayed = true;
	}

	public void hideCDFields() {
		cdId.clear();
		cdTitle.clear();
		cdCategory.clear();
		cdPrice.clear();
		cdValue.clear();
		cdQuantity.clear();
		cdArtist.clear();
		cdRecordLabel.clear();
		cdMusicType.clear();
		cdReleasedDate.getEditor().clear(); // Clear the date picker text field

		cdId.setVisible(false);
		cdTitle.setVisible(false);
		cdCategory.setVisible(false);
		cdPrice.setVisible(false);
		cdValue.setVisible(false);
		cdQuantity.setVisible(false);
		cdArtist.setVisible(false);
		cdRecordLabel.setVisible(false);
		cdMusicType.setVisible(false);
		cdReleasedDate.setVisible(false);

		labelCdId.setVisible(false);
		labelCdTitle.setVisible(false);
		labelCdCategory.setVisible(false);
		labelCdPrice.setVisible(false);
		labelCdValue.setVisible(false);
		labelCdQuantity.setVisible(false);
		labelCdArtist.setVisible(false);
		labelCdRecordLabel.setVisible(false);
		labelCdMusicType.setVisible(false);
		labelCdReleasedDate.setVisible(false);

		isCDInfoDisplayed = false;
	}

	public void showAllUser() throws SQLException{
		List<User> listUser = getBController().getAllUser();
		userIDCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
		userNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		userEmailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		userAddressCol.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
		userPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
		userTableView.getItems().setAll(listUser);
		subUserForm.setVisible(false);
		userTableView.setRowFactory(tv -> new TableRow<User>() {
			@Override
			public void updateItem(User item, boolean empty) {
				super.updateItem(item, empty) ;
				if (item == null) {
					setStyle("");
				} else if (item.getBan()) {
					setStyle("-fx-background-color: red;");
				} else {
					setStyle("");
				}
			}
		});
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
		if (!isBookInfoDisplayed) {
			// Display information (first click)
			showBookFields();

			isBookInfoDisplayed = true;
		} else {
			// Create book (second click)
			try {
				int bookIdValue = safeParseInt(bookId.getText());
				int bookPriceValue = safeParseInt(bookPrice.getText());
				int bookValueValue = safeParseInt(bookValue.getText());
				int bookQuantityValue = safeParseInt(bookQuantity.getText());
				int bookPagesValue = safeParseInt(bookPages.getText());

				getBController().createBook(bookIdValue, bookTitle.getText(), bookCategory.getText(), bookPriceValue,
						bookValueValue, bookQuantityValue, "book", bookAuthor.getText(), (String) bookCover.getValue(),
						bookPublisher.getText(), java.sql.Date.valueOf(bookPubDate.getValue()), bookPagesValue,
						bookLanguage.getText(), bookCategory.getText(), "assets/images/book/book3.jpg");
				System.out.println("Publish Date Value: " + bookPubDate.getValue());

				hideBookFields();
				showAllBook();

				// Reset state for the next click
				isBookInfoDisplayed = false;
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
			showAlert(Alert.AlertType.WARNING, "No Selection", "No Book Selected",
					"Please select a book in the table.");
			return;
		} else

			try {
				if (!isBookInfoDisplayed) {
					showBookFields();
					isBookInfoDisplayed = true;

				} else {
					// Parse input values
					int bookPriceValue = safeParseInt(bookPrice.getText());
					int bookValueValue = safeParseInt(bookValue.getText());
					int bookQuantityValue = safeParseInt(bookQuantity.getText());
					int bookPagesValue = safeParseInt(bookPages.getText());
					java.sql.Date sqlPubDate = java.sql.Date.valueOf(bookPubDate.getValue()); // Chuyển LocalDate thành
					// java.sql.Date

					// Gọi phương thức updateBook với các giá trị đã được chuyển đổi
					getBController().updateBook(selectedBook.getId(), bookTitle.getText(), bookCategory.getText(),
							bookPriceValue, bookValueValue, bookQuantityValue, "book", bookAuthor.getText(),
							(String) bookCover.getValue(), bookPublisher.getText(), sqlPubDate, bookPagesValue,
							bookLanguage.getText(), bookCategory.getText());

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
			showAlert(Alert.AlertType.WARNING, "No Selection", "No Book Selected",
					"Please select a book in the table.");
			return;
		}
		Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this book?",
				ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> result = confirmAlert.showAndWait();
		if (result.get() == ButtonType.YES) {
			try {
				getBController().deleteBook(selectedBook.getId());
				showAllBook();
				showAlert(Alert.AlertType.INFORMATION, "Deletion Successful", "Book Deleted",
						"Book has been deleted successfully.");
			} catch (SQLException ex) {
				showAlert(Alert.AlertType.ERROR, "Deletion Failed", "Error Deleting Book",
						"There was an error deleting the book.");
			}
		}
	}

	public void showAllCD() throws SQLException {
		List<CD> listCD = getBController().getAllCD();
		cdIDCol.setCellValueFactory(new PropertyValueFactory<CD, Integer>("id"));
		cdValueCol.setCellValueFactory(new PropertyValueFactory<CD, Integer>("value"));
		cdPriceCol.setCellValueFactory(new PropertyValueFactory<CD, Integer>("price"));
		cdQuantityCol.setCellValueFactory(new PropertyValueFactory<CD, Integer>("quantity"));
		cdCategoryCol.setCellValueFactory(new PropertyValueFactory<CD, String>("category"));
		cdTitleCol.setCellValueFactory(new PropertyValueFactory<CD, String>("title"));
		cdArtistCol.setCellValueFactory(new PropertyValueFactory<CD, String>("artist"));
		cdRecordCol.setCellValueFactory(new PropertyValueFactory<CD, String>("recordLabel"));
		cdMusicTypeCol.setCellValueFactory(new PropertyValueFactory<CD, String>("musicType"));
		cdReleasedCol.setCellValueFactory(new PropertyValueFactory<CD, Date>("releasedDate"));
		cdTableView.getItems().setAll(listCD);
	}

	public void createCD(ActionEvent event) throws SQLException {
		if (!isCDInfoDisplayed) {
			// Display information (first click)
			showCDFields();
			isCDInfoDisplayed = true;
		} else {
			// Create CD (second click)
			try {
				int cdIdValue = safeParseInt(cdId.getText());
				int cdPriceValue = safeParseInt(cdPrice.getText());
				int cdValueValue = safeParseInt(cdValue.getText());
				int cdQuantityValue = safeParseInt(cdQuantity.getText());
				String imageUrl = "assets/images/cd/cd10.jpg";

				getBController().createCD(
						cdIdValue,
						cdTitle.getText(),
						cdCategory.getText(),
						cdPriceValue,
						cdValueValue,
						cdQuantityValue,
						"cd",
						cdArtist.getText(),
						cdRecordLabel.getText(),
						cdMusicType.getText(),
						java.sql.Date.valueOf(cdReleasedDate.getValue()),
						imageUrl
				);

				hideCDFields();
				showAllCD();
				isCDInfoDisplayed = false;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateCD() throws SQLException {
		CD selectedCD = cdTableView.getSelectionModel().getSelectedItem();

		if (selectedCD == null) {
			showAlert(Alert.AlertType.WARNING, "No Selection", "No CD Selected", "Please select a CD in the table.");
			return;
		}

		try {
			if (!isCDInfoDisplayed) {
				showCDFields();
				isCDInfoDisplayed = true;
			} else {
				int cdPriceValue = safeParseInt(cdPrice.getText());
				int cdValueValue = safeParseInt(cdValue.getText());
				int cdQuantityValue = safeParseInt(cdQuantity.getText());

				java.sql.Date sqlReleasedDate = java.sql.Date.valueOf(cdReleasedDate.getValue());

				getBController().updateCD(
						selectedCD.getId(),
						cdTitle.getText(),
						cdCategory.getText(),
						cdPriceValue,
						cdValueValue,
						cdQuantityValue,
						"cd",
						cdArtist.getText(),
						cdRecordLabel.getText(),
						cdMusicType.getText(),
						sqlReleasedDate
				);

				hideCDFields();
				showAllCD();
				isCDInfoDisplayed = false;
			}
		} catch (NumberFormatException e) {
			showAlert(Alert.AlertType.ERROR, "Invalid Input", "Input Error", "Please enter valid numeric values.");
			e.printStackTrace();
		}
	}

	public void deleteCD() throws SQLException {
		CD selectedCD = cdTableView.getSelectionModel().getSelectedItem();

		if (selectedCD == null) {
			showAlert(Alert.AlertType.WARNING, "No Selection", "No CD Selected", "Please select a CD in the table.");
			return;
		}

		Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this CD?", ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> result = confirmAlert.showAndWait();

		if (result.get() == ButtonType.YES) {
			try {
				getBController().deleteCD(selectedCD.getId());
				showAllCD();
				showAlert(Alert.AlertType.INFORMATION, "Deletion Successful", "CD Deleted", "CD has been deleted successfully.");
			} catch (SQLException ex) {
				showAlert(Alert.AlertType.ERROR, "Deletion Failed", "Error Deleting CD", "There was an error deleting the CD.");
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

	public void setCreateUserBtn() {
		userLabelForm.setText("Create User");
		subUserForm.setVisible(true);
		saveUpdateUserBtn.setVisible(false);
		saveCreateUserBtn.setVisible(true);

		userNameField.setText("");
		userAddressField.setText("");
		userPhoneNumberField.setText("");
		userEmailField.setText("");
	}

	public void setUpdateUserBtn() {
		User selectedUser = userTableView.getSelectionModel().getSelectedItem();
		if (selectedUser != null) {
			userLabelForm.setText("Edit User");
			subUserForm.setVisible(true);
			saveUpdateUserBtn.setVisible(true);
			saveCreateUserBtn.setVisible(false);

			userNameField.setText(selectedUser.getName());
			userAddressField.setText(selectedUser.getAddress());
			userPhoneNumberField.setText(selectedUser.getPhone());
			userEmailField.setText(selectedUser.getEmail());
		} else {
			subUserForm.setVisible(false);
		}
	}

	public void setBanUserBtn() throws SQLException {
		subUserForm.setVisible(false);
		User selectedUser = userTableView.getSelectionModel().getSelectedItem();
		if (selectedUser != null) {
			getBController().banUser(selectedUser.getId(), selectedUser.getBan());
			showAllUser();
		}
	}

	public void setDeleteUserBtn() throws SQLException {
		subUserForm.setVisible(false);
		User selectedUser = userTableView.getSelectionModel().getSelectedItem();
		if (selectedUser != null) {
			getBController().deleteUser(selectedUser.getId());
			showAllUser();
		}
	}

	public void setSaveCreateUserBtn() throws SQLException {
		String name = userNameField.getText();
		String address = userAddressField.getText();
		String phone = userPhoneNumberField.getText();
		String email = userEmailField.getText();

		getBController().createUser(name, email, address, phone);
		showAllUser();
	}

	public void setSaveUpdateUserBtn() throws SQLException{
		User selectedUser = userTableView.getSelectionModel().getSelectedItem();
		if (selectedUser != null) {
			int id = selectedUser.getId();
			String name = userNameField.getText();
			String address = userAddressField.getText();
			String phone = userPhoneNumberField.getText();
			String email = userEmailField.getText();

			getBController().updateUser(id, name, email, address, phone);
			showAllUser();
		} else {
			subUserForm.setVisible(false);
		}
	}
	@FXML
	void logout() throws IOException, InterruptedException, SQLException {
		this.homeScreenHandler.show();
	}
}
