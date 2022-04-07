package restaurant;

public class RestaurantDetailDTO {
	private RestaurantDTO restaurant;
	private int reviewCount;
	private int replyCount;
	private double rating;

	public RestaurantDetailDTO() {
		super();
	}

	public RestaurantDetailDTO(RestaurantDTO restaurant, int reviewCount, int replyCount, double rating) {
		this.restaurant = restaurant;
		this.reviewCount = reviewCount;
		this.replyCount = replyCount;
		this.rating = rating;
	}

	public RestaurantDTO getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantDTO restaurant) {
		this.restaurant = restaurant;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
}
