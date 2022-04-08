package review;

import restaurant.RestaurantDTO;

public class ReviewDetailDTO {
	private ReviewDTO review;
	private int likeCount;
	private RestaurantDTO restaurant;

	public ReviewDetailDTO() {
		super();
	}

	public ReviewDetailDTO(ReviewDTO review, int likeCount, RestaurantDTO restaurant) {
		this.review = review;
		this.likeCount = likeCount;
		this.restaurant = restaurant;
	}

	public ReviewDTO getReview() {
		return review;
	}

	public void setReview(ReviewDTO review) {
		this.review = review;
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
}
