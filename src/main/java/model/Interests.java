package model;

public class Interests {
	private int id;
	private int user_id;
	private int genre_id;

	public Interests() { 
		super();
	}
	public Interests(int id, int user_id, int genre_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.genre_id = genre_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
}
