package controller;

import entity.media.Book;
import entity.media.CD;
import entity.media.DVD;
import entity.media.Media;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ManagerHomeController extends BaseController {
	public List getAllMedia() throws SQLException {
		return new Media().getAllMedia();
	}

	public int getCountMedia(String type) throws SQLException {
		return new Media().getCountMedia(type);
	}

	public List getAllBook() throws SQLException {
		return new Book().getAllMedia();
	}

	public void createBook(int id, String title, String category, int price, int value, int quantity, String type,
			String author, String coverType, String publisher, Date publishDate, int numOfPages, String language,
			String bookCategory, String imageUrl) throws SQLException {
		new Book().createBook(id, title, category, price, value, quantity, type, author, coverType, publisher,
				publishDate, numOfPages, language, bookCategory, imageUrl);
	}

	public void updateBook(int id, String title, String category, int price, int value, int quantity, String type,
			String author, String coverType, String publisher, Date publishDate, int numOfPages, String language,
			String bookCategory) throws SQLException {
		new Book().updateBook(id, title, category, price, value, quantity, type, author, coverType, publisher,
				publishDate, numOfPages, language, bookCategory);
	}

	public void deleteBook(int id) throws SQLException {
		new Book().deleteBook(id);
	}

	// Methods for CD
	public List getAllCD() throws SQLException {
		return new CD().getAllMedia();
	}

	public void createCD(int id, String title, String category, int price, int value, int quantity, String type,
			String artist, String recordLabel, Date releaseDate, String musicGenre, String imageUrl)
			throws SQLException {
		new CD().createCD(id, title, category, price, value, quantity, type, artist, recordLabel, musicGenre,
				releaseDate, imageUrl);
	}

	public void updateCD(int id, String title, String category, int price, int value, int quantity, String type,
			String artist, String recordLabel, Date releaseDate, String musicGenre) throws SQLException {
		new CD().updateCD(id, title, category, price, value, quantity, type, artist, recordLabel, musicGenre,
				releaseDate);
	}

	public void deleteCD(int id) throws SQLException {
		new CD().deleteCD(id);
	}

	// Methods for DVD
	public List getAllDVD() throws SQLException {
		return new DVD().getAllMedia();
	}

	public void createDVD(int id, String title, String category, int price, int value, int quantity, String type,
			String director, int runtime, String studio, String subtitles, Date releasedDate, String filmType,
			String imageUrl) throws SQLException {
		new DVD().createDVD(id, title, category, price, value, quantity, type, director, runtime, studio, subtitles,
				releasedDate, filmType, imageUrl);
	}

	public void updateDVD(int id, String title, String category, int price, int value, int quantity, String type,
			String director, int runtime, String studio, String subtitles, Date releasedDate, String filmType)
			throws SQLException {
		new DVD().updateDVD(id, title, category, price, value, quantity, type, director, runtime, studio, subtitles,
				releasedDate, filmType);
	}

	public void deleteDVD(int id) throws SQLException {
		new DVD().deleteDVD(id);
	}

}
