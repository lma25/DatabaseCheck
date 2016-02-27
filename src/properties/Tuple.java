package properties;

// A Tuple contains the information of each line of the table,
// including class information and student's ID.
// Class information contains class name and professor name.
public class Tuple {
	// Fields.
	private ClassInfo classInfo;
	private int studentId;
	
	// Constructor.
	public Tuple(String cn, String p, int id){
		this.classInfo = new ClassInfo(cn, p);
		this.studentId = id;
	}

	// The following methods are getter of every field.
	public ClassInfo getClassInfo(){
		return this.classInfo;
	}
	public int getStudentId(){
		return this.studentId;
	}
}
