package sinc.user.model.vo;

public class ChartVO {

	private String  country;
	private int		popularity;
	
	public ChartVO() {
	}
	
	public ChartVO(String country, int popularity) {
		this.country = country;
		this.popularity = popularity;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	
}
