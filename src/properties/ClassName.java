package properties;

public enum ClassName {
	// ClassName can only be history, chemistry, physics, mathematics.
	HISTORY, CHEMISTRY, PHYSICS, MATHEMATICS;
	// The following funciton convert a String type class name to the enum ClassName.
	public static ClassName toClassName(String className){
		ClassName result;
		switch(className.toLowerCase()){
			case "history": result = ClassName.HISTORY;
							break;
			case "chemistry": result = ClassName.CHEMISTRY;
							break;
			case "physics": result = ClassName.PHYSICS;
							break;
			case "mathematics": result = ClassName.MATHEMATICS;
							break;
			default: result = null;
							break;
		}
		return result;
	}
}
