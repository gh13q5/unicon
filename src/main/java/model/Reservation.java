package model;

import java.util.Date;

public class Reservation {

	private Date reservation_date;
	private int game_id;
	private int user_id;
	
	//������
	public Reservation() {}
	
	public Reservation(Date reservation_date, int game_id, int user_id) {
		this.reservation_date = reservation_date;
		this.game_id = game_id;
		this.user_id = user_id;
	}

	//getter & setter
	public Date getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
