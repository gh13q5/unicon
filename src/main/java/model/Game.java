package model;

public class Game {
	private int game_id;
	private String title;
	private String start_date;
	private String end_date;
	private String image_address;
	private String description;
	private String category;
	private String reward_image;
	private String reward_text;
	private int total_reservations;

	private int company_id;

	public Game() { 
		super();
	}
	// not null인 field만 받는 생성자
	public Game(int game_id, String title, String start_date, String end_date, String category, int total_reservations,
			int company_id) {
		super();
		this.game_id = game_id;
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.category = category;
		this.total_reservations = total_reservations;
		this.company_id = company_id;
	}
	// field 전부를 받는 생성자
	public Game(int game_id, String title, String start_date, String end_date, String image_address, String description,
			String category, String reward_image, String reward_text, int total_reservations, int company_id) {
		super();
		this.game_id = game_id;
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.image_address = image_address;
		this.description = description;
		this.category = category;
		this.reward_image = reward_image;
		this.reward_text = reward_text;
		this.total_reservations = total_reservations;
		this.company_id = company_id;
	}

	public int getGame_id() {
		return game_id;
	}
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getImage_address() {
		return image_address;
	}
	public void setImage_address(String image_address) {
		this.image_address = image_address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getReward_image() {
		return reward_image;
	}
	public void setReward_image(String reward_image) {
		this.reward_image = reward_image;
	}
	public String getReward_text() {
		return reward_text;
	}
	public void setReward_text(String reward_text) {
		this.reward_text = reward_text;
	}
	public int getTotal_reservations() {
		return total_reservations;
	}
	public void setTotal_reservations(int total_reservations) {
		this.total_reservations = total_reservations;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
}
