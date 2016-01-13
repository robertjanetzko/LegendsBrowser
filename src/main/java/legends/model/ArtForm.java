package legends.model;

public class ArtForm {
	protected int id = -1;
	protected String name;
	protected int originEnId = -1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOriginEnId() {
		return originEnId;
	}

	public void setOriginEnId(int originEnId) {
		this.originEnId = originEnId;
	}

}
