package review;

import restaurant.RestaurantDTO;

public class ReviewDetailDTO {
	private ReviewDTO review;
	private String writer;
	private int likeCount;
	private RestaurantDTO restaurant;
	private long overDate;
	private String orderedItems;

	public ReviewDetailDTO() {
		super();
	}

	public ReviewDetailDTO(ReviewDTO review, String writer, int likeCount, RestaurantDTO restaurant, long overDate,
			String orderedItems) {
		this.review = review;
		this.writer = writer;
		this.likeCount = likeCount;
		this.restaurant = restaurant;
		this.overDate = overDate;
		this.orderedItems = orderedItems;
	}

	public ReviewDTO getReview() {
		return review;
	}

	public void setReview(ReviewDTO review) {
		this.review = review;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public RestaurantDTO getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantDTO restaurant) {
		this.restaurant = restaurant;
	}

	public long getOverDate() {
		return overDate;
	}

	public void setOverDate(long overDate) {
		this.overDate = overDate;
	}

	public String getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(String orderedItems) {
		this.orderedItems = orderedItems;
	}
}
