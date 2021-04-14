package blacklist;

public class BlackList {
	private String id;

	public BlackList() {
	}

	public BlackList(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BlackList [id=" + id + "]";
	}

}
