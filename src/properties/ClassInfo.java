package properties;

public class ClassInfo {
	public final ClassName className;
	public final String professor;
	
	public ClassInfo(String className, String professor){
		this.className = ClassName.toClassName(className);
		this.professor = professor;
	}
	
	// Override equals():
	// Two ClassInfos are same if and only if their className and professor are same.
	@Override
	public boolean equals(Object o){
		if(o.getClass() != this.getClass()){
			return false;
		}
		ClassInfo c = (ClassInfo)o;
		return this.className.equals(c.className) 
				&& this.professor.equals(c.professor);
	}
	// Override hashCode()
	@Override
	public int hashCode(){
		return this.className.hashCode() + this.professor.hashCode();
	}
}
