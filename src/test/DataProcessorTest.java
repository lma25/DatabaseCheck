package test;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import main.DataProcessor;
import main.DefaultValue;

import org.junit.Test;

public class DataProcessorTest {
	// The following function can add data to the database.
	// Example: After addClass("History", "Doe", 1, 3) executed,
	//			"History", "Doe", 1
	//			"History", "Doe", 2
	//			"History", "Doe", 3
	//			will be added to the database.
	public void addClass(String className, String professor, int studentStart, int studentEnd){
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new FileWriter(DefaultValue.TESTFILEPATH, true));
			for(int i = studentStart; i <= studentEnd; ++i){
				bw.write(className + ", " + professor + ", " + i);
				bw.newLine();
				bw.flush();
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(bw!=null) {
					bw.close();
				}
			}catch(Exception e){
			       System.out.println("Error in closing the BufferedWriter.");
			}
		}
		
	}
	@Test
	public void test() {
		// Create a new test database file.
		File file = new File(DefaultValue.TESTFILEPATH);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			file.delete();
		}
		
		// Write data into a test database file.
		addClass("Chemistry", "Joseph", 1, 40);	// Joseph's Chemistry has students whose IDs are from 1 to 40.
		addClass("Chemistry", "Jane", 41, 70);	// Jane's Chemistry has students whose IDs are from 41 to 70.
		addClass("History", "Jane", 1, 30);	// Jane's History has students whose IDs are from 1 to 30.
		addClass("History", "Mike", 31, 50);	// Mike's History has students whose IDs are from 31 to 50.
		addClass("Mathematics", "Doe", 1, 20);	// Doe's Mathematics has students whose IDs are from 1 to 20.
		addClass("Physics", "Tom", 20, 40);	// Tom's Physics has students whose IDs are from 20 to 40.
		
		// Start testing DataProcessor.
		DataProcessor processor = new DataProcessor(DefaultValue.TESTFILEPATH);
		assertEquals(40, processor.numberOfStudentInClass("Chemistry", "Joseph"));
		assertEquals(30, processor.numberOfStudentInClass("Chemistry", "Jane"));
		assertEquals(30, processor.numberOfStudentInClass("History", "Jane"));
		assertEquals(20, processor.numberOfStudentInClass("History", "Mike"));
		assertEquals(20, processor.numberOfStudentInClass("Mathematics", "Doe"));
		assertEquals(21, processor.numberOfStudentInClass("Physics", "Tom"));
		assertEquals(50, processor.numberOfStudentTakingMultiClasses());
		
		// Delete the test database file.
		file.delete();
	}

}
