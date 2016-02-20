package test;

public final class MyImmutable {

	private String myImmutableString;
	
	

	public MyImmutable(String myImmutableString) {
		super();
		this.myImmutableString = myImmutableString;
	}

	public String getMyImmutableString() {
		return myImmutableString;
	}

	public void setMyImmutableString(String myImmutableString) {
		this.myImmutableString = myImmutableString;
	}
	
	
}
