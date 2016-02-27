package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import properties.ClassInfo;
import properties.Tuple;

public class DataProcessor {
	// tuples contains a list of Tuples of the database file.
	private LinkedList<Tuple> tuples = new LinkedList<>();
	
	// Convert each line of the database file to a Tuple and add it to the end of tuples.
	public DataProcessor(String path){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			while(line != null){
				tuples.add(convertToTuple(line));
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Convert one line in the database file to a tuple.
	private Tuple convertToTuple(String line){
		String[] attributes = line.split(", ");
		if(attributes.length != 3){
			return null;
		}		
		return new Tuple(attributes[0] , attributes[1], Integer.parseInt(attributes[2]));
	}
	
	// The following function returns number of students who registered for a certain Class.
	public int numberOfStudentInClass(String className, String professor){
		ClassInfo classInfo = new ClassInfo(className, professor);
		// Create a HashSet to save student ID.
		// When the student registered the class which matches the input, this student's ID will be add to the HashSet.
		HashSet<Integer> lookup = new HashSet<>();
		for(Tuple t : tuples){
			if(t.getClassInfo().equals(classInfo)){
				lookup.add(t.getStudentId());
			}
		}
		return lookup.size();
	}
	
	// The following function returns number of students who take more than one Class.
	public int numberOfStudentTakingMultiClasses(){
		int result = 0;
		// Create a HashMap. Key is student's ID. Value is a HashSet of classes that this student takes.
		HashMap<Integer, HashSet<ClassInfo>> lookup = new HashMap<>();
		// Iterate tuples and build the HashMap lookup.
		for(Tuple t : tuples){
			if(!lookup.containsKey(t.getStudentId())){
				lookup.put(t.getStudentId(), new HashSet<ClassInfo>());
			}
			lookup.get(t.getStudentId()).add(t.getClassInfo());
		}
		// Iterate every key(student ID) in lookup. When the class hashset's size > 1, make ++result.
		for(Integer id : lookup.keySet()){
			if(lookup.get(id).size() > 1){
				++result;
			}
		}
		return result;
	}
	
	// The inputs of main function are:
	// 		input file address, operation(1. get number of student in class. 2. get number of student who take more than one class),
	//		class name (if operation1), professor(if operation1).
	// Example: assume there is a file C:/database.txt.
	// 			when args = {"C://database.txt", "1", "History", "Jane"}, the result should be the number of student in Jane's History class.
	//			when args = {"C://database.txt", "1"}, the result should be the number of student who take more than one class.
	public static void main(String[] args){
		DataProcessor processor = new DataProcessor(args[0]);
		if(args[1].equals("1")){
			System.out.print("The number of student in " + args[3] + "'s " + args[2] + " class: ");
			System.out.println(processor.numberOfStudentInClass(args[2], args[3]));
		}else if(args[1].equals("2")){
			System.out.print("The number of student who take more than one class: ");
			System.out.println(processor.numberOfStudentTakingMultiClasses());
		}
	}
}
