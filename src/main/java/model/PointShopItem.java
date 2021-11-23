package model;

public class PointShopItem {
	
	private String name;
	private int price;
	private String image_address;
	private String description;
	private int game_id;
	
	//»ý¼ºÀÚ
	public PointShopItem() {}
	
	public PointShopItem(String name, int price, String image_address, String description, int game_id) {
		
		this.name = name;
		this.price = price;
		this.image_address = image_address;
		this.description = description;
		this.game_id = game_id;
		
	}

	//getter & setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

}
