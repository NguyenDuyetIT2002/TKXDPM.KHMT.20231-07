package entity.media;

import entity.db.AIMSDB;
import utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class DVD extends Media {

	private static Logger LOGGER = Utils.getLogger(Media.class.getName());

	String director;
	int runtime;
	String studio;
	String subtitles;
	Date releasedDate;
	String filmType;

	public DVD() throws SQLException {

	}

	public DVD(int id, String title, String category, int price, int value, int quantity, String type, String director,
			int runtime, String studio, String subtitles, Date releasedDate, String filmType) throws SQLException {
		super(id, title, category, price, value, quantity, type);
		this.director = director;
		this.runtime = runtime;
		this.studio = studio;
		this.subtitles = subtitles;
		this.releasedDate = releasedDate;
		this.filmType = filmType;
	}

	public String getDirector() {
		return this.director;
	}

	public DVD setDirector(String director) {
		this.director = director;
		return this;
	}

	public int getRuntime() {
		return this.runtime;
	}

	public DVD setRuntime(int runtime) {
		this.runtime = runtime;
		return this;
	}

	public String getStudio() {
		return this.studio;
	}

	public DVD setStudio(String studio) {
		this.studio = studio;
		return this;
	}

	public String getSubtitles() {
		return this.subtitles;
	}

	public DVD setSubtitles(String subtitles) {
		this.subtitles = subtitles;
		return this;
	}

	public Date getReleasedDate() {
		return this.releasedDate;
	}

	public DVD setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
		return this;
	}

	public String getFilmType() {
		return this.filmType;
	}

	public DVD setFilmType(String filmType) {
		this.filmType = filmType;
		return this;
	}

	@Override
	public Media getMediaById(int id) throws SQLException {
		String sql = "SELECT * FROM " + "DVD " + "INNER JOIN Media " + "ON Media.id = DVD.id " + "where Media.id = "
				+ id + ";";
		Statement stm = AIMSDB.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		if (res.next()) {
			String title = res.getString("title");
			String category = res.getString("category");
			int price = res.getInt("price");
			int value = res.getInt("value");
			int quantity = res.getInt("quantity");
			String type = res.getString("type");

			String director = res.getString("director");
			int runtime = res.getInt("runtime");
			String studio = res.getString("studio");
			String subtitles = res.getString("subtitles");
			Date releasedDate = res.getDate("releasedDate");
			String filmType = res.getString("filmType");

			return new DVD(id, title, category, price, value, quantity, type, director, runtime, studio, subtitles,
					releasedDate, filmType);
		} else {
			throw new SQLException();
		}
	}

	@Override
	public List<DVD> getAllMedia() throws SQLException {
		String sql = "SELECT * FROM " + "DVD " + "INNER JOIN Media " + "ON Media.id = DVD.id " + ";";
		Statement stm = AIMSDB.getConnection().createStatement();
		ResultSet res = stm.executeQuery(sql);
		ArrayList<DVD> dvdList = new ArrayList<>();
		while (res.next()) {
			int id = res.getInt(1);
			String title = res.getString("title");
			String category = res.getString("category");
			int price = res.getInt("price");
			int value = res.getInt("value");
			int quantity = res.getInt("quantity");
			String type = res.getString("type");

			String director = res.getString("director");
			int runtime = res.getInt("runtime");
			String studio = res.getString("studio");
			String subtitles = res.getString("subtitles");
			Date releasedDate = res.getDate("releasedDate");
			String filmType = res.getString("filmType");

			DVD dvd = new DVD(id, title, category, price, value, quantity, type, director, runtime, studio, subtitles,
					releasedDate, filmType);
			dvdList.add(dvd);
		}
		return dvdList;
	}

	public void createDVD(int id, String title, String category, int price, int value, int quantity, String type,
			String director, int runtime, String studio, String subtitles, Date releasedDate, String filmType,
			String imageUrl) {
		String insertMediaSql = "INSERT INTO Media (id, title, category, price, value, quantity, type, imageUrl) VALUES ("
				+ id + ", '" + title + "', '" + category + "', " + price + ", " + value + ", " + quantity + ", '" + type
				+ "', '" + imageUrl + "')";
		String insertDVDSql = "INSERT INTO DVD (id, director, runtime, studio, subtitles, releasedDate, filmType) VALUES ("
				+ id + ", '" + director + "', " + runtime + ", '" + studio + "', '" + subtitles + "', '" + releasedDate
				+ "', '" + filmType + "')";
		try {
			Statement stm = AIMSDB.getConnection().createStatement();
			stm.executeUpdate(insertMediaSql);
			stm.executeUpdate(insertDVDSql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateDVD(int id, String title, String category, int price, int value, int quantity, String type,
			String director, int runtime, String studio, String subtitles, Date releasedDate, String filmType) {
		String updateMediaSql = "UPDATE Media SET " + "title = '" + title + "', " + "category = '" + category + "', "
				+ "price = " + price + ", " + "value = " + value + ", " + "quantity = " + quantity + ", " + "type = '"
				+ type + "' " + "WHERE id = " + id;

		String updateDVDSql = "UPDATE DVD SET " + "director = '" + director + "', " + "runtime = " + runtime + ", "
				+ "studio = '" + studio + "', " + "subtitles = '" + subtitles + "', " + "releasedDate = '"
				+ releasedDate + "', " + "filmType = '" + filmType + "' " + "WHERE id = " + id;
		try {
			Statement stm = AIMSDB.getConnection().createStatement();
			stm.executeUpdate(updateMediaSql);
			stm.executeUpdate(updateDVDSql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteDVD(int id) {
		String deleteMediaSql = "DELETE FROM Media WHERE id = " + id;
		String deleteDVDSql = "DELETE FROM DVD WHERE id = " + id;
		try {
			Statement stm = AIMSDB.getConnection().createStatement();
			stm.executeUpdate(deleteMediaSql);
			stm.executeUpdate(deleteDVDSql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "{" + super.toString() + " director='" + director + "'" + ", runtime='" + runtime + "'" + ", studio='"
				+ studio + "'" + ", subtitles='" + subtitles + "'" + ", releasedDate='" + releasedDate + "'"
				+ ", filmType='" + filmType + "'" + "}";
	}
}
